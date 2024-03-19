/**
 * class HitNotifier an interface that defines any object to be sensetive for each hit from a ball
 */

public interface HitNotifier {

    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}