public class Checking extends Account {
    private static String accountType = "Corrente";
    //constructor
    Checking(double initialDeposit){
        super();
        this.setBalance(initialDeposit);

    }
    @Override
    public String toString(){
        return "Tipo de conta: " +accountType +"\n" +
                "Número de conta: " + this.getAccountNumber() +"\n" +
                "Saldo:" + this.getBalance();
    }
}
