import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;
import java.awt.*;
import java.awt.*;
public class Gamepanel extends JPanel {

private int strikes;
private String currentword;
private String[] celebwords = {"Taylor Swift", "Drake", "Tom Hanks", "Emma Stone", "Brad Pitt", 
							   "Angelina Jolie", "Andrew Garfield", "Leonardo Dicaprio",
							  "Nicole Kidman", "Meryl Streep"}; 
private ArrayList<String> words;
private String ptword = " ";
private String firstword;
private ArrayList <Character> guessletters;
		
		public Gamepanel()
	{
		words = new ArrayList<String>();
		words.addAll(Arrays.asList(celebwords));
		strikes = 0;
		
		firstword = getnewword();
		createPaintword(firstword);
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(450,400));
		Border border =  BorderFactory.createEtchedBorder(Color.RED, Color.RED);
		TitledBorder title = BorderFactory.createTitledBorder(border,"Hangman");
		title.setTitleFont(new Font("Braggadocio", Font.BOLD, 24));
		title.setTitleColor(Color.RED);
		setBorder(title);

	}
		
	public String getCurrentWord()
	{
		return currentword;
	}
	
	public void updateGamew(String theword)
	{
		
	}
	
	public String getnewword()
	{
		guessletters = new ArrayList<Character>();
		if (!words.isEmpty())
		{
		int randindex = (int)(Math.random() * words.size());
	//	System.out.print(randindex); // check index
		currentword = words.get(randindex);
		words.remove(randindex);
		strikes = 0;
		return currentword;
		}
		else {
			System.out.println("Word ArrayList is empty Error"); // Test... Remove later
			return "ERROR";
		}
	}
	
	public void createPaintword(String paintword)
	{
		char [] arraychar = paintword.toCharArray();
		ptword = " ";
		for (int x = 0; x < arraychar.length; x++)
		{
			if (arraychar[x] == ' ')
			{
				ptword += ' ';
			}
			else
			{
				ptword += "_ ";
				
			}
		}
	
		repaint();
	
	}
	
	public String returnPaintversion(String paintword)
	{
		String paintversion = " ";
		
		char [] arraychar = paintword.toCharArray();
	
		for (int x = 0; x < arraychar.length; x++)
		{
			if (arraychar[x] == ' ')
			{
				paintversion += ' ';
			}
			else if (arraychar[x] == '_')
			{
				paintversion += "_";
			}
			else
			{
				paintversion += arraychar[x] + " ";
				
			}
		}
	
		repaint();
		return paintversion;
	
	}
	
	
	public boolean guessWord(String guess)
	{
		Boolean newgame = false;
		String guessword1 = guess.trim();
		String tempcurrentword = " ";
		String tempcurr2 = currentword.trim();
		for (int x = 0; x < tempcurr2.length(); x++)
		{
			if(tempcurr2.charAt(x) == ' ')
			{
				
			}
			
			else {
				tempcurrentword += tempcurr2.charAt(x);
			}
		}
		
		tempcurrentword = tempcurrentword.trim();
	//	System.out.println(guessword1);
	//	System.out.println(tempcurrentword);
	//	System.out.println(currentword);
		
		if(tempcurrentword.equalsIgnoreCase(guessword1) || currentword.equalsIgnoreCase(guessword1))
		{
			ptword = returnPaintversion(currentword);
			if (words.isEmpty())
			{
			System.out.println("Words is Empty");
			strikes = -1;
			newgame = true;
			}
			repaint();
		}
		
		else {
			strikes = 10;
			newgame = true;
		}
		
		return newgame;
		
		
	}
	
	public int guessChar(char let)
	{	String tempS = " ";
		char letter = Character.toUpperCase(let);
		boolean nostrike = false;
//		System.out.println(currentword);   // erase later
		if (!guessletters.contains(letter))
		{
		
		for (int x = 0; x < currentword.length(); x++ )
		{
			char temp = currentword.toUpperCase().charAt(x);
			if (temp == letter)
			{
				tempS += letter + " ";
				nostrike = true;
			}
			else if (temp == ' ')
			{
				tempS += " ";
			}
			
			else if (!guessletters.isEmpty()) {
				boolean match = false;
				
				for(int anum = 0; anum < guessletters.size(); anum++)
				{
					char arrachar = guessletters.get(anum);
					arrachar = Character.toUpperCase(arrachar);
					if (arrachar == temp)
					{
						tempS += arrachar + " ";
						match = true;
					}
				}
				
				if (!match)
				{
					tempS += "_ ";
				}
			}
			
			else {
				tempS += "_ ";
			}
			
		}
		} // end of if for guessletters
		
		if (!nostrike)
		{
			System.out.println("WRONG");
			strikes++;
		}
		else {
			guessletters.add(letter);
			ptword = tempS.trim();
		//	System.out.println(ptword); // test
			String finiwrd = returnPaintversion(currentword);
			finiwrd = finiwrd.trim();
		//	System.out.println(finiwrd); // test
			if (ptword.equalsIgnoreCase(finiwrd))
			{
				if (words.isEmpty())
				{
					strikes = -1;
				}
				else {
				strikes = 10;
     			System.out.println("You Win");
				}
			}
		}
		System.out.println(strikes);
		repaint();
		return strikes;
	}
	
	
	
	
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(153,0,0));
		g2.setStroke(new BasicStroke(5));
		// hangman figure
		
		g2.drawLine(125, 75, 125, 275); // straigh vertical line
		g2.drawLine(75, 275, 175, 275); // baseline
		g2.drawLine(125, 75, 225, 75); // topline
		g2.drawLine(127, 105, 152, 77); // crossline
		g2.drawLine(225, 75, 225, 100); // dropline
		
		if (strikes == -1) {
			String winner = "Congratulations!! You guessed all the words!";
			g2.setColor(Color.RED);
			g2.setFont(new Font("Braggadocio", Font.BOLD, 14));
			g2.drawString(winner, (250 - (winner.length() * 5)), 325);
		}
		
		
		if(strikes >= 1) // draw head
		{	g2.setColor(Color.BLACK);
			g2.drawOval(210, 100, 30, 30);
		}
		
		if(strikes >= 2) // draw body
		{
			g2.drawLine(225, 130, 225, 200);
			
		}
		
		if(strikes >= 3) // draw left leg
		{
			g2.drawLine(225, 150, 205, 175);
		}
		
		if(strikes >= 4) // draw right leg
		{
			g2.drawLine(225, 200, 245, 230);
		}
		
		if(strikes >= 5)// draw right arm
		{
			g2.drawLine(225, 150, 245, 175);
		}
		
		if(strikes >= 6) // draw left arm
		{
			g2.drawLine(225, 200, 205, 230);
		}
		
		
		if (strikes >= 10)
		{
			
			System.out.println("You Lose"); /// delete
		
		}
			
		
		if (ptword != null)
		{
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Braggadocio", Font.BOLD, 25));
		g2.drawString(ptword, (200 - (ptword.length() * 5)), 375);
		}
	}
// end of class	
}
