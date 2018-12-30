In these folders I have compiled my C programming courseworks, not only are they for
teaching us C but they were also for teaching us about MINIX and POSIX systems and 
as such they are built to run on MINIX 3.2.1.
------------------------------------------------------------------------------------------
CSC2025-ASSIGNMENT1-DIST
Here I was tasked with completeing implementations of higher level abstractions of 
the int type and char* (string) type to bring them closer to their Java counterparts.

The files that have been modified by me are the string_o, integer, and obj_store c 
files, where I had to program the subtract, divide, modulo, newString, deleteString,
char_at, index_of, get_value, equals, and store_obj functions
------------------------------------------------------------------------------------------
CSC2025-ASSIGNEMENT2-DIST
Here I was tasked with programming various system calls and their associated library 
fuctions for logging file system operations along with modifying some of these file system
operations to log successful and failed operations, this was tested through updating MINIX
with my files and then running some test classes.

The files that have been modified by me are the fslog, fsloglib.c, link, and read c files, 
where I programmed and modified the do_startfslog, do_stopfslog, do_getfsloginf, do_getfslog, 
startfslog, stopfslog, getfsloginf, getfslog, do_read_write, read_write, and do_unlink 
functions.