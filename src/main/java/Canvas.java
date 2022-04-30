import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Canvas extends JPanel {
    private final Model model;
    private Image gamerImage;
    private Image gamerRightImage;
    private Image gamerLeftImage;
    private Image gamerUpImage;
    private Image wallImage;
    private Image boxImage;
    private Image xImage;

    public Canvas(Model model){
        this.model = model;
        setBackground(Color.BLACK);
        setOpaque(true);
        File fileGamer = new File("images/gamer.png");
        File fileGamerRight = new File("images/gamerRight.png");
        File fileGamerLeft = new File("images/gamerLeft.png");
        File fileGamerUp = new File("images/gamerUp.png");
        File fileWall = new File("images/wall.png");
        File fileBox = new File("images/box.png");
        File fileX = new File("images/x.png");
        try {
            gamerImage = ImageIO.read(fileGamer);
            gamerRightImage = ImageIO.read(fileGamerRight);
            gamerLeftImage = ImageIO.read(fileGamerLeft);
            gamerUpImage = ImageIO.read(fileGamerUp);
            wallImage = ImageIO.read(fileWall);
            boxImage = ImageIO.read(fileBox);
            xImage = ImageIO.read(fileX);
        } catch (IOException ioException) {
            System.out.println(ioException);
            System.exit(-1);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (model.getStateGame()) {
            drawDesktop(g);
        } else {
            drawErrorMessage(g);
        }
    }

    private void drawErrorMessage(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.ORANGE);
        g.drawString("Initialization ERROR!", 100, 100);
    }

    private void drawDesktop(Graphics g){
        int x = 50;
        int y = 50;
        int width = 50;
        int height = 50;
        int offset = 1;

        int[][] desktop = model.getDesktop();
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    drawGamer(g, x, y);
                } else if (desktop[i][j] == 2) {
                    g.drawImage(wallImage, x, y, null);
                } else if (desktop[i][j] == 3) {
                    g.drawImage(boxImage, x, y, null);
                } else if (desktop[i][j] == 4) {
                    g.drawImage(xImage, x, y, null);
                }
                x += width + offset;
            }
            x = 50;
            y += height + offset;
        }
    }

    private void drawGamer(Graphics g, int x, int y){
        String direction = model.getDirection();

        switch (direction) {
            case ("Down"):
                g.drawImage(gamerImage, x, y, null);
                break;
            case ("Up"):
                g.drawImage(gamerUpImage, x, y, null);
                break;
            case ("Right"):
                g.drawImage(gamerRightImage, x, y, null);
                break;
            case ("Left"):
                g.drawImage(gamerLeftImage, x, y, null);
                break;
            default:
                break;
        }
    }
}

