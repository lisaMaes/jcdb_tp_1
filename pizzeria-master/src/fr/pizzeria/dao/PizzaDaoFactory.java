package fr.pizzeria.dao;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 * @author Lisa
 * Sert de controller sur la question de la gestion du type de mémoire
 * 
 *
 */
public class PizzaDaoFactory {
	
	public static IPizzaDao getDao(int choice) throws ClassNotFoundException, IOException, SQLException{
		
		IPizzaDao iPizzaDao = null;
		
		switch (choice) {
		case 1:
			
			iPizzaDao = new PizzaMemDao();
			
			break;
		case 2:
			
			iPizzaDao = new PizzaJDBCDao();
			break;
		default:
			
			iPizzaDao = new PizzaMemDao();
			
			break;
		}
		
		return iPizzaDao;
	}
}
