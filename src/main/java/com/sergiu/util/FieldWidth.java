package com.sergiu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldWidth {
    private static final Logger LOGGER = LoggerFactory.getLogger(FieldWidth.class);

    public static int getPredefinedWidth(String fieldName) {
        switch (fieldName) {
            case "cnp":
                return 120;
            case "firstName":
                return 100;
            case "lastName":
                return 100;
            case "highSchool":
                return 80;
            case "bacGrade":
                return 50;
            case "bacBestGrade":
                return 50;
            default:
                LOGGER.info("For filed:" + fieldName + "there is no width defined. Default will be 100");
                return 100;
        }
    }
}
