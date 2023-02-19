package services

import constants.MAX_CAPACITY
import exceptions.ParkingLotIsFullException
import models.*

class ParkingLotService {

    private val parkingLot: ParkingLot = ParkingLot()
    private var ticketNumber = 1
    private var receiptNumber = 1

    fun park(vehicleType: VehicleType): Ticket {
        val freeSpot = getFreeSlot() ?: throw ParkingLotIsFullException()
        val ticket = Ticket(freeSpot.getSpotNumber(), ticketNumber++)
        parkingLot.park(vehicleType, freeSpot.getSpotNumber())
        return ticket
    }

    fun unPark(ticket: Ticket): Receipt {
        parkingLot.freeSpot(ticket.getParkingSpotNumber())
        return Receipt(ticket, receiptNumber++).generateReceipt()
    }


    private fun getFreeSlot(): ParkingSpot? {
        for (spotNumber in 1..MAX_CAPACITY) {
            val spot = parkingLot.getSpot(spotNumber)!!
            if (spot.getParkedVehicleType() == null)
                return spot
        }
        return null
    }



}