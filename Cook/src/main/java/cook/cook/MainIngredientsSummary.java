package cook.cook;

import java.util.Map;

public class MainIngredientsSummary {
    // Κύρια μέθοδος που εκτελεί το πρόγραμμα
    public static void main(String[] args) {
        // Δημιουργία αντικειμένου της κλάσης IngredientsSummary
        IngredientsSummary summary = new IngredientsSummary();
        // Προσθήκη συστατικών και των ποσοτήτων τους στη σύνοψη 
        summary.addIngredient("Ζάχαρη", 200.0);
        summary.addIngredient("Αλεύρι", 500.0);
        // Επανάληψη μέσα από το σύνολο των συστατικών
        for (Map.Entry<String, Double> entry : summary.entrySet()) {
           // Εκτύπωση του ονόματος του συστατικού και της ποσότητας
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
