package utilz;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ShapeProperties {
    public final int width;
    public final int height;
    public final Color color;
    public final int arcSize; // Arc size for rounded corners

    public ShapeProperties(int width, int height, Color color, int arcSize) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.arcSize = arcSize; // Initialize the arc size
    }

    public ShapeProperties(int size, Color color, int arcSize) {
        this(size, size, color, arcSize); // Default arc size to 0 if not specified
    }

    public ShapeProperties(int size, Color color) {
        this(size, size, color, size / 4); // Default arc size to 0 if not specified
    }

    public static RoundRectangle2D createRoundRect(int x, int y, ShapeProperties shapeProperties) {
        return new RoundRectangle2D.Double(x, y, shapeProperties.width, shapeProperties.height, shapeProperties.arcSize, shapeProperties.arcSize);
    }

}
