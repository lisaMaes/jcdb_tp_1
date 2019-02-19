package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws SQLException {
		System.out.println("Lister les pizzas");
		try {
			for (Pizza pizza: dao.findAllPizzas()){
				System.out.println(pizza);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		}

	}

}
