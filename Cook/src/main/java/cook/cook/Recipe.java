package cook.cook;

import java.util.*;

// Αντιπροσωπεύει μια συνταγή που περιλαμβάνει υλικά, σκεύη, βήματα και μερίδες
public class Recipe {
    private final Map<String, Double> ingredients; // Χάρτης με υλικά και ποσότητες
    private final Map<String, String> units;       // Χάρτης με μονάδες μέτρησης ανά υλικό
    private final Set<String> utensils;           // Σύνολο απαιτούμενων σκευών
    private final List<String> steps;             // Λίστα με βήματα συνταγής
    private final Map<String, Long> stepTimers;   // Χάρτης για βήματα και χρόνους
    private final int baseServings;               // Αρχικός αριθμός μερίδων

    // Κατασκευαστής που αρχικοποιεί όλα τα πεδία της συνταγής
    public Recipe() {
        this.ingredients = new HashMap<>();
        this.units = new HashMap<>();
        this.utensils = new HashSet<>();
        this.steps = new ArrayList<>();
        this.stepTimers = new HashMap<>();
        this.baseServings = 1; // Προεπιλεγμένες μερίδες
    }

    // Υπολογισμός συνολικών υλικών για τον αριθμό μερίδων που καθορίζεται
    public Map<String, Double> calculateTotalIngredients(int servingsMultiplier) {
        Map<String, Double> totalIngredients = new HashMap<>();
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            String ingredient = entry.getKey();
            double quantity = entry.getValue();
            totalIngredients.put(ingredient, quantity * servingsMultiplier / baseServings); // Κλιμάκωση της ποσότητας
        }
        return totalIngredients;
    }

    public Map<String, Long> getStepTimers() {
        return stepTimers;
    }


    // Λήψη της μονάδας μέτρησης για συγκεκριμένο υλικό
    public String getUnit(String key) {
        return units.getOrDefault(key, "μονάδες"); // Επιστροφή μονάδας ή προεπιλογή αν δεν υπάρχει
    }

    // Επιστροφή του συνόλου των απαιτούμενων σκευών
    public Set<String> getRequiredUtensils() {
        return utensils;
    }

    // Επιστροφή των βημάτων της συνταγής
    public List<String> getSteps()
    {
        return steps;
    }

    // Εμφάνιση της συνταγής με όλα τα στοιχεία της
    public void display(int servingsMultiplier) {
        System.out.println("Συνταγή για " + servingsMultiplier + " μερίδες:");

        System.out.println("\nΥλικά:");
        Map<String, Double> totalIngredients = calculateTotalIngredients(servingsMultiplier);
        for (Map.Entry<String, Double> entry : totalIngredients.entrySet()) {
            String ingredient = entry.getKey();
            double quantity = entry.getValue();
            String unit = getUnit(ingredient);
            System.out.printf("- %s: %.2f %s%n", ingredient, quantity, unit);
        }

        System.out.println("\nΣκεύη:");
        for (String utensil : utensils) {
            System.out.printf("- %s%n", utensil);
        }

        System.out.println("\nΒήματα:");
        int stepNumber = 1;
        for (String step : steps) {
            long time = stepTimers.getOrDefault(step, 0L);
            if (time > 0) {
                System.out.printf("%d. %s (Χρόνος: %d λεπτά)%n", stepNumber++, step, time);
            } else {
                System.out.printf("%d. %s%n", stepNumber++, step);
            }
        }
    }

    // Προσθήκη υλικού στη συνταγή
    public void addIngredient(String ingredient, double quantity, String unit) {
        ingredients.put(ingredient, quantity);
        if (unit != null) {
            units.put(ingredient, unit);
        }
    }

    // Προσθήκη σκεύους στη συνταγή
    public void addUtensil(String utensil) {
        utensils.add(utensil);
    }

    // Προσθήκη βήματος στη συνταγή
    public void addStep(String step) {
        steps.add(step);
    }

    // Προσθήκη χρόνου σε συγκεκριμένο βήμα
    public void addTimer(String step, long time) {
        if (!steps.contains(step)) {
            throw new IllegalArgumentException("Το βήμα δεν υπάρχει στη συνταγή: " + step);
        }
        stepTimers.put(step, time); // Προσθήκη του χρόνου στο χάρτη
    }
}