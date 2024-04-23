package com.example.mobilestudy.utils;

public class Converter {
    public static String convertCity(String city) {
        switch (city) {
            case "krasnoyarsk":
                return "Красноярск";
            case "msk":
                return "Москва";
            case "nsk":
                return "Новосибирск";
            default:
                return "Unknown city";
        }
    }

    public static String convertType(String type) {
        switch (type) {
            case "exhibition":
                return "Выставки";
            case "education":
                return "Образование";
            default:
                return "Unknown type";
        }
    }
}
