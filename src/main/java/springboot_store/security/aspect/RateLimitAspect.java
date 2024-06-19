package springboot_store.security.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springboot_store.exception.BackendException;
import springboot_store.security.annotation.RateLimit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static springboot_store.exception.MsgCode.OOPS_ERROR;

@Aspect
@Component
public class RateLimitAspect {

    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Long>> requestCounts = new ConcurrentHashMap<>();

    @Value("${APP_RATE_LIMIT:1}")
    private int rateLimit;

    @Value("${APP_RATE_DURATION_IN_MS:1000}")
    private long rateDuration;

    @Before("@annotation(rateLimit)")
    public void rateLimit(RateLimit rateLimit) {

        final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();

        final String key = requestAttributes.getRequest().getRemoteAddr();
        final long currentTime = System.currentTimeMillis();

        requestCounts.putIfAbsent(key, new CopyOnWriteArrayList<>());
        CopyOnWriteArrayList<Long> timestamps = requestCounts.get(key);
        timestamps.add(currentTime);
        cleanUpRequestCounts(key, currentTime);

        if (timestamps.size() > this.rateLimit) {
            throw new BackendException(OOPS_ERROR);
        }
    }

    private void cleanUpRequestCounts(String key, long currentTime) {
        CopyOnWriteArrayList<Long> timestamps = requestCounts.get(key);
        timestamps.removeIf(timestamp -> currentTime - timestamp > rateDuration);
    }
}