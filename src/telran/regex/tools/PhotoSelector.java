package telran.regex.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhotoSelector {
    public static String[] selectPicture(String[] pictures, String regex) {
        Pattern pattern = Pattern.compile(regex);
        List<String> result = new ArrayList<>();

        for (String picture : pictures) {
            Matcher matcher = pattern.matcher(picture);
            if (matcher.find()) {
                result.add(picture);
            }
        }

        return result.toArray(new String[0]);
    }
}
