import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.font.TextAttribute;

import javax.imageio.ImageIO;

public class ImgOperation {

    public static void writeTextOnImage(File coverImg,
            HashMap<String, HashMap<String, HashMap<String, Integer>>> sectionCoordinates,
            HashMap<String, String> chequeData, File coverOutImg) {

        BufferedImage bufferedCoverImg = null;
        try {
            bufferedCoverImg = ImageIO.read(coverImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics g = bufferedCoverImg.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        g.setColor(new Color(10, 10, 10));
        Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
        // adding space bettween letters as an text attribute
        attributes.put(TextAttribute.TRACKING, 0.25);
        g.setFont(g.getFont().deriveFont(attributes));

        for (String key : chequeData.keySet()) {
            g.drawString(chequeData.get(key),
                    sectionCoordinates.get(key).get("start").get("x") + 5,
                    sectionCoordinates.get(key).get("start").get("y") + 20);
        }

        g.dispose();
        try {
            ImageIO.write(bufferedCoverImg, "png", coverOutImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
