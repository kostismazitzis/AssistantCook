package cook.cook;

import java.util.HashMap;
import java.util.Map;

public class ShoppingList {
    private final Map<String, Double> shoppingItems = new HashMap<>();

    // Πρόσθεση υλικών στη λίστα αγορών από μία συνταγή
    public void addIngredients(Map<String, Double> recipeIngredients) {
        for (Map.Entry<String, Double> entry : recipeIngredients.entrySet()) {
            String ingredient = entry.getKey();
            double quantity = entry.getValue();

            shoppingItems.put(ingredient, shoppingItems.getOrDefault(ingredient, 0.0) + quantity);
        }
    }

    // Εκτύπωση συγκεντρωτικής λίστας αγορών
    public void printList() {
        System.out.println("Λίστα αγορών:");
        for (Map.Entry<String, Double> entry : shoppingItems.entrySet()) {
            System.out.printf("- %s: %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        Map<String, Double> recipe1 = Map.of( // Συνταγή 1
            "Αλεύρι (gr)", 500.0,
            "Ζάχαρη (gr)", 200.0,
            "Γάλα (ml)", 250.0
        );

        Map<String, Double> recipe2 = Map.of( // Συνταγή 2
            "Αλεύρι (gr)", 300.0,
            "Ζάχαρη (gr)", 100.0,
            "Βούτυρο (gr)", 200.0
        );

        Map<String, Double> recipe3 = Map.of( // Συνταγή 3
            "Γάλα (ml)", 500.0,
            "Βούτυρο (gr)", 100.0,
            "Αυγά", 6.0
        );

        ShoppingList list = new ShoppingList(); // Δημιουργία λίστας
        // Προσθήκη συνταγών στη λίστα
        list.addIngredients(recipe1);
        list.addIngredients(recipe2);
        list.addIngredients(recipe3);

        list.printList(); // Εκτύπωση λίστας
    }
}

