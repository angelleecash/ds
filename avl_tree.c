#include <stdlib.h>
#include <memory.h>
#include "avl_tree.h"

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

void avlTreeAdd(AvlTree* tree, void* data)
{
	if(!tree->root)
	{
		tree->root = avlTreeCreateNode(0, 0, 0, data);
		return;
	}

	AvlTreeNode* p = tree->root;
	AvlTreeNode* lp = 0;
	int c = 0;
	while(p)
	{
		c = tree->compareFunction(data, p->data);
		lp = p;
		if(c == -1)
		{
			p = p->left;
		}
		else
		{
			p = p->right;
		}
	}

	AvlTreeNode* newNode = avlTreeCreateNode(lp, 0, 0, data);
	if(c == -1)
	{
		lp->left = newNode;
	}
	else
	{
		lp->right = newNode;
	}

	p = newNode;
	while(lp)
	{
		if(p == lp->left)
		{
			lp->balanceFactor += 1;
		}
		else
		{
			lp->balanceFactor -= 1;
		}

		if(lp->balanceFactor == 0)
		{
			break;
		}
		else if(lp->balanceFactor >= 2)
		{
		}
		else if(lp->balanceFactor <= -2)
		{
		}
	}
}

void avlTreeDelete(AvlTree* tree, void* data)
{
}