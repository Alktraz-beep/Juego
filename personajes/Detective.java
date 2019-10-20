package unam.fi.poo.personajes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unam.fi.poo.personajes.Entidad;
import unam.fi.poo.ventanas.NuevoJuego;
import unam.fi.poo.objetos.*;
import javafx.animation.AnimationTimer;
import java.io.File;
import java.util.ArrayList;
import unam.fi.poo.almacen.Administrador;
/**
Clase que permite generar al Detective que usa el patron de diseño Singleton para que este pueda ser unico durante su partida
al terminar unicamente se reinicia su vida, golpe, puntos y su nombre se reingresa
**/
public class Detective extends ImageView{
	private static String pathDetective=new File("./Images/Personajes/DetectiveDer.gif").getAbsolutePath();
	private static String pathDetectiveIzq=new File("./Images/Personajes/DetectiveIzq.gif").getAbsolutePath();
	//private static String pathGranada=new File("./Images/objetos/GranadaEf.gif").getAbsolutePath();
	private Image image;
	private static String nombre;
	private static int puntos;
	private static int vida=10000;
	private static int dano=50;
	private static int botiquines=0;
	private static int granadas=0;
	private static Detective instancia;
	public static double posX;
	public static double posY;
	private int ancho=300;
	private int alto=500;
	private static double piso=141;
	private AnimationTimer timer;
	private	int rapidez=8;
	private	boolean brincar=false;
	private	long tiempoBrincar=200;
	long time0=System.nanoTime();
	int cont=0;

	/**
	Constructor que permite inicializar al detective con su imagen
	**/
	private Detective(){
		super();
		try{
			this.image=new Image(new File(this.pathDetective).toURI().toString(),this.ancho,this.alto,false,false);
			super.setImage(this.image);
			this.setTranslateY(140);
			this.setTranslateX(-48);
		}catch(Exception e){
			System.out.println("No se cargo el personaje");
		}
	}
	/**
	Metodo que permite obtener el tiempo de brinco para el Detective
	@return tiempoBrincar long que es el tiempo que tarda en brincar
	**/
	public long getTiempo(){
		return this.tiempoBrincar;
	}
	/**
	Metodo que permite obtener el brinco para el Detective
	@return brincar double que es el brinco
	**/
	public boolean getBrincar(){
		return this.brincar;
	}
	/**
	Metodo que permite poner nombre al Detective por medio de la ventana de introduccion que es un nuevo juego
	@param nombre1 String que es el nombre que el jugador decida poner
	**/
	public static void setNombre(String nombre1){
		nombre=nombre1;
	}
	/**
	Metodo que regresa la vida que tiene el jugador
	@return vida que es la vida del jugador
	**/
	public static int getVida(){
		return vida;
	}
	/**
	Metodo que permite poner la vida en caso de reiniciarse, se pone 10000 para la vida
	@param vida1 int que es la vida del jugador
	**/
	public static void setVida(int vida1){
		vida=vida1;
	}
	/**
	Metodo que permite obtener el nombre del Detective que es el jugador 
	@return nombre String que es el nombre del jugador
	**/
	public static String getNombre(){
		return nombre;
	}
	/**
	Metodo que permite obtener el numero de botiquines recojidos y actualizando los usados
	@return botiquines int que es el numero de botiquines actuales del jugador
	**/
	public static int getNumBotiquines(){
		return botiquines;
	}

	/*
	public static int getNumGranadas(){
		return granadas;
	}
	*/

