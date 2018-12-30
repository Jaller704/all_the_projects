#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include "../fsloglib.h"
#include "fslog_testutil.h" // provides print__fsloginf

int main(int argc, char **argv) {
    struct fsloginf fsloginf;
    
    int r = getfsloginf(&fsloginf);

    if (!r) {
        print_fsloginf(&fsloginf);
    } else {
        perror("getfsloginf error: ");
    }

    return r;
}
