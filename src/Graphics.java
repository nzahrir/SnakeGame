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
		g2d.fillRect(0, 0, Game.WIDTH * Game.DIMENSION + 5, Game.HEIGHT * Game.DIMENSION + 5);
		
		if(state == "START") {
			g2d.setColor(Color.white);
			g2d.drawString("Press Any Key", Game.WIDTH/2 * Game.DIMENSION - 40, Game.HEIGHT / 2 * Game.DIMENSION - 20);
		}
		else if(state == "RUNNING") {
			g2d.setColor(Color.red);
			g2d.fillRect(food.getX() * Game.DIMENSION, food.getY() * Game.DIMENSION, Game.DIMENSION, Game.DIMENSION);
		
			g2d.setColor(Color.green);
			
			snake.getBody().stream().forEach(g2d::fill);
		}
		else {
			g2d.setColor(Color.white);
			g2d.drawString("Your Score: " + snake.getScore(), Game.WIDTH/2 * Game.DIMENSION - 40, Game.HEIGHT / 2 * Game.DIMENSION - 20);
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
