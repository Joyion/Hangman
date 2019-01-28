import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hangman extends JFrame {
	private int wwidth = 500;
	private int wheight = 500;
	private JPanel mainp;
	private JLabel title;
	private JButton onep;
	private JButton twop;
	
	
	Gamepanel onegamep;
	JPanel onegamep2;
	JPanel gameplaypanel;
	JPanel newgamepanel;
	JPanel newwordpanel;
	Boolean gamewon;
	
	JTextField charbox;
	JTextField guessbox;
	JButton guess;
	JButton addchar;
	JButton quit;
	JButton quit2;
	JButton newgamebt;
	JButton newwordbt;
	
	int strikes;
	String currentword;
	
	
	public static void main(String[] args) {
		
		Hangman rungame = new Hangman();
		
	}
	
	public Hangman()
	{
		setLayout(new BorderLayout(0, 0));
		setTitle("Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(wwidth, wheight);
		getContentPane().setBackground(Color.WHITE);
		buildMainPanel();
		setVisible(true);
		
	}
	
	private void buildMainPanel()
	{
		mainp = new JPanel();
		mainp.setLayout(new BorderLayout());
		mainp.setBackground(Color.white);
//		JPanel centermain = new JPanel();
		
		JPanel bottommain = new JPanel ();
		
		title = new JLabel("Hangman");
		title.setFont(new Font("Braggadocio", Font.BOLD, 40));	
		title.setForeground(Color.RED);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setSize(100, 100);
		
		onep = new JButton("Start Game");
		onep.addActionListener(new StartGameListener());
		
	/*	twop = new JButton("Two Players");
		twop.addActionListener(new StartGameListener()); */  // I plan to make to make a Two Player version
		
		onep.setFont(new Font("Arial", Font.BOLD, 16));
		onep.setForeground(Color.RED);
		onep.setBackground(Color.WHITE);
		
	/*	twop.setFont(new Font("Arial", Font.BOLD, 16));
		twop.setForeground(Color.RED);
		twop.setBackground(Color.WHITE); */
		
		
		
		
		bottommain.add(onep);
//		bottommain.add(twop);			// 2 player
		bottommain.setBackground(Color.WHITE);
		mainp.add(title, BorderLayout.CENTER);
		mainp.add(bottommain, BorderLayout.SOUTH);
		
		setContentPane(mainp);
	}
	
	
/*	private void buildLevelpain()
	{
		
		
	}
*/
	
	private void buildHangman()
	{
		onegamep = new Gamepanel();
		onegamep2 = new JPanel();
		onegamep2.setPreferredSize(new Dimension(500, 100));
		gameplaypanel = new JPanel();
		gameplaypanel.setForeground(Color.WHITE);
		gameplaypanel.setBackground(Color.WHITE);
		
		
		charbox = new JTextField(1);
		charbox.setFont(new Font("Arial", Font.BOLD, 25));
		
		guessbox = new JTextField(10);
		guessbox.setFont(new Font("Arial", Font.PLAIN, 14));
		
		guess = new JButton("Guess Word");
		guess.addActionListener(new StartGameListener());
		guess.setForeground(Color.RED);
		guess.setFont(new Font("Arial", Font.BOLD, 12));
		guess.setBackground(Color.WHITE);
		
		addchar = new JButton("Guess Character");
		addchar.addActionListener(new StartGameListener());
		addchar.setForeground(Color.RED);
		addchar.setFont(new Font("Arial", Font.BOLD, 12));
		addchar.setBackground(Color.WHITE);
		
		quit = new JButton("QUIT");
		quit.addActionListener(new StartGameListener());
		quit.setForeground(Color.RED);
		quit.setFont(new Font("Arial", Font.BOLD, 12));
		quit.setBackground(Color.WHITE);
		
		quit2 = new JButton("QUIT");
		quit2.addActionListener(new StartGameListener());
		quit2.setForeground(Color.RED);
		quit2.setFont(new Font("Arial", Font.BOLD, 12));
		quit2.setBackground(Color.WHITE);
		
		
		
		
		newgamebt = new JButton("New Game");
		newgamebt.addActionListener(new StartGameListener());
		newgamebt.setForeground(Color.RED);
		newgamebt.setFont(new Font("Arial", Font.BOLD, 12));
		newgamebt.setBackground(Color.WHITE);
		
		newwordbt = new JButton("Next Word");
		newwordbt.addActionListener(new StartGameListener());
		newwordbt.setForeground(Color.RED);
		newwordbt.setFont(new Font("Arial", Font.BOLD, 12));
		newwordbt.setBackground(Color.WHITE);
	
		
	JPanel Charpanel = new JPanel();
	Charpanel.add(charbox);
	Charpanel.add(addchar);
	JPanel guesspanel = new JPanel();
	guesspanel.add(guessbox);
	guesspanel.add(guess);
	
	
	
	newgamepanel = new JPanel();   // possibly remove
	newgamepanel.add(newgamebt);
	newgamepanel.setBackground(Color.WHITE);
	
	
	newwordpanel = new JPanel();   // possibly remove
	newwordpanel.add(newwordbt);
	newwordpanel.add(quit2);
	newwordpanel.setBackground(Color.WHITE);

	
	
	onegamep2.add(Charpanel);
	onegamep2.add(guesspanel);
	onegamep2.add(quit);

	Charpanel.setBackground(Color.WHITE);
	guesspanel.setBackground(Color.WHITE);
	onegamep2.setBackground(Color.WHITE);

	
	gameplaypanel.add(onegamep);
	gameplaypanel.add(onegamep2);
	setContentPane(gameplaypanel);
	validate();

	}
	
	private void endGame(boolean matchwon)
	{
		if(matchwon) {
			onegamep2.setVisible(false);
			gameplaypanel.remove(onegamep2);
			gameplaypanel.add(newgamepanel);
			gameplaypanel.validate();
			validate();
			
		}
		else {
		onegamep2.setVisible(false);
		gameplaypanel.remove(onegamep2);
		gameplaypanel.add(newwordpanel);
		newwordpanel.setVisible(true);
		gameplaypanel.validate();
		validate();
	
		}
		
		
		
	} 

	
	private class StartGameListener implements ActionListener
	{
		// action perfomed for all buttons
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();
			
			if (action.equals("Start Game"))
			{
				buildHangman();
			// end of if
			}
			
			else if(action.equals("Guess Character"))
			{
				char x;
				String z = charbox.getText();
				z = z.trim();
				
				if(z.length() > 1) {
				 System.out.print("1 character only");
				}
				else 
				{
				x = z.charAt(0);
				int strikes = onegamep.guessChar(x);
				if (strikes == 6 || strikes == -1)
				{
					boolean winorlose = true;
					endGame(winorlose);
				}
				else if (strikes == 10) {
					boolean winorlose = false;
					endGame(winorlose);
				}
				}
				
				charbox.setText(" ");
				
			 // end of else if action equals
			}
			
			else if (action.equals("Guess Word"))
			{
				String wordguess = guessbox.getText();
				guessbox.setText(" ");
				boolean winorlose = onegamep.guessWord(wordguess);
				endGame(winorlose);
				
			}
			
			else if (action.equals("QUIT"))
			{
				setContentPane(mainp);
			}
			
			else if (action.equals("New Game"))
			{
				setContentPane(mainp);
			}
			
			else if (action.equals("Next Word"))
			{
				String word = onegamep.getnewword();
				onegamep.createPaintword(word);
				newwordpanel.setVisible(false);
				gameplaypanel.remove(newwordpanel);
				gameplaypanel.add(onegamep2);
				onegamep2.setVisible(true);
				gameplaypanel.validate();
				validate();
				
			}
			
			// end of action performed method
		}
		// end of StartGameListener class
	}

	// end of Hangman Class
}
