package asciiRPG.entity;

import asciiRPG.tiles.FloorTile;

import java.util.Objects;
import java.util.Random;

public class Predator extends Entity implements NPC {

    private FloorTile tile;
    private char glyph = 'P';
    private int health = 100;

    private int hunger = 0;
    private int thirst = 0;

    private double lastTileSeed = 0;

    private Location location;

    private Desire currentDesire = Desire.SOCIALIZE;
    private String lastDirection = "";

    public Predator(int x, int y, FloorTile tile) {
        this.location = new Location(x, y);
        this.tile = tile;
    }

    public FloorTile getTile() {
        this.lastTileSeed = this.tile.getSeed();
        return this.tile;
    }

    public void setTile(FloorTile tile) {
        this.tile = tile;
    }

    public void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    @Override
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
        if (lowerRoll > 800) {
            setDesire(hungerRoll > thirstRoll ? Desire.DRINK : Desire.SOCIALIZE);
        }
    }

    Random rand = new Random();
    private void walkRandomly() {
        int roll = rand.nextInt((10 - 1) + 1) + 1;
        if (getLocation().getX() > 1 && getLocation().getY() > 1) {
            switch (roll) {
                case 1 -> goEast();
                case 2 -> goWest();
                case 3 -> goNorth();
                case 4 -> goSouth();
                default -> {
                }
            }
        }
    }

    private void findNearbyWater() {
        if (!Objects.equals(this.tile.getName(), "Water")) {
            if (lastTileSeed > this.tile.getSeed()) {
                walkRandomly();
            } else {
                switch (this.lastDirection) {
                    case "East" -> goEast();
                    case "West" -> goWest();
                    case "North" -> goNorth();
                    case "South" -> goSouth();
                    default -> walkRandomly();
                }
            }
        } else {
            this.setThirst(this.thirst - 50);
            this.setDesire(Desire.SOCIALIZE);
        }
    }

    private void goEast() {
        setLocation(getLocation().getX() + 1, getLocation().getY());
        this.lastDirection = "East";
    }

    private void goWest() {
        setLocation(getLocation().getX() - 1, getLocation().getY());
        this.lastDirection = "West";
    }
    private void goNorth() {
        setLocation(getLocation().getX(), getLocation().getY() + 1);
        this.lastDirection = "North";
    }
    private void goSouth() {
        setLocation(getLocation().getX(), getLocation().getY() - 1);
        this.lastDirection = "South";
    }

    @Override
    public void takeTurn() {
        System.out.println(location.getX());

        setHunger(this.hunger + 1);
        setThirst(this.thirst + 4);
        if (currentDesire == Desire.SOCIALIZE) {
            this.glyph = 'P';
            walkRandomly();
            decideGoalForTurn();
        } else if (currentDesire == Desire.EAT) {
            // just for now
            walkRandomly();
        } else if (currentDesire == Desire.DRINK) {
            this.glyph = 'T';
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
