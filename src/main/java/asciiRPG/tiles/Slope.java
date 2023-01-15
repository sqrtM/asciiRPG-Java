package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

import static asciiPanel.AsciiPanel.white;

public class Slope extends FloorTile {

    private final char glyph = '/';
    private final Color color = white;
    private final String name = "Slope";
    private final Double speedMod = 1.8;
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
    public void removeContents(int index) {
        this.contains.remove(index);
    }

}
