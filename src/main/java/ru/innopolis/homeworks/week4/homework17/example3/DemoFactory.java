/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example3;

import ru.innopolis.homeworks.week4.homework17.example3.factory.DecodedImage;
import ru.innopolis.homeworks.week4.homework17.example3.image_readers.GifReader;
import ru.innopolis.homeworks.week4.homework17.example3.image_readers.ImageReader;
import ru.innopolis.homeworks.week4.homework17.example3.image_readers.JpegReader;

public class DemoFactory {
    public static void main(String[] args) {
        DecodedImage decodedImage;
        ImageReader reader = null;
        String image = args.length > 0 ? args[0] : "";
        String extension = image.substring(image.indexOf('.') + 1);
        if ("gif".equals(extension)) {
            reader = new GifReader(image);
        }
        if ("jpeg".equals(extension)) {
            reader = new JpegReader(image);
        }
        assert reader != null;
        decodedImage = reader.getDecodedImage();
        System.out.println(decodedImage);
    }
}
