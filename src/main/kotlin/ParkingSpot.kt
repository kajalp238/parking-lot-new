class ParkingSpot(private var spotNumber: Int) {

    private var vehicle: VehicleType? = null

    fun getParkedVehicle(): VehicleType? {
        return vehicle
    }

    fun getSpotNumber(): Int {
        return spotNumber
    }

    fun isFree(): Boolean {
        return vehicle == null
    }

    fun assignVehicle(vehicle: VehicleType) {
        this.vehicle = vehicle
    }

    fun unassignVehicle() {
        vehicle = null
    }
}