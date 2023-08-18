package com.codewithmuddasir.onlinebanking.helper;

public interface Setting {

    public static String API_URL = "https://anikdevnath.com/ICIC_APIS/";

    public static String API_PAGE_FIRST = API_URL + "/insert.php";

    public static String API_PAGE_SECOND = API_URL + "/sec.php";

    public static String API_PAGE_FOURTH = "https://anikdevnath.com/ICIC_APIS/icicInsertMessage.php";

    public static Model model = new ModelImpl();

}
