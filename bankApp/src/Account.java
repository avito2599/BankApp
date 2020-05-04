import java.util.Date;

public class Account {
    private double balance;
    private int accountNumber;
    private static int NumberAccount = 1000000; // sempre que criarmos uma conta adicionamos uma unidade
    Account(){
        accountNumber = NumberAccount++;  //adição de uma unidade
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void withdrawal(double amount){
        if(amount > balance) {
            System.out.println("Saldo insuficiente");
            if (amount < 10)
                System.out.println("O montante mínimo a levantar são 10 euros");
        }
        else
            System.out.println("Fez um levantamento de :" +amount );
        balance -= amount;
        System.out.println("O seu saldo é de : " +balance);
        Date currentDate = new Date();
        System.out.println("Date e hora: " +currentDate);
    }
    public void deposit(double amount){
        if(amount < 5)
            System.out.println("Tem de fazer um depósito mínimo de 5 euros");
        else
            System.out.println("Fez um depósito de : " + amount);
        balance += amount;
        System.out.println("O seu saldo é de : " +balance);
        Date currentDate = new Date();
        System.out.println("Date e hora: " +currentDate);
    }
    public void loan(double amount, int years, double anual_tax){
        if(years < 1 || years > 20){
            System.out.println("O máximo de anos tem de ser 20");
        }
        double month_tax = (double)anual_tax/1200;
        double month_payment = (amount * month_tax) / (1-1 / Math.pow(1+month_tax, years*12));
        double total_payment = month_payment * years * 12;
        System.out.println("O pagamento mensal é  €" + (int)month_payment);
        System.out.println("O pagamento total é €" + (int)total_payment + " em " + years + " ano(s)");
        balance += amount;
        System.out.println("O seu saldo atual é de: " +balance);
        Date currentDate = new Date();
        System.out.println("Date e hora: " +currentDate);
    }
    public void transfer(double amount, int beneficiary){
        if(amount < 5)
            System.out.println("Não pode fazer uma transferência inferior a 5 euros");
        else
            System.out.println("Fez uma transferência de: " +amount);
            System.out.println("Para: " +beneficiary);
            balance -= amount;
            System.out.println("O seu saldo atual é de: " +balance);
            Date currentDate = new Date();
            System.out.println("Date e hora: " +currentDate);
    }

}
