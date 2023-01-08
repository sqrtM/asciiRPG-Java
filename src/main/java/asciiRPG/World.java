package asciiRPG;

import java.awt.Color;

public class World {

    private Tile[][] tiles;
    private int width;
    private int height;
    private double time = 0;

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getGlyph(int x, int y){
        return tile(x, y).getGlyph();
    }

    public Color getColor(int x, int y){
        return tile(x, y).getColor();
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
    }

    public Tile tile(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height)
            return Tile.BOUNDS;
        else
            return tiles[x][y];
    }
}
