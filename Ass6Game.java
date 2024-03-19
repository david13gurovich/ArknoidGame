import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

/**
 * @Author: David Gurovich
 * ID: 318572047
 * assighment number : 6
 * class: OOP
 * this class is only responsible for running the entire game
 */
public class Ass6Game {


    /**
     * main function that runs the game
     * @param args is an array of levels that the user wants to play
     */
    public static void main(String[] args) {
        List<LevelInformation> allLevels = new LinkedList<LevelInformation>();
        String[] stringArguments = new String[args.length];
        stringArguments = args;
        if (args.length == 0) {
            allLevels.add(new LevelOne());
            allLevels.add(new LevelTwo());
            allLevels.add(new LevelThree());
            allLevels.add(new LevelFour());
        } else {
            for (String argument : stringArguments) {
                try {
                    int levelNumber = Integer.parseInt(argument);
                    if (levelNumber == 1)
                        allLevels.add(new LevelOne());
                    if (levelNumber == 2)
                        allLevels.add(new LevelTwo());
                    if (levelNumber == 3)
                        allLevels.add(new LevelThree());
                    if (levelNumber == 4)
                        allLevels.add(new LevelFour());
                }catch(Exception e)
                {
                    continue;
                }
            }
        }
        GameFlow game = new GameFlow();
        game.runLevels(allLevels);
    }
}

