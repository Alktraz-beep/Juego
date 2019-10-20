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
Clase que implementa el octavo escenario donde se muestra el muelle, hay 3 botiquines y una mejora tipo A antes del ultimo boss
@version 28/05/2019
**/
public class EscenarioMuelle extends AnimationTimer{
	private  static final String RUTA_FONDO1=new File("./Images/escenarios/Muelle1.png").getAbsolutePath();
	
	private  static final String RUTA_MFONDO1=new File("./Music/Alone2.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_MMuerto=new File("./Music/Muerto.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_DISPARO=new File("./Music/Disparo.mp3").getAbsolutePath(); //ruta de audio ambiente

	private int CANTIDAD_DE_ENEMIGOS=17;//este puede cambiar con escenas
	private ImageView BACKGROUND;;
	
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
	private ArrayList<Hibryd> hibs;
	private ArrayList<Azathot> azathots;
	private ArrayList<Cyaegha> cys;
	private ArrayList<Botiquin> botiquinesArray;
	private MejoraDePistolaTipoA mpa;

	private GridPane grid;
	private ArrayList<String> input=new ArrayList<String>();
	/**
	Constructor que permite realizar la inicializacion de los enemigos y de las mejoras asi como tambien de los botiquines
	en sus posiciones respectivas
	@param grid GridPane para situar los objetos y los enemigos
	**/
	public EscenarioMuelle(GridPane grid){
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
			this.botiquinesArray.get(i).setFitHeight(30);
			this.botiquinesArray.get(i).setTranslateY(140);
			this.botiquinesArray.get(i).setTranslateX(400+i*200);
		}

		this.mpa=new MejoraDePistolaTipoA(20,20,1000,140);

		this.cys=new ArrayList<Cyaegha>();
		for(int i=0;i<5;i++){
			cys.add(new Cyaegha(70,100,Detective.getInstance().getTranslateY()));
			cys.get(i).setFitWidth(Detective.getInstance().getFitWidth()+50);
			cys.get(i).setFitHeight(Detective.getInstance().getFitHeight()+70);
			cys.get(i).setRapidez(0.5);
		}

		this.azathots=new ArrayList<Azathot>();
		for(int i=0;i<5;i++){
			azathots.add(new Azathot());
			azathots.get(i).setFitWidth(Detective.getInstance().getFitWidth()+50);
			azathots.get(i).setFitHeight(Detective.getInstance().getFitHeight()+50);
			azathots.get(i).setTranslateY(Detective.getInstance().getTranslateY());
			azathots.get(i).setRapidez(0.7);
		}
		this.hibs=new ArrayList<Hibryd>();
		for(int i=0;i<7;i++){
			hibs.add(new Hibryd(1,1));
			hibs.get(i).setFitWidth(Detective.getInstance().getFitWidth()+10);
			hibs.get(i).setFitHeight(Detective.getInstance().getFitHeight()+20);
			hibs.get(i).setTranslateY(Detective.getInstance().getTranslateY());
			hibs.get(i).setRapidez(0.7);
		}

		//iniciar fondo etc

	}

