package com.example.api_aes.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public class Converter extends AbstractHttpMessageConverter<Object> {

	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Inject
    private ObjectMapper objectMapper;

    public Converter(){
        super(MediaType.APPLICATION_JSON,
            new MediaType("application", "*+json", DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return objectMapper.readValue(decrypt(inputMessage.getBody()), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(encrypt(objectMapper.writeValueAsBytes(o)));
    }

    private InputStream decrypt(InputStream inputStream){
    	try {
    		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    		byte[] original = cipher.doFinal(inputStream.readAllBytes());
    		InputStream targetStream = new ByteArrayInputStream(original);
    		return targetStream;
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}

    	return null;
    }

    private byte[] encrypt(byte[] bytesToEncrypt){
        // do your encryption here 
    	try {
    		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

    		byte[] encrypted = cipher.doFinal(bytesToEncrypt);
    		return encrypted;
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return null;

    }
}
