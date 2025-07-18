package com.learn.bookstore.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@UtilityClass
public class CoverImageFileUtil {

    public static final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png))$)";

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String FILE_NAME_FORMAT = "%s_%s";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        return BookFileUtil.isAllowedExtension(fileName, pattern);
    }

    public static void assertAllowed(MultipartFile file, String pattern) {
        final long size = file.getSize();
        if (size > MAX_FILE_SIZE) {
            throw new RuntimeException("Max file size is 2MB");
        }

        final String fileName = file.getOriginalFilename();
        if (!isAllowedExtension(fileName, pattern)) {
            throw new RuntimeException("Only Jpg, png, gif, bmp files are allowed");
        }
    }

    public static String getFileName(final String name) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final String date = dateFormat.format(System.currentTimeMillis());
        return String.format(FILE_NAME_FORMAT, name, date);
    }
}