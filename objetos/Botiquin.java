package unam.fi.poo.objetos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Entidad;
import unam.fi.poo.ventanas.NuevoJuego;
import javafx.animation.AnimationTimer;
import java.io.File;
 
import unam.fi.poo.comportamientos.Recogible;
/**
Clase Botiquin que implementa un botiquin y otorga vida al detective 
ademas implementa la interfaz recogible
**/
public class Botiquin extends ImageView implements Recogible{
	private static String pathBotiquin=new File("./Images/objetos/Botiquin.png").getAbsolutePath();
	private Image image;
	private static String nombre="Botiquin";
	private static int vida=800;
	public static double posX;
	public static double posY;
	private int width=200;
	private int height=100;
	private	int rapidez=8;
	private boolean recogido=false;

	/**
	Constructor que permite iniciar un botiquin con su imagen respectiva
	su vida  y su posicion
	**/
	public Botiquin(){
		super();
		try{
			this.image=new Image(new File(this.pathBotiquin).toURI().toString(),this.width,this.height,false,false);
			super.setImage(this.image);
			this.setTranslateY(140);
			this.setTranslateX(900);
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	/**
	Metodo que permite obtener la vida que otorga el botiquin por uso
	@return vida int que es estatica que es la vida del botiquin
	**/
	public static int getVida(){
		return vida;
	}
	/**
	Metodo que permite obtener el nombre de este objeto
	@return nombre String que es el nombre de este objeto
	**/
	public static String getNombre(){
		return nombre;
	}
	/**
	metodo sobre escrito de la interfaz que permite poner la capacidad de ser recogido
	**/
	public void setRecogido(){
		this.recogido=true;
	}
	/**
	metodo sobre escrito de la interfaz que permite obtener la capacidad de ser recogido
	@return recogido boolean que dice si este objeto fue recogido
	**/
	@Override
	public boolean getRecogido(){
		return this.recogido;
	}
}

