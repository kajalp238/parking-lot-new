package models

import constants.MAX_CAPACITY
import exceptions.InvalidSpotException
import exceptions.SpotIsOccupiedException
import exceptions.VehicleIsNotParkedException

class ParkingLot {

    private val parkingSpots = mutableListOf<ParkingSpot?>()

    init {
        for (spotNumber in 0..MAX_CAPACITY) {
            parkingSpots.add(ParkingSpot(spotNumber))
        }
    }

    fun getSpot(spotNumber: Int): ParkingSpot? {

        if (spotNumber > MAX_CAPACITY || spotNumber <= 0)
            throw InvalidSpotException()

        return parkingSpots[spotNumber]

    }

    fun reserveSpot(vehicleType: VehicleType, spotNumber: Int): ParkingSpot {

        val spot = getSpot(spotNumber)!!

        if (spot.isFree()) {
            parkingSpots[spot.getSpotNumber()]?.assignVehicle(vehicleType)
            return spot
        }

        throw SpotIsOccupiedException()
    }

    fun freeSpot(spotNumber: Int): ParkingSpot {

        val spot = getSpot(spotNumber)!!
        if (spot.isFree()) throw VehicleIsNotParkedException()

        spot.unAssignVehicle()

        return spot
    }

}