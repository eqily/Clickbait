import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JComponent{
	private final int WIDTH;
	private final int HEIGHT;
	private final Image target;
	private int targetX;
	private int targetY;
	private Timer t;
  private int score;
  private int ticks;
  private static final int MAX_TICKS = 15;
  private boolean gameOver;
  private boolean clicked;

	public Canvas(int width, int height, String image){
		WIDTH = width;
		HEIGHT = height;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		target = new ImageIcon(image).getImage();
    moveTarget();
    score = 0;
    ticks = 0;
    gameOver = false;
    clicked = false;

		class TimerListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				moveTarget();
        clicked = false;
        ticks++;
        if(ticks > MAX_TICKS){
          t.stop();
          gameOver = true;
        }
			}
		}
		t = new Timer(500, new TimerListener());


    class CanvasMouseListener implements MouseListener{
      //all 5 methods required, otherwise it will not compile
      public void mouseClicked(MouseEvent e){}//requires press and release without movement
      public void mousePressed(MouseEvent e){
        if(!gameOver && !clicked)
          clickCheck(e.getX(), e.getY());
      }
      public void mouseReleased(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
    }
    addMouseListener(new CanvasMouseListener());

    class CanvasKeyListener implements KeyListener{
      public void keyPressed(KeyEvent e){}
      public void keyReleased(KeyEvent e){}
      public void keyTyped(KeyEvent e){
        System.out.println(e.getKeyChar());//will send clicked key char to console
       if(e.getKeyChar() == 'r'){
         reset();
       }
      }
    }
    addKeyListener(new CanvasKeyListener());
    //we have to give focus to listen 
    setFocusable(true);
    requestFocus();

		t.start();
	}

  public void reset(){
    moveTarget();
    score = 0;
    ticks = 0;
    gameOver = false;
    clicked = false;
    t.start();
  }

  public void clickCheck(int x, int y){
    if(x >= targetX && x <= targetX + target.getWidth(this) && y >= targetY && y <= targetY + target.getHeight(this)){
      score++;
      clicked = true;
      System.out.println(score);
    }
  }

	public void moveTarget(){
		targetX = (int) (Math.random() * (WIDTH - target.getWidth(this)));
		targetY = (int) (Math.random() * (HEIGHT - target.getHeight(this)));
    repaint();
	}

	public void paintComponent(Graphics gr){
		Graphics2D g = (Graphics2D) gr;
		g.drawImage(target, targetX, targetY, this);
    g.drawString("Score: " + score, 5, 15);
	}
}