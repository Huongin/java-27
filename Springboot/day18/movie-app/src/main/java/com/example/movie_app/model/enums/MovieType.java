package com.example.movie_app.model.enums;

public enum MovieType {
    PHIM_BO("Phim Bộ"),
    PHIM_LE("Phim Lẻ"),
    PHIM_CHIEU_RAP("Phim Chiếu Rạp");

   public String value;

    public String getValue() {
        return value;
    }

    private MovieType(String value) {
        this.value = value;
    }
}