	/**
	Metodo sobreescrito del AnimationTimer que permite hacer el bucle de juego  con las etiquetas 
	permite implementar el movimiento de cada personaje
	@param current que es el tiempo transcurrido
	**/
	@Override
	public void handle(long current){
		update();	
		vida.setText("Vida "+Detective.getInstance().getNombre()+": "+Detective.getVida());
		puntos.setText("Score : "+Detective.getPuntos());
		botiquines.setText("Botiquines : "+Detective.getNumBotiquines());
		
		Iterator<Hibryd> hibIter=hibs.iterator();
		while(hibIter.hasNext()){
			Hibryd hib=hibIter.next();	
			hib.moverHaciaObjetivo(current);
			hib.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent m){
					hib.bajarVida(Detective.getInstance().getDano());
					if(!hib.estaVivo()){
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
			if(Detective.getInstance().getBoundsInParent().intersects(hib.getBoundsInParent()) && hib.estaVivo()){
				Detective.getInstance().bajarVida(hib.golpe);
				Detective.mostrarDano();
				Detective.setPuntos(-10);
			}else if(!Detective.getInstance().getBoundsInParent().intersects(hib.getBoundsInParent())){
				Detective.mostrarNormal();
			}
			if(hib.getVida()<=0){
				this.grid.getChildren().remove(hib);
				hibIter.remove();
			}
		}
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
		Iterator<Cyaegha> cyIter=cys.iterator();
		while(cyIter.hasNext()){
			Cyaegha cy=cyIter.next();	
			cy.moverHaciaObjetivo(current);
			cy.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent m){
					cy.bajarVida(Detective.getInstance().getDano());
					if(!cy.estaVivo()){
						Detective.setPuntos(1000);
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

			if(Detective.getInstance().getBoundsInParent().intersects(cy.getBoundsInParent()) && cy.estaVivo()){
				Detective.getInstance().bajarVida(cy.golpe);
				Detective.mostrarDano();
				Detective.setPuntos(-10);
			}else if(!Detective.getInstance().getBoundsInParent().intersects(cy.getBoundsInParent())){
				Detective.mostrarNormal();
			}
			if(cy.getVida()<=0){
				this.grid.getChildren().remove(cy);
				cyIter.remove();
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
			Detective.getInstance().setTranslateX(22);
			Detective.getInstance().setTranslateY(100);
			Detective.getInstance().setPos(22,100);
			Detective.getInstance().setPiso(141);
			Detective.getInstance().setFitWidth(50);
			Detective.getInstance().setAncho(50);
			Detective.getInstance().setFitHeight(80);
			Detective.getInstance().setAlto(100);
			Detective.getInstance().setRapidez(2);
			System.out.println("Escenario 7 terminado");
			grid.getChildren().clear();
			mFondo.stop();
			EscenarioFinal escfinal=new EscenarioFinal(this.grid);

			grid.getChildren().add(escfinal.getBackground());
			grid.getChildren().add(escfinal.getCt());
			grid.getChildren().add(escfinal.getBackground2());
			grid.getChildren().addAll(escfinal.getBotiquin());
			grid.getChildren().add(Detective.getInstance());
			grid.getChildren().addAll(escfinal.getVida());
			grid.getChildren().addAll(escfinal.getVidaBoss());
			grid.getChildren().addAll(escfinal.getPuntos());
			grid.getChildren().addAll(escfinal.getBotiquinesLbl());
			escfinal.start();
		}
	}

	/**
	Metodo que actualiza las diferentes teclas que ingresa el jugador y las acciones del detective
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
		if(this.mpa.getBoundsInParent().intersects(Detective.getInstance().getBoundsInParent()) && NuevoJuego.input.contains("E") && !this.mpa.getRecogido()){
			grid.getChildren().remove(this.mpa);
			Detective.addMejora(this.mpa);
			this.mpa.setRecogido();
		}
		
	}
	/**
	Este metodo permite obtener la vida del jugador y mostrarla en el juego
	@return vida que es la vida del jugador actual
	**/
	public Etiqueta getVida(){
		return this.vida;
	}
	/**
	Este metodo permite obtener el numero de botiquines que recoje 
	@return botiquines que es el numero de botiquines en forma de etiqueta
	**/
	public Etiqueta getBotiquinesLbl(){
		return this.botiquines;
	}
	/**
	Este metodo permite regresar el numero actual de puntos y mostrarlos
	@return puntos que son los puntos de tipo Etiqueta
	**/
	public Etiqueta getPuntos(){
		return this.puntos;
	}
	/**
	Metodo que regresa el fondo de la escena, que es el tercer escenario afuera de la comisaria
	@return BACKGROUND que es la imagen del fondo
	**/
	public ImageView getBackground(){
		return BACKGROUND;
	}
	/**
	Metodo que regresa la lista de Cyaegha para mostrarlos en el escenario
	@return cys que son los enemigos del Detective
	**/
	public ArrayList<Cyaegha> getCY(){
		return this.cys;
	}
	/**
	Metodo que regresa los enemigos de tipo Azathot
	@return azathots que son los enemigos de tipo Azathot
	**/
	public ArrayList<Azathot> getAzathots(){
		return this.azathots;
	}
	/**
	Metodo que regresa los enemigos de tipo hibryd
	@return hibs que son los enemigos de tipo Hibryd 
	**/
	public ArrayList<Hibryd> getHibs(){
		return this.hibs;
	}
	/**
	Metodo que regresa los botiquines que recogera el jugador
	@return botiquinesArray que es el array de botiquines que usa el jugador
	**/
	public ArrayList<Botiquin> getBotiquin(){
		return this.botiquinesArray;
	}
	/**
	Metodo que permite regresar la mejora de pistola de este escenario que es de tipo B
	@return mpb es una mejora de pistola tipo B,aumenta el daño
	**/
	public  MejoraDePistola getMejora(){
		return this.mpa;
	}
}
