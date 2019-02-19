package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws SQLException {
		System.out.println("Mettre à jour une pizza");
		
		
		System.out.println("Code de la pizza à modifier:");
		String codePizza = scanner.next();
		
		System.out.println("Nouveau code :");
		String nvCode = scanner.next();
		
		System.out.println("Nouveau libellé :");
		String nvLibelle = scanner.next();
		
		System.out.println("Nouveau prix :");
		double nvPrix = scanner.nextDouble();
		
		try {
			dao.updatePizza(codePizza, new Pizza(nvCode, nvLibelle, nvPrix));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		}

	}

}
