#ari larson
#5/26/2015
#windows side listening simple UDP in python
import os
import socket
import sys

#IP = "10.0.0.162"#10.0.1.17
IP = socket.gethostname()
PORT = 5005
 
sock = socket.socket(socket.AF_INET, # Internet
                      socket.SOCK_STREAM) # TCP
sock.connect(('', PORT))
print sys.argv[0] 
while True:
    #data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
    #if(sys.argv[1]=="voice"):   
    #    os.system("espeak -ven+f3 -s150 "+" ' " + data + " ' ")
    #else:
	#print "received message", data
    data = sock.recv(1024)
    print data
