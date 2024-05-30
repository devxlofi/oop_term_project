
import java.awt.Color;
import java.awt.Graphics;

abstract class GcuBoundedShape extends GcuShape
{
    private boolean fill; 
    
    public GcuBoundedShape() {
        super();
        fill = false;
    }
    
    public GcuBoundedShape(int startX, int startY, int endX, int endY, Color color, boolean fill) {
        super(startX, startY, endX, endY, color);
        this.fill = fill;
    }
    
    /**
     * Mutator methods
     */

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     * Accessor Methods
     */
  
    public int getUpperLeftX() {
        return Math.min(getStartX(), getEndX());
    }

    public int getUpperLeftY() {
        return Math.min(getStartY(), getEndY());
    }
    
    public int getWidth() {
        return Math.abs(getStartX() - getEndX());
    }
  
    public int getHeight() {
        return Math.abs(getStartY() - getEndY());
    }
    
    public boolean getFill() {
        return fill;
    }
    
    abstract public void draw( Graphics g );
}
