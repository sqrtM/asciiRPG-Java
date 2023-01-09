package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

import static asciiPanel.AsciiPanel.yellow;

public class Shore implements FloorTile {

    private final char glyph = ':';
    private final Color color = yellow;
    private final String name = "Shore";
    private final Double speedMod = 1.2;
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
