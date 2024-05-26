
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class PanelBotones extends JPanel implements ActionListener {
    
    private JButton nuevoJuego;
    private JButton reiniciarJuego;
    private JButton top10;
    private JButton cambiar;
    private VentanaJuego juego;

    public PanelBotones(VentanaJuego vjuego) {
        juego = vjuego;

        // Usar BoxLayout con orientación Y_AXIS para apilar botones verticalmente
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nuevoJuego = new JButton("Nuevo Juego");
        nuevoJuego.setAlignmentX(CENTER_ALIGNMENT); // Centrar los botones horizontalmente
        nuevoJuego.addActionListener(this);

        reiniciarJuego = new JButton("Reiniciar Juego");
        reiniciarJuego.setAlignmentX(CENTER_ALIGNMENT);
        reiniciarJuego.addActionListener(this);

        top10 = new JButton("Top 10");
        top10.setAlignmentX(CENTER_ALIGNMENT);
        top10.addActionListener(this);
        
        cambiar = new JButton("Cambiar Jugador");
        cambiar.setAlignmentX(CENTER_ALIGNMENT);
        cambiar.addActionListener(this);
        
        
        add(Box.createVerticalGlue()); // Añadir espacio flexible arriba
        add(nuevoJuego);
        add(Box.createVerticalStrut(10)); // Añadir espacio fijo entre los botones
        add(reiniciarJuego);
        add(Box.createVerticalStrut(10)); // Añadir espacio fijo entre los botones
        add(top10);
        add(Box.createVerticalStrut(10)); // Añadir espacio flexible abajo
        add(cambiar);
        add(Box.createVerticalGlue()); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevoJuego) {
            juego.nuevoJuego();
        } else if (e.getSource() == reiniciarJuego) {
            juego.reiniciarJuego();
        } else if (e.getSource() == top10) {
            juego.top10();
        } else if(e.getSource() == cambiar) {
        	juego.cambiarJugador();
        }
        
    }
}

