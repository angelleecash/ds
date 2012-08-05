#include "avl_tree.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <string.h>

void assert(int v, const char* message)
{
	if(!v)
	{
		printf("%s.\n", message);
		
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

void avlTreeBasicTestAdd()
{
	
	//int a[] = {1, -1 , 33, 7, 0, 77, 8};

	//int a[] = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	int a[] = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
	
	AvlTree tree;
	avlTreeInit(&tree, compare);

	for(int i= 0;i < 16;i++)
	{
		assert(!avlTreeContain(&tree, &a[i]), "Should not contain.");
		avlTreeAdd(&tree, &a[i]);
		assert(avlTreeContain(&tree, &a[i]), "Should contain.");
		printTree(&tree);
	}
}

void avlTreeRandomTestAdd()
{
	const int num = 200000;

	int b[num];

	for(int k=0;k<1;k++)
	{
		AvlTree tree;
		avlTreeInit(&tree, compare);
		int c = 0;
		srand((unsigned)time(0));
		for(int q=0;q<num;q++)
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
				assert(avlTreeContain(&tree, &v), "Exist element not found.");
			}
			else
			{
				b[c] = v;
				assert(!avlTreeContain(&tree, &b[c]), "Not Exist element found.");
				avlTreeAdd(&tree, &b[c]);
				assert(avlTreeContain(&tree, &b[c]), "Exist element not found.");
				
				c++;
			}
		}

		avlTreeDestroy(&tree);
	}
}

void avlTreeBasicTestDelete()
{
	
	//int a[] = {1, -1 , 33, 7, 0, 77, 8};

	//int a[] = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	int a[] = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
	
	AvlTree tree;
	avlTreeInit(&tree, compare);

	for(int i= 0;i < 16;i++)
	{
		//assert(!avlTreeContain(&tree, &a[i]), "Should not contain.");
		avlTreeAdd(&tree, &a[i]);
		//assert(avlTreeContain(&tree, &a[i]), "Should contain.");
		//printTree(&tree);
		verifyBalanceFactor(tree.root);
	}

	//printf("--------------------------------------------\n");

	//for(int i=0;i<16;i++)
	for(int i=15;i>=0;i--)
	{
		//printf("--------------------------------------------\n");
		//printf("Before deleting %d.\n", a[i]);
		//printTree(&tree);
		//printf("\n");
		avlTreeDelete(&tree, &a[i]);
		verifyBalanceFactor(tree.root);
		assert(!avlTreeContain(&tree, &a[i]), "Could not delete");
		//printf("\n");
		//printf("After deleting %d.\n", a[i]);
		//printTree(&tree);
		//printf("--------------------------------------------\n\n");
		
	}
}

void avlTreeRandomTestDelete()
{
	srand((unsigned)time(0));
	/*
	int a[] = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
	const int len = sizeof(a) / sizeof(int);
	for(int n=0;n < 1; n++)
	{
		AvlTree tree;
		avlTreeInit(&tree, compare);

		int m[len];
		memcpy(m, a, sizeof(a));
		int ii[len];

		for(int i= 0;i < len;i++)
		{
			avlTreeAdd(&tree, &m[i]);
			ii[i]=i;
		}

		int c = len;

		while(c > 0)
		{
			int in = rand();
			if(in < 0)
			{
				in = -in;
			}

			in %= c;
			int ri = ii[in];
			printf("deleting %d.\n", m[ri]);
			printf("before\n");
			printTree(&tree);
			printTree(&tree);
			printTree(&tree);
			printf("\n");
			assert(avlTreeContain(&tree, &m[ri]), "-------------WTF.");
			avlTreeDelete(&tree, &m[ri]);
			verifyBalanceFactor(tree.root);
			assert(!avlTreeContain(&tree, &m[ri]), "------------Unable to delete.");

			printf("after\n");
			printTree(&tree);
			printTree(&tree);
			printTree(&tree);
			
			for(int z=in;z<c-1;z++)
			{
				ii[z] = ii[z+1];
			}

			c--;
			printTree(&tree);
			printf("\n\n");
		}
		
		avlTreeDestroy(&tree);
	}
	*/
	

	
	const int num = 200000;

	int b[num];

	for(int k=0;k<1;k++)
	{
		AvlTree tree;
		avlTreeInit(&tree, compare);
		int c = 0;
		
		for(int q=0;q<num;q++)
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

			if(!exist)
			{
				b[c] = v;
				assert(!avlTreeContain(&tree, &b[c]), "Not Exist element found.");
				avlTreeAdd(&tree, &b[c]);
				assert(avlTreeContain(&tree, &b[c]), "Exist element not found.");
				
				c++;
			}
		}

		int* ii = (int*)malloc(sizeof(int) * c);
		for(int w=0;w< c;w++)
		{
			ii[w] = w;
		}

		while(c > 0)
		{
			int r = rand();
			if(r < 0)
			{
				r = -r;
			}

			r %= c;
			int ri = ii[r];
			assert(avlTreeContain(&tree, &b[ri]), "WTF.");
			avlTreeDelete(&tree, &b[ri]);
			//verifyBalanceFactor(tree.root);
			assert(!avlTreeContain(&tree, &b[ri]), "Unable to delete.");

			for(int j=r;j<c-1;j++)
			{
				ii[j] = ii[j+1];
			}
			c--;
		}

		free(ii);

		avlTreeDestroy(&tree);
	}
	
}


void main()
{
	//avlTreeBasicTestAdd();
	//avlTreeRandomTestAdd();
	//avlTreeBasicTestDelete();
	avlTreeRandomTestDelete();
	printf("OK");
	getchar();
}