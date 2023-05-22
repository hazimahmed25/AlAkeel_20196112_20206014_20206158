package smile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("Restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerServ {
	
	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Rest");
    EntityManager em = emFactory.createEntityManager();
	Order order ;
	
	
	@POST
    public void CreateOrder(String RestaurantName, Long customerId, List<Meal> items) {
    	order.setRestaurantName(RestaurantName);
    	order.setCustomerID(customerId);
    	order.addItem(items);
    	
    	String simpleQuery=" SELECT * FROM runners WHERE status = 'available' ";
    	Query query= em.createQuery(simpleQuery);
    	Random random = new Random() ;
    	List<Runner> run =query.getResultList();
    	int index = random.nextInt(run.size());
    	Runner runner = run.get(index);
    	String RunnerName = runner.getName();
    	order.setRunnerName(RunnerName);
    	runner.setState("Busy");
    	em.persist(order);
    }
	
	@GET
	@Path("details")
	public void getDetails(long customerID)
	{
		try{
            order = em.find(Order.class, customerID );
            if (order != null){
              if(customerID == order.getCustomerID()) {
            	System.out.print
              (
              "date is : " + order.getDate() + " " 
            + " restaurant name : " +order.getRestaurantName() + " "
            + " items you ordered is " +  order.getListItems() + " "
            + " runner name is " + order.getRunnerName() + " "
            + " total receipt value is " + order.getTotal()
            );
            } else 
                System.out.print("Not Found");
            }
        }catch(Exception exception){
            // code to handle PersistenceException
        	
        }
	}
	List<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	@GET
	@Path("Restaurants")
	public void listAllRestaurants(){
	    for (Restaurant restaurant : restaurants) {
	        System.out.println(restaurant.getName()); 
	    }       
 }
}
