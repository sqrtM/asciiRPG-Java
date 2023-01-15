package asciiRPG.entity;

import java.util.Random;

public class Predator extends Entity implements NPC {

    private char glyph = 'P';
    private int health = 100;

    private int hunger = 0;
    private int thirst = 0;

    private Location location;

    private Desire currentDesire = Desire.SOCIALIZE;

    public Predator(int x, int y) {
        this.location = new Location(x, y);
    }

    public void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    public Location getLocation() {
        return location;
    }

    private void setDesire(Desire desire) {
        this.currentDesire = desire;
    }

    private void setHunger(int hunger) {
        this.hunger = hunger;
    }

    private void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public char getGlyph() {
        return this.glyph;
    }

    int getHealth() {
        return this.health;
    }

    private void decideGoalForTurn() {
        double hungerRoll = hunger / Math.random();
        double thirstRoll = thirst / Math.random();
        double lowerRoll = Math.min(hungerRoll, thirstRoll);
        if (lowerRoll > 400) {
            setDesire(hungerRoll > thirstRoll ? Desire.DRINK : Desire.EAT);
        }
    }

    Random rand = new Random();
    private void findNearbyWater() {
        int roll = rand.nextInt((4 - 1) + 1) + 1;
        if (getLocation().getX() > 1 && getLocation().getY() > 1) {
            switch (roll) {
                case 1 -> setLocation(getLocation().getX() + 1, getLocation().getY());
                case 2 -> setLocation(getLocation().getX() - 1, getLocation().getY());
                case 3 -> setLocation(getLocation().getX(), getLocation().getY() + 1);
                case 4 -> setLocation(getLocation().getX(), getLocation().getY() - 1);
            }
        }
    }

    @Override
    public void takeTurn() {
        setHunger(this.hunger + 1);
        setThirst(this.thirst + 4);
        if (currentDesire == Desire.SOCIALIZE) {
            decideGoalForTurn();
        } else if (currentDesire == Desire.EAT) {
            // just for now
        } else if (currentDesire == Desire.DRINK) {
            findNearbyWater();
        }
    }

    @Override
    public void attack() {

    }

    public static class Location {

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int x;
        private int y;

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
