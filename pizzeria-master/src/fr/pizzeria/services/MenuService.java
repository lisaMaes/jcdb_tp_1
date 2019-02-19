package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;

public abstract class MenuService {

	public abstract void executeUC(Scanner scanner, IPizzaDao dao) throws SQLException;
}
