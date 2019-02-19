package fr.pizzeria.console;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFactory;
import fr.pizzeria.services.MenuService;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		
	
		
		/* Initialisation du scanner */
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		
		IPizzaDao dao = null;
		
		println("***** Pizzeria Stockage *****");
		println("1. Stocker en console ");
		println("2. Stocker en BDD ");
		choix = scanner.nextInt();
		
		try {
			 dao = PizzaDaoFactory.getDao(choix);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		}

		
		/* Structure de répétition de la boucle principale */
		do {
			println("***** Pizzeria Administration *****");
			println("1. Lister les pizzas ");
			println("2. Ajouter une nouvelle pizza ");
			println("3. Mettre à jour une pizza ");
			println("4. Supprimer une pizza ");
			println("99. Sortir ");
	
			System.out.print("Veuillez choisir une option:");
			choix = scanner.nextInt();
			
			MenuService service = MenuServiceFactory.getInstance(choix);
			
			try {
				service.executeUC(scanner, dao);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} while(choix!=99);
		
		try {
			
			dao.closeConnectionBdd();
			
		} catch (Exception e) {
			// TODO: handle exception*
			throw new SQLException();
		}
		
		scanner.close();
		
	}
	
	

	private static void println(String msg){
		System.out.println(msg);
	}
}
