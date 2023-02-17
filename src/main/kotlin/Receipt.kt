import java.time.LocalDateTime

class Receipt(ticket: Ticket) {

    private var receiptNumber: Int = 1
    private var entryTime = ticket.getEntryTime()
    private var exitTime = LocalDateTime.now()
    private var parkingFee: Long = 0

    fun getReceiptNumber(): Int {
        return receiptNumber
    }

    private fun generateReceiptNumber(): Int {
        return receiptNumber++
    }

    fun getParkingFee(): Long {
        return parkingFee
    }

}