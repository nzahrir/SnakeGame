import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener{

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int DIMENSION = 20;

    private Snake snake;
    private Food food;
    private Graphics graphics;
    private JFrame window;

    Game(){
        window = new JFrame();

        snake = new Snake();
        
        food = new Food(snake);
        
        graphics = new Graphics(this);

        window.add(graphics);
        
        window.setTitle("Snake");
        window.setSize(WIDTH * DIMENSION + 2, HEIGHT * DIMENSION + DIMENSION + 4);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startGame(){
        graphics.setState("RUNNING");
    }
    
    public void update(){
        if(graphics.state == "RUNNING"){
            if(didEatFood()) {
            snake.grow();
            food.spawnFood(snake);
            }
            else if(wallCollision() || snakeCollision()) graphics.setState("END");
            else snake.move();
        }
    }

    private boolean wallCollision(){
        if(snake.getHeadX() < 0
        || snake.getHeadX() >= WIDTH * DIMENSION
        || snake.getHeadY() < 0
        || snake.getHeadY() >= HEIGHT * DIMENSION)
            return true;
        
        return false;
    }

    private boolean didEatFood(){
        if(snake.getHeadX() == food.getX() * DIMENSION && snake.getHeadY() == food.getY() * DIMENSION)
            return true;
        return false;
    }

    private boolean snakeCollision(){
    	return snake.getBody()
                .stream()
                .skip(1)
                .anyMatch( s -> snake.getHeadX() == s.x && snake.getHeadY() == s.y);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

       if(graphics.state.equals("RUNNING")) {
           switch (keyCode) {
               case KeyEvent.VK_W -> snake.up();
               case KeyEvent.VK_A -> snake.left();
               case KeyEvent.VK_S -> snake.down();
               case KeyEvent.VK_D -> snake.right();
           }
       }

       else this.startGame();
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
