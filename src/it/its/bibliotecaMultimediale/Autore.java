package it.its.bibliotecaMultimediale;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Autore implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final String cognome;
    private final LocalDate dataNascita;

    public Autore(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Autore autore)) return false;

        return Objects.equals(nome, autore.nome) && Objects.equals(cognome, autore.cognome) && Objects.equals(dataNascita, autore.dataNascita);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nome);
        result = 31 * result + Objects.hashCode(cognome);
        result = 31 * result + Objects.hashCode(dataNascita);
        return result;
    }

    @Override
    public String toString() {
        return "Autore{" +
                "\nnome='" + nome + '\'' +
                ",\ncognome='" + cognome + '\'' +
                ",\ndataNascita=" + dataNascita +
                '}';
    }
}
