package AreaWorld;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Area extends JPanel {
    private int sizeW;
    private int sizeH;
    private int space;
    private Prime[][] matrix;
    private int width;
    private int height;

    public void setMatrix(Prime[][] matrix) {
        this.matrix = matrix;
        this.repaint();
    }

    Area(int sizeW, int sizeH, int space, World world) {
        super();
        this.sizeW = sizeW;
        this.sizeH = sizeH;
        this.space = space;
        matrix = world.getMatrix();
        width = world.getWidth();
        height = world.getHeight();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g2d.setPaint((matrix[i][j].getColor()));
                g2d.fillRect(i*(sizeW+space), j*(sizeH+space), sizeW, sizeH);
            }
        }


    }
}
