package Interfaz;

public interface Vistas {
   
    void cargarCombos();

    void clearView();


    void grabFocus();

    void onViewOpened();

    void saveView();

    void setView();

    void viewCamposEnabled(Boolean tipo);

    void viewEliminar();

    void viewGuardar();
    
    void cancelarView();
    
    void deleteView();
    
}
