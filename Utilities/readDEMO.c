#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

int main(void)
{
    /*the dankest of comments is right here*/

    /*creates a new file pointer called fPointer*/
    FILE * fPointer;

    /*this will open test.txt and print it to the console*/
    /*there must be a file called test.txt*/
    fPointer = fopen("test.txt", "r");
    char singleLine[150];

    while(!feof(fPointer)){
        fgets(singleLine, 150, fPointer);
        puts(singleLine);
    }

    fclose(fPointer);
    return 0;

}
