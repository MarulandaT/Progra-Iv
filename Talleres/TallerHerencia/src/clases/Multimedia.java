/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author l_mar
 */
public class Multimedia {
    
    //Atributos de clase
    public String Titulo; 
    public String Autor;
    public TipoFormato Formato;
    public int Duracion;
    
    //Constructor de clase
    public Multimedia (String titulo, String autor, TipoFormato formato, int duracion)
    {
        this.Titulo = titulo;
        this.Autor = autor;
        this.Formato = formato;
        this.Duracion = duracion;
    }
    
    //Metodos para obtener la información
    public String getTitulo()
    {
        return this.Titulo;
    }
    
    public String getAutor()
    {
        return this.Autor;
    }
    
    public TipoFormato getFormato()
    {
        return this.Formato;
    }
    
    public int getduracion()
    {
        return this.Duracion;
    
    }
    
    //Metodo toString
    @Override 
    public String toString ()
    {
        return this.Autor+" "+this.Formato+" "+this.Titulo+" "+this.Duracion;
    }
    
    //Determinar un metodo equals propio
    public boolean equals()
    {
        if(this.Titulo == this.Autor)
        {
            return true;
        } else {
            return false;
        }
    }
    
}
