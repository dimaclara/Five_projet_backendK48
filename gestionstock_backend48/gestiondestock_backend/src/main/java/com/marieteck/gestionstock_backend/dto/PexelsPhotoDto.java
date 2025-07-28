package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PexelsPhotoDto {
    private Long id;
    private Integer width;
    private Integer height;
    private String url;
    private String photographer;

    @JsonProperty("photographer_url")
    private String photographerUrl;

    @JsonProperty("photographer_id")
    private Long photographerId;

    @JsonProperty("avg_color")
    private String avgColor;

    private PexelsPhotoSrcDto src;
    private Boolean liked;
    private String alt;
}
