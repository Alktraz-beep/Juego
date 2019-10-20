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
import unam.fi.poo.ventanas.VentanaMenu;
import unam.fi.poo.ventanas.*;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.almacen.Administrador;
/**
Clase que implementa la ventana que pregunta si el usuario desea salir o no y permite hacer la respectiva operacion de regresar al menu o seguir
**/
public class VentanaSalir extends Stage{
	private Boton aceptarBtn;
	private Boton cancelarBtn;
	private Text t;
	private static boolean decision;
	String nombre=new String();
	/**
	Constructor que permite inicializar la ventana para salir, con los botones, aceptar y cancelar que preguntan al usuario cual es la descicion
	@param ventana Stage que utiliza para salir de la ventana de NuevoJuego
	@param grid GridPane que utiliza para salir de los objetos con los que se esta interactuando en el escenario
	**/
	public VentanaSalir(Stage ventana,GridPane grid){
		GridPane root=new GridPane();
		root.setHgap(30);
		root.setVgap(30);
		root.setAlignment(Pos.CENTER);
		Scene s=new Scene(root,720,420);
		NuevoJuego.input.clear();
		this.t=new Text("¿Deseas salir?");
		Stage stage=this;

		String fondo=new String("-fx-min-width:1280px;-fx-minheight:720px;-fx-background-size:1280px 720px;-fx-background-color:black");
		root.setStyle(fondo);
		

		this.aceptarBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent a){
				Administrador admin=new Administrador();
				admin.registrarInformacion();
				reiniciarPersonaje();
				decision=true;
				ventana.close();
				grid.getChildren().clear();
				new VentanaMenu();
				close();
			}
		},90,30);

		this.cancelarBtn=new Boton("",new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent a){
				decision=false;
				close();
			}
		},90,30);
	//botones
		this.aceptarBtn.setStyle("-fx-background-color:transparent;-fx-min-width:90px;-fx-min-height:30px;-fx-background-image:url(File:./Images/menu/aceptarBtn.png);-fx-background-size:90px 30px; -fx-background-repeat:no-repeat");
		this.cancelarBtn.setStyle("-fx-background-color:transparent;-fx-min-width:90px;-fx-min-height:30px;-fx-background-image:url(File:./Images/menu/Cancelar.png);-fx-background-size:90px 30px; -fx-background-repeat:no-repeat");
		this.t.setFont(Font.font(Font.getFontNames().get(200),FontWeight.BOLD,30));
		this.t.setStyle("-fx-font-smooth-type:lcd;-fx-fill:linear-gradient(from 0% 0% to 100% 200%,repeat,purple 20%,red 50%);-fx-stroke:black;-fx-stroke-width:1");

		root.add(this.aceptarBtn,9,6);
		root.add(this.cancelarBtn,3,6);
		root.add(this.t,5,0);
		this.setScene(s);
		this.initStyle(StageStyle.UNDECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("Salir");
		this.show();
	}
	
	/**
	Metodo que obtiene la desicion que tomo el usuario como estatica
	@return descision boolean que es la decision de aceptar o cancelar, true o false de salir o no
	**/
	public static boolean getDecision(){
		return decision;
	}
	/**
	Metodo que permite modificar la decision del jugador de acuerdo a lo elegido
	@param des boolean que es la decision tomada
	**/
	public static void setDecision(boolean des){
		decision=des;
	}
	/**
	metodo vacio que reinicia al personaje en caso de que la decision sea aceptar salir de la partida
	**/
	public void reiniciarPersonaje(){
		Detective.getInstance().setTranslateX(-48);
		Detective.getInstance().setTranslateY(140);
		Detective.getInstance().setPos(-48,140);
		Detective.getInstance().setVida(10000);
		Detective.getInstance().iniciarPuntos();
		Detective.getInstance().setBotiquines(0);
		Detective.getInstance().setPiso(141);
		Detective.getInstance().setFitWidth(300);
		Detective.getInstance().setFitHeight(500);
		Detective.getInstance().setDano(50);
	}
}
