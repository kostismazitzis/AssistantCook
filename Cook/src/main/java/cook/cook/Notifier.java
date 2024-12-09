package cook.cook;
//Διεπαφή για την ειδοποίηση όταν ολοκληρωθεί μια συνταγή
public interface Notifier {
    void finished(Recipe recipe);
}
