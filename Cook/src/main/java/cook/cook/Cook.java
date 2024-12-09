package cook.cook;

import gr.hua.dit.oop2.Countdown;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Cook {
    public static void main(String[] args) {
        System.out.println("Η εφαρμογή Cook ξεκίνησε!");

        // Χρήση της εξάρτησης Countdown
        Countdown countdown = new Countdown(10); // Αρχικό όριο 10 δευτερόλεπτα
        countdown.start();

        System.out.println("Countdown ολοκληρώθηκε!");

        // Δημιουργία συνταγής
        String fileName = "recipe.cook";
        String recipeContent = """
                @Αυγά{3}
                @Αλεύρι{125%gr}
                @Γάλα{250%ml}
                @Αλάτι{1}
                @Βούτυρο{20%gr}
                """; 

        // Αποθήκευση συνταγής στο αρχείο
        writeRecipeToFile(fileName, recipeContent);
    }

    // Μέθοδος για την αποθήκευση της συνταγής στο αρχείο
    public static void writeRecipeToFile(String fileName, String recipeContent) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            writer.write(recipeContent);
            System.out.println("Η συνταγή αποθηκεύτηκε στο αρχείο: " + fileName);
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά τη δημιουργία του αρχείου: " + e.getMessage());
        }
    }
}
