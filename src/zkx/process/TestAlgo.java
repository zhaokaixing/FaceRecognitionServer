package zkx.process;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.rectangle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.objdetect.CascadeClassifier;

public class TestAlgo {

	public static void Test(){
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//System.loadLibrary("opencv-341");
		System.out.println("\nRunning DetectFaceDemo");
		// Create a face detector from the cascade file in the resources
		CascadeClassifier faceDetector = new CascadeClassifier("//Users//zkx//Documents//webeclipse//FaceRecognitionProject//lbpcascade_frontalface_improved.xml");
		Mat image = imread("//Users//zkx//Documents//webeclipse//FaceRecognitionProject//IMG_6598.JPG");
		// Detect faces in the image.
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		// Draw a bounding box around each face.
		for (Rect rect : faceDetections.toArray()) {
			rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}
		// Save the visualized detection.
		String filename = "//Users//zkx//Documents//webeclipse//FaceRecognitionProject//FaceDetection.png";
		System.out.println(String.format("Writing %s", filename));
		imwrite(filename, image);
	}

}
