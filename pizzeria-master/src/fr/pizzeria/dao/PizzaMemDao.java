package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {

	private List<Pizza> pizzas = new ArrayList<Pizza>();
	
	public PizzaMemDao(){
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L’orientale", 13.50));
		pizzas.add(new Pizza("IND", "L’indienne", 14.00));
	}

	public void closeConnectionBdd(){
		
	}
	
	//@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	//@Override
	public void saveNewPizza(Pizza pizza) {
		pizzas.add(pizza);

	}

	//@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		for (Pizza p: pizzas){
			if (p.getCode().equals(codePizza)){
				p.setCode(pizza.getCode());
				p.setLibelle(pizza.getLibelle());
				p.setPrix(pizza.getPrix());
				return;
			}
		}

	}

	//@Override
	public void deletePizza(String codePizza) {
		Iterator<Pizza> it = pizzas.iterator();
		while (it.hasNext()){
			Pizza p = it.next();
			if (p.getCode().equals(codePizza)){
				it.remove();
			}
		}
	}

	public Pizza findPizzaByCode(String codePizza){
		
		Pizza pizzaFound = null;
		
		for(Pizza pizza: pizzas){			
			
			if(pizza.getCode().equals(codePizza)){
				pizzaFound = pizza;
			}
		}
		
		
		return pizzaFound;
	}
	
	public boolean pizzaExists(String codePizza){
		
		boolean pizzaFound = false;
		
		for(Pizza pizza: pizzas){
			
			
			if(pizza.getCode().equals(codePizza)){
				pizzaFound = true;
			}
		}
		
		return pizzaFound;
	}

}
