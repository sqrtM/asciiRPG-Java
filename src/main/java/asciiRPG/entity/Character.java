package asciiRPG.entity;

public class Character {

    private final String name = "sqrtM";
    private final String title = "unknown wanderer";
    private final char glyph = '@';
    private int health = 100;

    public char getGlyph() {
        return this.glyph;
    }

    public int getHealth() {
        return this.health;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }
}
