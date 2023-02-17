import java.time.Duration
import java.time.LocalDateTime

class Receipt(ticket: Ticket, private val receiptNumber: Int,private val exitTime: LocalDateTime? = LocalDateTime.now()) {

    private var entryTime = ticket.getEntryTime()
    private var parkingCharge: Long = 0

    fun getReceiptNumber(): Int {
        return receiptNumber
    }

    fun generateReceipt(): Receipt {
        parkingCharge = calculateParkingCharge()
        return this
    }

    fun calculateParkingCharge(): Long {
        val duration = Duration.between(entryTime, exitTime).toHours()
        return duration * VEHICLE_PER_HOUR_CHARGE
    }

}