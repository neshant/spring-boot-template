package com.example.sample.request;

import com.example.sample.domain.Investor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequest {
    @JsonProperty("name")
    @NotBlank(message = "Name may not be null")
    @NonNull
    String name;

    @NotBlank(message = "Description may not be null")
    @JsonProperty("description")
    @NonNull
    String description;

    @NotEmpty(message = "Investor list cannot be empty")
    @JsonProperty("investors")
    @NonNull
    Set<Investor> investors;
}
