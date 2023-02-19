package services

import exceptions.InvalidTicketException
import models.Receipt
import models.Ticket
import models.VehicleType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.testng.annotations.Test
import java.time.LocalDateTime

class ParkingLotServiceTest {

    @Test
    fun `it should park the vehicle`() {

        val parkingLotService = ParkingLotService()

        val actualTicket = parkingLotService.park(VehicleType.CAR)

        val expectedTicket = Ticket(1, 1, actualTicket.getEntryTime())
        assertEquals(expectedTicket, actualTicket)

    }

    @Test
    fun `it should unpark the vehicle`() {

        val parkingLotService = ParkingLotService()
        val ticket = parkingLotService.park(VehicleType.CAR)

        val receipt = parkingLotService.unPark(ticket)

        val expectedReceipt = Receipt(1, 0, ticket.getEntryTime()!!)
        assertEquals(expectedReceipt, receipt)

    }

    @Test
    fun `it should throw an exception as ticket is not valid`() {

        val ticket = Ticket(1, 1)

        assertThrows(InvalidTicketException::class.java) {
            ParkingLotService().unPark(ticket)
        }

    }

    @Test
    fun `it should calculate parking fee`() {

        val parkingLotService = ParkingLotService()

        val actualFee = parkingLotService.calculateParkingFee(
            LocalDateTime.of(2023, 2, 4, 0, 0),
            LocalDateTime.of(2023, 2, 4, 5, 0)
        )

        assertEquals(50, actualFee)

    }

    @Test
    fun `it should calculate parking fee when car is parked for some minutes`() {

        val parkingLotService = ParkingLotService()

        val actualFee = parkingLotService.calculateParkingFee(
            LocalDateTime.of(2023, 2, 4, 5, 0),
            LocalDateTime.of(2023, 2, 4, 5, 15)
        )

        assertEquals(10, actualFee)

    }

}