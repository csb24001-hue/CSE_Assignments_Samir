#include <stdio.h>
#include <time.h>

void constantTime() { int x = 100; x++; }

void linearTime(int n) {
    long long sum = 0;
    for(int i=0;i<n;i++) sum += i;
}

void quadraticTime(int n) {
    long long sum = 0;
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            sum += i + j;
}

int main() {
    int n;
    printf("Enter input size: ");
    scanf("%d",&n);
    clock_t start,end;

    start=clock(); constantTime(); end=clock();
    printf("O(1): %lf sec\n",(double)(end-start)/CLOCKS_PER_SEC);

    start=clock(); linearTime(n); end=clock();
    printf("O(n): %lf sec\n",(double)(end-start)/CLOCKS_PER_SEC);

    start=clock(); quadraticTime(n); end=clock();
    printf("O(n^2): %lf sec\n",(double)(end-start)/CLOCKS_PER_SEC);
    return 0;
}