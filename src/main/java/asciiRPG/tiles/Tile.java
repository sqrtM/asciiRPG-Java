package asciiRPG.tiles;

import asciiRPG.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

public interface Tile {

    char getGlyph();
    Color getColor();

    String getName();

    ArrayList<Entity> getContains();


}
