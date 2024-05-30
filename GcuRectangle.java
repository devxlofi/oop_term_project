
import java.awt.Color;
import java.awt.Graphics;

public class GcuRectangle extends GcuBoundedShape
{ 
    public GcuRectangle() {
        super();
    }

    public GcuRectangle(int startX, int startY, int endX, int endY, Color color, boolean fill) {
        super(startX, startY, endX, endY, color, fill);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        int x = Math.min(getStartX(), getEndX());
        int y = Math.min(getStartY(), getEndY());
        int width = Math.abs(getStartX() - getEndX());
        int height = Math.abs(getStartY() - getEndY());
        
        if (isFill()) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}
