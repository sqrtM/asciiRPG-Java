package asciiRPG;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import asciiRPG.screens.Screen;
import asciiRPG.screens.StartScreen;

import java.io.Serial;

public class ApplicationMain extends JFrame implements KeyListener {
    @Serial
    private static final long serialVersionUID = 1997L;

    private final AsciiPanel terminal;
    private Screen screen;

    private final int WINDOW_WIDTH = 100;
    private final int WINDOW_HEIGHT = 48;


    public ApplicationMain(){
        super();
        terminal = new AsciiPanel(WINDOW_WIDTH, WINDOW_HEIGHT, AsciiFont.TALRYTH_15_15);
        terminal.write("java rougelike", 1, 1);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }


    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}