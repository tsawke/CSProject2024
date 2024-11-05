import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class index{
    public static void createAndShowWindow() {
        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Welcome to Sokoban Game!");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JButton newGame = new JButton("New Game");
        JButton loadArchive = new JButton("Load Archive");
        JButton exit = new JButton("Exit");

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.DARK_GRAY);

        content.add(buttons, BorderLayout.CENTER);


        frame.setVisible(true);
    }
}
