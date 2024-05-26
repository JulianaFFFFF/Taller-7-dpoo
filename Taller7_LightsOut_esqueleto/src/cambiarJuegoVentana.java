import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cambiarJuegoVentana extends JDialog implements ActionListener {
    private VentanaJuego juego;
    private JTextField nombreField;
    private JButton cambiarButton;

    public cambiarJuegoVentana(VentanaJuego juego) {
        this.juego = juego;

        setTitle("Cambiar Jugador");
        setSize(300, 100);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Nuevo nombre de jugador:");
        nombreField = new JTextField();
        cambiarButton = new JButton("Cambiar");
        cambiarButton.addActionListener(this);

        add(label, BorderLayout.NORTH);
        add(nombreField, BorderLayout.CENTER);
        add(cambiarButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nuevoNombre = nombreField.getText().trim();
        if (!nuevoNombre.isEmpty()) {
            juego.cambiarNombreJugador(nuevoNombre);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
        }
    }
}

