/*
 * Replace the following string of 0s with your student number
 * 170256018
 */
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <stdio.h>
#include "fs.h"             // for glo.h:  mp, call_nr, who_p etc.
#include "fproc.h"
#include "fslog.h"

#define INVALID_ARG -22     /* gets converted to EINVAL by syscall */

#define EVENTS_FD_NR 3                  /* the file descriptor number of 
                                         * the events file to be ignored */
static char* EVENTS_PATH = "events";    /* the events file path to be ignored */

/* fsloginf is the log meta-information struct, initialised to:
 * start: 0, len: 0, ops2log: FSOP_NONE
 * see unistd.h for struct fsloginf definition
 */
static struct fsloginf fsloginf = { 0, 0, FSOP_NONE };

/* fslog is the array used for the in-memory log - see unistd.h for struct 
 * fslogrec definition
 */
static struct fslogrec fslog[NR_FSLOGREC];

/*
 * Description:
 * Start or restart logging of filesystem operations specified by a combination
 * of opcodes passed as a message field.
 * This system call will reset the start field of the filesystem log 
 * meta-information to 0 and the length field to 0.
 * An ops to log parameter is passed as a message field. This is ORed with the 
 * meta-information ops2log field and if the result is > FSOP_NONE, logging will
 * be on for the specified filesystem operation(s). If the resulting value of 
 * the ops2log field is FSOP_NONE then no operations will be logged. If the 
 * resulting value is FSOP_ALL then all FSOP operations and errors will be
 * logged. FSOP values are defined in unistd.h.
 *
 * Expected incoming message fields:
 * m_in.m1_i1 - an ORed combination of FSOP values that must be in the range
 *      FSOP_NONE to FSOP_ALL (inclusive). If m_in.m1_i1 is FSOP_NONE then 
 *      the call has no effect on operation logging. If m_in.m1_i1 is
 *      FSOP_ALL then logging will be on for all operations and errors.
 *
 * Return:
 * OK if the call succeeds
 * INVALID_ARG if m_in.m1_i1 is less than FSOP_NONE or greater than FSOP_ALL
 */
int do_startfslog() {
    /* CSC2025 mod start */
    int i = m_in.m1_i1;

    if(i < FSOP_NONE || i > FSOP_ALL){
        return INVALID_ARG;
    }

    fsloginf.start = 0;
    fsloginf.len = 0;

    if((i | fsloginf.ops2log) > FSOP_NONE) {
        fsloginf.ops2log = i;
    } else if((i | fsloginf.ops2log) == FSOP_ALL)  {
        fsloginf.ops2log = FSOP_ALL;
    } else if((i | fsloginf.ops2log) == FSOP_NONE){
        fsloginf.ops2log = FSOP_NONE;
    }
    /* CSC2025 mod end */
    return OK;
}

/*
 * Description:
 * Stop logging of filesystem operations specified by a combination of opcodes
 * passed as a message field.
 * The ops to stop logging are passed as a message field. Its complement is
 * ANDed with the meta-information ops2log field to stop logging for the 
 * specified filesystem operations. If the resulting value of the ops2log field
 * is FSOP_NONE logging will stop for all operations. If the 
 * resulting value is FSOP_ALL then logging will continue for all operations and
 * errors will be logged. FSOP values are defined in unistd.h.
 * This system call has no effect on the start or length fields of the 
 * filesystem log meta-information
 *
 * Expected incoming message fields:
 * m_in.m1_i1 - an ORed combination of FSOP values that must be in the range
 *      FSOP_NONE to FSOP_ALL (inclusive). If m_in.m1_i1 is FSOP_NONE then 
 *      the call has no effect on operation logging. If m_in.m1_i1 is
 *      FSOP_ALL then all logging is stopped.
 *
 * Return:
 * OK if the call succeeds
 * INVALID_ARG if m_in.m1_i1 is less than FSOP_NONE or greater than FSOP_ALL
 */
int do_stopfslog() {
    /* CSC2025 mod start */
    int i = m_in.m1_i1;

    if(i < FSOP_NONE || i > FSOP_ALL){
        return INVALID_ARG;
    }

    fsloginf.ops2log = (~i & fsloginf.ops2log);

    /* CSC2025 mod end */
    return OK;
}

/*
 * Description:
 * Get the log meta-information - a fsloginf struct with fields for the 
 * start position of the log, the length of the log (i.e. the number of valid
 * entries in the log array) and the value of the ops2log selector. The 
 * fsloginf struct is defined in unistd.h. The struct is returned by copying to 
 * an address provided as a message field.
 *
 * Expected incoming message fields:
 * m_in.m1_p1 - a pointer field that is the address to copy to of a fsloginf 
 *      struct in the address space of the calling process.
 *
 * Return:
 * OK if the call succeeds
 * In the case of failure:
 *      INVALID_ARG if m_in.m1_p1 is NULL
 *      An error status related to copying between process address spaces
 *      using sysvircopy (one of EDOM, EFAULT, EPERM, EINVAL) 
 *      see:
 *      https://wiki.minix3.org/doku.php?id=developersguide:kernelapi#sys_vircopy
 */
