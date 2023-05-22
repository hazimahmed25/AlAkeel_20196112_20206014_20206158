package smile;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Restaurant")
public class Restaurant implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
	private String name;
 
    @Column(name = "OwnerId")
    private long OwnerId;
    
    @OneToMany(mappedBy="Restaurant", fetch = FetchType.EAGER)
	private Set<Order> Order ;
    
    @OneToMany(mappedBy="Restaurant",fetch = FetchType.EAGER)
	private Set<Meal> Meal ;
    
    public void setMenu(Set<Meal> meal) {
    	this.Meal = meal;
    }
    public Set<Meal> getMenu() {
    	return Meal ;  
    }
    
    public Set<Order> getOrders() {
        return Order;
        
    }    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private int lengthOrder = Order.size();
    
    public int getLength() {
        return lengthOrder;
    }
//    private int deliveredLen = 0 ;
//    for(Order order : Order) {
//    if(	order.getState() == "delivered")
//    	deliveredLen ++ ;
//    }
    
      
}