package zkx.process;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

import org.opencv.objdetect.CascadeClassifier;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.rectangle;


public class ViolaAlgo {
	public void DetectFace(String inputImagePath, String outputImagePath){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning DetectFaceDemo");
		// Create a face detector from the cascade file in the resources
		CascadeClassifier faceDetector = new CascadeClassifier("//Users//zkx//Documents//webeclipse//FaceRecognitionProject//lbpcascade_frontalface_improved.xml");
		Mat image = imread(inputImagePath);
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
		String filename = outputImagePath;
		System.out.println(String.format("Writing %s", filename));
		imwrite(filename, image);
	}

}