int do_getfsloginf() {
    /* CSC2025 mod start */
    vir_bytes tar = (vir_bytes) m_in.m1_p1;
    if(!tar){
        return INVALID_ARG;
    }
    
    int r = OK;

    r = sys_vircopy(SELF, (vir_bytes) &fsloginf, who_e, tar, sizeof(fsloginf));
    
    if(r != OK){
        return r;
    }
    /* CSC2025 mod end */
    return OK;
}

/*
 * Description:
 * Get the log meta-information (as provided by do_getfsloginf) and the
 * in-memory log - the circular buffer of filesystem operation entries.
 * The information obtained is a fsloginf struct (as in do_getfsloginf) and 
 * an array of fslogrec structs. Both struct types are defined in unistd.h.
 * The information is returned by copying to addresses provided as message
 * fields.
 *
 * Expected incoming message fields:
 * m_in.m1_p1 - a pointer field that is the address to copy to of a fsloginf 
 *      struct in the address space of the calling process.
 * m_in.m1_p2 - a pointer field that is the address to copy to of an array of 
 *      fslogrec structs in the address space of the calling process.
 *
 * Return:
 * OK if the call succeeds
 * In the case of failure:
 *      INVALID_ARG if either m_in.m1_p1 or m_in.m1_p2 is NULL:
 *      An error status related to copying between process address spaces
 *      using sysvircopy (one of EDOM, EFAULT, EPERM, EINVAL) 
 *      see:
 *      https://wiki.minix3.org/doku.php?id=developersguide:kernelapi#sys_vircopy
 */
int do_getfslog() {
    /* CSC2025 mod start */
    vir_bytes fsloginf_tar = (vir_bytes) m_in.m1_p1;
    vir_bytes fslogrec_tar = (vir_bytes) m_in.m1_p2;

    if(!fsloginf_tar || !fslogrec_tar){
        return INVALID_ARG;
    }

    int r = OK;
    
    int s = OK;

    r = sys_vircopy(SELF, (vir_bytes) &fsloginf, who_e, fsloginf_tar, sizeof(fsloginf));
    s = sys_vircopy(SELF, (vir_bytes) fslog, who_e, fslogrec_tar, sizeof(struct fslogrec) * NR_FSLOGREC);
    
    if(r != OK){
        return r;
    } else if(s != OK) {
        return s;
    }
    /* CSC2025 mod end */
    return OK;
}

/* 
 * logfserr: implemented, do NOT change
 * see fslog.h for specification of this logging function 
 */
void logfserr(int opcode, int result, char *path) {
    if (fsloginf.ops2log & FSOP_ERR)
        logfsop(opcode, result, path, UNKNOWN_FD_NR, UNKNOWN_V_MODE,
            UNKNOWN_V_UID, UNKNOWN_V_GID, UNKNOWN_V_SIZE);
}

/* 
 * logfserr_nopath: implemented, do NOT change
 * see fslog.h for specification of this logging function
 */
void logfserr_nopath(int opcode, int result) {
    logfserr(opcode, result, NULL);
}

/* 
 * logfsop: implemented, do NOT change
 * see fslog.h for specification of this logging function 
 */
void logfsop(int opcode, int result, char *path, int fd_nr, mode_t v_mode, 
    uid_t v_uid, gid_t v_gid, off_t v_size) {
    if (path && fd_nr == EVENTS_FD_NR 
            && strncmp(EVENTS_PATH, path, PATH_MAX) == 0)
        return;     // system events operation that is ignored
        
    if (fsloginf.ops2log & opcode) {
        int next = (fsloginf.start + fsloginf.len) % NR_FSLOGREC;
        
        fslog[next].timestamp = time(NULL);
        fslog[next].opcode = opcode;
        fslog[next].result = result;
        
        if (path) {
            (void) strncpy(fslog[next].path, path, PATH_MAX - 1);
            fslog[next].path[PATH_MAX - 1] = '\0';
        } else {
            fslog[next].path[0] = '\0';
        }
        
        fslog[next].fd_nr = fd_nr;
        fslog[next].v_mode = v_mode;
        fslog[next].v_uid = v_uid;
        fslog[next].v_gid = v_gid;
        fslog[next].v_size = v_size;

        if (fsloginf.len == NR_FSLOGREC)
            fsloginf.start = (fsloginf.start + 1) % NR_FSLOGREC;
        else 
            fsloginf.len++;        
    }
}

/* 
 * logfsop_nopath: implemented, do NOT change
 * see fslog.h for specification of this logging function
 */
void logfsop_nopath(int opcode, int result, int fd_nr, mode_t v_mode,
    uid_t v_uid, gid_t v_gid, off_t v_size) {
    logfsop(opcode, result, NULL, fd_nr, v_mode, v_uid, v_gid, v_size);
}

