import socket
serverIP = "127.0.0.1"
serverPort = 9876
msg = "Ping Python Udp!"
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.bind(('',9878));
client.sendto(bytes(msg, 'utf-8'), (serverIP, serverPort))

buff, address = client.recvfrom(1024)

print("received msg: " + str(buff, 'utf-8'))

