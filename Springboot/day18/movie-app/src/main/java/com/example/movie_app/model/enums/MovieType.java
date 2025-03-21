package com.example.movie_app.model.enums;

public enum MovieType {
    PHIM_BO("Phim Bộ"),
    PHIM_LE("Phim Lẻ"),
    PHIM_CHIEU_RAP("Phim Chiếu Rạp");

    private final String displayName;


    MovieType(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}
