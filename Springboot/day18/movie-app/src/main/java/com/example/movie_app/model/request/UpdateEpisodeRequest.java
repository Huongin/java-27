package com.example.movie_app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEpisodeRequest {
    private String name;
    private Integer displayOrder;
    private Boolean status;
}
