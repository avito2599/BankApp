import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Menu {
    Scanner input = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("-------------------------");
        System.out.println("| Bem-vindo ao A.I.R BANK |");
        System.out.println("-------------------------");
    }

    private void printMenu() {
        System.out.println("Que operação deseja efetuar?");
        System.out.println("1) Criar nova conta");
        System.out.println("2) Efetuar depósito");
        System.out.println("3) Efetuar levantamento");
        System.out.println("4) Dados");
        System.out.println("5) Empréstimo");
        System.out.println("6) Transferência");
        System.out.println("0) Sair");

    }

    private int getInput() {
        int choice = -1;
        System.out.println("Insira uma operação");
        do {
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números");
            }
            if (choice < 0 || choice > 6) {
                System.out.println("Escolha errada. Por favor tente novamente");
            }
        } while (choice < 0 || choice > 6);
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Obrigado por usar a nossa aplicação");
                System.exit(0);
                break;
            case 1:
                createAccount();

                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                makeWithdrawal();
                break;
            case 4:
                listBalances();
                break;
            case 5:
                makeloan();
                break;
            case 6:
                makeTransfer();
                break;
            default:
                System.out.println("Ocorreu um erro desconhecido!");
        }
    }

    private void createAccount() {
        String firstName, lastName, accountType = "";
        int id, nif;
        double initialDeposit = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Escolha entre a 'conta corrente' ou 'conta poupança'");
            accountType = input.nextLine();
            if (accountType.equalsIgnoreCase("corrente") || accountType.equalsIgnoreCase("poupança")) {
                valid = true;
            } else {
                System.out.println("Inválido");
            }
        }
        System.out.println("Nome");
        firstName = input.nextLine();
        System.out.println("Apelido");
        lastName = input.nextLine();
        System.out.println("Número do cartão de cidadão");
        id = input.nextInt();
        System.out.println("Número de identificação fiscal");
        nif = input.nextInt();

        valid = false;
        while (!valid) {
            try {
                initialDeposit = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("deposito tem de ser em números");
            }
            if (accountType.equalsIgnoreCase("corrente")) {
                if (initialDeposit < 100)
                    System.out.println("Tem de fazer um depósito mínimo de 100 euros para abrir a conta");
                else
                    valid = true;
            } else if (accountType.equalsIgnoreCase("poupança")) {
                if (initialDeposit < 50)
                    System.out.println("Tem de fazer um depósito mínimo de 50 euros para abrir a conta");
                else
                    valid = true;
            }
        }

        Account account;
        if (accountType.equalsIgnoreCase("corrente"))
            account = new Checking(initialDeposit);
        else
            account = new Savings(initialDeposit);
        // criar um cliente
        Customer customer = new Customer(firstName, lastName, id, nif, account);
        bank.addCustomer(customer);
    }

    private void makeDeposit() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println("Quanto pretende depositar?");
            double amount = 0;
            try {
                amount = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    private void makeWithdrawal() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println("Quanto pretende levantar?");
            double amount = 0;
            try {
                amount = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            bank.getCustomer(account).getAccount().withdrawal(amount);
        }
    }

    private void makeloan() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println("Quanto pretende pedir emprestado?");
            System.out.println("Em quantos anos deseja pagar?");
            System.out.println("Insira a percentagem anual");
            double amount = 0;
            int years = 0;
            double anual_tax = 0;
            try {
                amount = Double.parseDouble(input.nextLine());
                years= Integer.parseInt(input.nextLine());
                anual_tax = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
                years = 0;
                anual_tax = 0;
            }
            bank.getCustomer(account).getAccount().loan(amount, years, anual_tax);
        }
    }
    public void makeTransfer(){
        int account = selectAccount();
        if (account >= 0) {
            System.out.println("Quanto pretende enviar?");
            double amount = 0;
            System.out.println("Número de conta que pretende enviar");
            int beneficiaryAccountNumber = 0;
            try {
                amount = Double.parseDouble(input.nextLine());
                beneficiaryAccountNumber = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
                beneficiaryAccountNumber = 0;
            }
            bank.getCustomer(account).getAccount().transfer(amount, beneficiaryAccountNumber);
        }
    }

    private void listBalances() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }

    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <= 0) {
            System.out.println("o banco ainda não possui clientes");
            return -1;
        }
        System.out.println("Escolha uma conta");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ") " + customers.get(i).basicInfo()); //Adicionamos um para que incie a 1 para o utilizador
        }
        int account = 0;
        System.out.println("Por favor insira a sua escolha:");
        try {
            account = Integer.parseInt(input.nextLine()) - 1; // precisa de voltar a zero para ser lido corretamente pela máquina
        } catch (NumberFormatException e) {
            account = -1;
        }

        // corrige o bug de seleção de conta ex(seleção e conta inexistente)
        if (account < 0 || account > customers.size()) {
            System.out.println("Escolheu uma conta inválida");   // ou inexistente
            account = -1;
        }
        return account;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }

}
