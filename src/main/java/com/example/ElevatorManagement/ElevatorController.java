package com.example.ElevatorManagement;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

@RestController
public class ElevatorController {

    @Value("${lift}")
    private int totalLift;

    @Autowired
    private ElevatorManager elevatorManager;


    List<Elevator> elevatorList = new ArrayList<>();

    @PostConstruct
    void init() {
        for (int i = 1; i < totalLift + 1; i++) {
            elevatorList.add(new Elevator(i, Direction.UP, 0, new TreeSet<>(), new TreeSet<>(Collections.reverseOrder())));
        }
    }

    @GetMapping("v1/fromInside/{lift}/{floor}")
    public String getLiftFromInside(@PathVariable int lift, @PathVariable int floor) {
        elevatorManager.updateLift(elevatorList, lift, floor);
        return "We ll be there shortly";
    }

    @GetMapping("v1/fromOutSide/{currFloor}/{targetFloor}")
    public String getLiftFromOutSide(@PathVariable int currFloor, @PathVariable int targetFloor) {
        return elevatorManager.findLift(elevatorList, currFloor, targetFloor);
    }


}
