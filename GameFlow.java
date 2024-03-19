/**
 * Class GameFlow is responsible to put togher big parts of the entire programm and runs
 * all the levels with all the features as pause screen and countdown before each level starts,
 * level after level , fluidly
 */

import java.util.LinkedList;
import java.util.List;

import biuoop.GUI;


public class GameFlow {
    static int score = 0;

    /**
     * constructor function that is basicly super()
     */
    public GameFlow() {
    }

    /**
     * this functio does all the work
     * @param levels is a list of the order of all levels,
     * that we got by the user or the deafault run of the programm
     */
    public void runLevels(List<LevelInformation> levels) {
        Animation KeyPressStoppableAnimationObject;
        Animation endScreen;
        int count = 0;
        for (LevelInformation levelInfo : levels) {
            count++; //this counter is responsible to be the stop condition (number of levels played out of..)
            GameLevel level = new GameLevel(levelInfo, score);
            level.initialize();
            //running the each level until the player won or lost
            while (level.areThereMoreBalls() && level.areThereMoreBLocks()) {
                score = level.run();
            }
            if (!level.areThereMoreBalls()) {  //when the player lost
                endScreen = new EndScreen(score, false, level.getKeyboard(), level.getGui());
                KeyPressStoppableAnimationObject = new KeyPressStoppableAnimation(level.getKeyboard(),
                        biuoop.KeyboardSensor.SPACE_KEY, endScreen, level.getAnimationRunner());
                level.getAnimationRunner().run(KeyPressStoppableAnimationObject);
            }

            if (level.areThereMoreBalls()) {  //when the player won
                if (!(level.areThereMoreBLocks()) && count == levels.size()) {
                    endScreen = new EndScreen(score, true, level.getKeyboard(), level.getGui());
                    KeyPressStoppableAnimationObject = new KeyPressStoppableAnimation(level.getKeyboard(),
                            biuoop.KeyboardSensor.SPACE_KEY, endScreen, level.getAnimationRunner());
                    level.getAnimationRunner().run(KeyPressStoppableAnimationObject);
                }
            }
        }
    }
}