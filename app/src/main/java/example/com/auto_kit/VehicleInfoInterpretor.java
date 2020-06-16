package example.com.auto_kit;

public class VehicleInfoInterpretor{
    public Vehicle getVehicle(String vehicle_name) {
        if ("Activa".equals(vehicle_name))
            return new Activa();
        else
            return null;
    }
}
interface Vehicle{
    String getAverage();
    String getFuelCapacity();
}

class Activa implements Vehicle{
    public String getAverage(){
        return "50 km/l";
    }
    public String getFuelCapacity(){
        return "5 l";
    }
}


