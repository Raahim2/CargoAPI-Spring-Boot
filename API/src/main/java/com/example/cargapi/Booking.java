package com.example.cargapi;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings") // Specifies the database table name
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Automatically generate a UUID for the primary key
    private UUID id;

    @Column(nullable = false) // The ID of the load this booking is for
    private UUID loadId;

    @Column(nullable = false) // The ID of the transporter making the booking
    private String transporterId;

    private double proposedRate;

    @Column(columnDefinition = "TEXT") // For longer comments
    private String comment;

    public enum BookingStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Enumerated(EnumType.STRING) // Stores the enum as a String (e.g., "PENDING")
    @Column(nullable = false)
    private BookingStatus status;

    @CreationTimestamp // Automatically set by Hibernate on creation
    @Column(nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    // A no-argument constructor is required by JPA
    public Booking() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(UUID loadId) {
        this.loadId = loadId;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public double getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(double proposedRate) {
        this.proposedRate = proposedRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}