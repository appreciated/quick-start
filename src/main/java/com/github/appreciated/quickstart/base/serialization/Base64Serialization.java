package com.github.appreciated.quickstart.base.serialization;

import java.io.*;
import java.util.Base64;

/**
 * Created by appreciated on 29.12.2016.
 */
public class Base64Serialization {

    public static String encodeObject(Object object) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(outputStream.toByteArray());
    }

    public static Object decodeObject(String encodedComponent) {
        Object decodedObject = null;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] data = decoder.decode(encodedComponent);
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            decodedObject = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return decodedObject;
    }
}
