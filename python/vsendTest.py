# Ari Larson
# 1/24/17
#stream video (successive, maybe compressed jpgs) from webcam with cv, over udp from droid
#largely adapted from http://www.chioka.in/python-live-video-streaming-example/

from camera import VideoCamera
import socket

UDP_IP="10.0.0.17"

UDP_PORT=5005

print "UDP target ip: ",UDP_IP
print "UDP target port: ", UDP_PORT

sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
cam = VideoCamera()

while True:
	frame = camera.get_frame()
	sock.sendto(frame,(UDP_IP, UDP_PORT))
