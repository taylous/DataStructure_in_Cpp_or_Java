#include <cstdio>
#include <cstring>


void print_arr(int* arr, int n) {
	for (int i = 0; i < n; i++) {
		printf("%d ", arr[i]);
	}
	printf("\n");
}

int pow(int a, int b) {
	if (b == 0)return 1;
	for (int i = 1; i < b; i++) {
		a *= a;
	}
	return a;
}

int digit(int num, int d, int r) {
	printf("digit num : %d\n", num);
	int* number = new int[d+1];
	memset(number, 0, sizeof(number));
	int idx = d;
	while (num != 0) {
		number[idx--] = num % r ;
		num = num / r;
	}
	print_arr(number, d + 1);
	idx = number[d];
	delete[] number;
	return idx;
}

// r : radix , n : #element, d : maximum 10^d
int RadixSort(int* arr, int* link, const int d, const int r, const int n) {
	int* e = new int[r];
	int* f = new int[r];
	int first = 1, i, j;
	for (i = 0; i < n; i++) link[i] = i + 1;
	link[n] = 0;
	for (i = d - 1; i >= 0; i--) {
		print_arr(link, 11);
		print_arr(arr, 10);
		for (int l = 0; l < r; l++) {
			e[l] = f[l] = 0;
		}
		print_arr(f, r);
		for (int current = first; current; current = link[current]) {
			if (current == 0)break;
			int k = digit(arr[current], i, r);
			printf("1111\n");
			if (f[k] == 0) {
				printf("1111\n");
				f[k] = e[k] = current;
			}
			else link[e[k]] = current;
			printf("1111\n");
			e[k] = current;
			printf("%d %d %d %d\n", k, f[k], e[k], link[e[k]]);
		}
		for (j = 0; !f[j]; j++);
		first = f[j];
		int last = e[j];
		for (int k = j + 1; k < r; k++) {
			if (f[k]) {
				link[last] = f[k];
				last = e[k];
			}
		}
		link[last] = 0;
	}
	return first;
}


int main() {
	int* arr = new int[10]{3,123412,34,2134,12,354,1234,4,1234,623124};
	int* link = new int[11];
	print_arr(arr, 10);
	RadixSort(arr, link, 6, 10, 10);
	
}