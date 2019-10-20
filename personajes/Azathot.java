package unam.fi.poo.personajes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Detective;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
/**
Clase que permite generar un enemigo de tipo Azathot
**/
public class Azathot extends Entidad{
	private static String pathAzathot=new File("./Images/Personajes/azathot.gif").getAbsolutePath();
	private Image image;
	private static final String nombre="Azathot";
	public double posX;
	public  double posY;
	private AnimationTimer timer;
	private	boolean brincar;
	long time0=System.nanoTime();
	int cont=0;

	/**
	Constructor de Azathot que  inicia la imagem de la ruta dada, con posicion, tiene un golpe de 20, y vida 900
	**/
	public Azathot(){
		super(pathAzathot,300,300,4);
		try{
			this.setTranslateY(140);
			this.setTranslateX(generarPosicionXAleatoria());
			super.golpe=20;
			super.setVida(900);
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	/**
	Metodo que permite generar la posicion aleatoria dentro del escenario  para azathot
	@return px double que es la posicion aleatoria en x, que es mayor a la posicion donde se encuentra el Detective
	**/
	public double generarPosicionXAleatoria(){
		double px=1280*Math.random();
		if(px<700)
			px=generarPosicionXAleatoria();
		return px;
	}
}

