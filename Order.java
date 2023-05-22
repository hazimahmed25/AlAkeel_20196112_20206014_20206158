package smile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Order")
public class Order implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	public long getId() {
		return id;
	}
	
	@ElementCollection
    private List<Meal> items = new ArrayList<>();
	
	public List<Meal> getListItems(){
		return items ;
	}
	
	
    @Column(name = "total_price")
    private int totalPrice;
    
    
    @Column(name = "CustomerID")
    private long customerID;
    
    public long getCustomerID() {
        return customerID;
    }
    public void setCustomerID(long cust) {
         this.customerID = cust ;
    }
    
    
    @Column(name = "orderStatus")
    private String orderStatus;
 

    public String getState() {
        return orderStatus;
    }
    @Column(name="date")
    @Temporal(TemporalType.DATE)
    protected Date registrationDate;
    
    public Date getDate() {
    	return registrationDate;
    }

    public void setState(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    @ManyToOne
    @JoinColumn(name = "runner_id")
    private Runner runner;
    
    public long getRunnerId() {
    	return runner.getId();
    }
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
      public void setRestaurantName(String name) {
    	  restaurant.setName(name);
      }
      public void setRunnerName(String name) {
    	  runner.setName(name);
      }
      public String getRunnerName(){
    	  return runner.getName();
      }
      public String getRestaurantName() {
    	  return restaurant.getName();
      }
      
      public void addItem( List<Meal> item ) {
          items.addAll(item) ;
      }
      public int getTotal(){
    	  for(Meal meal : items) {
    		  int totalPrice =+ meal.getPrice();
    	 }
    	 return totalPrice+ runner.getPrice();
      }
      
      
      
//    public Order(List<String> items, int totalPrice, Runner runner, Restaurant restaurant, String orderStatus) {
//        this.items = items;
//        this.totalPrice = totalPrice;
//        this.runner = runner;
//        this.restaurant = restaurant;
//        this.orderStatus = orderStatus;
//    }
    



	
	
}
