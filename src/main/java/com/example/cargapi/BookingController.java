package com.example.cargapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking") 
public class BookingController {

    private final BookingRepo bookingRepo;

    @Autowired
    public BookingController(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

   
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking) {
        Booking savedBooking = bookingRepo.save(newBooking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(
            @RequestParam(required = false) UUID loadId,
            @RequestParam(required = false) String transporterId,
            @RequestParam(required = false) Status status) {

        Booking filterBy = new Booking();
        filterBy.setLoadId(loadId);
        filterBy.setTransporterId(transporterId);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Booking> example = Example.of(filterBy, matcher);

        List<Booking> bookings = bookingRepo.findAll(example);
        return ResponseEntity.ok(bookings);
    }

  
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID bookingId) {
        return bookingRepo.findById(bookingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable UUID bookingId, @RequestBody Booking updatedBookingDetails) {
        return bookingRepo.findById(bookingId)
                .map(existingBooking -> {
                    existingBooking.setProposedRate(updatedBookingDetails.getProposedRate());
                    existingBooking.setComment(updatedBookingDetails.getComment());
                    existingBooking.setStatus(updatedBookingDetails.getStatus());

                    Booking savedBooking = bookingRepo.save(existingBooking);
                    return ResponseEntity.ok(savedBooking);
                })
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        if (bookingRepo.existsById(bookingId)) {
            bookingRepo.deleteById(bookingId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}