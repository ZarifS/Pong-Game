import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

//Paddles for Pong
public class Paddle implements Runnable {
	int x, y, yDirection, id;
	
	Rectangle paddle;
	 
	public Paddle(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id=id;
		paddle = new Rectangle(x, y, 20, 75);
		
	}
	
	public void keyReleased(KeyEvent e){
		switch(id){
			default:
				System.out.println("Please enter a valid ID in Paddle Constructor");
				break;
			case 2:
				if(e.getKeyCode() == e.VK_UP){
					setYDirection(0);
				}
				if(e.getKeyCode() == e.VK_DOWN){
					setYDirection(0);	
				}
				break;
			case 1: 
				if(e.getKeyCode() == e.VK_W){
					setYDirection(0);
			
				}
				if(e.getKeyCode() == e.VK_S){
					setYDirection(0);
				}
				
				break;
		}
	//Determines which paddle is paddle one and two
	}
	
	public void keyPressed(KeyEvent e){
		switch(id){
			default:
				System.out.println("Please enter a valid ID in Paddle Constructor"); //Error Check 
				break;
			case 2:
				if(e.getKeyCode() == e.VK_UP){
					setYDirection(-1);
				}
				if(e.getKeyCode() == e.VK_DOWN){
					setYDirection(+1);	
				}
				break;
			case 1: 
				if(e.getKeyCode() == e.VK_W){
					setYDirection(-1);
			
				}
				if(e.getKeyCode() == e.VK_S){
					setYDirection(+1);
				}
				
				break;
		}
	
	}
	
	public void draw(Graphics g){
		switch(id){
		default:
			System.out.println("Please enter a valid ID in Paddle Constructor");
			break;
		case 1:
			g.setColor(Color.CYAN);
			g.fillRect(paddle.x -5, paddle.y, paddle.width, paddle.height);
			break;
		
		case 2:
			g.setColor(Color.PINK);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;		
		} //Draws the paddles 
	}
	
	public void setYDirection(int yDir){
		yDirection = yDir;
	}
	
	public void move(){
		paddle.y += yDirection;
		if(paddle.y<= 20)
			paddle.y=20;
		if(paddle.y>=415)
			paddle.y= 415;
		// Moves the paddle 
	}
	
	public void run(){
		try{
			while(true){
				move();
				Thread.sleep(3); //Sets speed of paddle 
			}
			
		}catch(Exception e){System.err.println(e.getMessage());}
	}

}
