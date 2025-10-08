package LatoRok3.apki_przemyslowe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import LatoRok3.apki_przemyslowe.models.Employee;
import LatoRok3.apki_przemyslowe.service.EmploymentService;
import LatoRok3.apki_przemyslowe.models.Stanowikso;;

@SpringBootApplication
public class AplikacjePrzemysłoweApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplikacjePrzemysłoweApplication.class, args);
	}

	public static EmploymentService serwis = new EmploymentService();

	Employee pracus1 = new Employee("Janusz", "Gasipies", "j.nusz@mail.com", "Twoja Mama inc.", Stanowikso.Programista, 8000.00);
	
}
