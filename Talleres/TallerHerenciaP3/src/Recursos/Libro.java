/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

/**
 *
 * @author l_mar
 */
public class Libro extends Material implements Prestable{
    //Creacion de atributos
    public boolean Prestado;
    
    //Definición del constructor
    public Libro(String cod, String titulo, int year) {
        super(cod, titulo, year);
        this.Prestado = false;
    }

    //Implementacion de los metodos que define la interfaz
    @Override
    public void prestar() {
        this.Prestado = true;
    }

    @Override
    public void devolver() {
        this.Prestado = false;
    }

    @Override
    public boolean prestado() {
        return this.Prestado;
    }
    
    @Override
    public String toString()
    {
        return this.Titulo+" "+this.Codigo+" "+this.Prestado+" "+this.Year;
    }
    
}
