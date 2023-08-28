package Modelos.GestionProyecto;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name="tipo_proyecto") 

public class TipoProyecto implements Comparable, Serializable{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
  
    private int codigo; 

    @Column(columnDefinition = "TEXT")
    private String denominacion;       
   
    @Column(columnDefinition = "TEXT")
    private String observacion;    
    private int estado;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TipoProyecto() {
        
    }

    public TipoProyecto(int codigo, String denominacion) {
        this.codigo=codigo;
        this.denominacion=denominacion;
    }
    
    public void eliminar() {
        this.setEstado(1);
    }
   
    public String getCodigoS() {
        return String.valueOf(this.getCodigo());
    }
    
    
    @Override
    public String toString () {
        return  this.getDenominacion();
    }  
       
    @Override
    public int compareTo(Object o) {
        TipoProyecto p = (TipoProyecto) o;
        return this.getDenominacion().compareTo(p.getDenominacion());
    }

  
    
    
}
