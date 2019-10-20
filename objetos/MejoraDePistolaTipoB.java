package unam.fi.poo.objetos;
import java.io.File;
/**
Clase que permite implementar una mejora de pistola de tipo B que son las segundas mas fuertes que la c
**/
public class MejoraDePistolaTipoB extends MejoraDePistola{
	private static String RUTA_TIPO_B=new File("./Images/objetos/BalasTipoB.png").getAbsolutePath();

	/**
	Constructor de la mejora que permite iniciar con su imagen y sus dimensiones, tambien su posicion en el plano xy de la escena
	@param height int que es la altura de la imagen
	@param width int que es el ancho de la imagen
	@param x double que es la distancia en x donde se mostrara en la escena
	@param y double que es la distancia  en y donde se mostrara en la escena
	**/
	public MejoraDePistolaTipoB(int height,int width,double x,double y){
		super(RUTA_TIPO_B,height,width,x,y,500);
	}
	/**
	Metodo sobreescrito que permite hacer saber si el objeto se recogió o no
	regresa true si ha sido recogido y false si no se ha recogido
	@return recogido boolean que es la caracteristica y estado del objeto
	**/
	@Override
	public boolean getRecogido(){
		return super.recogido;
	}
	/**
	Metodo que permite hacer al estado de recogido como verdadero despues de que Deetective lo ha recogido del escenario
	**/
	public void setRecogido(){
		super.recogido=true;
	}
	
}
