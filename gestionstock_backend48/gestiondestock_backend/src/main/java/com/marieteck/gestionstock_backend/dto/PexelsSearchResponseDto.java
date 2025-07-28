package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class PexelsSearchResponseDto {
    private Integer page;

    @JsonProperty("per_page")
    private Integer perPage;

    private List<PexelsPhotoDto> photos;

    @JsonProperty("total_results")
    private Integer totalResults;

    @JsonProperty("next_page")
    private String nextPage;

    @JsonProperty("prev_page")
    private String prevPage;
}
