package unam.fi.poo.controles;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.*;
/**
Clase que permite implementar un boton en el juego, de acuerdo al evento y su dimension
**/
public class Boton extends Button {
	/**
	Constructor que permite iniciar al boton, por medio del evento que permite su accion y su ancho y alto ademas de su nombre
	@param nombre String que es el nombre del boton
	@param me EventHandler que es el evento que controla su comportamiento
	@param width int que es el ancho del boton
	@param height int que es el alto del boton
	**/
	public Boton(String nombre,EventHandler<ActionEvent> me,int width,int height){
		super(nombre);
		super.setOnAction(me);
		super.setMinWidth(width);
		super.setMinHeight(height);
	}

}
