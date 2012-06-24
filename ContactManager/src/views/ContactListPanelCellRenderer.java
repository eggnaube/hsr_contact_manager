package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import domain.Contact;



public class ContactListPanelCellRenderer implements ListCellRenderer {

	RenderPanel renderPanel = new RenderPanel();

	public ContactListPanelCellRenderer () {
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object obj,
			int row, boolean isSelected, boolean hasFocus) {

		Contact contact = (Contact) obj;
		renderPanel.showContact(contact, isSelected);

		return renderPanel;
	}

	private class RenderPanel extends javax.swing.JPanel {

		private static final long serialVersionUID = 1L;
		private JLabel topLabel;
		private JPanel bottomPanel;
		private JLabel bottomLabel;
		private JPanel space;


		public void showContact(Contact contact, boolean isSelected) {
			topLabel.setText(contact.getName().equals("") == true ? "«New Contact»": contact.getName());
			bottomLabel.setText(contact.getFirstName());
			if(isSelected){
				this.setBackground(Color.YELLOW);
				topLabel.setForeground(Color.BLACK);				
				bottomLabel.setForeground(Color.BLACK);				
			}
			else{
				this.setBackground(Color.WHITE);
				bottomLabel.setForeground(Color.BLACK);				
				bottomLabel.setForeground(Color.BLACK);				
			}

		}

		public RenderPanel() {
			super();
			initGUI();
		}

		private void initGUI() {
			try {
				GridLayout thisLayout = new GridLayout(2, 1);
				thisLayout.setColumns(1);
				//thisLayout.setHgap(5);
				//thisLayout.setVgap(5);
				thisLayout.setRows(2);
				this.setLayout(thisLayout);
				this.setPreferredSize(new java.awt.Dimension(200, 40));
				{
					topLabel = new JLabel();
					this.add(topLabel);
				}
				{
					bottomPanel = new JPanel();
					bottomPanel.setOpaque(false);
					FlowLayout bottomPanelLayout = new FlowLayout();
					bottomPanelLayout.setAlignment(FlowLayout.LEFT);
					bottomPanel.setLayout(bottomPanelLayout);
					this.add(bottomPanel);
					{
						space = new JPanel();
						bottomPanel.add(space);
						space.setPreferredSize(new java.awt.Dimension(15, 5));
						space.setOpaque(false);
					}
					{
						bottomLabel = new JLabel();
						bottomPanel.add(bottomLabel);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
}
