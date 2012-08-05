#ifndef AVL_TREE_H
#define AVL_TREE_H

typedef int (* AvlCompareFunction) (void* a, void* b);

typedef struct AvlTreeNode
{
	struct AvlTreeNode* left;
	struct AvlTreeNode* right;
	struct AvlTreeNode* parent;
	void* data;
	int balanceFactor;
} AvlTreeNode;

typedef struct AvlTree
{
	AvlTreeNode* root;
	AvlCompareFunction compareFunction;
} AvlTree;

void avlTreeNodeInit(AvlTreeNode* node, AvlTreeNode* parent, AvlTreeNode* left, AvlTreeNode* right, void* data);
AvlTreeNode* avlTreeCreateNode(AvlTreeNode* parent, AvlTreeNode* left, AvlTreeNode* right, void* data);
void avlTreeNodeDestroy(AvlTreeNode* node);

void avlTreeInit(AvlTree* tree, AvlCompareFunction compareFunction);
void avlTreeAdd(AvlTree* tree, void* data);
void avlTreeDelete(AvlTree* tree, void* data);
int avlTreeContain(AvlTree* tree, void* data);
void avlTreeDestroy(AvlTree* tree);

AvlTreeNode* avlTreeLeftRotate(AvlTreeNode* node);
AvlTreeNode* avlTreeRightRotate(AvlTreeNode* node);
AvlTreeNode* avlTreeLeftBalance(AvlTreeNode* node);
AvlTreeNode* avlTreeRightBalance(AvlTreeNode* node);

AvlTreeNode* avlTreeSuccessor(AvlTreeNode* node);
AvlTreeNode* avlTreePrecusor(AvlTreeNode* node);

void traverse(AvlTreeNode* node);
void printTree(AvlTree* tree);

void verifyBalanceFactor(AvlTreeNode* node);
int avlTreeHeight(AvlTreeNode* node);

#endif