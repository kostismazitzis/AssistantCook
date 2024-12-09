package cook.cook;

import java.util.Map;

/**
 * Κλάση που αναπαριστά ένα βήμα συνταγής.
 * Περιλαμβάνει περιγραφή, απαιτούμενα συστατικά με ποσότητες και χρόνο εκτέλεσης.
 */
public class Step {

    // Περιγραφή του βήματος (π.χ., "Κόβουμε τα λαχανικά.")
    private String description;

    // Χάρτης που αντιστοιχεί συστατικά (όνομα) με τις ποσότητές τους (π.χ., "Αλεύρι" -> 200.0)
    private Map<String, Double> ingredients;

    // Χρόνος εκτέλεσης του βήματος σε λεπτά (π.χ., 10.0 για 10 λεπτά)
    private double time;

    //Επιστρέφει την περιγραφή του βήματος.
    public String getDescription() {
        return description;
    }

     //Επιστρέφει τα συστατικά και τις ποσότητές τους που απαιτούνται για το βήμα.
    public Map<String, Double> getIngredients() {
        return ingredients;
    }

    
     //Επιστρέφει τον χρόνο εκτέλεσης του βήματος.
     public double getTime() {
        return time;
    }

    //Θέτει την περιγραφή του βήματος.
    public void setDescription(String description) {
        this.description = description;
    }

     //Θέτει τα συστατικά και τις ποσότητές τους για το βήμα.
    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }

    //Θέτει τον χρόνο εκτέλεσης του βήματος.
    public void setTime(double time) {
        this.time = time;
    }

    //Επιστρέφει το βήμα σε μορφοποιημένη συμβολοσειρά.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Description: ").append(description).append("\n");
        sb.append("Ingredients: \n");
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            sb.append(" - ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("Time: ").append(time).append(" minutes\n");
        return sb.toString();
    }
}
