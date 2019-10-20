package unam.fi.poo.ventanas;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.io.File;
import java.util.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import unam.fi.poo.controles.*;
import unam.fi.poo.ventanas.NuevoJuego;
/**
Clase que implementa una nueva ventana de Introduccion al juego que consta de un video y pedir el nombre del jugador para posteriormente iniciar un Nuevo Juego

**/
public class Introduccion extends Stage{
	private String pathVideo=new File("./Videos/intro.mp4").getAbsolutePath();
	private String pathZOS=new File("./Music/zoneofsilence.mp3").getAbsolutePath();
	private Media mediaV;
	private MediaPlayer mediaPV;
	private MediaView mediaVV;
	private Media mediaZOS=new Media(new File(this.pathZOS).toURI().toString());
	private MediaPlayer mediaPZOS=new MediaPlayer(this.mediaZOS);
	private CajaDeTexto nombreTxt;
	private Boton aceptarBtn;
	private Boton siguienteBtn;
	private Boton anteriorBtn;
	private Text t;
	String nombre=new String();
	ArrayList<String> notas=new ArrayList<String>();
	private static int contador=0;
	private static int contEsc=0;
	/**
	Constructor de Introduccion que permite iniciar la imagen, video y caja de texto del juego y el obtener el nombre del jugador, ademas
	de mostrar las notas, que cuentan mas sobre la historia del juego
	**/
	public Introduccion(){
		//Group root=new Group();
		GridPane root=new GridPane();
		root.setHgap(30);
		root.setVgap(30);
		root.setAlignment(Pos.CENTER);
		Scene s=new Scene(root,1280,720);

		
		this.t=new Text("Ingresa tú nombre:");
		Stage stage=this;

		this.nombreTxt=new CajaDeTexto();
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/nota1.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/nota2.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/nota3.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/Controles.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/info1.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/info2.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/info3.png)"));
		this.notas.add(new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-image:url(File:./Images/menu/info4.png)"));

		//Video de Introduccion
		this.mediaV=new Media(new File(this.pathVideo).toURI().toString());
		this.mediaPV=new MediaPlayer(this.mediaV);
		this.mediaVV=new MediaView(this.mediaPV);
		
		
		this.mediaVV.fitHeightProperty().bind(stage.heightProperty());
		this.mediaVV.fitWidthProperty().bind(stage.widthProperty());
		this.mediaPV.play();


		root.getChildren().add(this.mediaVV);
		
		s.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e){
				//System.out.println(e.getCode().toString());
				if(e.getCode().toString()=="ESCAPE" && contEsc<1){
					contEsc++;
					mediaPV.stop();
					pedirNombre(root);
				}
			}
		});
		mediaPV.setOnReady(new Runnable(){
			@Override
			public void run(){
			}
		});
		mediaPV.setOnEndOfMedia(new Runnable(){
			@Override
			public void run(){
				pedirNombre(root);
			}
		});
		
		this.aceptarBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				//obtener el nombre
				root.getChildren().remove(t);
				try{
					if(nombreTxt.getText().isEmpty())
						throw new Exception("Ingresa un nombre primero");
					nombre=nombreTxt.getText();
					mediaPZOS.play();
					root.getChildren().clear();
					mostrarHistoria(root);
				}catch(Exception ea){
					t.setText(ea.getMessage());
					root.add(t,1,6);
				}
			}
		},90,30);

		this.siguienteBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent a){
				contador++;
				if(contador<=7)
					root.setStyle(notas.get(contador));
				if(contador==8){
					contador=0;
					contEsc=0;
					new NuevoJuego(nombre);
					close();
				}
			}
		},90,30);

		this.anteriorBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent a){
				contador--;
				if(contador<0)
					contador=0;
				if(contador<=7)
					root.setStyle(notas.get(contador));
			}
		},90,30);

		this.setScene(s);
		this.initStyle(StageStyle.UNDECORATED);
		this.setTitle("Intro");
		this.show();
	}
	
	/**
	Metodo que permite pedir el nombre, muestra una caja, por ello se pide un GridPane de esta escena para modificar el fin del video 
	y empezar a pedir el nombre del jugador
	@param root GridPane que es el grid de esta escena
	**/
	public void pedirNombre(GridPane root){
		root.getChildren().remove(mediaVV);
		root.setStyle("-fx-background-color:black;-fx-min-width:1280px;-fx-min-height:720px;-fx-background-image: url(File: ./Images/menu/ZOS.png);-fx-background-size:1280px 720px");
		this.aceptarBtn.setStyle("-fx-background-color:transparent;-fx-min-width:90px;-fx-min-height:30px;-fx-background-image:url(File:./Images/menu/aceptarBtn.png);-fx-background-size:90px 30px; -fx-background-repeat:no-repeat");
		this.t.setFont(Font.font(Font.getFontNames().get(200),FontWeight.BOLD,30));
		this.t.setStyle("-fx-font-smooth-type:lcd;-fx-fill:linear-gradient(from 0% 0% to 100% 200%,repeat,purple 20%,red 50%);-fx-stroke:black;-fx-stroke-width:1");
		root.add(this.aceptarBtn,9,6);
		root.add(this.nombreTxt,5,6,3,1);
		root.add(this.t,1,6);

	}
	/**
	Metodo que permite hacer un cambio en el grid para quitar el video cuando finalice y mostrar un cambio al ingresar la historia con las notas y los controles mas especificaciones del juego
	@param root GridPane que es el grid de esta escena
	**/
	public void mostrarHistoria(GridPane root){
		root.setStyle(notas.get(0));
		this.siguienteBtn.setStyle("-fx-background-color:transparent;-fx-min-width:90px;-fx-min-height:30px;-fx-background-image:url(File:./Images/menu/siguienteBtn.png);-fx-background-size:90px 30px; -fx-background-repeat:no-repeat");
		this.anteriorBtn.setStyle("-fx-background-color:transparent;-fx-min-width:90px;-fx-min-height:30px;-fx-background-image:url(File:./Images/menu/anteriorBtn.png);-fx-background-size:90px 30px; -fx-background-repeat:no-repeat");
		root.add(this.siguienteBtn,9,19);
		root.add(this.anteriorBtn,3,19);
	}
}
