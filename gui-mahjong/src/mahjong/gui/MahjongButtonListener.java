package mahjong.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class MahjongButtonListener implements ActionListener {
	private ArrayList<String> idents = new ArrayList<String>();
	private ArrayList<JButton> jbuts = new ArrayList<JButton>();
	private MahjongControler controler;

	public MahjongButtonListener(MahjongControler mc) {
		this.controler = mc;
	}
	

	public void addButton(String ident, JButton button) {
		button.addActionListener(this);
		idents.add(ident);
		jbuts.add(button);
	}


	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controler.buttonPressed(idents.get(jbuts.indexOf(arg0.getSource())));
		
	}

}
