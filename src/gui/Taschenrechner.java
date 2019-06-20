package gui;
import java.awt.*;           // Ur-Grafik

import javax.swing.*;        // Moderne Grafik

import java.awt.event.*;     // einige Unterkapitel müssen 
							 // extra importiert werden



public class Taschenrechner extends JFrame implements ActionListener 
{
	// Eigenschaften
	// Alle FrameObjekt auf die actionPerformed zugreiffen muss
	// müssen hier ausserhalb des Konstruktors deklariert werden
	
	JButton calc = new JButton("Berechnen");
	JButton ini;
	JRadioButton plus;
	JRadioButton minus;
	JRadioButton multi;
	JRadioButton divi;
	JRadioButton mod;
	JRadioButton percent;
	ButtonGroup opera;       
	
	JLabel zahl1 = new JLabel("Zahl1");
	JLabel zahl2 = new JLabel("Zahl2");
	JLabel Resultat = new JLabel("Resultat");
	JLabel ResultatAnzeige = new JLabel("---------");
	JTextField z1 = new JTextField("---");
	JTextField z2 = new JTextField("---");
	
	public Taschenrechner() 
	{
		// Konstruktor definiert Maskenaufbau
		ini   = new JButton("alles löschen");
		
		ini.setActionCommand("ini");
		ini.addActionListener(this);
		calc.setActionCommand("calc");
		calc.addActionListener(this);
		
		plus  = new JRadioButton("+");
		minus = new JRadioButton("-");
		multi = new JRadioButton("*");
		divi  = new JRadioButton("/");
		mod   = new JRadioButton("%");
		percent = new JRadioButton("per");

		plus.setActionCommand("plus");
		minus.setActionCommand("minus");
		multi.setActionCommand("multi");
		divi.setActionCommand("divi");
		mod.setActionCommand("mod");
		percent.setActionCommand("percent");

		
		// Nur eine Operation darf gewählt sein
		
		opera = new ButtonGroup();
		opera.add(plus);
		opera.add(minus);
		opera.add(multi);
		opera.add(divi);
		opera.add(mod);
		opera.add(percent);
		
		
		
		// Layout und Panel
		JPanel nord = new JPanel();
		nord.setLayout(new FlowLayout());//Flow Layout
		nord.add(calc);
		nord.add(ini);
		
		JPanel ost = new JPanel(); //z s za Spaltenabstand
		ost.setLayout(new GridLayout(6,1,20,10));//Grid Layout
		ost.add(plus);
		ost.add(minus);
		ost.add(multi);
		ost.add(divi);
		ost.add(mod);
		ost.add(percent);
		
		JPanel zentrum = new JPanel();
		zentrum.setLayout(null);//No Layout, Elements are given specific positions 
		
		zahl1.setBounds(10, 20, 80, 20);
		zahl2.setBounds(10, 40, 80, 20);
		Resultat.setBounds(10,60,80,20);
		z1.setBounds(100, 20, 80, 20);
		z2.setBounds(100, 40, 80, 20);
		ResultatAnzeige.setBounds(100,60,80,20);
		
		zentrum.add(zahl1);
		zentrum.add(zahl2);
		zentrum.add(z1);
		zentrum.add(z2);
		zentrum.add(Resultat);
		zentrum.add(ResultatAnzeige);
		
		this.setLayout(new BorderLayout());
		this.add(nord, BorderLayout.NORTH);
		this.add(ost,BorderLayout.EAST);
		this.add(zentrum, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setSize(380,220);
		this.setTitle("Taschenrechner");
	}
	
	public void actionPerformed(ActionEvent ev) 
	{
		// Programmablauf
		// welcher Button wurde gedrückt ?
		if(ev.getActionCommand().equals("ini"))
		{
			opera.clearSelection();
			z1.setText("");
			z2.setText("");
			ResultatAnzeige.setText("");
		}
		if(ev.getActionCommand().equals("calc"))
		{
			// Lesen vom Bildschirm
			ButtonModel mbm = opera.getSelection();    // welcher Radio-
			String operator = mbm.getActionCommand();  // Button ?
			
			double za1,za2,resultat = 0.0;
			za1 = Double.parseDouble(z1.getText());
			za2 = Double.parseDouble(z2.getText());
			
			// Berechnen
			if(operator.equals("plus")) { resultat = za1 + za2; }
			if(operator.equals("minus")) { resultat = za1 - za2; }
			if(operator.equals("multi")) { resultat = za1 * za2; }
			if(operator.equals("divi")) { resultat = za1 / za2; }
			if(operator.equals("mod")) { resultat = za1 % za2; }
			if(operator.equals("percent")) { resultat = za2 / za1 *100; }

			
			// Ausgabe
			
			String result = "" + resultat;
			if (operator.equals("percent")) {result = ""+resultat+"%";}
			ResultatAnzeige.setText(result);
		}
		
	}
	
	public static void main(String[] args) 	
	{
		Taschenrechner myTR = new Taschenrechner();
	}

}
