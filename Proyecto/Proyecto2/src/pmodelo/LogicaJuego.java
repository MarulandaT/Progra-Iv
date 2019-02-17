/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmodelo;

import pvista.Tablero;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import pcontrolador.GuardarPuntaje;
import pvista.MenuPpal;
import pvista.Ventana;


/**
 *
 * @author l_mar
 */
public class LogicaJuego {
    public static int tamTablero = 15;
    
    public static int tamBarcoSimple = 1;
    public static int tamBarcoDoble = 2;
    public static int barcoSimple = 6;
    public static int barcoDoble = 6;
    
    private JFrame ventana;
    
    private boolean ejecutar;
    
    GuardarPuntaje pun = new GuardarPuntaje();
    
    public void Iniciar() throws Exception {
         
        ventana = new Ventana();
        Ventana x = new Ventana();
        
        x.DimensionVentana(ventana);
        ejecutar = true;
        
        MenuPpal MenuInicio = new MenuPpal(ventana);
        MenuInicio.loadTitleScreen();
        while(MenuInicio.EsVVisible()){}
        
        Barco[] j1Barcos = IniciarBarcos(true);
        Barco[] pcBarcos = IniciarBarcos(false);
        
        Tablero t = new Tablero(DistribuirBarcos(j1Barcos),DistribuirBarcos(pcBarcos));
        JLabel nMovimientos = new JLabel(); 
        
        x.CargarElementos(ventana, t,nMovimientos);
        
        ciclo(t,j1Barcos,pcBarcos,nMovimientos); 
    }
    
    public Barco[] IniciarBarcos(boolean EsJugador1){
        Barco[] barcosSimples = crearBarco(tamBarcoSimple, barcoSimple, EsJugador1);
        Barco[] barcosDobles = crearBarco(tamBarcoDoble, barcoDoble, EsJugador1);
        
        Barco[] barcos = concatArrayBarcos(barcosSimples, barcosDobles);
        return barcos;
    }
    
    public Barco[] crearBarco(int tam, int numElementos, boolean EsJugador1){
        Barco[] listaBarco = new Barco[numElementos];
        for (int i = 0; i < numElementos; i++) {
            PiezaBarco[] BarcoArray = new PiezaBarco[tam];
            for (int j = 0; j < tam; j++){
                PiezaBarco p = new PiezaBarco(EsJugador1);
                BarcoArray[j] = p;
            }
            listaBarco[i] = new Barco(BarcoArray);
        }
        return listaBarco;
    }
    
    public Barco[] concatArrayBarcos(Barco[] s, Barco[] d) {
        int sLen = s.length;
        int dLen = d.length;
        Barco[] c = new Barco[sLen + dLen]; 
        System.arraycopy(s, 0, c, 0, sLen);
        System.arraycopy(d, 0, c, sLen, dLen);
        return c;
    }
    
    public Object[][] DistribuirBarcos(Barco[] b){
        Object[][] resultado = new Object[15][15];
        
        for(int i= 0; i<15; i++){
            for(int  j=0; j<15; j++){
                resultado[i][j] = 0;
            }
        }
        
        int x,y;
        
        for (int i = 9 ; i < 12 ; ) {
            x = (int)Math.floor(Math.random()*14);
            y = (int)Math.floor(Math.random()*15);
            for (int j = 0 ; j < 15 ; j++) {
                for (int k = 0 ; k < 15 ; k++) {
                    if (!resultado[x][y].getClass().getName().equals("pmodelo.PiezaBarco")) {
                        resultado[x][y] = b[i].getPiezaBarco()[0];
                        resultado[x+1][y] = b[i].getPiezaBarco()[1];
                        i++;
                    }
                }
            }
        }
        
        for (int i = 6 ; i < 9 ; ) {
            x = (int)Math.floor(Math.random()*15);
            y = (int)Math.floor(Math.random()*14);
            for (int j = 0 ; j < 15 ; j++) {
                for (int k = 0 ; k < 15 ; k++) {
                    if (!resultado[x][y].getClass().getName().equals("pmodelo.PiezaBarco")) {
                        resultado[x][y] = b[i].getPiezaBarco()[0];
                        resultado[x][y+1] = b[i].getPiezaBarco()[1];
                        i++;
                    }
                }
            }
        }
        
        for (int i = 0 ; i < 6;) {
            x = (int)Math.floor(Math.random()*15);
            y = (int)Math.floor(Math.random()*15);
            for (int j = 0 ; j < 15 ; j++) {
                for (int k = 0 ; k < 15 ; k++) {
                    if (!resultado[x][y].getClass().getName().equals("pmodelo.PiezaBarco")) {
                        resultado[x][y] = b[i].getPiezaBarco()[0];
                        i++;
                    }
                }
            }
        }
        return resultado;
    }
    
