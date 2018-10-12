
package Clases;
import java.io.Serializable;

public class Contacto implements Serializable
{
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String alias;

    public Contacto(String Nombre, String Telefono, String Correo, String Direccion, String Alias)
    {
        this.nombre=Nombre;
        this.telefono=Telefono;
        this.correo=Correo;
        this.direccion=Direccion;
        this.alias=Alias;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getAlias() {
        return alias;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAlias(String Alias) {
        this.alias = Alias;
    }

    @Override
    public String toString() {
        return nombre + ";" + telefono + ";" + correo + ";" + direccion + ";" + alias;
    }
   
}
