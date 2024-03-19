/**
 * Class Counter is creating objects that help to keep scoring whatever we like
 */

public class Counter {

    private int value;

    //builder function
    public Counter(int value)
    {
        this.value = value;
    }
    // add number to current count.
    public void increase(int number){
        this.value += number;
    }
    // subtract number from current count.
    public void decrease(int number){
        this.value -= number;
    }
    // get current count.
    public int getValue(){
        return this.value;
    }
}