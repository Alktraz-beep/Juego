package unam.fi.poo.personajes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
/**
Clase que implementa a la entidad Cthulhu con su imagen , es el boss final
@version 30/05/2019
**/
public class Cthulhu extends Entidad{
	private static String pathCt=new File("./Images/Personajes/Cthulhu1.gif").getAbsolutePath();
	private static String pathCt2=new File("./Images/Personajes/Cthulhu2.gif").getAbsolutePath();
	private static final String nombre="Cthulhu";
	public double posX;
	public  double posY;
	private AnimationTimer timer;
	long time0=System.nanoTime();
	int cont=0;

	
	/**
	Constructor de Cthulhu que inicializa su ancho y alto
	ademas de su posicion 
	@param width que es el ancho de Cthulhu
	@param height que es el alto de Cthulhu
	@param y double que es la coordenada en y
	**/
	public Cthulhu(double width,double height,double y){
		super(pathCt,(int)width,(int)height,4);
		try{
			super.setTranslateY(y);
			super.setTranslateX(generarPosicionXAleatoria());
			super.golpe=100000;
			super.setVida(100000);
			this.posX=3;
			this.posY=3;
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	
	/**
	Metodo que permite saber si esta dañado Cthulhu
	@return validacion boolean que es la validacion de que esta dañado
	**/
	public boolean estaDanado(){
		boolean	validacion=false;
			if(getVida()<500)
				validacion=true;
		return validacion;
	}
	/**
	Metodo que regresa la posicion aleatoria de Cthulhu
	@return px como el desplazamiento en x que se desplaza 
	**/
	public double generarPosicionXAleatoria(){
		double px=1280*Math.random();
		if(px<700)
			px=generarPosicionXAleatoria();
		return px;
	}
	/**
	metodo que permite hacer el vuelo de Cthulhu en las cooredenadas xy para que pueda moverse sobre el escenario 
	y sea de forma que ataque al detective
	y permite el cambio de forma despues de que haber bajado la mitad de la vida
	@param t double que es el tiempo
	**/
	public void volar(double t){
		double x=0;
		double y=0+200*Math.sin(t);
		if(this.getVida()>50000){
			x=800+430*Math.cos(t);
		}else if(this.getVida()<=50000){
			x=700+560*Math.cos(t);
			this.setImage(new Image(new File(pathCt2).toURI().toString(),500,500,false,false));
		}
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}

