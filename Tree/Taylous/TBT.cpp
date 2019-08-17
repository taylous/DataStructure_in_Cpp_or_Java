#include <iostream>
using namespace std;

class Node {

	friend class TBT;

private:

	int data;
	Node* left;
	Node* right;
	bool leftThread;
	bool rightThread;

public:

	Node(int data_ = 0) :data(data_), left(nullptr), right(nullptr), leftThread(true), rightThread(true) {}
	~Node() {}
};

class TBT {

private:

	Node* root;

	Node* inorderSuccessor(Node*);
	void inorder(Node*);

public:

	TBT() :root(nullptr) {}
	~TBT() {}

	void insert(int);
	void inorder();
};

void TBT::insert(int data_) {

	Node* newNode = new Node(data_);

	//if (this->root->data == 987654321) {

	//	root->rightThread = false;
	//	root->right = root;

	//	root->leftThread = false;
	//	root->left = newNode;
	//	return;
	//}
	Node* parent = nullptr;
	Node* search = this->root;

	// Thread == true 인 경우는 leaf node 일 때만
	while (search != nullptr) {

		parent = search;

		if (search->data < data_) {

			if (!search->rightThread)
				search = search->right;
			else
				break;
		}
		else {

			if (!search->leftThread)
				search = search->left;
			else
				break;
		}
	}

	if (parent == nullptr) {

		this->root = newNode;
	}
	else if (parent->data < data_) {

		newNode->left = parent;
		newNode->right = parent->right;

		parent->rightThread = false;
		parent->right = newNode;
	}
	else {

		newNode->right = parent;
		newNode->left = parent->left;

		parent->leftThread = false;
		parent->left = newNode;
	}
}

void TBT::inorder() {

	this->inorder(this->root->left);
}

void TBT::inorder(Node* node) {

	if (node == nullptr)
		return;

	Node* search = node;
	while (!search->leftThread)
		search = search->left;

	while (search != nullptr) {

		cout << search->data << " ";
		search = this->inorderSuccessor(search);
	}
	cout << endl;
}

Node* TBT::inorderSuccessor(Node* node) {

	if (node->rightThread)
		return node->right;

	node = node->right;
	while (!node->leftThread)
		node = node->left;
	return node;
}

int main() {

	TBT* tbt = new TBT();

	tbt->insert(44);
	tbt->insert(17);
	tbt->insert(88);
	tbt->insert(32);
	tbt->insert(65);
	tbt->insert(97);
	tbt->insert(28);
	tbt->insert(54);
	tbt->insert(82);
	tbt->insert(29);
	tbt->insert(76);
	tbt->insert(80);
	tbt->insert(78);

	tbt->inorder();
	return 0;
}