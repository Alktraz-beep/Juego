package unam.fi.poo.personajes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
/**
Clase que implementa a la entidad Cyaegha con su imagen 
@version 27/05/2019
**/
public class Cyaegha extends Entidad{
	private static String pathCy=new File("./Images/Personajes/Cyaegha.gif").getAbsolutePath();
	private static final String nombre="Cyaegha";
	public double posX;
	public  double posY;
	private AnimationTimer timer;
	long time0=System.nanoTime();
	int cont=0;

	
	/**
	Constructor de Cyaegha que inicializa su ancho y alto
	ademas de su posicion 
	@param width que es el ancho de Cyaegha 
	@param height que es el alto de Cyaegha 
	@param y double que es la coordenada en y del enemigo
	**/
	public Cyaegha(double width,double height,double y){
		super(pathCy,(int)width,(int)height,0.4);
		try{
			super.setTranslateY(y);
			super.setTranslateX(generarPosicionXAleatoria());
			super.golpe=100;
			super.setVida(100);
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	
	/**
	Metodo que permite saber si esta dañado Cyaegha
	@return validacion boolean que es la validacion de que esta dañado
	**/
	public boolean estaDanado(){
		boolean	validacion=false;
			if(getVida()<500)
				validacion=true;
		return validacion;
	}
	/**
	Metodo que regresa la posicion aleatoria de Cyaegha
	@return px como el desplazamiento en x que se desplaza 
	**/
	public double generarPosicionXAleatoria(){
		double px=1280*Math.random();
		if(px<700)
			px=generarPosicionXAleatoria();
		return px;
	}
}

