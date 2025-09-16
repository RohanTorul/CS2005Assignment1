package Assignment1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CustomButton extends JButton{
    private int row;
    private int col;
    private boolean pressed = false;
    public CustomButton(int row, int col, String text){
        this.row = row;
        this.col = col;
        this.setText(text);
        this.setBackground(Color.decode("#f3a683"));
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public boolean isPressed(){
        return pressed;
    }
    public void setPressed(boolean pressed){
        this.pressed = pressed;
    }

}

class ButtonGrid extends JPanel implements ActionListener{
    private boolean p1;
    private CustomButton[][] GridButtons = new CustomButton[5][5];
    private int[][] GridNumbers = new int[5][5];
    private int score = 0;
    private boolean frozen = false;
    public ButtonGrid(){
        p1 = Math.random() < 0.5;
        this.setLayout(new GridLayout(5,5,0,0));
        this.setSize(500,500);
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5; j++){
                GridNumbers[i][j] = (int) (Math.random() * 5 + 1);
                GridButtons[i][j] = new CustomButton(i,j, (Integer.toString(GridNumbers[i][j])));
                GridButtons[i][j].addActionListener(this);
                GridButtons[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                this.add(GridButtons[i][j]);
            }
        }
        
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public boolean getPlayer(){
        return p1;
    }
    public void resetPlayer(){
        p1 = true;
    }
    public boolean getfrozen(){
        return this.frozen;
    }
    public void setfrozen(boolean frozen){
        this.frozen = frozen;
    }

    public void reset(){
        // reset numbers
        // reset colours
        // reset pressed
        // reset player
        // reset score
        this.frozen = false;
        frozen = false;
        this.score = 0;
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5; j++){
                GridNumbers[i][j] = (int) (Math.random() * 5 + 1);
                GridButtons[i][j].setText(Integer.toString(GridNumbers[i][j]));
                GridButtons[i][j].setBackground(Color.decode("#f3a683"));
                GridButtons[i][j].setPressed(false);
                p1 = Math.random() < 0.5;
                
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        CustomButton clicked = (CustomButton)e.getSource();
        System.out.println("Player: "+ (p1 ? "1" : "2") + " clicked " + " Row: " + clicked.getRow() + " Col: " + clicked.getCol());
        System.out.println("Frozen is " + this.frozen);
        if(clicked.isPressed()|| this.frozen) return;
        clicked.setPressed(clicked.isPressed() || true);
        this.score += GridNumbers[clicked.getRow()][clicked.getCol()];
        System.out.println(score);
        //System.out.println("Player: "+ p1 + " Row: " + clicked.getRow() + " Col: " + clicked.getCol());
        clicked.setBackground(p1 ? Color.decode("#f7d794") : Color.decode("#f5cd79"));
        p1=(!p1);
    }
}

class HelpPage extends JFrame{
    public HelpPage(){
        this.setTitle("Help/Instructions");
        this.setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText("Pontoon is a simple game, where the aim is to force the other player to cause the running total to go above 21.\r\n" + //
                        "\r\n" + //
                        "The game begins with a grid of squares.  Each square contains a random value between 1 and 5 \r\n"+ //
                        "\r\n" + //
                        "The program will randomly choose which player goes first.  That player must then select a square \r\n" + //
                        "\r\n" + //
                        "the value of that square is added to the 'Score'.  The other player then takes their turn \r\n" + //
                        "\r\n" + //
                        "Players cannot select a square that's already been chosen by either player.\r\n" + //
                        "\r\n" + //
                        "When a player is forced to make the total go above 21, the game ends and the other player is declared the winner!");
        textArea.setFont(new Font("Arial", Font.BOLD, 15));
        textArea.setBackground(Color.decode("#f5cd79"));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
    }
}

public class GUI extends JFrame implements ActionListener{
    private JPanel TopPanel; // Displays who's turn it is and the current score
    private ButtonGrid MiddlePanel; // Displays the grid of buttons
    private JPanel BottomPanel; // Displays the Start/Reset button
    private JButton BottomButton;
    private JButton helpButton;
    private JLabel PlayerLabel;
    private JLabel ScoreLabel;
    public boolean ISRUNNING = true;
    public GUI() {
        // this.addWindowListener(new WindowListener(){

        //     public void windowOpened(WindowEvent e) {}
        //     public void windowClosing(WindowEvent e) {
        //         ISRUNNING = false;
        //     }
        //     public void windowClosed(WindowEvent e) {
        //         ISRUNNING = false;
        //     }
        //     public void windowIconified(WindowEvent e) {}
        //     public void windowDeiconified(WindowEvent e) {}
        //     public void windowActivated(WindowEvent e) {}
        //     public void windowDeactivated(WindowEvent e) {}
        // }
        // );
        this.setSize(800,800);
        this.setTitle("Rohan's Pontoon game");
        setLocationRelativeTo(null);
        this.setBackground(Color.decode("#303952"));
        getContentPane().setBackground(Color.decode("#f19066"));
        TopPanel = new JPanel();
        TopPanel.setBackground(Color.decode("#f19066"));
        TopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,800/30,0));
        PlayerLabel = new JLabel("Player: 1");
        ScoreLabel = new JLabel("Score: ");
        PlayerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        helpButton = new JButton("[>>Help me!<<]");
        helpButton.setFont(new Font("Arial", Font.BOLD, 20));
        helpButton.setBackground(Color.decode("#778beb"));
        helpButton.addActionListener(this);
        TopPanel.add(helpButton);
        TopPanel.add(PlayerLabel);
        TopPanel.add(ScoreLabel);

        MiddlePanel = new ButtonGrid();
        BottomPanel = new JPanel();
        BottomPanel.setBackground(Color.decode("#f19066"));
        BottomButton = new JButton("Start/Reset Game");
        BottomButton.setFont(new Font("Arial", Font.BOLD, 20));
        BottomButton.setBackground(Color.decode("#e15f41"));
        BottomPanel.add(BottomButton);
        BottomButton.addActionListener(this);
        getContentPane().setLayout(new BorderLayout(-1,-1));
        getContentPane().add(TopPanel, BorderLayout.NORTH);
        getContentPane().add(MiddlePanel, BorderLayout.CENTER);
        getContentPane().add(BottomPanel, BorderLayout.SOUTH);

        TopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        MiddlePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        BottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
    }
    public void step(){
        this.repaint();
        
        int currentScore = this.MiddlePanel.getScore();
        this.ScoreLabel.setText("Score: " + currentScore);
        
        if(currentScore > 21){
           //System.out.println("Player " + (this.MiddlePanel.getPlayer() ? "1" : "2") + " wins!");
           BottomButton.setText(("Player " + (this.MiddlePanel.getPlayer() ? "1" : "2") + " wins! click to restart!"));
           this.PlayerLabel.setText("current Player: " + (this.MiddlePanel.getPlayer() ? "1" : "2") + "(Winner!)");
           this.MiddlePanel.setfrozen(true);
        }
        else{
            this.MiddlePanel.setfrozen(false);
            this.PlayerLabel.setText("current Player: " + (this.MiddlePanel.getPlayer() ? "1" : "2")+"         ");
            BottomButton.setText("Start/Reset Game");
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this.helpButton){
            HelpPage helpPage = new HelpPage();
            helpPage.setVisible(true);
        }
        else if( e.getSource() == this.BottomButton){
            this.MiddlePanel.reset();
            this.MiddlePanel.setfrozen(false);
            System.out.println("frozen: " + this.MiddlePanel.getfrozen());
            System.out.println("Score: " + this.MiddlePanel.getScore());
        }
       
    }
}
    
