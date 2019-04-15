package com.example.sample.request;

import com.example.sample.domain.Investor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequest {
    @JsonProperty("name")
    @NotBlank(message = "Name may not be null")
    String name;

    @NotBlank(message = "Description may not be null")
    @JsonProperty("description")
    String description;

    @NotEmpty(message = "Investor list cannot be empty")
    @JsonProperty("investors")
    Set<Investor> investors;
}
