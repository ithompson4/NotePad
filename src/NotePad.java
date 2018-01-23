// Program that creates a simple text editor in Java with similar functionality to Windows NotePad

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import java.io.*;

public class NotePad extends JFrame implements ActionListener{

	public JTextArea textArea = new JTextArea(20, 120);  // used for output
	
	String findString;
	String path;
	File path1;
	
	String content;
	Clipboard clip = getToolkit().getSystemClipboard();   
	Find finder;
	
	JMenu fileMenu = new JMenu("File"); // create file menu	
	JMenuItem newFile = new JMenuItem("New");  // create new file menu	
	JMenuItem openFile = new JMenuItem("Open"); // create open file menu	
	JMenuItem saveFile = new JMenuItem("Save"); // create save file menu	
	JMenuItem saveAsFile = new JMenuItem("Save As"); // create save as menu		
	JMenuItem exit = new JMenuItem("Exit"); // create exit menu	
	
	JMenu editMenu = new JMenu("Edit"); // create edit menu	
	JMenuItem copyEdit = new JMenuItem("Copy"); // create copy menu	
	JMenuItem cutEdit = new JMenuItem("Cut"); // create cut menu	
	JMenuItem pasteEdit = new JMenuItem("Paste"); // create paste menu	
	JMenuItem searchEdit = new JMenuItem("Search"); // create search menu	
	JMenuItem replaceEdit = new JMenuItem("Replace"); // create replace menu	

	JMenu formatMenu = new JMenu("Format"); // create format menu			
	JMenuItem fontFormat = new JMenuItem("Font"); // create font menu	
	JCheckBoxMenuItem wrapFormat = new JCheckBoxMenuItem("Word Wrap"); // create wrapFormat menu
	
	JMenu helpMenu = new JMenu("Help"); // create help menu	
	JMenuItem aboutHelp = new JMenuItem("About"); // create about menu	

