package ej.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.BatchStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private Long id;
    private BatchStatus status;

}
