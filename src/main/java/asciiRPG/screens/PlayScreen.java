package asciiRPG.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import asciiRPG.entity.Character;
import asciiRPG.entity.Entity;
import asciiRPG.entity.Predator;
import asciiRPG.world.World;
import asciiRPG.world.WorldBuilder;
import asciiRPG.tiles.FloorTile;

public class PlayScreen implements Screen {
    private World world;
    private int centerX;
    private int centerY;
    private int screenWidth;
    private int screenHeight;
    private Character player;

    public PlayScreen(){
        screenWidth = 80;
        screenHeight = 45;
        createWorld();
    }

    // this is being called every time the screen changes to the play screen??
    private void createWorld(){
        world = new WorldBuilder(500, 500)
                .makeCaves()
                .build();
        player = new Character();
    }

    public int getScrollX() { return Math.max(0, Math.min(centerX - screenWidth / 2, world.getWidth() - screenWidth)); }

    public int getScrollY() { return Math.max(0, Math.min(centerY - screenHeight / 2, world.getHeight() - screenHeight)); }

    private String getTileInfoString(FloorTile tile) {
        return tile.getName() + " " + tile.getGlyph();
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        world.getEntities().forEach(Entity::takeTurn);

        int left = getScrollX();
        int top = getScrollY();

        displayTiles(terminal, left, top);

        terminal.write(player.getGlyph(), centerX - left, centerY - top);

        terminal.writeCenter("-- press [escape] to lose or [enter] to win --", screenHeight + 1);

        terminal.write("turn : " + world.getTime(), screenWidth + 1, 0);
        terminal.write(player.getName(), screenWidth + 1, 2);
        terminal.write(player.getTitle(), screenWidth + 1, 3);

        terminal.write((char) 0xE0 + " " + "[-----]", screenWidth + 1, 5);
        terminal.write((char) 0xE1 + " " + "[-----]", screenWidth + 1, 6);
        terminal.write((char) 0xE2 + " " + "[-----]", screenWidth + 1, 7);
        terminal.write((char) 0xE3 + " " + "[-----]", screenWidth + 1, 8);

        terminal.write(getTileInfoString(world.getTiles()[centerX][centerY]), screenWidth + 1, 10, world.getTiles()[centerX][centerY].getColor());

    }

    private void displayCreatures(AsciiPanel terminal, int wx, int wy, int x, int y) {
        int newX = world.getTiles()[wx][wy].getContains().get(0).getLocation().getX();
        int newY = world.getTiles()[wx][wy].getContains().get(0).getLocation().getY();
        world.getTiles()[newX][newY].setContains(world.getTiles()[wx][wy].getContains().get(0));
        world.getTiles()[wx][wy].removeContents(0);
        terminal.write(world.getGlyph(wx, wy), x, y, world.getColor(wx, wy));
        terminal.write(world.getTiles()[newX][newY].getContains().get(0).getGlyph(), x, y);
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < screenWidth; x++){
            for (int y = 0; y < screenHeight; y++){
                int wx = x + left;
                int wy = y + top;
                terminal.write(world.getGlyph(wx, wy), x, y, world.getColor(wx, wy));
                if (world.getTiles()[wx][wy].getContains().size() != 0) {
                    displayCreatures(terminal, wx, wy, x, y);
                }
            }
        }
    }

    private void scrollBy(int mx, int my) {
        world.setTime(Math.round((world.getTime() + world.getTiles()[centerX][centerY].getSpeedMod()) * 100) / 100d);
        centerX = Math.max(0, Math.min(centerX + mx, world.getWidth() - 1));
        centerY = Math.max(0, Math.min(centerY + my, world.getHeight() - 1));
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                return new LoseScreen();
            }
            case KeyEvent.VK_ENTER -> {
                return new WinScreen();
            }
            // this SPACE one is just for now.. just for testing.
            case KeyEvent.VK_SPACE -> scrollBy(0, 0);
            case KeyEvent.VK_LEFT, KeyEvent.VK_H -> scrollBy(-1, 0);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_L -> scrollBy(1, 0);
            case KeyEvent.VK_UP, KeyEvent.VK_K -> scrollBy(0, -1);
            case KeyEvent.VK_DOWN, KeyEvent.VK_J -> scrollBy(0, 1);
            case KeyEvent.VK_Y -> scrollBy(-1, -1);
            case KeyEvent.VK_U -> scrollBy(1, -1);
            case KeyEvent.VK_B -> scrollBy(-1, 1);
            case KeyEvent.VK_N -> scrollBy(1, 1);
        }
        return this;
    }
}