package asciiRPG.world;

import asciiRPG.entity.Predator;
import asciiRPG.tiles.*;

public class WorldBuilder {
    private int width;
    private int height;
    private FloorTile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new FloorTile[width][height];
    }

    public World build() {
        return new World(tiles);
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
                        tiles[x][y].setContains(new Predator(x, y));
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
