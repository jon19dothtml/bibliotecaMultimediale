package it.its.bibliotecaMultimediale;

import java.util.Objects;

public class DVD extends MaterialeBiblioteca {
    private final Autore regista;
    private final int durata;

    public enum Genere {
        AZIONE,
        HORROR,
        DRAMMATICO
    }

    private final Genere genere;

    public DVD(long id, String titolo, int annoRilascio, int disponibilita, Autore regista, int durata, Genere genere) {
        super(id, titolo, annoRilascio, disponibilita);
        this.regista = regista;
        this.durata = durata;
        this.genere = genere;
    }

    public Autore getRegista() {
        return regista;
    }

    public int getDurata() {
        return durata;
    }

    public Genere getGenere() {
        return genere;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof DVD dvd)) return false;

        return durata == dvd.durata && Objects.equals(regista, dvd.regista) && genere == dvd.genere;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(regista);
        result = 31 * result + durata;
        result = 31 * result + Objects.hashCode(genere);
        return result;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + this.getId() +
                "titolo='" + this.getTitolo() + '\'' +
                "annoRilascio=" + this.getAnnoRilascio() + '\'' +
                "disponibilita=" + this.getDisponibilita() + '\'' +
                "regista=" + regista +
                ", durata=" + durata +
                ", genere=" + genere +
                '}';
    }
}
