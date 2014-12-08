#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

int main(void)
{
    /*the dankest of comments is right here*/
    FILE * fPointer; /*creates a new file pointer called fPointer*/
    fPointer = fopen("test.txt", "w");

    fputs("The purpose of this file is to show that we can write to notepad within C", fPointer);
    fputs("\nDoing this is super easy and only requires a few lines of code in C", fPointer);

    fclose(fPointer);
    return 0;

}
