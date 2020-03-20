import java.io.*;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Клдасс содержащий комманды и дающий к ним доступ
 */
public class Commands {
    private static HashSet<File> readsFiles = new HashSet<>();

    private static void help(){
        System.out.println("help : вывести справку по доступным командам"); //+
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"); //+
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении"); //+
        System.out.println("insert key {element} : добавить новый элемент с заданным ключом"); //+
        System.out.println("insert : добавить новый элемент с заданным ключом");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");//+
        System.out.println("update : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_key key : удалить элемент из коллекции по его ключу");//+
        System.out.println("clear : очистить коллекцию");//+
        System.out.println("save : сохранить коллекцию в файл");//+
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла." +
                " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме."); //+
        System.out.println("exit : завершить программу (без сохранения в файл)"); //+
        System.out.println("replace_if_greater key {name} : заменить значение поля name по ключу, если новое значение больше старого");//+
        System.out.println("replace_if_lowe key {name} : заменить значение поля name по ключу, если новое значение меньше старого");//+
        System.out.println("remove_lower_key key : удалить из коллекции все элементы, ключ которых меньше, чем заданный");//+
        System.out.println("average_of_impact_speed : вывести среднее значение поля impactSpeed для всех элементов коллекции");//+
        System.out.println("filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку");//+
        System.out.println("print_ascending : вывести элементы коллекции в порядке возрастания поля id");//+
        System.out.println("Ожидается что поле {element} будет заполнено по такому шаблону:");
        System.out.println("name(слово)(не может быть пустым),realHero(true/false),hasToothpick(true/false),impactSpeed(целое число),minutesOfWaiting(дробное число),weaponType(enum)(не может быть пустым),mood(enum)(не может быть пустым)" );
        System.out.println("После чего следуйте инструкциям");
        System.out.println("Или");
        System.out.println("name(слово)(не может быть пустым),coordX(дробное число)(больше -672.0),coordY(целое число)(меньше 151),realHero(true/false),hasToothpick(true/false),impactSpeed(целое число),minutesOfWaiting(дробное число)," +
                "weaponType(enum)(не может быть пустым),mood(enum)(не может быть пустым),carName(слово)");
        System.out.println("Поля отделяются запятыми");
        System.out.println("Weapon Type: HAMMER, PISTOL, SHOTGUN, RIFLE, KNIFE");
        System.out.println("Mood: SADNESS, SORROW, RAGE, FRENZY");
    }
    private static void info(HashMap<String,HumanBeing> hashMap, Date date){
        System.out.println("Тип: " + hashMap.getClass());
        System.out.println("Дата инициализации: " + date);
        System.out.println("Колличество элементов  в коллекции: " + hashMap.size());
    }
    private static void show(HashMap<String,HumanBeing> hashMap){
        if(hashMap.size() == 0) {
            System.out.println("Коллекция пуста");
        }else {
            printColl(hashMap.toString());
        }
    }
    private static void insert(HashMap<String,HumanBeing> hashMap,String[] args,String key){
        Scanner scanner = new Scanner(System.in);
        try {
            if(args.length>=6) {
                System.out.println("Введите координату Y(целое число от -бесконечности до 151) и нажмите Enter");
                String y = scanner.nextLine();
                System.out.println("Введите координату X(дробное число от -672 до бесконечности) и нажмите Enter");
                String x = scanner.nextLine();
                System.out.println("Введите carName(слово или пустая строка) и нажмите Enter");
                String carName = scanner.nextLine();
                HumanBeing humanBeing = new HumanBeing(args, x, y, carName);
                humanBeing.setId(hashMap.hashCode());
                hashMap.put(key, humanBeing);
            }else {
                HumanBeing humanBeing = new HumanBeing(args);
                humanBeing.setId(hashMap.hashCode());
                hashMap.put(key, humanBeing);
            }
            System.out.println("Элемент добавлен");
        }catch (Exception e){
            System.err.println("Элемент не добавлен");
        }
    }
    private static void insert(HashMap<String,HumanBeing> hashMap){
        Scanner scanner = new Scanner(System.in);
        HumanBeing humanBeing = HumanBeing.scannerObj(scanner);
        System.out.println("Введите ключ");
        humanBeing.setId(humanBeing.hashCode());
        hashMap.put(scanner.nextLine(),humanBeing);
    }
    private static void update(HashMap<String,HumanBeing> hashMap,String[] args,long id){
        boolean isExist = true;
        Scanner scanner = new Scanner(System.in);
        try {
            Object[] objects = hashMap.keySet().toArray();
            for (int i = 0; i < objects.length; i++) {
                String key = String.valueOf(objects[i]);
                if (id == hashMap.get(key).getId()) {
                    if(args.length>=6) {
                        System.out.println("Введите координату Y(целое число от -бесконечности до 151) и нажмите Enter");
                        String y = scanner.nextLine();
                        System.out.println("Введите координату X(дробное число от -672 до бесконечности) и нажмите Enter");
                        String x = scanner.nextLine();
                        System.out.println("Введите carName(слово или пустая строка) и нажмите Enter");
                        String carName = scanner.nextLine();
                        HumanBeing humanBeing = new HumanBeing(args, x, y, carName);
                        humanBeing.setId(hashMap.hashCode());
                        hashMap.put(key, humanBeing);
                    }else {
                        HumanBeing humanBeing = new HumanBeing(args);
                        humanBeing.setId(hashMap.hashCode());
                        hashMap.put(key, humanBeing);
                    }
                    i = objects.length + 1;
                    System.out.println("Элемент обновлен");
                    isExist = false;
                }
            }
            if(isExist){
                System.err.println("Элемент с таким id не найден");
            }
        }catch (Exception e){
            System.err.println("Элемент не обнволен");
        }
    }
    private static void update(HashMap<String,HumanBeing> hashMap){
        Scanner scanner = new Scanner(System.in);
        boolean isExist = true;
        System.out.println("Введите id");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            Object[] objects = hashMap.keySet().toArray();
            for (int i = 0; i < objects.length; i++) {
                String key = String.valueOf(objects[i]);
                if (id == hashMap.get(key).getId()) {
                    HumanBeing humanBeing = HumanBeing.scannerObj(scanner);
                    hashMap.put(key,humanBeing);
                    i = objects.length + 1;
                    System.out.println("Элемент обновлен");
                    isExist = false;
                }
            }
            if(isExist){
                System.err.println("Элемент с таким id не найден");
            }
        }catch (Exception e){
            System.err.println("Элемент не обнволен");
        }
    }
    private static void remove_key(HashMap<String,HumanBeing> hashMap,String key){
        try {
            if (hashMap.containsKey(key)) {
                hashMap.remove(key);
                System.out.println("Элемент удален");
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Элемент не удален");
            System.err.println("Такого ключа не существует");
        }
    }
    private static void clear(HashMap<String,HumanBeing> hashMap){
        hashMap.clear();
        System.out.println("Коллекция очищена");
    }
    private static void replace_if_greater(HashMap<String,HumanBeing> hashMap,String name,String key){
        if(name.compareTo(hashMap.get(key).getName()) < 0){
            try {
                hashMap.get(key).setName(name);
            }catch (Exception e){
                System.err.println("Поле введено некоректно");
            }
            System.out.println("Имя обновлено");
        }else {
            System.err.println("Имя не обновлено");
        }
    }
    private static void replace_if_lowe(HashMap<String,HumanBeing> hashMap,String name,String key){
        if(name.compareTo(hashMap.get(key).getName()) > 0){
            try {
                hashMap.get(key).setName(name);
            }catch (Exception e){
                System.err.println("Значение некоректно");
            }
            System.out.println("Имя обновлено");
        }else {
            System.err.println("Имя не обновлено");
        }
    }
    private static void remove_lower_key(HashMap<String,HumanBeing> hashMap,String key){
        int k = 0;
        Object[] keys = hashMap.keySet().toArray();
        for (Object o : keys) {
            if (key.compareTo(String.valueOf(o)) < 0) {
                hashMap.remove(String.valueOf(o));
                k++;
            }
        }
        System.out.println("Удаленно " + k + " элемента/ов");
    }
    private static void average_of_impact_speed(HashMap<String,HumanBeing> hashMap){
        int result = 0;
        Object[] keys = hashMap.keySet().toArray();
        for (Object key : keys) {
            result = result + hashMap.get(key).getImpactSpeed();
        }
        System.out.println("Среднее значение поля impactSpeed для " + hashMap.size() + " элементов = " + result/hashMap.size());
    }
    private static void filter_contains_name(HashMap<String,HumanBeing> hashMap,String name){
        Object[] keys = hashMap.keySet().toArray();
        for (Object key : keys) {
            if (hashMap.get(key).getName().contains(name)) {
                System.out.println(hashMap.get(key).toString());
            }
        }
    }
    private static void print_ascending(HashMap<String,HumanBeing> hashMap){
        if(hashMap.size() == 0) {
            System.out.println("Коллекция пуста");
        }else {
            List<HumanBeing> idForSort = new ArrayList<>(hashMap.values());
            Collections.sort(idForSort);
            printColl(idForSort.toString());
        }
    }

    /**
     * Метод для сохраниения коллекции в файл
     * @param hashMap
     * @param file
     * @throws IOException
     */
    private static void save(HashMap<String,HumanBeing> hashMap, File file) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        Object[] keys = hashMap.keySet().toArray();
        Object[] values = hashMap.values().toArray();
        Format format = new SimpleDateFormat("yyyy.MM.dd");
        String nnull = "";
        String str;
        for (int i = 0;i<keys.length;i++) {
            bufferedOutputStream.write(((String) keys[i]).getBytes());
            bufferedOutputStream.write(44);

            HumanBeing humanBeing = (HumanBeing) values[i];
            str = String.valueOf(humanBeing.getId());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            bufferedOutputStream.write(humanBeing.getName().getBytes());
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getCoordinates().getX());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getCoordinates().getY());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            str = format.format(humanBeing.getCreationDate());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getRealHero());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getHasToothpick());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getImpactSpeed());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            if(humanBeing.getMinutesOfWaiting() != null) {
                str = String.valueOf(humanBeing.getMinutesOfWaiting());
                bufferedOutputStream.write(str.getBytes());
            }else {
                bufferedOutputStream.write(nnull.getBytes());
            }
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getWeaponType());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.write(44);

            str = String.valueOf(humanBeing.getMood());
            bufferedOutputStream.write(str.getBytes());

            if(humanBeing.getCar().getName() != null){
                bufferedOutputStream.write(44);
                bufferedOutputStream.write(humanBeing.getCar().getName().getBytes());
            }
            if(i != keys.length-1) {
                bufferedOutputStream.write(10);
            }
        }
        bufferedOutputStream.close();
    }
    private static void execute_script(HashMap<String,HumanBeing> hashMap, File file) throws IOException{
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
        int symbol = inputStreamReader.read();
        String word = "";
        while (symbol != -1){
            if(symbol == 10 ){
                symbol = inputStreamReader.read();
            }
            while (symbol != -1 && symbol != 10){
                if (symbol != 13) {
                    word = word + String.valueOf(Character.toChars(symbol));
                }
                symbol = inputStreamReader.read();
            }

            takerCommands(word,hashMap,file);
            word = "";
        }
    }

    private static void printColl(String s) {
        String[] arrayInfoAboutColl = s.substring(1, s.length() - 1).split(",");
        for (String i : arrayInfoAboutColl)
            if (i.startsWith(" ")) {
                System.out.println(i.substring(1));
            } else {
                System.out.println(i);
            }
        }

    public static boolean checkFile(File file){
        if(!file.exists()){
            System.err.println("Такого файла не существует");
            System.err.println(file);
            return true;
        }
        if(!file.canRead()){
            System.err.println("Нет прав на чтение файла");
            System.err.println(file);
            return true;
        }
        if(!file.canWrite()){
            System.err.println("Нет прав на запись в файл");
            System.err.println(file);
            return true;
        }
        return false;
    }
    /**
     * Метод дающий доступ к коммандам
     * @param command - выполняемая команда
     * @param humanBeingHashMap - аргументы команды если есть
     * @param file - файл в котором лежит коллекция
     */
    public static void takerCommands(String command,HashMap<String, HumanBeing> humanBeingHashMap,File file){
        Date date = new Date();
        String[] commandAndArgs = command.split(" ");
        switch (commandAndArgs[0]){
            case "help":
                help();
                break;
            case "info":
                info(humanBeingHashMap,date);
                break;
            case "show":
                show(humanBeingHashMap);
                break;
            case "insert":
                try {
                    String[] arg = commandAndArgs[2].split(",");
                    String key = commandAndArgs[1];
                    insert(humanBeingHashMap, arg, key);
                }catch (ArrayIndexOutOfBoundsException e){
                    insert(humanBeingHashMap);
                }
                break;
            case "update":
                try {
                    String[] arg = commandAndArgs[2].split(",");
                    long id = Long.parseLong(commandAndArgs[1]);
                    update(humanBeingHashMap,arg,id);
                }catch (ArrayIndexOutOfBoundsException e){
                    update(humanBeingHashMap);
                }catch (NumberFormatException e){
                    System.err.println("Комманда введена верено");
                    System.err.println("Ожидается число в поле id");
                }
                break;
            case "remove_key":
                try {
                    String key = commandAndArgs[1];
                    remove_key(humanBeingHashMap,key);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Комманда введена верено");
                    System.err.println("Отсутствует ключ");
                }
                break;
            case "clear":
                clear(humanBeingHashMap);
                break;
            case "save":
                try {
                    save(humanBeingHashMap, file);
                }catch (IOException e){
                    System.err.println("Файл не найден");
                    System.err.println("Коллекция не сохранена");
                }
                break;
            case "execute_script":
                try {
                    File fileForScript  = new File(commandAndArgs[1]);
                    if(!checkFile(fileForScript)) {
                        if(!readsFiles.contains(fileForScript)) {
                            readsFiles.add(fileForScript);
                            execute_script(humanBeingHashMap, fileForScript);
                        }else {
                            readsFiles.clear();
                            throw new Exception();
                        }
                    }
                }catch (IOException e){
                    System.err.println("Не могу получить доступ к файлу");
                }catch (Exception e){
                    System.err.println("Вы пытаетесь выполнить скрипт который ещё выпоняется");
                    System.err.println("Это приведет к зацикливанию");
                }
                break;
            case "replace_if_greater":
                try {
                    String key = commandAndArgs[1];
                    String name = commandAndArgs[2];
                    replace_if_greater(humanBeingHashMap,name,key);
                }catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Комманда введена верено");
                    System.err.println("Отсутствует ключ");
                }
                break;
            case "replace_if_lowe":
                try {
                    String key = commandAndArgs[1];
                    String name = commandAndArgs[2];
                    replace_if_lowe(humanBeingHashMap,name,key);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Комманда введена верено");
                    System.err.println("Отсутствует ключ");
                }
                break;
            case "remove_lower_key":
                try {
                    String key = commandAndArgs[1];
                    remove_lower_key(humanBeingHashMap,key);
                }catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Комманда введена верено");
                    System.err.println("Отсутствует ключ");
                }
                break;
            case "average_of_impact_speed":
                average_of_impact_speed(humanBeingHashMap);
                break;
            case "filter_contains_name":
                try {
                    String name = commandAndArgs[1];
                    filter_contains_name(humanBeingHashMap,name);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Комманда введена верено");
                    System.err.println("Отсутствует аргумент");
                }
                break;
            case "print_ascending":
                print_ascending(humanBeingHashMap);
                break;
            default:
                System.err.println("Некоректная команда");
                System.err.println("Чтобы увидеть список доступных команд наберите help");
        }
    }
}
