package com.udea.driver2023.controller;
import com.udea.driver2023.exception.InvalidRatingException;
import com.udea.driver2023.exception.ModelNotFoundException;
import com.udea.driver2023.model.Flight;
import com.udea.driver2023.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/flight")
@CrossOrigin("*")

public class FlightController {
    @Autowired
    FlightService flightService;
    @PostMapping("/save")
    public long save(
            @RequestBody Flight flight) throws InvalidRatingException {
        if(flight.getRating() > 5)
            throw new InvalidRatingException("Id should be less than or equal 5");
        flightService.save(flight);
        return flight.getIdFlight();
    }
    @GetMapping("/listAll")
    public Iterable<Flight> listAllFlights(){return flightService.list();}
    @GetMapping("/list/{id}")
    public Flight listFlightById( @PathVariable("id") int id){
        Optional<Flight> flight= flightService.listId(id);
        if(flight.isPresent()){
            return flight.get();
        }
        throw new ModelNotFoundException("ID de Flight invalido");
    }
    @GetMapping("/topFlights")
    public ResponseEntity<List<Flight>> viewBestFlights(){
        List<Flight> list=flightService.viewBestFlight();
        return new ResponseEntity<List<Flight>>(list, HttpStatus.ACCEPTED);
    }
    @PutMapping
    public Flight updateFlight(@RequestBody Flight flight){
        return flightService.update(flight);
    }
    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable long id){
        return flightService.delete(id);
    }
}