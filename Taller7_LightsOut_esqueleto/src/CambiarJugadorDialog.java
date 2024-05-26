import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarJugadorDialog extends JDialog implements ActionListener {
    private JTextField nombreField;
    private JButton aceptarButton;
    private VentanaJuego ventana;

    public CambiarJugadorDialog(VentanaJuego ventana) {
        this.ventana = ventana;

        setTitle("Cambiar Jugador");
        setSize(300, 150);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel nombreLabel = new JLabel("Ingrese su nombre (3 caracteres):");
        nombreField = new JTextField(20);
        aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(this);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);

        add(inputPanel, BorderLayout.CENTER);
        add(aceptarButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aceptarButton) {
            String nombre = nombreField.getText().trim().toUpperCase().replace(";", "");
            if (nombre.length() == 3) {
                ventana.cambiarNombreJugador(nombre);
                ventana.reiniciarJuego();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El nombre debe tener exactamente 3 caracteres y no contener ';'", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

