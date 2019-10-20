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
Clase que permite implementar la ventana de un NuevoJuego el cual tambien muestra el primer escenario y se desarrolla
es el nucleo de la logica del juego e interaccion con los mounstros y objetos
**/

public class NuevoJuego extends Stage{
	public static final float ANCHO_DE_LA_ESCENA=1280;
	public static final float ALTO_DE_LA_ESCENA=720;
	private Stage stage=this;
	private static int numEscenario=1;
	public static ArrayList<String> input=new ArrayList<String>();
	private AnimationTimer timer;
	int contador=0;
	/**
	Constructor que permite iniciar la ventana del juego, donde el jugador interactuara
	@param nombre String que es el nombre del jugador
	**/
	public NuevoJuego(String nombre){
		GridPane grid=new GridPane();
		grid.setAlignment(Pos.CENTER);

		Scene sceneGrid=new Scene(grid,1280,720);

		VentanaSalir.setDecision(false);
		EscenarioComisaria comisaria=new EscenarioComisaria(grid);
		sceneGrid.setCursor(comisaria.getCursor());
		Detective.getInstance();
		Detective.getInstance().setNombre(nombre);
		Detective.getInstance().setAncho(200);
		Detective.getInstance().setAlto(200);
		System.out.println(Detective.getNombre());
		grid.getChildren().add(comisaria.getBackground());
		grid.getChildren().addAll(comisaria.getBotiquin());
		grid.getChildren().add(Detective.getInstance());
		grid.getChildren().add(comisaria.getVida());
		grid.getChildren().add(comisaria.getPuntos());
		grid.getChildren().add(comisaria.getBotiquinesLbl());
		grid.getChildren().addAll(comisaria.getHibridos());
		comisaria.start();

		//los controles del jugador
		sceneGrid.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e){
				String code=e.getCode().toString();
				if(!input.contains(code))
					input.add(code);
				if(input.contains("UP") || input.contains("W")){
					Detective.getInstance().moverY(-2);	
					Detective.getInstance().regresarAlSuelo();
					
				}
				if(input.contains("ESCAPE")){
					VentanaSalir vs=new VentanaSalir(stage,grid);
					input.remove("ESCAPE");
				}
				if(input.contains("SPACE"))
					Detective.usarBotiquin();
			}
		});
		sceneGrid.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent a){
				String code=a.getCode().toString();
				if(input.contains(code))
					input.remove(code);
			}
		});
	

		this.initStyle(StageStyle.UNDECORATED);
		this.setScene(sceneGrid);
		this.show();
	}
}
