package asciiRPG;

import java.awt.Color;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;
import asciiRPG.entity.Entity;

/* TODO - the code as is works exactly as intended. the reason that Ps show up everywhere
    is because this enum is just creating one version of each of these and copying it.
    what we need to do is have a separate class for each of these, each with their own
    "contains" array. that will allow for each tile to have its own behavior, completely
    undisturbed by any of the other tiles.
*/

public enum Tile {
    WATER(
            (char) 0xF7,
            AsciiPanel.brightBlue.brighter(),
            "Water",
            4.0,
            new ArrayList<>()
    ),
    SHORE(
            ':',
            AsciiPanel.yellow,
            "Shore",
            1.2,
            new ArrayList<>()
    ),
    FLOOR(
            (char)250,
            AsciiPanel.green.darker(),
            "Floor",
            1.0,
            new ArrayList<>()
    ),
    SLOPE(
            '/',
            AsciiPanel.white,
            "Slope",
            1.8,
            new ArrayList<>()
    ),
    MOUNTAIN(
            '^',
            AsciiPanel.white.darker().darker(),
            "Wall",
            3.0,
            new ArrayList<>()
    ),
    BOUNDS(
            'X',
            AsciiPanel.brightBlack,
            "Bounds",
            0.0,
            new ArrayList<>()
    );

    private final char glyph;
    public char getGlyph() { return glyph; }

    private final Color color;
    public Color getColor() { return color; }

    private final String name;
    public String getName() { return name; }

    private final Double speedMod;
    public Double getSpeedMod() { return speedMod; }

    private final ArrayList<Entity> contains;
    public ArrayList<Entity> getContains() { return contains; }
    public void setContains(Entity item) { contains.add(item); }

    Tile(char glyph, Color color, String name, Double speedMod, ArrayList<Entity> contains){
        this.glyph = glyph;
        this.color = color;
        this.name = name;
        this.speedMod = speedMod;
        this.contains = contains;
    }
}
