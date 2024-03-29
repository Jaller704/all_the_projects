/*
 * Replace the following string of 0s with your student number
 * 170256018
 */
#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <dirent.h>
#include "obj_store.h"

#define OSTR_REP_MAX 4096

static bool ostore_on = false;

const char *OFILE_FMT = "./%s/%#zx.txt";
const char *OSTORE_DIR = "ostore";

/* 
 * Declaration of private _get_ofile_path function.
 * 
 * Given an object id (e.g. the address of the object), this function returns
 * a newly allocated pathname for the object file. OFILE_FMT defines the path
 * format. 
 * For example, if the oid is 0x7f91c1402710, the returned path will be:
 *      ./ostore/0x7f91c1402710.txt
 *
 * _get_ofile_path dynamically allocates the path name. It is the user's
 * responsibility to free the allocated memory.
 *
 * _get_ofile_path is used by store_obj to create the filename to use to 
 * store an object and by unlink_obj to create the filename to unlink/delete.
 */
char *_get_ofile_path(size_t oid);

/* disable_ostore: implemented, do NOT change */
void disable_ostore() {
    ostore_on = false;
    
    DIR* osd = opendir(OSTORE_DIR);
    
    if (osd) {
        struct dirent* de;
        
        while ((de = readdir(osd))) {
            if (de->d_name[0] != '.') {
                char* path = NULL;
                (void) asprintf(&path, "./%s/%s", OSTORE_DIR, de->d_name);
                if (path) {
                    unlink(path);
                    free(path);
                }
            }
        }
        
        closedir(osd);
        
        rmdir(OSTORE_DIR);
    }
    
    errno = 0; // ignore errors
}

/* enable_ostore: implemented, do NOT change */
bool enable_ostore() {
    struct stat sbuf;
    
    errno = 0;
    
    int r = stat(OSTORE_DIR, &sbuf);
    
    if (r) {
        if (errno == ENOENT) {
            errno = 0;
            /* ostore doesn't exist, create it */
            r = mkdir(OSTORE_DIR, 0755);
        }
    } else if (!S_ISDIR(sbuf.st_mode)) {
        /* exists but is not a directory */
        r = -1;
        errno = ENOTDIR;
    }
    
    ostore_on = !r;
    
    return ostore_on;
}

/* ostore_is_on: implemented, do NOT change */
bool ostore_is_on() {
    return ostore_on;
}

/* 
 * TODO: IMPLEMENT store_obj
 * See comments to the store_obj function declaration in obj_store.h for the
 * specification of this function. Also see the comments below in the body of
 * the function.
 */
bool store_obj(void* obj, char* (*new_str_rep)(void*)) {
    /* 
     * Your should:
     *      - include appropriate error checking of parameters to the function
     *        and of return values from library function calls
     *      - only use file system operations: open, write, close and unlink
     *      - use _get_ofile_path to create the path of the file to write to
     *        (see comments before declaration of _get_ofile_path)
     *      - call new_str_rep to get the string representation and write it
     *        to file (see commments in obj_store.h etc.)
     *        note: OSTR_REP_MAX defines the maximum length of a string 
     *        representation
     *      - return true if the string representation is successfully written 
     *        to file and false otherwise, in which case no file should remain
     */
    if(!obj || !new_str_rep) {
        errno = EINVAL;
        return false;
    }
    
    if(!ostore_is_on || !OSTORE_DIR) {
        errno = ENOENT;
        return false;
    }
    

    char *newOFilePath = _get_ofile_path((size_t) obj);

    int filDes = open(newOFilePath, O_CREAT | O_WRONLY);
    if(filDes < 0){
        unlink(newOFilePath);
        free(newOFilePath);
        return false;
    }
    free(newOFilePath);
    
    char *buf = (*new_str_rep)(obj);
    
    if(!buf){
        errno = EINVAL;
        return false;
    }
    
    int nBytesW = 0;
    nBytesW = write(filDes, buf, strlen(buf));  
    /* for some reason if the number of bytes to write is 
       OSTR_LEN_MAX or over some arbitrary size write will 
       return -1, it was also doing this when I used strlen(buf)
       previously but now it works most of the time and 
       I'm unsure why */
    /* after not changing anything but removing a few debug 
       printf calls, it now appears to work 100% of the time 
       on both tests */
    if(nBytesW < 0) {
        free(buf);
        return false;
    }

    close(filDes);

    free(buf);

    return true;
}

/* unlink_obj: implemented, do NOT change */
void unlink_obj(void *obj) {
    if (ostore_on && obj) {
        char *ofile_path = _get_ofile_path((size_t) obj);
    
        if (ofile_path) {
            unlink(ofile_path);
        
            free(ofile_path);
        }
    }
    
    return;
}

/* private _get_ofile_path: implemented do NOT change */
char *_get_ofile_path(size_t oid) {
    char *ofile_path = NULL;

    if (oid) 
        (void) asprintf(&ofile_path, OFILE_FMT, OSTORE_DIR, oid);
    
    return ofile_path;
}

