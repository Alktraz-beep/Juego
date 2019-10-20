package unam.fi.poo.controles;

import javafx.scene.control.Label;

/**
Clase que permite ingresar una etiqueta en las ventanas, para los nombres, mostrar vida,botiquines entre otras
**/
public class Etiqueta extends Label {
	/**
	Constructor que permite iniciar la etiqueta por medio del nombre, o texto que llevara dentro
	@param etiqueta String que es el mensaje que lleva dentro
	**/
	public Etiqueta(String etiqueta){
		super();
		this.setText(etiqueta);
	}
}
