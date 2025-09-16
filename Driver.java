package Assignment1;
import javax.swing.UIManager;

public class Driver {
    public static void main(String[] args) {

        // try
        // {
        // UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();

        // }

        System.out.println("Hello world!");
        GUI gui = new GUI();
        while (true) {
            gui.step();
        }
    }
}