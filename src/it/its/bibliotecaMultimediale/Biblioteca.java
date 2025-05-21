package it.its.bibliotecaMultimediale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Biblioteca {
    private final Set<MaterialeBiblioteca> collezioneMateriali;

    public Biblioteca() {
        this.collezioneMateriali = new HashSet<>();
    }

    public Set<MaterialeBiblioteca> getCollezioneMateriali() {
        return collezioneMateriali;
    }

    public void aggiungiMateriali(MaterialeBiblioteca materiale) {
        collezioneMateriali.add(materiale);
    }

    public String stampaCollezioneMateriali() {
        StringBuilder builder = new StringBuilder();
        for (MaterialeBiblioteca materiale : collezioneMateriali) { //classe per collezione
            builder.append(materiale.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public List<MaterialeBiblioteca> ricercaElementi(String titolo) throws Exception {
        //mod accesso, tipo ritorno, nome metodo e parametri
        List<MaterialeBiblioteca> risultato = new ArrayList<>();
        for (MaterialeBiblioteca materiale : collezioneMateriali) {
            if (titolo.equals(materiale.getTitolo())) {
                risultato.add(materiale);
            }
        }
        if(risultato.isEmpty())
           throw new NoItemException("Errore");
        return risultato;
    }

    public MaterialeBiblioteca ricercaElementi(long id) {
        for (MaterialeBiblioteca materiale : collezioneMateriali) {
            if (id==materiale.getId()) {
                return materiale;
            }
        }
        return null;
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
        List<MaterialeBiblioteca> risultato = new ArrayList<>();
        for (MaterialeBiblioteca materiale : collezioneMateriali) {
            if (materiale.getClass().equals(clazz)) {
                risultato.add(materiale);
            }
        }
        return risultato;
    }

    public void dettaglio(long id) {
        for (MaterialeBiblioteca materiale : collezioneMateriali) {
            if (id == materiale.getId()) {
                System.out.println("Oggetto trovato: " + materiale);
            }
        }
    }
}
