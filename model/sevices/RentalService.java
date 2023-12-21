package model.sevices;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerDay;
    private Double pricePErHour;
    private  TaxService taxService;

    public RentalService(Double pricePerDay, Double pricePErHour, TaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePErHour = pricePErHour;
        this.taxService = taxService;
    }

    public void processInVoice(CarRental carRental) {
       double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
       double hours = minutes / 60;

       double basicPayment;
       if (hours <= 12.0 ) {
           basicPayment = pricePErHour * Math.ceil(hours);

       }
       else {
           basicPayment = pricePerDay * Math.ceil(hours / 24);
       }

       double tax = taxService.tax(basicPayment);
       carRental.setInvoice(new Invoice(basicPayment,tax));


    }
}
