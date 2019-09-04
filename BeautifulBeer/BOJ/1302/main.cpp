#include <iostream>
#include <tuple>

using namespace std;

typedef struct hash_node {
	bool is_head;
	union {
		int size;
		char str[51];
	};
	int count;
	hash_node* next;
	hash_node(bool is_head) {
		this->is_head = is_head;
		if (is_head) {
			this->size = 0;
		}
		count = 0;
		this->next = nullptr;
	}
}Node;

int strlen(char* str) {
	if (str == nullptr)return -1;
	int idx = 0;
	while (str[idx++]);
	return idx;
}

int strcmp(char* str1, char* str2) {
	int result = 0;
	int idx = 0;
	while (str1[idx] && str2[idx]) {
		if (str1[idx] > str2[idx]) {
			result = 1;
			break;
		}
		else if (str1[idx] < str2[idx]) {
			result = -1;
			break;
		}
		idx++;
	}
	if (str1[idx] == str2[idx]) {
		result = 0;
	}
	else if (str1[idx]) {
		result = 1;
	}
	else if(str2[idx]){
		result = -1;
	}
	return result;
}

void strcpy(char* str1, char* str2) {
	int length = strlen(str2);
	for (int i = 0; i < length; i++) {
		str1[i] = str2[i];
	}
}


void sorting(hash_node** arr, int books) {
	hash_node* tmp;
	for (int i = 0; i < books; i++) {
		for (int j = i+1; j < books; j++) {
			if (i == j)continue;
			if (arr[i]->count < arr[j]->count) {
				// swap 하기
				tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
			}
			else if(arr[i]->count == arr[j]->count) {
				if (strcmp(arr[i]->str, arr[j]->str) == 1) {
					//swap 하기
					tmp = arr[j];
					arr[j] = arr[i];
					arr[i] = tmp;
				}
			}
		}
	}
}

class HashMap {
private:
	Node** hash_tables;
	int n, books;
public:
	int get(char* str);
	void put(char* str);
	int hash(char* str);
	std::tuple<hash_node**, int> get_items();
	HashMap(int n, int books) : n(n), books(books){
		hash_tables = new Node * [n];
		for (int i = 0; i < n; i++) {
			hash_tables[i] = nullptr;
		}
	}
};

int HashMap::hash(char* str) {
	int value = 0;
	for(int i=0; i<strlen(str); i++){
		value = (value + str[i] * 1193) % this->n;
	}
	return value;
}

void HashMap::put(char* str) {
	int v = this->hash(str);
	if (this->hash_tables[v] == nullptr) {
		hash_tables[v] = new hash_node(true);
		hash_tables[v]->next = new hash_node(false);
		strcpy(hash_tables[v]->next->str, str);
		hash_tables[v]->next->count = 1;
	}
	else {
		hash_node* tmp = hash_tables[v]->next;
		while (tmp->next != nullptr) {
			if (strcmp(tmp->str, str) == 0) {
				tmp->count++;
				return;
			}
			tmp = tmp->next;
		}
		if (strcmp(tmp->str, str) == 0) {
			tmp->count++;
		}
		else {
			tmp->next = new hash_node(false);
			strcpy(tmp->next->str, str);
			tmp->next->count = 1;
		}
	}
}

int HashMap::get(char* str) {
	int v = this->hash(str);
	if (hash_tables[v] == nullptr) return -1;
	hash_node* tmp = hash_tables[v]->next;
	while (tmp->next) {
		if (strcmp(tmp->str, str) == 0) {
			return tmp->count;
		}
		tmp = tmp->next;
	}
	if (strcmp(tmp->str, str) == 0) {
		return tmp->count;
	}
	return -1;
}

std::tuple<hash_node**, int> HashMap::get_items() {
	hash_node** result;
	int idx = 0, count = 0;
	for (int i = 0; i < this->n; i++) {
		if (hash_tables[i] == nullptr)continue;
		hash_node* tmp = hash_tables[i]->next;
		while (tmp != nullptr) {
			count++;
			tmp = tmp->next;
		}
	}
	result = new hash_node * [count];
	for (int i = 0; i < this->n; i++) {
		if (hash_tables[i] == nullptr)continue;
		hash_node * tmp = hash_tables[i]->next;
		while (tmp != nullptr) {
			result[idx++] = tmp;
			tmp = tmp->next;
		}
	}
	return std::make_tuple(result, count);
}

int main() {
	int books;
	char buf[51];
	cin >> books;
	HashMap hmap(10000, books);
	for (int i = 0; i < books; i++) {
		cin >> buf;
		hmap.put(buf);
	}
	auto items = hmap.get_items();
	for (int i = 0; i < std::get<1>(items); i++) {
		if (std::get<0>(items)[i] != nullptr) {
			cout << std::get<0>(items)[i]->str << ' ' << std::get<0>(items)[i]->count << '\n';
		}
	}
	cout << '\n';
	sorting(std::get<0>(items), std::get<1>(items));
	for (int i = 0; i < std::get<1>(items); i++) {
		if (std::get<0>(items)[i] != nullptr) {
			cout << std::get<0>(items)[i]->str << ' ' << std::get<0>(items)[i]->count << '\n';
		}
	}
	cout << std::get<0>(items)[0]->str << '\n';
}