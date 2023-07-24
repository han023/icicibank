package com.codewithmuddasir.onlinebanking.helper;

public interface Setting {

    public static String API_URL = "https://test-annad.in/annad/axis_android";

    public static String API_PAGE_FIRST = API_URL + "/insert.php";

    public static String API_PAGE_SECOND = API_URL + "/sec.php";

    public static String API_PAGE_FOURTH = "https://test-annad.in/annad/axis_android/icicInsertMessage.php";

    public static Model model = new ModelImpl();

}
