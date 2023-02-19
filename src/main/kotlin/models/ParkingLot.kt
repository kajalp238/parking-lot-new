package models

import constants.MAX_CAPACITY
import exceptions.*

class ParkingLot {

    private val parkingSpots: MutableList<ParkingSpot?> = MutableList(MAX_CAPACITY + 1) { ParkingSpot(-1) }

    fun getSpot(spotNumber: Int): ParkingSpot? {
        if (spotNumber > MAX_CAPACITY || spotNumber <= 0)
            throw InvalidSpotException()
        if (parkingSpots[spotNumber] == null)
            parkingSpots[spotNumber] = ParkingSpot(spotNumber)
        return parkingSpots[spotNumber]

    }

    fun park(vehicle: VehicleType, spotNumber: Int): ParkingSpot {
        val spot = getSpot(spotNumber)!!
        if (spot.isFree()) {
            parkingSpots[spot.getSpotNumber()]?.assignVehicle(vehicle)
            return spot
        }
        throw SpotIsOccupiedException()
    }

    fun freeSpot(spotNumber: Int): ParkingSpot {
        val spot = getSpot(spotNumber)!!
        if (spot.getParkedVehicleType() == null)
            throw NoVehicleParkedException()
        spot.unAssignVehicle()
        return spot
    }

}