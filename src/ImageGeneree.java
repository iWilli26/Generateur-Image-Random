import java.util.Random;
import java.awt.image.*;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;

public class ImageGeneree extends JComponent {
    Random graine = new Random();
    Expr exp_r;
    Expr exp_g;
    Expr exp_b;
    RenderedImage im;
    
    Expr random_expr(int level) {
        if (level == 0) {
            if (graine.nextBoolean()) {
                return (new X());
            } else {
                return (new Y());
            }
        } else {
            Expr e;
            switch (graine.nextInt(4)) {
                case 0:
                    e = new Sin(random_expr(level - 1));
                    break;
                case 1:
                    e = new Cos(random_expr(level - 1));
                    break;
                case 2:
                    e = new Moyenne(random_expr(level - 1), random_expr(level - 1));
                    break;
                case 3:
                    e = new Mult(random_expr(level - 1), random_expr(level - 1));
                    break;
                default:
                    e = new Expr();
                    break;
            }
            return e;
        }
    }

    public void generation(int width, int height, int lvlR, int lvlG, int lvlB){
        exp_r=random_expr(lvlR);
        exp_g=random_expr(lvlG);
        exp_b=random_expr(lvlB);
        construitImage(width, height);
    }

    public void construitImage(int width, int height) {
        BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                int r = (int) (255 * (exp_r.eval(2 * ((float) i / width) - 1, ((float) j / height) * 2 - 1) + 1) / 2);
                int g = (int) (255 * (exp_g.eval(2 * ((float) i / width) - 1, ((float) j / height) * 2 - 1) + 1) / 2);
                int b = (int) (255 * (exp_b.eval(2 * ((float) i / width) - 1, ((float) j / height) * 2 - 1) + 1) / 2);
                r = Math.max(r, 0);
                g = Math.max(g, 0);
                b = Math.max(b, 0);
                buff.setRGB(i, j, (new Color(r, g, b)).getRGB());
            }
        }
        im = buff;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRenderedImage(im, null);
    }

    public void saveFile(String fileName) {
        try {
            File f = new File(fileName);
            ImageIO.write(im, "jpg", f);
        } 
        catch (IOException e) {
        }
    }
}