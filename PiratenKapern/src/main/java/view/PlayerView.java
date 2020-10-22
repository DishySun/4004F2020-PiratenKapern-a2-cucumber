package view;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PlayerView {
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	
	public PlayerView(Socket s) throws IOException {
		this.s = s;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream());
	}
	
	public String askName() throws IOException {
		out.println("name?");
		out.flush();
		String s = in.readLine();
		return s;
	}

	public String getCommand() throws IOException {
		out.println("command?");
		out.flush();
		return in.readLine();
	}

	public void send(String msg) {
		out.println(msg);
		out.flush();
	}

	public void shutdown() {
		out.println("game end");
		out.flush();
		try {
			this.s.shutdownInput();
			this.s.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
