package guiUtil;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Format;

import javax.swing.Icon;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;


@SuppressWarnings("serial")
public class WatermarkTextField extends JFormattedTextField 
								implements FocusListener {

    public enum Position{
    	LEFT,
    	RIGHT
    }

    private String alternativeText = new String();
    private Icon icon = null;
    private Position iconPosition = Position.LEFT;
    
    public WatermarkTextField(Format format){
    	super(format);
    	init();
    }
    
    public WatermarkTextField(MaskFormatter format){
    	super(format);
    	init();    	
    }
    
    public WatermarkTextField() {
        super();
        init();
    }
    
    public void init(){
    	setMargin(new Insets(2, 2, 2, 2)); 
    	addFocusListener(this);    	
    }


    public void setIcon(Icon icon){
    	setIcon(icon, Position.LEFT);
    }
    
    public void setIcon(Icon icon, Position pos){
        this.icon = icon;
        this.iconPosition = pos;
    }

    public void setAlternativeText(String text) {
    	alternativeText = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // set the icon
        if(icon != null){
	        int x = 0;
            int y = (getHeight() - icon.getIconHeight())/2;
            
            // fix margin
            if(iconPosition == Position.LEFT){        	
	            x = 2;
	        	setMargin(new Insets(2, 2 + icon.getIconWidth(), 2, 2)); 
	        }else{
	        	x = this.getWidth() - icon.getIconWidth()  - 2;
	            setMargin(new Insets(2, 2, 2, 2 + icon.getIconWidth())); 
	        }        

            icon.paintIcon(this, g, x, y);
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

            g2d.drawString(alternativeText, this.getInsets().left, textBottom);
            g2d.setRenderingHints(renderHints);

            // apply old settings
            g.setFont(oldFont);
            g.setColor(oldColor);
        }
    }

    public void focusGained(FocusEvent e) {
        repaint();
    }

    public void focusLost(FocusEvent e) {
        repaint();
    }

}
