// Main Class for Pong Game By: Zarif Shahriar

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;



public class Main extends JFrame {

	//Double Buffering
	Image dbImage;
	Graphics dbg;
	
	//Ball objects
	static Ball b= new Ball(293,243);
	
	//Ball Threads
	Thread ball = new Thread(b);
	Thread p1 = new Thread(b.p1);
	Thread p2 = new Thread(b.p2);
	
	//Booleans for difficulty
	boolean hardDifficulty = false;
	
	//Boolean for Game state
	boolean gameStarted = false;
	
	//Boolean for hovering over menu buttons 
	boolean startHover;
	boolean difHover;
	
	//Menu Buttons 
	Rectangle startButton = new Rectangle (250, 200, 100, 45);
	Rectangle difficultyButton = new Rectangle (250, 250, 100, 45);
	Rectangle GameMode = new Rectangle (250, 300, 100, 45);
	
	// Variables for screen size
	int GWIDTH = 600;
	int GHEIGHT = 500;
	Dimension dimension = new Dimension(GWIDTH, GHEIGHT);

    public Main() {
	
    	// JFrame Properties  
    	this.setSize(dimension);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Pong Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        //Action Listener
        this.addKeyListener(new AL());
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
    }

    public void startGame(){
    	gameStarted = true;
    	ball.start();
    	p1.start();
    	p2.start();
    }
    
    public void GameMode(){
 
    }
    
    public class AL extends KeyAdapter {
    	//Listener for pressed keys

        public void keyPressed(KeyEvent e) {
    		b.p1.keyPressed(e);
    		b.p2.keyPressed(e);	
	    }

        public void keyReleased(KeyEvent e) {
        	b.p1.keyReleased(e);
    		b.p2.keyReleased(e);
        }
        
       
    }
    
    public void paint(Graphics g){
    	dbImage = createImage(getWidth(), getHeight());
    	dbg = dbImage.getGraphics();
    	draw(dbg);
    	g.drawImage(dbImage, 0, 0, this);
    }
    
    public void draw(Graphics g){
    	// Menu Drawings
    	if(!gameStarted){
    	g.setFont(new Font("Impact", Font.PLAIN, 26));
	    	g.setColor(Color.WHITE);
	    	g.drawString("Pong Game", 240,175);
	    	if(!startHover)
	    		g.setColor(Color.CYAN);
	    	else
	    		g.setColor(Color.PINK);
	    	g.fillRect(startButton.x,  startButton.y,  startButton.width, startButton.height);
	    	g.setFont(new Font("Impact", Font.PLAIN,15));
	    	g.setColor(Color.WHITE);
	    	g.drawString("Start Game", startButton.x+15, startButton.y+27);
	    	g.setColor(Color.WHITE);
	    	g.drawString("Copyright: Shahriar Inc.", 10, 490);
	    	if(!difHover)
	    		g.setColor(Color.CYAN);
	    	else
	    		g.setColor(Color.PINK);
	    	g.fillRect(difficultyButton.x, difficultyButton.y, difficultyButton.width, difficultyButton.height);
	    	g.setColor(Color.WHITE);
	    	g.drawString("Difficulty:", difficultyButton.x+5, difficultyButton.y+25);
	    	if(!hardDifficulty){
	    	g.setColor(Color.BLUE);
	    	g.drawString("Easy", difficultyButton.x+65, difficultyButton.y+25);
	    	}
	    	else{
	    		g.setColor(Color.RED);
	    		g.drawString("Hard", difficultyButton.x+65, difficultyButton.y+25);
	    	}
	    	g.setFont(new Font("Impact", Font.PLAIN,20));
	    	g.setColor(Color.YELLOW);
	    	g.drawString("How to Play", 255, 330);
	    	g.setFont(new Font("Impact", Font.PLAIN,15));
	    	g.drawString("The goal is to get the ball to the other end of the screen. First to 10 wins.", 90, 355);
	    	g.drawString("Player 1 is blue and will move with the W and S keys.", 146, 370);
	    	g.drawString("Player 2 is red and will move with the up and down keys.", 135, 385);
	    	
	    	
    	}
    	
    	else{
	    	//Game Drawings
	    	b.draw(g);
	    	b.p1.draw(g);
	    	b.p2.draw(g);
	    	g.setFont(new Font("Impact", Font.PLAIN,15));
	    	g.setColor(Color.WHITE);
	    	g.drawString("Copyright: Shahriar Inc.", 10, 490);
	    	//Score
	    	g.setFont(new Font("Arial", Font.BOLD, 20));
	    	g.drawString(""+b.p1Score, 200, 60);
	    	g.drawString(""+b.p2Score, 400, 60);
	    if (b.p1Score ==10){
	    	g.setColor(Color.CYAN);
	    	g.setFont(new Font("Impact", Font.PLAIN,20));
	    	g.drawString("Blue Player Wins!", 230, 250);
	    	b.setDifficulty(5000);
	    }
	    	
	    if (b.p2Score ==10){
	    		g.setColor(Color.PINK);
		    	g.setFont(new Font("Impact", Font.PLAIN,20));
		    	g.drawString("Red Player Wins!", 230, 250);
		    	b.setDifficulty(5000); 	
	    	
	    }
    	
    	}
    	repaint();
    }
    
    
    public class MouseHandler extends MouseAdapter {

    	public void mouseMoved(MouseEvent e){
    		//Checks if mouse is over (hovers) the buttons 
    		int mx = e.getX();
        	int my = e.getY();
        	if((mx > startButton.x && mx < (startButton.x+startButton.width)) &&
        	   (my > startButton.y && my < (startButton.y+startButton.height))){
        	   startHover = true; }
        	else 
        		startHover = false;
        	if((mx > difficultyButton.x && mx < (difficultyButton.x+difficultyButton.width)) &&
             	   (my > difficultyButton.y && my < (difficultyButton.y+difficultyButton.height))){
             	   difHover = true; }
             	else 
             		difHover = false;
        	// If the mouse is over sets the boolean to true to change color 	
    	}
    	
    	public void mousePressed(MouseEvent e){
    	// Checks if the mouse pressed each button 
    	int mx = e.getX();
    	int my = e.getY();
    	if((mx > startButton.x && mx < (startButton.x+startButton.width)) &&
    	   (my > startButton.y && my < (startButton.y+startButton.height))){
    	   startGame();	
    		}
    	if((mx > difficultyButton.x && mx < (difficultyButton.x+difficultyButton.width)) &&
    	    	   (my > difficultyButton.y && my < (difficultyButton.y+difficultyButton.height))){
    		
    		//Toggling functionality 
    		if(!hardDifficulty){
    			b.setDifficulty(1);
    			hardDifficulty = true;
    		}
    		else{
    			b.setDifficulty(3);
    			hardDifficulty = false;
    		}
    	}
    	}
   }
    
    //Method to play background music
    public static void play() {
    		//new thread for music only 
			new Thread() {
				@Override
				public void run() {
					try {
						File file = new File("music.wav");
						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(file));
						clip.start();
						// Creates the music file and clip 
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}.start();
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //Starts the tread 
		}
	

    
    
    public static void main(String[] args) {
        Main m = new Main();
        play(); //calls the frame and plays music 
       
    }
}