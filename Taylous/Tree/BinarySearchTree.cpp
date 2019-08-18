#include <iostream>
using namespace std;

//////////////////////////////////////////////////////////////////////////////////
// CLASSES
//////////////////////////////////////////////////////////////////////////////////
class Node {

	friend class BinarySearchTree;

private:
	int data;
	Node* leftChild;
	Node* rightChild;

public:
	Node(char data_ = 0) :data(data_), leftChild(nullptr), rightChild(nullptr) {}
	~Node() {}
};

class BinarySearchTree {

private:

	Node* root;
	int n;

	bool flag;

	void insert(Node*& node, int data_) {

		if (node == nullptr) {

			node = new Node(data_);
			return;
		}

		if (node->data < data_)
			insert(node->rightChild, data_);
		else
			insert(node->leftChild, data_);
	}

	void contains(Node* node, int data_) {

		if (node == nullptr || this->flag == true)
			return;
		if (node->data == data_) {

			this->flag = true;
			return;
		}

		if (node->data < data_)
			contains(node->rightChild, data_);
		else
			contains(node->leftChild, data_);
	}

	Node* remove(Node* node, int data) {

		if (node->data < data) {

			node->rightChild = remove(node->rightChild, data);
		}
		else if (node->data > data) {

			node->leftChild = remove(node->leftChild, data);
		}
		else {

			if (!node->leftChild && !node->rightChild) {
				delete(node);
			}
			else if (!node->rightChild) {

				return node->leftChild;
			}
			else if (!node->leftChild) {

				return node->rightChild;
			}
			else {

				Node* parent = node->rightChild;
				Node* successor = node->rightChild;

				while (successor->leftChild != nullptr) {

					parent = successor;
					successor = successor->leftChild;
				}
				if (parent == successor) {

					node->data = successor->data;
					node->rightChild = nullptr;
				}
				else {
					parent->leftChild = successor->rightChild;
					node->data = successor->data;
				}
				delete(successor);
			}
		}
		return node;
	}

	void inorder(Node* node) {

		if (node == nullptr)
			return;

		inorder(node->leftChild);
		cout << node->data << " ";
		inorder(node->rightChild);
	}

	Node* predecessor(Node* node) {

		Node* search = node->leftChild;
		Node* ret = nullptr;

		while (search->rightChild->rightChild != nullptr)
			search = search->rightChild;

		ret = search->rightChild;
		search->rightChild = nullptr;
		return ret;
	}

	Node* successor(Node* node) {

		Node* search = node->rightChild;
		Node* ret = nullptr;

		while (search->leftChild->leftChild != nullptr)
			search = search->leftChild;

		ret = search->leftChild;
		search->leftChild = nullptr;
		return ret;
	}

public:
	BinarySearchTree() :root(nullptr), n(0), flag(false) {}
	~BinarySearchTree() {}

	void insert(int);
	bool contains(int);
	void remove(int);

	void inorder();
};

//////////////////////////////////////////////////////////////////////////////////
// FUNCTIONS
//////////////////////////////////////////////////////////////////////////////////

void BinarySearchTree::insert(int data_) {

	cout << "INSERT> " << data_ << endl;

	this->n++;
	this->insert(this->root, data_);
}

bool BinarySearchTree::contains(int data_) {

	this->flag = false;
	this->contains(this->root, data_);
	return this->flag;
}

void BinarySearchTree::inorder() {

	this->inorder(this->root);
}

void BinarySearchTree::remove(int data_) {

	this->remove(this->root, data_);
}

void main() {

	BinarySearchTree* bst = new BinarySearchTree();

	bst->insert(44);
	bst->insert(17);
	bst->insert(88);
	bst->insert(32);
	bst->insert(65);
	bst->insert(97);
	bst->insert(28);
	bst->insert(54);
	bst->insert(82);
	bst->insert(29);
	bst->insert(76);
	bst->insert(80);
	bst->insert(78);

	bst->inorder();

	bst->remove(44);

	cout << "AFTER DELETE" << endl;
	bst->inorder();
}
