package services;

import java.util.List;

import data.Lego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/lego")
public class LegoService {

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	// test
	@Path("/getlego")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getLego() {
		return "<h1>Lego service!!!</h1>";
	}

	@Path("/setvalues")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// test
	// public String ping() {
	// return "AJAX-RS is alive!";
	// }
	public Lego setValues(Lego lego) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("legoapp");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(lego);
		em.getTransaction().commit();
		return lego;
	}

	@Path("/getvalues")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getValues() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("legoapp");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Lego> q = em.createQuery("select s from Lego s order by s.id desc",
				Lego.class);
		q.setMaxResults(1);
		List<Lego> list = q.getResultList();
		em.getTransaction().commit();
		Lego lego = list.get(0);
		return lego.getId() + "#" + lego.getRun() + "#" + lego.getSpeed() + "#" + lego.getTurn();
	}

	// @GET
	// @Path("/getdog")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Dog getDog() {
	// Dog d = new Dog(1, "Laiska", 28);
	// return d;
	// }

	// @GET
	// @Path("/getonedog/{par}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Dog getOneDog(@PathParam("par") int id) {
	// ArrayList<Dog> list = getDogList();
	// return list.get(id);
	// }

	// @GET
	// @Path("/getdogsbyweight/{p1}/{p2}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public ArrayList<Dog> getDogsByName(@PathParam("p1") float low,
	// @PathParam("p2") float high) {
	// ArrayList<Dog> list = getDogList();
	// ArrayList<Dog> result = new ArrayList<>();
	// for (Dog d : list) {
	// if (d.getWeight() >= low && d.getWeight() <= high) {
	// result.add(d);
	// }
	// }
	// return result;
	// }

	// @GET
	// @Path("/readall")
	// public void readAll() {
	// RequestDispatcher rd = request.getRequestDispatcher("/jsp/printdogs.jsp");
	// request.setAttribute("dogs", getDogList());
	// try {
	// rd.forward(request, response);
	// } catch (ServletException | IOException e) {

	// e.printStackTrace();
	// }
	// }

	// @GET
	// @Path("/adddogbyget")
	// @Produces(MediaType.APPLICATION_JSON)
	// public ArrayList<Dog> addDogByGet(@QueryParam("id") int id,
	// @QueryParam("breed") String breed,
	// @QueryParam("weight") float weight) {
	// Dog d = new Dog(id, breed, weight);
	// ArrayList<Dog> list = getDogList();
	// list.add(d);
	// return list;
	// }

	// @POST
	// @Path("/adddogbypost")
	// @Consumes("application/x-www-form-urlencoded")
	// public void addDogByPost(@FormParam("id") int id, @FormParam("breed") String
	// breed,
	// @FormParam("weight") float weight) {
	// Dog d = new Dog(id, breed, weight);
	// ArrayList<Dog> list = getDogList();
	// list.add(d);
	// RequestDispatcher rd = request.getRequestDispatcher("/jsp/printdogs.jsp");
	// request.setAttribute("dogs", list);
	// try {
	// rd.forward(request, response);
	// } catch (ServletException | IOException e) {

	// e.printStackTrace();
	// }
	// }

	// public ArrayList<Dog> getDogList() {
	// ArrayList<Dog> list = new ArrayList<>();
	// list.add(new Dog(1, "East Sibrian Laika", 30));
	// list.add(new Dog(2, "West Sibrian Laika", 25));
	// list.add(new Dog(3, "White Shephard", 40));
	// list.add(new Dog(4, "Husky", 20));
	// list.add(new Dog(5, "Chiuahua", 1));
	// return list;
	// }

}
