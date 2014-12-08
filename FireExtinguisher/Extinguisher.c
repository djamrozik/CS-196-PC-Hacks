#include<stdio.h>
#include <windows.h>

int main()
{
//Kevin Chang
   FILE *fp;
    char str[500];
    char address[] = " \"C:\\Users\\USER\\Documents\\GitHub\\CS-196-PC-Hacks\\FireExtinguisher\\"; //change this to the customizable address
    strcpy(str, "del ");
    strcat(str, address);
    strcat(str,"Extinguisher.exe\"");
    strcat(str, "\r\ndel ");
    strcat(str, address);
    strcat(str,"text.bat\"");



    fp = fopen( "text.txt" , "ab+" );
    fputs(str, fp);
    fclose(fp);

//change txt to bat
    int ret;
   char oldname[] = "text.txt";
   char newname[] = "text.bat";

   ret = rename(oldname, newname);
    Sleep(1000);
    //system("text.bat");
   return 0;
}
