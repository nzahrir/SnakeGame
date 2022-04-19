import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener{

    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 20;

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
        window.setSize(width * dimension + 2, height * dimension + dimension + 4);
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
        if(snake.getX() < 0
        || snake.getX() >= width * dimension
        || snake.getY() < 0
        || snake.getY() >= height * dimension
        )return true;
        
        return false;
    }

    private boolean didEatFood(){
        if(snake.getX() == food.getX() * dimension && snake.getY() == food.getY() * dimension)
            return true;
        return false;
    }

    private boolean snakeCollision(){
    	
        for(int i = 1; i < snake.getBody().size(); i++){
            if(snake.getX() == snake.getBody().get(i).x
            && snake.getY() == snake.getBody().get(i).y){
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

       if(graphics.state.equals("RUNNING")) {
           switch (keyCode) {
               case KeyEvent.VK_W:
                   snake.up();
                   break;

               case KeyEvent.VK_A:
                   snake.left();
                   break;

               case KeyEvent.VK_S:
                   snake.down();
                   break;

               case KeyEvent.VK_D:
                   snake.right();
                   break;
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
