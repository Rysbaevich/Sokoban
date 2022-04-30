import javax.swing.*;

public class Viewer {

    private Canvas canvas;
    private JFrame frame;

    public Viewer(){
        Controller controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);

        frame = new JFrame("Sokoban Game");
        frame.setSize(1050, 800);
        frame.add(canvas);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(controller);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    public void update(){
        canvas.repaint();
    }

    public void showWonMessage() {
        JOptionPane.showMessageDialog(frame, "Куттуктайм, бул жолу жеңиш сиз тарапта!");
    }

    public void showStartMessage() {
        JOptionPane.showMessageDialog(frame,
                "                                  Салам оюнчу!\n" +
                "Эрежеси: Кутучаларды 'X' катары белгиленген жерге жылдыруу керек.\n" +
                "Башкаруу үчүн багыттоочу баскычтарды колдонуңуз:   ←,   ↑,   →,   ↓.\n" +
                "Оюндун ушул деңгээлин кайра баштоо үчүн 'ESC' баскычын басыңыз.");
    }
}

