import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {
    private List<Rectangle> body;
    private int w = Game.width;
    private int h =  Game.height;
    private int d = Game.dimension;
    private String move;
    
    public Snake(){
        body = new ArrayList<>();

        Rectangle temp = new Rectangle(d, d);
        temp.setLocation(w / 2 *d, h / 2 * d);
        body.add(temp);

        temp = new Rectangle(d, d);
        temp.setLocation((w / 2 - 1) *d, (h  / 2 )* d);
        body.add(temp);

        temp = new Rectangle(d, d);
        temp.setLocation((w / 2 - 2) *d, (h  / 2)* d);
        body.add(temp);

        move = "NOTHING";
    }
    public void move(){
        if(move != "NOTHING") {
            Rectangle first = body.get(0);
            Rectangle temp = new Rectangle(Game.dimension, Game.dimension);

            switch (move) {
                case "UP": temp.setLocation(first.x, first.y - Game.dimension);
                    break;

                case "DOWN": temp.setLocation(first.x, first.y + Game.dimension);
                    break;

                case "LEFT": temp.setLocation(first.x - Game.dimension, first.y);
                    break;

                case "RIGHT": temp.setLocation(first.x + Game.dimension, first.y);
                    break;
            }
            body.add(0,temp);
            body.remove(body.size()-1);
        }
    }

    public void grow(){
        Rectangle first = body.get(0);
        
        Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
        
        switch (move) {
            case "UP": temp.setLocation(first.x, first.y - Game.dimension);
                break;

            case "DOWN": temp.setLocation(first.x, first.y + Game.dimension);
                break;

            case "LEFT": temp.setLocation(first.x - Game.dimension, first.y);
                break;

            case "RIGHT": temp.setLocation(first.x + Game.dimension, first.y);
                break;
        }
        body.add(0,temp);
    }


    public List<Rectangle> getBody() {
        return body;
    }

    public void setBody(List<Rectangle> body) {
        this.body = body;
    }

    public int getX(){
        return body.get(0).x;
    }
    public int getY(){
        return body.get(0).y;
    }
    public String getMove() {
    	return move;
    }

    public void up(){
    	if(move != "DOWN") move = "UP";
    }
    public void down(){
    	if(move != "UP") move = "DOWN";
    }
    public void left(){
    	if(move != "RIGHT") move = "LEFT";
    }
    public void right(){
    	if(move != "LEFT") move = "RIGHT";
    }

    public String getScore(Snake snake) {
        return String.valueOf(snake.getBody().size()-3);
    }
}
