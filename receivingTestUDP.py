#ari larson
#5/26/2015
#windows side listening simple UDP in python

import socket
 
UDP_IP = "10.0.0.55"
UDP_PORT = 5005
 
sock = socket.socket(socket.AF_INET, # Internet
                      socket.SOCK_DGRAM) # UDP
print "Socket defined"
sock.bind((UDP_IP, UDP_PORT))
print "Socket Bound"

while True:
    data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
    print "received message:", data