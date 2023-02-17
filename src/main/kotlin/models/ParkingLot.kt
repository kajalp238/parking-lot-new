package models

import constants.MAX_CAPACITY
import exceptions.InvalidSpotException
import exceptions.SpotIsOccupiedException

class ParkingLot {

    private val spots: MutableList<ParkingSpot?> = MutableList(MAX_CAPACITY + 1) { null }

    fun getSpot(spotNumber: Int): ParkingSpot? {
        if (spotNumber > MAX_CAPACITY || spotNumber <= 0)
            throw InvalidSpotException()
        if (spots[spotNumber] == null)
            spots[spotNumber] = ParkingSpot(spotNumber)
        return spots[spotNumber]

    }

    fun park(vehicle: VehicleType, spotNumber: Int): ParkingSpot {
        val spot = getSpot(spotNumber)!!
        if (spot.isFree()) {
            spots[spot.getSpotNumber()]?.assignVehicle(vehicle)
            return spot
        }
        throw SpotIsOccupiedException()
    }

    fun freeSpot(spotNumber: Int): ParkingSpot {
        val spot = getSpot(spotNumber)!!
        if (spot.getParkedVehicle() == null)
            throw InvalidSpotException()
        spot.unassignVehicle()
        return spot
    }

}