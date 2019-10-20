package unam.fi.poo.objetos;
import java.io.File;
/**
Clase que permite implementar la mejora de pistola de tipo A que es la que provoca mas daño de los tres tipos
**/
public class MejoraDePistolaTipoA extends MejoraDePistola{
	private static String RUTA_TIPO_A=new File("./Images/objetos/BalasTipoA.png").getAbsolutePath();

	/**
	Constructor que llama al constructor padre mandando algunos de sus parametros como ancho y alto y posicion en xy 
	de acuerdo al escenario que se emplee
	@param height int que es la altura de la imagen 
	@param width int que es el ancho de la imagen 
	@param x double que es su cordenada en x
	@param y double que es su cordenada en y
	**/
	public MejoraDePistolaTipoA(int height,int width,double x,double y){
		super(RUTA_TIPO_A,height,width,x,y,1000);
	}
	/**
	Metodo sobre escrito sobre si esta recogido o no el objeto de tipo  MejoraDePistolaTipoA
	@return recogido boolean que devuelve true si lo esta, y false si no ha sido recogido
	**/
	@Override
	public boolean getRecogido(){
		return super.recogido;
	}
	/**
	Metodo que inicia la capacidad de si esta recogido o no el objeto de tipo  MejoraDePistolaTipoA
	si ha sido recogido se pone true al objeto
	**/
	public void setRecogido(){
		super.recogido=true;
	}
	
}
