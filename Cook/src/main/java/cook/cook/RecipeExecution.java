package cook.cook;

import java.io.IOException;
import java.util.Scanner;

public class RecipeExecution {

    // Εκτελεί τη συνταγή βήμα-βήμα
    public static void executeRecipe(Recipe recipe) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Ξεκινάμε την εκτέλεση της συνταγής ===");
        int stepNumber = 1;

        for (String stepDescription : recipe.getSteps()) {
            if (stepDescription == null || stepDescription.trim().isEmpty()) {
                continue; // Παράβλεψε κενά βήματα
            }

            System.out.printf("Βήμα %d: %s%n", stepNumber++, stepDescription);

            // Έλεγχος για χρόνο αναμονής
            long waitTime = recipe.getStepTimers().getOrDefault(stepDescription, 0L);
            if (waitTime > 0) {
                System.out.printf("Χρόνος αναμονής: %d λεπτά%n", waitTime);

                // Ξεκινά η αντίστροφη μέτρηση
                if (!startCountdown(waitTime)) {
                    // Αν ο χρήστης πάτησε Enter, παράκαμψε την αναμονή
                    System.out.println("Η αναμονή παρακάμφθηκε από τον χρήστη!");
                } else {
                    // Αν ολοκληρώθηκε η αναμονή, ενημερώνει τον χρήστη
                    System.out.println("Ο χρόνος αναμονής ολοκληρώθηκε!");
                }
            }

            // Περιμένει επιβεβαίωση από τον χρήστη πριν το επόμενο βήμα
            System.out.println("Πατήστε Enter για να συνεχίσετε στο επόμενο βήμα...");
            scanner.nextLine();
        }

        System.out.println("\nΗ συνταγή ολοκληρώθηκε! Καλή όρεξη!");
    }

    // Ξεκινά την αντίστροφη μέτρηση με δυνατότητα παράκαμψης από τον χρήστη
    private static boolean startCountdown(long minutes) {
        Scanner scanner = new Scanner(System.in);
        long seconds = minutes * 60; // Μετατροπή λεπτών σε δευτερόλεπτα

        System.out.println("Πατήστε Enter για να παρακάμψετε την αναμονή...");
        while (seconds > 0) {
            System.out.printf("Απομένουν %d λεπτά και %d δευτερόλεπτα...%n", seconds / 60, seconds % 60);

            try {
                // Περιμένει για 1 δευτερόλεπτο
                Thread.sleep(1000);

                // Αν ο χρήστης πατήσει Enter, παρακάμπτει την αναμονή
                if (System.in.available() > 0) {
                    scanner.nextLine(); // Διαβάζει το Enter
                    return false; // Αναμονή παρακάμφθηκε
                }

                seconds--;

            } catch (InterruptedException | IOException e) {
                System.err.println("Σφάλμα κατά την αναμονή: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }

        return true; // Αναμονή ολοκληρώθηκε
    }
}
