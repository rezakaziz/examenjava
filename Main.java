package gestioncolis;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class Main {
	
	private int testCount = 0;
    private int testFailed = 0;

    public static void main(String[] args) {
        (new Main()).run();
    }

    public void run() {
        //testPersonne();
        //testColis();
        //testColisExpress();
        //testGestionColis();

        System.out.println("*** TESTS RUN: " + testCount + " - FAILURE: " + testFailed + " - SUCCESS: " + (testCount - testFailed) + " ***");
    }
/*
    private void testPersonne() {
        System.out.println("== Test Personne ==");

        Personne personne = new Personne("Alice", "Paris");
        check("Nom correct", personne.getName().equals("Alice"), "Nom attendu: Alice");
        check("Adresse correcte", personne.getAdresse().equals("Paris"), "Adresse attendue: Paris");

        personne.setName("Bob");
        personne.setAdresse("Lyon");
        check("Nom modifié", personne.getName().equals("Bob"), "Nom attendu: Bob");
        check("Adresse modifiée", personne.getAdresse().equals("Lyon"), "Adresse attendue: Lyon");
        checkAffichage(()->personne.afficherDetails(),"Affichage Correct","Bob Lyon\n");
        System.out.println();
    }

    private void testColis() {
        System.out.println("== Test Colis ==");

        Personne expediteur = new Personne("Alice", "Paris");
        Personne destinataire = new Personne("Bob", "Lyon");
        Colis colis = new Colis(expediteur, destinataire, 5.0);

        check("Poids correct", colis.getPoids() == 5.0, "Poids attendu: 5.0");
        check("Expéditeur correct", colis.getExpediteur() == expediteur, "Expéditeur attendu: Alice");
        check("Destinataire correct", colis.getDestinataire() == destinataire, "Destinataire attendu: Bob");

        colis.setPoids(10.0);
        check("Poids modifié", colis.getPoids() == 10.0, "Poids attendu: 10.0");
        checkAffichage(()->colis.afficherDetails(),"Affichage Correct", "Num Colis :0\nPoids :10.0Kg\nSource :Alice Paris\nDestination :Bob Lyon\nTarif : 25.0 Euros\n");
        System.out.println();
    }

    private void testColisExpress() {
        System.out.println("== Test ColisExpress ==");
        
        // Création des objets nécessaires
        Personne expediteur = new Personne("Charlie", "Marseille");
        Personne destinataire = new Personne("David", "Nice");
        ColisExpress colisExpress = new ColisExpress(expediteur, destinataire, 2.0, "2024-11-20", 5.0);

        // Tests des getters
        check("Poids correct", colisExpress.getPoids() == 2.0, "Poids attendu: 2.0");
        check("Date de livraison correcte", colisExpress.getDateLivraison().equals("2024-11-20"), "Date attendue: 2024-11-20");
        check("Surcharge correcte", colisExpress.getSurcharge() == 5.0, "Surcharge attendue: 5.0");

        // Modification et vérification des setters
        colisExpress.setSurcharge(10.0);
        check("Surcharge modifiée", colisExpress.getSurcharge() == 10.0, "Surcharge attendue: 10.0");

        // Vérification de l'affichage
        String expectedOutput = "Num Colis :1\nPoids :2.0Kg\nSource :Charlie Marseille\nDestination :David Nice\nDate de livraison : 2024-11-20\nTarif : 15.0 Euros\n";
        checkAffichage(() -> colisExpress.afficherDetails(), "Affichage Correct", expectedOutput);

        System.out.println();
    }

    private void testGestionColis() {
    System.out.println("== Test GestionColis ==");

    // Création de la gestion des colis
    GestionColis gestionColis = new GestionColis();

    // Ajout des premiers colis simples
    Colis colis1 = new Colis(new Personne("Alice", "Paris"), new Personne("Bob", "Lyon"), 3.0);
    Colis colis2 = new Colis(new Personne("Eve", "Toulouse"), new Personne("Frank", "Bordeaux"), 4.0);

    // Ajout des premiers colis express
    ColisExpress colisExpress1 = new ColisExpress(new Personne("Charlie", "Marseille"), new Personne("David", "Nice"), 2.0, "2024-11-20", 5.0);
    ColisExpress colisExpress2 = new ColisExpress(new Personne("Isabelle", "Lille"), new Personne("Jack", "Grenoble"), 6.0, "2024-11-21", 7.0);

    // Ajout des deuxièmes colis simples
    Colis colis3 = new Colis(new Personne("Grace", "Nantes"), new Personne("Hank", "Strasbourg"), 5.0);
    Colis colis4 = new Colis(new Personne("Laura", "Dijon"), new Personne("Mike", "Rennes"), 3.5);

    // Ajout des deuxièmes colis express
    ColisExpress colisExpress3 = new ColisExpress(new Personne("Nina", "Tours"), new Personne("Olivier", "Nancy"), 4.0, "2024-11-22", 6.0);
    ColisExpress colisExpress4 = new ColisExpress(new Personne("Paul", "Caen"), new Personne("Quentin", "Avignon"), 5.5, "2024-11-23", 8.0);

    // Ajout des colis dans la gestion
    gestionColis.ajoutColis(colis1);
    gestionColis.ajoutColis(colis2);
    gestionColis.ajoutColis(colisExpress1);
    gestionColis.ajoutColis(colisExpress2);
    gestionColis.ajoutColis(colis3);
    gestionColis.ajoutColis(colis4);
    gestionColis.ajoutColis(colisExpress3);
    gestionColis.ajoutColis(colisExpress4);

    // Vérification des ajouts
    check("Ajout colis simple", gestionColis.getColis()[0] == colis1, "Colis attendu: Alice -> Bob");
    check("Ajout colis express", gestionColis.getColis()[2] == colisExpress1, "Colis attendu: Charlie -> David");
    

    // Recherche de colis
    int index = gestionColis.rechercherColisByNumber(colis4.getNumColis());
    check("Recherche colis", index == 5, "Colis trouvé à l'index 5");

    // Vérification de l'affichage d'un colis simple
    String expectedColis4Details = "Num Colis :7\nPoids :3.5Kg\nSource :Laura Dijon\nDestination :Mike Rennes\nTarif : 8.75 Euros\n";
    checkAffichage(() -> gestionColis.afficherColis(5), "Affichage colis simple", expectedColis4Details);

    // Vérification de l'affichage d'un colis express
    String expectedColisExpress3Details = "Num Colis :8\nPoids :4.0Kg\nSource :Nina Tours\nDestination :Olivier Nancy\nDate de livraison : 2024-11-22\nTarif : 16.0 Euros\n";
    checkAffichage(() -> gestionColis.afficherColis(6), "Affichage colis express", expectedColisExpress3Details);

    // Vérification de l'affichage des colis express
    String expectedColisExpressList = "4\n5\n8\n9\n"; // Les numéros des colis express ajoutés
    checkAffichage(() -> gestionColis.afficherListeColisExpress(), "Affichage Liste colis express", expectedColisExpressList);

    System.out.println();
}
*/
    private void check(String message, boolean condition, String debug) {
        testCount++;

        if (!condition) {
            System.out.print("[echec]\t");
            testFailed++;
        } else {
            System.out.print("[ok]\t");
        }

        System.out.println(message);
        if (!condition) {
            System.out.println("---");
            System.out.println(debug);
            System.out.println("---");
        }
    }
    
    private void checkAffichage(Runnable method, String message, String expectedOutput) {
        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Run the method
        method.run();

        // Restore the standard output
        System.setOut(originalOut);

        // Compare the captured output with the expected output
        String actualOutput = outContent.toString();
        check(message, actualOutput.equals(expectedOutput), "Sortie attendue:\n" + expectedOutput + "\nSortie obtenue:\n" + actualOutput);
    }

}
