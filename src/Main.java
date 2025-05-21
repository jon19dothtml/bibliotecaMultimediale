import it.its.bibliotecaMultimediale.*;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = Main.caricaBiblioteca();
        GestioneUtenti gestioneUtenti= Main.caricaUtenti();
        GestionePrestiti gestionePrestiti = new GestionePrestiti();
        int scelta = 0;
        do {
            System.out.println("""
                    Benvenuto nel menù. Seleziona: \s
                    1- per aggiungere materiale\s
                    2- per aggiungere un utente\s
                    3- per ricercare un materiale\s
                    4- per richiedere un prestito\s
                    5- per restituire un prestito\s
                    6- per ricercare un utente\s
                    0- per uscire""");
            Scanner scanner = new Scanner(System.in);
            scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 0:
                    System.out.println("Arrivederci");
                    break;
                case 1:
                    Main.aggiungiMateriale(biblioteca, scanner);
                    break;
                case 2:
                    Main.aggiungiUtente(gestioneUtenti, scanner);
                    break;
                case 3:
                    Main.ricercaMateriale(biblioteca, scanner);
                    break;
                case 4:
                    Main.richiediPrestito(biblioteca, gestioneUtenti, gestionePrestiti, scanner);
                    break;
                case 5:
                    Main.restituzionePrestito(biblioteca, gestionePrestiti, gestioneUtenti, scanner);
                    break;
                case 6:
                    Main.ricercaUtente(gestioneUtenti, scanner);
                    break;
                default:
                    System.out.println("Scelta non valida!!! Arrivederci e grazie");
            }
        } while (scelta != 0);
        Main.salvaBibilioteca(biblioteca);
        Main.salvaUtenti(gestioneUtenti);
    }

    private static void salvaBibilioteca(Biblioteca biblioteca) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("C:/Users/A880apulia/IdeaProjects/Biblioteca/resource/biblioteca.txt"))) {
            for (MaterialeBiblioteca materiale : biblioteca.getCollezioneMateriali()) {
                outputStream.writeObject(materiale);
            }
        } catch (IOException ex) {
            System.out.println("Eccezione in scrittura");
            ex.printStackTrace();
        }
    }

    private static Biblioteca caricaBiblioteca() {
        Biblioteca biblioteca = new Biblioteca();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("C:/Users/A880apulia/IdeaProjects/Biblioteca/resource/biblioteca.txt"))) {
            MaterialeBiblioteca materialeBiblioteca = null;
            while ((materialeBiblioteca = (MaterialeBiblioteca) inputStream.readObject()) != null) {
                biblioteca.aggiungiMateriali(materialeBiblioteca);
            }
            System.out.println("Caricati: " + biblioteca.getCollezioneMateriali().size());
            return biblioteca;
        } catch (EOFException eofException) {
            System.out.println("End of file raggiunta");
        } catch (IOException | ClassNotFoundException ioEx) {
            System.out.println("Eccezione");
            ioEx.printStackTrace();
        } finally {
            System.out.println("Questo lo eseguo sempre");
        }
        return biblioteca;
    }

    public static void salvaUtenti(GestioneUtenti gestioneUtenti){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("C:/Users/A880apulia/IdeaProjects/Biblioteca/resource/utente.txt"))) {
            for (Utente utente : gestioneUtenti.getCollezioneUtenti()) {
                outputStream.writeObject(utente);
            }
        } catch (IOException ex) {
            System.out.println("Eccezione in scrittura");
            ex.printStackTrace();
        }
    }

    public static GestioneUtenti caricaUtenti(){
        GestioneUtenti gestioneUtenti= new GestioneUtenti();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("C:/Users/A880apulia/IdeaProjects/Biblioteca/resource/utente.txt"))) {
            Utente utente= null;
            while ((utente = (Utente) inputStream.readObject()) != null) {
                gestioneUtenti.aggiungiUtente(utente);
            }
            System.out.println("Caricati: " + gestioneUtenti.getCollezioneUtenti().size());
            return gestioneUtenti;
        } catch (EOFException eofException) {
            System.out.println("End of file raggiunta");
        } catch (IOException | ClassNotFoundException ioEx) {
            System.out.println("Eccezione");
            ioEx.printStackTrace();
        } finally {
            System.out.println("Questo lo eseguo sempre");
        }
        return gestioneUtenti;
    }

    private static void ricercaUtente(GestioneUtenti gestioneUtenti, Scanner scanner) {
        System.out.println("Scegli per cosa ricercare. Spingi: " +
                "\n1- per ricercare per id" +
                "\n2- per ricercare per nome e cognome" +
                "\n3- per ricercare per nome o cognome" +
                "\n0- per uscire");
        int scelta= scanner.nextInt();
        scanner.nextLine();
        switch (scelta){
            case 0:
                System.out.println("Arrivederci");
                break;
            case 1:
                System.out.println("Inserisci l'id da ricercare: ");
                int id= scanner.nextInt();
                scanner.nextLine();
                try {
                    Utente risultato= gestioneUtenti.ricercaUtente(id);
                    System.out.println("Risultato: " + risultato);

                } catch (Exception e) {
                    System.out.println("non ho trovato nessun elemento con id: " + id);
                }
                break;
            case 2:
                System.out.println("Inserisci il nome da ricercare: ");
                String nome= scanner.nextLine();
                System.out.println("Inserisci il cognome da ricercare: ");
                String cognome= scanner.nextLine();
                try {
                    List<Utente> risultato = gestioneUtenti.ricercaUtente(nome, cognome);
                    System.out.println("Risultato: " +risultato);
                } catch (Exception e) {
                    System.out.println("non ho trovato nessun elemento con nome: " + nome + "e cognome: " + cognome);
                }
                break;
            case 3:
                System.out.println("Inserisci il nome o il cognome da ricercare");
                String ricerca= scanner.nextLine();
            try {
                    List<Utente> risultato= gestioneUtenti.ricercaUtente(ricerca);
                    System.out.println("Risultato: " +risultato);
                }catch (Exception e) {
                    System.out.println("non ho trovato nessun elemento con nome o cognome: " + ricerca);
                }
                break;
            default:
                System.out.println("Scelta non valida!!! Arrivederci e grazie");
        }
    }

    private static void restituzionePrestito(Biblioteca biblioteca, GestionePrestiti gestioneUtenti, GestioneUtenti gestioneUtenti1, Scanner scanner) {
    }

    private static void richiediPrestito(Biblioteca biblioteca, GestioneUtenti gestioneUtenti, GestionePrestiti gestionePrestiti, Scanner scanner) {
        System.out.println("Inserisci l'id del materiale ");
        long id;
        id=scanner.nextLong();
        scanner.nextLine();
        MaterialeBiblioteca riferimentoMateriale= biblioteca.ricercaElementi(id);
        System.out.println("Inserisci l'id dell'utente");
        id= scanner.nextLong();
        scanner.nextLine();
        Utente riferimentoUtente= gestioneUtenti.ricercaUtente((int) id);
        LocalDate dataPrestito= LocalDate.now();
        if(riferimentoUtente!=null && riferimentoMateriale!=null && riferimentoMateriale.getDisponibilita()>0){
            riferimentoMateriale.setDisponibilita(riferimentoMateriale.getDisponibilita()-1);
            Prestito prestito= new Prestito(riferimentoMateriale, riferimentoUtente, dataPrestito);
        }
    }

    private static void ricercaMateriale(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("Per cosa vuoi ricercare il materiale? \n" +
                "\n1- per titolo" +
                "\n2- per autore" +
                "\n3- per tipo" +
                "\n0- per uscire");
        int scelta= scanner.nextInt();
        scanner.nextLine();
        switch(scelta) {
            case 0:
                System.out.println("Arrivederci");
                break;
            case 1:
                System.out.println("Inserisci il titolo: ");
                String titolo = scanner.nextLine();
                try {
                    List<MaterialeBiblioteca> risultato = biblioteca.ricercaElementi(titolo);
                    System.out.println("Risultato: " + risultato);

                } catch (Exception e) {
                    System.out.println("non ho trovato nessun elemento con titolo: " + titolo);
                }
                break;
            case 2:
                System.out.println("Inserisci l'autore/ regista");
                Autore autore= Main.acquisisciAutore(scanner);
                try{
                    List<MaterialeBiblioteca> risultato = biblioteca.ricercaElementi(autore);
                    System.out.println("Risultato: " + risultato);
                }catch (Exception e){
                    System.out.println("non ho trovato nessun elemento con AUTORE: " + autore);
                }
                break;
            case 3:
                Map<String, Class<? extends MaterialeBiblioteca>> TIPO_MAP = new HashMap<>();
                TIPO_MAP.put("libro", Libro.class);
                TIPO_MAP.put("dvd", DVD.class);
                TIPO_MAP.put("rivista", Rivista.class);
                System.out.println("Inserisci il tipo da cercare: ");
                String tipo= scanner.nextLine();
                if (tipo == null)
                    throw new IllegalArgumentException("Valore nullo");
                tipo = tipo.toLowerCase();
                Class<? extends MaterialeBiblioteca> tipoClass= TIPO_MAP.get(tipo);
                if (tipoClass == null) {
                   throw new IllegalArgumentException("Tipo non valido");
                }
                List<MaterialeBiblioteca> risultato= biblioteca.ricercaElementi(tipoClass);
                System.out.println("Risultato: " +risultato);
                break;
            default:
                System.out.println("Scelta non valida!!! Arrivederci e grazie");
        }
    }

    private static void aggiungiUtente(GestioneUtenti gestioneUtenti, Scanner scanner) {
        System.out.println("Inserisci id utente: ");
        int id= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci nome: ");
        String nome= scanner.nextLine();
        System.out.println("Inserisci cognome: ");
        String cognome= scanner.nextLine();
        Utente utente = new Utente(id, nome, cognome);
        gestioneUtenti.aggiungiUtente(utente);
        System.out.println("Utente aggiunto correttamente! " +utente );


    }

    private static void aggiungiMateriale(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("""
                Cosa vuoi aggiungere? Seleziona:\s
                1- per aggiungere un libro\s
                2- per aggiungere un DVD\s
                3- per aggiungere una rivista\s
                """);
        int scelta = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci id: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("\nInserisci il titolo: ");
        String titolo = scanner.nextLine();
        System.out.println("\nInserisci anno rilascio: ");
        int annoRilascio = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nInserisci disponibilità: ");
        int disponibilita = scanner.nextInt();
        scanner.nextLine();
        switch (scelta) {
            case 1:
//                System.out.println("Hai selezionato: Libro !");
                System.out.println("Inserisci ISBN:");
                String ISBN = scanner.nextLine();
                System.out.println("Inserisci il numero di pagine: ");
                int pagine = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci l'autore: ");
                Autore autore = Main.acquisisciAutore(scanner);
                Libro libro = new Libro(id, titolo, annoRilascio, disponibilita, ISBN, pagine, autore);
                biblioteca.aggiungiMateriali(libro);
                break;
            case 2:
//                System.out.println("Hai selezionato: DVD !");
                System.out.println("Inserisci il regista: ");
                Autore regista = Main.acquisisciAutore(scanner);
                System.out.println("Inserisci la durata in minuti:");
                int durata = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci il genere: ");
                String genere = scanner.nextLine();
                DVD.Genere genereEnum = DVD.Genere.lookUp(genere);
                DVD dvd = new DVD(id, titolo, annoRilascio, disponibilita, regista, durata, genereEnum);
                biblioteca.aggiungiMateriali(dvd);
                break;
            case 3:
//                System.out.println("Hai selezionato rivista !");
                System.out.println("Inserisci il numero di uscita: ");
                int numeroUscita = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci la periodicità: ");
                String periodicita = scanner.nextLine();
                Rivista.Periodicita periodicitaEnum = Rivista.Periodicita.lookup(periodicita);
                Rivista rivista = new Rivista(id, titolo, annoRilascio, disponibilita, numeroUscita, periodicitaEnum);
                biblioteca.aggiungiMateriali(rivista);
        }
    }

    private static Autore acquisisciAutore(Scanner scanner) {
        System.out.println("Inserisci il nome dell'autore: ");
        String nome = scanner.nextLine();
        System.out.println("Inserisci il cognome dell'autore: ");
        String cognome = scanner.nextLine();
        System.out.println("Inserisci la data di nascita nel formato gg/mm/aaaa ");
        String dataNascita = scanner.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate dataFormattataItalia = LocalDate.parse(dataNascita, format);
            return new Autore(nome, cognome, dataFormattataItalia);
        } catch (DateTimeParseException ex) {
            System.out.println("Formato data errato! ");
            return new Autore(nome, cognome, null);
        }
    }
}