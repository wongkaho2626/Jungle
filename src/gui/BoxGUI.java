package gui;

import javax.swing.JLabel;

public class BoxGUI {
	private String boxName;
	private JLabel jLabel;
	private String ImageIconLocation;
	
	public BoxGUI(String boxName, JLabel jLabel) {
		this.boxName = boxName;
		this.jLabel = jLabel;
	}

	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public JLabel getjLabel() {
		return jLabel;
	}
	public void setjLabel(JLabel jLabel) {
		this.jLabel = jLabel;
	}
	public String getImageIconLocation() {
		return ImageIconLocation;
	}
	public void setImageIconLocation(String imageIconLocation) {
		ImageIconLocation = imageIconLocation;
	}
}
