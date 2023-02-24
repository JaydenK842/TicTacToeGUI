import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class TicTacToeFrame extends JFrame {
    Vector<Vector<JButton>> buttons = new Vector<>();
    Vector<JButton> justButton = new Vector<>();
    String player = "X";
    int turnsPlayed = 1;

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

    public TicTacToeFrame() { //Sets up the frame
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
        results.append("It's X's turn.\n");

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 4, screenHeight / 4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void table() { //Table panel
        Vector<JButton> row = new Vector<>();

        //Sets the table panel and gives it a grid layout with spacing
        table = new JPanel();
        table.setLayout(new GridLayout(3, 3));
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Creates all the buttons, then adds them to a vector for easy updating
        tLeft = new JButton();
        tLeft.setPreferredSize(new Dimension(50, 50)); // Sets the size for all buttons
        justButton.add(tLeft);
        tMiddle = new JButton();
        justButton.add(tMiddle);
        tRight = new JButton();
        justButton.add(tRight);
        mLeft = new JButton();
        justButton.add(mLeft);
        middle = new JButton();
        justButton.add(middle);
        mRight = new JButton();
        justButton.add(mRight);
        bLeft = new JButton();
        justButton.add(bLeft);
        bMiddle = new JButton();
        justButton.add(bMiddle);
        bRight = new JButton();
        justButton.add(bRight);

        //Sets the buttons up
        resetOrSet(justButton, 2);

        //Adds all the buttons to the grid panel
        table.add(tLeft);
        table.add(tMiddle);
        table.add(tRight);
        table.add(mLeft);
        table.add(middle);
        table.add(mRight);
        table.add(bLeft);
        table.add(bMiddle);
        table.add(bRight);

        //Adds rows of vectors with buttons in a 2D vector
        row.add(tLeft);
        row.add(tMiddle);
        row.add(tRight);
        buttons.add(row);
        row = new Vector<>();
        row.add(mLeft);
        row.add(middle);
        row.add(mRight);
        buttons.add(row);
        row = new Vector<>();
        row.add(bLeft);
        row.add(bMiddle);
        row.add(bRight);
        buttons.add(row);
    }

    public void info() { //Info panel
        //Info panel and the text area with a scroll bar
        info = new JPanel();

        results = new JTextArea(10,40);
        results.setEditable(false);

        scroll = new JScrollPane(results);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        info.add(scroll);
    }

    private void options() { //Options panel
        //Options panel and the quit button
        options = new JPanel();

        quit = new JButton("Quit");

        quit.addActionListener((ActionEvent ae) -> quit());
        options.add(quit);
    }

    public void buttonClick(String turn, JButton buttonChosen) {
        //When the button is clicked, it will set the text to the player
        buttonChosen.setText(turn);

        //Disables the button
        buttonChosen.setEnabled(false);

        //Either someone wins, it becomes a tie or it becomes the next players turn
        if (win(turn)) {
            JOptionPane.showMessageDialog(frame, player + " Wins!");
            turnsPlayed = 0;
            resetOrSet(justButton, 1);
        } else if (turnsPlayed == 7) {
            JOptionPane.showMessageDialog(frame, "It's a Tie!");
            turnsPlayed = 1;
            resetOrSet(justButton, 1);
        } else {
            turnsPlayed++;
            player = changeTurn(turn);
            results.append("It's " + player + "'s turn.\n");
        }
    }

    private String changeTurn(String turn) {
        //Changes the turn
        if (turn.equals("X")) {
            return "O";
        } else {
            return "X";
        }
    }

    private boolean win(String turn) {
        //Checks all the win possibilities
        return isColWin(buttons, turn) || isRowWin(buttons, turn) || isDiagonalWin(buttons, turn);
    }

    private boolean isRowWin(Vector<Vector<JButton>> buttons, String turn) {
        //Checks for row wins
        for (int v = 0; v < 3; v++) {
            if (buttons.get(0).get(v).getText().equals(turn) && buttons.get(1).get(v).getText().equals(turn) && buttons.get(2).get(v).getText().equals(turn)) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(Vector<Vector<JButton>> buttons, String turn) {
        //Checks for column wins
        for (int v = 0; v < 3; v++) {
            if (buttons.get(v).get(0).getText().equals(turn) && buttons.get(v).get(1).getText().equals(turn) && buttons.get(v).get(2).getText().equals(turn)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(Vector<Vector<JButton>> buttons, String turn) {
        //Checks for a diagonal win
        return (buttons.get(0).get(0).getText().equals(turn) && buttons.get(1).get(1).getText().equals(turn) && buttons.get(2).get(2).getText().equals(turn)) || (buttons.get(2).get(0).getText().equals(turn) && buttons.get(1).get(1).getText().equals(turn) && buttons.get(0).get(2).getText().equals(turn));
    }

    private void resetOrSet(Vector<JButton> justButton, int ROS) {
        //Will either reset the button or created the buttons depending on ROS
        if (ROS == 1) {
            //Re-enables all the buttons and resetting their text
            for (JButton button : justButton) {
                button.setEnabled(true);
                button.setText("");
            }

            //Begins on turn X again
            player = "X";
            results.append("It's " + player + "'s turn.\n");
        } else {
            for (JButton button : justButton) {
                //Not sure what this means, but it makes the button not grey out the text when deactivated
                button.setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.BLACK;
                    }
                });

                //Gives the button the action when pressed
                button.addActionListener((ActionEvent ae) -> buttonClick(player, button));

                //Sets its background color to white and changes the font size to arial and size 30
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Arial", Font.BOLD, 30));
            }
        }
    }

    private void quit() {
        JOptionPane.showMessageDialog(frame, player + " Quit.");
        System.exit(0);
    }
}