	// no - argument constructor set up GUI		
	public NotePad(){
		
		super("Java Notepad"); // set the title
                                    textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // set a default font for the TextArea
		setSize(500, 500); // set the size of the window

		// add textArea to scrollPane
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	                  add(scroll, BorderLayout.CENTER); // add scroll to application
	                  setDefaultCloseOperation(EXIT_ON_CLOSE); // set the default close operation 
		getContentPane().setLayout(new BorderLayout()); // the BorderLayout to fill content automatically
		getContentPane().add(textArea); // add textArea to content		
	    
		fileMenu.add(newFile); // add newFile to file menu
		fileMenu.add(openFile); // add openFile to file menu
		fileMenu.add(saveFile); // add saveFile to file menu
		fileMenu.add(saveAsFile); // saveAsFile to file menu
		fileMenu.add(exit);	// add exit to file menu	
	    
		editMenu.add(copyEdit); // add copyEdit to edit menu
		editMenu.add(cutEdit); // add cutEdit to edit menu
		editMenu.add(pasteEdit); // add pasteEdit to edit menu
		editMenu.add(searchEdit); // add searchEdit to edit menu
		editMenu.add(replaceEdit); // add replaceEdit to edit menu	
		
		formatMenu.add(fontFormat); // add fontFormat to format menu
		formatMenu.add(wrapFormat);	// add wrapFormat to format menu
					
		helpMenu.add(aboutHelp); // add about to help menu
		
		JMenuBar menuBar = new JMenuBar(); // create menu bar
		setJMenuBar(menuBar); // add menu bar to application
		menuBar.add(fileMenu); // add file menu to menu bar
		menuBar.add(editMenu); // add edit menu to menu bar
		menuBar.add(formatMenu); // add format menu to menu bar
		menuBar.add(helpMenu); // add help menu to menu bar
		
	                 // find window
                                   finder = new Find(this);
                                   finder.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // set the default close operation for finder
        
                                    //Add listeners to the items     
		newFile.addActionListener(this); // add an action listener to newFile 	
		openFile.addActionListener(this); // add an action listener to openFile	
		saveFile.addActionListener(this); // add an action listener to saveFile			
		saveAsFile.addActionListener(this);	// add an action listener to saveAsFile		
                                    exit.addActionListener(this); // add an action listener to exit			
		copyEdit.addActionListener(this); // add an action listener to copy			
		cutEdit.addActionListener(this); // add an action listener to cut		
		pasteEdit.addActionListener(this); // add an action listener to paste	
		searchEdit.addActionListener(this);	// add an action listener to search	
                                    replaceEdit.addActionListener(this); // add an action listener to replace
		fontFormat.addActionListener(this); // add an action listener to font format			
		wrapFormat.addActionListener(this); // add an action listener to wrap format		
		aboutHelp.addActionListener(this); // add an action listener to about   
	}
	// method actionPerformed
	public void actionPerformed(ActionEvent e){

		// exit button
		if (e.getSource() == exit){
		     dispose(); // dispose all resources and close the application
		} // end if	

		// new file button
		else if(e.getSource() == newFile){
		      newFile();
		} // end else if

		// open file button
		else if(e.getSource() == openFile){
		      openFile();	
		} // end else if

		// save file button
		else if(e.getSource() == saveFile){
		      saveFile();	
	                  } // end else if 

		// saveAs file button
		else if(e.getSource() == saveAsFile){
		       saveAs();
	                  } // end else if 

		// copy button
		else if(e.getSource() == copyEdit){
		      copyEdit();
		} // end else if
	
		// cut button
		else if(e.getSource() == cutEdit){	
		       cutEdit();
	                   } // end else if
	
		// paste button
		else if(e.getSource() == pasteEdit){	
		      pasteEdit();
		} // end else if

		// search button
		else if(e.getSource() == searchEdit){
		      searchEdit();
		} // end else if	
		
		// replace button
		else if(e.getSource() == replaceEdit){
		       replaceEdit();
		} // end else if 

		// font format button		
		else if(e.getSource() == fontFormat){
		       changeFont fontFormat = new changeFont();
		} // end else if
		
		// wrap format button
		else if(e.getSource() == wrapFormat){
		      wrapFormat();			
		} // end else if	

		// about button
		else if(e.getSource() == aboutHelp){
                                          JOptionPane.showMessageDialog(this, "Iryna Okolot Thompson\nCopyright 2004 Iryna Thompson. All rights reserved",
            		      "About NotePad", JOptionPane.INFORMATION_MESSAGE);
		} // end else if

    } // end method actionPerformed
     // method newFile
     public void newFile(){
            if(textArea.getText().equals("") || textArea.getText().equals(content)){
                 textArea.setText("");
                 content = "";
                 path = "";
                 setTitle("untitled - Notepad");
            } // end if

            else{
                  int nFile = JOptionPane.showConfirmDialog(this, "The text has been changed\nDo you want to save the changes?");
                  if(nFile == 0){
                       saveFile();
                 }
                 else if(nFile == 1){
            	      textArea.setText("");
                        path = "";
                        setTitle("untitled - Notepad");
                }
               else if(nFile == 2){
            	   return;
               }
          }
    } // end method newFile
	
	// method openFile
	public void openFile(){
		// display file dialog, so user can choose file to open
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int result = fileChooser.showOpenDialog(this);

	    // if user click Cancel button on dialog, return 
	    if(result == fileChooser.CANCEL_OPTION){
	    	return;
	    } // end if
	    
	    File myfile = fileChooser.getSelectedFile();
	    
	    // display error if invalid
	    if(myfile == null || myfile.getName().equals("")){
	    	JOptionPane.showMessageDialog(this, "Select a file!", "Error!", JOptionPane.ERROR_MESSAGE);
	        return;
	    } // end if

	    try{
	    	// create BufferedReader for reading a file name 
	    	BufferedReader input = new BufferedReader(new FileReader(myfile));
	        StringBuffer Buf = new StringBuffer();
	        String line;
	        while((line = input.readLine()) != null){
	        	Buf.append(line + "\n");
	        } // end while
	        textArea.setText(Buf.toString());
	        content = textArea.getText();
	        path = myfile.toString();
	        setTitle(myfile.getName() + " - Notepad");
	    } // end try

	    // error message
	    catch(FileNotFoundException e){
	    	JOptionPane.showMessageDialog(null, "File not found: " + e);
	    } // end catch

	    catch(IOException e){
	    	JOptionPane.showMessageDialog(null, "IO ERROR: " + e);
	    } // end catch
	} // end method openFile
	
