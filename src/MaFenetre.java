import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;

class MaFenetre extends JFrame {

    String fileName;

    int levelR, levelG, levelB;
    Panel p1 = new Panel();
    Panel p2 = new Panel();
    Button save = new Button("Sauvegarder");
    Button quit = new Button("Quitter");

    Button gen = new Button("Générer");

    JScrollBar scrollr1 = new JScrollBar(Adjustable.HORIZONTAL);

    JScrollBar scrollr2 = new JScrollBar(Adjustable.HORIZONTAL);

    JScrollBar scrollr3 = new JScrollBar(Adjustable.HORIZONTAL);

    ImageGeneree image = new ImageGeneree();
    MaFenetre() {
        super("Mon magnifique TP");
        setSize(650, 600);
        Container c = this.getContentPane();

        scrollr1.setPreferredSize(new Dimension(180, 15));
        scrollr2.setPreferredSize(new Dimension(180, 15));
        scrollr3.setPreferredSize(new Dimension(180, 15));
        p1.add(scrollr1);
        p1.add(scrollr2);
        p1.add(scrollr3);
        p2.add(gen);
        p2.add(save);
        p2.add(quit);
        c.add(p1, BorderLayout.NORTH);
        c.add(image, BorderLayout.CENTER);
        c.add(p2, BorderLayout.SOUTH);
        scrollr1.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollr1.getValueIsAdjusting()) {
                    levelR = ae.getValue()/9;
                    return;
                }
            }
        });
        scrollr2.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollr2.getValueIsAdjusting()) {
                    levelG = ae.getValue()/9;
                    return;
                }
            }
        });

        scrollr3.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollr3.getValueIsAdjusting()) {
                    levelB = ae.getValue()/9;
                    return;
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.saveFile("oui.jpg");
            }
        });
        gen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.exp_r=image.random_expr(levelR);
                image.exp_g=image.random_expr(levelG);
                image.exp_b=image.random_expr(levelB);
                image.construitImage(600,600);
                image.repaint();
            }
        });

        setVisible(true);

    }
}
