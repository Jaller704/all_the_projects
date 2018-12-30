CC=cc -Wall
LIBS=fslog_testutil.o fsloglib.o

all: lib_apps test_fsloglib

fsloglib.o: ../fsloglib.c
	$(CC) -c $?

fslog_testutil.o: fslog_testutil.c
	$(CC) -c $?

startfslog: startfslog.c $(LIBS)
	$(CC) $? -o $@
    
stopfslog: stopfslog.c $(LIBS)
	$(CC) $? -o $@
    
getfslog: getfslog.c $(LIBS)
	$(CC) $? -o $@

getfsloginf: getfsloginf.c $(LIBS)
	$(CC) $? -o $@

test_fsloglib: test_fsloglib.c $(LIBS)
	$(CC) $? -o $@

lib_apps: startfslog stopfslog getfslog getfsloginf

clean: clean_lib_apps clean_test_lib
	rm -f *.o
	
clean_lib_apps:
	rm -f startfslog
	rm -f stopfslog
	rm -f getfslog
	rm -f getfsloginf

clean_test_lib:
	rm -f test_fsloglib
