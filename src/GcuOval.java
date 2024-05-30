import java.awt.Color;
import java.awt.Graphics;

public class GcuOval extends GcuBoundedShape 
{ 
    public GcuOval() {
        super();
    }
    
    public GcuOval(int startX, int startY, int endX, int endY, Color color, boolean fill) {
        super(startX, startY, endX, endY, color, fill);
    }

    @Override
    public void draw( Graphics g ) {
        g.setColor(getColor());
        if (getFill()) {
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        else {
            g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }   
}
