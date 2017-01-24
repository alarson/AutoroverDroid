#first attempt 5/26
#Ari Larson
#code for udp communication referenced from wiki.python.org

import socket

UDP_IP="10.0.0.17"
UDP_PORT=5005
MESSAGE="hello world"

print "UDP target ip: ",UDP_IP
print "UDP target port: ", UDP_PORT
print "message: ",MESSAGE

sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
sock.sendto(MESSAGE,(UDP_IP, UDP_PORT))
