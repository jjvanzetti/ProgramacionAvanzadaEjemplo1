package Util;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JS server
 */
/**
 * El DefaultTableModelJS es el modelo de la tabla que permite editar o no una celda de la misma.
 * extiende del DefaultTableModel generico de la tabla
 * @author Administrador
 */
public class DefaultTableModelJSNotEdit extends DefaultTableModel{

    //contructores que tiene su clase padre, deben ir para poder crear modelos de este estilo

    public DefaultTableModelJSNotEdit(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public DefaultTableModelJSNotEdit(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public DefaultTableModelJSNotEdit(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DefaultTableModelJSNotEdit(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DefaultTableModelJSNotEdit(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public DefaultTableModelJSNotEdit() {
    }
    
    
    //sobre escribo el metodo de celda editable
    @Override
    public boolean isCellEditable(int row, int column){
        //si el numero de la columna es igual al numero de la columna que seteo que no sea editable
        return false;
    }

}
