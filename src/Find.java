// Find class

import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Find extends JFrame implements ActionListener{
    int startIndex = 0;
    Label label1;
    Label label2;
    TextField tf;
    TextField tr;
    JButton findBut; 
    JButton replace;
    JButton cancel; 
    NotePad newNote;
    
    // no-argument constructor 
    public Find(NotePad mynote){
    	
        super("Find / Replace"); // set the title
        newNote = mynote;
 
        label1 = new Label("Find What: ");
        label2 = new Label("Replace With: ");
        tf = new TextField(30);
        tr = new TextField(30);
        findBut = new JButton("Find");
        replace = new JButton("Replace");
        cancel = new JButton("Cancel");
 
        setLayout(null);
        int label_w = 80;
        int label_h = 20;
        int tf_w = 120;
 
        label1.setBounds(10,10, label_w, label_h);
        add(label1);
        tf.setBounds(10 + label_w, 10, tf_w, 20);
        add(tf);
        label2.setBounds(10, 10 + label_h + 10, label_w, label_h);
        add(label2);
        tr.setBounds(10+label_w, 10 + label_h + 10, tf_w, 20);
        add(tr);
 
        findBut.setBounds(220, 10, 100, 20);
        add(findBut); 
        findBut.addActionListener(this);
        replace.setBounds(220, 50, 100, 20);
        add(replace);
        replace.addActionListener(this);
        cancel.setBounds(220, 90, 100, 20);
        add(cancel);
        cancel.addActionListener(this);
 
        int w = 340;
        int h = 150;
 
        setSize(w,h);
        // set window position
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(center.x-w/2, center.y-h/2);
        setVisible(false);
    } // end constructor find
    
    // actionPerformed method
    public void actionPerformed(ActionEvent e){
    	
    	if(e.getSource()==findBut){
    		find();
        } // end if     
    	
        else if(e.getSource() == replace){
        	replace();
        } // end if
    	
        else if(e.getSource() == cancel){
        	this.setVisible(false);
        } // end if
    } // end method actionPerformed
    
    // find method
    public void find(){
    	
    	int start = newNote.textArea.getText().indexOf(tf.getText());
        if(start == -1){
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find " + tf.getText() + "!");
            return;
        } // end if 
        
        if(start == newNote.textArea.getText().lastIndexOf(tf.getText())){
        	startIndex = 0;
        } // end if
        
        int end = start + tf.getText().length();
        newNote.textArea.select(start, end);
    } // end method find
    
    // replace method
    public void replace(){
    	try{
    	    find();
            newNote.textArea.replaceSelection(tr.getText());
        } // end try
    	
    	 // error message
        catch(NullPointerException e){
        	System.out.print("Null Pointer Exception: " + e);
        } // end catch
    } // end method replace    
} // end class find