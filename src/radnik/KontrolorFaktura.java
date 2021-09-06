/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radnik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Biljana
 */
public class KontrolorFaktura {
    private Connection connection;
    private String url; 
    private Faktura f;
    private FakturaStavka kf;
 
    
    public KontrolorFaktura() throws Exception 
  {      url="jdbc:oracle:thin:@localhost:1521:XE";  
      connection=DriverManager.getConnection(url, "c##biljana", "biljana");
        f=new Faktura(connection); 
        kf=new FakturaStavka(connection);
    

    }
    
        public KontrolorFaktura(Connection connection) throws Exception 
  {      url="jdbc:oracle:thin:@localhost:1521:XE";  
      connection=DriverManager.getConnection(url, "c##biljana", "biljana");
        f=new Faktura(connection);     
    

    }

    public boolean kreirajFakturu(String brojFakture,String datum,String datum1,String izbelement,float ukupno){
    boolean pom=false;
        pom=f.kreirajFakturu(brojFakture,datum,datum1,izbelement,ukupno);
        return pom;
    
    }
    
    public boolean kreirajStavkuFakture(String brojFakture,int rbr,int imeSifraI,float cena){
    boolean pom=false;
   
        pom=kf.kreirajStavkuFakture(brojFakture,rbr,imeSifraI,cena);
        
        return pom;
    }
    
    public ArrayList vratiFaktureFrmPrikaziFakture(){
    ArrayList listaFaktura=f.vratiFaktureFrmPrikaziFakture();
    return (listaFaktura);
    }
    
     public ArrayList PrikaziStavkeFakture(String brojF){
 ArrayList tabela=f.PrikaziStavkeFakture(brojF);
 return(tabela);
 }
     public float vratiCenu(){
    float c=f.vratiCenu();
    return (c);
    }
}
