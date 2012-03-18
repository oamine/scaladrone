package test
/**
 * This Main file generate a
 * all command class file from scaladrone file
 * Don't use it
 * It's just a tool...
 * 
 * Amine
 * 
 * */

import scala.actors.Actor
import scala.actors.Actor._
import scaladrone._
import java.lang.reflect.Method
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.PrintWriter;

object Main {

  
  /**
   * prend une methode en parametre et renvoie
   * les parametre de cette method séparé
   * par un , et avec les parentheses
   * */
  def properParam(m : Method) : String = {
	  var params = m getParameterTypes()
	  var res = "("
	  for (i<- 0 to params.length-1){
	    if (i>0) res+=", "
	      res += params(i).toString().charAt(1) + " : "+params(i).toString().first.toUpper+params(i).toString().substring(1)
	    //res += params(i).toString().split("\\.")(params(i).toString().split("\\.").length-1).charAt(0) + " : "+params(i).toString()
	  }
	  res += ")"
	  if (res=="()") res = ""
	  return res
  }
  
  
  /**
   * ecrit dans un fichier
   * */
   	def ecrire(path : String, text : String){
		var ecri = new PrintWriter(new FileWriter(path))
		try
		{
			ecri = new PrintWriter(new FileWriter(path));
			ecri.print(text);
			ecri.flush();
			ecri.close();
		}
	} 
  
  	/**
  	 * 
  	 * renvoie juste les noms des parametres
  	 * sans leur Type
  	 * */
    def properParamMini(m : Method) : String = {
	  var params = m getParameterTypes()
	  var res = "("
	  for (i<- 0 to params.length-1){
	    if (i>0) res+=", "
	      res += params(i).toString().charAt(1)
	  }
	  res += ")"
	  if (res=="()") res = ""
		  return res
  }
  
    
    /**
     * prend une methode en parametre et renvoie
     * ce que la methode retourne sous forme de string
     * "Float" ou n'importe quoi.... sous la forme :
     *  ": String =" 
     *  sinon rien 
     * */
  def properReturnType(m : Method) : String={
    var params = m.getReturnType()
    if (params.toString() != "void")
    	return " : "+params.toString().first.toUpper+params.toString().substring(1)+" = "
    else
    	return ""
  }
  
  def main(args: Array[String]) {
		//Fichier  f= new Fichier();
		var drone= new Drone();
		
		for (i<- 0 to drone.getClass().getMethods().length-1) {
		  
		   var toto = ("package command\n\n"
				   +"/**" +
				     "* Command for scaladrone" +
				   	 "* @author Amine O." +
				   	 "* @author Sarah A." +
				   	 "*/"
				   +"import scaladrone.Drone\n"
				   +"import scala.actors.Actor\n\n"
			       +"case class "+drone.getClass().getMethods()(i).getName().first.toUpperCase
			       +properParam(drone.getClass().getMethods()(i))
			       + " extends Command "
			       +"{\n"
			       +"	def execute(d : Drone){\n"
			       +"		d."+drone.getClass().getMethods()(i).getName()+properParamMini(drone.getClass().getMethods()(i))+" \n"
			       +"	}\n"
			       +"}"
		   +"")
		   var path = drone.getClass().getMethods()(i).getName().first.toUpperCase+drone.getClass().getMethods()(i).getName().substring(1)+".scala"
		   this.ecrire(path,toto)


		   //println(drone.getClass().get)
		}
	 }
}