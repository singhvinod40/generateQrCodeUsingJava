package com.qrCodeGenerator.QRCode.controller;


import com.google.zxing.WriterException;
import com.qrCodeGenerator.QRCode.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QrController {


    @Autowired
    private QrService qrService;


    @PostMapping("/getQRCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody String text) throws IOException, WriterException {
        byte[] qrCode = qrService.generateQRService(text);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
    }

    @GetMapping("/getStatuc")
    public String getStatuc() throws IOException, WriterException {

        return "Server is up and Running";
    }
}
