/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radnik;
import java.sql.*;
import java.lang.*;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Biljana
 */
public class FakturaStavka  {
    private Connection connection;
    private String brojFakture;
    private int rbr;
    private int zaposleniId;
    private float cena;
    private String url;
    
    

    public FakturaStavka(Connection connection,String brojFakture, int rbr, int zaposleniId, float cena) {
     
        this.connection = connection;
        this.brojFakture = brojFakture;
        this.rbr = rbr;
        this.zaposleniId = zaposleniId;
        this.cena = cena;
    }

    public FakturaStavka(Connection connection) {
        this.connection = connection;
        
        url="jdbc:oracle:thin:@localhost:1521:XE";
    }

    public FakturaStavka(Connection connection, String brojFakture, int rbr) {
       this.connection = connection;
        this.brojFakture = brojFakture;
       
        this.rbr = rbr;
    }
   

     
     public boolean kreirajStavkuFakture(String brojFakture,int rbr,int imeSifraI,float cena){
    boolean pom=false;
    try{        
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();
        System.out.println("broj fakture pre baze je"+brojFakture);
        System.out.println("rbr je"+rbr);
        System.out.println("ime sifra je"+imeSifraI);
        System.out.println("cena je"+cena);
            String upit="INSERT INTO faktura_stavka "+
            "(rbr,faktura_broj,zaposleni_id,cena) "+
                "VALUES ("+rbr+",'"+brojFakture+"',"+imeSifraI+","+cena+")";
     

            if(statement.executeUpdate(upit)==1)
            pom=true;           
         
            statement.close();
    }
    catch(SQLException sql){
    JOptionPane.showMessageDialog(null, sql);
       
    }
    catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
         
        }  
            return (pom);
    }
    
}
