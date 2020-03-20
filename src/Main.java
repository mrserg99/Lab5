import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean isExit = false;
            String path = args[0];
            File file = new File(path);
//        File file = new File("D:\\Stud\\Прога\\lab5\\Lab5\\src\\test.csv");
            if (Commands.checkFile(file)) {
                System.exit(-1);
            }
            HashMap<String, HumanBeing> humanBeingHashMap = MakerCollection.makerHumanBeingColl(file);
            while (!isExit) {
                System.out.println("\n" + "Введите команду");
                System.out.println("Чтобы увидеть список команд наберите help");
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    isExit = true;
                } else {
                    Commands.takerCommands(command, humanBeingHashMap, file);
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Необходимо передать файл с обЪектами");
        }
    }
}