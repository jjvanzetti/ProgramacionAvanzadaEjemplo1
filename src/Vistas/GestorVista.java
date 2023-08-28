package Vistas;

import GUtilr.mdi.MDIForm;
import Hibernate.GestorHibernate;
import Util.UtilJtable;
import ireport.GestorDeReportes;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestorVista  extends GestorHibernate {
    private int opcABM; // Se define para saber si es actualizacion o alta
    private JDesktopPane escritorio;
    private MDIForm pantalla;
    private DefaultComboBoxModel modelCombo;
    private GestorDeReportes gestorReportes;
    private String titulo;
    private UtilJtable UtilTable= new UtilJtable();
    private FrmGenerica auxForm;
    public static final String pathReportes="C:\\PP\\Reportes\\";
    
    public UtilJtable getUtilTable() {
        return UtilTable;
    }

    public void setUtilTable(UtilJtable UtilTable) {
        this.UtilTable = UtilTable;
    }
    
    public GestorDeReportes getGestorReportes() {
        return gestorReportes;
    }

    public void setGestorReportes(GestorDeReportes gestorReportes) {
        this.gestorReportes = gestorReportes;
    }
    

    public int getOpcABM() {
        return opcABM;
    }
    
    public void setOpcABM(int opcABM) {
        this.opcABM = opcABM;
    }

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public void setEscritorio(JDesktopPane escritorio) {
        this.escritorio = escritorio;
    }

    public DefaultComboBoxModel getModelCombo() {
        return modelCombo;
    }

    public void setModelCombo(DefaultComboBoxModel modelCombo) {
        this.modelCombo = modelCombo;
    }

    public MDIForm getPantalla() {
        return pantalla;
    }

    public void setPantalla(MDIForm pantalla) {
        this.pantalla = pantalla;
    }

    public FrmGenerica getAuxForm() {
        return auxForm;
    }

    public void setAuxForm(FrmGenerica auxForm) {
        this.auxForm = auxForm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public  void setModoNuevo() {
        this.setOpcABM(0);
    }
    
    public  void setModoEditar() {
        this.setOpcABM(1);
    }
   
    public boolean isEdit() {
        return (this.getOpcABM() ==1);
    }
 
    public boolean isExtra() {
        return (this.getOpcABM() >=2);
    }

    public boolean isNuevo() {
        return (this.getOpcABM() ==0);
    }

    public boolean isOnlyRead() {
        return (this.getOpcABM() ==10);
    }
    
    public int convertirToInteger(String valor) {
        int numero;
        if ((valor).equals("")) 
            numero=0;           
        else 
           numero = Integer.valueOf(valor);
        
        return numero;
    }
    
    public int convertirToInteger(JTextField txt) {
        return convertirToInteger(txt.getText());
    }
    
    public String convertirToString(int valor){
        String numeroString;
        if (valor==0)  
            numeroString="";           
        else 
           numeroString = String.valueOf(valor);
        
        return numeroString;
    }
  public boolean isCero(JTextField txt){
        return txt.getText().equals("0");
    }
  
    public boolean isEmpty(JTextField txt) {
        return txt.getText().equals("");

    }
    
    public boolean isEmpty(JComboBox cmb) {
        return  cmb.getSelectedItem().equals("");
    }
   
    public boolean isEmpty( JTable tbl) {
        return tbl.getRowCount()==0;
    }


    public  DefaultComboBoxModel getComboModelFraccionamiento() {
        Object[] model={"" , "Unidad", "Kg"};
        return new DefaultComboBoxModel(model);
    }
    

    public void cancelarView() {

    }

 
    public void deleteView() {
     
    }

    public void getView() {
      
    }

    public boolean isDatosValidos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void newModel() {
      
    }

    public void saveView() {

    }


    public void actualizarView() {
      
    }

    
    public boolean getModelXCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int setModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public String getTipoCopia(int copia){
        String auxValor="";
        switch (copia){
            case 0:
                 auxValor="Original";
                 break;
            
            case 1:
                 auxValor="Duplicado";
                 break;
             
            case 2:
                 auxValor="Triplicado";
                 break;
        }
        return auxValor;
    }

    public void abrirListado(String archivo){
        try{
            gestorReportes= new GestorDeReportes(archivo);
            gestorReportes.agregarParametro("tituloMembrete", "");
            gestorReportes.agregarParametro("tituloMembrete2", "");
            gestorReportes.agregarParametro("frase", "");
            gestorReportes.agregarParametro("pieMembrete", "");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void imprimirListado() {
        try{
            gestorReportes.imprimir();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
    public void agregarDatosListado(HashSet lista) {
        gestorReportes.setColeccionDeDatos(lista);
    }

    public void agregarDatosListado(Set lista) {
        gestorReportes.setColeccionDeDatos(lista);
    }
  
    public void agregarDatosListado(TreeSet listaDatosOrdenada){
        gestorReportes.setColeccionDeDatos(listaDatosOrdenada);
    }

    public void agregarDatosListado(List listaDatosOrdenada){
        gestorReportes.setColeccionDeDatos(listaDatosOrdenada);
    }

    public void agregarParametroListado(String nombre,Object objeto){
        gestorReportes.agregarParametro(nombre, objeto);
    }

    public void openFormulario(JDesktopPane escritorio) {

    }
 
    public void newModelTable(JTable tbl, String[] titulo, String[] ancho) {
        this.getUtilTable().newModelJS(tbl, titulo);
        //para inmovilizar las columnas
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.getTableHeader().setResizingAllowed(false);
        this.getUtilTable().anchoTabla(tbl,ancho);
       
    }
    
    
    public boolean  isItemTablaSelected(JTable tbl) {
          return !(tbl.getSelectedRow()==-1);
    }

    public void removeItemTabla(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.removeRow(tbl.getSelectedRow());
    }

}
