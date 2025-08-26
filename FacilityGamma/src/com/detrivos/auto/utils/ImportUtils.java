package com.detrivos.auto.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImportUtils {

    public static BufferedImage loadImage(String path) {
        BufferedImage img = new BufferedImage(1, 1, 1);
        try {
            img = ImageIO.read(new File("FacilityGamma/res/" + path));
        } catch (IOException e) {
            System.err.println("Error loading image at: " + path + "\n" + e.getLocalizedMessage());
        }
        return img;
    }
}
