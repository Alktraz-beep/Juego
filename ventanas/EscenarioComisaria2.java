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
Clase que implementa el segundo escenario de la comisaria donde el detective esta apunto de salir de ella, donde se presenta por primera vez
los segundos enemigos mas fuertes, azathot e implementa otro botiquin para recuperar vida el detective con la misma musica pero diferente escenario
**/
public class EscenarioComisaria2 extends AnimationTimer{
	private  static final String RUTA_FONDO1=new File("./Images/escenarios/esc2.png").getAbsolutePath();
	
	private  static final String RUTA_CURSOR=new File("./Images/menu/Cursor.png").getAbsolutePath(); //poner ruta de cursor imagen
	private  static final String RUTA_MFONDO1=new File("./Music/Alone1.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_MMuerto=new File("./Music/Muerto.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_DISPARO=new File("./Music/Disparo.mp3").getAbsolutePath(); //ruta de audio ambiente

	private int CANTIDAD_DE_ENEMIGOS=6;//este puede cambiar con escenas
	private ImageView BACKGROUND;;
	private static Image imagenCursor=new Image(new File(RUTA_CURSOR).toURI().toString());
	private static ImageCursor imagenC;
	
	private static Media mediaFondo=new Media(new File(RUTA_MFONDO1).toURI().toString());
	private static MediaPlayer mFondo;
	private static Media mediaDisparo=new Media(new File(RUTA_DISPARO).toURI().toString());
	private static MediaPlayer mDisparo=new MediaPlayer(mediaDisparo);
	private static Media mediaMuerto=new Media(new File(RUTA_MMuerto).toURI().toString());
	private static MediaPlayer mMuerto=new MediaPlayer(mediaMuerto);
	//enemigos
	//jugador
	private Etiqueta vida;
	private Etiqueta puntos;
	private Etiqueta botiquines;
	//Objetos
	private static int numEscenario=1;
	private ArrayList<Hibryd> hibridos;
	private ArrayList<Azathot> azathots;
	private Botiquin botiquin;
	private GridPane grid;
	private ArrayList<String> input=new ArrayList<String>();
	private	boolean escenarioTerminado=false;
	/**
	Constructor de el segundo escenario comisaria donde implementa e inicializa un arreglo de Hybrid y de Azathot y un botiquin que es
	escencial para el escenario
	@param grid GridPane que es el grid del juego pasado por la anterior escena para añadir y quitar las actualizaciones de enemigos y personajes para este escenario
	**/
	public EscenarioComisaria2(GridPane grid){
		this.grid=grid;
		this.imagenC=new ImageCursor(this.imagenCursor,this.imagenCursor.getWidth()*3,this.imagenCursor.getHeight()*3);
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
		this.botiquin=new Botiquin();

		hibridos=new ArrayList<Hibryd>();
		for(int i=0;i<4;i++){
			hibridos.add(new Hibryd(205,500));
		}

		azathots=new ArrayList<Azathot>();
		for(int i=0;i<2;i++){
			azathots.add(new Azathot());
			azathots.get(i).setRapidez(1);
		}

		//iniciar fondo etc

	}

	/**
	Metodo sobreescrito que permite crear el bicle de juego para el escenario parte dos de la comisaria
	@param current long que es el tiempo transcurrido
	**/
	@Override
	public void handle(long current){
		update();	
		vida.setText("Vida : "+Detective.getVida());
		puntos.setText("Score : "+Detective.getPuntos());
		botiquines.setText("Botiquines : "+Detective.getNumBotiquines());
		
		Iterator<Azathot> azaIter=azathots.iterator();
		while(azaIter.hasNext()){
			Azathot azathot=azaIter.next();	
			azathot.moverHaciaObjetivo(current);
			azathot.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent m){
					azathot.bajarVida(Detective.getInstance().getDano());
					if(!azathot.estaVivo()){
						Detective.setPuntos(100);
						CANTIDAD_DE_ENEMIGOS-=1;
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
			if(Detective.getInstance().getBoundsInParent().intersects(azathot.getBoundsInParent()) && azathot.estaVivo()){
				Detective.getInstance().bajarVida(azathot.golpe);
				Detective.mostrarDano();
				Detective.setPuntos(-10);
			}else if(!Detective.getInstance().getBoundsInParent().intersects(azathot.getBoundsInParent())){
				Detective.mostrarNormal();
			}
			if(azathot.getVida()<=0){
				this.grid.getChildren().remove(azathot);
				azaIter.remove();
			}
		}
		Iterator<Hibryd> hibIter=hibridos.iterator();
		while(hibIter.hasNext()){
			Hibryd hibrido=hibIter.next();	
			hibrido.moverHaciaObjetivo(current);
			hibrido.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent m){
					hibrido.bajarVida(Detective.getInstance().getDano());
					if(!hibrido.estaVivo()){
						Detective.setPuntos(100);
						CANTIDAD_DE_ENEMIGOS-=1;
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

			if(Detective.getInstance().getBoundsInParent().intersects(hibrido.getBoundsInParent()) && hibrido.estaVivo()){
				Detective.getInstance().bajarVida(hibrido.golpe);
				Detective.mostrarDano();
				Detective.setPuntos(-10);
			}else if(!Detective.getInstance().getBoundsInParent().intersects(hibrido.getBoundsInParent())){
				Detective.mostrarNormal();
			}
			if(hibrido.getVida()<=0){
				this.grid.getChildren().remove(hibrido);
				hibIter.remove();
			}
			//System.out.println(" "+Detective.getInstance().getTranslateX()+" "+Detective.getInstance().getTranslateY());
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
		if(Detective.getInstance().getTranslateX()>NuevoJuego.ANCHO_DE_LA_ESCENA && CANTIDAD_DE_ENEMIGOS<=0){
			this.stop();
			Detective.getInstance().setTranslateX(530);
			Detective.getInstance().setTranslateY(270);
			Detective.getInstance().setPiso(270);
			Detective.getInstance().setAncho(70);
			Detective.getInstance().setFitWidth(70);
			Detective.getInstance().setAlto(120);
			Detective.getInstance().setFitHeight(120);
			Detective.getInstance().setPos(530,270);
			Detective.getInstance().setRapidez(2);
			System.out.println("Escenario 2 terminado");
			grid.getChildren().clear();
			mFondo.stop();
			EscenarioComisaria3 comisaria3=new EscenarioComisaria3(this.grid);
			grid.getChildren().add(comisaria3.getBackground());
			grid.getChildren().addAll(comisaria3.getBotiquin());
			grid.getChildren().add(comisaria3.getMejora());
			grid.getChildren().add(Detective.getInstance());
			grid.getChildren().add(comisaria3.getBotiquinesLbl());
			grid.getChildren().add(comisaria3.getVida());
			grid.getChildren().add(comisaria3.getPuntos());
			grid.getChildren().addAll(comisaria3.getHibridos());
			grid.getChildren().addAll(comisaria3.getAzathots());
			comisaria3.start();
		}
	}

	/**
	Metodo que permite hacer las actualizaciones para la entrada de teclado del usuario, asi como las acciones del detective que se envian al bucle de juego
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
		if(this.botiquin.getBoundsInParent().intersects(Detective.getInstance().getBoundsInParent()) && NuevoJuego.input.contains("E") && !this.botiquin.getRecogido()){
			grid.getChildren().remove(this.botiquin);
			Detective.addBotiquin();
			this.botiquin.setRecogido();
		}
	}
	/**
	Metodo que obtiene la vida de el escenario como etiqueta
	@return vida Etiqueta que es la vida del jugador que se obtiene
	**/
	public Etiqueta getVida(){
		return this.vida;
	}
	/**
	Metodo que obtiene el numero de botiquines
	@return botiquines que son los botiquines que el jugador a recogido
	**/
	public Etiqueta getBotiquinesLbl(){
		return this.botiquines;
	}
	/**
	Metodo que obtiene los puntos en este escenario
	@return puntos que son los puntos obtenidos por el jugador
	**/
	public Etiqueta getPuntos(){
		return this.puntos;
	}
	/**
	Metodo que regresa la imagen para el escenario 2 de la comisaria
	@return BACKGROUND que es el fondo de este escenario
	**/
	public ImageView getBackground(){
		return BACKGROUND;
	}
	/**
	Metodo que regresa una lista de hibridos que son enemigos de la escena 2 de la comisaria
	@return hibridos ArrayList que son los enemigos de tipo hibryd
	**/
	public ArrayList<Hibryd> getHibridos(){
		return this.hibridos;
	}
	/**
	Metodo que regresa los enemigos de tipo Azathot que son mas fuertes de la comisaria en la parte 2
	@return azathots que son los enemigos en forma de arreglo
	**/
	public ArrayList<Azathot> getAzathots(){
		return this.azathots;
	}
	/**
	Metodo que regresa los botiquines que hay en el escenario
	@return botiquin Botiquin que es el botiquin que almacena el jugador
	**/
	public Botiquin getBotiquin(){
		return this.botiquin;
	}
	/**
	Metodo que verifica si ya se termino esta escena, el escenario 2
	@return escenarioTerminado boolean que es la la verificacion de que se termino el escenario
	**/
	public boolean terminado(){
		return this.escenarioTerminado;
	}
	
}
