package scaladrone;
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.PrintWriter;
 
public class Fichier
{
	public static void ecrire(String path, String text) 
	{
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter(path));
			ecri.print(text);
			ecri.flush();
			ecri.close();
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a)
		{
			System.out.println("Problème d'IO");
		}
	}//ecrire
 
	public String lire (String path) 
	{
		BufferedReader lect ;
		String tmp = "";
		try
		{
			lect = new BufferedReader(new FileReader(path)) ;
			while (lect.ready()==true) 
			{
				tmp += lect.readLine() ; 
			}//while
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
		return tmp;
	}//lecture 

	 public static void main (String[] args){
		//Fichier  f= new Fichier();
		Drone drone= new Drone();
		
		   for (int i=0; i<drone.getClass().getMethods().length - 1;i++) 
			   System.out.println(drone.getClass().getMethods()[i]);

		   
	 }
}
