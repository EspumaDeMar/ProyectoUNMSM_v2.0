package Utilitario;

import javax.swing.table.DefaultTableModel;

public class ModeloJTable extends DefaultTableModel {

    public ModeloJTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }    

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
