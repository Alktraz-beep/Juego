package unam.fi.poo.personajes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
/**
Clase que implementa a la entidad Shub Niggurath con su imagen , es un boss final
@version 28/05/2019
**/
public class ShubNiggurath extends Entidad{
	private static String pathShub=new File("./Images/Personajes/ShubNiggurath.gif").getAbsolutePath();
	private static final String nombre="Shub-Niggurath";
	public double posX;
	public  double posY;
	private AnimationTimer timer;
	long time0=System.nanoTime();
	int cont=0;

	
	/**
	Constructor de ShubNiggurath que inicializa su ancho y alto
	ademas de su posicion 
	@param width que es el ancho de ShubNiggurath
	@param height que es el alto de ShubNiggurath
	@param y double que es la coordenada en y
	**/
	public ShubNiggurath(double width,double height,double y){
		super(pathShub,(int)width,(int)height,4);
		try{
			super.setTranslateY(y);
			super.setTranslateX(generarPosicionXAleatoria());
			super.golpe=100;
			super.setVida(30000);
			this.posX=3;
			this.posY=3;
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	
	/**
	Metodo que permite saber si esta dañado ShubNiggurath
	@return validacion boolean que es la validacion de que esta dañado
	**/
	public boolean estaDanado(){
		boolean	validacion=false;
			if(getVida()<500)
				validacion=true;
		return validacion;
	}
	/**
	Metodo que regresa la posicion aleatoria de ShubNiggurath
	@return px como el desplazamiento en x que se desplaza 
	**/
	public double generarPosicionXAleatoria(){
		double px=1280*Math.random();
		if(px<700)
			px=generarPosicionXAleatoria();
		return px;
	}
	/**
	metodo que permite hacer el vuelo de ShubNiggurath en las cooredenadas xy para que pueda moverse sobre el escenario 
	y sea de forma que ataque al detective
	@param t double que es el tiempo
	**/
	public void volar(double t){
		double y=0+200*Math.sin(t);
		double x;
		double randomNum=10*Math.random();
		if(randomNum<9)
			x=400+200*Math.cos(t);
		else
			x=400+600*Math.cos(t);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}

