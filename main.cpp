#include "avl_tree.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

int assert(int v)
{
	if(!v)
	{
		printf("\nfuck");
		getchar();
		exit(0);
	}
}

int compare(void* a, void* b)
{
	if(*(int*)a == *(int*)b)
	{
		return 0;
	}
	else if(*(int*)a < *(int*)b)
	{
		return -1;
	}
	else
	{
		return 1;
	}
}

void main()
{
	AvlTree tree;
	avlTreeInit(&tree, compare);

	/*
	int a[] = {1, -1 , 33, 7, 0, 77, 8};

	

	for(int i= 0;i < 7;i++)
	{
		avlTreeAdd(&tree, &a[i]);
		printTree(&tree);
	}
	*/
	
	int b[1000];


	

	for(int k=0;k<300;k++)
	{
		int c = 0;
		srand((unsigned)time(0));
		for(int q=0;q<1000;q++)
		{
			int v = rand();
			int exist = 0;
			for(int t=0;t<c;t++)
			{
				if(b[t]==v)
				{
					exist = 1;
					break;
				}
			}

			if(exist)
			{
				assert(avlTreeContain(&tree, &v));
			}
			else
			{
				assert(!avlTreeContain(&tree, &v));
				avlTreeAdd(&tree, &v);
				assert(avlTreeContain(&tree, &v));
				b[c++] = v;
			}

			
		}
	}
	

	

	getchar();
}