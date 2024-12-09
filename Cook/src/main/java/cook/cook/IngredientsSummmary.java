package cook.cook;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class IngredientsSummary {
    // Map για αποθήκευση συστατικών και ποσοτήτων
    private Map<String, Double> ingredients = new HashMap<>();

    // Προσθήκη συστατικού στη σύνοψη
    public void addIngredient(String name, double quantity) {
        ingredients.put(name, quantity);
    }

    // Επιστρέφει το σύνολο των συστατικών (key-value pairs)
    public Set<Map.Entry<String, Double>> entrySet() {
        return ingredients.entrySet();
    }

    // Εμφάνιση των συστατικών
    public void display() {
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}





