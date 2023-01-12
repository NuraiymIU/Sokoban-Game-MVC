import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class Viewer {
    private Canvas canvas;
    private Controller controller;
    private JFrame frame;
    private JMenuBar menuBar;

    public Viewer() {
        controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);
        initFrame();
        menuBar = new JMenuBar();
        initLevelsMenu();
        frame.add(initMenuPanel(), BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private void initFrame() {
        frame = new JFrame("Sokoban Game MVC Pattern");
        frame.setSize(700, 760);
        frame.add("Center", canvas);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(controller);
    }

    private JPanel initMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(menuBar, BorderLayout.NORTH);
        return menuPanel;
    }

    private JMenuItem createItems(String name, String command) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setActionCommand(command);
        menuItem.addActionListener(controller);
        return menuItem;
    }

    private void initLevelsMenu() {
        JMenu levelsMenu = new JMenu("Levels");
        Font font = new Font("Bodoni MT" , Font.PLAIN, 18);
        levelsMenu.setFont(font);
        JMenuItem restartItem = createItems("Restart","restart");
        restartItem.setBackground(new Color(50,200,0));
        JMenuItem firstLevelItem = createItems("Level 1", "level 1");
        JMenuItem secondLevelItem = createItems("Level 2", "level 2");
        JMenuItem thirdLevelItem = createItems("Level 3", "level 3");
        JMenuItem fourthLevelItem = createItems("Level 4", "level 4");
        JMenuItem fifthLevelItem = createItems("Level 5", "level 5");
        JMenuItem sixthLevelItem = createItems("Level 6", "level 6");
        JMenuItem seventhLevelItem = createItems("Level 7", "level 7");
        JMenuItem eighthLevelItem = createItems("Level 8", "level 8");
        JMenuItem ninthLevelItem = createItems("Level 9", "level 9");

        levelsMenu.add(restartItem);
        levelsMenu.add(firstLevelItem);
        levelsMenu.add(secondLevelItem);
        levelsMenu.add(thirdLevelItem);
        levelsMenu.add(fourthLevelItem);
        levelsMenu.add(fifthLevelItem);
        levelsMenu.add(sixthLevelItem);
        levelsMenu.add(seventhLevelItem);
        levelsMenu.add(eighthLevelItem);
        levelsMenu.add(ninthLevelItem);
        menuBar.add(levelsMenu);
    }

    public void update() {
        canvas.repaint();
    }

    public boolean showDialogWon() {
        JOptionPane.showMessageDialog(frame, "You are won!!!\nDo you want to go the next level?");
        return true;
    }

    public void showDialogEnd() {
        JOptionPane.showMessageDialog(frame, "Congratulations!!! You have passed all levels\nDo you want to go the 1-st level?");

    }
}
