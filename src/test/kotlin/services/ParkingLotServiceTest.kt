import exceptions.VehicleIsNotParkedException
import models.Ticket
import models.VehicleType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.testng.annotations.Test
import services.ParkingLotService

class ParkingLotServiceTest {

    @Test
    fun `it should park the vehicle`() {

        val actualTicket = ParkingLotService().park(VehicleType.CAR)

        val ticketNumberField = actualTicket.javaClass.getDeclaredField("ticketNumber")
        ticketNumberField.isAccessible = true
        assertEquals(1, ticketNumberField.get(actualTicket))

    }

    @Test
    fun `it should unpark the vehicle`() {
        val parkingLotService = ParkingLotService()
        val ticket = parkingLotService.park(VehicleType.CAR)

        val receipt = parkingLotService.unPark(ticket)

        val receiptNumberField = receipt.javaClass.getDeclaredField("receiptNumber")
        receiptNumberField.isAccessible = true
        assertEquals(1, receiptNumberField.get(receipt))
    }

    @Test
    fun `it should throw an exception as unparking the vehicle which is not parked`() {
        val ticket = Ticket(1, 1)

        assertThrows(VehicleIsNotParkedException::class.java) {
            ParkingLotService().unPark(ticket)
        }
    }


}