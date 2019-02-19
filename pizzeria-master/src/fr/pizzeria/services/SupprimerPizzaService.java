package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws SQLException {
		System.out.println("Supprimer une pizza");
		
		System.out.println("Code de la pizza à supprimer:");
		String codePizzaSupp = scanner.next();
		
		try {
			dao.deletePizza(codePizzaSupp);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		}

	}

}
