package Modelos.GestionProyecto;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table (name="proyecto") 

public class Proyecto implements Comparable{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    
    private int codigo; 
      
    @Column(columnDefinition = "TEXT")
    private String denominacion;       
 
    private String observacion;    
    private int estado;

    @OneToOne (targetEntity = TipoProyecto.class, cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private TipoProyecto tipoProyecto;

    @OneToMany(targetEntity = ItemProyecto.class, cascade= CascadeType.ALL, fetch=FetchType.LAZY )
    private Set<ItemProyecto> items = new HashSet();

    public Set<ItemProyecto> getItems() {
        return items;
    }

    public void setItems(Set<ItemProyecto> items) {
        this.items = items;
    }

    public TipoProyecto getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }
      
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
    
    public Proyecto() {
    }

    public Proyecto(int codigo, String denominacion) {
        this.setCodigo(codigo);
        this.setDenominacion(denominacion);
    }
    
    public void eliminar() {
        this.setEstado(1);
    }
    
    @Override
    public String toString () {
        return  this.getDenominacion();
    }  
    
 
       
    @Override
    public int compareTo(Object o) {
        Proyecto p = (Proyecto) o;
        return this.getDenominacion().compareTo(p.getDenominacion());
    }

    public String getCodigoS() {
        return this.getCodigo()+"";
    }
    
    
}
