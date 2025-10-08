package LatoRok3.apki_przemyslowe.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import LatoRok3.apki_przemyslowe.models.Employee;
import LatoRok3.apki_przemyslowe.models.Stanowikso;

public class EmploymentService {
    private final Map<String, Employee> pracownicy_i_maile = new HashMap<>();

    public boolean addEmployee(Employee pracownik) {
        if (pracownik == null || pracownik.jakiMail() == null || pracownik.jakiMail().trim().isEmpty()) {
            throw new IllegalArgumentException("Pracownik i/lub jego mail jest niepodany");
        }

        String key = pracownik.jakiMail().trim().toLowerCase(Locale.ROOT);

        if (pracownicy_i_maile.containsKey(key)) {
            return false;
        }

        pracownicy_i_maile.put(key, pracownik);
        return true;
    }

    public List<Employee> wszyscyPracownicy() {
        return new ArrayList<>(pracownicy_i_maile.values());
    }

    public Optional<Employee> pracownikPoMailu(String mail) {
        if (mail == null) {
            return Optional.empty();
        }

        String key = mail.trim().toLowerCase(Locale.ROOT);
        return Optional.ofNullable(pracownicy_i_maile.get(key));
    }

    public Boolean removeByEmail(String mail) {
        if (mail == null) {
            return false;
        }

        String key = mail.trim().toLowerCase(Locale.ROOT);
        return pracownicy_i_maile.remove(key) != null;
    }

    public List<Employee> pracownicyWFirmie(String firma) {
        if (firma == null) {
            return List.of();
        }
        String f = firma.trim();
        return pracownicy_i_maile.values().stream()
                .filter(e -> e != null && e.jakaFirma() != null && e.jakaFirma().trim().equalsIgnoreCase(f))
                .collect(Collectors.toList());
    }

    public List<Employee> pracownicyPosortowaniWedlugNazwiska() {
        return pracownicy_i_maile.values().stream()
                .sorted(Comparator.comparing(e -> {
                    String nazwisko = e == null ? null : e.dawajNazwisko();
                    return nazwisko == null ? "" : nazwisko.toLowerCase(Locale.ROOT);
                }))
                .collect(Collectors.toList());
    }

    public Map<Stanowikso, List<Employee>> grupujPoStanowisku() {
        return pracownicy_i_maile.values().stream()
                .filter(e -> e != null && e.jakaPozycja() != null)
                .collect(Collectors.groupingBy(Employee::jakaPozycja));
    }

    public Map<Stanowikso, Long> zliczPoStanowisku() {
        return pracownicy_i_maile.values().stream()
                .filter(e -> e != null && e.jakaPozycja() != null)
                .collect(Collectors.groupingBy(Employee::jakaPozycja, Collectors.counting()));
    }

    public OptionalDouble sredniaPlacy() {
        return pracownicy_i_maile.values().stream()
                .filter(e -> e != null && e.jakiSzmal() != null)
                .mapToDouble(Employee::jakiSzmal)
                .average();
    }

    public Optional<Employee> pracownikZNajwyzszaPlaca() {
        return pracownicy_i_maile.values().stream()
                .filter(e -> e != null && e.jakiSzmal() != null)
                .max(Comparator.comparingDouble(Employee::jakiSzmal));
    }

}
