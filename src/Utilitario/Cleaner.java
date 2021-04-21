package Utilitario;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Cleaner {

    /**
     * *
     *
     * @param panel
     */
    public static void limpiarCampos(JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JTextField) {
                ((JTextField) panel.getComponent(i)).setText("");
            } else if (panel.getComponent(i) instanceof JPasswordField) {
                ((JPasswordField) panel.getComponent(i)).setText("");
            } else if (panel.getComponent(i) instanceof JComboBox) {
                ((JComboBox) panel.getComponent(i)).setSelectedIndex(-1);
            }
        }
    }
}
