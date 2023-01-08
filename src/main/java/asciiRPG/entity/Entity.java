package asciiRPG.entity;

public abstract class Entity {
    char glyph = 'C';
    int health;

    public abstract char getGlyph();
    abstract int getHealth();
}
