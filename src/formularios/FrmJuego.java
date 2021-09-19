package formularios;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel David Ortiz
 */
public class FrmJuego implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel game_panel;
    JLabel texto = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean jugador1;
    
    private JMenu menu;
    private JMenuItem m1;
    
    private String turn, modo;
    private int  dia, mes, año, hora, minuto, segundo;

    public FrmJuego() {

        game_panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(23, 112, 166));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        texto.setBackground(new Color(23, 112, 166));
        texto.setForeground(new Color(255, 255, 255));
        texto.setFont(new Font("Ink Free", Font.BOLD, 45));
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setText("UMG TIC TAC TOE GAME 1.0");
        texto.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        game_panel.setLayout(new GridLayout(3, 3));
        game_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            game_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        
        // <editor-fold defaultstate="collapsed" desc="Menu">
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Archivo");
        m1 = new JMenuItem("Estadísticas");
        m1.addActionListener(this);
        menu.add(m1);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        // </editor-fold>

        title_panel.add(texto);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(game_panel);
        
        frame.setLocationRelativeTo(null);

        turnoUno();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                if (jugador1) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        jugador1 = false;
                        //texto.setText("Turno de O");
                        check();
                    }
                    //check();
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        jugador1 = true;
                        //texto.setText("Turno de X");
                        check();
                    }
                }
            }
        }
        
        if (e.getSource() == m1) {
            System.out.println("Hola estadisticas");
        }
    }

    public void turnoUno() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (random.nextInt(2) == 0) {
            jugador1 = true;
            //texto.setText("Turno de X");
        } else {
            jugador1 = false;
            //texto.setText("Turno de O");
        }
    }

    public String check() {
        int aa = 0, b = 0, c = 0;
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = buttons[0].getText() + buttons[1].getText() + buttons[2].getText();
                    aa = 0; b = 1; c = 2;
                    break;
                case 1:
                    line = buttons[3].getText() + buttons[4].getText() + buttons[5].getText();
                    aa = 3; b = 4; c = 5;
                    break;
                case 2:
                    line = buttons[6].getText() + buttons[7].getText() + buttons[8].getText();
                    aa = 6; b = 7; c = 8;
                    break;
                case 3:
                    line = buttons[0].getText() + buttons[3].getText() + buttons[6].getText();
                    aa = 0; b = 3; c = 6;
                    break;
                case 4:
                    line = buttons[1].getText() + buttons[4].getText() + buttons[7].getText();
                    aa = 1; b = 4; c = 7;
                    break;
                case 5:
                    line = buttons[2].getText() + buttons[5].getText() + buttons[8].getText();
                    aa = 2; b = 5; c = 8;
                    break;
                case 6:
                    line = buttons[0].getText() + buttons[4].getText() + buttons[8].getText();
                    aa = 0; b = 4; c = 8;
                    break;
                case 7:
                    line = buttons[2].getText() + buttons[4].getText() + buttons[6].getText();
                    aa = 2; b = 4; c = 6;
                    break;
            }
            //For X winner
            if (line.equals("XXX")) {
                ganaX(aa,b,c);
                estadisticas("X");
                return "X";
            } // For O winner
            else if (line.equals("OOO")) {
                ganaO(aa,b,c);
                estadisticas("O");
                return "O";
            }
        }
        
        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(buttons).contains(
                    String.valueOf(a + 1))) {
                break;
            }
            else if (a == 8) {
                return "draw";
            }
        }
        
        System.out.println(
            turn + "'s turn; enter a slot number to place "
            + turn + " in:");
        return null;
    }

    public void ganaX(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            buttons[i].setOpaque(true);
        }
        JOptionPane.showMessageDialog(null, "Gano X");
    }

    public void ganaO(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            buttons[i].setOpaque(true);
        }
        JOptionPane.showMessageDialog(null, "Gano 0");

    }
    
    public void modo(String modo){
        setModo(modo);
    }
    
    public void estadisticas(String ganador){
        String hora = hora();
        Date objDate = new Date(); 
        
        System.out.println(objDate); 
        String strDateFormat = "yyyymmddhhmmss"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto 
        
        System.out.println("El ganador fue " + ganador + "en modo " + getModo() + " " + objSDF.format(objDate));
    }
    
    public String hora(){
        Calendar calendario = Calendar.getInstance();
        setHora(calendario.get(Calendar.HOUR_OF_DAY));
        setMinuto(calendario.get(Calendar.MINUTE));
        setSegundo(calendario.get(Calendar.SECOND));
        
        String hr;
        hr = null;

        hr = (getHora()<=9?"0"+getHora():getHora()) + ":" + (getMinuto()<=9?"0"+getMinuto():getMinuto()) + ":" + (getSegundo()<=9?"0"+getSegundo():getSegundo());
        return hr;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }
}
