package fr.univcotedazur.multifidelity.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE) //Protected access
public class Constants {

    public static class Common {
        public static final String STRING_TYPE = "string";

        public static final String INTEGER_TYPE = "integer";

        public static final String UUID_TYPE = "UUID";

        public static final String DATE_FORMAT = "date-time";

        public static final String BOOLEAN_TYPE = "boolean";

        public static final String FLOAT_TYPE = "float";

        public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    }

}
