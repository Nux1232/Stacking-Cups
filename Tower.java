
import java.util.ArrayList;
import java.awt.Color;

/**
 * A tower that has cups and lids.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Tower {
    private int maxHeight;
    private int width;
    private boolean isVisible;
    private ArrayList<Object> items;
    
    public Tower(int width, int maxHeight) {
        this.maxHeight = maxHeight;
        this.width = width;
        this.isVisible = false;
        this.items = new ArrayList<Object>();
    }
    
    /**
     * Adds a cup to the tower.
     * @param An integer that determines the idSize of the cup (Cup number and lid number must be equals).
     */
    public void pushCup(int i) {
        Cup cup = new Cup();
        items.add(cup);
    }
    
    /**
     * Deletes the cup from the top of the tower.
     */
    public void popCup() {
        
    }
    
    /**
     * Deletes a cup from the tower.
     */
    public void removeCup() {
        
    }
    
    /**
     * Adds a lid to the cup.
     * @param An integer that determines the lid (Cup number and Lid number must be equals).
     */
    public void pushLid(int i) {
    }
    
    /**
     * Deletes the lid from the top of the tower.
     */
    public void popLid() {
        
    }
    
    /**
     * Deletes a specific lid from the tower.
     */
    public void removeLid() {
        
    }
    
    /**
     * Orders the tower from highest to lowest.
     */
    public void orderTower() {
        
    }
    
    /**
     * Reverses the tower from lowest to highest.
     */
    public void reverseTower() {
        
    }
    
    /**
     * Indicates the height of the tower.
     */
    public int height() {
        
    }
    
    /**
     * Returns the amount of cups that have a lid, ordered from lowest to highest.
     * @return A list of integers.
     */
    public int[] lidedCups() {
        
    }
    
    /**
     * Orders from base to top in lowercase.
     */
    public String[][] stackingItems() {
        
    }
    
    /**
     * Makes visible the tower. If it was visible does nothing.
     */
    public void makeVisible() {
        erase();
        isVisible = true;
    }
    
    /**
     * Makes invisible the tower. If it was invisible does nothing.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }
    
    /**
     * Exits from the simulator.
     */
    public void exit() {
        
    }
    
    /**
     * Indicates if the last operation could be done.
     * @return True or False.
     */
    public boolean ok() {
        
    }
    
    /*
     * Draw the tower with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(maxHeight, width));
            canvas.wait(10);
        }
    }

    /*
     * Erase the tower on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
