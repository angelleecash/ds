package info.chenliang.ds.astar;

import info.chenliang.ds.astar.AStar.Node;

import java.util.List;

public interface AStarMap {
	public List<Node> getAdjacentNodes(Node node);
	public int getG(Node from, Node to);
	public int getH(Node from, Node to);
}
