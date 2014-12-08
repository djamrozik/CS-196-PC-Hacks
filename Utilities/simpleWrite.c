#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

void simpleWrite(char *fileName, char *textInput)
{
    FILE * fPointer;
    fPointer = fopen(fileName, "w");

    fputs(textInput, fPointer);

    fclose(fPointer);
}