	// method save file
	public void saveFile(){
		if(path1 == null){
			saveAs();
            return;
        } // end if
		try{
			FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(textArea.getText());
            content = textArea.getText();             
            fileWriter.close();          
        } // end try
        catch(IOException i){
        	JOptionPane.showMessageDialog(this, "Failed to save the file", "Error", JOptionPane.ERROR_MESSAGE);
        } // end catch		        
    } // end method saveFile	
	// method save as file 
	public void saveAs(){
		
		//display file dialog, so user can choose file to open
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(this);

        // if user clicked cancel button on dialog, return
        if(result == fileChooser.CANCEL_OPTION){
        	return;
        } // end if

        File myfile = fileChooser.getSelectedFile();
        // display error if invalid
        if(myfile == null || myfile.getName().equals("")){
        	JOptionPane.showMessageDialog(this, "Please enter a file name!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } // end if
        
        // if name exists, output information about it
        if(myfile.exists()){
        	result = JOptionPane.showConfirmDialog(this, "A file with same name already exists!\nAre you sure want to overwrite?");
            if(result != 0)
            return;
        } // end if
        
        try{
        	FileWriter fileWriter = new FileWriter(myfile);
            fileWriter.write(textArea.getText());
            content = textArea.getText();
            setTitle(myfile.getName() + " - Notepad");
            fileWriter.close();
        } // end try

        catch(IOException e){
        	JOptionPane.showMessageDialog(this, "Failed to save the file", "Error", JOptionPane.ERROR_MESSAGE);
        } // end catch
        path1 = fileChooser.getSelectedFile();
    } // end method saveAs	

	// copy method
	public void copyEdit(){
		String selection = textArea.getSelectedText();
        StringSelection clipString = new StringSelection(selection);
        clip.setContents(clipString,clipString);
	} // end method copyEdit

	// cut method
	public void cutEdit(){
		String selection = textArea.getSelectedText();
        StringSelection cutString = new StringSelection(selection);
        clip.setContents(cutString,cutString);
        textArea.replaceRange(" ", textArea.getSelectionStart(), textArea.getSelectionEnd());
	} // end method cutEdit	

	// paste method
	public void pasteEdit(){
		Transferable cliptran = clip.getContents(NotePad.this);
        try{
        	// choose and replace a string
        	String selection = (String) cliptran.getTransferData(DataFlavor.stringFlavor);
        	textArea.replaceRange(selection, textArea.getSelectionStart(), textArea.getSelectionEnd());
        } // end try

        catch(Exception exc){
        	System.out.println("Not String Flavor...");
        } // end catch
	} // end method pasteEdit

	// search method
	public void searchEdit(){
		finder.setVisible(true);
	} // end method searchEdit

	// method replace 	
	public void replaceEdit(){
		finder.setVisible(true);		        
	} // end method replceEdit 

        // wrap Format method 
	public void wrapFormat(){
		if(wrapFormat.isSelected()){
			textArea.setLineWrap(true);
		} // end if
                else
                	textArea.setLineWrap(false);
	} // end method wrapFormat

	// class changeFont
	class changeFont extends JFrame implements ActionListener{
		
    	// create font menu
    	String availableFontString[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JList fontList = new JList(availableFontString);
        JLabel fontLabel = new JLabel("Font");
        JTextField valueFont = new JTextField("Arial");
        JScrollPane fontPane = new JScrollPane(fontList); 
        
        String fontStyleString[] = {"Regular", "Bold", "Italic", "Bold Italic"};
        JList styleList = new JList(fontStyleString);
        JLabel styleLabel = new JLabel("Style");
        int ver = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int hor = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        JScrollPane stylePane = new JScrollPane(styleList, ver, hor);
        JTextField valueStyle = new JTextField("Regular");
        
        String fontSizeString[] = {"8", "9", "10", "12", "14", "16", "18", "20", "22", "24", "28"};
        JList sizeList = new JList(fontSizeString);
        JLabel sizeLabel = new JLabel("Font size");
        JScrollPane sizePane = new JScrollPane(sizeList);
        JTextField valueSize = new JTextField("12");
        JButton okBut = new JButton("Ok");
        JButton cancelBut = new JButton("Cancel");
        JLabel sampleLabel = new JLabel("Sample: ");
        JTextField sample = new JTextField(" AaBbCc");
        Font myFont; 

        // no-argument constructor
        public changeFont(){ 
        	
            setSize(500,300);
            setTitle("Font");
            setVisible(true);
            sample.setEditable(false);
            getContentPane().setLayout(null);
            fontLabel.setBounds(10,10,170,20);
            valueFont.setBounds(10,35,170,20);
            fontPane.setBounds(10,60,170,150);

            styleLabel.setBounds(200,10,100,20);
            valueStyle.setBounds(200,35,100,20);
            stylePane.setBounds(200,60,100,150);

            sizeLabel.setBounds(320,10,50,20);
            valueSize.setBounds(320,35,50,20);
            sizePane.setBounds(320,60,50,150);

            okBut.setBounds(400,35,80,20);
            cancelBut.setBounds(400,60,80,20);

            sampleLabel.setBounds(150,235,100,30);
            sample.setBounds(200,235,100,30);
            
            // add content
            getContentPane().add(fontLabel);
            getContentPane().add(fontPane);
            getContentPane().add(valueFont); 

            getContentPane().add(styleLabel);
            getContentPane().add(stylePane);
            getContentPane().add(valueStyle);

            getContentPane().add(sizeLabel);
            getContentPane().add(sizePane);
            getContentPane().add(valueSize);

            getContentPane().add(okBut);
            getContentPane().add(cancelBut);
            getContentPane().add(sampleLabel);
            getContentPane().add(sample); 
            
            okBut.addActionListener(this); // add addActionListener to ok button
            cancelBut.addActionListener(this); // add addActionListener to cancel button

            fontList.addListSelectionListener(new ListSelectionListener(){
        	    public void valueChanged(ListSelectionEvent event){
        	    	if(!event.getValueIsAdjusting()){
        	    		valueFont.setText(fontList.getSelectedValue().toString());
                       	                  myFont = new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                                                      sample.setFont(myFont);
                                    } // end if
                     }
            });
            
            styleList.addListSelectionListener(new ListSelectionListener(){
        	    public void valueChanged(ListSelectionEvent event){
        		    if(!event.getValueIsAdjusting()){
        		             valueStyle.setText(styleList.getSelectedValue().toString());
                                                 myFont = new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                                                 sample.setFont(myFont);
                    } // end if 
                }
            });
            
            sizeList.addListSelectionListener(new ListSelectionListener(){
        	    public void valueChanged(ListSelectionEvent event){
        		    if(!event.getValueIsAdjusting()){
        	                               valueSize.setText(sizeList.getSelectedValue().toString());
                                                 myFont = new Font (valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                                                 sample.setFont(myFont);
                                        } // end if
                     }
            });
      }
      // actionPerformed method  
      public void actionPerformed(ActionEvent e){

    	  // ok button option
    	  if(e.getSource() == okBut){
                               myFont= new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                               textArea.setFont(myFont);
                               setVisible(false);
                    } // end if
      } // end actionPerformed method
      
    } // end class changeFont
} // end class NotePad