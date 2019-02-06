package ru.innopolis.lectures.week1.lecture04;

import java.io.*;

public class StreamDemo {
    public static void main(String[] args) throws IOException {
//        testByteStreams();
        testFileStreams();
    }

    private static void testFileStreams() throws IOException {
        try (InputStream inputStream = new ErrorInputStreamReader("./.gitignore")) {
            System.out.println("Размер файла: " + inputStream.available());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            throw new IOException("Message");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void testByteStreams() throws IOException {
        byte[] arr = "hello мир!".getBytes();
        InputStream byteArrayInputStream = new ByteArrayInputStream(arr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int b;
        while ((b = byteArrayInputStream.read()) != -1) {
            System.out.println((char)b);
            byteArrayOutputStream.write(b);
        }
        System.out.println(byteArrayOutputStream);
    }
}
