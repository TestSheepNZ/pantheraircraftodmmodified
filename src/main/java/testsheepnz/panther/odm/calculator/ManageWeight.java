package testsheepnz.panther.odm.calculator;

public class ManageWeight {

    // Weights
    private double acWeight = 6000.0;
    private double acEquipmentWeight;
    private double acFuelRemaining;
    private double acMaxFuel;

    public ManageWeight() {
        acWeight = 6000.0;
        acEquipmentWeight = 0.0;
        acFuelRemaining = 0.0;
        acMaxFuel = 3000.0;
    }

    // Return all up weight
    public double getAllUpWeight() {
        return acWeight + acEquipmentWeight + acFuelRemaining;
    }

    public double getRemainingFuel() {
        return acFuelRemaining;
    }

    public boolean modifyFuelWeight(double mod) {
        acFuelRemaining -= mod;
        return true;
    }

    // Manage aircraft equipment
    public boolean addA2AMissile() {
        return addA2AMissile(1);
    }

    public boolean addA2AMissile(int num) {
        acEquipmentWeight += (num * 150.0);
        return true;
    }

    public boolean addReconPod() {
        acEquipmentWeight += 800.0;
        return true;
    }

    public boolean addDumbBomb() {
        return addDumbBomb(1);
    }

    public boolean addDumbBomb(int num) {
        acEquipmentWeight += (num * 600.0);
        return true;
    }

    public boolean addIntelliBomb() {
        acEquipmentWeight += 2000.0;
        return true;
    }

    public boolean addExternalFuelTanks() {
        acEquipmentWeight += 200.0;
        acMaxFuel += 3000.0;
        return true;
    }

    public boolean dropDumbBomb() {
        return dropDumbBomb(1);
    }

    public boolean dropDumbBomb(int num) {
        acEquipmentWeight -= (num * 600.0);
        return true;
    }

    public boolean dropIntelliBomb() {
        acEquipmentWeight -= 2000.0;
        return true;
    }

    // Refuel
    public boolean addFuel(double fuelTopUp, double altitude) {
        if ((acFuelRemaining + fuelTopUp) > acMaxFuel) {
            System.out.println("Too heavy " + acFuelRemaining + " out of " + acMaxFuel);
            return false;
        }

        if (altitude != 0.0 && altitude < 10000.0) {
            System.out.println("Too low " + altitude);
            return false;
        }

        acFuelRemaining += fuelTopUp;
        return true;
    }

}
