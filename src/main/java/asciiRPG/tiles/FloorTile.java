package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

public abstract class FloorTile implements Tile {

    public abstract char getGlyph();

    public abstract Color getColor();

    public abstract String getName();

    public abstract ArrayList<Entity> getContains();

    public abstract Double getSpeedMod();

    public abstract void setContains(Entity item);

    public abstract void removeContents(Entity index);

    public abstract double getSeed();
}
