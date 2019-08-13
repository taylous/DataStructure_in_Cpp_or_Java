#include <iostream>
using namespace std;

//////////////////////////////////////////////////////////////////////////////////
// CLASSES
//////////////////////////////////////////////////////////////////////////////////
class Node {

	friend class BinarySearchTree;

private:
	char ch;
	Node* leftChild;
	Node* rightChild;

public:
	Node(char ch_ = '\u0000') :ch(ch_), leftChild(nullptr), rightChild(nullptr) {}
	~Node() {}
};

class BinarySearchTree {
	
private:

	Node* root;
	int n;

	bool flag;

	void insert(Node*& node, char ch_) {

		if (node == nullptr) {

			node = new Node(ch_);
			return;
		}

		if (node->ch < ch_)
			insert(node->rightChild, ch_);
		else
			insert(node->leftChild, ch_);
	}

	void contains(Node* node, char ch_) {

		if (node == nullptr || this->flag == true)
			return;
		if (node->ch == ch_) {

			this->flag = true;
			return;
		}
			
		if (node->ch < ch_)
			contains(node->rightChild, ch_);
		else
			contains(node->leftChild, ch_);
	}

	void inorder(Node* node) {

		if (node == nullptr)
			return;

		inorder(node->leftChild);
		cout << node->ch << " ";
		inorder(node->rightChild);
	}

public:
	BinarySearchTree() :root(nullptr), n(0) {}
	~BinarySearchTree() {}

	void insert(char);
	bool contains(char);
	char remove(char);

	void inorder();
};

//////////////////////////////////////////////////////////////////////////////////
// FUNCTIONS
//////////////////////////////////////////////////////////////////////////////////

void BinarySearchTree::insert(char ch_) {

	cout << "INSERT> " << ch_ << endl;

	this->n++;
	this->insert(this->root, ch_);
}

bool BinarySearchTree::contains(char ch_) {

	this->flag = false;
	this->contains(this->root, ch_);
	return this->flag;
}

void BinarySearchTree::inorder() {

	this->inorder(this->root);
}

void main() {

	BinarySearchTree* bst = new BinarySearchTree();

	bst->insert('K');
	bst->insert('I');
	bst->insert('T');
	bst->insert('A');
	bst->insert('J');
	bst->insert('S');
	bst->insert('X');

	bst->inorder();
}
