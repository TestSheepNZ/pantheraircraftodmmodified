package testsheepnz.panther.odm.calculator;

public class AircraftManagement {

    //
    private ManageWeight acWeightManagement;
    private CalculateFuelUsage fuelUsage;

    public AircraftManagement() {
        acWeightManagement = new ManageWeight();
        fuelUsage = new CalculateFuelUsage();
    }

    // Return all up weight
    public double getAllUpWeight() {
        return acWeightManagement.getAllUpWeight();
    }

    public double getRemainingFuel() {
        return acWeightManagement.getRemainingFuel();
    }

    // Climb / dive fuel usage
    public boolean climb(double newAltitude) {

        boolean returnStatus = fuelUsage.climb(newAltitude);

        if (returnStatus) {
            acWeightManagement.modifyFuelWeight(fuelUsage.getFuelUsed());
        }
        return returnStatus;
    }

    public boolean dive(double newAltitude) {
        boolean returnStatus = fuelUsage.dive(newAltitude);

        if (returnStatus) {
            acWeightManagement.modifyFuelWeight(fuelUsage.getFuelUsed());
        }
        return returnStatus;
    }

    // Fuel usage over a leg ...
    public boolean aircraftLeg(double speed, double distance) {
        boolean returnStatus;

        // Set current weight
        fuelUsage.setAircraftWeight(acWeightManagement.getAllUpWeight());

        returnStatus = fuelUsage.aircraftLeg(speed, distance);

        if (returnStatus) {
            acWeightManagement.modifyFuelWeight(fuelUsage.getFuelUsed());
        }

        return returnStatus;
    }

    public double getFuelUsed() {
        return fuelUsage.getFuelUsed();
    }

    // Manage aircraft equipment
    public boolean addA2AMissile() {
        return acWeightManagement.addA2AMissile(1);
    }

    public boolean addA2AMissile(int num) {
        return acWeightManagement.addA2AMissile(num);
    }

    public boolean addReconPod() {
        return acWeightManagement.addReconPod();
    }

    public boolean addDumbBomb() {
        return acWeightManagement.addDumbBomb(1);
    }

    public boolean addDumbBomb(int num) {
        return acWeightManagement.addDumbBomb(num);
    }

    public boolean addIntelliBomb() {
        return acWeightManagement.addIntelliBomb();
    }

    public boolean addExternalFuelTanks() {
        return acWeightManagement.addExternalFuelTanks();
    }

    public boolean dropDumbBomb() {
        return acWeightManagement.dropDumbBomb(1);
    }

    public boolean dropDumbBomb(int num) {
        return acWeightManagement.dropDumbBomb(num);
    }

    public boolean dropIntelliBomb() {
        return acWeightManagement.dropIntelliBomb();
    }

    // Refuel
    public boolean addFuel(double fuelTopUp) {
        return acWeightManagement.addFuel(fuelTopUp, fuelUsage.getAltitude());
    }

}
