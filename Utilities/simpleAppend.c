#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

void simpleAppend(char *fileName, char *textInput)
{
    FILE * fPointer;
    fPointer = fopen(fileName, "a");

    fprintf(fPointer, textInput);

    fclose(fPointer);
}
