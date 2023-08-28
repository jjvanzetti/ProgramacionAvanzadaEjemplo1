package Util;

import java.util.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class UtilJtable {

    public void newModelJS(JTable table, String [] titulo){
        table.setModel(new DefaultTableModelJS(
            new Object [][] {},titulo) {
            Class[] types = new Class [] {    //aca tengo quee poner toda la cantidad de objeto//
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class, 
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
    }  

    public static HashSet convertToSet (JTable table ) {
         HashSet set=new HashSet();
         int i=0;
         for(i=0;i<table.getRowCount();i++){
             set.add(table.getValueAt(i, 0));
          }
         return set;  
    }
    
    public static TreeSet convertToTreeSet (JTable table ) {
        TreeSet set= new TreeSet();
        int i=0;
        for (i=0;i<table.getRowCount();i++){
            set.add(table.getValueAt(i, 0));}
        return set;  
    }   

    public static TreeSet convertToTreeSet (List table ) {
        TreeSet set= new TreeSet();
        int i=0;
        for (i=0;i<table.size();i++){
            set.add(table.get(i));}
        return set;
    }


    public static TreeSet convertirHashSetToTreeSet(Set lista){
        TreeSet set= new TreeSet();
         Iterator ite = lista.iterator();
         while(ite.hasNext()){
             set.add(ite.next());
         }
         return set;
    }
    
     public static DefaultListModel convertirTreeSetToModelo(Set lista) {
        DefaultListModel modelo= new DefaultListModel();
        Iterator it = lista.iterator();
        while(it.hasNext())
         {
            modelo.addElement(it.next());
         }
        return modelo;
    
    }   
  
     public static List convertirSetToLista(Set lista) {
        List modelo= new ArrayList();
        int i=0;
        Iterator it = lista.iterator();
        while(it.hasNext())
         {
            modelo.add(it.next());
         }
        return modelo;
    
    }
     
    public static Set convertToSet (JList list ) {
         DefaultListModel modeloLista=(DefaultListModel) list.getModel();
         Set set=new HashSet();
         int i=0;
         for(i=0;i<modeloLista.getSize();i++){
            set.add(modeloLista.getElementAt(i));  }
         
         return set;  

    }
    
   public DefaultComboBoxModel convertirArrayToModelo(String[] array) {
        DefaultComboBoxModel modeloAux= new DefaultComboBoxModel();
        int i=0;
        for(i=0;i<array.length ;i++){
          if  (array[i].equals("")){}
          else 
           modeloAux.addElement(array[i]);
         }
         return modeloAux;  
    }
      //fin convertidores 
    
    /** datos a pasar ancho de la columna, cada subindice del string es la columna que pas
     * @param tablao*/
    public void anchoTabla(JTable tabla, String[] columnas){
        TableColumn column = null;
        //Iterator ite = columnas.iterator();
        for(int i=0;i<columnas.length;i++){
            column = tabla.getColumnModel().getColumn(i);
            column.setPreferredWidth(Integer.parseInt(columnas[i]));

        }
    }

    public void columnaInvisibleTabla(JTable tabla, int indice){
        TableColumn column = null;
        column = tabla.getColumnModel().getColumn(indice);
        tabla.removeColumn(column);
    }

}
