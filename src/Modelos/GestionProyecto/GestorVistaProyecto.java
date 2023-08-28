package Modelos.GestionProyecto;
import Util.UtilJtable;
import javax.swing.*;
import Vistas.GestorVista;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class GestorVistaProyecto  extends GestorVista {
    private FrmProyecto form;                
    private Proyecto  model;
    private GestorVistaTipoProyecto gestorTipoProyecto = new GestorVistaTipoProyecto();
   
    public Proyecto  getModel() {
        return model;
    }

    public void setModel(Proyecto  motivoCuota) {
        this.model = motivoCuota;
    }

    public GestorVistaTipoProyecto getGestorTipoProyecto() {
        return gestorTipoProyecto;
    }

    public void setGestorTipoProyecto(GestorVistaTipoProyecto gestorTipo) {
        this.gestorTipoProyecto = gestorTipo;
    }
 
    public void setModel(JComboBox cmb) {
        this.setModel((Proyecto) cmb.getSelectedItem());
    }


    public FrmProyecto getForm() {
        return form;
    }

    public void setForm(FrmProyecto form) {
        this.form = form;
    }
  
     public void initializeTabla(JTable tbl) {
        String[] titulo={"","Denominación"};
        String[] ancho ={"5","200"};
        this.newModelTable(tbl,titulo,ancho);  
    } 
    
    
    @Override
    public void newModel() {
        this.setModel(new Proyecto());
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
             this.getModel().setTipoProyecto((TipoProyecto) this.getForm().getCmbTipoProyecto().getSelectedItem());
             this.getModel().setItems(UtilJtable.convertToSet(this.getForm().getTbl()));
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
    
        if (this.isEmpty(this.getForm().getTbl())) {
           JOptionPane.showMessageDialog(null, "Falta ingresar el item de Proyecto");
            this.getForm().getCmbTipoProyecto().grabFocus();
            return false; 
        } 
    
        if (this.isEmpty(this.getForm().getCmbTipoProyecto())) {
           JOptionPane.showMessageDialog(null, "Falta ingresar la tipo de Proyecto");
            this.getForm().getCmbTipoProyecto().grabFocus();
            return false; 
        } 
        return true;
    }

    @Override
    public boolean getModelXCodigo(String codigo) {
        Proyecto auxModel;
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
        this.setForm(new FrmProyecto(this));
        this.setTitulo(this.getForm().getTitle());
        this.getEscritorio().add(this.getForm());
        this.getForm().setVisible(true);
    }
      
    @Override
    public void getView() {
        this.getForm().getTxtCodigo().setText(this.getModel().getCodigoS());
        this.getForm().getTxtDenominacion().setText(this.getModel().getDenominacion());
        this.getForm().getCmbTipoProyecto().setSelectedItem(this.getModel().getTipoProyecto());
        this.setItems(this.getForm().getTbl());
    }
    
    @Override
    public void actualizarView() {
        this.getForm().viewGuardar();
        if (this.getOpcABM()==0) {
            this.getForm().getTxtCodigo().setText(this.getModel().getCodigoS());
        }
    } 

    public void setModelTipoProyecto(JComboBox cmb) {
        cmb.setModel(getComboModelTipoProyecto());
   }
   
    private void setItems(JTable tbl) {
        this.initializeTabla(tbl);
        DefaultTableModel modeloTabla= (DefaultTableModel) tbl.getModel();
        for (ItemProyecto item : this.getModel().getItems()) {
             Object[] fila = {item, item.getDenominacion()}; 
             modeloTabla.addRow(fila);
        }
    }
    
    public void addItem() {
        if (this.isItemValidos()) {
            DefaultTableModel modeloTabla  = (DefaultTableModel) this.getForm().getTbl().getModel(); 
            ItemProyecto item  = new  ItemProyecto(this.getForm().getTxtItemDenominacion().getText());  
          
            Object[] fila = {item, item.getDenominacion() }; 
            modeloTabla.addRow(fila); 
            this.getForm().clearItemView();
        }    
    }

    private boolean isItemValidos() {
        return true;
    }

    public void removeItem() {
       if (this.isItemTablaSelected(this.getForm().getTbl())){
           this.removeItemTabla(this.getForm().getTbl());
        }
    }

   public void imprimir() {
        this.abrirListado(this.pathReportes+"ListadoTipoDominio.jasper");
        this.agregarParametroListado("titulo", "Proyecto");
        this.agregarParametroListado("usuario", "Yo");
        this.agregarDatosListado(this.listar());
        this.imprimirListado();
    }

    public  void openFormularioTipo(DefaultComboBoxModel model) {
        GestorVistaTipoProyecto gestorVistaTipoGasto =new GestorVistaTipoProyecto();
        gestorVistaTipoGasto.openFormulario(model,this.getEscritorio());
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
    
    public List <Proyecto> listar(){   
        return this.listarClase(Proyecto.class,"codigo",0);
    }
        
    public List <Proyecto> listar(TipoProyecto tipo){   
        Criteria crit = getSession().createCriteria(Proyecto.class)
            .add (Restrictions.eq("tipoProyecto",tipo))  
            .add (Restrictions.eq("estado",0));
        return crit.list();
    }
    
    public DefaultComboBoxModel getComboModel() {      
        DefaultComboBoxModel auxModel= new DefaultComboBoxModel();
        auxModel.addElement("");
        for (Proyecto auxTipo : this.listar()) {
            auxModel.addElement(auxTipo);
        }
        return auxModel;
    }  
    
    public DefaultComboBoxModel getComboModel(TipoProyecto tipo) {      
        DefaultComboBoxModel auxModel= new DefaultComboBoxModel();
        auxModel.addElement("");
        for (Proyecto auxTipo : this.listar()) {
            auxModel.addElement(auxTipo); 
        }
         return auxModel;
    }
    
     public int getUltimoCodigo(){
          try {
           Proyecto auxModel= (Proyecto) this.listarUltimo(Proyecto.class).get(0);
           return auxModel.getCodigo();
        }
        catch(Exception e){
           return 0;
        }

    }

    public Proyecto buscarCodigo(String codigo) {
        return this.buscarCodigo(Integer.valueOf(codigo));
    }
   
    public Proyecto buscarCodigo(int codigo) {
        Proyecto  auxModel=null;
        try {
          auxModel= (Proyecto) this.listarClaseCodigo(Proyecto.class, codigo).get(0);
          return auxModel;
        }
        catch(Exception e){
          return auxModel;
       }
    }

    public DefaultComboBoxModel getComboModelTipoProyecto() {
       return this.getGestorTipoProyecto().getComboModel();
    }


}
