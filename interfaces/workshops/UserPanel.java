package interfaces.workshops;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilities.Utilities;
import worldclasses.Account;

public class UserPanel extends JPanel {

    private Account account;

    private JButton userButton;

    public UserPanel(Account account) {
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    private void initComponents() {
        String imagePath;
        String nickname;
        ImageIcon imageIcon;

        try {
            imagePath = this.account.getImage();
            nickname = this.account.getNickname();
        } catch (Exception e) {
            imagePath = "/images/profile/image-00.png";
            nickname = "Iniciar Sesion";
        }
        imageIcon = Utilities.getImageIcon(imagePath, 30, 30);

        // Set up Panel --------------------------------------------------------
        this.setLayout(new FlowLayout());

        // Set up Components ---------------------------------------------------
        this.userButton = new JButton();

        // ---------------------------------------------------------------------
        this.userButton.setIcon(imageIcon);
        this.userButton.setText(nickname);

        // ---------------------------------------------------------------------
        this.add(userButton);
    }

    private void initEvents() {
        this.userButton.addActionListener(ae -> {
            
        });
    }
}
