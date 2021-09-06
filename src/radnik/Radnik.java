package radnik;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Biljana Nedic
 */
import java.sql.*;
import java.lang.*;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Radnik extends java.lang.Object {
    private int sifRadnik;
    private String imePrezime;
    private String jmbg; 
    private String pozicija; 
    private String adresa;
    private String mobilni;
    private Connection connection;
    private String url;
    
    public Radnik(Connection c) { 
    
    connection=c;
    }
    
     public Radnik() { 
    }
    public Radnik(Connection c, int  SifRad) 
{       
        sifRadnik=SifRad;
        connection=c;

}

    public int getSifRadnik() {
        return sifRadnik;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getPozicija() {
        return pozicija;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getMobilni() {
        return mobilni;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setSifRadnik(int sifRadnik) {
        this.sifRadnik = sifRadnik;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setMobilni(String mobilni) {
        this.mobilni = mobilni;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

   
    
    
     public boolean SacuvajRadnika (int sifRadnik, String imePrezime, String jmbg, String pozicija, String adresa, String mobilni)     //cuva radnika
{
    boolean pom=false;
    try{        
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();

            String upit="INSERT INTO radnik "+
            "(sifra,ime_prezime,maticni_broj,pozicija,adresa,mobilni) "+
                "VALUES ('"+sifRadnik+"','"+imePrezime+"','"+jmbg+"','"+pozicija+"','"+adresa+"','"+mobilni+"')";
     

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
     
     // sacuvati radnika i njegovu organizacionu jedinicu
     
     public boolean SacuvajRadnika2Tabele(int sifRadnik, String imePrezime, String jmbg, String pozicija, String adresa, String mobilni,int zapOrgJedId, int orgJedId,int zapId)
{
    boolean pom1=false;
    boolean pom2=false;
    boolean pom3=false;
    try{        
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();

            String upit="INSERT INTO radnik "+
            "(sifra,ime_prezime,maticni_broj,pozicija,adresa,mobilni) "+
                "VALUES ('"+sifRadnik+"','"+imePrezime+"','"+jmbg+"','"+pozicija+"','"+adresa+"','"+mobilni+"')";
                      
            
            if(statement.executeUpdate(upit)==1)
            pom1=true;           
         
            statement.close();
            statement=connection.createStatement();
            upit="INSERT INTO zap_org_jed "+
            "(zap_org_jed_id,org_jed_id,zap_id) "+
                "VALUES ('"+zapOrgJedId+"','"+orgJedId+"','"+zapId+"')";
            
            if(statement.executeUpdate(upit)==1)
            pom2=true; 
            
            if(pom1 && pom2)
            pom3=true;           
         
            statement.close();
    }
    catch(SQLException sql){
    JOptionPane.showMessageDialog(null, sql);
       
    }
    catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
         
        }  
            return (pom3);
}
     
     
     public boolean ObrisiRadnika(int sifra){
      boolean pom=false;
         try{
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();
            
            String upit="DELETE FROM  radnik "+
            " WHERE  (sifra='"+sifra+"')";

            if(statement.executeUpdate(upit)==1)
            pom=true;

           statement.close();

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }     
     return (pom);
     }
     
     
     public boolean ObrisiRadnika2(int sifra){
      boolean pom1=false;
      boolean pom2=false;
      boolean pom3=false;
         try{
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();
            
            String upit="DELETE FROM  radnik "+
            " WHERE  (sifra='"+sifra+"')";

            if(statement.executeUpdate(upit)==1)
            pom1=true;

            statement.close();
            
            statement=connection.createStatement();
            upit="DELETE FROM  zap_org_jed "+
            " WHERE  (zap_id='"+sifra+"')";

            if(statement.executeUpdate(upit)==1)
            pom2=true;
             if(pom1 && pom2)
            pom3=true;           
         
            statement.close();
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }     
     return (pom3);
     }
     
          public boolean PromeniRadnika(int polje1,String polje2,String polje3,String polje4, String polje5, String polje6){
      boolean pom=false;
             try{
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();
            
            String upit="UPDATE radnik SET "+
            "ime_prezime='"+polje2+"', "+
            "maticni_broj='"+polje3+"', "+
            "pozicija='"+polje4+"', "+
            "adresa='"+polje5+"', "+
            "mobilni='"+polje6+"' "+
            " WHERE  (sifra="+polje1+")";

            if( statement.executeUpdate(upit)==1)
            pom=true;

            statement.close();

        }
        catch(Exception e){

            System.out.println("greska kod Promeni radnika");
        }
             
     return (pom);
     }
          
          public boolean PromeniRadnika2(int polje1, String polje2, String polje3, String polje4, String polje5, String polje6,int zapOrgJedId, int orgJedId,int zapId)
     {
      boolean pom1=false;
      boolean pom2=false;
      boolean pom3=false;
             try{
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();
            
            String upit="UPDATE radnik SET "+
            "ime_prezime='"+polje2+"', "+
            "maticni_broj='"+polje3+"', "+
            "pozicija='"+polje4+"', "+
            "adresa='"+polje5+"', "+
            "mobilni='"+polje6+"' "+
            " WHERE  (sifra="+polje1+")";

            if( statement.executeUpdate(upit)==1)
            pom1=true;

            statement.close();
            statement=connection.createStatement();
                 //System.out.println("PRISTUP BAZI"+ zapOrgJedId+" "+  orgJedId+" "+ zapId);
            upit="UPDATE zap_org_jed SET "+
            "org_jed_id='"+orgJedId+"', "+ //IMA ZAREZ
            "zap_id='"+zapId+"' "+ //NEMA ZAREZ
            " WHERE  (zap_org_jed_id="+zapOrgJedId+")";

            
            if(statement.executeUpdate(upit)==1)
            pom2=true; 
            
            if(pom1 && pom2)
            pom3=true;           
         
            statement.close();

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
             
     return (pom3);
     
     
     }
          
          public boolean PromeniDatumSanitarnog(int sifra, String naziv,String datum){
      boolean pom=false;
             try{
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement statement=connection.createStatement();
                // System.out.println("Datum koji je stigao do radnika je "+datum);
                // System.out.println("Sifra koja je stigla do radnika je "+sifra);
            String upit="UPDATE sanitarni SET "+
            "naziv='"+naziv+"', "+
            "datum_od=TO_DATE('"+datum+"', 'DD-MM-YYYY') "+
            " WHERE  (zaposleni_id="+sifra+")";

            if( statement.executeUpdate(upit)==1)
            pom=true;

            statement.close();

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
             
     return (pom);
     }
          public ArrayList PrikaziRadnike() {
            ArrayList tabela=new ArrayList();
              try{
            
           
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="Select * from radnik";            
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                String polje1=String.valueOf(rs.getInt(1));
                String polje2=rs.getString(2);
                String polje3=rs.getString(3);
                String polje4=rs.getString(4);
                String polje5=rs.getString(5);
                String polje6=rs.getString(6);
                tabela.add(polje1);
                tabela.add(polje2);
                tabela.add(polje3);
                tabela.add(polje4);
                tabela.add(polje5);
                tabela.add(polje6);
                
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
          
          
        public String vratiImePomocuSifre(int sifra) { 
        String imena=null;
              try{
            
           
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="select ime_prezime\n" +
            "from radnik\n" + 
            " WHERE  (sifra="+sifra+")";
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                 String polje1=rs.getString(1);  
                 imena=polje1;
                 
            }
                 
            //connection.close();

        }
              
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(imena);
        
        
        
        }
          public ArrayList PrikaziRadnikePoOrgJedinicama() {
            ArrayList tabela=new ArrayList();
              try{
            
           
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="select ime_prezime,maticni_broj,pozicija,naziv,adresa,mobilni\n" +
"from zap_org_jed left join radnik on zap_org_jed.zap_id=radnik.sifra\n" +
"left join org_jedinica on zap_org_jed.org_jed_id=org_jedinica.org_jed_id";            
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                String polje1=rs.getString(1);
                String polje2=rs.getString(2);
                String polje3=rs.getString(3);
                String polje4=rs.getString(4); 
                String polje5=rs.getString(5);
                String polje6=rs.getString(6);
                tabela.add(polje1);
                tabela.add(polje2);
                tabela.add(polje3);
                tabela.add(polje4);
                tabela.add(polje5);
                tabela.add(polje6);
                
            }
           // connection.close();

        }
              
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(tabela);
          
          }
          
          
        public ArrayList vratiOrgJedinice()/*uzima iz baze vrednosti  
                                           za kombo box kod frmOrgJedinica*/
{ ResultSet rs;

 ArrayList org_jedinice=new ArrayList();
  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select naziv\n" +
"from org_jedinica ";
         rs=statement.executeQuery(upit);
         int i=0;
         String pomif[];
         
         while (rs.next()) 
         {   i++;
             pomif=new String[1]; 
             pomif[0]=rs.getString(1);
             org_jedinice.add(pomif);
       
         }
            System.out.println("broj uzetih vrednosti za kombo u radniku"+org_jedinice.size());                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (org_jedinice);
} 
        
        public ArrayList vratiSifreRadnika()/*uzima iz baze vrednosti  
                                           za kombo box kod frmOrgJedinica*/
{ ResultSet rs;

 ArrayList sifre_radnika=new ArrayList();
  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select sifra\n" +
"from radnik ";
         rs=statement.executeQuery(upit);
         int i=0;
         int pomif;
         
         while (rs.next()) 
         {   i++;
             pomif=rs.getInt(1);
             sifre_radnika.add(pomif);
       
         }
            //System.out.println("broj uzetih vrednosti za kombo u formi PromeniRadnika"+sifre_radnika.size());                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (sifre_radnika);
}  
        
         
        public ArrayList vratiZaSanitarni()/*uzima iz baze vrednosti samo sa datumom_od +12 meseci<current_date
                                           za kombo box kod frmSanitarniPregled*/
{ ResultSet rs;

 ArrayList imePrezime=new ArrayList();
  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select ime_prezime,sifra\n" +
"from radnik left join sanitarni on radnik.SIFRA=sanitarni.ZAPOSLENI_ID \n" +
"where ADD_MONTHS(datum_od, 12)<SYSDATE";
         rs=statement.executeQuery(upit);
         int i=0;
         String pomif[];
         
         while (rs.next()) 
         {   i++;
             pomif=new String[2]; 
             pomif[0]=rs.getString(1);
             pomif[1]=String.valueOf(rs.getInt(2));
             imePrezime.add(pomif);
       
         }
            System.out.println("broj uzetih vrednosti za kombo u radniku"+imePrezime.size());                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (imePrezime);
} 
        
         public ArrayList vratiImeISifruRadnika()/*uzima iz baze vrednosti samo sa datumom_od +12 meseci<current_date
                                           za kombo box kod frmSanitarniPregled*/
{ ResultSet rs;

 ArrayList imePrezime=new ArrayList();
  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select ime_prezime,sifra\n" +
"from radnik";
         rs=statement.executeQuery(upit);
         int i=0;
         String pomif[];
         
         while (rs.next()) 
         {   i++;
             pomif=new String[2]; 
             pomif[0]=rs.getString(1);
             pomif[1]=String.valueOf(rs.getInt(2));
             imePrezime.add(pomif);
       
         }
            System.out.println("broj uzetih vrednosti za kombo u radniku"+imePrezime.size());                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (imePrezime);
} 
        
    public ArrayList vratiFiltriranoOrgJedinice(String nazivOrgJed)/*uzima iz baze vrednosti  
                                           za kombo box kod frmOrgJedinica*/
{
ArrayList tabela=new ArrayList();
              try{
            
           
            //String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
           String upit="select ime_prezime,maticni_broj,pozicija,naziv,adresa,mobilni\n" +
"from zap_org_jed left join radnik on zap_org_jed.zap_id=radnik.sifra\n" +
"left join org_jedinica on zap_org_jed.org_jed_id=org_jedinica.org_jed_id\n" +
"where naziv='"+nazivOrgJed+"'";        
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                String polje1=rs.getString(1);
                String polje2=rs.getString(2);
                String polje3=rs.getString(3);
                String polje4=rs.getString(4); 
                String polje5=rs.getString(5);
                String polje6=rs.getString(6);
                tabela.add(polje1);
                tabela.add(polje2);
                tabela.add(polje3);
                tabela.add(polje4);
                tabela.add(polje5);
                tabela.add(polje6);
                
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
    
    public ArrayList vratiRadnikeSaSifrom()/*uzima iz baze vrednosti  
                                           za kombo box kod frmOrgJedinica*/
{
ArrayList tabela=new ArrayList();
              try{
            
           
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="select sifra,ime_prezime,maticni_broj,pozicija,naziv,adresa,mobilni\n" +
"from zap_org_jed left join radnik on zap_org_jed.zap_id=radnik.sifra\n" +
"left join org_jedinica on zap_org_jed.org_jed_id=org_jedinica.org_jed_id\n";          
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                String polje1=rs.getString(1);
                String polje2=rs.getString(2);
                String polje3=rs.getString(3);
                String polje4=rs.getString(4); 
                String polje5=rs.getString(5);
                String polje6=rs.getString(6);
                String polje7=rs.getString(7);
                tabela.add(polje1);
                tabela.add(polje2);
                tabela.add(polje3);
                tabela.add(polje4);
                tabela.add(polje5);
                tabela.add(polje6);
                tabela.add(polje7);
                
            }
                 
           // connection.close();

        }
              
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(tabela);
}
    
    public String[] vratiRadnikeFrmPromeniRadnika(int sifra)
{
String niz[]=new String[5];
              try{
            
           
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="select ime_prezime,maticni_broj,pozicija,adresa,mobilni\n" +
            "from radnik\n" + 
            "where sifra='"+sifra+"'";
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                String polje1=rs.getString(1);
                String polje2=rs.getString(2);
                String polje3=rs.getString(3);
                String polje4=rs.getString(4); 
                String polje5=rs.getString(5);
                
                niz[0]=polje1;
                niz[1]=polje2;
                niz[2]=polje3;
                niz[3]=polje4;
                niz[4]=polje5;     
            }
                 
            //connection.close();

        }
              
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(niz);
}
    
    
    
    public String[] vratiSanitarneOrg(String naziv)
{
String niz[]=new String[8];
              try{
            
           
           // String url="jdbc:oracle:thin:@localhost:1521:XE";
            //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
            Statement st=connection.createStatement();
            String upit="select naziv_org,adresa,grad,telefon,email,tekuci_racun,maticni_broj,pib\n" +
            "from sanitarni_org\n" + 
            "where naziv_org='"+naziv+"'";
            ResultSet rs=st.executeQuery(upit);   
            while(rs.next()){ 
                String polje1=rs.getString(1);
                String polje2=rs.getString(2);
                String polje3=rs.getString(3);
                String polje4=rs.getString(4); 
                String polje5=rs.getString(5);
                String polje6=rs.getString(6);
                String polje7=rs.getString(7);
                String polje8=rs.getString(8);
                
                niz[0]=polje1;
                niz[1]=polje2;
                niz[2]=polje3;
                niz[3]=polje4;
                niz[4]=polje5;
                niz[5]=polje6;
                niz[6]=polje7;
                niz[7]=polje8;
                
                
               
                
            }
                 
           // connection.close();

        }
              
            catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
            return(niz);
}
    public int vratiPoslednjuSifruRadnika()/*uzima iz baze vrednosti  
                                           za private promenljivu sifraRkraj u frmNoviRadnik2*/
{ ResultSet rs;

 int max_sifra_radnika=1;
  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select max(sifra)\n" +
"from radnik ";
         rs=statement.executeQuery(upit);
         while(rs.next()){
             max_sifra_radnika=rs.getInt(1);
         }
       System.out.println("maksimalna sifra radnika je"+max_sifra_radnika);                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (max_sifra_radnika);
}  
    
     public int vratiPoslednjuSifruZapOrgJed()/*uzima iz baze vrednosti  
                                           za private promenljivu sifraRkraj u frmNoviRadnik2*/
{ ResultSet rs;

 int max_sifra_zap_org_jed=1;
  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select max(zap_org_jed_id)\n" +
"from zap_org_jed ";
         rs=statement.executeQuery(upit);
         while(rs.next()){
             max_sifra_zap_org_jed=rs.getInt(1);
         }
       System.out.println("maksimalna sifra org jedinice je "+max_sifra_zap_org_jed);                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
           
        return (max_sifra_zap_org_jed);
}
    
    public int vratiZapOrgJedId(int org_jed_id1, int zap_id1)//za frmPromeniRadnika2
     {
     int vratiId=0;
     ResultSet rs;


  try{
      //String url="jdbc:oracle:thin:@localhost:1521:XE";
      //connection=DriverManager.getConnection(url, "c##biljana", "biljana");
      Statement statement=connection.createStatement();
          String upit="select zap_org_jed_id\n" +
"from zap_org_jed \n" +
"where (org_jed_id='"+org_jed_id1+"')"+"and (zap_id='"+zap_id1+"')"; 
         rs=statement.executeQuery(upit);
         while(rs.next()){
             vratiId=rs.getInt(1);
         }
      // System.out.println("sifra org jedinice je "+max_sifra_zap_org_jed);                            
    	 statement.close();
 	 }//try

 	  catch(SQLException sql){

            JOptionPane.showMessageDialog(null, sql);
        }
        catch(Exception e){

            JOptionPane.showMessageDialog(null, e);
        }
    return (vratiId);
     }
    
}
