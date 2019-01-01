# Client_Server_Calculator-SocketProgramming

A simple implementation of Client-Server model using socket programming.

Compilation and running instructions:
There are two file namely:
• server.java
• client.java
Environment:
The provided code can be tested on UNIX/Linux systems.
Compilation:
Both files needs to compiled before the execution. The compilation can be done on different system as:
server_sys> javac server.java
client_sys> javac client.java
Execution:
After successful compilation, both files needs to be executed.
server.java accepts one arguments as port number which can be any number other than reserved port
numbers. For sake, take the port number as the last 4 digits of my UFID.
server_sys> java server <port_number_last_4_ digit_UFID> (Port number to be used: 3997)
client.java accepts two arguments that are server address and port number where, as mentioned, port number
should be last 4 digits of my UFID i.e 3997.
client_sys> java client <server IP/URL> <port_number_last_4_ digit_UFID>
Description:
• server.java:-
- The server program creates a socket connection.
- After successful establishment of connection with client, the server receives the input (equation) from the
client side and verifies the equation is in valid format and processes the result and return a corresponding
value.
- If there is any kind of error in the equation, the server returns a corresponding value to client.
- If upon receiving the “bye” message the server closes the connection from the client and keeps on running
to accept the incoming connections.
- Upon receiving the “terminate” message the server terminates all the connections and shutdowns.
• client.java:-
- The client programs opens the socket for communication.
- After successful establishment of connection with server, the client takes input from user and passes the
equation to the server to process.
- After the server processes the request, the client receives a corresponding return value from the server and
according to return value a corresponding result is displayed.
- If client writes “bye” as input, it receives a return value of -5 which closes the socket of client and
terminates the client.
- If client writes “terminates”, both server and client shutdown.

**********
