package ej.airport.converter;

import ej.airport.dto.JobType;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, JobType> {

    @Override
    public JobType convert(String value) {
        try {
            return JobType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Illegal argument: " + value);
        }
    }

}