	/**
	Metodo que permite reiniciar puntos en caso de acabar partida 
	**/
	public static void iniciarPuntos(){
		puntos=0;
	}
	/**
	Metodo que permite aumentar los puntos de acuerdo a los enemigos eliminados 
	@param puntosmas int que son los puntos a sumar de lo obtenido
	**/
	public static void setPuntos(int puntosmas){
		puntos+=puntosmas;
	}
	/**
	Metodo que permite poner botiquines, en caso de reiniciar los botiquines a poner es 0
	@param botiquines1 que son el numero de botiquines a poner
	**/
	public static void setBotiquines(int botiquines1){
		botiquines=botiquines1;
	}
	/**
	Metodo que permite obtener puntos del jugador
	@return puntos int que es el numero de puntos
	**/
	public static int getPuntos(){
		return puntos;
	}
	/**
	Metodo que permite poner posicion en las coordenadas xy del detective
	@param x double que es la posicion en x
	@param y double que es la posicion en y
	**/
	public void setPos(double x,double y){
		posX=x;
		posY=y;
	}
	/**
	Metodo que obtiene su coordenada en x del jugador
	@return posX double que es la posicion en x del jugador
	**/
	public double getPosX(){
		return posX;
	}
	/**
	Metodo que obtiene su coordenada en y del jugador
	@return posY double que es la posicion en y del jugador
	**/
	public double getPosY(){
		return posY;
	}
	/**
	Metodo que permite poner un ancho  al jugador 
	@param width int que es el ancho del personaje
	**/
	public void setAncho(int width){
		this.ancho=width;
	}
	/**
	Metodo que permite asignar la rapidez al jugador 
	@param rapidez int que es la rapidez del jugador en la que se desplaza
	**/
	public void setRapidez(int rapidez){
		this.rapidez=rapidez;
	}
	/**
	Metodo que permite poner un alto al jugador 
	@param height int que es el alto del personaje
	**/
	public void setAlto(int height){
		this.alto=height;
	}
	/**
	Metodo que permite asignar la altura en el piso para colocarlo y evitar la logica con diferentes escenarios en la posicion y el salto
	@param piso int que es la altura a la que esta el piso de acuerdo al escenario
	**/
	public void setPiso(double piso){
		this.piso=piso;
	}
	/**
	Metodo que permite obtener el daño del jugador que es el daño con que golpea
	@return dano int que es el daño con que dispara 
	**/
	public static int getDano(){
		return dano;
	}
	/**
	Metodo que permite asignar un daño al detective
	@param dano1 que es el daño a asignar, al reiniciar se pone en 50
	**/
	public static void setDano(int dano1){
		dano=dano1;
	}
	/**
	Metodo que permite saber si esta vivo el jugador
	regresa true si esta vivo y false si esta muerto
	@return validacion boolean que es la validacion de si esta vivo o no
	**/
	public static boolean estaVivo(){
		boolean validacion=true;
		if(vida<=0)
			validacion=false;
		return validacion;
	}
	/**
	Metodo que permite mover al personaje en el eje X a los dos lados permitiendo los cambios 
	@param var int que es la variable, se manda -2 si es a la izquierda, y 2 si es a la derecha
	**/
	public void moverX(int var){
		boolean moverDerecha=var>0;
		cambiarDireccion(var);
		if(moverDerecha==true)
			this.setTranslateX(this.getTranslateX()+10);
		else
			this.setTranslateX(this.getTranslateX()-10);
		System.out.println(""+this.getTranslateX()+" , "+this.getTranslateY());
		setPos(getTranslateX(),getTranslateY());
	}
	/**
	Metodo que permite mover al personaje en el eje Y es un brinco que da y no aumenta por la prolongacion de la tecla presionada, si no
	que es lineal en cada escenario este es una animacion
	@param var int que es la variable, se manda -2 si es a la arriba, y 2 si es a la abajo donde comunmente no hace nada
	**/
	public void moverY(int var){

		this.timer=new AnimationTimer(){
			public void handle(long now){
				brincar=var<0;
				double t =(now-time0)/1000000000.0;
				time0=now;
				if(brincar==true){
					if(instancia.getTranslateY()>piso || instancia.getTranslateY()<(piso-(double)2*alto)){
						rapidez*=-1;
						cont++;
					}
					setTranslateY(getTranslateY()-rapidez);
				}
				if(cont==2){
					cont=0;
					this.stop();
					setPos(getTranslateX(),piso);
				}
			}
		};
		timer.start();
	}

