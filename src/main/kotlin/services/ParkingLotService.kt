package services

import constants.MAX_CAPACITY
import exceptions.ParkingLotIsFullException
import models.*

var ticketNumber = 1
var receiptNumber = 1

class ParkingLotService {

    private val parkingLot: ParkingLot = ParkingLot()

    fun park(vehicle: VehicleType): Ticket {
        val freeSpot = getFreeSlot() ?: throw ParkingLotIsFullException()
        val ticket = Ticket(freeSpot.getSpotNumber(), ticketNumber++)
        parkingLot.park(vehicle, freeSpot.getSpotNumber())
        return ticket
    }

    fun unpark(ticket: Ticket): Receipt {
        parkingLot.freeSpot(ticket.getParkingSpotNumber())
        return Receipt(ticket, receiptNumber++).generateReceipt()
    }


    private fun getFreeSlot(): ParkingSpot? {
        for (spotNumber in 1..MAX_CAPACITY) {
            val spot = parkingLot.getSpot(spotNumber)!!
            if (spot.getParkedVehicle() == null)
                return spot
        }
        return null
    }


}