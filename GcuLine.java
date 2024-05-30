import java.awt.Color;
import java.awt.Graphics;

public class GcuLine extends GcuShape
{  
    public GcuLine() {
        super();
    }
    
    public GcuLine(int startX, int startY, int endX, int endY, Color color) {
        super(startX, startY, endX, endY, color);
    }
  
    @Override
    public void draw( Graphics g ) {
        g.setColor(getColor());
        g.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
