package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.sevices.BrazilTaxService;
import model.sevices.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start,finish, new Vehicle(carModel));

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia ");
        double pricePerDay = sc.nextDouble();

        RentalService rs = new RentalService(pricePerDay,pricePerHour,new BrazilTaxService());
        rs.processInVoice(cr);

        System.out.println("Fatura:");
        System.out.println("Pagamento básico: R$" + cr.getInvoice().getBasicPayment());
        System.out.println("Imposto: R$" + cr.getInvoice().getTax());
        System.out.println("Pagamento Total: R$" + cr.getInvoice().getTotalpayment());





        sc.close();

    }
}
