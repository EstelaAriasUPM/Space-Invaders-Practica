package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends JFrame  {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public Main() {

        initUI();
    }

    private void initUI() {

        add(new Board());
        logger.info("Board se crea correctamente");

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new Main();
            ex.setVisible(true);
        });
    }
}
