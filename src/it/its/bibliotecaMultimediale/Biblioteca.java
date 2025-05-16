package it.its.bibliotecaMultimediale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Biblioteca {
    private final Set<MaterialeBiblioteca> collezioneMateriali;
    private final Set<Utente> collezioneUtente;
    private final Set<Prestito> collezionePrestito;

    public Biblioteca() {
        this.collezioneMateriali = new HashSet<>();
        this.collezioneUtente = new HashSet<>();
        this.collezionePrestito = new HashSet<>();
    }

    public void aggiungiMateriali(MaterialeBiblioteca materiale) {
        collezioneMateriali.add(materiale);
    }

    public void aggiungiUtente(Utente utente) {
        collezioneUtente.add(utente);
    }

    public void aggiungiPrestito(Prestito prestito) {
        collezionePrestito.add(prestito);
    }

    public String stampaCollezioneMateriali() {
        StringBuilder builder = new StringBuilder();
        for (MaterialeBiblioteca materiale : collezioneMateriali) { //classe per collezione
            builder.append(materiale.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public String stampaCollezioneUtente() {
        StringBuilder builder = new StringBuilder();
        for (Utente utente : collezioneUtente) { //classe per collezione
            builder.append(utente.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public String stampaCollezionePrestito() {
        StringBuilder builder = new StringBuilder();
        for (Prestito prestito : collezionePrestito) { //classe per collezione
            builder.append(prestito.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public List<MaterialeBiblioteca> ricercaElementi(String titolo) {
        //mod accesso, tipo ritorno, nome metodo e parametri
        List<MaterialeBiblioteca> risultato = new ArrayList<>();
        for (MaterialeBiblioteca materiale : collezioneMateriali) {
            if (titolo.equals(materiale.getTitolo())) {
                risultato.add(materiale);

            }
        }
        return risultato;
    }

    public List<MaterialeBiblioteca> ricercaElementi(Autore autore) {
        //mod accesso, tipo ritorno, nome metodo e parametri
        List<MaterialeBiblioteca> risultato = new ArrayList<>();
        for (MaterialeBiblioteca materiale : collezioneMateriali) {
            if (materiale instanceof Libro libro) {
                if (autore.equals(libro.getRiferimentoAutore())) {
                    risultato.add(materiale);
                }
            } else if (materiale instanceof DVD dvd) {
                if (autore.equals(dvd.getRegista())) {
                    risultato.add(materiale);
                }
            }
        }
        return risultato;
    }

    public List<MaterialeBiblioteca> ricercaElementi(Class<? extends MaterialeBiblioteca> clazz) {
        //mod accesso, tipo ritorno, nome metodo e parametri
        List<MaterialeBiblioteca> risultato= new ArrayList<>();
        for(MaterialeBiblioteca materiale: collezioneMateriali){
            if(materiale.getClass().equals(clazz)){
                risultato.add(materiale);
            }
        }
        return risultato;
    }


}
