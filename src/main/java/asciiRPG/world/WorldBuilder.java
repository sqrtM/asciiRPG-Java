package asciiRPG.world;

import asciiRPG.entity.Entity;
import asciiRPG.entity.Predator;
import asciiRPG.tiles.*;

import java.util.ArrayList;

public class WorldBuilder {
    private int width;
    private int height;
    private FloorTile[][] tiles;
    private final ArrayList<Entity> entities = new ArrayList<>();

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new FloorTile[width][height];
    }

    public World build() {
        return new World(tiles, entities);
    }

    private WorldBuilder randomizeTiles() {
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double seed = noiseGenerator.noise(x, y);
                if (seed < -0.1) {
                    tiles[x][y] = new Water();
                } else if (seed < 0.05) {
                    tiles[x][y] = new Shore();
                } else if (seed < 0.3) {
                    tiles[x][y] = new Floor();
                    if (Math.random() < 0.01) {
                        Predator predator = new Predator(x, y);
                        tiles[x][y].setContains(predator);
                        entities.add(predator);
                    }
                } else if (seed < 0.6) {
                    tiles[x][y] = new Slope();
                } else {
                    tiles[x][y] = new Mountain();
                }
            }
        }
        return this;
    }

    public WorldBuilder makeCaves() {
        return randomizeTiles();
    }

}
