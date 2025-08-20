package com.example.cargapi;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;



@Entity
@Table(name = "loads") // Specifies the database table name
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Automatically generate a UUID for the primary key
    private UUID id;

    @Column(nullable = false) // Assuming shipperId is mandatory
    private String shipperId;

    @Embedded // Embeds the Facility class fields into this table
    private Facility facility;

    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;

    public enum LoadStatus {
        POSTED,
        BOOKED,
        CANCELLED
    }

    @Column(columnDefinition = "TEXT") // Use TEXT for potentially long comments
    private String comment;

    @CreationTimestamp // Automatically sets the value on creation
    @Column(nullable = false, updatable = false)
    private LocalDateTime datePosted;

    @Enumerated(EnumType.STRING) // Stores the enum value as a String (e.g., "POSTED") in the DB
    @Column(nullable = false)
    private LoadStatus status;

    // A no-argument constructor is required by JPA
    public Load() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getNoOfTrucks() {
        return noOfTrucks;
    }

    public void setNoOfTrucks(int noOfTrucks) {
        this.noOfTrucks = noOfTrucks;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus status) {
        this.status = status;
    }
}