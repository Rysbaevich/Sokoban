import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements KeyListener {

    private Model model;

    public Controller(Viewer viewer){
        model = new Model(viewer);
    }

    public Model getModel(){
        return model;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();

        String direction = "";

        switch (keyCode){
            case KeyEvent.VK_LEFT:
                direction = "Left";
                break;
            case KeyEvent.VK_UP:
                direction = "Up";
                break;
            case KeyEvent.VK_RIGHT:
                direction = "Right";
                break;
            case KeyEvent.VK_DOWN:
                direction = "Down";
                break;
            case 27:
                direction = "Restart";
                break;
            default:
                return;
        }
        model.move(direction);
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}

