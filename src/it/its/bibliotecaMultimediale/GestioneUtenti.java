package it.its.bibliotecaMultimediale;

import java.util.HashSet;
import java.util.Set;

public class GestioneUtenti {
    private final Set<Utente> collezioneUtente;

    public GestioneUtenti() {
        this.collezioneUtente = new HashSet<>();
    }

    public void aggiungiUtente(Utente utente) {
        collezioneUtente.add(utente);
    }

    public String stampaCollezioneUtente() {
        StringBuilder builder = new StringBuilder();
        for (Utente utente : collezioneUtente) { //classe per collezione
            builder.append(utente.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
    public void rimuoviUtente(long id) {
        collezioneUtente.removeIf(utente -> id == utente.getId());
    }

}