    public void ciclo(Tablero t,  Barco[] pc, Barco[] jugador, JLabel Mvtos) throws IOException, Exception {
        
        while(ejecutar) {
            
            Mvtos.setText("Movimientos restantes: " + Integer.toString(t.getDisparo().getTicks()));
            
            boolean todosMuertos1 = true;
            boolean todosMuertos2 = true;
            
            for (int i = 0; i < jugador.length; i++) {
                for (int j = 0; j < jugador[i].getPiezaBarco().length; j++) {
                    if ( !jugador[i].getPiezaBarco()[j].pDestruida()) {
                       todosMuertos1 = false; 
                    }
                }
            }
            
            for (int i = 0; i < pc.length; i++) {
                for (int j = 0; j < pc[i].getPiezaBarco().length; j++) {
                    if ( !pc[i].getPiezaBarco()[j].pDestruida()) {
                       todosMuertos2 = false; 
                    }
                }
            }
            
            if(todosMuertos1 || todosMuertos2 || t.getDisparo().getTicks() == 0) {
                /*
            
                if (todosMuertos1) {
                    
                    Mvtos.setText("Movimientos restantes: " + Integer.toString(0));
                    JLabel res = new JLabel();
                    
                    ventana.getContentPane().remove(t);
                    try { Thread.sleep(5000); 
                    } catch(InterruptedException e ) 
                    { System.out.println("x"); }
                    if (t.getPuntaje() < 15){
                        pun.runFile();
                        pun.savePuntaje("El puntaje es: " + Integer.toString(t.getPuntaje()));
                    }
                    res.setText("HAS GANADO!");
                    res.setLocation(100, 100);
                    res.setSize(200, 40);
                    ventana.getContentPane().add(res);
                    ventana.repaint();
                    this.setEjecutar(false);
                } else if (todosMuertos2) {
                    Mvtos.setText("Movimientos restantes: " + Integer.toString(0));
                    JLabel res = new JLabel();
                    ventana.getContentPane().remove(t);
                    try { Thread.sleep(5000); 
                    } catch(InterruptedException e ) 
                    { System.out.println("x"); }
                    res.setText("CPU HA GANADO!");
                    res.setLocation(100, 100);
                    res.setSize(200, 40);
                    ventana.getContentPane().add(res);
                    ventana.repaint();
                    this.setEjecutar(false);
                } else if (t.getTicks() == 0) {
                    t.repaint();
                    Mvtos.setText("Movimientos restantes: " + Integer.toString(0));
                    JLabel res = new JLabel();
                    for (int i = 0; i < jugador.length; i++) {
                        for (int j = 0; j < jugador[i].getPiezaBarco().length; j++) {
                            jugador[i].getPiezaBarco()[j].Destruido();
                        }
                    }
                    t.repaint();
                    ventana.getContentPane().remove(t);
                    try { Thread.sleep(5000); 
                    } catch(InterruptedException e ) 
                    { System.out.println("x"); }
                    res.setText("HAS PERDIDO");
                    res.setLocation(100, 100);
                    res.setSize(200, 40);
                    ventana.getContentPane().add(res);
                    ventana.repaint();
                    this.setEjecutar(false);
                } */
            } 
            t.repaint();
        }
    }
    
    public void setEjecutar(boolean E) {
        ejecutar = E;
    }
}