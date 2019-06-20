package gui;

import java.awt.event.*;
import javax.swing.*;


public class Eingabe extends JFrame implements ActionListener
{
/**
 * Class creates simple GUI with commentary box filled in with set values
 */
	private static final long serialVersionUID = 1L;
	JTextField username = new JTextField(15);
	JPasswordField password = new JPasswordField(15);
	JTextArea comments = new JTextArea(4,15);
	JButton senden = new JButton("Senden");
	JButton kontrolle = new JButton("Kontrolle");
	JCheckBox grossklein = new JCheckBox("Gross=Klein");
	
	public Eingabe() //Constructor
	{
		super("Rückmelde Formular");
		setSize(260,260);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//senden.setText("Senden");
		
		JPanel pane = new JPanel();
		JLabel usernameLabel = new JLabel("Benutzer:");
		JLabel passwordLabel = new JLabel("Password:");
		
		JLabel commentsLabel = new JLabel("Bemerkungen:");
		comments.setLineWrap(true);
		comments.setWrapStyleWord(true);
		
		senden.addActionListener(this);
		kontrolle.addActionListener(this);
		grossklein.addActionListener(this);
		
		//alles einbauen
		pane.add(usernameLabel);
		pane.add(username);
		pane.add(passwordLabel);
		pane.add(password);
		pane.add(commentsLabel);
		pane.add(comments);
		pane.add(senden);
		pane.add(kontrolle);
		pane.add(grossklein);
		setContentPane(pane);
		
		//show()
		this.setVisible(true);
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Eingabe eingabe = new Eingabe();
		eingabe.addWindowListener(null);
	}

	@Override
	public void actionPerformed(ActionEvent evt) //required action listener
	{
		if(evt.getSource() == kontrolle)
		{
			comments.setText("Kontrolle");
		}
		else
		{
			if(evt.getSource() == grossklein)
			{
				if(grossklein.isSelected())
				{
					comments.setText("GROSS=klein");
				}
				else
				{
					comments.setText("GROSS <> klein");
				}
			}
			else
			{
				String ausgabe;
				ausgabe= username.getText() + " = "+password.getText();
				comments.setText(ausgabe);
			}
		}
		
	}

}
