#include <stdlib.h>
#include <memory.h>
#include "avl_tree.h"
#include <stdio.h>

void traverse(AvlTreeNode* node)
{
	if(node)
	{
		traverse(node->left);
		printf("%d ", *(int*)node->data);
		traverse(node->right);
	}
}

void printTree(AvlTree* tree)
{
	AvlTreeNode* node = tree->root;
	traverse(node);
	printf("\n");
}


void avlTreeNodeInit(AvlTreeNode* node, AvlTreeNode* parent, AvlTreeNode* left, AvlTreeNode* right, void* data)
{
	node->parent = parent;
	node->left = left;
	node->right = right;
	node->data = data;
	node->balanceFactor = 0;
}

AvlTreeNode* avlTreeCreateNode(AvlTreeNode* parent, AvlTreeNode* left, AvlTreeNode* right, void* data)
{
	AvlTreeNode* node = (AvlTreeNode*)malloc(sizeof(AvlTreeNode));
	avlTreeNodeInit(node, parent, left, right, data);
	return node;
}

void avlTreeInit(AvlTree* tree, AvlCompareFunction compareFunction)
{
	tree->compareFunction = compareFunction;
	tree->root = 0;
}

int avlTreeContain(AvlTree* tree, void* data)
{
	if(tree->root)
	{
		AvlTreeNode* node = tree->root;
		while(node)
		{
			int result = tree->compareFunction(data, node->data);
			if(result == 0)
			{
				return true;
			}
			else if(result == -1)
			{
				node = node->left;
			}
			else
			{
				node = node->right;
			}
		}
	}
	return 0;
}

void avlTreeAdd(AvlTree* tree, void* data)
{
	if(avlTreeContain(tree, data))
	{
		return;
	}

	if(!tree->root)
	{
		tree->root = avlTreeCreateNode(0, 0, 0, data);
		return;
	}

	AvlTreeNode* node = tree->root;
	AvlTreeNode* previousNode = 0;
	int c = 0;
	while(node)
	{
		c = tree->compareFunction(data, node->data);
		previousNode = node;
		if(c == -1)
		{
			node = node->left;
		}
		else
		{
			node = node->right;
		}
	}

	AvlTreeNode* newNode = avlTreeCreateNode(previousNode, 0, 0, data);
	if(c == -1)
	{
		previousNode->left = newNode;
	}
	else
	{
		previousNode->right = newNode;
	}

	while(previousNode)
	{
		if(tree->compareFunction(previousNode->data, data) == -1)
		{
			previousNode->balanceFactor -= 1;
		}
		else
		{
			previousNode->balanceFactor += 1;
		}

		if(previousNode->balanceFactor == 0)
		{
			break;
		}
		else if(previousNode->balanceFactor == 2)
		{
			AvlTreeNode* newRoot = avlTreeRightBalance(previousNode);
			if(previousNode == tree->root)
			{
				tree->root = newRoot;
			}
			break;
		}
		else if(previousNode->balanceFactor == -2)
		{
			AvlTreeNode* newRoot = avlTreeLeftBalance(previousNode);
			if(previousNode == tree->root)
			{
				tree->root = newRoot;
			}
			break;
		}

		previousNode = previousNode->parent;
	}
}

AvlTreeNode* avlTreeSuccessor(AvlTreeNode* node)
{
	AvlTreeNode* sucessor = node->right;

	while(sucessor && sucessor->left)
	{
		sucessor = sucessor ->left;
	}

	return sucessor;
}

AvlTreeNode* avlTreePrecusor(AvlTreeNode* node)
{
	AvlTreeNode* precursor = node->left;
	while(precursor && precursor->right)
	{
		precursor = precursor->right;
	}

	return precursor;
}

void avlTreeDelete(AvlTree* tree, void* data)
{
	if(!tree->root)
	{
		return;
	}

	if(!avlTreeContain(tree, data))
	{
		return;
	}

	AvlTreeNode* node = tree->root;

	while(node)
	{
		int result = tree->compareFunction(data, node->data);
		if(result == 0)
		{
			break;
		}
		else if(result == -1)
		{
			node = node->left;
		}
		else
		{
			node = node->right;
		}
	}

	AvlTreeNode* successor = avlTreeSuccessor(node);
	if(successor)
	{
		node->data = successor->data;
		node = successor;
	}
	else
	{
		AvlTreeNode* precursor = avlTreePrecusor(node);
		if(precursor)
		{
			node = precursor;
		}
	}

	if(node->right)
	{
		AvlTreeNode* successor = avlTreeSuccessor(node);
		node->data = successor->data;
		node = successor;
	}

	AvlTreeNode* parent = node->parent;

	while(parent)
	{
		int result = tree->compareFunction(data, parent->data);
		if(result == -1)
		{
			parent->balanceFactor -= 1;
		}
		else
		{
			parent->balanceFactor += 1;
		}

		if(parent->balanceFactor == -1 || parent->balanceFactor == 1)
		{
			break;
		}
		else if(parent->balanceFactor == -2)
		{
			AvlTreeNode* newRoot = avlTreeRightBalance(parent);
			if(parent == tree->root)
			{
				tree->root = newRoot;
			}
		}
		else if(parent->balanceFactor == 2)
		{
			AvlTreeNode* newRoot = avlTreeLeftBalance(parent);
			if(parent == tree->root)
			{
				tree->root = newRoot;
			}
		}
		parent = parent->parent;
	}

	if(node->parent)
	{
		int result = tree->compareFunction(node->data, node->parent->data);
		if(result == -1)
		{
			node->parent->left = 0;
		}
		else
		{
			node->parent->right = 0;
		}
	}

	free(node);

	if(node == tree->root)
	{
		tree->root = 0;
	}
}

AvlTreeNode* avlTreeLeftRotate(AvlTreeNode* node)
{
	AvlTreeNode* rightNode = node->right;
	node->right = rightNode->left;

	rightNode->parent = node->parent;
	node->parent = rightNode;

	rightNode->left = node;

	node->balanceFactor += 1;
	if(rightNode->balanceFactor < 0)
	{
		node->balanceFactor -= rightNode->balanceFactor;
	}

	rightNode->balanceFactor += 1;
	if(node->balanceFactor > 0)
	{
		rightNode->balanceFactor += node->balanceFactor;
	}
	return rightNode;
}

AvlTreeNode* avlTreeRightRotate(AvlTreeNode* node)
{
	AvlTreeNode* leftNode = node->left;
	node->left = leftNode->right;

	leftNode->parent = node->parent;
	node->parent = leftNode;

	node->balanceFactor -= 1;
	if(leftNode->balanceFactor > 0)
	{
		node->balanceFactor -= leftNode->balanceFactor;
	}

	leftNode->balanceFactor -= 1;
	if(node->balanceFactor < 0)
	{
		leftNode->balanceFactor += node->balanceFactor;
	}

	return leftNode;
}

AvlTreeNode* avlTreeLeftBalance(AvlTreeNode* node)
{
	if(node->left->balanceFactor == -1)
	{
		avlTreeLeftRotate(node->left);
	}

	return avlTreeRightRotate(node);
}

AvlTreeNode* avlTreeRightBalance(AvlTreeNode* node)
{
	if(node->right->balanceFactor == 1)
	{
		avlTreeRightRotate(node->right);
	}

	return avlTreeLeftRotate(node);
}