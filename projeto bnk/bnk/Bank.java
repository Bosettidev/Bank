import java.util.Scanner;
import java.util.ArrayList;

public class Bank {
    public static void main(String[] args) { // -- clean --
        Scanner scanner = new Scanner(System.in);
        String username = NameUser.cadasName(scanner);
        String cpfValido = CpfUser.cadasCpfUser(scanner);
        String teleValido = TelefoneUser.cadasTeleUser(scanner);

        System.out.println("\nUsuário cadastrado com sucesso!");
        System.out.println("Nome: " + username);
        System.out.println("CPF: " + cpfValido);
        System.out.println("Telefone: " + teleValido);

        String unicoCadas = ChoseCadas.choseCadasUnico(scanner, cpfValido, teleValido);
        String senhaString = senhaUser.senha(scanner);

        System.out.println("Cadastro realizado com sucesso! Seu número único é: " + unicoCadas+"\n"+senhaString);
        long depositoUser = deposit.deposito(scanner,senhaString,cadasUnico);
        scanner.close();
    }
}

class NameUser {
    public static String cadasName(Scanner scanner) {
        while (true) {
            System.out.print("Digite seu nome (mais de 4 caracteres): ");
            String nomeValidar = scanner.nextLine();

            if (nomeValidar.length() > 3) {
                System.out.println("Nome válido: " + nomeValidar);
                return nomeValidar;
            } else {
                System.out.println("Nome inválido. Tente novamente.");
            }
        }
    }
}
class CpfUser {
    public static String cadasCpfUser(Scanner scanner) {
        while (true) {
            System.out.print("Digite seu CPF (11 dígitos): ");
            String cpfUserValidar = scanner.nextLine();

            if (cpfUserValidar.length() == 11 && cpfUserValidar.matches("\\d+")) {
                System.out.println("Número válido: " + cpfUserValidar);
                return cpfUserValidar;
            } else {
                System.out.println("CPF inválido. Tente novamente.");
            }
        }
    }
}
class TelefoneUser {
    public static String cadasTeleUser(Scanner scanner) {
        while (true) {
            System.out.print("Digite seu telefone (10 dígitos): ");
            String teleUserValidar = scanner.nextLine();

            if (teleUserValidar.length() == 10 && teleUserValidar.matches("\\d+")) {
                System.out.println("Número válido: " + teleUserValidar);
                return teleUserValidar;
            } else {
                System.out.println("Telefone inválido. Tente novamente.");
            }
        }
    }
}
class ChoseCadas {
    private static ArrayList<Long> userCadastrado = new ArrayList<>();
    public static String choseCadasUnico(Scanner scanner, String cpfUser, String telefoneUser) {
        while (true) {
            System.out.print("Escolha qual será seu número único de cadastro (cpf ou telefone): ");
            String cadasUnico = scanner.nextLine();
            try {
                if (cadasUnico.equals("cpf")) {
                    long cpfLong = Long.parseLong(cpfUser);
                    userCadastrado.add(cpfLong);
                    System.out.println("Seu cadastro único é seu CPF.");
                    return cpfUser;
                } else if (cadasUnico.equals("telefone")) {
                    long telefoneLong = Long.parseLong(telefoneUser);
                    userCadastrado.add(telefoneLong);
                    System.out.println("Seu cadastro único é seu telefone.");
                    return telefoneUser;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro. Tente novamente.");
            }
        }
    }
    public static boolean verificaCadastro(long cadastro) {
        return userCadastrado.contains(cadastro);
    }
}

class senhaUser {
    public static String senha (Scanner scanner){
        while (true){
            System.out.print("qual vai ser a sua senha (mais de 5 digitos): ");
            String userSenha = scanner.nextLine();
            if (userSenha.length() > 4) {
                System.out.println("sua senha é "+userSenha+"?");
                String confirmSenha = scanner.nextLine();
                if (confirmSenha.equals("sim")){
                    return confirmSenha;
                }
            }else {
                System.out.println("você não digitou certo tente de novo");
            }
        }
    }
}
class deposit {
    public void deposito(Scanner scanner, String senhaUser, String cadasUnico) {
        System.out.print("Qual será a quantia a ser depositada: ");
        long quantia = scanner.nextLong();
        scanner.nextLine();
        try {
            long cadastroLong = Long.parseLong(cadasUnico);
            if (ChoseCadas.verificaCadastro(cadastroLong)) {
                System.out.println("Depósito de " + quantia + " realizado com sucesso!");
            } else {
                System.out.println("Impossível realizar o depósito. Cadastro não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao processar o número de cadastro.");
        }
    }
}
