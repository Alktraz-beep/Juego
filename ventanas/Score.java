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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import unam.fi.poo.controles.*;
import unam.fi.poo.almacen.*;
/**
Metodo que permite mostrar la ventana de Score donde se muestra la puntuación de los jugadores
**/
public class Score extends Stage{
	/**
	Constructor que inicializa el fondo del score para poder posteriormente mostrar todas las puntuaciones
	**/
	private Etiqueta nombre;
	private Etiqueta score;
	public Score(){
		GridPane grid=new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		Scene sceneGrid=new Scene(grid);
		grid.setStyle("-fx-min-height:500px;-fx-min-width:900px;-fx-background-color:black;-fx-background-size:900px 500px;-fx-background-image:url(File:./Images/menu/Score.png);-fx-background-repeat:no-repeat;-fx-background-position:center;");
		
		this.nombre=new Etiqueta("Nombre");
		this.nombre.setFont(Font.font(Font.getFontNames().get(200),FontWeight.BOLD,15));
		this.nombre.setTextFill(Color.web("#0076a3"));
		this.nombre.setTranslateX(-10);
		this.nombre.setTranslateY(-200);

		this.score=new Etiqueta("Score");
		this.score.setFont(Font.font(Font.getFontNames().get(200),FontWeight.BOLD,15));
		this.score.setTextFill(Color.web("#0076a3"));
		this.score.setTranslateX(100);
		this.score.setTranslateY(-200);

		ArrayList<String> imput=new ArrayList<String>();
		//Muestra los datos
		Administrador admin=new Administrador();
		ArrayList<String>puntaje =new ArrayList<String>();
		ArrayList<Etiqueta>puntajeLbl =new ArrayList<Etiqueta>();
		puntaje=admin.mostrarInformacion();
		for(int i=0;i<puntaje.size();i++){
			puntajeLbl.add(new Etiqueta(puntaje.get(i)));
			puntajeLbl.get(i).setFont(Font.font(Font.getFontNames().get(200),FontWeight.BOLD,15));
			puntajeLbl.get(i).setTextFill(Color.web("#0076a3"));
			puntajeLbl.get(i).setTranslateX(-10);
			puntajeLbl.get(i).setTranslateY(-180+20*i);
		}

		//Cerrar con esc
		sceneGrid.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
				//System.out.println(e.getCode().toString());
				if(e.getCode().toString()=="ESCAPE")
					close();
			}
		});
		grid.getChildren().add(this.nombre);
		grid.getChildren().add(this.score);
		grid.getChildren().addAll(puntajeLbl);
		this.setMinWidth(900);
		this.setMinHeight(400);
		this.initStyle(StageStyle.UNDECORATED);
		this.setScene(sceneGrid);
		this.setTitle("Score");
		this.initModality(Modality.APPLICATION_MODAL);
		this.show();
	}
}
