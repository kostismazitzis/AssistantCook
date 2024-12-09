package gr.hua.dit.oop2;

public class Countdown {
    private final int seconds;

    public Countdown(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("Ο αριθμός των δευτερολέπτων πρέπει να είναι μεγαλύτερος του 0.");
        }
        this.seconds = seconds;
    }

    public void start() {
        try {
            for (int i = seconds; i > 0; i--) {
                System.out.println("Αντίστροφη μέτρηση: " + i + " δευτερόλεπτα...");
                Thread.sleep(1000); // Αναμονή 1 δευτερόλεπτου
            }
        } catch (InterruptedException e) {
            System.err.println("Η αντίστροφη μέτρηση διακόπηκε: " + e.getMessage());
        }
    }
}
