/**
 * class Animation is an interface that defines any object
 * to handle an animation that runs on the screen
 */

import biuoop.DrawSurface;


public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}