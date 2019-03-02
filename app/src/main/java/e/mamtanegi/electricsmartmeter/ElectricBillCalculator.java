package e.mamtanegi.electricsmartmeter;

public class ElectricBillCalculator {
    private float prevreading,currentreading;
    private  int domesticprice=4;
    private int business=5;
private boolean domestic;
    private float totalbill;
    public ElectricBillCalculator(float prevreading, float currentreading, boolean domestic) {
        this.prevreading = prevreading;
        this.currentreading = currentreading;
       this.domestic=domestic;
    }


    public float calculatebill() {
        if (domestic == true) {
            return (currentreading - prevreading) * domesticprice;
        } else
            return (currentreading - prevreading) * business;

        }
    }