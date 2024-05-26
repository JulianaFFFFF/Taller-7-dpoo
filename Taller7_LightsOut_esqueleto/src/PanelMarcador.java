import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class PanelMarcador extends JPanel {
    private JLabel jugadas;
    private JLabel nombreJugador;
    private VentanaJuego juego;

    public PanelMarcador(VentanaJuego vjuego) {
        juego = vjuego;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        nombreJugador = new JLabel("Jugador: " + juego.getNombreJugador());
        nombreJugador.setFont(new Font("Arial", Font.BOLD, 15));

        jugadas = new JLabel("Jugadas: 0");
        jugadas.setFont(new Font("Arial", Font.BOLD, 15));

        add(nombreJugador);
        add(jugadas);
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Marcador"));
    }

    public void setJugadas(int conteo) {
        jugadas.setText("Jugadas: " + conteo);
    }

    public void setNombreJugador(String nombre) {
        nombreJugador.setText("Jugador: " + nombre);
    }
}


