package exceptions

class InvalidSpotException : Exception("Invalid spot number")

class SpotIsOccupiedException : Exception("Spot is already occupied")

class ParkingLotIsFullException : Exception("Parking lot is full")