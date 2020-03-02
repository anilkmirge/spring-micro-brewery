package next.monster.springmicrobrewery.web.controller.v2;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import next.monster.springmicrobrewery.services.v2.BeerServiceV2;
import next.monster.springmicrobrewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {
  private final BeerServiceV2 beerServiceV2;

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId) {
    return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity handlePost(@Valid @RequestBody BeerDtoV2 beer) {
    log.debug("in handle post...");
    val savedBeer = beerServiceV2.saveNewBeer(beer);
    var headers = new HttpHeaders();
    // todo add hostname to url
    headers.add("Location  ", "/api/v2/beer/" + savedBeer.getId().toString());
    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDtoV2 beer) {
    beerServiceV2.updateBeer(beerId, beer);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("beerId") UUID beerId) {
    beerServiceV2.deleteById(beerId);
  }
}
