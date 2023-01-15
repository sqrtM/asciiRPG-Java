package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

import static asciiPanel.AsciiPanel.yellow;

public class Shore extends FloorTile {

    private final char glyph = ':';
    private final Color color = yellow;
    private final String name = "Shore";
    private final Double speedMod = 1.2;
    private final ArrayList<Entity> contains = new ArrayList<>();
    private final double seed;

    public Shore(double seed) {
        this.seed = seed;
    }

    public double getSeed() {
        return seed;
    }

    @Override
    public char getGlyph() {
        return this.glyph;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<Entity> getContains() {
        return this.contains;
    }

    @Override
    public Double getSpeedMod() {
        return this.speedMod;
    }

    @Override
    public void setContains(Entity item) {
        this.contains.add(item);
    }

    @Override
    public void removeContents(Entity index) {
        this.contains.remove(index);
    }

}
