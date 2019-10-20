package unam.fi.poo.almacen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import unam.fi.poo.personajes.Detective;
/**
Clase Administrador, permite hacer el registro y mostrar la informacion que se muestra en score, que es el nombre y puntaje del jugador
@version 30/05/2019
**/

public class Administrador{
	private final static String RUTA_ARCHIVOS="./almacen/";
	private final static String NOMBRE_SCORES="Scores.txt";
	
	public final static String SEPARADOR_INFORMACION=":";
	File almacen;
	/**
	Constructor de administrador permite iniciar la ruta
	**/
	public Administrador(){
		almacen=new File(RUTA_ARCHIVOS+NOMBRE_SCORES);
	}
	/**
	**/
	public void registrarInformacion(){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(almacen,true));
			bw.write(Detective.getInstance().convertirATexto());
			bw.newLine();
			bw.flush();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	Metodo que permite mostrar la informacion por medio de un array de string 
	@return data que es el arreglo con la informacion de los jugadores
	**/
	public ArrayList<String> mostrarInformacion(){
		ArrayList<String> data = new ArrayList<String>();
		try{
			BufferedReader br=new BufferedReader(new FileReader(almacen));
			String line=null;
			while((line=br.readLine())!=null){
				data.add(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return data;
	}
}
