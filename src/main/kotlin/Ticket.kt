import java.time.LocalDateTime

class Ticket(private var parkingSpotNumber: Int) {

    private var ticketNumber: Int = 1
    private var entryTime: LocalDateTime? = LocalDateTime.now()

    private fun generateTicketNumber(): Int {
        return ticketNumber++
    }

    fun getEntryTime(): LocalDateTime? {
        return entryTime
    }

    fun getParkingSpotNumber(): Int {
        return parkingSpotNumber
    }

    fun getTicketNumber():Int{
        return ticketNumber
    }

}