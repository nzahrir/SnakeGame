import java.awt.*;

public class Food {
	
    private int x;
    private int y;

    public Food(Snake snake){
        this.spawnFood(snake);
    }

    public void spawnFood(Snake snake){
        boolean onSnake = true;
        while(onSnake) {
            onSnake = false;
            FoodPosX();
            FoodPosY();
            onSnake = shouldRedrawFood(snake);
        }
    }

    public boolean shouldRedrawFood(Snake snake){
        return snake
                .getBody()
                .stream()
                .anyMatch(rectangle -> rectangle.getX() == this.getX() || rectangle.getY() == this.getY());
    }
    
    public void FoodPosX() {
    	setX((int)(Math.random() * Game.width - 1));
    }
    
    public void FoodPosY() {
    	setY((int)(Math.random() * Game.height - 1));
    }


    public int getX() {
        return x;
    }
    
    public void setX(int x) {
    	this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
}
