public class Savings extends Account {
    private static String accountType = "Poupança";
    //constructor
    Savings(double initialDeposit){
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
