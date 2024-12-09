package cook.cook;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

class Pattern {

    private String regex;

    // Στατική μέθοδος για τη δημιουργία ενός αντικειμένου Pattern
    static Pattern compile(String regex) throws PatternSyntaxException {
        if (regex == null || regex.isEmpty()) {
            throw new IllegalArgumentException("Το regex δεν μπορεί να είναι null ή κενό.");
        }
        Pattern pattern = new Pattern();
        pattern.regex = regex;
        return pattern;
    }

    // Μέθοδος που επιστρέφει έναν Matcher για μια συμβολοσειρά
    Matcher matcher(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Η συμβολοσειρά δεν μπορεί να είναι null.");
        }
        // Χρησιμοποιούμε την έτοιμη κλάση της Java `java.util.regex.Pattern`
        java.util.regex.Pattern javaPattern = java.util.regex.Pattern.compile(regex);
        return javaPattern.matcher(line);
    }

    // Επιστροφή του regex που έχει αποθηκευτεί
    public String getRegex() {
        return regex;
    }
}

