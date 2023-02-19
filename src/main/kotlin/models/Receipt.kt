package models

import java.time.LocalDateTime

data class Receipt(
    private val receiptNumber: Int,
    private val parkingFee: Long,
    private val entryTime: LocalDateTime,
    private val exitTime: LocalDateTime? = LocalDateTime.now()
)
