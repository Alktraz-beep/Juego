package unam.fi.poo.personajes;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.canvas.GraphicsContext;
import java.io.File;
import unam.fi.poo.ventanas.NuevoJuego;
/**
Clase padre de todos los enemigos que son entidades y permite iniciarlas
**/
public class Entidad extends ImageView{
	
	private Image image;
	public int golpe;
	private double rapidez;
	private int vida;
	/**
	Constructor que permite iniciar la imagen del enemigo, con su ancho, alto y su rapidez
	@param RUTA String que es la ruta de la imagen del enemigo
	@param width int que es el ancho del enemigo
	@param height int que es el alto del enemigo
	@param rapidez double que es la rapidez del enemigo
	**/
	public Entidad(String RUTA,int width,int height,double rapidez){
		super();
		try{	
			this.image=new Image(new File(RUTA).toURI().toString(),width,height,false,false);
			super.setImage(image);
			this.rapidez=rapidez;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	Metodo que permite hacer la animacion de mover al objetivo sobre el eje x que es en donde se encuentra el Detective
	@param t long que es el tiempo transcurrido mandado por el escenario y su bucle
	**/
	public void moverHaciaObjetivo(long t){
		if(this.getTranslateX()<0 || this.getTranslateX()>NuevoJuego.ANCHO_DE_LA_ESCENA)
			this.rapidez*=-1;
		this.setTranslateX(this.getTranslateX()+this.rapidez);
	}
	/**
	Metodo que permite iniciar la rapidez
	@param rapidez double que es la rapidez con que se desplaza el enemigo
	**/
	public void setRapidez(double rapidez){
		this.rapidez=rapidez;
	}
	/**
	metodo que permite ponerle vida al enemigo
	@param vida int que es la vida del enemigo
	**/
	public void setVida(int vida){
		this.vida=vida;
	}
	/**
	metodo que permite obtener la vida del enemigo
	@return vida int que es la vida del enemigo
	**/
	public int getVida(){
		return this.vida;
	}
	/**
	Metodo que permite saber si esta vivo o muerto el enemigo
	@return validacion boolean que es la validacion true, esta vivo y false que esta muerto
	**/
	public boolean estaVivo(){
		boolean validacion=true;
		if(getVida()<=0)
			validacion=false;
		return validacion;
	}
	/**
	Metodo que permite bajar vida al enemigo despues de un disparo del detective
	@param dano int que es el daño que provoca el Detective
	**/
	public void bajarVida(int dano){
		this.vida-=dano;
	}
}

