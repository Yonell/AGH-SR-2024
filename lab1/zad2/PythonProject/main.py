import socket
serverIP = "127.0.0.1"
serverPort = 9876
msg = "Ping Python Udp!"
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(bytes(msg, 'utf-8'), (serverIP, serverPort))
client.sendto(bytes("żółta gęś", 'utf-8'), (serverIP, serverPort))
