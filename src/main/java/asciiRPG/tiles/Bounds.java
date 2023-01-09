package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

import static asciiPanel.AsciiPanel.brightBlack;

public class Bounds implements FloorTile {

    private final char glyph = 'X';
    private final Color color = brightBlack;
    private final String name = "Bounds";
    private final Double speedMod = 0.0;
    private final ArrayList<Entity> contains = new ArrayList<>();


    public char getGlyph() {
        return this.glyph;
    }

    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public Double getSpeedMod() {
        return this.speedMod;
    }

    public ArrayList<Entity> getContains() {
        return this.contains;
    }

    public void setContains(Entity item) {
        this.contains.add(item);
    }

    public void removeContents(int index) {
        this.contains.remove(index);
    }
}
