package cook.cook;

public class CookFileParser {

    public static Recipe parse(String fileContent) {
        Recipe recipe = new Recipe();

        // Διαχωρίζουμε το περιεχόμενο σε γραμμές
        String[] lines = fileContent.split("\n");

        for (String line : lines) {
            System.out.println("Επεξεργασία γραμμής: " + line);  // Προσθήκη debug

            if (line.startsWith("@")) { // Επεξεργασία γραμμών που περιέχουν υλικά
                // Διαχωρίζουμε το όνομα του υλικού από την ποσότητα και τη μονάδα
                String[] parts = line.substring(1).split("\\{");
                String ingredient = parts[0];
                String[] valueUnit = parts[1].replace("}", "").split("%");

                System.out.println("Προσωρινή τιμή: " + valueUnit[0]);  // Προσθήκη debug

                // Ελέγχουμε αν υπάρχει το % και αφαιρούμε το ποσοστό
                double quantity = 0;
                try {
                    if (valueUnit.length > 1) {
                        // Αν υπάρχει το %, το αφαιρούμε και μετατρέπουμε την τιμή σε αριθμό
                        quantity = Double.parseDouble(valueUnit[0]);
                    } else {
                        // Αν δεν υπάρχει %, αναλαμβάνουμε τη μετατροπή κανονικά
                        quantity = Double.parseDouble(valueUnit[0]);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Σφάλμα κατά την μετατροπή τιμής: " + e.getMessage());
                }

                // Ορισμός μονάδας μέτρησης, αν υπάρχει
                String unit = valueUnit.length > 1 ? "%" : null;

                // Προσθήκη του υλικού στη συνταγή
                recipe.addIngredient(ingredient, quantity, unit);
            } else if (line.startsWith("#")) { // Επεξεργασία γραμμών που περιέχουν σκεύη
                // Αφαίρεση του "#" και προσθήκη του σκεύους στη συνταγή
                recipe.addUtensil(line.substring(1).trim());
            } else if (line.startsWith("~")) { // Επεξεργασία γραμμών που περιέχουν βήματα με χρόνο
                // Διαχωρίζουμε το βήμα από το χρόνο
                String[] parts = line.substring(1).split("\\{");
                String step = parts[0].trim();
                String timeLine = line.substring(1).trim();

                // Χρησιμοποιούμε κανονικές εκφράσεις για να αφαιρέσουμε το ποσοστό και να αφήσουμε μόνο τον αριθμό
                String timeStr = parts[1].replace("λεπτά", "").replace("}", "").trim();
                timeStr = timeStr.replace("%", ""); // Αφαιρούμε το ποσοστό

                // Μετατροπή της χρονικής τιμής σε long
                long time = 0;
                try {
                    time = Long.parseLong(timeStr);
                } catch (NumberFormatException e) {
                    System.err.println("Σφάλμα κατά την μετατροπή του χρόνου: " + e.getMessage());
                }
                //Συσχέτισε τον χρόνο με το τελευταίο βήμα που προστέθηκε
                if (!recipe.getSteps().isEmpty()){
                    String lastStep = recipe.getSteps().get(recipe.getSteps().size() - 1);
                    recipe.addTimer(lastStep, time);
                }else {
                    System.err.println("Δέν βρέθηκε προηγούμενο βήμα για σύνδεσγ με τον χρόνο: " + time + "λεπτά.");
                }
                // Προσθήκη του βήματος και του χρονόμετρου στη συνταγή
                recipe.addStep(step);
                recipe.addTimer(step, time);
            } else { // Επεξεργασία γενικών βημάτων συνταγής
                // Προσθήκη του βήματος στη συνταγή
                recipe.addStep(line.trim());
            }
        }
        // Επιστροφή της συνταγής με όλα τα δεδομένα
        return recipe;
    }
}
