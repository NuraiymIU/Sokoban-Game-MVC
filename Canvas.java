import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class Canvas extends JPanel {
    private Model model;
    private Image gamerImage;
    private Image wallImage;
    private Image boxImage;
    private Image goalImage;

    public Canvas(Model model) {
        this.model = model;
        setBackground(Color.BLACK);
        setOpaque(true);
        File fileNameImageGamer = new File("images/gamer.png");
        File fileNameImageWall = new File("images/wall.png");
        File fileNameImageBox = new File("images/box.png");
        File fileNameImageGoal = new File("images/goal.png");
        try {
            gamerImage = ImageIO.read(fileNameImageGamer);
            wallImage = ImageIO.read(fileNameImageWall);
            boxImage = ImageIO.read(fileNameImageBox);
            goalImage = ImageIO.read(fileNameImageGoal);
        } catch (IOException ioexception) {
            System.out.println(ioexception);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if(!model.isErrorStatus()) {
            errorDraw(g);
            return;
        }
        desktopDraw(g);
    }

    private void desktopDraw(Graphics g) {
        super.paint(g);
        int x = 40;
        int y = 40;
        int width = 50;
        int height = 50;
        int offset = 10;
        int[][] desktop = model.getDesktop();
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    g.drawImage(gamerImage, x, y, null);
                } else if (desktop[i][j] == 2) {
                    g.drawImage(wallImage, x, y, null);
                } else if (desktop[i][j] == 3) {
                    g.drawImage(boxImage, x, y, null);
                } else if (desktop[i][j] == 4) {
                    g.drawImage(goalImage, x, y, null);
                }
                x = x + width + offset;
            }
                x = 40;
                y = y + height + offset;
            }
        }

    private void errorDraw(Graphics g) {
        Font font = new Font("Corbel", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawRect(70, 160, 530, 60);
        g.drawString("Error! Level has an incorrect structure!", 95, 200);
    }
}
