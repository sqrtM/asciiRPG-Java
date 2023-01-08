package asciiRPG;

import asciiRPG.entity.Predator;

import java.awt.*;

public class WorldBuilder {
    private int width;
    private int height;
    private Tile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
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
                    tiles[x][y] = Tile.WATER;
                } else if (seed < 0.05) {
                    tiles[x][y] = Tile.SHORE;
                } else if (seed < 0.3) {
                    tiles[x][y] = Tile.FLOOR;
                    /*
                    if (Math.random() < 0.01) {
                        tiles[x][y].setContains(new Predator());
                    }
                    */
                } else if (seed < 0.6) {
                    tiles[x][y] = Tile.SLOPE;
                } else {
                    tiles[x][y] = Tile.MOUNTAIN;
                }
            }
        }
        return this;
    }

    public WorldBuilder makeCaves() {
        return randomizeTiles();
    }

}
