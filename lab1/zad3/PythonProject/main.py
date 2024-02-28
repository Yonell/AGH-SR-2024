import socket
serverIP = "127.0.0.1"
serverPort = 9876
msg = "Ping Python Udp!"
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.bind(('', 9877))
client.sendto((42).to_bytes(4,"little"), (serverIP, serverPort))

buff, address = client.recvfrom(4)
integer = int.from_bytes(buff,"little")
print(f"Received value: {integer}")

