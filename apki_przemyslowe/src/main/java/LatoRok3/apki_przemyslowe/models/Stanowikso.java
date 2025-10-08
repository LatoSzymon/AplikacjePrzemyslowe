package LatoRok3.apki_przemyslowe.models;

public enum Stanowikso {
    Prezes(1, 25000),
    Wiceprezes(2, 18000),
    Manager(3, 12000),
    Programista(4, 8000),
    Stażysta(5, 3000);

    private final double bazowyPrzychod;
    private final int pozycja;

    Stanowikso(int pozycja, double bazowyPrzychod) {
        this.pozycja = pozycja;
        this.bazowyPrzychod = bazowyPrzychod;
    }

    public int jakaPraca() {
        return pozycja;
    }

    public double jakaPlaca() {
        return bazowyPrzychod;
    }

}
