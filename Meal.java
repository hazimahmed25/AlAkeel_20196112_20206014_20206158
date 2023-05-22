package smile;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "Meal")
public class Meal{

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	 @Column(name = "name")
		private String name;
	 
	 @Column(name = "price") 
		private int price ;
	 
	 @ManyToOne
	    @JoinColumn(name = "restaurant_id")
	    private Restaurant restaurant;
	 
	 public Long getId() {
	        return id;
	 }
	 
	 public void setId(Long id) {
	        this.id = id;
	 }
	 public void setName(String name) {
	        this.name = name;
	 }
	 public String getName() {
	        return name;
	 }
	 public void setPrice(int price) {
	        this.price = price;
	 }
	 public int getPrice() {
	        return price ;
	 }
	 
}
