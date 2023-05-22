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
@Table(name = "Runner")
public class Runner implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 @Column(name = "name")
		private String name;
	 
	 @Column(name = "delivery fees")
	    private int fees;
	 
	 @Column(name = "status")
	    private String state;
	 
	 
	 public String getState() {
	        return state;
	   }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public Long getId() {
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
	    public void setFees(int fees) {
	        this.fees = fees;
	 }
	 public int getPrice() {
	        return fees ;
	 }
	 @OneToMany(mappedBy="Runner", fetch = FetchType.EAGER)
		private Set<Order> Order ;
}
