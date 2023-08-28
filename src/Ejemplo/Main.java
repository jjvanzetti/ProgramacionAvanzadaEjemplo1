package Ejemplo;
import Hibernate.HibernateUtil;
import Vistas.GestorVistaPrincipal;

public class Main {

   public static void main(String[] args) {
        HibernateUtil.inicializar();
        GestorVistaPrincipal gestorVista = new GestorVistaPrincipal();
        gestorVista.abrirPrincipal(); 
    }
 
   
     

}
