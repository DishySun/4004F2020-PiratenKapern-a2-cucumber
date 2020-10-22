package application.server;


import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import game.GameControl;

public class Server{
	private static int PORT = 4004;
	private GameControl gc;
	
	public Server () {
		gc = new GameControl();
	}
	
	public void start() throws IOException {
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("Server Started\nWaiting connection...");
		while (true) {
			Socket s = ss.accept();
			if(gc.playerConnect(s)) break;
		}
	}
	
	public static void main(String args[]) throws IOException {
		Server server = new Server();
		server.start();
	}



}
