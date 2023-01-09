package asciiRPG.tiles;

import asciiRPG.entity.Entity;

public interface FloorTile extends Tile {

    Double getSpeedMod();
    void setContains(Entity item);
    void removeContents(int index);
}
