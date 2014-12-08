#include <stdio.h>
#include <string.h>

void simpleSendMail(char *to, char*body)

        //THIS NEEDS TO HAVE SENDMAIL ON A UNIX MACHINE

        char cmd[100];  // to hold the command.
        char to[] = to; // email id of the recepient.
        char body[] = body;    // email body.
        char tempFile[100];     // name of tempfile.

        strcpy(tempFile,tempnam("/tmp","sendmail")); // generate temp file name.

        FILE *fp = fopen(tempFile,"w"); // open it for writing.
        fprintf(fp,"%s\n",body);        // write body to it.
        fclose(fp);             // close it.

        sprintf(cmd,"sendmail %s < %s",to,tempFile); // prepare command.
        system(cmd);     // execute it.

        return 0;
}
