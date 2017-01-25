#ari larson
#5/26/2015
#windows side listening simple UDP in python
import os
import socket
import sys

UDP_IP = "10.0.0.162"#10.0.1.17
UDP_PORT = 5005
 
sock = socket.socket(socket.AF_INET, # Internet
                      socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT))
print sys.argv[0] 
while True:
    data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
    if(sys.argv[1]=="voice"):   
        os.system("espeak -ven+f3 -s150 "+" ' " + data + " ' ")
    else:
	print "received message", data
