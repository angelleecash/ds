package info.chenliang.ds.astar;

import java.util.ArrayList;
import java.util.List;

public class AStar {
	
	
	static class Node
	{
		int x,y;
		
		Node parent;
		int g,h;
	}
	
	static class OpenList
	{
		List<AStar.Node> list = new ArrayList<AStar.Node>();
		
		public Node pickLowestCostNode()
		{
			return null;
		}
		
		public void addNode(Node node)
		{
			
		}
		
		public boolean contains(Node node)
		{
			return false;
		}
		
		public boolean isEmpty()
		{
			return false;
		}
	}
	
	static class ClosedList
	{
		List<AStar.Node> list = new ArrayList<AStar.Node>();
		
		public void addNode(Node ndoe)
		{
			
		}
		
		public boolean contains(Node node)
		{
			return false;
		}
		
		public Path generatePath()
		{
			return null;
		}
	}
	
	static class Path
	{
		List<AStar.Node> list = new ArrayList<AStar.Node>();
	}
	
	OpenList openList;
	ClosedList closedList;
	
	public AStar() {
		super();
		this.openList = new OpenList();
		this.closedList = new ClosedList();
	}
	
	public Path execute(AStarMap map, Node from, Node to)
	{
		Path path = null;
		
		openList.addNode(from);
		while(!openList.isEmpty())
		{
			Node newNodeInPath = openList.pickLowestCostNode();
			closedList.addNode(newNodeInPath);
			
			if(closedList.contains(to))
			{
				path = closedList.generatePath();
				break;
			}
			
			List<Node> adjacentNodes = map.getAdjacentNodes(newNodeInPath);
			for(Node adjacentNode : adjacentNodes)
			{
				int newGCost = map.getG(newNodeInPath, adjacentNode);
				if(openList.contains(adjacentNode))
				{
					if(adjacentNode.g > newNodeInPath.g + newGCost)
					{
						adjacentNode.parent = newNodeInPath;
						adjacentNode.g = newNodeInPath.g + newGCost;
					}
				}
				else
				{	
					adjacentNode.parent = newNodeInPath;
					adjacentNode.g = newNodeInPath.g + newGCost;
					adjacentNode.h = map.getH(adjacentNode, to);
					
					openList.addNode(adjacentNode);
				}
			}
		}
		
		return path;
	}

	public static void main(String[] args) {
		
	}

	
}
