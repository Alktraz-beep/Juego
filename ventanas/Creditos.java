package unam.fi.poo.ventanas;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;
import java.io.File;
import java.util.*;
import javafx.scene.media.Media;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import unam.fi.poo.controles.*;
/**
Clase que permite implementar una ventana dentro del menu para poder mostrar los creditos del juego
**/
public class Creditos extends Stage{
	/**
	Constructor de creditos que inicia la imagen de esta ventana, y sus caracteristicas donde se muestra los creditos
	**/
	public Creditos(){
		GridPane grid=new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		Scene sceneGrid=new Scene(grid);
		grid.setStyle("-fx-min-height:500px;-fx-min-width:900px;-fx-background-color:black;-fx-background-size:900px 500px;-fx-background-image:url(File:./Images/menu/Creditos.png);-fx-background-repeat:no-repeat;-fx-background-position:center;");
		
		ArrayList<String> imput=new ArrayList<String>();

		//Cerrar con esc
		sceneGrid.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
				//System.out.println(e.getCode().toString());
				if(e.getCode().toString()=="ESCAPE")
					close();
			}
		});
		
		this.setMinWidth(900);
		this.setMinHeight(400);
		this.initStyle(StageStyle.UNDECORATED);
		this.setScene(sceneGrid);
		this.setTitle("Creditos");
		this.initModality(Modality.APPLICATION_MODAL);
		this.show();
	}
}
