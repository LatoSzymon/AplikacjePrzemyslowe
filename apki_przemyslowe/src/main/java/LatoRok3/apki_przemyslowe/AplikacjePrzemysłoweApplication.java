package LatoRok3.apki_przemyslowe;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import LatoRok3.apki_przemyslowe.models.Employee;
import LatoRok3.apki_przemyslowe.service.EmploymentService;
import LatoRok3.apki_przemyslowe.models.Stanowikso;

@SpringBootApplication
public class AplikacjePrzemysłoweApplication {

	public static EmploymentService serwis = new EmploymentService();

	public static void initializeEmp() {
		Employee pracus1 = new Employee("Janusz", "Gasipies", "j.nusz@mail.com", "Twoja Mama inc.", Stanowikso.Programista, 8000.00);
		Employee pracus2 = new Employee("Tytus", "Bomba", "kapitan.b@gmail.com", "Twoja Mama inc.", Stanowikso.Prezes, 21370.0);
		Employee pracus3 = new Employee("Tonald", "Dusk", "fur.dojczland@schmetterling.com", "Mejm.pl", Stanowikso.Stażysta, 0.0);
		Employee pracus4 = new Employee("Daniel", "Ilf", "d.ilf@mail.com", "Twoja Mama inc.", Stanowikso.Wiceprezes, 12000.0);
		Employee impostor = new Employee("Ignacy Maksymilian", "Postor", "j.nusz@mail.com", "Twoja Mama inc.", Stanowikso.Programista, 4.0);

		serwis.addEmployee(pracus1);
		serwis.addEmployee(pracus2);
		serwis.addEmployee(pracus3);
		serwis.addEmployee(pracus4);
		serwis.addEmployee(impostor);
	}

	public static void main(String[] args) {
		SpringApplication.run(AplikacjePrzemysłoweApplication.class, args);

		initializeEmp();

		System.out.println("Wszyscy pracownicy:");
		List<Employee> wszyscy = serwis.wszyscyPracownicy();
		wszyscy.forEach(e -> System.out.println(e));

		String firma = "Twoja Mama inc.";
		System.out.println("Pracownicy w firmie: " + firma);
		List<Employee> zFirmy = serwis.pracownicyWFirmie(firma);
		zFirmy.forEach(e -> System.out.println(e));

		System.out.println("Niewolnicy według nazwiska:");
		List<Employee> posortowani = serwis.pracownicyPosortowaniWedlugNazwiska();
		posortowani.forEach(e -> System.out.println(e));

		System.out.println("Po stanowisku:");
		Map<Stanowikso, List<Employee>> grupy = serwis.grupujPoStanowisku();
		grupy.forEach((stan, lista) -> {
			System.out.println(stan + " ma" + lista.size() + " osób");
			lista.forEach(emp -> System.out.println(emp));
		});

		System.out.println("Zliczanie po stanowisku:");
		Map<Stanowikso, Long> zliczenia = serwis.zliczPoStanowisku();
		zliczenia.forEach((stan, cnt) -> {
			System.out.println(stan);
			System.out.println(cnt);
		});

		OptionalDouble avg = serwis.sredniaPlacy();
		if (avg.isPresent()) {
			System.out.println("Średnia płaca: " + avg.getAsDouble());
		} else {
			System.out.println("Średniej płacy nie ma, wszyscy robią za darmo (wpiszą sobie do cv)");
		}

		Optional<Employee> najw = serwis.pracownikZNajwyzszaPlaca();
		if (najw.isPresent()) {
			System.out.println("Pracownik z najwyższą płacą: " + najw.get());
		} else {
			System.out.println("Pracownik z najwyższą płacą NIE ISTNIEJE");
		}

		Employee nowy = new Employee("Nowy", "Pracownik", "nowy@firma.com", "SomeCo", Stanowikso.Programista, 9000.0);
		boolean added = serwis.addEmployee(nowy);
		System.out.println(added);
		boolean addedDuplikat = serwis.addEmployee(new Employee("Klon", "Klonowy", "j.nusz@mail.com", "Z Archiwum X", Stanowikso.Stażysta, 100.0));
		System.out.println("Jeśli duplikacja maila dobrze działa to będzie false >>>>" + addedDuplikat);

		boolean usunieto = serwis.removeByEmail("fur.dojczland@schmetterling.com");
		System.out.println("fur.dojczland@schmetterling.com przestał istnieć (I hope??)" + usunieto);
		System.out.println("Lista po abortowaniu");
		for (Employee e : serwis.wszyscyPracownicy()) {
			System.out.println(e);
		}

		Optional<Employee> znaleziony = serwis.pracownikPoMailu("kapitan.b@gmail.com");
		if (znaleziony.isPresent()) {
			System.out.println("GDZIE JEST BOMBA?" + znaleziony.get());
		} else {
			System.out.println("Bomby nie ma :c");
		}

		// SpringApplication.run(AplikacjePrzemysłoweApplication.class, args);
	}


}	