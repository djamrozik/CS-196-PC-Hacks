#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

void simpleRead(char *fileName)
{
    FILE * fPointer;
    fPointer = fopen(fileName, "r");

    char singleLine[150];

    while(!feof(fPointer)){
        fgets(singleLine, 150, fPointer);
        puts(singleLine);
    }

    fclose(fPointer);
}
