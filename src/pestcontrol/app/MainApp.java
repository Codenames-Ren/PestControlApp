
package pestcontrol.app;
import pestcontrol.util.Hibernate;
import view.FrameLogin;
import javax.swing.SwingUtilities;

/**
 *
 * @author ren
 */
public class MainApp {
    public static void main(String[] args) {
        Hibernate.getSessionFactory();
        System.out.println("Hibernate tested ok!");
        
        //open login frame
        SwingUtilities.invokeLater(() -> {
            new FrameLogin().setVisible(true);
        });
    }
}
