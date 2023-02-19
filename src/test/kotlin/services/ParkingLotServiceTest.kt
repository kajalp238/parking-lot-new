package services

import exceptions.VehicleIsNotParkedException
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

    @Test
    fun `it should calculate parking fee`(){

        val parkingLotService = ParkingLotService()

        val actualFee = parkingLotService.calculateParkingFee(LocalDateTime.of(2023, 2, 4, 0, 0), LocalDateTime.of(2023, 2, 4 ,5, 0))

        assertEquals(50, actualFee)

    }

    @Test
    fun `it should calculate parking fee when car is parked for some minutes`(){

        val parkingLotService = ParkingLotService()

        val actualFee = parkingLotService.calculateParkingFee(LocalDateTime.of(2023, 2, 4, 5, 0),
                                LocalDateTime.of(2023, 2, 4 ,5, 15))

        assertEquals(10, actualFee)

    }

}