	/**
	Metodo que permite regresar al suelo al detective dada la interpolacion en el brinco permite que este no este por decimas mas arriba o menos 
	y estas no se acumulen a lo largo del juego creando una inconscistencia de la posicion
	**/
	public void regresarAlSuelo(){
		this.setTranslateY(141);
	}
	/**
	Metodo que permite inicializar la instancia del Detective en caso de que este sea nulo para poder hacerlo singleton
	@return instancia Detective que es el Detective como su propio atributo
	**/
	public static Detective getInstance(){
		if(instancia==null)
			instancia=new Detective();
		return instancia;
	}
	/**
	Metodo que permite hacer el cambio de imagenes de la posicion en derecha o izquierda en las interpolaciones de movimiento
	@param var int que es la direccion a donde va se da como parametro -2 si es izquierda y 2 si es derecha
	**/
	public void cambiarDireccion(int var){
		boolean cambiarDerecha=var>0;
		if(cambiarDerecha){
			this.image=new Image(new File(this.pathDetective).toURI().toString(),300,500,false,false);
			this.setImage(this.image);
			this.setTranslateX(getPosX());
		}else{
			this.image=new Image(new File(this.pathDetectiveIzq).toURI().toString(),300,500,false,false);
			this.setImage(this.image);
			this.setTranslateX(getPosX());
		}
	}	
	
	/**
	Metodo que permite bajar la vida del jugador de acuerdo a una interseccion con el enemigo y con el daño que este provoca ya que todos provocan un daño diferente
	@param dano int que es el daño que se disminuye a la vida del jugador
	**/
	public static void bajarVida(int dano){
		vida-=dano;
		mostrarDano();
	}
	/**
	Metodo que permite añadir vida dado el uso de un botiquin
	@param aumento int que es la cantidad de vida que se aumentara por uso de botiquin
	**/
	public static void addVida(int aumento){
		vida+=aumento;
	}
	/**
	Metodo que permite añadir un botiquin al inventario del Detective para ser usado por el jugador
	**/
	public static void addBotiquin(){
		botiquines++;
	}
	/**
	Metodo que permite añadir una mejora de pistola, dependiendo del tipo la usara este aunque es de uso instantaneo
	directamente aumenta el daño con que dispara el Detective
	@param mp MejoraDePistola que es una mejora de pistola de algun tipo A,B o C
	**/
	public static void addMejora(MejoraDePistola mp){
		setDano(dano+mp.getDano());
	}

	/*
	public static void addGranada(){
		granadas++;
	}
	*/


	/**
	Metodo que permite usar uno de los botiquines añadidos 
	y aumentar la vida de acuerdo a la vida que otorga un botiquin
	En caso de no haber no aumenta nada de vida
	**/
	public static void usarBotiquin(){
		if(botiquines>0){
			botiquines--;
			addVida(Botiquin.getVida());
		}else{
			System.out.println("No Tienes Botiquines");
		}
	}
	
	/*
	public static void usarGranada(){
		if(granadas>0){
			granadas--;
		}else{
			System.out.println("No Tienes Granadas");
		}
	}*/

	/**
	Metodo que permite mostrar el daño, que son otras dos imagenes del Detective en rojo mostrando que ha sido dañado
	Hace el cambio de rutas de las imagenes que corresponden a derecha e izquierda
	**/
	public static void mostrarDano(){
		pathDetective=new File("./Images/Personajes/DetectiveDanoDer.gif").getAbsolutePath();
		pathDetectiveIzq=new File("./Images/Personajes/DetectiveDanoIzq.gif").getAbsolutePath();
	}
	/**
	Metodo que cambia las rutas de la imagen del detective para cuando no esta siendo dañado
	**/
	public static void mostrarNormal(){
		pathDetective=new File("./Images/Personajes/DetectiveDer.gif").getAbsolutePath();
		pathDetectiveIzq=new File("./Images/Personajes/DetectiveIzq.gif").getAbsolutePath();
	}
	/**
	Metodo que permite regresar el nombre y puntaje del jugador, con el Detective
	@return datos que es el nombre del jugador y el puntaje del mismo
	**/
	public String convertirATexto(){
		return this.getNombre()+"                                "+this.getPuntos();
	}
}

