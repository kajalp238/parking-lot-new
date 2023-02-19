package models

import exceptions.InvalidSpotException
import exceptions.SpotIsOccupiedException
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ParkingLotTest {
    @Test
    fun `should return parking spot`() {

        val parkingLot = ParkingLot()

        val parkingSpot = parkingLot.getSpot(1)

        assertThat(parkingSpot, CoreMatchers.instanceOf(ParkingSpot::class.java))

    }

    @Test
    fun `it should throw InvalidSpotException`() {

        val parkingLot = ParkingLot()

        assertThrows(InvalidSpotException::class.java) {
            parkingLot.getSpot(-1)
        }

    }

    @Test
    fun `it should throw SpotIsOccupiedException`() {

        val parkingLot = ParkingLot()
        parkingLot.reserveSpot(VehicleType.CAR, 1)

        assertThrows(SpotIsOccupiedException::class.java) {
            parkingLot.reserveSpot(VehicleType.CAR, 1)
        }

    }

    @Test
    fun `it should free the spot`() {

        val parkingLot = ParkingLot()
        parkingLot.reserveSpot(VehicleType.CAR, 1)

        val parkingSpot = parkingLot.freeSpot(1)

        assertEquals(null, parkingSpot.getParkedVehicleType())

    }


}