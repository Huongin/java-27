package com.example.movie_app.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEpisodeRequest {
    @NotEmpty(message = "Tên tập phim không được để trống")
    private String name;

    @NotNull(message = "Thứ tự hiển thị không được để trống")
    private Integer displayOrder;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @NotNull(message = "movieId không được để trống")
    private Integer movieId;
}
