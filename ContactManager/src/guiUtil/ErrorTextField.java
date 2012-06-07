package guiUtil;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ErrorTextField extends JTextField {
	
	ImageIcon errorIcon = new ImageIcon("Resources/error.png");
	IErrorTextFieldValidator validator;
	boolean error;
	boolean showLeft = false;
	private String watermarkText;


    public ErrorTextField(){
    	this(new IErrorTextFieldValidator () {
    		@Override
    		public boolean validate(String currentText) {
    			return true;
    		}
    	}, "");
    }

    public ErrorTextField(String aWatermarkText){
    	this(new IErrorTextFieldValidator () {
    		@Override
    		public boolean validate(String currentText) {
    			return true;
    		}
    	}, aWatermarkText);
    }

	public ErrorTextField(IErrorTextFieldValidator aValidator){
    	this(aValidator, "");
    }

	
    public ErrorTextField(IErrorTextFieldValidator aValidator, String aWatermarkText){
    	validator = aValidator;
    	watermarkText = aWatermarkText;
    	setOpaque(true);
    	addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				error = ! validator.validate(getText());
				repaint();
			}
		});
    }
    
    public boolean isValid() {
    	return ! error;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
    	super.paintComponent(g);

        // set the icon
        if(error){
	        int x = 0;
            int y = (getHeight() - errorIcon.getIconHeight())/2;
            
            // fix margin
            if(showLeft){        	
	            x = 2;
	        	setMargin(new Insets(2, 2 + errorIcon.getIconWidth(), 2, 2)); 
	        }else{
	        	x = this.getWidth() - errorIcon.getIconWidth()  - 2;
	            setMargin(new Insets(2, 2, 2, 2 + errorIcon.getIconWidth())); 
	        }        

            errorIcon.paintIcon(this, g, x, y);
        }else{
            setMargin(new Insets(2, 2, 2, 2)); 
        }

        
        // if we don't have the focus and there is no text ...
        if (!hasFocus() && getText().length() == 0) {
        	
        	// remember old settings
            Font  oldFont  = g.getFont();
            Color oldColor = g.getColor();
            
            // apply current settings
            g.setFont(oldFont.deriveFont(Font.ITALIC));
            g.setColor(new Color(133, 133, 133));

            // calc text position
            int h = g.getFontMetrics().getHeight();
            int textBottom = (getHeight() - h) / 2 + h - 4;

            // Draw Text
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints renderHints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
            					 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2d.drawString(watermarkText, this.getInsets().left, textBottom);
            g2d.setRenderingHints(renderHints);

            // apply old settings
            g.setFont(oldFont);
            g.setColor(oldColor);
        }

    }


}


