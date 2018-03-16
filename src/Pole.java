import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pole extends JPanel {

    int sizeH;
    int sizeW;
    int spase;
    Pole(int sizeH, int sizeW, int spase) {
        super();
        this.spase = spase;
        this.sizeH = sizeH;
        this.sizeW = sizeW;
    }
    ArrayList<ArrayList<Point>> store = new ArrayList<ArrayList<Point>>();
    public void setStore(ArrayList<ArrayList<Point>> store) {
        this.store = store;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.gray);
        g2d.fillRect(0,0, getWidth(), getHeight());


        for (int i = 0; i < store.size(); i++){
            for (int j = 0; j <store.get(i).size(); j++) {
                Point point = store.get(i).get(j);
                if (point.visible) {
                    g2d.setPaint(point.color);
                    g2d.fillRect( i*(sizeW+spase), j*(sizeH+spase), sizeW, sizeH);
                    if (point.type == PointTypes.PEOPLE) {
                        People p = (People)point;
                        g2d.drawString(String.valueOf(p.getFullness()),30,30);
                        g2d.setPaint(Color.white);
                        switch (p.Sight) {
                            case UP:
                                g2d.drawLine(i*(sizeW+spase) + sizeW/2, j*(sizeH+spase) + sizeH/2, i*(sizeW+spase) - sizeW/2, j*(sizeH+spase) - sizeH/2);
                                break;
                        }
                    }
                }
            }
        }

    }
}
