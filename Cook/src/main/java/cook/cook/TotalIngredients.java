package cook.cook;

import java.util.HashMap;
import java.util.Map;


 // Κλάση για τη διαχείριση του συνολικού ποσού συστατικών.
 //Αποθηκεύει συστατικά και τις ποσότητές τους.
 
public class TotalIngredients {

    // Χάρτης που αποθηκεύει συστατικά και ποσότητες
    private Map<String, Double> ingredients;

    // Constructor: Αρχικοποίηση του χάρτη
    public TotalIngredients() {
        this.ingredients = new HashMap<>();
    }

    //Προσθέτει ή ενημερώνει την ποσότητα ενός συστατικού.
    //Αν το συστατικό υπάρχει ήδη, προστίθεται στην υπάρχουσα ποσότητα.
    
     
    public void put(String name, double quantity) {
        ingredients.put(name, ingredients.getOrDefault(name, 0.0) + quantity);
    }

    
    //Επιστρέφει την ποσότητα ενός συστατικού.
     
    public double get(String name) {
        return ingredients.getOrDefault(name, 0.0);
    }

    
     //Επιστρέφει τον χάρτη με όλα τα συστατικά και τις ποσότητές τους.
     
    public Map<String, Double> getAll() {
        return ingredients;
    }

    
    //Εκτυπώνει όλα τα συστατικά και τις ποσότητές τους.
     
    public void display() {
        System.out.println("\n=== Total Ingredients ===");
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
