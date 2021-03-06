import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Kamil Krauze
 * @version 02/04/2021
 */
public class Menu extends JFrame {

	String[] langTypes = { "English to Spanish", "Spanish to English" };
	String[] DictionaryFiles = {"dictionary.txt" };

	public static void main(String[] Args) {
		Dictionary dictionary = new Dictionary("dictionary.txt");

		Menu menu = new Menu(dictionary);
		menu.mainMenu();
		
	}

	private Dictionary dictionary;

	public Menu(Dictionary dictionary) {
		super();
		this.dictionary = dictionary;
	}

	public void mainMenu() {
		int panelOffset = 60;

		//////////////////////// PANELS ////////////////////////////////////////
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0, 0, 800, 600);
		panelMain.setLayout(null);

		JPanel langOptions = new JPanel();
		langOptions.setBackground(Color.gray);
		langOptions.setBounds(5, 40, 780, 50);
		langOptions.setLayout(null);

		JPanel initLang = new JPanel();
		initLang.setBackground(Color.gray);
		initLang.setBounds(25, 5, 200, 40);
		initLang.setLayout(null);

		JPanel newLang = new JPanel();
		newLang.setBackground(Color.gray);
		newLang.setBounds(400, 5, 200, 40);
		newLang.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.gray);
		panel1.setBounds(25, 35 + panelOffset, 350, 325);
		panel1.setLayout(null);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.gray);
		panel2.setBounds(400, 35 + panelOffset, 350, 325);
		panel2.setLayout(null);

		JPanel translate = new JPanel();
		translate.setBackground(Color.gray);
		translate.setBounds(25, 375 + panelOffset - 10, 725, 60);
		translate.setLayout(null);

		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.gray);
		btnPanel.setBounds(25, 490, 725, 60);
		btnPanel.setLayout(null);

		//////////////////////////////// PANEL COMPONENTS
		//////////////////////////////// ////////////////////////////////////////

		JComboBox LangBox = new JComboBox(langTypes);
		LangBox.setSelectedIndex(0);
		LangBox.setBounds(0, 0, 150, 40);
		LangBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int userChoice = LangBox.getSelectedIndex();
				dictionary = new Dictionary(DictionaryFiles[userChoice]);
			}
		});

		JLabel greeting = new JLabel();
		greeting.setBounds(-50, 15, 500, 25);
		greeting.setText("Welcome");
		greeting.setFont(new Font("Verdana", Font.BOLD, 25));
		greeting.setVerticalAlignment(JLabel.BOTTOM);
		greeting.setHorizontalAlignment(JLabel.RIGHT);

		JTextArea toTranslate = new JTextArea();
		toTranslate.setBounds(5, 5, 340, 315);
		toTranslate.setFont(new Font("Verdana", Font.PLAIN, 15));
		toTranslate.setText("Enter here the text you want to translate.");
		toTranslate.setLineWrap(true);
		toTranslate.setWrapStyleWord(true);

		JScrollPane TranslateScroll = new JScrollPane(toTranslate);
		TranslateScroll.setBounds(0, 5, 340, 315);

		JTextArea translatedBox = new JTextArea();
		translatedBox.setBounds(5, 5, 340, 315);
		translatedBox.setFont(new Font("Verdana", Font.PLAIN, 15));
		translatedBox.setText("The translated text will be outputted here.");
		translatedBox.setLineWrap(true);
		translatedBox.setWrapStyleWord(true);

		JScrollPane TranslatedScroll = new JScrollPane(translatedBox);
		TranslatedScroll.setBounds(5, 5, 340, 315);

		JButton translateBTN = new JButton();
		translateBTN.setBounds(310, 5, 100, 50);
		translateBTN.setText("Translate");
		translateBTN.setToolTipText("Translate your text");
		translateBTN.addActionListener(new myActionListener(dictionary, toTranslate, translatedBox));
		translateBTN.setActionCommand("Translate");

		int btnOffset = 150;

		JButton load = new JButton();
		load.setBounds(5 + btnOffset, 5, 100, 50);
		load.setText("Load");
		load.setToolTipText("Load a text (.txt format) file that would you want to translate from.");
		load.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae)
			{
				if (ae.getActionCommand().equals("LoadFile"))
				{
					Translator translator = new Translator(dictionary);
					
					JFileChooser jFileChooser = new JFileChooser();
				    jFileChooser.setCurrentDirectory(new File("user.home"));
				         
				    int result = jFileChooser.showOpenDialog(new JFrame());
				     
				    if (result == JFileChooser.APPROVE_OPTION)
				    {
				    	File selectedFile = jFileChooser.getSelectedFile();
				    	try {
							
							translatedBox.setText(translator.translateFile(selectedFile.getAbsolutePath()));
						} catch (IOException e) {
							e.printStackTrace();
						}
				    	JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath(), "File Selected", JOptionPane.INFORMATION_MESSAGE);
				    }
				}
			}
		});
		load.setActionCommand("LoadFile");

		JButton save = new JButton();
		save.setBounds(110 + btnOffset, 5, 100, 50);
		save.setText("Save");
		save.setToolTipText("Save your translation to a text file (.txt format).");
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if (ae.getActionCommand().equals("Save"))
				{
					Translator translator = new Translator();
					
					JFileChooser save = new JFileChooser();
					save.setCurrentDirectory(new File("user.home"));
					
					int result = save.showOpenDialog(new JFrame());
					
					 if (result == save.APPROVE_OPTION)
					 {
						 File file = save.getSelectedFile();
						 if(file == null)
						 {
							 return;
						 }
						 if(!file.getName().toLowerCase().endsWith(".txt"))
						 {
							 file = new File(file.getParentFile(), file.getName() + ".txt");
						 }
						 
						 try
						 {
							 translator.saveTextToAFile(translatedBox.getText(), file.getName());
						 }
						 catch(Exception e)
						 {
							 e.printStackTrace();
						 }
					 }
				}
			}
		});
		save.setActionCommand("Save");

		// JButton test = new JButton();
		// test.setBounds(215 + btnOffset, 5, 100, 50);
		// test.setText("Test");
		// test.setToolTipText("Run a test");
		// test.addActionListener(new ActionListener() {
		// 	@Override
		// 	public void actionPerformed(ActionEvent ae) {
		// 		String action = ae.getActionCommand();
		// 		if (action.equals("Test")) {
		// 			Tester test = new Tester();
		// 			test.testBinaryTrees();
		// 		}
		// 	}
		// });
		// test.setActionCommand("Test");

		JButton modify = new JButton();
		modify.setBounds(320 + btnOffset, 5, 100, 50);
		modify.setText("Modify");
		modify.setToolTipText("Modify the translation");
		modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (action.equals("Modify")) {
					modifyMenu();
				}
			}
		});
		modify.setActionCommand("Modify");

		//////////////////// APP FRAME ///////////////////////////////////

		JFrame menu = new JFrame();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setLayout(null);
		menu.setSize(800, 600);
		menu.setBackground(Color.darkGray);
		menu.setTitle("Translator");
		menu.setResizable(false);

		/////////////////// APPLYING TO FRAME ///////////////////////////////////

		menu.add(langOptions);

		langOptions.add(initLang);
		initLang.add(LangBox);

		langOptions.add(newLang);

		menu.add(panel1);
		panel1.add(this.add(TranslateScroll));

		menu.add(panel2);
		panel2.add(this.add(TranslatedScroll));

		menu.add(translate);
		translate.add(translateBTN);

		menu.add(btnPanel);
		btnPanel.add(load);
		btnPanel.add(save);
		btnPanel.add(modify);

		panelMain.add(greeting);
		menu.add(panelMain);

		menu.setVisible(true);
	}

	public void modifyMenu() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0, 0, 800, 300);
		panelMain.setLayout(null);

		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5, 5, 775, 300);
		langPanel.setLayout(null);


		final String html = "<html><body style='width: %1spx'>%1s";
		
		JLabel header = new JLabel();
		header.setBounds(5, 5, 775, 25);
		header.setFont(new Font("Verdana", Font.BOLD, 15));
		header.setText("Instructions");
		
		JLabel desc = new JLabel();
		desc.setBounds(5, 35, 775, 25);
		desc.setFont(new Font("Verdana", Font.PLAIN, 26/2));
		desc.setText("This menu allows you to modify the dictionary of this program to your own desire.");
		
		JLabel inst1 = new JLabel();
		inst1.setBounds(5, 65, 775, 25);
		inst1.setFont(new Font("Verdana", Font.PLAIN, 26/2));
		inst1.setText("1. The 'ADD' button allows you to add a new defintion into the dictionary, by entering an English word and then the spanish");
		
		JLabel inst1_2 = new JLabel();
		inst1_2.setBounds(5,80,775,25);
		inst1_2.setFont(new Font("Verdana", Font.PLAIN, 26/2));
		inst1_2.setText("     or any other language's equivalent.");
		
		JLabel inst2 = new JLabel();
		inst2.setBounds(5, 105, 775, 25);
		inst2.setFont(new Font("Verdana", Font.PLAIN, 26/2));
		inst2.setText("2. The 'DELETE' button allows you to remove a definition from the dictionary by specifying an English word.");
		
		JLabel inst3 = new JLabel();
		inst3.setBounds(5, 135, 775, 25);
		inst3.setFont(new Font("Verdana", Font.PLAIN, 26/2));
		inst3.setText("3. The 'EDIT' button allows you to modify an existing defintion within the dictionary by specifying which word in English");
		
		JLabel inst3_2 = new JLabel();
		inst3_2.setBounds(5,150,775,25);
		inst3_2.setFont(new Font("Verdana", Font.PLAIN, 26/2));
		inst3_2.setText("     and then the new defintion in Spanish or any other language");

		JButton AddBTN = new JButton();
		AddBTN.setBounds(245, 185, 100, 50);
		AddBTN.setText("Add");
		AddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (action.equals("Add")) {
					addMenu();
				}
			}
		});
		AddBTN.setActionCommand("Add");

		JButton DelBTN = new JButton();
		DelBTN.setBounds(455, 185, 100, 50);
		DelBTN.setText("Delete");
		DelBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (action.equals("Delete")) {
					DeleteMenu();
				}
			}
		});
		DelBTN.setActionCommand("Delete");

		JButton EditBTN = new JButton();
		EditBTN.setBounds(350, 185, 100, 50);
		EditBTN.setText("Edit");
		EditBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (action.equals("Edit")) {
					EditMenu();
				}
			}
		});
		EditBTN.setActionCommand("Edit");

		JFrame modMenu = new JFrame();
		modMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modMenu.setLayout(null);
		modMenu.setSize(800, 300);
		modMenu.setBackground(Color.gray);
		modMenu.setTitle("Modify");
		modMenu.setResizable(false);

		modMenu.add(panelMain);
			panelMain.add(langPanel);
				langPanel.add(header);
				langPanel.add(desc);
				langPanel.add(inst1);
				langPanel.add(inst1_2);
				langPanel.add(inst2);
				langPanel.add(inst3);
				langPanel.add(inst3_2);
			
			panelMain.add(langPanel);
				langPanel.add(AddBTN);
				langPanel.add(DelBTN);
				langPanel.add(EditBTN);

		modMenu.setVisible(true);
	}

	public void addMenu() {

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0, 0, 400, 300);
		panelMain.setLayout(null);

		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5, 5, 375, 250);
		langPanel.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(15, 3, 173, 25);
		label1.setText("English word:");

		JTextField EngBox = new JTextField();
		EngBox.setBounds(15, 25, 173, 25);
		EngBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EngBox.setText("");

		JLabel label2 = new JLabel();
		label2.setBounds(15, 78, 173, 25);
		label2.setText("Spanish word:");

		JTextField EspBox = new JTextField();
		EspBox.setBounds(15, 100, 173, 25);
		EspBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EspBox.setText("");

		JLabel label3 = new JLabel();
		label3.setBounds(15, 153, 173, 25);
		label3.setText("Word class:");

		JComboBox WordClassBox = new JComboBox(WordType.values());
		WordClassBox.setSelectedIndex(0);
		WordClassBox.setBounds(15, 175, 173, 25);

		JButton button1 = new JButton();
		button1.setBounds(225, 25, 125, 75);
		button1.setText("Add");
		button1.setToolTipText(
				"Please add text to all text fields then press this button to add your word to the dictionary.");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (EngBox.getText().trim().length() != 0 || EspBox.getText().trim().length() != 0) {

					DictionaryEntry entry = new DictionaryEntry(EngBox.getText(), EspBox.getText(),WordType.NONE);
					try {
						dictionary.addEntry(entry);
					} catch (IDExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,
							EngBox.getText() + ", " + EspBox.getText() + ", " + WordClassBox.getSelectedItem(),
							"Added translation", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cannot implement translation. Please add text to the field(s)",
							"Cannot add translation!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button1.setActionCommand("AddWord");

		JFrame addMenu = new JFrame();
		addMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addMenu.setLayout(null);
		addMenu.setSize(400, 300);
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

	public void DeleteMenu() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0, 0, 400, 300);
		panelMain.setLayout(null);
		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5, 5, 375, 250);
		langPanel.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(15, 3, 173, 25);
		label1.setText("English word:");

		JTextField EngBox = new JTextField();
		EngBox.setBounds(15, 25, 173, 25);
		EngBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EngBox.setText("");

		JButton button1 = new JButton();
		button1.setBounds(225, 25, 125, 75);
		button1.setText("Delete");
		button1.setToolTipText(
				"Please add text to all text fields then press this button to delete the word from the dictionary.");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (action.equals("Delete")) {
					if (EngBox.getText().trim().length() != 0) {
						
						try {
							dictionary.deleteEntry(EngBox.getText());
						} catch (NodeDoesntExistException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,
								EngBox.getText() + "has been removed from the dictionary",
								"Removed Item", JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Cannot remove item. Please add text to the field(s)",
								"Cannot Remove Item", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		button1.setActionCommand("Delete");

		JFrame DeleteMenu = new JFrame();
		DeleteMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DeleteMenu.setLayout(null);
		DeleteMenu.setSize(400, 300);
		DeleteMenu.setBackground(Color.gray);
		DeleteMenu.setTitle("Delete");
		DeleteMenu.setResizable(false);

		DeleteMenu.add(panelMain);
		panelMain.add(langPanel);
		langPanel.add(label1);
		langPanel.add(EngBox);

		langPanel.add(button1);

		DeleteMenu.setVisible(true);
	}

	public void EditMenu() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension dim = kit.getScreenSize();

		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.gray);
		panelMain.setBounds(0, 0, 600, 400);
		panelMain.setLayout(null);

		JPanel langPanel = new JPanel();
		langPanel.setBackground(Color.gray);
		langPanel.setBounds(5, 5, 575, 350);
		langPanel.setLayout(null);

		JLabel label1 = new JLabel();
		label1.setBounds(15, 3, 173, 25);
		label1.setText("English word:");

		JTextField EngBox = new JTextField();
		EngBox.setBounds(15, 25, 173, 25);
		EngBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EngBox.setText("");

		JLabel label2 = new JLabel();
		label2.setBounds(15, 78, 173, 25);
		label2.setText("New Spanish Word:");

		JTextField EspBox = new JTextField();
		EspBox.setBounds(15, 100, 173, 25);
		EspBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		EspBox.setText("");

		JLabel label3 = new JLabel();
		label3.setBounds(15, 153, 173, 25);
		label3.setText("Word class:");

		JButton button1 = new JButton();
		button1.setBounds(225, 25, 125, 75);
		button1.setText("Edit");
		button1.setToolTipText(
				"Please add text to all text fields then press this button to apply the edit to the dictionary.");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String action = ae.getActionCommand();
				if (action.equals("Edit")) {
					if (EngBox.getText().trim().length() != 0 && EspBox.getText().trim().length() != 0) {
						try {
							dictionary.modifyEntry(EngBox.getText(), EspBox.getText());
						} catch (NodeDoesntExistException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,
								EngBox.getText() + ", " + EspBox.getText() + ", ",
								"Modified Item", JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Cannot modify item. Please add text to the field(s)",
								"Cannot modify item", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		button1.setActionCommand("Edit");

		JFrame EditMenu = new JFrame();
		EditMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditMenu.setLayout(null);
		EditMenu.setSize(400, 300);
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
		langPanel.add(button1);

		EditMenu.setVisible(true);
	}

	private class myActionListener implements ActionListener {
		private Dictionary dictionary;
		private JTextArea textBox;
		private JTextArea textBox2;

		public myActionListener(Dictionary dictionary, JTextArea textBox, JTextArea textBox2) {
			this.dictionary = dictionary;
			this.textBox = textBox;
			this.textBox2 = textBox2;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			String action = ae.getActionCommand();
			if (action.equals("Translate")) {
				Translator translator = new Translator(dictionary);
				String translation = translator.translateText(textBox.getText());
				System.out.println(translation);
				
				textBox2.setText(translation);
				//JOptionPane.showMessageDialog(null, translation, "Completed Translation", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
