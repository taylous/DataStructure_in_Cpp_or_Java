#include <iostream>
using namespace std;

class Heap {

private:

	int* table;
	int n;

	void sink(int);
	void swim(int);
	void exch(int, int);
	bool less(int, int);

public:
	Heap(int size = 100) {

		this->table = new int[size + 1];
		this->n = 0;
	}

	void insert(int);
	int remove();
	bool isEmpty();
};

bool Heap::isEmpty() {

	return this->n == 0;
}

void Heap::exch(int a, int b) {

	int t = this->table[a];
	this->table[a] = this->table[b];
	this->table[b] = t;
}

// 내림차순으로 설정
// 부등호를 바꾸거나 return 값을 바꾸면 오름차순
bool Heap::less(int a, int b) {

	return this->table[a] < this->table[b] ? 1 : 0;
}

void Heap::sink(int k) {

	while (k * 2 <= this->n) {

		int j = k * 2;
		while (j < this->n && this->less(j, j + 1))
			j++;

		if (!less(k, j))
			break;
		exch(k, j);
		k = j;
	}
}

void Heap::swim(int k) {

	while (k > 1 && this->less(k / 2, k)) {

		exch(k / 2, k);
		k /= 2;
	}
}

void Heap::insert(int data) {

	this->table[++n] = data;
	swim(n);
}

int Heap::remove() {

	int ret = this->table[1];
	exch(1, this->n--);
	this->table[n + 1] = 0;
	sink(1);
	return ret;
}

void main() {

	Heap* heap = new Heap(10);

	for (int i = 1; i <= 10; i++)
		heap->insert(i);
	
	while (!heap->isEmpty())
		cout << heap->remove() << " ";
	cout << endl;
}