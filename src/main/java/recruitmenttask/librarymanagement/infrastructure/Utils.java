package recruitmenttask.librarymanagement.infrastructure;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bartosz on 2017-01-22.
 */
public class Utils {

    //private static final String YEAR_PATTERN = "^[12][0-9]{3}$";

    public static <T> T returnSingleResult(List<T> result) {
        return result.isEmpty() ? null : result.get(0);
    }

}
