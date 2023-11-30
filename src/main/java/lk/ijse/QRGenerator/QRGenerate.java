package lk.ijse.QRGenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;
public class QRGenerate {
    public QRGenerate() throws WriterException, IOException {
        String data="http://www.google.com";
      
        String path="E:\\QRjava\\google.jpg";

        BitMatrix matrix=new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE,500,500);
        MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
    }
}
