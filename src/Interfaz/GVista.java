package Interfaz;

import javax.swing.JDesktopPane;
public interface GVista {
   
    void cancelarView();

    void deleteView();

    void getView();

    boolean isDatosValidos();

    void newModel();

    void openFormulario(JDesktopPane escritorio);

    void saveView();
    
    void actualizarView();

    boolean getModelXCodigo(String codigo);

    int setModel();

}
