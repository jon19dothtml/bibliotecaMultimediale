package it.its.bibliotecaMultimediale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestionePrestiti {
    private final Set<Prestito> collezionePrestito;

    public GestionePrestiti() {
        this.collezionePrestito = new HashSet<>();
    }

    public void aggiungiPrestito(Prestito prestito) {
        collezionePrestito.add(prestito);
    }

    public String stampaCollezionePrestito() {
        StringBuilder builder = new StringBuilder();
        for (Prestito prestito : collezionePrestito) { //classe per collezione
            builder.append(prestito.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
    public void restituzionePrestito(long idUtente, long idMateriale, LocalDate dataPrestito){
        for(Prestito prestito: collezionePrestito){
            if(idUtente==prestito.getRiferimentoUtente().getId()
            && idMateriale==prestito.getRiferimentoMateriale().getId()
            && dataPrestito.equals(prestito.getDataPrestito())
            && prestito.getDataRestituzione()==null){
                prestito.setDataRestituzione(LocalDate.now());
            }
        }
    }
    public List<Prestito> ricercaPrestito(Utente utente){
        List<Prestito> risultato = new ArrayList<>();
        for(Prestito prestito: collezionePrestito){
            if(utente.equals(prestito.getRiferimentoUtente())){
                risultato.add(prestito);
            }
        }
        return risultato;
    }
    public List<Prestito> ricercaPrestito(MaterialeBiblioteca materialeBiblioteca){
        List<Prestito> risultato = new ArrayList<>();
        for(Prestito prestito: collezionePrestito){
            if(materialeBiblioteca.equals(prestito.getRiferimentoMateriale())){
                risultato.add(prestito);
            }
        }
        return risultato;
    }
}
