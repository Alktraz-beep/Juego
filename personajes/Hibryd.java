package unam.fi.poo.personajes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
/**
Clase que permite generar un Hybrid que son los primeros enemigos del Detective 
**/
public class Hibryd extends Entidad{
	private static String pathZombie=new File("./Images/Personajes/Hibryd1.gif").getAbsolutePath();
	private static final String nombre="Hibryd";
	public double posX;
	public  double posY;
	//private int vida=500;
	private AnimationTimer timer;
	private	boolean brincar;
	long time0=System.nanoTime();
	int cont=0;

	/**
	Constructor de Hibryd que permite inicializar su ancho y alto del hibryd 
	@param width int que es el ancho de la imagen de este enemigo
	@param height int que es el alto de la imagen de este enemigo
	**/
	public Hibryd(int width,int height){
		super(pathZombie,250,500,0.5);
		try{
			super.setTranslateY(140);
			super.setTranslateX(generarPosicionXAleatoria());
			super.golpe=10;
			super.setVida(400);
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	/**
	Metodo que permite generar la posicion aleatoria de Hibryd que son la posicion fuera dle rango del jugador pero aleatoria
	@return px double que es la posicion en x aleatoria 
	**/
	public double generarPosicionXAleatoria(){
		double px=1280*Math.random();
		if(px<700)
			px=generarPosicionXAleatoria();
		return px;
	}
}

