package unam.fi.poo.aplicacion;

import javafx.application.Application;
import javafx.stage.Stage;
import unam.fi.poo.ventanas.*;
/**
Zone Of Silence: es un juego de survival horror en 2D que permite al jugador experimentar un ambiente de horror con una historia y jugabilidad
es de accion, disparos, esta aplicación contiene un menu principal, controles, asi como tambien desarrollar la puntuacion a lo largo del juego
este juego contiene 8 escenarios en donde se desarrolla

@author Ramirez Viramontes Josue Yafte
@version 27/05/2019
**/
public class Juego extends Application{
	/**
	Metodo que permite empezar el ciclo de la ventana menu para iniciar el juego
	@param primaryStage Stage que es el stage que se genera
	**/
	public void start(Stage primaryStage){
		new VentanaMenu();
	}
	/**
	Metodo principal main que permite iniciar la aplicacion
	@param args String[] que es el arreglo de argumentos para el programa
	**/
	public static void main(String[] args){
		launch(args);
	}
}
