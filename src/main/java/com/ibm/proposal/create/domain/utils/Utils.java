package com.ibm.proposal.create.domain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private Utils(){}

    public static String[] createFieldOptions(String... options) {
        return options;
    }

    public static String dateFieldFormatter(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}
