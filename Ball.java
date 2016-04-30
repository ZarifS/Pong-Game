import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Ball implements Runnable {

	//Global Variables 
	int x, y, xDirection, yDirection;
	
	int difficulty = 3;
	//Score 
	int p1Score , p2Score;
	
	//Paddles for players 
	Paddle p1 = new Paddle(15,240,1);
	Paddle p2 = new Paddle(570, 140, 2);
	
	Rectangle ball;
	public Ball(int x, int y){
		p1Score = p2Score = 0;
		this.x = x;
		this.y = y;
		//Sets ball moving randomly 
		Random r = new Random();
		int rDir = r.nextInt(2);
		if (rDir == 0) 
			rDir --;
		setXDirection (rDir);
		int yrDir = r.nextInt(2);
		if (yrDir == 0) 
			yrDir --;
		setYDirection (yrDir);
		
		// Create 'Ball;
		ball = new Rectangle(this.x, this.y, 12, 12);
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
		
	}
	
	public void setXDirection(int xDir){
		xDirection = xDir;
	}
	
	public void setYDirection(int yDir){
		yDirection = yDir;
	}
	
	public void run(){
		try{
			
		while(true){
			move();
			Thread.sleep(difficulty);
		}
	}catch(Exception e)
		{System.err.println( e.getMessage() );}
	}
	
	public void setDifficulty(int diff){
		difficulty = diff;
	}
	
	public void collision(){
		if(ball.intersects(p1.paddle))
			setXDirection(+1);
		if(ball.intersects(p2.paddle))
			setXDirection(-1);
			
	}
	
	public void move(){
		collision();
		ball.x += xDirection;
		ball.y += yDirection;
		// Bounce ball when edge is detected 
		if(ball.x<=0){
			setXDirection(+1);
			p2Score ++;
		}
		if(ball.x>=585) {
			setXDirection(-1);
			p1Score++;
			}
		
		if(ball.y <=19){
			setYDirection(+1);
		}
		if(ball.y>= 478){
			setYDirection(-1);
		}
	}
	
}
