package zkx.process;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class ImageProcess {
	public static String DecodeImage(String strImage)  
    {  
        if (strImage == null) 
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            byte[] b = decoder.decodeBuffer(strImage);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {  
                    b[i]+=256;  
                }  
            }   
            String outputImagePath = "//Users//zkx//Desktop//ProjetOption//new.jpg"; 
            OutputStream out = new FileOutputStream(outputImagePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return outputImagePath;  
        }   
        catch (Exception e)   
        {  
            return null;  
        }  
    }  
	
	public static String EncodeImage(String inputImagePath){
		InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(inputImagePath);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);
	}
}
