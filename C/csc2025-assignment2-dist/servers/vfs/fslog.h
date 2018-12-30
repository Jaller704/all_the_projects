#ifndef __VFS_FSLOG_H__
#define __VFS_FSLOG_H__
#include <sys/types.h>

#define UNKNOWN_FD_NR   -1  /* unknown file descriptor number */
#define UNKNOWN_V_MODE  0   /* unknown file open mode */
#define UNKNOWN_V_UID   -1  /* unknown file owner uid */
#define UNKNOWN_V_GID   -1  /* unknown file group gid */
#define UNKNOWN_V_SIZE  -1  /* unknown file size */

/*
 * logfserr(int opcode, int result, char *path)
 * 
 * Description:
 * Log the details of a filesystem error. If error logging is enabled for the
 * operation identified by the given opcode, log the opcode, the result and
 * path of the file being operated on to the in-memory filesystem log.
 * Use this logging function when the file path being operated on is known. 
 * That is, for certain cases of errors in open, create and unlink operations.
 * This function is responsible for checking that logging is enabled. The 
 * user of the function does not need to check and should not check. 
 * 
 * Parameters:
 * opcode - the filesystem logging opcode representing the current 
 *      filesystem call. Available opcodes defined in unistd.h are: FSOP_OPEN,
 *      FSOP_CLOSE, FSOP_READ, FSOP_WRITE, FSOP_CREAT and FSOP_UNLNK.
 * result - the result/error status of the call.
 * path - the path of the file being operated on. The path is only known 
 *      for certain cases of errors in open, create and unlink operations. Use
 *      logfserr_nopath when the path is unknown.
 */
void logfserr(int opcode, int result, char *path);

/*
 * logfserr_nopath(int opcode, int result)
 * 
 * Description:
 * Log the details of a filesystem error. If error logging is enabled for the
 * operation identified by the given opcode, log the opcode and the result to
 * the in-memory filesystem log.
 * Use this logging function when the file path is not known.
 * That is, for certain cases of errors in open, create and unlink operations
 * and for all cases of errors in close, read and write operations (when only
 * the file descriptor is known).
 * This function is responsible for checking that logging is enabled. The 
 * user of the function does not need to check and should not check. 

 * Parameters:
 * opcode - the filesystem logging opcode representing the current 
 *      filesystem call. Available opcodes defined in unistd.h are: FSOP_OPEN,
 *      FSOP_CLOSE, FSOP_READ, FSOP_WRITE, FSOP_CREAT and FSOP_UNLNK.
 * result - the result/error status of the call.
 */
void logfserr_nopath(int opcode, int result);

/*
 * logfsop(int opcode, int result, char *path, int fd_nr, mode_t v_mode, 
 *      uid_t v_uid, gid_t v_gid, off_t v_size)
 * 
 * Description:
 * Log the details of a filesystem operation. If logging is enabled for the
 * operation identified by the given opcode, log the opcode, the result, the 
 * path of the file being operated and other parameters to the in-memory
 * filesystem log.
 * Use this logging function when the file path being operated on is known. 
 * That is, for certain cases of open, create and unlink operations.
 * This function is responsible for checking that logging is enabled. The 
 * user of the function does not need to check and should not check. 
 * 
 * Parameters:
 * opcode - the filesystem logging opcode representing the current 
 *      filesystem call. Available opcodes defined in unistd.h are: FSOP_OPEN,
 *      FSOP_CLOSE, FSOP_READ, FSOP_WRITE, FSOP_CREAT and FSOP_UNLNK.
 * result - the result status of the call.
 * path - the path of the file being operated on. The path is only known 
 *      for certain cases of open, create and unlink operations. Use
 *      logfsop_nopath when the path is unknown.
 * fd_nr - the file descriptor number, use UNKNOWN_FD_NR if unknown
 * v_mode - the file open/permission mode, use UNKNOWN_V_MODE if unknown
 * v_uid - the uid of the file owner, use UNKNOWN_V_UID if unknown
 * v_gid - the gid of the file owner, use UNKNOWN_V_GID if unknown
 * v_size - the file size, use UNKNOWN_V_SIZE if unknown
 */
void logfsop(int opcode, int result, char *path, int fd_nr, mode_t v_mode, 
    uid_t v_uid, gid_t v_gid, off_t v_size);
    
/*
 * logfsop_nopath(int opcode, int result, int fd_nr, mode_t v_mode,
 *      uid_t v_uid, gid_t v_gid, off_t v_size)
 * 
 * Description:
 * Log the details of a filesystem operation. If logging is enabled for the
 * operation identified by the given opcode, log the opcode, the result and
 * other parameters to the in-memory filesystem log.
 * Use this logging function when the file path is not known.
 * That is, for all cases of close, read and write operations (when only the
 * file descriptor is known).
 * This function is responsible for checking that logging is enabled. The 
 * user of the function does not need to check and should not check. 

 * Parameters:
 * opcode - the filesystem logging opcode representing the current 
 *      filesystem call. Available opcodes defined in unistd.h are: FSOP_OPEN,
 *      FSOP_CLOSE, FSOP_READ, FSOP_WRITE, FSOP_CREAT and FSOP_UNLNK.
 * result - the result/error status of the call.
 * fd_nr - the file descriptor number, use UNKNOWN_FD_NR if unknown
 * v_mode - the file open/permission mode, use UNKNOWN_V_MODE if unknown
 * v_uid - the uid of the file owner, use UNKNOWN_V_UID if unknown
 * v_gid - the gid of the file owner, use UNKNOWN_V_GID if unknown
 * v_size - the file size, use UNKNOWN_V_SIZE if unknown
 */
void logfsop_nopath(int opcode, int result, int fd_nr, mode_t v_mode,
    uid_t v_uid, gid_t v_gid, off_t v_size);



#endif