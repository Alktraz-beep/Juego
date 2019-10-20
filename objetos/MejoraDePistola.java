package unam.fi.poo.objetos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Entidad;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
 
import unam.fi.poo.comportamientos.Recogible;
/**
Clase que permite implementar la Mejora de pistola para el uso del jugador, esta unicamente aumenta el daño que provoca el Detective
Es abstracta 
**/
public abstract class MejoraDePistola extends ImageView implements Recogible{
	private Image image;
	private static String nombre="Mejora De Pistola";
	private static int dano;
	public static double posX;
	public static double posY;
	private int width;
	private int height;
	protected boolean recogido=false;

	/**
	Constructor que permite inicializar los tipos de mejoras de pistola
	por su ruta de imagen que las diferencia, el alto y ancho y la posicion en x y y
	@param RUTA String que es la ruta de la imagen que se pondra al objeto
	@param height int que es la altura de la imagen
	@param width int que es el ancho de la imagen
	@param x double que es la posicion en x de la mejora
	@param y double que es la posicion en y de la mejora
	@param dano int que es el daño que otorga al detective para que provoque mas daño
	**/
	public MejoraDePistola(String RUTA,int height,int width,double x,double y,int dano){
		super();
		try{
			this.image=new Image(new File(RUTA).toURI().toString(),width,height,false,false);
			super.setImage(this.image);
			this.setTranslateX(x);
			this.setTranslateY(y);
			//this.setPos(x,y);
			this.dano=dano;
		}catch(Exception e){	
			e.printStackTrace();	
		}
	}
	/**
	Metodo que permite obtener el daño que da la mejora de pistola
	@return dano int que es el daño de la mejora
	**/
	public int getDano(){
		return this.dano;
	}
}

