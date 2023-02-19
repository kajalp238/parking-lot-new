package models

object Generator {

    private var ticketNumber = 1
    private var receiptNumber = 1

    fun nextTicketNumber(): Int {
        return ticketNumber++
    }

    fun nextReceiptNumber(): Int {
        return receiptNumber++
    }

}