package it.its.bibliotecaMultimediale;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DVD extends MaterialeBiblioteca implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Autore regista;
    private final int durata;

    public enum Genere {
        AZIONE,
        HORROR,
        DRAMMATICO;
        public static Map<String, Genere> GENERE_MAP = new HashMap<>();

        static {
            GENERE_MAP.put("azione", AZIONE);
            GENERE_MAP.put("horror", HORROR);
            GENERE_MAP.put("drammatico", DRAMMATICO);
        }

        public static Genere lookUp(String value) {
            if (value == null)
                throw new IllegalArgumentException("Valore nullo");
            value = value.toLowerCase();
            Genere genere = GENERE_MAP.get(value);
            if (genere != null) {
                return genere;
            } else {
                throw new IllegalArgumentException("Genere non valido");
            }

        }
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
                ",\nid=" + this.getId() +
                ",\ntitolo='" + this.getTitolo() + '\'' +
                ",\nannoRilascio=" + this.getAnnoRilascio() + '\'' +
                ",\ndisponibilita=" + this.getDisponibilita() + '\'' +
                ",\nregista=" + regista +
                ",\ndurata=" + durata +
                ",\ngenere=" + genere +
                '}';
    }
}
