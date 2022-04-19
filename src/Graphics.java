import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics 
extends JPanel 
implements ActionListener {
	
    private Timer timer = new Timer(100, this);
    public String state;

    private Game game;
    private Snake snake;
    private Food food;

    public Graphics(Game game){
        timer.start();
        state = "START";
        snake = game.getSnake();
        food = game.getFood();
        this.game = game;

        this.addKeyListener(game);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Game.width * Game.dimension + 5, Game.height * Game.dimension + 5);
		
		if(state == "START") {
			g2d.setColor(Color.white);
			g2d.drawString("Press Any Key", Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
		}
		else if(state == "RUNNING") {
			g2d.setColor(Color.red);
			g2d.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension);
		
			g2d.setColor(Color.green);
			
			snake.getBody().stream().forEach(rectangle -> g2d.fill(rectangle));

		}
		else {
			g2d.setColor(Color.white);
			g2d.drawString("Your Score: " + (snake.getBody().size() - 3), Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
		}
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        this.game.update();
    }
    
    public void setState(String state) {
    	this.state = state;
    }
}
