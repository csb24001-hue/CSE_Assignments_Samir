#include <stdio.h>
#include <stdlib.h>

void constantSpace() { int x = 10; printf("%d\n", x); }

void linearSpace(int n) {
    int *arr = (int*)malloc(n*sizeof(int));
    free(arr);
}

void quadraticSpace(int n) {
    int **matrix = (int**)malloc(n*sizeof(int*));
    for(int i=0;i<n;i++) matrix[i]=(int*)malloc(n*sizeof(int));
    for(int i=0;i<n;i++) free(matrix[i]);
    free(matrix);
}

int main() {
    int n;
    scanf("%d",&n);
    constantSpace();
    linearSpace(n);
    quadraticSpace(n);
    return 0;
}