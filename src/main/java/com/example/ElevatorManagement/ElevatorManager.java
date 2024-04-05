package com.example.ElevatorManagement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ElevatorManager {
    public void updateLift(List<Elevator> elevatorList, int lift, int floor) {
        Optional<Elevator> elevator= elevatorList.stream().filter(p->p.getLiftNumber()==lift).findAny();
        if(elevator.isPresent()){
            Elevator elevatorEntity= elevator.get();
            elevatorEntity.getUpDir().add(floor);
            elevatorEntity.getDownDir().add(floor);
        }
    }

    public String findLift(List<Elevator> elevatorList, int currFloor, int targetFloor) {
        if(currFloor==targetFloor){
            return "Not funny  -- Captain Jack Sparrow";
        }
        Direction direction= (currFloor-targetFloor)>0?Direction.DOWN:Direction.UP;
        Stream<Elevator> findByDirection= elevatorList.stream().filter(p->p.getDirection().equals(direction));
        Optional<Elevator> findLift;
        if(direction.equals(Direction.UP)){
            findLift = findByDirection.filter(p->p.getCurrentFloor()<currFloor).findAny();
        }else{
            findLift= findByDirection.filter(p->p.getCurrentFloor()>currFloor).findAny();
        }
        if(findLift.isPresent()){
            findLift.get().getUpDir().add(targetFloor);
            findLift.get().getDownDir().add(targetFloor);
            findLift.get().getUpDir().add(currFloor);
            findLift.get().getDownDir().add(currFloor);
        }else{
            Elevator elevator= elevatorList.stream().findAny().get(); //minimum 1 lift is always present
            elevator.getDownDir().add(targetFloor);
            elevator.getUpDir().add(targetFloor);
            elevator.getDownDir().add(currFloor);
            elevator.getUpDir().add(currFloor);
            findLift = Optional.of(elevator);
        }

        return "lift assigned = "+ findLift.get().getLiftNumber();
    }
}
