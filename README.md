### File List

1. EchoServer.java
2. EchoThread.java

### Description

This program implements a multi-threaded echo server.
For every inputted English uppercase or lowercase character, it will be sent back to the client
If the sequence "quit" is encountered, the connection will close for that client, but not shut down the server

### Quit Method

Using a termination string reference, the program checks if the current sequence of inputs potentially leads
to "quit", it follows the sequence and either terminates if a "quit" is found, or waits until another new
potential sequence appears
