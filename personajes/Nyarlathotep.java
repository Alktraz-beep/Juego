package unam.fi.poo.personajes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
/**
Clase que implementa a la entidad Nyarlathotep con su imagen 
**/
public class Nyarlathotep extends Entidad{
	private static String pathNy=new File("./Images/Personajes/NyarlathotepIzq.gif").getAbsolutePath();
	private static final String nombre="Nyarlathotep";
	public double posX;
	public  double posY;
	private AnimationTimer timer;
	long time0=System.nanoTime();
	int cont=0;

	
	/**
	Constructor de Nyarlathotep que inicializa su ancho y alto
	ademas de su posicion 
	@param width que es el ancho de Nyarlathotep
	@param height que es el alto de Nyarlathotep
	@param y double que es la coordenada en y
	**/
	public Nyarlathotep(double width,double height,double y){
		super(pathNy,(int)width,(int)height,4);
		try{
			super.setTranslateY(y);
			super.setTranslateX(generarPosicionXAleatoria());
			super.golpe=40;
			super.setVida(10000);
			this.posX=3;
			this.posY=3;
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	
	/**
	Metodo que permite saber si esta dañado nyarlathotep
	@return validacion boolean que es la validacion de que esta dañado
	**/
	public boolean estaDanado(){
		boolean	validacion=false;
			if(getVida()<500)
				validacion=true;
		return validacion;
	}
	/**
	Metodo que regresa la posicion aleatoria de Nyarlathotep
	@return px como el desplazamiento en x que se desplaza 
	**/
	public double generarPosicionXAleatoria(){
		double px=1280*Math.random();
		if(px<700)
			px=generarPosicionXAleatoria();
		return px;
	}
	/**
	metodo que permite hacer el vuelo de nyarlathotep en las cooredenadas xy para que pueda moverse sobre el escenario 
	y sea de forma que ataque al detective
	**/
	public void volar(){
		this.setTranslateX(this.getTranslateX()+this.posX);
		this.setTranslateY(this.getTranslateY()+this.posY);
		if(this.getTranslateX()<=0 || this.getTranslateX()>=NuevoJuego.ANCHO_DE_LA_ESCENA)
			this.posX*=-1;
		if(this.getTranslateY()<=-300 || this.getTranslateY()>=NuevoJuego.ALTO_DE_LA_ESCENA/2)
			this.posY*=-1;
	}
}

