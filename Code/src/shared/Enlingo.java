package shared;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import mainMenu.MainMenuControl;

import javax.swing.UIManager;

public class Enlingo {
    public static void main(String[] args) {
        // Set FlatLaf Dark theme
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainMenuControl();
    }

    public static void hideAllViews(MainMenuControl mainMenuControl) {
        mainMenuControl.hideAllViews();
    }
}
