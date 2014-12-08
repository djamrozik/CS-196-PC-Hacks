#include<stdio.h>

int doesExist( char *fileName)
	{
		err = fopen_s(&ptrFile, fileName, "err");

		if(err != 0)
			return  1;

		return  0;

	}
