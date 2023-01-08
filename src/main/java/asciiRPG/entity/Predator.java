package asciiRPG.entity;

import java.awt.*;

public class Predator extends Entity implements NPC {

    private char glyph = 'P';
    private int health = 100;

    private int hunger = 0;
    private int thirst = 0;

    private Point location = new Point(0,0);

    private Desire currentDesire = Desire.SOCIALIZE;

    private void setDesire(Desire desire) {
        this.currentDesire = desire;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public char getGlyph() {
        return this.glyph;
    }

    int getHealth() {
        return this.health;
    }

    private void decideGoalForTurn() {
        double hungerRoll = hunger * Math.random();
        double thirstRoll = thirst * Math.random();
        double lowerRoll = Math.min(hungerRoll, thirstRoll);
        if (lowerRoll < 150) {
            setDesire(hungerRoll > thirstRoll ? Desire.DRINK : Desire.EAT);
        }
    }

    private void findNearbyWater() {

    }

    @Override
    public void takeTurn() {
        if (currentDesire == Desire.SOCIALIZE) {
            decideGoalForTurn();
        } else if (currentDesire == Desire.EAT) {

        } else if (currentDesire == Desire.DRINK) {
            findNearbyWater();
        }
    }

    @Override
    public void attack() {

    }
}
