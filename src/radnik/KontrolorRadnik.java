package radnik;
import java.sql.*;
import java.lang.*;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Biljana Nedic
 */
public class KontrolorRadnik {
    private Connection connection;
    private String url; 
    private Radnik radnik;
    
        public KontrolorRadnik() throws Exception 
  {   url="jdbc:oracle:thin:@localhost:1521:XE";  
      connection=DriverManager.getConnection(url, "c##biljana", "biljana");
        radnik=new Radnik(connection);     

    }
    

    
public boolean SacuvajRadnika(int sifRadnik, String imePrezime, String jmbg, String pozicija, String adresa, String mobilni)   //cuva radnika
{
    boolean pom=false;
 try {pom=radnik.SacuvajRadnika(sifRadnik,imePrezime,jmbg,pozicija,adresa,mobilni);
    if (pom!=true){connection.rollback();}
    connection.close();}//try
catch (SQLException sql) {JOptionPane.showMessageDialog(null, sql);}
return (pom);
}

public boolean SacuvajRadnika2Tabele(int sifRadnik, String imePrezime, String jmbg, String pozicija, String adresa, String mobilni,int zapOrgJedId, int orgJedId,int zapId){
 boolean pom=false;
pom=radnik.SacuvajRadnika2Tabele(sifRadnik, imePrezime, jmbg, pozicija, adresa, mobilni,zapOrgJedId, orgJedId,zapId);
 
return (pom);

}
public boolean PromeniRadnika2(int sifRadnik, String imePrezime, String jmbg, String pozicija, String adresa, String mobilni,int zapOrgJedId, int orgJedId,int zapId){
 boolean pom=false;
pom=radnik.PromeniRadnika2( sifRadnik,  imePrezime,  jmbg,  pozicija,  adresa,  mobilni, zapOrgJedId,  orgJedId, zapId);
 
return (pom);
}

public boolean ObrisiRadnika(int sifra)   //brise radnika
{
    boolean pom=false;
    pom=radnik.ObrisiRadnika(sifra);
        
    return (pom);
}

public boolean PromeniRadnika(int polje1,String polje2,String polje3, String polje4,String polje5,String polje6)   //cuva radnika
{
     boolean pom=false;
pom=radnik.PromeniRadnika(polje1,polje2,polje3,polje4,polje5,polje6);
 
return (pom);
}

 public ArrayList PrikaziRadnike(){
 ArrayList tabela=radnik.PrikaziRadnike();
 return(tabela);
 }

public ArrayList PrikaziRadnikePoOrgJedinicama(){
 ArrayList tabela=radnik.PrikaziRadnikePoOrgJedinicama();
 return(tabela);
 }

     public ArrayList vratiOrgJedinice()/*uzima iz baze vrednosti  
                                           za kombo box kod frmOrgJedinica*/
{
    ArrayList orgJedKombo=radnik.vratiOrgJedinice();
    return (orgJedKombo);
}
     
     public ArrayList vratiSifreRadnika()/*uzima iz baze vrednosti  
                                           za kombo box sifre, kod frmPromeniRadnika2*/
{
    ArrayList sifreRadnik=radnik.vratiSifreRadnika();
    return (sifreRadnik);
}
      public int vratiPoslednjuSifruRadnika()/*uzima iz baze vrednosti  
                                           za private promenljivu sifraRkraj u frmNoviRadnik2*/
{
    int maxsifraRadnik=radnik.vratiPoslednjuSifruRadnika();
    return (maxsifraRadnik);
    
}
      public int vratiPoslednjuSifruZapOrgJed()/*uzima iz baze vrednosti  
                                           za private promenljivu sifraOJRkraj u frmNoviRadnik2*/
{
    int maxsifraZapOrgJed=radnik.vratiPoslednjuSifruZapOrgJed();
    return (maxsifraZapOrgJed);
    
}
      

    public ArrayList vratiFiltriranoOrgJedinice(String nazivOrgJed)/*uzima filtrirane vrednosti
                                                iz baze za kombo box kod frmOrgJedinica*/
{
    ArrayList orgJedKombo=radnik.vratiFiltriranoOrgJedinice(nazivOrgJed);
    return (orgJedKombo);
}
    public ArrayList vratiRadnikeSaSifrom()/*uzima filtrirane vrednosti
                                                iz baze za kombo box kod frmPromeniRadnika*/
{
    ArrayList orgJedKombo=radnik.vratiRadnikeSaSifrom();
    return (orgJedKombo);
}
    
    public String[] vratiRadnikeFrmPromeniRadnika(int sifra){
    String[] nizRadnika=radnik.vratiRadnikeFrmPromeniRadnika(sifra);
    return (nizRadnika);
    }
    
    public String[] vratiSanitarneOrg(String naziv){
    String[] nizPodataka=radnik.vratiSanitarneOrg(naziv);
    return (nizPodataka);
    }
    
    public ArrayList vratiZaSanitarni()
     {
     ArrayList vratiZaSan=radnik.vratiZaSanitarni();
    return (vratiZaSan);
     }
    public ArrayList vratiImeISifruRadnika()
     {
     ArrayList vratiSifraIme=radnik.vratiImeISifruRadnika();
    return (vratiSifraIme);
     }
     public int vratiZapOrgJedId(int org_jed_id, int zap_id)
     {
     int vratiId=radnik.vratiZapOrgJedId(org_jed_id,zap_id);
    return (vratiId);
     }
      public String vratiImePomocuSifre(int sifra) { 
         String vratiIme=radnik.vratiImePomocuSifre(sifra);
    return (vratiIme); 
        
        
        }
     
     
     public boolean ObrisiRadnika2(int sifra){
     boolean pom=false;
    pom=radnik.ObrisiRadnika2(sifra);
        
    return (pom);
     }
     
     public boolean PromeniDatumSanitarnog(int izabranaSifra,String naziv, String datum){
      boolean pom=false;
      pom=radnik.PromeniDatumSanitarnog(izabranaSifra,naziv,datum);
      
        return (pom);
     
     }
}
