
#include <stdio.h>
#include <time.h> // For nanosleep

int main() {
    FILE *fp;
    int i;
    struct timespec ts;

    // Time specification for 1 millisecond
    ts.tv_sec = 0; // 0 seconds
    ts.tv_nsec = 1000000L; // 1000 microseconds in nanoseconds

    // Open file for writing
    fp = fopen("output.txt", "w");
    if (fp == NULL) {
        perror("Error opening file");
        return -1;
    }

    // Write 100 lines to the file with a 1ms delay between each
    for (i = 1; i <= 100; i++) {
        fprintf(fp, "Line %d\n", i);
        fflush(fp); // Flush the output buffer after each write
        nanosleep(&ts, NULL); // Sleep for 1 millisecond
    }

    // Close the file
    fclose(fp);

    return 0;
}