package mahjong.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IGMMouseListener extends MouseAdapter {
	private IGMahjong igmc;
	public IGMMouseListener(IGMahjong igmc){
		this.igmc = igmc;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		igmc.reportClic(e.getY(),e.getX());
	}

}
