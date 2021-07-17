package ej.airport.step;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

@Log4j2
public class Skipper implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        if (skipCount >= 100) {
            return false;
        }
        log.error("{}", exception.getMessage());
        return true;
    }

}
