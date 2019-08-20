#include<iostream>
using namespace std;

class TreeNode {
	friend class Tree;
private:
	int data;
	TreeNode* leftChild;
	TreeNode* rightChild;

public:
	TreeNode(int temp = 0) : data(temp), leftChild(nullptr), rightChild(nullptr)
	{
	}

	TreeNode* operator=(const TreeNode* temp) {
		data = temp->data;
		leftChild = temp->leftChild;
		rightChild = temp->rightChild;
		return this;
	}
};

class Stack {
public:
	Stack(int stackCapacity = 100) : capacity(stackCapacity)
	{
		stack = new TreeNode[stackCapacity];
		top = -1;
	}
	bool IsEmpty() const;
	TreeNode Top() const;
	void Push(const TreeNode* item);
	TreeNode* Pop();
	void ChangeSize(TreeNode* a, const int oldSize, const int newSize);
private:
	TreeNode* stack;
	int top;
	int capacity;
};

class Tree {
public:
	void recInorder();
	void recInorder(TreeNode* currentNode);
	void recPreorder();
	void recPreorder(TreeNode* currentNode);
	void recPostorder();
	void recPostorder(TreeNode* currentNode);

	void NonrecInorder();
	void NonrecPreorder();
	void NonrecPostorder();

	void Insert(int val);
	void Insert(int val, TreeNode*& node);
	inline void Visit(TreeNode* Node);
private:
	TreeNode* root;
};

void Tree::Insert(int val) {
	Insert(val, root);
}

void Tree::Insert(int val, TreeNode*& node) {
	if (node == nullptr) {
		node = new TreeNode(val);
	}
	else {
		if (node->data < val) {
			Insert(val, node->rightChild);
		}
		else {
			Insert(val, node->leftChild);
		}
	}
}

void Tree::NonrecPostorder() {
	Stack* s = new Stack();
	TreeNode* currentNode = root;
}

void Tree::NonrecPreorder() {
	Stack* s = new Stack();
	TreeNode* currentNode = root;
	while (1) {
		while (currentNode) {
			Visit(currentNode);
			s->Push(currentNode);
			currentNode = currentNode->leftChild;
		}
		if (s->IsEmpty()) return;
		currentNode = s->Pop()->rightChild;
	}
}

void Tree::NonrecInorder() {
	Stack* s = new Stack();
	TreeNode* currentNode = root;
	while (1) {
		while (currentNode) {
			s->Push(currentNode);
			currentNode = currentNode->leftChild;
		}
		if (s->IsEmpty()) return;
		currentNode = s->Pop();
		Visit(currentNode);
		currentNode = currentNode->rightChild;
	}
}

inline void Tree::Visit(TreeNode* Node) {
	printf_s("%d\n", Node->data);
}

void Tree::recPostorder() {
	recPostorder(root);
}

void Tree::recPostorder(TreeNode* currentNode) {
	if (currentNode) {
		recPostorder(currentNode->leftChild);
		recPostorder(currentNode->rightChild);
		Visit(currentNode);
	}
}

void Tree::recPreorder() {
	recPreorder(root);
}

void Tree::recPreorder(TreeNode* currentNode) {
	if (currentNode) {
		Visit(currentNode);
		recPreorder(currentNode->leftChild);
		recPreorder(currentNode->rightChild);
	}
}

void Tree::recInorder() {
	recInorder(root);
}

void Tree::recInorder(TreeNode* currentNode) {
	if (currentNode) {
		recInorder(currentNode->leftChild);
		Visit(currentNode);
		recInorder(currentNode->rightChild);
	}
}


bool Stack::IsEmpty() const {
	return top == -1;
}

TreeNode Stack::Top() const {
	if (top == -1) {
		throw "Stack Empty";
	}
	return stack[top];
}

void Stack::Push(const TreeNode* item) {
	if (top == capacity - 1) {
		ChangeSize(stack, capacity, 2 * capacity);
	}
	stack[++top] = item;
}

TreeNode* Stack::Pop() {
	if (top == -1) {
		throw "Stack Empty";
	}
	TreeNode* temp = new TreeNode(stack[top]);
	stack[top--].~TreeNode();

	return temp;
}

void Stack::ChangeSize(TreeNode* a, const int oldSize, const int newSize) {
	TreeNode* temp = new TreeNode[newSize];
	copy(a, a + oldSize, temp);
	delete[] a;
	a = temp;
}

int main() {
	Tree* tr = new Tree();
	tr->Insert(10);
	tr->Insert(5);
	tr->Insert(7);
	tr->Insert(20);

	return 0;
}