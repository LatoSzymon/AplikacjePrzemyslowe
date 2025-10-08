package LatoRok3.apki_przemyslowe.models;
import java.util.Objects;

public class Employee {
    private String name;
    private String secondName;
    private String email;
    private String firma;
    private Stanowikso stanowisko;
    private Double placa;

    public Employee(String name, String vorname, String email, String firma, Stanowikso stanowikso, Double money) {
        this.name = name;
        this.secondName = vorname;
        this.email = email;
        this.firma = firma;
        this.stanowisko = stanowikso;
        this.placa = money;
    }

    public void noweImie(String imie) {
        this.name = imie;
    }

    public void noweNazwisko(String vorname) {
        this.secondName = vorname;
    }

    public void nowyMail(String mail) {
        this.email = mail;
    }

    public void zmianaFirmy(String firma) {
        this.firma = firma;
    }

    public void awans(Stanowikso noweStanowisko) {
        this.stanowisko = noweStanowisko;
    }

    public void setMoney(Double nowaKasa) {
        this.placa = nowaKasa;
    }

    public String dawajImie() {
        return this.name;
    }

    public String dawajNazwisko() {
        return this.secondName;
    }

    public String jakiMail() {
        return this.email;
    }

    public String jakaFirma() {
        return this.firma;
    }

    public Stanowikso jakaPozycja() {
        return this.stanowisko;
    }

    public Double jakiSzmal() {
        return this.placa;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Employee)) {
            return false;
        }

        Employee employee = (Employee) obj;

        return Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", firma='" + firma + '\'' +
                ", stanowisko=" + stanowisko +
                ", placa=" + placa + '}';
    }


}
