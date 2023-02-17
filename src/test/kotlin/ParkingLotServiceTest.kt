import exceptions.InvalidSpotException
import exceptions.SpotIsOccupiedException
import org.junit.jupiter.api.Assertions.*
import org.testng.annotations.Test

class ParkingLotServiceTest {

    @Test
    fun `it should park the vehicle`() {

        val ticket = ParkingLotService().park(VehicleType.CAR)

        assertEquals(1, ticket.getTicketNumber())
        assertEquals(1, ticket.getParkingSpotNumber())

    }

    @Test
    fun `it should unpark the vehicle`() {
        val parkingLotService = ParkingLotService()
        val ticket = parkingLotService.park(VehicleType.CAR)

        val receipt = parkingLotService.unpark(ticket)

        assertEquals(1, receipt.getReceiptNumber())
    }

    @Test
    fun `it should throw an exception as unparking the vehicle which is not parked`() {
        val ticket = Ticket(1, 1)

        assertThrows(InvalidSpotException::class.java) {
            ParkingLotService().unpark(ticket)
        }
    }

    @Test
    fun `it should throw an exception as spot already have a vehicle`() {
        val parkingLot = ParkingLot()
        parkingLot.park(VehicleType.CAR, 1)

        assertThrows(SpotIsOccupiedException::class.java) {
            parkingLot.park(VehicleType.CAR, 1)
        }

    }

}