package Modelos.GestionProyecto;
import javax.persistence.*;

@Entity
@Table (name="item_proyecto") 

public class ItemProyecto implements Comparable{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
 
    @Column(columnDefinition = "TEXT")
    private String denominacion;       

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    ///////////////////////////////////////////
    public ItemProyecto() {
    }
 
    public ItemProyecto(String text) {
        this.setDenominacion(text);
    }

    @Override
    public String toString () {
        return  this.getDenominacion();
    }  
    
       
    @Override
    public int compareTo(Object o) {
        ItemProyecto p = (ItemProyecto) o;
        return this.getDenominacion().compareTo(p.getDenominacion());
    }
    
    
}
