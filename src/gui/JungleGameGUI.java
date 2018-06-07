package gui;
import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class JungleGameGUI {

	public JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnNewStartButton, btnLoadButton, btnSaveButton;
	private JScrollPane scrollPane;
	private JPanel panel;
	private List<String> trapArray, riverArray;

	private List<BoxGUI> boxGUIList;
	private List<PieceGUI> pieceGUIList;
	private HashMap<String, BoxGUI> chessBoardMapGUI;
	private HashMap<String, PieceGUI> pieceMapGUI;

	public JungleGameGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 46, 396, 655);

		textField = new JTextField();
		textField.setBounds(598, 712, 396, 26);
		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(null);
		GridBagConstraints textFieldgbc = new GridBagConstraints();
		textFieldgbc.insets = new Insets(0, 0, 0, 0);
		contentPane.add(textField, textFieldgbc);
		contentPane.add(scrollPane);
		
		btnNewStartButton = new JButton("New Start");
		btnNewStartButton.setBounds(598, 11, 117, 31);
		contentPane.add(btnNewStartButton);
		
		btnSaveButton = new JButton("Save");
		btnSaveButton.setBounds(737, 11, 117, 31);
		contentPane.add(btnSaveButton);
		
		btnLoadButton = new JButton("Load");
		btnLoadButton.setBounds(877, 11, 117, 31);
		contentPane.add(btnLoadButton);
	
	}
	
	public void startNewGame() {
		startNewPanel();
		
		trapArray = Arrays.asList("C1", "C9", "D2", "D8", "E1", "E9");
		riverArray = Arrays.asList("B4", "B5", "B6", "C4", "C5", "C6", "E4", "E5", "E6", "F4", "F5", "F6");
		
		boxGUIList = new ArrayList<BoxGUI>();
		pieceGUIList = new ArrayList<PieceGUI>();
		chessBoardMapGUI = new HashMap<String, BoxGUI>();
		pieceMapGUI = new HashMap<String, PieceGUI>();
		
		int cnt = 1;
		for(char alphabet = 'A'; alphabet <= 'G'; alphabet++) {
			JLabel jLabel = new JLabel("" + alphabet);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 0, 5, 5);
			gbc.gridx = cnt;
			gbc.gridy = 0;
			panel.add(jLabel, gbc);
			cnt++;
		}
		
		cnt = 9;
		for(int i = 1; i < 10; i++) {
			JLabel jLabel = new JLabel(String.valueOf(cnt));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 0, 5, 5);
			gbc.gridx = 0;
			gbc.gridy = i;
			panel.add(jLabel, gbc);
			cnt--;
		}
		
		for (char alphabet = 'A'; alphabet <= 'G'; alphabet++) {
			for(int i = 1; i <= 9; i++) {
				BoxGUI boxGUI = new BoxGUI(alphabet+String.valueOf(i), new JLabel());
				boxGUIList.add(boxGUI);
			}
		}
		
		for(BoxGUI boxGUI : boxGUIList) {
			if(boxGUI.getBoxName().equals("D1") || boxGUI.getBoxName().equals("D9")) {
				boxGUI.setImageIconLocation("./resource/den.png");
			}else if(trapArray.contains(boxGUI.getBoxName())) {
				boxGUI.setImageIconLocation("./resource/trap.png");
			}else if(riverArray.contains(boxGUI.getBoxName())) {
				boxGUI.setImageIconLocation("./resource/river.png");
			}else if(boxGUI.getBoxName().equals("A3") || boxGUI.getBoxName().equals("G7")) {
				boxGUI.setImageIconLocation("./resource/elephant.png");
			}else if(boxGUI.getBoxName().equals("G1") || boxGUI.getBoxName().equals("A9")) {
				boxGUI.setImageIconLocation("./resource/lion.png");
			}else if(boxGUI.getBoxName().equals("A1") || boxGUI.getBoxName().equals("G9")) {
				boxGUI.setImageIconLocation("./resource/tiger.png");
			}else if(boxGUI.getBoxName().equals("E3") || boxGUI.getBoxName().equals("C7")) {
				boxGUI.setImageIconLocation("./resource/leopard.png");
			}else if(boxGUI.getBoxName().equals("C3") || boxGUI.getBoxName().equals("E7")) {
				boxGUI.setImageIconLocation("./resource/wolf.png");
			}else if(boxGUI.getBoxName().equals("F2") || boxGUI.getBoxName().equals("B8")) {
				boxGUI.setImageIconLocation("./resource/dog.png");
			}else if(boxGUI.getBoxName().equals("B2") || boxGUI.getBoxName().equals("F8")) {
				boxGUI.setImageIconLocation("./resource/cat.png");
			}else if(boxGUI.getBoxName().equals("G3") || boxGUI.getBoxName().equals("A7")) {
				boxGUI.setImageIconLocation("./resource/rat.png");
			}else {
				boxGUI.setImageIconLocation("./resource/empty.png");
			}
		}
		
		int x = 1;
		int y = 9;
		for(BoxGUI boxGUI : boxGUIList) {
			boxGUI.getjLabel().setIcon(new ImageIcon(boxGUI.getImageIconLocation()));
			if(y == 9) {
				if(x == 7) {
					boxGUI.getjLabel().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
				}else {
					boxGUI.getjLabel().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.red));
				}
			}else if(x == 7) {
				boxGUI.getjLabel().setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.red));
			}else {
				boxGUI.getjLabel().setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.red));
			}
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 0, 0);
			gbc.gridx = x;
			gbc.gridy = y;
			panel.add(boxGUI.getjLabel(), gbc);
			y--;
			if(y == 0) {
				x++;
				y = 9;
			}
		}
		
		for(BoxGUI boxGUI : boxGUIList) {
			chessBoardMapGUI.put(boxGUI.getBoxName(), boxGUI);
		}
		
		panel.revalidate();
		panel.repaint();
	}
	
	public void startNewPanel() {
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(6, 11, 580, 737);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		panel.revalidate();
		panel.repaint();
	}
	
	public void reset() {
		boxGUIList = null;
		pieceGUIList = null;
		chessBoardMapGUI = null;
		pieceMapGUI = null;
		panel = null;
	}
	
	public JButton getBtnNewStartButton() {
		return btnNewStartButton;
	}
	
	public JButton getBtnSaveButton() {
		return btnSaveButton;
	}
	
	public JButton getBtnLoadButton() {
		return btnLoadButton;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public HashMap<String, BoxGUI> getChessBoardMapGUI(){
		return chessBoardMapGUI;
	}
	
	public HashMap<String, PieceGUI> getPieceMapGUI(){
		return pieceMapGUI;
	}
	
	public void setPieceMapGUI(String pieceLocation, PieceGUI pieceGUI) {
		pieceMapGUI.put(pieceLocation, pieceGUI);
	}
	
	public List<PieceGUI> getPieceGUIList(){
		return pieceGUIList;
	}
	
	public List<BoxGUI> getBoxGUIList(){
		return boxGUIList;
	}
}
