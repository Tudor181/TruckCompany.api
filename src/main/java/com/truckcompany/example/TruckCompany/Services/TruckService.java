package com.truckcompany.example.TruckCompany.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.DateToLocalTimeConverter;
import org.springframework.stereotype.Service;

import com.truckcompany.example.TruckCompany.Truck;
import com.truckcompany.example.TruckCompany.Repositories.ITruckRepository;

import lombok.val;

@Service
public class TruckService {
    // let the framework know to
    @Autowired // instanciate this class here
    private ITruckRepository truckRepository;

    public List<Truck> allTrucks() {
        return truckRepository.findAll();
    }

    public Optional<Truck> truckById(String id) {
        try {
            Optional<Truck> truckFound = truckRepository.findById(new ObjectId(id));
            List<String> driverIdsModified = new ArrayList<String>();
            if (!truckFound.isEmpty()) {
                // truckFound.ifPresent(value -> {
                // value.getDriverIds().forEach(driverId -> {
                // driverId.setId(driverId.getId().toString());
                // });
                // ;
                // });
                // (driverId -> {
                // driverId.toString();
                // });
                return truckFound;
            } else {
                return null;
            }

        } catch (

        RuntimeException e) {
            throw new RuntimeException("The truck was not found");
        }
    }

    public Optional<Truck> truckByImdbId(String imdbId) {
        return truckRepository.findTruckByImdbId(imdbId);
    }

    public Truck createTruck(String manufacturer, Optional<String> title) {
        // Truck truck = new Truck(id, "Default",
        // LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),
        // "undefined",
        // "undefined",
        // Collections.emptyList(), manufacturer, Collections.emptyList());

        Truck truckInserted = truckRepository
                .insert(new Truck(
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(),
                        manufacturer, title));
        return truckInserted;
    }

}
