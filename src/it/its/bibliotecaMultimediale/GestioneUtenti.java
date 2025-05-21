package it.its.bibliotecaMultimediale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public void rimuoviUtente(int id) {
        collezioneUtente.removeIf(utente -> id == utente.getId());
    }

    public Utente ricercaUtente(int id) {
        for (Utente utente : collezioneUtente) {
            if (utente.getId() == id) {
                return utente;
            }
        }
        return null;
    }

    public List<Utente> ricercaUtente(String nome, String cognome){
        List<Utente> risultato= new ArrayList<>();
        for (Utente utente: collezioneUtente){
            if(utente.getNome().equals(nome) && utente.getCognome().equals(cognome)){
                risultato.add(utente);
            }
        }
        return risultato;
    }
    public List<Utente> ricercaUtente(String ricerca){
        List<Utente> risultato= new ArrayList<>();
        for (Utente utente: collezioneUtente){
            if(utente.getNome().equals(ricerca) || utente.getCognome().equals(ricerca)){
                risultato.add(utente);
            }
        }
        return risultato;
    }

    public Set<Utente> getCollezioneUtenti() {
        return collezioneUtente;
    }

}
