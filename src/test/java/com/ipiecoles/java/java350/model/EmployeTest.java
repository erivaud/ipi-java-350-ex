package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.exception.EmployeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // pour JUnit5 (pas le même pour le 4)
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    // pjvilloud@protonmail.com
    @Test
    public void testAugmenterSalaireDoubleNegatif() {
            // Given
        Employe e = new Employe();
        e.setSalaire(Entreprise.SALAIRE_BASE);
        double pourcentage = -0.5;

        try {
            // When
            e.augmenterSalaire(pourcentage);
            Assertions.fail("Devrait lancer une exception : pourcentage négatif");
        }
        catch (EmployeException employeException) {
            // Then
            Assertions.assertEquals("Le pourcentage donné : " + pourcentage + " ne peut être inférieur à 0, il est illégal de diminuer un salaire.", employeException.getMessage());
        }
    }

    @Test
    public void testAugmenterSalaireDoubleZero() {
        // Given
        Employe e = new Employe();
        e.setSalaire(Entreprise.SALAIRE_BASE);
        double pourcentage = 0.0;

        try {
            // When
            e.augmenterSalaire(pourcentage);
            Assertions.fail("Devrait lancer une exception : pourcentage = 0" );
        } catch (EmployeException employeException) {
            // Then
            Assertions.assertEquals("Le pourcentage d'augmentation est égal à 0 : aucune augmentation de salaire n'est effective", employeException.getMessage());
        }
    }

    @Test
    public void testAugmenterSalaireNull() {
        // Given
        Employe e = new Employe();
        e.setSalaire(null);

        try {
            // When
            e.augmenterSalaire(0.5);
            Assertions.fail("Devrait lancer une exception : Salaire null");
        } catch (EmployeException employeException) {
            // Then
            Assertions.assertEquals("Le salaire de l'employe n'est pas initialisé, il doit être renseigné avant d'être augmenté", employeException.getMessage());
        }
    }


    @Test
    public void testAugmenterSalaire50Pourcent() throws EmployeException {
        // Given : cas nominal
        Employe e = new Employe();
        e.setSalaire(Entreprise.SALAIRE_BASE);
        // When
        e.augmenterSalaire(0.5);
        // Then
        Assertions.assertEquals(2281.83, (double)e.getSalaire());
    }

    @Test
    public void testAugmenterSalaire200Pourcent() throws EmployeException {
        // Given
        Employe e = new Employe();
        e.setSalaire(Entreprise.SALAIRE_BASE);
        // When
        e.augmenterSalaire(2.0);
        // Then
        Assertions.assertEquals(4563.66, (double)e.getSalaire());
    }

    @Test
    public void testNombreAnneesAncienneteDateEmbaucheNull() {

        //Given = Initialisation des données d'entrée
        Employe e = new Employe();
        e.setDateEmbauche(null); // au cas où le constructeur par défaut prévoit une date d'embauche par défaut

        //When = Exécution de la méthode à tester
        Integer nbAnneesAnciennete = e.getNombreAnneeAnciennete();


        //Then = Vérifications de ce qu'a fait la méthode
        Assertions.assertEquals(0, (int)nbAnneesAnciennete);
        //on caste le Integer en int pour faire une comparaison equals
    }

    @Test
    public void testNombreAnneesAncienneteDateEmbaucheNow() {
        // Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        // When
        Integer nbAnneAnciennete = e.getNombreAnneeAnciennete();

        // Then
        Assertions.assertEquals(0, (int)nbAnneAnciennete);
    }

    @Test
    public void testNombreAnneesAncienneteMinus2(){
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2));

        Integer nbAnneeAnciennete = e.getNombreAnneeAnciennete();

        Assertions.assertEquals(2, (int)nbAnneeAnciennete);
    }

    @Test
    public void testNombreAnneesAncienneteDateEmbaucheAfterNow() {

        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(1));

        Integer nbAnneeAnciennete = e.getNombreAnneeAnciennete();

        Assertions.assertEquals(0, (int)nbAnneeAnciennete);
    }



 //   Créer une méthode de test paramétré permettant de tester le plus exhaustivement possible la méthode getPrimeAnnuelle et corriger les éventuels problèmes de cette méthode.

    @ParameterizedTest(name = "pour employé marticule {1}, perf {0}, ancienneté {2}, temps partiel {3} : prime annuelle {4}")
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0"
            // ici les valeurs données doivent être calculées à la main et non pas par la code, sinon évidemment ça va être evalidé.
    })

    void testGetPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double primeAnnuelle ){

        // Given
        Employe e = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);

        // When
        Double primeATester = e.getPrimeAnnuelle();

        // Then
        Assertions.assertEquals(primeAnnuelle, primeATester);

    }

// Intégration continue
    // Evaluation de la qualité
    // créer une branche à partir de là où on en est
    // et commecer les tests unitaires TDD + coder augmenterSalaire()
    // pourcentage entre zéro à un
    // pareil getNbRtt()
    // pour dans deux semaines d'ici à aujourd'hui le 5 mars 2019
    // en fait quand on revoit le prof.

}

// création d'une nouvelle branche : ok, nommée : stlbranch
// switch sur la branche ok
// Sign in Travis CI avec GitHub : ok
// activation de Travis CI sur le repo : ok
// configuration Travis : ok et premier build : ok
