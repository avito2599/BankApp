public class Customer {
    private final String firstName;
    private final String lastName;
    private final int id;
    private final int nif;
    private final Account account;

    public Customer(String firstName, String lastName, int id, int nif, Account account) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.id = id;
        this.nif = nif;
        this.account = account;
    }

    @Override
    public String toString(){
        return "\nInformação do cliente\n" +
                "Nome : " +firstName +"\n" +
                "Apelido: " +lastName +"\n" +
                "Número do CC: " +id +"\n" +
                "NIF: " +nif +"\n" +
                account;
    }

    public String basicInfo(){
        return "Nome : " +firstName +"\n" +
                "Apelido: " +lastName +"\n" +
                "Conta Nº: " +account.getAccountNumber();
    }

    Account getAccount(){
        return account;
    }
}
