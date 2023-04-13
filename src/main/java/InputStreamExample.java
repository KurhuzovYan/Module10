import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InputStreamExample {

    public String searchCorrectNumber() {
        String result = "";

        try (InputStream fis = new FileInputStream("numbers.txt")) {
            byte[] buffer = new byte[256];
            int c;

            while ((c = fis.read(buffer)) > 0) {
                if (c < 256) {
                    buffer = Arrays.copyOf(buffer, c);
                }
            }

            String[] arrNum = new String(buffer).split(System.lineSeparator());

            for (String number : arrNum) {
                if (number.matches("(\\(?\\d{3}\\)?[\\- ]?)[\\d\\-]{8}")) {
                    result += number + "\n";
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}