package smile;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("Restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantOwner {
	
	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Rest");
    EntityManager em = emFactory.createEntityManager();
	
	@POST
	@Path("Menu")
	 public void CreateMenu(Restaurant rest) {
		em.persist(rest);
	  }
	
	@GET
	@Path("{id}")
	public Restaurant getDetails(@PathParam("id")Long id) {
		return em.find(Restaurant.class,id);
	}
//	private Set<Meal> Meal ;
	
	String s ="SELECT Meal FROM Resaturant m WHERE m.restaurant.id = restaurantId" ;
	@GET
	public void getMeal(Long restaurantId) {
		
    	Restaurant rest = em.find(Restaurant.class, restaurantId);
    	Query query= em.createQuery(s);
    	List<Meal> meal = query.getResultList();
    	List<Meal> old =  meal;
    	}
    	
    	
    	
    	
	@PUT
	public void addMenuItem(Long restaurantId, String name, int price) {
    Restaurant restaurant = em.find(Restaurant.class, restaurantId);
    Meal meal = new Meal();
    meal.setName(name);
    meal.setPrice(price);
    restaurant.getMenu().add(meal);
    em.merge(restaurant.getMenu());
	}
	
	private int deliveredLen = 0 ;
	private int CanceledLen = 0 ;

	@Path("{id}")
	public void getReport(@PathParam("id")Long id) {
		Restaurant rest = new Restaurant() ;
		rest = em.find(Restaurant.class,id);
	    Set<Order> orders = rest.getOrders();
	  for(Order order : orders) {
		  if (order.getState() == "delivered" ) {  
			  deliveredLen++;
		  }
		  else {
			  CanceledLen++;
		  }
	  }
		System.out.println("number of total orders is  : " + rest.getLength());
		System.out.println("number of completed order is  : " + deliveredLen);
		System.out.println("number of completed order is  : " + CanceledLen);
	}
}
