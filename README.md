## JavaCorbaFileSystem
This is a sample Distributed File System using Java Corba. Feel free to use or modify the code.

## Env
- jdk 1.8
- windows 10 64bits

## How to use
### <1> compile the `TFile.idl` file using compiler `idlj`
```
$ cd src
$ idlj -fall TFile.idl 
```
### <2> compile the server program and client program
```
$ javac FileClient.java client/*.java
$ javac FileServer.java server/*.java
```
### <3> run the application
```
// start orbd 
$ start orbd -ORBInitialPort 1050 -ORBInitialHost localhost   
// start server program
$ java FileServer -ORBInitialPort 1050 -ORBInitialHost localhost
// start client program
$ java FileClient ORBInitialPort 1050 -ORBInitialHost localhost
```
