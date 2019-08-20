#include <iostream>
using namespace std;

class DoubleList;

class DoubleListNode
{
private:
    int data;
    DoubleListNode *left, *right;
public:
    DoubleListNode(int element = 0, DoubleListNode *lNode = NULL, DoubleListNode *rNode = NULL) :data(element), left(lNode), right(rNode)
    {
    }
    friend class DoubleList;
};

class DoubleList
{
private:
    DoubleListNode *first;

public:
    DoubleList() {
        first = new DoubleListNode;
        first->left = first;
        first->right = first;
    }
    ~DoubleList() {
        DoubleListNode *temp = first;
        DoubleListNode *tempNext = first->right;

        while(1) {
            if(tempNext->data != 0) {
                delete temp;
                temp = tempNext;
                tempNext = tempNext->right;
            } else {
                delete temp;
                break;
            }
        }
    }

    DoubleListNode* get_first() {
        return first;
    }

    void insert(DoubleListNode *x, DoubleListNode *y=NULL) {
        if(y == NULL) {
            x->left = first->left;
            x->right = first;
            first->left->right = x;
            first->left = x;
        } else {
            x->left = y;
            x->right = y->right;
            x->right->left = y;
            x->right = y;
        }
    }
    void delete1(DoubleListNode *x) {
        if(x == first) {
            cout << "do not delete!" << endl;
        } else {
            x->left->right = x->right;
            x->right->left = x->left;
            delete x;
        }
    }
};