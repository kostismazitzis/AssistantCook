package cook.cook;

import java.util.*;

public class RecipeManager {

    private final Map<String, Double> ingredients = new HashMap<>();
    private final Map<Integer, Integer> stepTimes = new HashMap<>();
    private final List<String> steps;

    public RecipeManager() {
        this.steps = new ArrayList<>();
    }

    // Προσθήκη ή προσθήκη ποσότητας αν το υλικό υπάρχει ήδη
    public void addIngredient(String ingredient, double quantity) {
        ingredients.put(ingredient, ingredients.getOrDefault(ingredient, 0.0) + quantity);
    }

    // Προσαρμογή των ποσοτήτων ανάλογα με τον αριθμό των ατόμων
    public void adjustIngredientsForPeople(int people) {
        double factor = people / 4.0; // Αν θεωρήσουμε 4 άτομα ως την βασική ποσότητα
        Map<String, Double> adjustedIngredients = new HashMap<>();
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            adjustedIngredients.put(entry.getKey(), entry.getValue() * factor);
        }
        ingredients.clear();
        ingredients.putAll(adjustedIngredients);
    }

    // Υπολογισμός του συνολικού χρόνου για τα βήματα
    public int getTotalTime() {
        int totalTime = 0;
        for (int time : stepTimes.values()) {
            totalTime += time;
        }
        return totalTime;
    }

    // Προσθήκη του χρόνου για κάθε βήμα
    public void addStepTime(int step, int timeInMinutes) {
        stepTimes.put(step, timeInMinutes);
    }

    // Προσθήκη των βημάτων της συνταγής
    public void addStep(String step) {
        steps.add(step);
    }

    // Εμφάνιση της συνταγής
    public void displayRecipe() {
        System.out.println("Υλικά:");
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            System.out.printf("- %s: %.2f%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\nΣκεύη:");
        System.out.println("- Μπλέντερ");
        System.out.println("- Μπολ");
        System.out.println("- Μεγάλο αντικολλητικό τηγάνι");

        System.out.println("\nΒήματα:");
        for (int i = 0; i < steps.size(); i++) {
            int stepTime = stepTimes.getOrDefault(i + 1, 0); // Αποφυγή NullPointerException
            System.out.printf("%d. %s - Χρόνος: %d λεπτά%n", i + 1, steps.get(i), stepTime);
        }

        System.out.println("\nΣυνολικός χρόνος: " + getTotalTime() + " λεπτά");
    }

    // Main method για δοκιμή
    public static void main(String[] args) {
        RecipeManager rm = new RecipeManager();

        // Προσθήκη των βημάτων
        rm.addStep("Σπάστε τα αυγά σε ένα μπλέντερ, προσθέστε το αλεύρι, το γάλα και το αλάτι και χτυπήστε μέχρι να γίνει λείο.");
        rm.addStep("Ρίξτε το μείγμα σε ένα μπολ και αφήστε το να σταθεί για 15 λεπτά.");
        rm.addStep("Λιώστε το βούτυρο σε ένα μεγάλο αντικολλητικό τηγάνι σε μέτρια φωτιά, γείρετε το τηγάνι ώστε να καλυφθεί η επιφάνεια με το βούτυρο.");
        rm.addStep("Ρίξτε μία κουτάλα από το μείγμα στο τηγάνι και γείρετε το τηγάνι για να απλωθεί το μείγμα, μαγειρέψτε για 2 λεπτά.");
        rm.addStep("Μόλις χρυσίσει από κάτω, γυρίστε την κρέπα και μαγειρέψτε για 1 λεπτό.");

        // Προσθήκη χρόνου για κάθε βήμα
        rm.addStepTime(1, 15);
        rm.addStepTime(2, 15);
        rm.addStepTime(3, 5);
        rm.addStepTime(4, 2);
        rm.addStepTime(5, 1);

        // Προσθήκη υλικών
        rm.addIngredient("Αυγά", 3);
        rm.addIngredient("Αλεύρι (gr)", 125);
        rm.addIngredient("Γάλα (ml)", 250);
        rm.addIngredient("Αλάτι θαλασσινό", 1);
        rm.addIngredient("Βούτυρο (gr)", 20);

        // Εξακρίβωση και προσαρμογή για 6 άτομα
        rm.adjustIngredientsForPeople(6);

        // Εμφάνιση συνταγής
        rm.displayRecipe();
    }
}