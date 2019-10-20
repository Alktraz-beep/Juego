package unam.fi.poo.ventanas;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.image.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import unam.fi.poo.controles.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import unam.fi.poo.ventanas.*;

/**
Clase que permite implementar una ventana para mostrar el menu principal donde se encuentran los diferentes opciones que tiene el programa,
Nuevo Juego,Score, Creditos y Salir de juego
**/
public class VentanaMenu extends Stage{
	private String pathMusica=new File("./Music/fuckluv.mp3").getAbsolutePath();
	//private String pathVideo1=new File("./Videos/intro.mp4").getAbsolutePath();
	private Media mediaMusica;
	private Media mediaVideo1;
	//Botones
	private Boton nuevoJBtn;
	private Boton controlesBtn;
	private Boton creditosBtn;
	private Boton puntajesBtn;
	private Boton salirBtn;
	Controles vc;
	Creditos vcre;
	/**
	Constructor de VentanaMenu que permite inicializar esta ventana con hojas de estilo para la implementacion de la imagen y botones
	**/
	public VentanaMenu(){
		GridPane grid=new GridPane();
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setHgap(20);	
		grid.setVgap(20);	
		grid.setId("pane");
		Scene sceneGrid = new Scene(grid,1080,720);
		grid.setGridLinesVisible(false);
	
		//Musica de fondo	
		this.mediaMusica=new Media(new File(this.pathMusica).toURI().toString());
		MediaPlayer fuckluv=new MediaPlayer(this.mediaMusica);
		fuckluv.setAutoPlay(true);
		fuckluv.setVolume(0.3);

		//------------Video Intro
		
		this.nuevoJBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				fuckluv.stop();
				new Introduccion();
				close();
			}
		},200,70);
		this.controlesBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				vc=new Controles();
			}
		},180,70);
		this.creditosBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				vcre=new Creditos();
			}
		},160,70);
		this.puntajesBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				new Score();
			}
		},140,70);
		this.salirBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				close();
			}
		},120,70);
		
		
		//Imagenes para el fondo,botones,etc
		grid.setStyle("-fx-min-height:720px,580px;-fx-min-width:1080px,1000px;-fx-background-image:url(File:./Images/menu/Hola.gif),url(File:./Images/menu/ZOS.png);-fx-background-size:1080px 720px,900px 580px; -fx-background-repeat:no-repeat,no-repeat;-fx-background-position:center,center;");
		this.nuevoJBtn.setStyle("-fx-min-height:70px;-fx-min-width:200px;-fx-background-color:transparent;-fx-background-size:200px 70px;-fx-background-image: url(File: ./Images/menu/nuevoJuegoBtn.png);-fx-background-position:center;");
		this.controlesBtn.setStyle("-fx-min-height:70px;-fx-min-width:180px;-fx-background-color:transparent;-fx-background-size:180px 70px;-fx-background-image: url(File: ./Images/menu/controlesBtn.png);-fx-background-position:center");
		this.creditosBtn.setStyle("-fx-min-height:70px;-fx-min-width:160px;-fx-background-color:transparent;-fx-background-size:160px 70px;-fx-background-image: url(File: ./Images/menu/creditosBtn.png);-fx-background-position:center");
		this.puntajesBtn.setStyle("-fx-min-height:70px;-fx-min-width:140px;-fx-background-color:transparent;-fx-background-size:140px 70px;-fx-background-image: url(File: ./Images/menu/scoreBtn.png);-fx-background-position:center");
		this.salirBtn.setStyle("-fx-min-height:70px;-fx-min-width:120px;-fx-background-color:transparent;-fx-background-size:120px 70px;-fx-background-image: url(File: ./Images/menu/salirBtn.png);-fx-background-position:center");
		//------------------------------------
			
		grid.add(this.nuevoJBtn,1,5);
		grid.add(this.controlesBtn,1,6);
		grid.add(this.creditosBtn,1,7);
		grid.add(this.puntajesBtn,1,8);
		grid.add(this.salirBtn,1,9);
		this.initModality(Modality.APPLICATION_MODAL);
		this.initStyle(StageStyle.UNDECORATED);
		//this.setMinHeight(720);
		//this.setMinWidth(1080);
		this.setScene(sceneGrid);
		this.setTitle("Bienvenido Zone of Silence");
		this.show();
		
	}
	/**
	Metodo que permite obtener el Stage que ayuda para el despliege de las ventanas y la logica del cambio de ventana a Nuevo Juego y este se cierre
	@return this Stage que es el Stage de la Ventana Menu
	**/
	public Stage getStage(){
		return this;
	}
}
