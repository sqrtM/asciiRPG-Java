package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

import static asciiPanel.AsciiPanel.brightBlack;

public class Bounds extends FloorTile {

    private final char glyph = 'X';
    private final Color color = brightBlack;
    private final String name = "Bounds";
    private final Double speedMod = 0.0;
    private final ArrayList<Entity> contains = new ArrayList<>();

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

    @Override
    public double getSeed() {
        return 0;
    }

}
