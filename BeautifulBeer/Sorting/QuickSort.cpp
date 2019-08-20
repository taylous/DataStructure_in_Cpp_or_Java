#include <cstdio>

inline void swap(int& a, int& b) {
	int tmp = a;
	a = b;
	b = tmp;
}

void print_arr(int(&arr)[10], int size) {
	for (int i = 0; i < size; i++) {
		if (i == size - 1) {
			printf("%d", arr[i]);
		}
		else {
			printf("%d, ", arr[i]);
		}
	}
	printf("\n");
}

void lomuto(int (&arr)[10], int left, int right) {
	if (left < right) {
		int pivot = arr[right];
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr[i], arr[j]);
			}
		}
		i++;
		swap(arr[i], arr[right]);
		lomuto(arr, i + 1, right);
		lomuto(arr, left, i - 1);
	}
}

int main() {
	int arr[] = {6,3,45,345,23,12,32343,44,6,305};
	print_arr(arr, 10);
	lomuto(arr, 0, 9);
	print_arr(arr, 10);
}