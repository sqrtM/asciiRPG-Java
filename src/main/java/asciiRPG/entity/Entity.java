package asciiRPG.entity;

import asciiRPG.tiles.FloorTile;

public abstract class Entity {
    char glyph = 'C';
    int health;

    public abstract char getGlyph();
    abstract int getHealth();

    public abstract void takeTurn();

    public Predator.Location getLocation() {
        return null;
    }

    public void setTile(FloorTile floorTile) {
    }
}
