//#include <cstdio>
//
//class ThreadBinaryTree;
//
//typedef struct ThreadBinaryTreeNode {
//friend class ThreadBinaryTree;
//private:
//	int data;
//	bool leftThread;
//	bool rightThread;
//	TBinaryTreeNode* leftChild;
//	TBinaryTreeNode* rightChild;
//
//public:
//	ThreadBinaryTreeNode(int data) : data(data), leftThread(false),
//		rightThread(false), leftChild(this), rightChild(this) {};
//	~ThreadBinaryTreeNode() {
//		delete leftChild;
//		delete rightChild;
//	};
//} TBinaryTreeNode;
//
//class ThreadBinaryTree {
//private:
//	TBinaryTreeNode* root;
//	TBinaryTreeNode* currentNode;
//
//public:
//	ThreadBinaryTree() {
//		currentNode = root = new TBinaryTreeNode(-1);
//	};
//	int Next();
//	TBinaryTreeNode* InorderSucc(TBinaryTreeNode* node);
//	void InsertRight(TBinaryTreeNode* s, TBinaryTreeNode* r);
//};
//
//int ThreadBinaryTree::Next() {
//	TBinaryTreeNode* temp = currentNode->rightChild;
//	if (temp != nullptr) {
//		while (!temp->leftThread) temp = temp->leftChild;
//	}
//	currentNode = temp;
//	return currentNode->data;
//}
//
//void ThreadBinaryTree::InsertRight(TBinaryTreeNode* s, TBinaryTreeNode* r) {
//	r->rightChild = s->rightChild;
//	r->rightThread = s->rightThread;
//	r->leftChild = s;
//	r->leftThread = true;
//	s->rightChild = r;
//	s->rightThread = false;
//	if (!r->rightThread) {
//		TBinaryTreeNode* temp = InorderSucc(r);
//		temp->leftChild = r;
//	}
//}
//
//TBinaryTreeNode* ThreadBinaryTree::InorderSucc(TBinaryTreeNode* node) {
//	TBinaryTreeNode* temp = node->rightChild;
//	if (temp != nullptr) {
//		while (!temp->leftThread) temp = temp->leftChild;
//	}
//	node = temp;
//	return node;
//}
//
//int main() {
//	int data[] = { 4, 2, 6, 7, 1, 82, 49, 382, 12 };
//	int length = 9;
//
//	ThreadBinaryTree* tree = new ThreadBinaryTree();
//	for (int i = 0; i < length; i++) {
//		tree->InsertRight()
//	}
//}