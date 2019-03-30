package testsheepnz.panther.odm.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculateFuelUsage {

    // Data
    private double fuelUsedLastManoeuvre;
    private double acAltitude;
    private double acWeight;

    // Fuel info
    private List<FuelRateData> fuelData;
    
    // Fuel info file
    private SystemProperties systemProperties;

    public CalculateFuelUsage() {
        systemProperties = new SystemProperties();

        fuelData = new ArrayList<FuelRateData>();
        acWeight = 6000.0;

        acAltitude = 0.0;
        fuelUsedLastManoeuvre = 0.0;

        initialiseFuelUsage();
    }

    // Set ac weight
    public boolean setAircraftWeight(double newWeight) {
        acWeight = newWeight;
        return true;
    }

    // Get value for fuel used last manoeuvre
    public double getFuelUsed() {
        return fuelUsedLastManoeuvre;
    }

    // Get the altitude
    public double getAltitude() {
        return acAltitude;
    }

    // Climb / dive fuel usage
    public boolean climb(double newAltitude) {
        double fuelUsed = 1 * (newAltitude - acAltitude) / 100.0;
        fuelUsedLastManoeuvre = fuelUsed;
        acAltitude = newAltitude;
        return true;
    }

    public boolean dive(double newAltitude) {
        acAltitude = newAltitude;
        fuelUsedLastManoeuvre = 0.0;
        return true;
    }

    // Fuel usage over a leg ...
    public boolean aircraftLeg(double speed, double distance) {
        double fuelUsage = 27.7; // Test data for now
        double fuelUsed;

        for (FuelRateData thisFuelData : fuelData) {
            if (thisFuelData.dataMatch(acAltitude, speed, acWeight)) {
                fuelUsage = thisFuelData.getFuelUsage();
            }
        }
        // System.out.println("Fuel usage " + fuelUsage);
        fuelUsed = fuelUsage * distance * 60.0 / speed;
        fuelUsedLastManoeuvre = fuelUsed;

        return true;
    }

    // Set up fuel usage data ...
    private void initialiseFuelUsage() {
    	int countData = 0;
    	
        try {
            File file = new File(systemProperties.getOdmFileName());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // FileWriter writeToFile = new FileWriter(file);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
                FuelRateData lineData = new FuelRateData(line);
                //Only add if correct
                if(lineData.getValidData()) {
                    fuelData.add(lineData);  
                    countData++;
                } else {
                    System.out.println("ERROR - fuel_data file corrupt");
                    System.exit(0);
                }
            }
            fileReader.close();
            //System.out.println("Contents of file:");
            //System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR - fuel_data file not found");
            System.exit(0);
        }
        
        if(countData != 180) {
            System.out.println("ERROR - fuel_data file missing entries");
            System.exit(0);
        }

    }
}
