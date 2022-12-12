package org.generation.italy.main;

import java.util.List;

import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.pojo.Promotion;
import org.generation.italy.main.service.PizzaService;
import org.generation.italy.main.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaRelazioniApplication implements CommandLineRunner {

	@Autowired
	PizzaService pizzaService;
	@Autowired
	PromotionService promotionService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaRelazioniApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		Promotion pr1 = new Promotion("Promozione1", "2022-10-10", "2022-10-12");
		Promotion pr2 = new Promotion("Promozione2", "2022-10-30", "2022-11-12");
		Promotion pr3 = new Promotion("Promozione3", "2020-11-20", "2021-01-20");
		

		promotionService.save(pr1);
		promotionService.save(pr2);
		promotionService.save(pr3);
		
		Pizza p1 = new Pizza("Pizza1", "Margherita", 6, pr2);
		Pizza p2 = new Pizza("Pizza2", "Diavola", 8, pr2);
		Pizza p3 = new Pizza("Pizza3", "Rossa", 5, pr1);
		Pizza p4 = new Pizza("Pizza4", "Capricciosa", 10, null);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		for (Pizza p : pizzas) {
			System.err.println(p);
			
		}
		
		promotionService.delete(promotionService.findById(2));
		
		System.out.println("-------------------");
		
		List<Promotion> promotions = promotionService.findAllWithPizzas();
		
		for (Promotion pr : promotions) {
			System.err.println(pr);
			
			for (Pizza p : pr.getPizzas()) {
				System.err.println(p);
			}
		}
		
		
	}

}