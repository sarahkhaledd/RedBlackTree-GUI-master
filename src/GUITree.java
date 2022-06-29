
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GUITree extends JPanel {
	private static final long serialVersionUID = 1L;
	private RedBlackTree tree = new RedBlackTree();
	private GUI gui = new GUI(tree);
	final JTextField Text = new JTextField(5);
	final JButton insert = new JButton();
	final JButton delete = new JButton();
	final JButton clear = new JButton();
	
	public GUITree() 
	{
		gui.setBackground(Color.LIGHT_GRAY);
		super.setLayout(new BorderLayout());
		ScrollPanal();
		setBottomPanel();
	}
	private void setPoint(JScrollPane scrollPane) 
	{
		scrollPane.getViewport().setViewPosition(new Point(750, 0));
	}
	private void setBottomPanel() 
	{
		Button(insert, "insert");
		Button(delete, "delete");
		Button(clear, "clear");
		JPanel panel = new JPanel();
		panel.add(clear);
		panel.add(Text);
		panel.add(delete);
		panel.add(insert);
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.SOUTH);
		insert.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent) 
			{
				if (Text.getText().equals(""))
					return;

				Integer elemet = Integer.parseInt(Text.getText());
				if (tree.contains(elemet)) {
					JOptionPane.showMessageDialog(null,
							"Number exists enter another one\r\n");
				} else {
					tree.insert(elemet);
					gui.repaint();
					Text.requestFocus();
					Text.selectAll();
				}
			}
		});

		delete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent) 
			{
				if (Text.getText().equals(""))
					return;

				Integer delete = Integer.parseInt(Text.getText());
				if (!tree.contains(delete)) {
					JOptionPane.showMessageDialog(null,
							"Number exists enter another one\r\n");
				} else {
					tree.delete(delete);
					gui.repaint();
					Text.requestFocus();
					Text.selectAll();
				}
			}
		});

		clear.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent) 
			{
				if (tree.isEmpty())
					JOptionPane
							.showMessageDialog(null, "Empty tree");
				else
					tree.clear();

				gui.setSearch(0);
				gui.repaint();
			}
		});

		Text.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				insert.doClick();
			}
		});
	}

	private void Button(JButton button, String img) 
	{
		try 
		{
			Image icon = ImageIO.read(getClass().getResource("/resources/" + img + ".png"));
			button.setIcon(new ImageIcon(icon));
			button.setBorderPainted(false);
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ScrollPanal() 
	{
		gui.setPreferredSize(new Dimension(2000, 1096));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(gui);
		scroll.setPreferredSize(new Dimension(550, 400));
		setPoint(scroll);
		add(scroll, BorderLayout.CENTER);
	}
}