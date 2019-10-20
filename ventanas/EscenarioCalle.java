package unam.fi.poo.ventanas;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;
import java.io.File;
import java.util.*;//de aqui obtiene Iterator y Arraylist
import javafx.util.Duration;//de aqui obtiene Duration
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.ImageCursor;
import javafx.scene.shape.Circle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import unam.fi.poo.controles.*;
import unam.fi.poo.personajes.*;
import unam.fi.poo.objetos.*;
import unam.fi.poo.ventanas.*;
/**
Escenartio 3 que es la calle, esta clase permite implementar el tercer escenario en la calle, con el enfrentamiento del primer jefe 
que es Nyarlathotep en el cual se muestra tambíen 3 botiquines que puede usar el jugador
**/
public class EscenarioCalle extends AnimationTimer{
	private  static final String RUTA_FONDO1=new File("./Images/escenarios/Calle.png").getAbsolutePath();
	
	private  static final String RUTA_MFONDO1=new File("./Music/boss1.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_MMuerto=new File("./Music/Muerto.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_MMuertoBoss=new File("./Music/muerteBos1.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_DISPARO=new File("./Music/Disparo.mp3").getAbsolutePath(); //ruta de audio ambiente

	private static final int CANTIDAD_DE_ENEMIGOS=1;//este puede cambiar con escenas
	private ImageView BACKGROUND;;
	
	private static Media mediaFondo=new Media(new File(RUTA_MFONDO1).toURI().toString());
	private static MediaPlayer mFondo;
	private static Media mediaDisparo=new Media(new File(RUTA_DISPARO).toURI().toString());
	private static MediaPlayer mDisparo=new MediaPlayer(mediaDisparo);
	private static Media mediaMuerto=new Media(new File(RUTA_MMuerto).toURI().toString());
	private static MediaPlayer mMuerto=new MediaPlayer(mediaMuerto);
	private static Media mediaMuertoBos=new Media(new File(RUTA_MMuertoBoss).toURI().toString());
	private static MediaPlayer mBoss=new MediaPlayer(mediaMuertoBos);
	//enemigos
	//jugador
	private Etiqueta vida;
	private Etiqueta vidaBoss;
	private Etiqueta puntos;
	private Etiqueta botiquines;
	private Nyarlathotep ny;
	//Objetos
	private static int numEscenario=1;

	private ArrayList<Botiquin> botiquinesArray;

	private GridPane grid;
	private ArrayList<String> input=new ArrayList<String>();
	/**
	Constructor del escenario en la calle que permite inicializar al jefe Nyarlathotep y a los botiquines, ademas de la imagen de escenario y la nueva musica de fondo
	@param grid GridPane que es el grid que se usa para que se pueda añadir o no cosas al escenario
	**/
	public EscenarioCalle(GridPane grid){
		this.grid=grid;
		this.BACKGROUND=new ImageView();
		this.BACKGROUND.setImage(new Image(new File(RUTA_FONDO1).toURI().toString(),NuevoJuego.ANCHO_DE_LA_ESCENA,NuevoJuego.ALTO_DE_LA_ESCENA,false,false));
		this.mFondo=new MediaPlayer(mediaFondo);
		mFondo.setAutoPlay(true);
		mFondo.setVolume(0.5);
		//Etiquetas
		this.vida=new Etiqueta("100");
		this.vida.setTranslateX(1100);
		this.vida.setTranslateY(-300);
		this.vida.setTextFill(Color.web("#0076a3"));
		this.vida.setFont(Font.font(Font.getFontNames().get(200)));

		this.vidaBoss=new Etiqueta("100");
		this.vidaBoss.setTranslateX(900);
		this.vidaBoss.setTranslateY(-300);
		this.vidaBoss.setTextFill(Color.web("#0076a3"));
		this.vidaBoss.setFont(Font.font(Font.getFontNames().get(200)));

		this.puntos=new Etiqueta("100");
		this.puntos.setTranslateX(1100);
		this.puntos.setTranslateY(-270);
		this.puntos.setTextFill(Color.RED);
		this.puntos.setFont(Font.font(Font.getFontNames().get(200)));

		this.botiquines=new Etiqueta("100");
		this.botiquines.setTranslateX(1100);
		this.botiquines.setTranslateY(-240);
		this.botiquines.setTextFill(Color.GREEN);
		this.botiquines.setFont(Font.font(Font.getFontNames().get(200)));

		//iniciando al jugador y enemigos
		this.botiquinesArray=new ArrayList<Botiquin>();
		for(int i=0;i<3;i++){
			this.botiquinesArray.add(new Botiquin());
			this.botiquinesArray.get(i).setFitWidth(70);
			this.botiquinesArray.get(i).setFitHeight(40);
			this.botiquinesArray.get(i).setTranslateY(300);
			this.botiquinesArray.get(i).setTranslateX(300+i*300);
		}

		this.ny=new Nyarlathotep(200,500,140);

	}

	/**
	Metodo sobreescrito que permite hacer el nuevo bucle para la batalla contra el jefe Nyarlathotep con sus respectivos movimientos para batalla
	con una vida mas grande y con movimiento para que sea una dificultad moderada
	@param current long que es  el tiempo transcurrido
	**/
	public void handle(long current){
		update();	
		vida.setText("Vida : "+Detective.getVida());
		vidaBoss.setText("Vida Nyarlathotep: "+ny.getVida());
		puntos.setText("Score : "+Detective.getPuntos());
		botiquines.setText("Botiquines : "+Detective.getNumBotiquines());
		
		this.ny.volar();
		this.ny.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e){
				ny.bajarVida(Detective.getInstance().getDano());
				if(!ny.estaVivo()){
					Detective.getInstance().setPuntos(100000);
					mBoss.play();
					mBoss.setOnEndOfMedia(
						new Runnable(){
							public void run(){
								mBoss.stop();
							}
						}
					);
				}
				mDisparo.play();
				mDisparo.setOnEndOfMedia(
					new Runnable(){
						public void run(){
							mDisparo.stop();
						}
					}
				);
			}
		});
		if(Detective.getInstance().getBoundsInParent().intersects(this.ny.getBoundsInParent()) && this.ny.estaVivo()){
			Detective.getInstance().bajarVida(this.ny.golpe);
			Detective.getInstance().mostrarDano();
			Detective.getInstance().setPuntos(-10);
		}else if(!Detective.getInstance().getBoundsInParent().intersects(this.ny.getBoundsInParent())){
			Detective.getInstance().mostrarNormal();
		}
		if(!this.ny.estaVivo()){
			grid.getChildren().remove(this.ny);
			this.vidaBoss.setText("Vida Nyarlathotep: MUERTO");
		}
		if(!Detective.estaVivo()){
			vida.setText("Vida : muerto");
			this.grid.getChildren().remove(Detective.getInstance());
			mMuerto.play();
			mFondo.stop();
			this.stop();
		}
		if(VentanaSalir.getDecision()){
			this.stop();
			mMuerto.stop();
			mFondo.stop();
		}
		if(Detective.getInstance().getTranslateX()>NuevoJuego.ANCHO_DE_LA_ESCENA && !ny.estaVivo()){
			this.stop();
			Detective.getInstance().setTranslateX(-8);
			Detective.getInstance().setTranslateY(140);
			Detective.getInstance().setPos(-8,140);
			Detective.getInstance().setPiso(141);
			Detective.getInstance().setFitWidth(200);
			Detective.getInstance().setAncho(200);
			Detective.getInstance().setFitHeight(350);
			Detective.getInstance().setAlto(230);
			Detective.getInstance().setRapidez(2);
			System.out.println("Escenario 4 terminado");
			grid.getChildren().clear();
			mFondo.stop();
			EscenarioCementerio cem=new EscenarioCementerio(this.grid);
			grid.getChildren().add(cem.getBackground());
			grid.getChildren().add(cem.getBotiquinesLbl());
			grid.getChildren().add(cem.getVida());
			grid.getChildren().add(cem.getPuntos());
			grid.getChildren().addAll(cem.getBotiquin());
			grid.getChildren().addAll(cem.getMejora());
			grid.getChildren().addAll(cem.getAzathots());
			grid.getChildren().add(Detective.getInstance());
			grid.getChildren().addAll(cem.getCY());
			cem.start();
		}
	}

	/**
	Metodo que permite actualizar la entrada del usuario de acuerdo a los objetos que hay en el escenario
	**/
	public void update(){
		if(NuevoJuego.input.contains(KeyCode.DOWN.toString()) || NuevoJuego.input.contains("S")){
			Detective.getInstance().moverY(2);	
		}
		if(NuevoJuego.input.contains(KeyCode.RIGHT.toString()) || NuevoJuego.input.contains("D")){
			Detective.getInstance().moverX(2);	
		}
		if(NuevoJuego.input.contains(KeyCode.LEFT.toString()) || NuevoJuego.input.contains("A")){
			Detective.getInstance().moverX(-2);	
		}
		Iterator<Botiquin> botIter=botiquinesArray.iterator();
		while(botIter.hasNext()){
			Botiquin botiquin=botIter.next();
			if(botiquin.getBoundsInParent().intersects(Detective.getInstance().getBoundsInParent()) && NuevoJuego.input.contains("E") && !botiquin.getRecogido()){
				grid.getChildren().remove(botiquin);
				Detective.addBotiquin();
				botiquin.setRecogido();
			}
		}
	}
	/**
	Metodo que obtiene la vida para mostrarla en el escenario
	@return vida que es la vida del jugador actual
	**/
	public Etiqueta getVida(){
		return this.vida;
	}
	/**
	Metodo que obtiene la vida  del jefe para mostrarla en el escenario
	@return vidaBoss que es la vida del jefe Nyarlathotep actual
	**/
	public Etiqueta getVidaBoss(){
		return this.vidaBoss;
	}
	/**
	Metodo que permite obtener el numero de botiqunes del juegador para mostrarlos en la escena
	@return botiquines que es una etiqueta y muestra el numero actual de botiquines
	**/
	public Etiqueta getBotiquinesLbl(){
		return this.botiquines;
	}
	/**
	Metodo que permite obtener los puntos del juegador para mostrarlos en la escena
	@return puntos que es una etiqueta y muestra el numero actual de puntos
	**/
	public Etiqueta getPuntos(){
		return this.puntos;
	}
	/**
	Metodo que obtiene la imagen del background que es la calle
	@return BACKGROUND que es el escenario de la calle en imagen
	**/
	public ImageView getBackground(){
		return BACKGROUND;
	}
	/**
	Metodo que permite obtener los botiquines que se implementan en el escenario y usará el Detective
	@return botiquinesArray que son los botiquines que el jugador recoje
	**/
	public ArrayList<Botiquin> getBotiquin(){
		return this.botiquinesArray;
	}
	/**
	Metodo que regresa al jefe que es Nyarlathotep
	@return ny que es Nyarlathotep un enemigo
	**/
	public Nyarlathotep getNy(){
		return this.ny;
	}
}
