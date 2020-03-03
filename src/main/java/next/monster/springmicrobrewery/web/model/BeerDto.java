package next.monster.springmicrobrewery.web.model;

import java.time.OffsetDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

  @Null
  private UUID id;

  @NotBlank
  private String beerName;

  @NotBlank
  private String beerStyle;

  @Positive
  private Long upc;

  private OffsetDateTime createdDate;

  private OffsetDateTime lastUpdatedDate;
}
