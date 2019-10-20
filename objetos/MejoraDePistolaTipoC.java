package unam.fi.poo.objetos;
import java.io.File;

/**
Clase que implementa una mejora de tipo C con un daño menor, esta es la mejora mas chica que hay en el juego
**/
public class MejoraDePistolaTipoC extends MejoraDePistola{
	private static String RUTA_TIPO_C=new File("./Images/objetos/BalasTipoC.png").getAbsolutePath();

	/**
	Constructor de la mejora que permite iniciar con su imagen y sus dimensiones y posicion en el escenario a la mejora de tipoC
	@param height int que es la altura de la imagen
	@param width int que es el ancho de la imagen
	@param x double que es la coordenada en x de la imagen
	@param y double que es la coordenada en y de la imagen
	**/
	public MejoraDePistolaTipoC(double height,double width,double x,double y){
		super(RUTA_TIPO_C,(int)height,(int)width,x,y,100);
		super.setFitWidth(width);
		super.setFitHeight(height);
	}
	/**
	Metodo que permite mostrar si el objeto ha sido recogido del escenario o no
	@return recogido boolean que es true si ha sido recogido y false si no
	**/
	@Override
	public boolean getRecogido(){
		return super.recogido;
	}
	/**
	Metodo que permite cambiar la característica de haber sido recogido, cambia a true si ha sido recogido del escenario 
	**/
	public void setRecogido(){
		super.recogido=true;
	}
	
}
