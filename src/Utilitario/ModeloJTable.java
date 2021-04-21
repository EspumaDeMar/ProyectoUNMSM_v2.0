package Utilitario;

import javax.swing.table.DefaultTableModel;

/***
 * 
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class ModeloJTable extends DefaultTableModel {

    /***
     * 
     * @param data
     * @param columnNames 
     */
    public ModeloJTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }    

    /***
     * 
     * @param row
     * @param column
     * @return {@code boolean}
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
