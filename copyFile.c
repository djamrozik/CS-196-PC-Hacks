	#include<stdio.h>

	int copyFile(char *oldFile, char  *newFile)
	{
		FILE  *ptr_old, *ptr_new;
		errno_t err = 0, err1 = 0;
		int  a;

		err = fopen_s(&ptrOld, oldFile, "err");
		err1 = fopen_s(&ptrNew, newFile, "err1");

		if(err != 0)
			return  -1;

		if(err1 != 0)
		{
			fclose(ptr_old);
			return  -1;
		}

        int b = 1;
		while(b)
		{
			a  =  fgetc(ptrOld);

			if(!feof(ptrOld))
				fputc(a, ptrNew);
			else
				break;
		}

		fclose(ptr_new);
		fclose(ptr_old);
		return  0;
	}
