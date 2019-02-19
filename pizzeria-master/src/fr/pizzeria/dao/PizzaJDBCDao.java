package fr.pizzeria.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.pizzeria.model.Pizza;

public class PizzaJDBCDao implements IPizzaDao{
	
	private List<Pizza> pizzas = new ArrayList<Pizza>();
	static Connection myConnection;
	
	public PizzaJDBCDao() throws IOException, ClassNotFoundException, SQLException{
		
		connectionToBdd();
	}
	
	public void connectionToBdd() throws IOException, SQLException, ClassNotFoundException{

		final Properties prop = new Properties();
		InputStream input = null;

		input = new FileInputStream("pizzeria-master/src/jdbc.properties");

		// load a properties file
		prop.load(input);

	
		Class.forName("com.mysql.jdbc.Driver");
		myConnection = DriverManager
					.getConnection(prop.getProperty("db.url"),prop.getProperty("db.username"),prop.getProperty("db.password"));
		
	}
	
	public void closeConnectionBdd() throws SQLException {
		myConnection.close();
	}
	
	public ResultSet initializeBDD() throws SQLException{
		 List<Pizza> pizzas = new ArrayList<Pizza>();
		
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L’orientale", 13.50));
		pizzas.add(new Pizza("IND", "L’indienne", 14.00));
		
		for(Pizza p : pizzas){
			
			this.saveNewPizza(p);
		}
		
		PreparedStatement selectAll= myConnection.prepareStatement("SELECT * FROM pizza");
		ResultSet results = selectAll.executeQuery();
		return results;
	}
	
	public List<Pizza> findAllPizzas() throws SQLException {
		// TODO Auto-generated method stub
		 List<Pizza> pizzas = new ArrayList<Pizza>();
		PreparedStatement selectAll= myConnection.prepareStatement("SELECT * FROM pizza");
		ResultSet results = selectAll.executeQuery();
		if(!results.next()){
			results = this.initializeBDD();
		}
		while (results.next()){
			Integer id = results.getInt("ID");
			String code = results.getString("code");
			String libelle = results.getString("libelle");
			double price = results.getDouble("prix");
			
			pizzas.add(new Pizza(id,code,libelle,price));
			
		}
		return pizzas;
	}

	public void saveNewPizza(Pizza pizza) throws SQLException {
		// TODO Auto-generated method stub
	
		PreparedStatement addPizza= myConnection.prepareStatement("INSERT INTO pizza VALUES(?,?,?,?1)");
		addPizza.setInt(1, pizza.getId());
		addPizza.setString(2,pizza.getCode());
		addPizza.setString(3,pizza.getLibelle());
		addPizza.setDouble(4,pizza.getPrix());
		int results = addPizza.executeUpdate();
		
		
		
	}

	public void updatePizza(String codePizza, Pizza pizza) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement updatePizza= myConnection.prepareStatement("UPDATE pizza SET code = ?, libelle = ?, prix = ? WHERE code = ? ");
		updatePizza.setString(1, pizza.getCode());
		updatePizza.setString(4,codePizza);
		updatePizza.setString(2,pizza.getLibelle());
		updatePizza.setDouble(3,pizza.getPrix());
		int results = updatePizza.executeUpdate();
	}

	public void deletePizza(String codePizza) throws SQLException {
		// TODO Auto-generated method stub
				
		PreparedStatement deletePizza= myConnection.prepareStatement("DELETE FROM pizza WHERE code = ? ");
		deletePizza.setString(1, codePizza);
		int results = deletePizza.executeUpdate();
	}

	public Pizza findPizzaByCode(String codePizza) throws SQLException {
		// TODO Auto-generated method stub
		
		Pizza pizza = null;
		
		PreparedStatement findByCode= myConnection.prepareStatement("SELECT * FROM pizza WHERE code = ?");
		findByCode.setString(1, codePizza);
		ResultSet results = findByCode.executeQuery();
		
		while (results.next()){
			Integer id = results.getInt("ID");
			String code = results.getString("code");
			String libelle = results.getString("libelle");
			double price = results.getDouble("prix");
			
			pizza = (new Pizza(id,code,libelle,price));
			return pizza;
		}
		
		return pizza;
	
	}

	public boolean pizzaExists(String codePizza) throws SQLException {
		// TODO Auto-generated method stub
		boolean exists = false;
		
		PreparedStatement findByCode= myConnection.prepareStatement("SELECT * FROM pizza WHERE code = ?");
		findByCode.setString(1, codePizza);
		ResultSet results = findByCode.executeQuery();

		if(results.next()){
			
			exists = true;
		}
		
		return exists;
	}
}
