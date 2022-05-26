import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Snake {
    private List<Rectangle> body;
    private int w = Game.WIDTH;
    private int h =  Game.HEIGHT;
    private int d = Game.DIMENSION;
    private String move;
    private Directions dir = Directions.UP;
    public Snake(){
        body = new ArrayList<>();
        System.out.println(dir.label);
        System.out.println(Arrays.toString(Directions.values()));
        //Creating Initial Sanke
        for (int i = 0; i < 3; ++i) {
            Rectangle temp = new Rectangle(d, d);
            temp.setLocation((w / 2 - i) * d, h / 2 * d);
            body.add(temp);
        };

        //Initial Movement State
        move = "NOTHING";
    }
    public void move(){
        if(move != "NOTHING") {
            Rectangle snakeHead = body.get(0);
            setSnakeDirection(snakeHead,move);
            body.remove(body.size()-1);
        }
    }

    public void grow(){
        Rectangle snakeHead = body.get(0);
        setSnakeDirection(snakeHead,move);
    }

    public void setSnakeDirection(Rectangle snakeHead, String move){
        Rectangle temp = new Rectangle(d, d);
        switch (move) {
            case "UP"-> temp.setLocation(snakeHead.x, snakeHead.y - d);
            case "DOWN"-> temp.setLocation(snakeHead.x, snakeHead.y + d);
            case "LEFT"-> temp.setLocation(snakeHead.x - d, snakeHead.y);
            case "RIGHT"->temp.setLocation(snakeHead.x + d, snakeHead.y);
        }
        body.add(0,temp);
    }

    public List<Rectangle> getBody() {
        return body;
    }


    public int getHeadX(){
        return body.get(0).x;
    }
    public int getHeadY(){
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

    public String getScore() {
        return String.valueOf(body.size()-3);
    }
}
