package cook.cook;
import java.util.regex.Matcher;

public class MainPattern {
    public static void main(String[] args) {
        try {
            // Δημιουργία αντικειμένου Pattern
            Pattern pattern = Pattern.compile("\\d+"); // Regex για αριθμούς

            // Δημιουργία Matcher για να βρει αντιστοιχίες σε συμβολοσειρά
            String input = "Η θερμοκρασία είναι 23 βαθμοί.";
            Matcher matcher = pattern.matcher(input);

            // Εμφάνιση όλων των αντιστοιχιών
            while (matcher.find()) {
                System.out.println("Βρέθηκε αριθμός: " + matcher.group());
            }
        } catch (Exception e) {
            System.err.println("Σφάλμα: " + e.getMessage());
        }
    }
}


