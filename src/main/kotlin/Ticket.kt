import java.time.LocalDateTime

class Ticket(private var parkingSpotNumber: Int, private var ticketNumber: Int, private var entryTime: LocalDateTime? = LocalDateTime.now()) {

    fun getEntryTime(): LocalDateTime? {
        return entryTime
    }

    fun getParkingSpotNumber(): Int {
        return parkingSpotNumber
    }

    fun getTicketNumber(): Int {
        return ticketNumber
    }

}