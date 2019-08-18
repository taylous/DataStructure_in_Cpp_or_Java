#include <cstdio>
#include <cstring>

constexpr int MAX_INT = 987654321;

class Heap {
private:
	int* arr;
	int heapSize;
	int capacity;

public:
	Heap(int size=10) : heapSize(0), capacity(size) {
		arr = new int[capacity + 2];
	}
	~Heap() {
		delete[] arr;
	}
	void push(int data);
	int pop();
	bool isEmpty();
	void doublingSize();
};

void Heap::doublingSize() {
	int* result = new int[(capacity + 1) * 2];
	memset(result, MAX_INT, sizeof(result));
	for (int i = 1; i <= capacity; i++) {
		result[i] = arr[i];
	}
	capacity *= 2;
	delete[] arr;
	arr = result;
}

void Heap::push(int data) {
	if (heapSize == capacity) {
		doublingSize();
	}
	int idx = ++heapSize;
	while (idx != 1 && data < arr[idx / 2]) {
		arr[idx] = arr[idx / 2];
		idx = idx / 2;
	}
	arr[idx] = data;
}

int Heap::pop() {
	if (isEmpty())return MAX_INT;
	int result = arr[1];
	int data = arr[heapSize];
	arr[heapSize] = MAX_INT;
	heapSize--;
	int idx = 1;
	while (data > arr[idx * 2] || data > arr[idx * 2 + 1]) {
		if (arr[idx * 2] < arr[idx * 2 + 1]) {
			if (idx * 2 <= heapSize) {
				arr[idx] = arr[idx * 2];
				idx *= 2;
			}
			else {
				break;
			}
		}
		else {
			if (idx * 2 + 1 <= heapSize) {
				arr[idx] = arr[idx * 2 + 1];
				idx = idx * 2 + 1;
			}
			else {
				break;
			}
		} 
	}
	arr[idx] = data;
	return result;
}

bool Heap::isEmpty() {
	return heapSize == 0;
}

int main() {
	Heap heap = Heap();
	for (int i = 20; i > 0; i--) {
		heap.push(i);
	}
	while (!heap.isEmpty()) {
		printf("%d ", heap.pop());
	}
	printf("\n");
}
