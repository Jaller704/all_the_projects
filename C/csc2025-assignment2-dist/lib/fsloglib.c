/*
 * Replace the following string of 0s with your student number
 * 170256018
 */
#include <lib.h>    // provides _syscall and message
#include <errno.h>
#include "fsloglib.h"

int startfslog(unsigned short ops2log) {
    /* CSC2025 mod start */
    if(ops2log < FSOP_NONE || ops2log > FSOP_ALL) {
        errno = EINVAL;
        return -1;
    }
    message m;

    m.m1_i1 = ops2log;

    int r = _syscall(VFS_PROC_NR, STARTFSLOG, &m);
    
    if(r > -1){
        return 0;
    }else {
        errno = EINVAL;
        return -1;
    }
    /* CSC2025 mod end */
    
}

int stopfslog(unsigned short ops2stoplog) {
    /* CSC2025 mod start */
    if(ops2stoplog < FSOP_NONE || ops2stoplog > FSOP_ALL) {
        errno = EINVAL;
        return -1;
    }
    message m;

    m.m1_i1 = ops2stoplog;

    int r = _syscall(VFS_PROC_NR, STOPFSLOG, &m);
    
    if(r > -1){
        return 0;
    }else {
        errno = EINVAL;
        return -1;
    }
    /* CSC2025 mod end */
}

int getfsloginf(struct fsloginf *fsloginf) {
    /* CSC2025 mod start */
    if(!fsloginf){
        errno = EINVAL;
        return -1;
    }

    message m;
    
    m.m1_p1 = (char *) fsloginf;

    int r = _syscall(VFS_PROC_NR, GETFSLOGINF, &m);

    if(r>-1){
        return 0;
    } else {
        errno = EINVAL;
        return -1;
    }
    /* CSC2025 mod end */
}

int getfslog(struct fsloginf *fsloginf, struct fslogrec fslog[]) {
    /* CSC2025 mod start */
    if(!fsloginf || !fslog){
        errno = EINVAL;
        return -1;
    }

    message m;
    
    m.m1_p1 = (char *) fsloginf;
    m.m1_p2 = (char *) fslog;

    int r = _syscall(VFS_PROC_NR, GETFSLOG, &m);
    
    if(r>-1){
        return 0;
    } else {
        errno = EINVAL;
        return -1;
    }
    /* CSC2025 mod end */
}
