import java.awt.*;

public class Tile {
    private int id;
    private boolean solid;
    private Image displaypic;

    public Tile(int tileid, Image display) {
        displaypic = display;
        id = tileid;
        solid = true;
    }

    public Tile(int tileid, Image display, boolean solid) {
        displaypic = display;
        id = tileid;
        this.solid = solid;
    }

    public Image getDisplaypic() {
        return displaypic;
    }

    public void setDisplaypic(Image displaypic) {
        this.displaypic = displaypic;
    }

    public void show(Graphics g, int x, int y) {
        g.drawImage(displaypic, x, y, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setType(int id, Image displaypic) {
        this.displaypic = displaypic;
        this.id = id;
    }

}
