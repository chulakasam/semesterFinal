package lk.ijse.QRGenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class QRGenerate {
    public QRGenerate(String id, String name, String address, String email) throws WriterException, IOException {
        String[]data={id,name,address,email};
      
        String path="/home/sampath/Documents/final project QR/client.jpg";

        BitMatrix matrix=new MultiFormatWriter().encode(Arrays.toString(data), BarcodeFormat.QR_CODE,500,500);
        MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
    }
}
