package models

import java.time.LocalDateTime


data class Ticket(
    private var parkingSpotNumber: Int,
    private var ticketNumber: Int,
    private var entryTime: LocalDateTime? = LocalDateTime.now().withNano(0)
) {
    fun getEntryTime(): LocalDateTime? {
        return entryTime
    }

    fun getParkingSpotNumber(): Int {
        return parkingSpotNumber
    }

}