import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SteganographyUtil {

    public static void encode(BufferedImage image, String data, String outputFilePath) throws IOException {
        // Convert the data to a binary string
        StringBuilder binaryData = new StringBuilder();
        for (char ch : data.toCharArray()) {
            binaryData.append(String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0'));
        }
        binaryData.append("00000011");  // EOF marker in binary

        int dataIndex = 0;
        int dataLength = binaryData.length();
        int width = image.getWidth();
        int height = image.getHeight();
        
        outerLoop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (dataIndex < dataLength) {
                    int pixel = image.getRGB(x, y);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;

                    // Encode data into the least significant bit of the blue channel
                    blue = (blue & 0xFE) | (binaryData.charAt(dataIndex) - '0');
                    dataIndex++;

                    int newPixel = (red << 16) | (green << 8) | blue;
                    image.setRGB(x, y, newPixel);
                } else {
                    break outerLoop;
                }
            }
        }
        ImageIO.write(image, "PNG", new File(outputFilePath));
    }
    
    public static String decode(BufferedImage image) {
        StringBuilder binaryData = new StringBuilder();
        int width = image.getWidth();
        int height = image.getHeight();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int blue = pixel & 0xff;
                binaryData.append(blue & 1);
            }
        }

        StringBuilder decodedData = new StringBuilder();
        for (int i = 0; i < binaryData.length(); i += 8) {
            String byteString = binaryData.substring(i, Math.min(i + 8, binaryData.length()));
            if (byteString.equals("00000011")) {
                break;
            }
            int charCode = Integer.parseInt(byteString, 2);
            decodedData.append((char) charCode);
        }

        return decodedData.toString();
    }
}
