import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

import java.awt.event.*;

/**
 *
 * @author Kamil Krauze
 * @version 02/04/2021
 */
public class Menu extends JFrame {

	private enum langList
	{
		English,
		Spanish
	}

	public static void main(String[] Args)
	{
		Menu menu = new Menu();
		menu.mainMenu();
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

		JComboBox initLangBox = new JComboBox(langList.values());
		initLangBox.setSelectedIndex(0);
		initLangBox.setBounds(5,0,150,40);

		JComboBox newLangBox = new JComboBox(langList.values());
		newLangBox.setSelectedIndex(1);
		newLangBox.setBounds(5,0,150,40);

		JLabel greeting = new JLabel();
		greeting.setBounds(-50, 15, 500, 25);
		greeting.setText("Welcome");
		greeting.setFont(new Font("Verdana", Font.BOLD, 25));
		greeting.setVerticalAlignment(JLabel.BOTTOM);
		greeting.setHorizontalAlignment(JLabel.RIGHT);

		JTextArea toTranslate = new JTextArea();
		toTranslate.setBounds(5,5,340,315);
		toTranslate.setFont(new Font("Verdana", Font.PLAIN, 15));
		toTranslate.setText("Enter here the text you want to translate.");
		toTranslate.setLineWrap(true);
		toTranslate.setWrapStyleWord(true);

		JScrollPane TranslateScroll = new JScrollPane(toTranslate);
		TranslateScroll.setBounds(0,5,340,315);

		JTextArea translated = new JTextArea();
		translated.setBounds(5,5,340,315);
		translated.setFont(new Font("Verdana", Font.PLAIN, 15));
		translated.setText("The translated text will be outputted here.");
		translated.setLineWrap(true);
		translated.setWrapStyleWord(true);

		JScrollPane TranslatedScroll = new JScrollPane(translated);
		TranslatedScroll.setBounds(5,5,340,315);

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
		modify.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(action.equals("Modify"))
				{
					modifyMenu();
				}
			}
		});
		modify.setActionCommand("Modify");

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
			panel1.add(this.add(TranslateScroll));


		menu.add(panel2);
			panel2.add(this.add(TranslatedScroll));

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
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0,0,800,600);
		panelMain.setLayout(null);

		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5,5,775,475);
		langPanel.setLayout(null);

		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.gray);
		btnPanel.setBounds(5,485,775,60);
		btnPanel.setLayout(null);

		JTextArea phraseBox = new JTextArea();
		phraseBox.setBounds(1,1,773,473);
		phraseBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		phraseBox.setLineWrap(true);
		phraseBox.setWrapStyleWord(true);
		phraseBox.setText("");

		JScrollPane phraseScroll = new JScrollPane(phraseBox);
		phraseScroll.setBounds(1,1,773,473);

		JButton ConfirmBTN = new JButton();
		ConfirmBTN.setBounds(480,5,100,50);
		ConfirmBTN.setText("Confirm");
		ConfirmBTN.setToolTipText("Confirm your current modifications");

		JButton ApplyBTN = new JButton();
		ApplyBTN.setBounds(585,5,100,50);
		ApplyBTN.setText("Apply");
		ApplyBTN.setToolTipText("Apply your current modifications");

		JButton AddBTN = new JButton();
		AddBTN.setBounds(165,5,100,50);
		AddBTN.setText("Add");
		AddBTN.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(action.equals("Add"))
				{
					addMenu();
				}
			}
		});
		AddBTN.setActionCommand("Add");

		JButton DelBTN = new JButton();
		DelBTN.setBounds(60,5,100,50);
		DelBTN.setText("Delete");
		DelBTN.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(action.equals("Delete"))
				{
					DeleteMenu();
				}
			}
		});
		DelBTN.setActionCommand("Delete");

		JButton EditBTN = new JButton();
		EditBTN.setBounds(270,5,100,50);
		EditBTN.setText("Edit");
		EditBTN.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(action.equals("Edit"))
				{
					EditMenu();
				}
			}
		});
		EditBTN.setActionCommand("Edit");

		JFrame modMenu = new JFrame();
		modMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modMenu.setLayout(null);
		modMenu.setSize(800,600);
		modMenu.setBackground(Color.gray);
		modMenu.setTitle("Modify");
		modMenu.setResizable(false);

		modMenu.add(panelMain);
			panelMain.add(langPanel);
				langPanel.add(this.add(phraseScroll));
			panelMain.add(btnPanel);
				btnPanel.add(AddBTN);
				btnPanel.add(DelBTN);
				btnPanel.add(EditBTN);
				btnPanel.add(ConfirmBTN);
				btnPanel.add(ApplyBTN);

		modMenu.setVisible(true);
	}

	public void addMenu()
	{

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0,0,400,300);
		panelMain.setLayout(null);

		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5,5,375,250);
		langPanel.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(15,3,173,25);
		label1.setText("English word:");

		JTextArea EngBox = new JTextArea();
		EngBox.setBounds(15,25,173,25);
		EngBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EngBox.setLineWrap(true);
		EngBox.setWrapStyleWord(true);
		EngBox.setText("");

		JLabel label2 = new JLabel();
		label2.setBounds(15,78,173,25);
		label2.setText("Spanish word:");

		JTextArea EspBox = new JTextArea();
		EspBox.setBounds(15,100,173,25);
		EspBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EspBox.setLineWrap(true);
		EspBox.setWrapStyleWord(true);
		EspBox.setText("");

		JLabel label3 = new JLabel();
		label3.setBounds(15,153,173,25);
		label3.setText("Word class:");

		JComboBox WordClassBox = new JComboBox(WordType.values());
		WordClassBox.setSelectedIndex(0);
		WordClassBox.setBounds(15,175,173,25);

		JButton button1 = new JButton();
		button1.setBounds(225,25,125,75);
		button1.setText("Add");
		button1.setToolTipText("Please add text to all text fields then press this button to add your word to the dictionary.");
		button1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(EngBox.getText().trim().length() != 0 || EspBox.getText().trim().length() != 0)
				{
					Dictionary dictionary = new Dictionary();

					new DictionaryEntry(EngBox.getText(), EspBox.getText(), String.valueOf(WordClassBox.getSelectedItem()));
					JOptionPane.showMessageDialog(null, EngBox.getText()+", "+EspBox.getText()+", "+WordClassBox.getSelectedItem(), "Added translation" , JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Cannot implement translation. Please add text to the field(s)", "Cannot add translation!" , JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button1.setActionCommand("AddWord");


		JFrame addMenu = new JFrame();
		addMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addMenu.setLayout(null);
		addMenu.setSize(400,300);
		addMenu.setBackground(Color.gray);
		addMenu.setTitle("Add");
		addMenu.setResizable(false);

		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);

		addMenu.setLocation(xPos, 0);

		addMenu.add(panelMain);
			panelMain.add(langPanel);
				langPanel.add(label1);
				langPanel.add(EngBox);

				langPanel.add(label2);
				langPanel.add(EspBox);

				langPanel.add(label3);
				langPanel.add(WordClassBox);
				langPanel.add(button1);

		addMenu.setVisible(true);
	}

	public void DeleteMenu()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0,0,400,300);
		panelMain.setLayout(null);
		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5,5,375,250);
		langPanel.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(15,3,173,25);
		label1.setText("English word:");

		JTextArea EngBox = new JTextArea();
		EngBox.setBounds(15,25,173,25);
		EngBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EngBox.setLineWrap(true);
		EngBox.setWrapStyleWord(true);
		EngBox.setText("");

		JLabel label2 = new JLabel();
		label2.setBounds(15,78,173,25);
		label2.setText("Spanish word:");

		JTextArea EspBox = new JTextArea();
		EspBox.setBounds(15,100,173,25);
		EspBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EspBox.setLineWrap(true);
		EspBox.setWrapStyleWord(true);
		EspBox.setText("");

		JLabel label3 = new JLabel();
		label3.setBounds(15,153,173,25);
		label3.setText("Word class:");

		JComboBox WordClassBox = new JComboBox(WordType.values());
		WordClassBox.setSelectedIndex(0);
		WordClassBox.setBounds(15,175,173,25);

		JButton button1 = new JButton();
		button1.setBounds(225,25,125,75);
		button1.setText("Delete");
		button1.setToolTipText("Please add text to all text fields then press this button to delete the word from the dictionary.");
		button1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(action.equals("Delete"))
				{if(EngBox.getText().trim().length() != 0 || EspBox.getText().trim().length() != 0)
				{
					Dictionary dictionary = new Dictionary();
					try {
						dictionary.deleteEntry(EngBox.getText());
					} catch (NodeDoesntExistException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, EngBox.getText()+", "+EspBox.getText()+", "+WordClassBox.getSelectedItem(), "Removed Item" , JOptionPane.INFORMATION_MESSAGE);
			
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Cannot remove item. Please add text to the field(s)", "Cannot Remove Item" , JOptionPane.WARNING_MESSAGE);
				}
				}
			}
		});
		button1.setActionCommand("Delete");

		JFrame DeleteMenu = new JFrame();
		DeleteMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DeleteMenu.setLayout(null);
		DeleteMenu.setSize(400,300);
		DeleteMenu.setBackground(Color.gray);
		DeleteMenu.setTitle("Delete");
		DeleteMenu.setResizable(false);

		DeleteMenu.add(panelMain);
			panelMain.add(langPanel);
				langPanel.add(label1);
				langPanel.add(EngBox);

				langPanel.add(label2);
				langPanel.add(EspBox);

				langPanel.add(label3);
				langPanel.add(WordClassBox);
				langPanel.add(button1);

		DeleteMenu.setVisible(true);
	}

	public void EditMenu()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0,0,600,400);
		panelMain.setLayout(null);

		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5,5,575,350);
		langPanel.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(15,3,173,25);
		label1.setText("English word:");

		JTextArea EngBox = new JTextArea();
		EngBox.setBounds(15,25,173,25);
		EngBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EngBox.setLineWrap(true);
		EngBox.setWrapStyleWord(true);
		EngBox.setText("");

		JLabel label2 = new JLabel();
		label2.setBounds(15,78,173,25);
		label2.setText("Spanish word:");

		JTextArea EspBox = new JTextArea();
		EspBox.setBounds(15,100,173,25);
		EspBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EspBox.setLineWrap(true);
		EspBox.setWrapStyleWord(true);
		EspBox.setText("");

		JLabel label3 = new JLabel();
		label3.setBounds(15,153,173,25);
		label3.setText("Word class:");

		JComboBox WordClassBox = new JComboBox(WordType.values());
		WordClassBox.setSelectedIndex(0);
		WordClassBox.setBounds(15,175,173,25);

		JButton button1 = new JButton();
		button1.setBounds(225,25,125,75);
		button1.setText("Edit");
		button1.setToolTipText("Please add text to all text fields then press this button to apply the edit to the dictionary.");
		button1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				String action = ae.getActionCommand();
				if(action.equals("Edit"))
				{
					if(EngBox.getText().trim().length() == 0 || EspBox.getText().trim().length() == 0)
					{
						Dictionary dictionary = new Dictionary();
						try {
							dictionary.modifyEntry(EngBox.getText(), EspBox.getText());
						} catch (NodeDoesntExistException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, EngBox.getText()+", "+EspBox.getText()+", "+WordClassBox.getSelectedItem(), "Modified Item" , JOptionPane.INFORMATION_MESSAGE);
				
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cannot modify item. Please add text to the field(s)", "Cannot modify item" , JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		button1.setActionCommand("Edit");

		JFrame EditMenu = new JFrame();
		EditMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditMenu.setLayout(null);
		EditMenu.setSize(400,300);
		EditMenu.setBackground(Color.gray);
		EditMenu.setTitle("Edit");
		EditMenu.setResizable(false);

		EditMenu.add(panelMain);
			panelMain.add(langPanel);
				langPanel.add(label1);
				langPanel.add(EngBox);

				langPanel.add(label2);
				langPanel.add(EspBox);

				langPanel.add(label3);
				langPanel.add(WordClassBox);
				langPanel.add(button1);

		EditMenu.setVisible(true);
	}

}
