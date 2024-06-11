package springboot_store.security.auth.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot_store.security.user.request.UserRequest;
import springboot_store.store.request.StoreRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegisterStoreRequest {

    @NotNull(message = "Field required")
    private UserRequest userRequest;

    @NotNull(message = "Field required")
    private StoreRequest storeRequest;
}