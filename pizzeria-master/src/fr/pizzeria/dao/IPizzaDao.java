package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	List<Pizza> findAllPizzas() throws SQLException ;
	void saveNewPizza(Pizza pizza)throws SQLException ;
	void updatePizza(String codePizza, Pizza pizza) throws SQLException ;
	void deletePizza(String codePizza) throws SQLException ;
	Pizza findPizzaByCode(String codePizza) throws SQLException ;
	boolean pizzaExists(String codePizza) throws SQLException ;
	void closeConnectionBdd() throws SQLException;
}

