package testsheepnz.panther.odm.calculator.test;

import testsheepnz.panther.odm.calculator.CalculateFuelUsage;
import org.junit.Test;
import testsheepnz.panther.odm.calculator.AircraftManagement;

import static org.junit.Assert.*;

public class CalculateFuelUsageTests {

    /*
     * Need to build a whole load of these
     */
    @Test
    public void test13680kgSpeed510ktDistance100nmAltitude13680ftFuelUsage () {
        CalculateFuelUsage fuelCalc = new CalculateFuelUsage();

        //Set weight as needed
        fuelCalc.setAircraftWeight(13680.0);

        //Set height as needed
        fuelCalc.climb(12000.0);

        // Or ...
        // fuelCalc.dive(10000.0);

        fuelCalc.aircraftLeg(510.0,100.0);
        double fuelUsed = fuelCalc.getFuelUsed();

        //assertTrue("Fuel data provided processed", fuelUsed==522.3529411764706);
        assertEquals("For weight 13680kg speed 510kts over 100nm at alt 12000 fuel used should be 522kg",
                fuelUsed, 522.3529411764706, 0.5);
    }

    @Test
    public void divingFrom32000ftTo10000ftUsesNoFuel() {
        CalculateFuelUsage fuelCalc = new CalculateFuelUsage();

        //Set weight as needed
        fuelCalc.setAircraftWeight(13680.0);

        //Set height as needed
        fuelCalc.climb(32000.0);
        fuelCalc.dive(10000.0);
        double fuelUsed = fuelCalc.getFuelUsed();

        assertEquals("For weight 13680kg diving from 32000ft to 10000ft uses no fuel",
                fuelUsed, 0.0, 0.5);
    }

    @Test
    public void climbingFromGroundTo32000ftUses320kg() {
        CalculateFuelUsage fuelCalc = new CalculateFuelUsage();

        //Set weight as needed
        fuelCalc.setAircraftWeight(13680.0);

        //Set height as needed
        fuelCalc.climb(32000.0);
        double fuelUsed = fuelCalc.getFuelUsed();

        assertEquals("For weight 13680kg climbing from 0f to 32000ft 320kg fuel used",
                fuelUsed, 320.0, 0.5);
    }
}
