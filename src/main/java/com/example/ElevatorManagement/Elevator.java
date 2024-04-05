package com.example.ElevatorManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Elevator {

    private int liftNumber;

    private Direction direction;

    private int currentFloor;

    private TreeSet<Integer> upDir;

    private TreeSet<Integer> downDir;


}
