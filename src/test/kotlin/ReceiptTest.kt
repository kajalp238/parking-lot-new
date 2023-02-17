import org.junit.jupiter.api.Assertions.*
import org.testng.annotations.Test
import java.time.LocalDateTime

class ReceiptTest{

    @Test
    fun `it should calculate charge`(){
        val ticket = Ticket(1, 1, LocalDateTime.of(2023, 2, 14, 8, 0, 0))
        val receipt = Receipt(ticket, 1,LocalDateTime.of(2023, 2, 14, 10, 0, 0))

        val expectedCharge = VEHICLE_PER_HOUR_CHARGE * 2
        val actualCharge = receipt.calculateParkingCharge()

        assertEquals(expectedCharge, actualCharge)

    }

}