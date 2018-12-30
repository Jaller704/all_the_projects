/*
 * Replace the following string of 0s with your student number
 * 170256018
 */
#include <stdbool.h>
#include <errno.h>
#include <limits.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#include "obj_store.h"
#include "string_o.h"

/* Prototype of private _add function prototype for implementation of the add 
 * member of struct integer 
 */
static String _add(String self, String s);

/* Prototype of private _char_at function prototype for implementation of the 
 * char_at member of struct integer 
 */
static char _char_at(String self, int posn);

/* Prototype of private _equals function prototype for implementation of the
 * equals member of struct integer 
 */
static bool _equals(String self, String s);

/* Prototype of private _get_value function prototype for implementation of 
 * the get_value member of struct integer 
 */
static char* _get_value(String self, char* buf);

/* Prototype of private _index_of function prototype for implementation of the
 * index_of member of struct integer 
 */
static int _index_of(String self, char c, int start);

/* Prototype of private _length function prototype for implementation of the
 * length member of struct integer 
 */
static int _length(String self);

/* the string representation of a String for saving to file */
static char *STR_REP_FMT = "str:%d:%s\n";

/* private _new_str_rep to dynamically allocate a new string representation 
 * of a String.
 * Pass a pointer to this function to the store_obj function of the obj_store
 * library.
 * Do NOT change this function.
 */
static char* _new_str_rep(void* obj) {
    char* str_rep = NULL;
    
    if (obj) {
        String s = (String) obj;
        /* asprintf allocates new string */
        (void) asprintf(&str_rep, STR_REP_FMT, s->_len, s->_val);
    }
    
    return str_rep;
}

/* newString: implemented, do NOT change */
String newString(char* value) {
    if(!value) {
        errno = EINVAL;
        return NULL;
    }
    int len = strlen(value);
    len = len > STR_LEN_MAX ? STR_LEN_MAX
                            : len;
    String self;

    self = (String) malloc(sizeof(struct string));
    if(self == NULL){
        errno = ENOMEM;
        return NULL;
    }

    self->_len = len;
    char *newValue;
    if(len == 0) {
        newValue = (char *) calloc(1,sizeof(char));
    } else {
        newValue = (char *) calloc(len,sizeof(char));
    }
    if(newValue == NULL){
        errno = ENOMEM;
        return NULL;
    }
    
    newValue = strncpy(newValue, value, len);
    self->_val = newValue;

    self->add = _add;
    self->char_at = _char_at;
    self->equals = _equals;
    self->get_value = _get_value;
    self->index_of = _index_of;
    self->length = _length;

    if (ostore_is_on()) {
        if(store_obj(self, _new_str_rep)){
            
        } else {
            /* ostore is on but failed to store object */
            free(self);
            self = NULL;        
        }
    }

    return self;
}

/* deleteString: implemented, do NOT change */
void deleteString(String *as) { 

    if(as != NULL && *as) {
        if(ostore_is_on()) {
            unlink_obj(*as);
        }

        free((*as)->_val);
        free(*as);

        *as = NULL;

    }

}

/* _add: implemented, do NOT change */
String _add(String self, String s) {
    if (!self || !s) {
        errno = EINVAL;
        return NULL;
    }

    int s1Len = 0;
    int s2Len = 0;
    

    s1Len = self->_len;
    s2Len = s->_len;

    int newLen = s1Len + s2Len;
    
    char nString[newLen];
    for(int i = 0; i < newLen; i++){
        nString[i] = '\0';
    }

    strcpy(nString, self->_val);
    newLen = strlen(nString);
    strcpy(&nString[s1Len], s->_val);

    newLen = strlen(nString);

    String fString = newString(nString);

    return fString;
}

/* _char_at: implemented, do NOT change */
char _char_at(String self, int posn) {
    if(!self || posn < 0) {
        errno = EINVAL;
        return 0;
    }
    char sChar;
    if(self->_len == 0){
        return 0;
    } else if(self->_len <= posn){
        errno = EINVAL;
        return 0;
    } else {
        sChar = (self->_val)[posn];
    }
    

    return sChar;
}

/* _equals: implemented do NOT change */
bool _equals(String self, String s) {
    if (!self || !s) {
        return false;
    }

    int same = strcmp(self->_val,s->_val);

    if(same == 0 || self == s) {
        return true;
    } else {
        return false;
    }
}

/* _get_value: implemented, do NOT change*/
char* _get_value(String self, char *buf)  {
    if (!self || !buf) {
        errno = EINVAL;
        return NULL;
    }

    buf = strcpy(buf, self->_val);
    return buf;

}

/* _index_of: implemented, do NOT change */
int _index_of(String self, char c, int start) {
    if(!self || start < 0 || !c) {
        errno = EINVAL;
        return -1;
    }

    if(start >= self->_len){
        errno = EINVAL;
        return -1;
    }
    for(int i = start; i < self->_len; i++){
        if((self->_val)[i] == c){
            return i;
        }
    }
    return -1;
    
}

/* _length: implemented, do NOT change */
int _length(String self) {
    if (!self) {
        errno = EINVAL;
        return -1;
    }

    return self->_len;
}
