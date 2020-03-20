import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;



public class MakerCollection {
    /**
     *Метод для чтения файла и создании коллекции
     * @param file - путь до файла с коллекцией, данные хранятся в формате csv
     * @return humanBeingHashMap - коллекция с объектами
     */
    static HashMap<String,HumanBeing> makerHumanBeingColl(File file){
        HashMap<String, HumanBeing> humanBeingHashMap = new HashMap<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
            int symbol = inputStreamReader.read();
            String key = "";
            String word = "";
            int i = 0;
            while (symbol != -1) {
                if (symbol == 10) {
                    symbol = inputStreamReader.read();
                }
                HumanBeing humanBeing = new HumanBeing();
                Coordinates coordinates = new Coordinates();
                i = 0;
                while (symbol != -1 && symbol != 10) {
                    while (symbol != -1 && symbol != 10 && symbol != 44) {
                        if (symbol != 13) {
                            word = word + String.valueOf(Character.toChars(symbol));
                        }
                        symbol = inputStreamReader.read();
                    }
                    switch (i) {
                        case 0:
                            key = word;
                            i++;
                            break;
                        case 1:
                            humanBeing.setId(Long.parseLong(word));
                            i++;
                            break;
                        case 2:
                            humanBeing.setName(word);
                            i++;
                            break;
                        case 3:
                            coordinates.setX(Double.valueOf(word));
                            i++;
                            break;
                        case 4:
                            coordinates.setY(Long.parseLong(word));
                            humanBeing.setCoordinates(coordinates);
                            i++;
                            break;
                        case 5:
                            humanBeing.setCreationDate(formatter.parse(word));
                            i++;
                            break;
                        case 6:
                            humanBeing.setRealHero(Boolean.valueOf(word));
                            i++;
                            break;
                        case 7:
                            if (!word.equals("")) {
                                humanBeing.setHasToothpick(Boolean.parseBoolean(word));
                            }
                            i++;
                            break;
                        case 8:
                            if (!word.equals("")) {
                                humanBeing.setImpactSpeed(Integer.parseInt(word));
                            }
                            i++;
                            break;
                        case 9:
                            if (!word.equals("")) {
                                humanBeing.setMinutesOfWaiting(Double.valueOf(word));
                            }
                            i++;
                            break;
                        case 10:
                            humanBeing.setWeaponType(WeaponType.valueOf(word.toUpperCase()));
                            i++;
                            break;
                        case 11:
                            humanBeing.setMood(Mood.valueOf(word.toUpperCase()));
                            i++;
                            break;
                        case 12:
                            if (!word.equals("")) {
                                humanBeing.setCar(new Car(word));
                            }
                            i++;
                            break;
                        case 13:
                            throw new Exception();
                    }
                    if (symbol != 10) {
                        symbol = inputStreamReader.read();
                    }
                    word = "";
                }
                if (humanBeing.getName() == null) {
                    throw new Exception();
                }
                humanBeingHashMap.put(key, humanBeing);
                key = "";
            }
            return humanBeingHashMap;
        }catch (Exception e){
            System.err.println("Файл содержит отшибку");
            System.err.println("Создание коллекции невозможно");
            System.err.println("В объекте не может быть больше 13 элиментов");
            System.exit(-1);
        }
        return null;
    }
}
