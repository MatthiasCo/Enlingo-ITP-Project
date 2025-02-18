import com.formdev.flatlaf.FlatDarkLaf;
import mainMenu.MainMenuControl;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Set FlatLaf Dark theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainMenuControl();
    }

    public static void hideAllViews(MainMenuControl mainMenuControl) {
        mainMenuControl.hideAllViews();
    }
}
