import java.awt.Color;

public class Node  
{
	public static boolean RED = false;
	public static boolean BLACK = true;
	public boolean color = RED;
	public Node left;
	public Node right;
	public Node parent;
	public int data;

	public Node() 
	{
	}

	public Node(int data) 
	{
		this.data = data;
	}

	public static int compare(int x, int y)
    {
    	if(x>y)
    		return 1;
    	else if(x==y)
    		return 0;
    	else
    		return -1;
    }
	
	public void setData(int data) 
	{
		this.data = data;
	}

	public void removeFromParent() 
	{
		if (getParent() == null)
			return;
		if (parent.getLeft() == this)
			parent.setLeft(null);
		else if (parent.getRight() == this)
			parent.setRight(null);
		this.parent = null;
	}

	public void setLeft(Node child) 
	{
		if (getLeft() != null)
			getLeft().setParent(null);
		if (child != null) 
		{
			child.removeFromParent();
			child.setParent(this);
		}
		this.left = child;
	}

	public void setRight(Node child) 
	{
		if (getRight() != null) 
		{
			getRight().setParent(null);
		}
		if (child != null) 
		{
			child.removeFromParent();
			child.setParent(this);
		}
		this.right = child;
	}

	public int getData()
	{
		return data;
	}

	public Node getLeft() 
	{
		return left;
	}

	public static Node getLeft(Node node) 
	{
		if(node==null)
			return null;
		else
			return node.getLeft();
	}
	public Node getRight() 
	{
		return right;
	}
	public static Node getRight(Node node) 
	{
		if(node==null)
			return null;
		else
			return node.getRight();
	}
	public static boolean isRed(Node node) 
	{
		return getColor(node) == RED;
	}

	public static boolean isBlack(Node node) 
	{
		return getColor(node) == BLACK;
	}

	public void setColor(boolean color) 
	{
		this.color = color;
	}
	public static void setColor(Node node, boolean color) 
	{
		if (node == null)
			return;
		node.setColor(color);
	}

	public boolean getColor() 
	{
		return color;
	}

	public static boolean getColor(Node node) 
	{
		if(node==null)
			return BLACK ;
		else
			return node.getColor();
	}

	public Color getActualColor() 
	{
		if (getColor() == RED)
			return new Color(250, 70, 70);
		else
			return new Color(70, 70, 70);
	}
	public void SetNewColor() 
	{
		color = !color;
	}

	public static void SetNewColor(Node node) 
	{
		if (node == null)
			return;

		node.setColor(!node.getColor());
	}

	public void setParent(Node parent) 
	{
		this.parent = parent;
	}

	public Node getParent() 
	{
		return parent;
	}

	public static Node getParent(Node node) 
	{
		if(node == null)
			return null;
		else
			return node.getParent();
	}

	public Node getGrandparent() 
	{
		Node parent = getParent();
		if(parent == null)
			return null;
		else
			return parent.getParent();	
	}
	public static Node getGrandparent(Node node) 
	{
		if(node == null)
			return null;
		else
			return node.getGrandparent();
	}

	public Node getChildren() 
	{
		Node parent = getParent();
		if (parent != null) 
		{ 
			if (this == parent.getRight())
			{
				return (Node) parent.getLeft();
			}
			else
			{
				return (Node) parent.getRight();
			}
		}
		return null;
	}
	public Node getUncle() 
	{
		Node parent = getParent();
		if (parent != null) 
		{ 
			return parent.getChildren();
		}
		return null;
	}
	public static Node getUncle(Node node) 
	{
		if(node == null)
			return null;
		else
			return node.getUncle();
	}
}