import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.*;

import uniandes.dpoo.taller7.modelo.*;

public class VentanaJuego extends JFrame {

    private VentanaTop ventanaTop;
    private PanelOpciones panelOpciones;
    private PanelMarcador panelMarcador;
    private PanelTablero panelTablero;
    private PanelBotones panelBotones;
    private Tablero tablero;
    private Top10 top;
    private int tamanio = 5;
    private int dificultad = 2;
    private int puntaje;
    private String nombreJugador = "ABB";

    public VentanaJuego() {
        tablero = new Tablero(5);
        top = new Top10();
        top.cargarRecords(new File("./data/top10.csv"));
        setTitle("LightsOut");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./data/luz.png").getImage());

        ventanaTop = new VentanaTop(this);
        panelOpciones = new PanelOpciones(this);
        panelMarcador = new PanelMarcador(this);
        panelTablero = new PanelTablero(this);
        panelBotones = new PanelBotones(this);

        setLayout(new BorderLayout());

        this.add(panelOpciones, BorderLayout.NORTH);
        this.add(panelMarcador, BorderLayout.SOUTH);
        this.add(panelTablero, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.EAST);
        int panelX = (getWidth() - panelTablero.getWidth() - getInsets().left - getInsets().right) / 2;
        int panelY = ((getHeight() - panelTablero.getHeight() - getInsets().top - getInsets().bottom) / 2);
        setVisible(true);
        panelTablero.setLocation(panelX, panelY);
        nuevoJuego();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    top.salvarRecords(new File("./data/top10.csv"));
                } catch (FileNotFoundException | UnsupportedEncodingException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        setVisible(true);
    }

    public void jugar(int i, int j) {
        tablero.jugar(i, j);
        panelMarcador.setJugadas(tablero.darJugadas());
        if (tablero.tableroIluminado()) {
            terminarJuego();
        }
    }

    private void terminarJuego() {
        int puntaje = tablero.calcularPuntaje();
        comprobarTop(puntaje);
    }

    public void cambiarJugador() {
        comprobarTop(puntaje);
        CambiarJugadorDialog cambiarJugadorDialog = new CambiarJugadorDialog(this);
        cambiarJugadorDialog.setVisible(true);
    }

    public void cambiarNombreJugador(String nombre) {
        this.nombreJugador = nombre;
        panelMarcador.setNombreJugador(nombre);
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void comprobarTop(int puntaje) {
        if (top.esTop10(puntaje)) {
            top.agregarRegistro(nombreJugador, puntaje);
            JOptionPane.showMessageDialog(this, "�Felicidades! Has alcanzado el top 10.");
        } else {
            JOptionPane.showMessageDialog(this, "El puntaje no es suficiente para el top 10.");
        }
    }

    public void establecerDificultad(int i) {
        this.dificultad = i;
    }

    public void establecerTamanio(int i) {
        this.tamanio = i;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public static void main(String[] args) {
        new VentanaJuego();
    }

    public void nuevoJuego() {
        tablero = new Tablero(tamanio);
        tablero.desordenar(dificultad);
        panelTablero.nuevoPanel(tablero);
        panelMarcador.setJugadas(0);
        panelMarcador.setNombreJugador(nombreJugador);
    }

    public void reiniciarJuego() {
        tablero.reiniciar();
        panelTablero.actulizarPanel();
        panelMarcador.setJugadas(0);
        panelMarcador.setNombreJugador(nombreJugador);
    }

    public void agregarTop(String nombre) throws FileNotFoundException, UnsupportedEncodingException {
        int punt = this.puntaje;
        top.agregarRegistro(nombre, punt);
        ventanaTop.setVisible(false);
    }

    public void top10() {
        // Crear un mensaje con los registros del top 10
        StringBuilder mensaje = new StringBuilder("Top 10 Puntajes:\n");
        int posicion = 1;
        for (RegistroTop10 registro : top.darRegistros()) {
            mensaje.append(posicion).append(". ").append(registro.darNombre()).append(" - ").append(registro.darPuntos()).append("\n");
            posicion++;
        }

        // Mostrar el mensaje en un di�logo
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Top 10 Puntajes", JOptionPane.INFORMATION_MESSAGE);
    }
}
