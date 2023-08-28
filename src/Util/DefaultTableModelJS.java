package Util;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * @author JS server
/**
 * El DefaultTableModelJS es el modelo de la tabla que permite editar o no una celda de la misma.
 * extiende del DefaultTableModel generico de la tabla
 * @author Administrador
 */
public class DefaultTableModelJS extends DefaultTableModel{

    //atributo que me va a permitir determinar que celda no va a ser editable
    private int columnaNoEditable=0;
    private int columnaNoEditable2=0;
    private int columnaNoEditable3=0;

    public int getColumnaNoEditable() {
        return columnaNoEditable;
    }

    public void setColumnaNoEditable(int columnaNoEditable) {
        this.columnaNoEditable = columnaNoEditable;
    }

    public int getColumnaNoEditable2() {
        return columnaNoEditable2;
    }

    public void setColumnaNoEditable2(int columnaNoEditable2) {
        this.columnaNoEditable2 = columnaNoEditable2;
    }

    public int getColumnaNoEditable3() {
        return columnaNoEditable3;
    }

    public void setColumnaNoEditable3(int columnaNoEditable3) {
        this.columnaNoEditable3 = columnaNoEditable3;
    }

    //contructores que tiene su clase padre, deben ir para poder crear modelos de este estilo

    public DefaultTableModelJS(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public DefaultTableModelJS(Object[][] data, Object[] columnNames, int nroColumnaEditable) {
        super(data, columnNames);
        this.setColumnaNoEditable(nroColumnaEditable);
        this.setColumnaNoEditable2(nroColumnaEditable);
        this.setColumnaNoEditable3(nroColumnaEditable);// por la dudas algun dia controlar
    }

     public DefaultTableModelJS(Object[][] data, Object[] columnNames, int nroColumnaEditable,int nroColumnaEditable2) {
        super(data, columnNames);
        this.setColumnaNoEditable(nroColumnaEditable);
        this.setColumnaNoEditable2(nroColumnaEditable2);
        this.setColumnaNoEditable3(nroColumnaEditable);
    }

    public DefaultTableModelJS(Object[][] data, Object[] columnNames, int nroColumnaEditable,int nroColumnaEditable2,int nroColumnaEditable3) {
        super(data, columnNames);
        this.setColumnaNoEditable(nroColumnaEditable);
        this.setColumnaNoEditable2(nroColumnaEditable2);
        this.setColumnaNoEditable3(nroColumnaEditable3);
    }

    public DefaultTableModelJS(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public DefaultTableModelJS(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DefaultTableModelJS(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DefaultTableModelJS(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public DefaultTableModelJS() {
    }
    /**
     * le seteo el nro de la columna que voy a dejar editar
     */
    public DefaultTableModelJS(int nroColumna) {
        this.setColumnaNoEditable(nroColumna);
    }
    
    //sobre escribo el metodo de celda editable
    @Override
    public boolean isCellEditable(int row, int column){
        //si el numero de la columna es igual al numero de la columna que seteo que no sea editable
        if ((column == this.getColumnaNoEditable()) ||
            (column == this.getColumnaNoEditable2())||
            (column == this.getColumnaNoEditable3()))
            return true;
        return false;
    }
}
