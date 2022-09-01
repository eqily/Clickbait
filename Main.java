import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
	public static void main(String[] args){
		JFrame frame = new JFrame("Click Bait");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Canvas canvas = new Canvas(700,500, "luma.png");
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}