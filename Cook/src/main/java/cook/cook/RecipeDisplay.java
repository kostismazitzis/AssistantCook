package cook.cook;
import java.util.Map;
import java.util.Set;

/**
 * Κλάση υπεύθυνη για την εμφάνιση συνταγών με επιπλέον δυνατότητες.
 */
public class RecipeDisplay {


     //Εμφανίζει λεπτομέρειες συνταγής, προσαρμοσμένες στις μερίδες.
    public static void displayRecipe(Recipe recipe, int servingsMultiplier) {
        // Εμφάνιση Υλικών
        System.out.println("\n=== Λίστα Υλικών ===");
        Map<String, Double> ingredientSummary = recipe.calculateTotalIngredients(servingsMultiplier);

        // Έλεγχος αν υπάρχουν υλικά για εμφάνιση
        if (ingredientSummary == null || ingredientSummary.isEmpty()) {
            System.out.println("Δεν υπάρχουν υλικά.");
        } else {
            // Εκτύπωση των υλικών και των ποσοτήτων
            for (Map.Entry<String, Double> entry : ingredientSummary.entrySet()) {
                String unit = recipe.getUnit(entry.getKey());
                
                // Αν δεν υπάρχει μονάδα για το υλικό, θέτουμε την προεπιλεγμένη "μονάδες"
                if (unit == null) {
                    unit = "μονάδες"; 
                }
                System.out.printf(" - %s: %.2f %s%n", entry.getKey(), entry.getValue(), unit);
            }
        }


        // Εμφάνιση Σκευών
        System.out.println("\n=== Σκεύη ===");
        Set<String> utensils = recipe.getRequiredUtensils();
        
        // Αν υπάρχουν σκεύη, τα εμφανίζουμε
        if (utensils != null && !utensils.isEmpty()) {
            for (String utensil : utensils) {
                System.out.printf(" - %s%n", utensil);
            }
        } else {
            // Αν δεν υπάρχουν σκεύη, εμφανίζουμε το μήνυμα
            System.out.println(" - Δεν απαιτούνται ειδικά σκεύη.");
        }

        // Εμφάνιση Βημάτων
        System.out.println("\n=== Βήματα Συνταγής ===");
        Iterable<String> steps = recipe.getSteps();
        
        // Έλεγχος αν υπάρχουν βήματα και εμφάνιση τους
        if (steps != null && steps.iterator().hasNext()) {
            for (String step : steps) {
                System.out.println(" - " + step);
            }
        } else {
            // Αν δεν υπάρχουν βήματα, εμφανίζουμε το μήνυμα
            System.out.println("Δεν υπάρχουν βήματα για αυτή τη συνταγή.");
        }
    }
}