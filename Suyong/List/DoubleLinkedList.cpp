#include <iostream>
using namespace std;


class DoubleLinkedList {

public:

	DoubleLinkedList() {

		head = tail = NULL;
		this->n = 0;
	}

	~DoubleLinkedList() {

		delete head;
		delete tail;
	}

	class Node {

	public:
		int data;
		Node* left;
		Node* right;

		Node(int data) {

			this->data = data;
			left = right = NULL;
		}
	};
	bool isEmpty();
	int size();
	void insert(int);
	int remove(int);
	int remove_head();
	int remove_tail();
	int get(int);
	void print();

private:
	Node * head;
	Node * tail;
	int n;
};

bool DoubleLinkedList::isEmpty() {

	return this->n == 0;
}

int DoubleLinkedList::size() {

	return this->n;
}

void DoubleLinkedList::insert(int data) {

	Node* node = new Node(data);
	this->n++;

	if (head == NULL) {

		head = tail = node;
		return;
	}
	tail->right = node;
	node->left = tail;
	tail = node;
}

int DoubleLinkedList::remove(int idx) {

	if (size() < idx || idx < 0)
		return -1;

	int ret = -1;
	this->n--;

	if (idx == 0) {

		ret = head->data;
		head = head->right;
	}
	else if (idx == this->n - 1) {

		ret = tail->data;
		tail = tail->left;
	}
	else {

		Node* search = head;

		while (idx-- > 0)
			search = search->right;

		ret = search->data;
		search->left->right = search->right;
		search->right->left = search->left;
		delete(search);
	}
	return ret;
}

int DoubleLinkedList::remove_head() {

	return this->n == 0 ? -1 : remove(0);
}

int DoubleLinkedList::remove_tail() {

	return this->n == 0 ? -1 : remove(this->n - 1);
}

int DoubleLinkedList::get(int idx) {

	if (size() < idx || idx < 0)
		return -1;

	Node* search = head;

	while (idx-- > 0)
		search = search->right;
	return search->data;
}

void DoubleLinkedList::print() {

	for (Node* search = head; search != NULL; search = search->right)
		cout << search->data << " ";
	cout << endl;
}

void main() {

	DoubleLinkedList* list = new DoubleLinkedList();

	for (int i = 0; i < 10; i++)
		list->insert(i);
	list->print();

	cout << list->get(5) << endl;
	cout << list->remove(5) << endl;
	cout << list->get(5) << endl;

	for (int i = 0; i < 9; i++)
		cout << list->remove_head();
	cout << endl;
	cout << list->size() << " " << list->isEmpty() << endl;
}