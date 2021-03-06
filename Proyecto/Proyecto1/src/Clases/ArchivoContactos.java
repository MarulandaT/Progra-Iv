
/**
 *ArchivoContactos.java
 *V 1.0
 *Enero 25-2019
 */

package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 * Clase encargada de hacer transacciones con el archivo serializable.
 * @author l_mar
 */
public class ArchivoContactos {
    
    //Atributos de Clase
     private File archivo;
     private File export;
     private File Import;
     private ListContacto laux;
     
     /**
      * Constructor de archivos para su apertura
      */
     public ArchivoContactos()
     {
         this.archivo = new File("Contactos.txt");
     }
     
     /**
      * Actualizar el archivo serializable que contiene la lista de contactos
      * @param c 
      */
    public void Update(ListContacto c)
    {
      try {
            FileOutputStream fos = new FileOutputStream(this.archivo);
            ObjectOutputStream cos = new ObjectOutputStream(fos);
            this.archivo.delete();
            this.archivo = new File("Contactos.txt");
            cos.writeObject(c);
            fos.close();
            cos.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } 
     }
    
    /**
     * Sacar los contactos del archivo serializable a un texto plano
     * @param c 
     */
    public void Exportar(ListContacto c)
    {
        String Ruta;
        Ruta = JOptionPane.showInputDialog("Ingrese la dirección de la carpeta"
                + "para guardar el archivo:");
        try {
            this.export = new File(Ruta + "//Export.txt");
            FileWriter fw = new FileWriter(this.export);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (int i=0; i<c.Size(); i++)
                {
                    bw.write(c.getContacto(i).toString());
                    bw.newLine();
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Obtener contactos desde un archivo txt
     * @param lc 
     */
    public void Importar(ListContacto lc)
    {
        String Ruta;
        Ruta = JOptionPane.showInputDialog("Ingrese la dirección "
                + "del archivo a importar:");
        if (Ruta!=null) {       
            try {
                this.Import = new File (Ruta);
                FileReader fr = new FileReader(this.Import);
                BufferedReader br = new BufferedReader(fr) ;
                String Cadena; 
                while ((Cadena=br.readLine()) != null) {
                    String[] parts = Cadena.split(";");
                    lc.Agregar(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
                br.close();
                fr.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Dirección Nula!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Inicializar el archivo con los datos guardados de otras ejecuciones
     * @return 
     */
    public ListContacto CargarAgenda()
    {
        if (this.archivo.exists()) {
            try {
                FileInputStream fis = new FileInputStream(this.archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.laux = (ListContacto)ois.readObject();
                ois.close();
                fis.close();
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        } else {
            this.laux = new ListContacto();
        }
        return this.laux;
    }
}
