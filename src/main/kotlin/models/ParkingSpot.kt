package models

class ParkingSpot(private var spotNumber: Int) {

    private var vehicleType: VehicleType? = null

    fun getParkedVehicle(): VehicleType? {
        return vehicleType
    }

    fun getSpotNumber(): Int {
        return spotNumber
    }

    fun isFree(): Boolean {
        return vehicleType == null
    }

    fun assignVehicle(vehicleType: VehicleType) {
        this.vehicleType = vehicleType
    }

    fun unAssignVehicle() {
        vehicleType = null
    }
}