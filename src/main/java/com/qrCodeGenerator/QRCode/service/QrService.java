package com.qrCodeGenerator.QRCode.service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class QrService {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;


    public byte[] generateQRService(String text) throws IOException, WriterException {

        String data = Optional.ofNullable(text).orElse("Vinod is greattt");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);

        // write the QR code to a file
        createQRCodeToFile(bitMatrix);
        return byteArrayOutputStream.toByteArray();

    }

    private void createQRCodeToFile(BitMatrix matrix) throws WriterException, IOException {
        String path = "demo.png";
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1),
                new File(path));

    }
}
