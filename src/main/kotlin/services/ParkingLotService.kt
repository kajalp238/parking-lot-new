package services

import constants.MAX_CAPACITY
import constants.VEHICLE_PER_HOUR_CHARGE
import exceptions.InvalidTicketException
import exceptions.ParkingLotIsFullException
import models.*
import java.time.Duration
import java.time.LocalDateTime

class ParkingLotService {

    private val parkingLot: ParkingLot = ParkingLot()
    private val validTickets = mutableMapOf<Int, Int>()

    fun park(vehicleType: VehicleType): Ticket {

        val freeSpot = getFreeSpot()

        parkingLot.reserveSpot(vehicleType, freeSpot.getSpotNumber())

        val ticketNumber = Generator.nextTicketNumber()
        validTickets[freeSpot.getSpotNumber()] = ticketNumber

        return Ticket(freeSpot.getSpotNumber(), ticketNumber)

    }

    fun unPark(ticket: Ticket): Receipt {

        val spotNumber = ticket.getParkingSpotNumber()

        if (ticket.getTicketNumber() != validTickets[spotNumber])
            throw InvalidTicketException()

        validTickets[spotNumber] = -1
        parkingLot.freeSpot(spotNumber)

        return generateReceipt(ticket)

    }

    private fun getFreeSpot(): ParkingSpot {

        for (spotNumber in 1..MAX_CAPACITY) {
            val spot = parkingLot.getSpot(spotNumber)!!
            if (spot.isFree()) return spot
        }

        throw ParkingLotIsFullException()

    }

    private fun generateReceipt(ticket: Ticket): Receipt {

        val exitTime = LocalDateTime.now().withNano(0)
        val parkingFee = calculateParkingFee(ticket.getEntryTime()!!, exitTime)

        return Receipt(Generator.nextReceiptNumber(), parkingFee, ticket.getEntryTime()!!, exitTime)

    }

    fun calculateParkingFee(entryTime: LocalDateTime, exitTime: LocalDateTime): Long {

        val duration = Duration.between(entryTime, exitTime.plusMinutes(59)).toHours()

        return duration * VEHICLE_PER_HOUR_CHARGE

    }

}