package models

import models.VehicleType.CAR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParkingSpotTest {

    @Test
    fun `it should assign vehicle to parking spot`() {

        val parkingSpot = ParkingSpot(1)

        parkingSpot.assignVehicle(CAR)

        assertEquals(CAR, parkingSpot.getParkedVehicleType())

    }

    @Test
    fun `it should free the spot`() {

        val parkingSpot = ParkingSpot(1)

        parkingSpot.unAssignVehicle()

        assertEquals(null, parkingSpot.getParkedVehicleType())

    }
}