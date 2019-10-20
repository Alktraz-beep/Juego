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
Clase EscenarioComisaria, esta clase permite implementar el primer ciclo del primer escenario donde se implementan hybrid y un botiquin
ademas de la musica de ambiente y la logica del juego en el escenario de la comisaria
hereda de la clase AnimationTimer la que le permite implementar el bucle de juego en uno de los hilos
**/
public class EscenarioComisaria extends AnimationTimer{
	private  static final String RUTA_FONDO1=new File("./Images/escenarios/Comisaria.png").getAbsolutePath();
	
	private  static final String RUTA_CURSOR=new File("./Images/menu/Cursor.png").getAbsolutePath(); //poner ruta de cursor imagen
	private  static final String RUTA_MFONDO1=new File("./Music/Alone1.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_MMuerto=new File("./Music/Muerto.mp3").getAbsolutePath(); //ruta de audio ambiente
	private  static final String RUTA_DISPARO=new File("./Music/Disparo.mp3").getAbsolutePath(); //ruta de audio ambiente

	private int CANTIDAD_DE_ENEMIGOS=4;//este puede cambiar con escenas
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
	private ArrayList<Hibryd> hibridos;
	private Botiquin botiquin;
	private GridPane grid;
	private ArrayList<String> input=new ArrayList<String>();
	/**
	Constructor de EscenarioComisaria, inicializa a los enemigos y a los objetos dentro de la escena del juego principal
	@param grid GridPane el cual utiliza para remover y poner los nodos de cada elemento a poner de acuerdo al cambio de escenario, muerte de un personaje
	**/
	public EscenarioComisaria(GridPane grid){
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
			hibridos.add(new Hibryd(250,500));
		}

		//iniciar fondo etc

	}
	/**
	Metodo sobreescrito handle que obtiene el tiempo transcurrido para poder implementar la logica de acuerdo a los enemigos que estan en este escenario, que son solamente Hybrid
	@param current de tipo long que es el tiempo transcurrido
	**/
	@Override
	public void handle(long current){
		update();	
		vida.setText("Vida "+Detective.getInstance().getNombre()+": "+Detective.getVida());
		puntos.setText("Score : "+Detective.getPuntos());
		botiquines.setText("Botiquines : "+Detective.getNumBotiquines());
		
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
			mMuerto.play();
			mFondo.stop();
			this.grid.getChildren().remove(Detective.getInstance());
			this.stop();
		}
		if(VentanaSalir.getDecision()){
			this.stop();
			mMuerto.stop();
			mFondo.stop();
		}
		if(Detective.getInstance().getTranslateX()>NuevoJuego.ANCHO_DE_LA_ESCENA && CANTIDAD_DE_ENEMIGOS<=0){
			this.stop();
			Detective.getInstance().setTranslateX(-48);
			Detective.getInstance().setPos(-48,140);
			System.out.println("Escenario 1 terminado");
			mFondo.stop();
			this.grid.getChildren().clear();
			EscenarioComisaria2 comisaria2=new EscenarioComisaria2(this.grid);
			this.grid.getChildren().add(comisaria2.getBackground());
			this.grid.getChildren().add(comisaria2.getBotiquin());
			this.grid.getChildren().add(Detective.getInstance());
			this.grid.getChildren().add(comisaria2.getVida());
			this.grid.getChildren().add(comisaria2.getBotiquinesLbl());
			this.grid.getChildren().add(comisaria2.getPuntos());
			this.grid.getChildren().addAll(comisaria2.getHibridos());
			this.grid.getChildren().addAll(comisaria2.getAzathots());
			comisaria2.start();
		}
	}

	/**
	Metodo que permite hacer una actualizacion de los movimientos que el usuario hace por medio de las teclas asi como el gestionamiento de 
	los objetos tomados 
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
	Metodo que regresa la etiqueta de vida que es la que se muestra en la escena para mostrar la vida del jugador
	@return vida Etiqueta, que es la vida del el jugador como etiqueta
	**/
	public Etiqueta getVida(){
		return this.vida;
	}
	/**
	Metodo que regresa los botiquines del detective que es la que se muestra en la escena 
	@return botiquines Etiqueta, que son los botiquines recogidos del el jugador como etiqueta
	**/
	public Etiqueta getBotiquinesLbl(){
		return this.botiquines;
	}
	/**
	Metodo que regresa los puntos del detective que es la que se muestra en la escena 
	@return puntos Etiqueta, que son los puntos obtenidos del el jugador como etiqueta
	**/
	public Etiqueta getPuntos(){
		return this.puntos;
	}
	/**
	Metodo que regresa el fondo de la escena en la que se esta
	@return background ImageView , que es el fondo del escenario, la comisaria parte 1
	**/
	public ImageView getBackground(){
		return BACKGROUND;
	}
	/**
	Metodo que regresa el cursor de la escena en la que se esta
	@return imageC ImageCursor, que es el cursor del escenaio
	**/
	public ImageCursor getCursor(){
		return this.imagenC;
	}
	/**
	Metodo que regresa un arreglo de hibridos que son los enemigos de el escenario de comisaria 1
	@return hibridos ArrayList que es una lista de tipo hibrido
	**/
	public ArrayList<Hibryd> getHibridos(){
		return this.hibridos;
	}
	/**
	Metodo que devuelve el botiquin que se utiliza en la escena de la comisaria 1
	@return botiquin Botiquin que es el botiquin que utiliza el jugador
	**/
	public Botiquin getBotiquin(){
		return this.botiquin;
	}
	
}
