package smile;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("UserService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class userService {

	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("User");
    EntityManager em = emFactory.createEntityManager();
    
    @POST
    @Path("SigniIn")
    public void SignIn(String Name ,long id , String role) {
    	User user = new User() ;
    	user.setId(id);
    	user.setName(Name);
    	user.setRole(role);
    	em.persist(user); 
    }
    @Path("SignInRunner")
    public void SignUpAsRunner(String Name, long id , int deliveryFee) {
        User user = new User();
        user.setRole("Runner");
        Runner runner = new Runner();
        runner.setName(Name);
        runner.setFees(deliveryFee);
        em.persist(runner);
    }
    @Path("login")
    public User logIn(String name ,long id , String role)
    {
    	TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.getName() = name AND u.getRole()= role"
    			+ "e.getId()=id ", User.class);
    	q.setParameter("id", id);
        q.setParameter("name",name);
        List<User> users = q.getResultList();
        if (!users.isEmpty())
        {
            return users.get(0);
        }
        else {
        	return null;
        }   
    }
    @Path("login")
    public Runner logInRunner(String name ,long id , String role)
    {
    	TypedQuery<Runner> q = em.createQuery("SELECT u FROM User u WHERE u.getName() = name AND u.getRole()= 'runner'"
    			+ "e.getId()=id ", Runner.class);
    	q.setParameter("id", id);
        q.setParameter("name",name);
        q.setParameter("role", "runner");
        List<Runner> run = q.getResultList();
        if (!run.isEmpty())
        {
            return run.get(0);
        }
        else {
        	return null;
        }   
    }
}
