package ru.levelp.at.lesson0709.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ProblemResponse {

    private String type;
    private String title;
    private String status;
    private String detail;
    private List<ViolationData> violations;
}
