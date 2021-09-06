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
public class Faktura {
    private Connection connection;
    private String url;
    
    private String brojFakture;
    private String datumPrometa;
    private String datumValute;
    private String sanitarnaOrganizacija;
    
    private ArrayList stavke_fakture;
    private FakturaStavka baf_stavka;
    private boolean faktura_sacuvana=false;
    private FakturaStavka fs;
    

    public Faktura(Connection connection, String brojFakture) {
        this.connection = connection;
        this.brojFakture = brojFakture;
        new FakturaStavka(connection);
    }

    public Faktura(Connection connection) {
        this.connection = connection;
        new FakturaStavka(connection);
        //url="jdbc:oracle:thin:@localhost:1521:XE";
    }
    
  public boolean kreirajFakturu(String brojFakture,String datum,String datum1,String izbelement,float ukupno){
    boolean pom=false;
    try{        
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();

            String upit="INSERT INTO faktura "+
            "(broj_f,datum_prometa,datum_valute,sanitarni_org,ukupno) "+
                "VALUES ('"+brojFakture+"','"+datum+"','"+datum1+"','"+izbelement+"',"+ukupno+")";
     

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
  
 public ArrayList vratiFaktureFrmPrikaziFakture()
{
ResultSet rs;

 ArrayList listaFaktura=new ArrayList();
  try{
      String url="jdbc:oracle:thin:@localhost:1521:XE";
      connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select broj_f\n" +
"from faktura";
         rs=statement.executeQuery(upit);
        
         String pomif="";
         
         while (rs.next()) 
         {   
             pomif=rs.getString(1);
             listaFaktura.add(pomif);
       
         }
           // System.out.println("broj uzetih vrednosti za kombo u prikazi fakture"+listaFaktura.size());                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (listaFaktura);
}

     public boolean kreirajStavkuFakture(String brojFakture,int rbr,int imeSifraI,float cena){
    boolean pom=false;
   
        pom=fs.kreirajStavkuFakture(brojFakture,rbr,imeSifraI,cena);
        
        return pom;
    }
    
   
     
    public ArrayList PrikaziStavkeFakture(String brojF) {
            ArrayList tabela=new ArrayList();
              try{
            
           
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="Select rbr,zaposleni_id,cena \n" +
                    "from faktura_stavka\n" +
                    "where faktura_broj='"+brojF+"'"; 
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                int polje1=rs.getInt(1);
                int polje2=rs.getInt(2);
                int polje3=rs.getInt(3);
          
                tabela.add(polje1);
                tabela.add(polje2);
                tabela.add(polje3);
                
                
            }
            //connection.close();

        }
              
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(tabela);
          
          }

        public float vratiCenu(){
        float vrati=0f;
        try{
    //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="select distinct cena\n" +
            "from faktura_stavka";
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                float polje1=rs.getFloat(1);
              vrati=polje1;  
            }
                 
           // connection.close();        
        }
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(vrati);
    
    
    }
    
}
