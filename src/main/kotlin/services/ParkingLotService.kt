package services

import constants.MAX_CAPACITY
import constants.VEHICLE_PER_HOUR_CHARGE
import exceptions.ParkingLotIsFullException
import models.*
import java.time.Duration
import java.time.LocalDateTime

class ParkingLotService {

    private val parkingLot: ParkingLot = ParkingLot()
    private var ticketNumber = 1
    private var receiptNumber = 1

    fun park(vehicleType: VehicleType): Ticket {

        val freeSpot = getFreeSpot()
        val ticket = Ticket(freeSpot.getSpotNumber(), ticketNumber++)

        parkingLot.reserveSpot(vehicleType, freeSpot.getSpotNumber())

        return ticket

    }

    fun unPark(ticket: Ticket): Receipt {

        parkingLot.freeSpot(ticket.getParkingSpotNumber())

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

        val exitTime = LocalDateTime.now()
        val parkingFee = calculateParkingFee(ticket.getEntryTime()!!, exitTime)

        return Receipt(receiptNumber++, parkingFee, ticket.getEntryTime()!!, exitTime)

    }

    fun calculateParkingFee(entryTime: LocalDateTime, exitTime: LocalDateTime): Long {

        val duration = Duration.between(entryTime, exitTime.plusMinutes(59)).toHours()

        return duration * VEHICLE_PER_HOUR_CHARGE

    }

}