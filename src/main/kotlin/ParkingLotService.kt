import exceptions.ParkingLotIsFullException

class ParkingLotService {

    private val parkingLot: ParkingLot = ParkingLot()

    fun park(vehicle: VehicleType): Ticket {
        val freeSpot = getFreeSlot() ?: throw ParkingLotIsFullException()
        val ticket = Ticket(freeSpot.getSpotNumber())
        parkingLot.park(vehicle, freeSpot.getSpotNumber())
        return ticket
    }

    fun unpark(ticket: Ticket): Receipt {
        parkingLot.freeSpot(ticket.getParkingSpotNumber())
        return Receipt(ticket).generateReceipt()
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