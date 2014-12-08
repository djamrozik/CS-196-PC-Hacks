#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

int main(void)
{
    //it's also really important to know how to append when we have to add stuff to the file

    /*the dankest of comments is right here*/

    /*creates a new file pointer called fPointer*/
    FILE * fPointer;

    /*there must be a file called test.txt*/
    fPointer = fopen("test.txt", "a");

    //use the fprintf function to append the file
    fprintf(fPointer, "\nThis is some super awesome content that is being appended to the file.");

    fclose(fPointer);
    return 0;

}
