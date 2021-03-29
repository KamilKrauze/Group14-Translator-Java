import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

import java.awt.event.*;

/**
 * 
 * @author Kamil Krauze
 * 
 */
public class Menu extends JFrame {
	
	String[] langList = {"English", "Spanish"}; 
	
	public static void main(String[] Args)
	{
		Menu menu = new Menu();
		menu.modifyMenu();
	}
	
	public void mainMenu()
	{
		int panelOffset = 60;
		
//////////////////////// PANELS ////////////////////////////////////////
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0,0,800,600);
		panelMain.setLayout(null);
		
		JPanel langOptions = new JPanel();
		langOptions.setBackground(Color.gray);
		langOptions.setBounds(5,40,780,50);
		langOptions.setLayout(null);
		
		JPanel initLang = new JPanel();
		initLang.setBackground(Color.gray);
		initLang.setBounds(25,5,200,40);
		initLang.setLayout(null);
		
		JPanel newLang = new JPanel();
		newLang.setBackground(Color.gray);
		newLang.setBounds(400,5,200,40);
		newLang.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.gray);
		panel1.setBounds(25,35+panelOffset,350,325);
		panel1.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.gray);
		panel2.setBounds(400,35+panelOffset,350,325);
		panel2.setLayout(null);

		JPanel translate = new JPanel();
		translate.setBackground(Color.gray);
		translate.setBounds(25,375+panelOffset-10,725,60);
		translate.setLayout(null);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.gray);
		btnPanel.setBounds(25,490,725,60);
		btnPanel.setLayout(null);
		
//////////////////////////////// PANEL COMPONENTS ////////////////////////////////////////
		
		JComboBox initLangBox = new JComboBox(langList);
		initLangBox.setSelectedIndex(0);
		initLangBox.setBounds(5,0,150,40);
		//languages.addActionListener(this);
		
		JComboBox newLangBox = new JComboBox(langList);
		newLangBox.setSelectedIndex(1);
		newLangBox.setBounds(5,0,150,40);
		
		JLabel greeting = new JLabel();
		greeting.setBounds(-50, 15, 500, 25);
		greeting.setText("Welcome");
		greeting.setFont(new Font("Verdana", Font.BOLD, 25));
		greeting.setVerticalAlignment(JLabel.BOTTOM);
		greeting.setHorizontalAlignment(JLabel.RIGHT);
		
		JTextArea translatee = new JTextArea();
		translatee.setBounds(5,5,340,315);
		translatee.setFont(new Font("Verdana", Font.PLAIN, 15));
		translatee.setText("Enter here the text you want to translate.");
		translatee.setLineWrap(true);
		translatee.setWrapStyleWord(true);
		
		JTextArea translated = new JTextArea();
		translated.setBounds(5,5,340,315);
		translated.setFont(new Font("Verdana", Font.PLAIN, 15));
		translated.setText("The translated text will be outputted here.");
		translated.setLineWrap(true);
		translated.setWrapStyleWord(true);
		
		JButton translateBTN = new JButton();
		translateBTN.setBounds(310,5,100,50);
		translateBTN.setText("Translate");
		translateBTN.setToolTipText("Translate your text");
		
		int btnOffset = 150;
		
		JButton load = new JButton();
		load.setBounds(5+btnOffset,5,100,50);
		load.setText("Load");
		load.setToolTipText("Load a text file that would you want to translate from");
		
		JButton save = new JButton();
		save.setBounds(110+btnOffset,5,100,50);
		save.setText("Save");
		save.setToolTipText("Save your translation to a text file");
		
		JButton test = new JButton();
		test.setBounds(215+btnOffset,5,100,50);
		test.setText("Test");
		test.setToolTipText("Run a test");
		
		JButton modify = new JButton();
		modify.setBounds(320+btnOffset,5,100,50);
		modify.setText("Modify");
		modify.setToolTipText("Modify the translation");
		
//////////////////// APP FRAME ///////////////////////////////////	
	
		JFrame menu = new JFrame();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setLayout(null);
		menu.setSize(800,600);
		menu.setBackground(Color.darkGray);
		menu.setTitle("Translator");
		menu.setResizable(false);
		
/////////////////// APPLYING TO FRAME ///////////////////////////////////
		
		menu.add(langOptions);
			
			langOptions.add(initLang);
				initLang.add(initLangBox);
			
			langOptions.add(newLang);
				newLang.add(newLangBox);
			
		menu.add(panel1);
			panel1.add(translatee);
		
		menu.add(panel2);
			panel2.add(translated);
		
		menu.add(translate);
			translate.add(translateBTN);
		
		menu.add(btnPanel);
			btnPanel.add(load);
			btnPanel.add(save);
			btnPanel.add(test);
			btnPanel.add(modify);
	
		panelMain.add(greeting);
			menu.add(panelMain);
		
		menu.setVisible(true);
	}
	
	public void modifyMenu()
	{
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0,0,800,600);
		panelMain.setLayout(null);
		
		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.BLUE);
		langPanel.setBounds(5,5,775,475);
		langPanel.setLayout(null);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.RED);
		btnPanel.setBounds(5,485,775,60);
		btnPanel.setLayout(null);
		
		JTextArea phraseBox = new JTextArea();
		phraseBox.setBounds(1,1,773,473);
		phraseBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		phraseBox.setLineWrap(true);
		phraseBox.setWrapStyleWord(true);
		
		JButton ConfirmBTN = new JButton();
		ConfirmBTN.setBounds(270,5,100,50);
		ConfirmBTN.setText("Confirm");
		ConfirmBTN.setToolTipText("Confirm your current modifications");
		
		JButton ApplyBTN = new JButton();
		ApplyBTN.setBounds(375,5,100,50);
		ApplyBTN.setText("Apply");
		ApplyBTN.setToolTipText("Apply your current modifications");
		
		JScrollPane scrollBar1 = new JScrollPane(phraseBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JFrame modMenu = new JFrame();
		modMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modMenu.setLayout(null);
		modMenu.setSize(800,600);
		modMenu.setBackground(Color.gray);
		modMenu.setTitle("Modify");
		modMenu.setResizable(false);
		
		modMenu.add(panelMain);
			panelMain.add(langPanel);
				langPanel.add(phraseBox);
				langPanel.add(scrollBar1);
			panelMain.add(btnPanel);
				btnPanel.add(ConfirmBTN);
				btnPanel.add(ApplyBTN);
		
		modMenu.setVisible(true);
	}
}
