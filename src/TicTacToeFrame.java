import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

public class TicTacToeFrame extends JFrame {
    Vector<Vector<JButton>> buttons;
    String player = "X";

    JFrame frame;
    JPanel main;

    JPanel table;
    JButton tLeft;
    JButton tMiddle;
    JButton tRight;
    JButton mLeft;
    JButton middle;
    JButton mRight;
    JButton bLeft;
    JButton bMiddle;
    JButton bRight;

    JPanel info;
    JTextArea results;
    JScrollPane scroll;

    JPanel options;
    JButton quit;

    public TicTacToeFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        frame = new JFrame();
        main = new JPanel();

        main.setLayout(new BorderLayout());
        frame.setTitle("Tic Tac Toe");

        table();
        main.add(table, BorderLayout.NORTH);

        info();
        main.add(info, BorderLayout.CENTER);

        options();
        main.add(options, BorderLayout.SOUTH);

        frame.add(main);

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 4, screenHeight / 4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void table() {
        Vector<JButton> row = null;

        table = new JPanel();
        table.setLayout(new GridLayout(3, 3, 10, 10));
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        tLeft = new JButton();
        tLeft.setPreferredSize(new Dimension(50, 50)); // Sets the size for all buttons
        tLeft.setFocusable(false);
        tLeft.addActionListener((ActionEvent ae) -> buttonClick(player, tLeft));
        table.add(tLeft);
        tMiddle = new JButton();
        tMiddle.setFocusable(false);
        tMiddle.addActionListener((ActionEvent ae) -> buttonClick(player, tMiddle));
        table.add(tMiddle);
        tRight = new JButton();
        tRight.setFocusable(false);
        tRight.addActionListener((ActionEvent ae) -> buttonClick(player, tRight));
        table.add(tRight);
        row.add(tLeft);
        row.add(tMiddle);
        row.add(tRight);
        buttons.add(row);
        row = null;


        mLeft = new JButton();
        mLeft.setFocusable(false);
        mLeft.addActionListener((ActionEvent ae) -> buttonClick(player, mLeft));
        table.add(mLeft);
        middle = new JButton();
        middle.setFocusable(false);
        middle.addActionListener((ActionEvent ae) -> buttonClick(player, middle));
        table.add(middle);
        mRight = new JButton();
        mRight.setFocusable(false);
        mRight.addActionListener((ActionEvent ae) -> buttonClick(player, mRight));
        table.add(mRight);
        row.add(mLeft);
        row.add(middle);
        row.add(mRight);
        buttons.add(row);
        row = null;

        bLeft = new JButton();
        bLeft.setFocusable(false);
        bLeft.addActionListener((ActionEvent ae) -> buttonClick(player, bLeft));
        table.add(bLeft);
        bMiddle = new JButton();
        bMiddle.setFocusable(false);
        bMiddle.addActionListener((ActionEvent ae) -> buttonClick(player, bMiddle));
        table.add(bMiddle);
        bRight = new JButton();
        bRight.setFocusable(false);
        bRight.addActionListener((ActionEvent ae) -> buttonClick(player, bRight));
        table.add(bRight);

    }

    public void info() {
        info = new JPanel();

        results = new JTextArea(10,40);
        results.setEditable(false);

        scroll = new JScrollPane(results);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        info.add(scroll);
    }

    private void options() {
        options = new JPanel();

        quit = new JButton("Quit");

        quit.addActionListener((ActionEvent ae) -> System.exit(0));
        options.add(quit);
    }

    public void buttonClick(String turn, JButton buttonChosen) {
        buttonChosen.setText(player);
        buttonChosen.setEnabled(false);
        player = changeTurn(player);
        tellTurn(player, results);
    }

    public String changeTurn(String turn) {
        if (player.equals("X")) {
            return "O";
        } else {
            return "X";
        }
    }

    public void tellTurn(String turn, JTextArea results) {
        results.append("It is " + turn + "'s turn.");
    }

    public boolean win() {

    }

    public void reset(Vector<JButton> buttons) {
        for (JButton button : buttons) {
            button.setEnabled(true);
            button.setText("");
        }

        player = "X";
    }
}