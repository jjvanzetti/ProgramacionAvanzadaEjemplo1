package Modelos.GestionProyecto;
import static Hibernate.HibernateUtil.getSession;
import javax.swing.*;
import Vistas.GestorVista;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class GestorVistaTipoProyecto2  extends GestorVista{
    private FrmTipoProyecto2 form;  
    private TipoProyecto  model;

    public TipoProyecto  getModel() {
        return model;
    }
    public void setModel(JComboBox cmb) {
        this.setModel((TipoProyecto) cmb.getSelectedItem());
    }

    public void setModel(TipoProyecto  motivoCuota) {
        this.model = motivoCuota;
    }

    public FrmTipoProyecto2 getForm() {
        return form;
    }

    public void setForm(FrmTipoProyecto2 form) {
        this.form = form;
    }
  
    @Override
    public void newModel() {
        this.setModel(new TipoProyecto());
        this.setModoNuevo();
    } 

    @Override
    public void cancelarView() {
        
    }

    @Override
    public void deleteView() {
        
         this.eliminar();
         this.getForm().viewEliminar();
    }
    
    @Override
    public int setModel() { 
        if (this.isDatosValidos()) {
            this.getModel().setDenominacion(this.getForm().getTxtDenominacion().getText());            
            this.getModel().setObservacion(this.getForm().getTxtObservaciones().getText());            
            
            return 0;
        }
        else {
            return 1;
        } 
    }
    
    @Override
    public void saveView() {
        int err;
        err= this.setModel();
        if (err ==0){ 
           this.saveModel(this.getOpcABM());
           this.actualizarView();
        }
    }
    
    @Override
    public boolean isDatosValidos() {
        if (this.isEmpty(this.getForm().getTxtDenominacion())) {
            JOptionPane.showMessageDialog(null, "Falta ingresar la descripción del Proyecto");
            this.getForm().getTxtDenominacion().grabFocus();
            return false;
        } 
        return true;
    }

    @Override
    public boolean getModelXCodigo(String codigo) {
        TipoProyecto auxModel;
        auxModel=this.buscarCodigo(codigo);
        if (auxModel!=null) {
           this.setModel(auxModel);
           return true;  
        }
        else {
            return false;
        }
    }

    public void setModelProyecto(JComboBox cmb) {
        cmb.setModel(getComboModel());
    }
 
    @Override
    public void openFormulario(JDesktopPane pantalla) {
        this.setEscritorio(pantalla);
        this.setForm(new FrmTipoProyecto2(this));
        this.setTitulo(this.getForm().getTitle());
        this.getEscritorio().add(this.getForm());
        this.getForm().setVisible(true);
    }
     
    public void openFormulario(DefaultComboBoxModel model, JDesktopPane pantalla) {
        this.setEscritorio(pantalla);
        this.setModelCombo(model);
        this.setOpcABM(2);
        this.setForm(new FrmTipoProyecto2(this));
        this.setTitulo(this.getForm().getTitle());
        this.getEscritorio().add(this.getForm());
        this.getForm().setVisible(true);
        this.setOpcABM(2);
    }
        
    @Override
    public void getView() {
        this.getForm().getTxtCodigo().setText(this.getModel().getCodigoS());
        this.getForm().getTxtDenominacion().setText(this.getModel().getDenominacion());
        this.getForm().getTxtObservaciones().setText(this.getModel().getObservacion());
    }
    
    @Override
    public void actualizarView() {
        this.getForm().viewGuardar();
        if (this.getOpcABM()==0) {
            this.getForm().getTxtCodigo().setText(this.getModel().getCodigoS());
        }
    
        if (this.getOpcABM()==2) {
            this.getModelCombo().addElement(this.getModel());
            this.getModelCombo().setSelectedItem(this.getModel());
            this.getForm().dispose(); 
        }
    } 

    public void imprimir() {

        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\1.jasper");
            this.abrirListado("C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports\\1.jasper");
            this.agregarParametroListado("titulo", "Proyecto");
//         this.agregarParametroListado("usuario", "Yo");
           this.agregarDatosListado(this.listar());
           this.imprimirListado();
        } 
        catch (JRException ex) {
            Logger.getLogger(GestorVistaTipoProyecto2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

    private void newCodigo() {
        this.getModel().setCodigo(this.getUltimoCodigo()+1);
    }
     
    public void saveModel(int opcABM) {
        switch (opcABM){
            case 0:
                this.guardarObjeto();
                break;
              
            case 1:
                this.actualizarObjeto();
                break;   
              
            case 2:
                this.guardarObjeto();
                break;
        
        }
    }
    
    public void guardarObjeto(){
        this.newCodigo();
        this.guardarObjeto(this.getModel());
    } 
   
    public void actualizarObjeto() {
        this.actualizarObjeto(this.getModel());
    }
  
    public void eliminar() {
        this.getModel().eliminar();
        this.actualizarObjeto();
    }
    
    public List <TipoProyecto> listar(){   
        return this.listarClase(TipoProyecto.class,"codigo",0);
    }
       
    public DefaultComboBoxModel getComboModel() {      
        DefaultComboBoxModel auxModel= new DefaultComboBoxModel();
        auxModel.addElement("");
        for (TipoProyecto auxTipo : this.listar()) {
            auxModel.addElement(auxTipo);
        }
         return auxModel;
    }
    
     public int getUltimoCodigo(){
          try {
           TipoProyecto auxModel= (TipoProyecto) this.listarUltimo(TipoProyecto.class).get(0);
           return auxModel.getCodigo();
        }
        catch(Exception e){
           return 0;
        }

    }

    public TipoProyecto buscarCodigo(String codigo) {
        return this.buscarCodigo(Integer.valueOf(codigo));
    }
   
    public TipoProyecto buscarCodigo(int codigo) {
        TipoProyecto  auxModel=null;
        try {
          auxModel= (TipoProyecto) this.listarClaseCodigo(TipoProyecto.class, codigo).get(0);
          return auxModel;
        }
        catch(Exception e){
          return auxModel;
        }
    }

   public void initializeTablaBusqueda(JTable tbl) {
        String[] titulo={"","Cód.","Denominación"};
        String[] ancho ={"5","25","380"};
        this.newModelTable(tbl,titulo,ancho);
    }
  
    public void setBusqueda() {
        Boolean error=false;
        this.initializeTablaBusqueda(this.getForm().getTblDatos());

        if (!error) {
            this.getForm().getTblDatos().setModel(this.listarDatos((DefaultTableModel )this.getForm().getTblDatos().getModel(),this.getOrdenamiento(),this.getForm().getTxtBusquedaDenominacion().getText()));
        }
        else{
            JOptionPane.showMessageDialog(null, "Falta ingresar datos para la búsqueda","Validación de Datos",JOptionPane.WARNING_MESSAGE);
        } 
    }
  
    private int getOrdenamiento() {
        int ord=0;

        return ord;
    }

     public Object getItemTablaSelected(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        return model.getValueAt(tbl.getSelectedRow(),0);
    }
     
    public void setDatos() {
        if(this.getOpcABM()==1){
            int resp = JOptionPane.showConfirmDialog(null, "Usted va a perder los cambios realizados en el producto, porque no ha grabado.\nDesea continuar?","Modificar Producto",JOptionPane.YES_NO_OPTION);
            if(resp==JOptionPane.YES_OPTION){
                this.setOpcABM(-1);
                if (this.isItemTablaSelected(this.getForm().getTblDatos())) {
                    this.setModel((TipoProyecto)this.getItemTablaSelected(this.getForm().getTblDatos()));  
                    this.getForm().clearView();
                    this.getForm().viewActualizar(); 
                }
            }
        }
        else{
            if (this.isItemTablaSelected(this.getForm().getTblDatos())) {
                this.setModel(( TipoProyecto)this.getItemTablaSelected(this.getForm().getTblDatos()));  
                this.getForm().clearView();
                this.getForm().viewActualizar();
            }     
        }
    }

    
    public DefaultTableModel listarDatos(DefaultTableModel auxModelTabla, int ordenamiento, String text) {
        TreeSet<TipoProyecto> listaO= new TreeSet();
        List<TipoProyecto> list= this.listar(text,ordenamiento);
        TipoProyecto  auxModel;
        Iterator it = (Iterator) list.iterator();
        while (it.hasNext())  {
            auxModel =(TipoProyecto) it.next(); 
            listaO.add(auxModel);
         }
       
        Iterator it2 = (Iterator) listaO.iterator();
        while (it2.hasNext())  {
            auxModel =( TipoProyecto ) it2.next();
            Object[] fila = {auxModel,auxModel.getCodigo(),auxModel.getDenominacion()}; 
            auxModelTabla.addRow(fila); 
        }
        return auxModelTabla;
    }
 
    
    
    public List<TipoProyecto> listar(String text,int ord) {
        Criteria crit = getSession().createCriteria(TipoProyecto.class)
             .add( Restrictions.eq("estado", 0));
             crit.add( Restrictions.like("denominacion",'%'+ text.toUpperCase()+'%'));
        return crit.list();
    }  

}