package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Samuel David Ortiz
 */
public class CuentaRegresiva {

    private int min, segundos, nuevo;
    Timer tiempo;
    boolean bandera;

    public CuentaRegresiva() {
        setMin(3);
        //setSegundos(3);
        //timer();
        
        System.out.println(getNuevo());
    }

    public void timer(JLabel label) {
        int minuto, segundo = 0;
        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getSegundos() == 0) {
                    setSegundos(60);
                    min--;
                }
                if (min < 0) {
                    int n = JOptionPane.showConfirmDialog(null, "Se se ha agotado el tiempo \n Desea reintentar", "Fin del juego", JOptionPane.YES_NO_CANCEL_OPTION);
                    setNuevo(n);
                    setMin(0);
                    setSegundos(0);
                    tiempo.stop();
                } else {
                    segundos--;

                    if (getSegundos() < 60) {
                        bandera = false;
                    }
                    if (getMin() < 60) {
                        //System.out.println(getMin());
                        if (getSegundos() < 60) {
                            //System.out.println(getSegundos());
                        }
                    }
                }
                //System.out.println("Tiempo " + (getMin() <= 9 ? "0" + getMin() : getMin()) + ":" + (getSegundos() <= 9 ? "0" + getSegundos() : getSegundos()));
                //((getMin()<=9)?"0"+getMin():getMin())+":"+(seg<=9?"0"+seg:seg))
                label.setText("Tiempo " + (getMin() <= 9 ? "0" + getMin() : getMin()) + ":" + (getSegundos() <= 9 ? "0" + getSegundos() : getSegundos()));
            }
        });
        tiempo.start();
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getNuevo() {
        return nuevo;
    }

    public void setNuevo(int nuevo) {
        this.nuevo = nuevo;
    }

    

}
