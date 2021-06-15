package main;


/* TextDemo.java requires no other files. */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class TextDemo extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextField textField;
    static JTextArea textArea;
    final static String newline = "\n";
 
    public TextDemo() {
        super(new GridBagLayout());
 
        textField = new JTextField(20);
        textField.addActionListener(this);
 
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }
 
    @SuppressWarnings("exports")
	public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        
        textArea.append(text + newline);
        if(text.contains("URL=")) 
        {
        	text = text.replace("URL=","");//  URL=http://localhost/Skyetek/datalog.php
        	Generalist.textUrl = text;
        	textArea.append("Url changed" + newline);
        	System.out.println("url changed by: "+text);
        }
        
        if(text.contains("help")) 
        {
        	textArea.append("-Set URL:\n");
        	textArea.append("  type URL=http://localhost/Skyetek/datalog.php to set on the localhost connection\n");
        	textArea.append("-Others ...\n");
        	
        }
        
        textField.selectAll();
 
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
                
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new TextDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    /*public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                
            }
        });
    }*/
    
    public static void writeinframe(String text) 
	{
    	textArea.append(text + newline);
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
