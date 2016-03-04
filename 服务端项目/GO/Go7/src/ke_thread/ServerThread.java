package ke_thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ke_util.FileUtil;


/**
 * 查询线程
 * 
 * 
 */
public class ServerThread implements Runnable {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}


	public void run() {

		try {
			// 读取客户端传过来信息的dataInputStream
			DataInputStream in = new DataInputStream(socket.getInputStream());

			// 向客户端发送信息的dataOutputStream
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());

			// 读取来自客户端的信息
			String accept = in.readUTF();

			System.out.println(accept);

			// 如果发来的消息是获取盘符
			if (accept.equals("getDisk")) {
				// 向客户端发送信息
				out.writeUTF(FileUtil.getDriver());

				System.out.println(FileUtil.getDriver());
			}
				out.writeUTF(FileUtil.getDirInTray(accept));
				System.out.println(FileUtil.getDirInTray(accept));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
