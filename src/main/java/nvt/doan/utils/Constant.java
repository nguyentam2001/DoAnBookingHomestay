package nvt.doan.utils;

import java.time.LocalDate;

public interface Constant {
    public static final String  FOLDER_PATH="src/main/resources/static/images";
    public static final int MAX_AGE_COOKIE =  24 * 60 * 60; // 1 tuần
    public static final Integer DAY_CANCEL=2;
    public static final LocalDate DATE_NOW=LocalDate.now();
    public static final String PAYMENT_SUCCESS_STATUS="00";
    public static final Integer CHECKOUT_STATUS=0;

    public static final Integer RENTING_STATUS=1;

    public static final Integer CANCEL_STATUS = 2;
    public static final Integer PAYMENT_FAIL = 3;
}
