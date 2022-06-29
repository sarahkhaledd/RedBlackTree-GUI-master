
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class GUI extends JPanel {

	private static final long serialVersionUID = 1250L;

	private RedBlackTree tree;
	private int radius = 15;
	private int y = 30;
	private Color textColor = new Color(200, 210, 210);
	private int Search;

	public GUI(RedBlackTree tree) 
	{
		this.tree = tree;
	}
	public void setSearch(int c) {
		Search = c;
	}
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		if (tree.isEmpty())
			return;
		Graphics2D graphics2d ;
		graphics2d= (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		paintTree(graphics2d, (Node) tree.getRoot(), getWidth() / 2, 30,getGap());
	}
	private void paintTree(Graphics2D g, Node root, int x, int y,int xOffset) 
	{
		if (x < 0)
		{
			setPreferredSize(new Dimension(2 * getWidth(), getHeight()));
		}
		if (Search != 0 && Node.compare(root.getData(), Search) == 0)
		{
			//g.setColor(Color.magenta);
			radius += 5;
			g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
			radius -= 5;
		}
		 drawNode(g, root, x, y);
		if (root.getLeft() != null) 
		{
			join(g, x - xOffset, y + y, x, y);
			paintTree(g, (Node) root.getLeft(), x - xOffset, y + y,xOffset / 2);
		}

		if (root.getRight() != null) 
		{
			join(g, x + xOffset, y + y, x, y);
			paintTree(g, (Node) root.getRight(), x + xOffset, y + y,xOffset / 2);
		}
	}
	private void drawNode(Graphics2D g, Node node, int x, int y) 
	{
		g.setColor(node.getActualColor());
		g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		g.setColor(textColor);
		int z=node.getData();
		String text = String.format("%d", z);
		FontMetrics fm = g.getFontMetrics(); //drawCenterText
		double t_width = fm.getStringBounds(text, g).getWidth();
		g.drawString(text, (int) (x - t_width / 2),(int) (y + fm.getMaxAscent() / 2));
		g.setColor(Color.BLACK);
	}

	private void join(Graphics2D g, int x1, int y1, int x2, int y2) 
	{
		double hypot = Math.hypot(y, x2 - x1);
		int x11 = (int) (x1 + radius * (x2 - x1) / hypot);
		int y11 = (int) (y1 - radius * y / hypot);
		int x21 = (int) (x2 - radius * (x2 - x1) / hypot);
		int y21 = (int) (y2 + radius * y / hypot);
		g.drawLine(x11, y11, x21, y21);
	}
	private int getGap() 
	{
		int depth = tree.getDepth();
		int multiplier = 30;
		float exponent = (float) 1.4;
		if (depth > 6) 
		{
			multiplier += depth * 6;
			exponent += .3;
		}
		return (int) Math.pow(depth, exponent) * multiplier;
	}

}
