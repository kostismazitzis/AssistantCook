package cook.cook;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        String fileName = "recipe.cook";

        // Δημιουργία και Αποθήκευση Συνταγής
        String recipeContent = """
                @Αυγά{3}
                @Αλεύρι{125%gr}
                @Γάλα{250%ml}
                @Αλάτι{1}
                @Βούτυρο{20%gr}
                #Μπλέντερ
                #Μπολ
                #Μεγάλο αντικολλητικό τηγάνι
                ~{15%λεπτά}
                Σπάστε τα αυγά σε ένα μπλέντερ, προσθέστε το αλεύρι, το γάλα και το αλάτι και χτυπήστε μέχρι να γίνει λείο.
                ~{15%λεπτά}
                Ρίξτε το μείγμα σε ένα μπολ και αφήστε το να σταθεί για 15 λεπτά.
                ~{5%λεπτά}
                Λιώστε το βούτυρο σε ένα μεγάλο αντικολλητικό τηγάνι σε μέτρια φωτιά, γείρετε το τηγάνι ώστε να καλυφθεί η επιφάνεια με το βούτυρο.
                ~{2%λεπτά}
                Ρίξτε μία κουτάλα από το μείγμα στο τηγάνι και γείρετε το τηγάνι για να απλωθεί το μείγμα, μαγειρέψτε για 2 λεπτά.
                ~{1%λεπτά}
                Μόλις χρυσίσει από κάτω, γυρίστε την κρέπα και μαγειρέψτε για 1 λεπτό.
                """; 
        // Αποθήκευση της συνταγής στο αρχείο
        Cook.writeRecipeToFile(fileName, recipeContent);

        // Ανάγνωση και Επεξεργασία Συνταγής
        try {
            String cookFileContent = Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
            Recipe parsedRecipe = CookFileParser.parse(cookFileContent);

            // Εμφάνιση Συνταγής
            RecipeDisplay.displayRecipe(parsedRecipe, 1); // Χωρίς αλλαγή στις μερίδες

            //Εκτέλεση Συνταγής
            RecipeExecution.executeRecipe(parsedRecipe);
        } catch (Exception e) {
            System.err.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
        }
    }
}

