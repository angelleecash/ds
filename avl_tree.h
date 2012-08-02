#ifndef AVL_TREE_H
#define AVL_TREE_H

typedef int (* AvlCompareFunction) (void* a, void* b);

typedef struct AvlTreeNode
{
	struct AvlTreeNode* left;
	struct AvlTreeNode* right;
	struct AvlTreeNode* parent;
	void* data;

	//height(left) - height(right)
	//valid value: -1, 0, 1
	int balanceFactor;
} AvlTreeNode;

typedef struct AvlTree
{
	AvlTreeNode* root;
	AvlCompareFunction compareFunction;
} AvlTree;

void avlTreeInit(AvlTree* tree, AvlCompareFunction compareFunction);

void avlTreeAdd(AvlTree* tree, void* data);
void avlTreeDelete(AvlTree* tree, void* data);

void avlTreeNodeInit(AvlTreeNode* node, AvlTreeNode* parent, AvlTreeNode* left, AvlTreeNode* right, void* data);
AvlTreeNode* avlTreeCreateNode(AvlTreeNode* parent, AvlTreeNode* left, AvlTreeNode* right, void* data);
void avlTreeNodeLeftRotate(AvlTreeNode* node);
void avlTreeNodeRightRotate(AvlTreeNode* node);
#endif