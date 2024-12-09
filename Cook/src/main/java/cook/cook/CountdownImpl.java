package cook.cook;
import cook.cook.Countdown;
import gr.hua.dit.oop2.countdown.Notifier;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownImpl implements Countdown {
    private final String name; // Όνομα της αντίστροφης μέτρησης
    private long seconds; // Υπόλοιπο δευτερόλεπτα για την αντίστροφη μέτρηση
    private Timer timer; // Χρησιμοποιείται για τον προγραμματισμό της αντίστροφης μέτρησης
    private final Set<Notifier> notifiers = new HashSet<>(); // Συλλογή των παρατηρητών (Notifiers)
    private boolean running = false; // Κατάσταση λειτουργίας της αντίστροφης μέτρησης
    
    //Αρχικοποίηση αντιίστροφης μέτρησης με όνομα και διάρκεια
    public CountdownImpl(String name, long seconds){
        this.name=name;
        this.seconds=seconds;
    }
    
    // Ξεκινά την αντίστροφη μέτρηση
    @Override
    public void start() {
        // Έλεγχος αν η αντίστροφη μέτρηση είναι ήδη ενεργή ή αν δεν υπάρχει χρόνος
        if (running || seconds <= 0) return; 
        running = true; // Ενημέρωση της κατάστασης
        timer = new Timer(); // Δημιουργία του χρονοδιακόπτη

        // Προγραμματισμός της αντίστροφης μέτρησης
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds > 0) {
                    seconds--; // Μείωση του υπολειπόμενου χρόνου κατά 1 δευτερόλεπτο
                } else {
                    stop(); // Διακοπή της αντίστροφης μέτρησης όταν φτάσει στο 0
                    notifyAllNotifiers(); // Ενημέρωση όλων των παρατηρητών
                }
            }
        }, 0, 1000); // Εκτέλεση κάθε δευτερόλεπτο
    }

    // Διακόπτει την αντίστροφη μέτρηση
    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel(); // Ακύρωση του χρονοδιακόπτη
            timer = null;
        }
        running = false; // Ενημέρωση της κατάστασης
    }

    // Επιστρέφει τον υπόλοιπο χρόνο της αντίστροφης μέτρησης σε δευτερόλεπτα
    @Override
    public long secondsRemaining() {
        return seconds;
    }

    // Προσθέτει έναν παρατηρητή (Notifier) στη συλλογή
    // Ο παρατηρητής θα ειδοποιηθεί όταν ολοκληρωθεί η αντίστροφη μέτρηση
    @Override
    public void addNotifier(Notifier notifier) {
        notifiers.add(notifier); // Προσθήκη του παρατηρητή στη συλλογή
    }

    // Επιστρέφει το όνομα της αντίστροφης μέτρησης
    @Override
    public String getName() {
        return name;
    }

    // Ενημερώνει όλους τους καταχωρημένους παρατηρητές ότι η αντίστροφη μέτρηση ολοκληρώθηκε
    private void notifyAllNotifiers() {
        for (Notifier notifier : notifiers) {
            notifier.finished((gr.hua.dit.oop2.countdown.Countdown) this); // Κλήση της μεθόδου finished κάθε παρατηρητή
        }
    }
}
