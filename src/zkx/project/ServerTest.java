package zkx.project;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zkx.process.ImageProcess;
import zkx.process.TestAlgo;
import zkx.process.ViolaAlgo;


/**
 * Servlet implementation class ServerTest
 */
public class ServerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int length = request.getContentLength();
			byte[] input = new byte[length];
			ServletInputStream sin = request.getInputStream();
			int c, count = 0;
			while ((c = sin.read(input, count, input.length - count)) != -1) {
				count += c;
			}
			sin.close();

			String recievedString = new String(input);
			System.out.println(recievedString);
			String decodeOutputImagePath = ImageProcess.DecodeImage(recievedString);
			System.out.println(decodeOutputImagePath);

			ViolaAlgo detectAlgoObject = new ViolaAlgo();
			String detectOutputImagePath = "//Users//zkx//Desktop//ProjetOption//FaceDetection.png";
			detectAlgoObject.DetectFace(decodeOutputImagePath, detectOutputImagePath);
			//TestAlgo.Test();
		
			String encodeString = ImageProcess.EncodeImage(detectOutputImagePath);
			//System.out.println(encodeString);
			
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			//Integer doubledValue = 2;

			writer.write(encodeString);
			writer.flush();
			writer.close();

		} catch (IOException e) {

			try {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(e.getMessage());
				response.getWriter().close();
			} catch (IOException ioe) {
			}
		}
	}
}
