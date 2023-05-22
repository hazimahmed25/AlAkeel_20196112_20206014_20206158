package smile;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import smile.Runner; 

@Stateless
@Path("Runner")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RunnerService {

	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Rest");
    EntityManager em = emFactory.createEntityManager();
    
    @PUT
    public void mark(long id) {
    Order order =em.find(Order.class, id);
    order.setState("delievered");
    Runner runner=em.find(Runner.class, order.getRunnerId()); 
    runner.setState("Availabe");
    em.merge(order);
    em.merge(runner); 
    }
    
    String q =
    		"SELECT * FROM Order  WHERE o.runner"
    		+ " = runner AND o.status = 'completed'"; 
    @GET
    public void NumOfTrips(long id){
    	Runner run = em.find(Runner.class, id);
    	Query query= em.createQuery(q);
    	List<Order> Completeorder =query.getResultList();
    	int completeTrip = Completeorder.size();
    	System.out.println("number of competeted trip is :  " + completeTrip);
    	    	
    }
	
}
