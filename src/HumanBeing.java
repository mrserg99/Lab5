import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class HumanBeing implements Comparable<HumanBeing>{
    private long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Boolean realHero;
    private boolean hasToothpick;
    private int impactSpeed;
    private Double minutesOfWaiting;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    /**
     * Конструктор нового метода
     */
    HumanBeing(){
        super();
    }

    /**
     * @param args - массив данных
     * @param x - координата x
     * @param y - координата y
     * @param carName - имя переменной
     * @throws Exception - выпадает при некоректных значениях
     */
    HumanBeing(String[] args,String x,String y,String carName) throws Exception{
        setName(args[0]);
        setCoordinates(new Coordinates(Double.valueOf(x), Long.parseLong(y)));
        setRealHero(Boolean.valueOf(args[1]));
        if(!(args[2].equals(""))) {
            setHasToothpick(Boolean.parseBoolean(args[2]));
        }else {
            setHasToothpick(false);
        }
        if(!(args[3].equals(""))){
            setImpactSpeed(Integer.parseInt(args[3]));
        }else {
            setImpactSpeed(0);
        }
        if(!(args[4].equals(""))){
            setMinutesOfWaiting(Double.valueOf(args[4]));
        }else{
            setMinutesOfWaiting(null);
        }
        setWeaponType(WeaponType.valueOf(args[5].toUpperCase()));
        setMood(Mood.valueOf(args[6].toUpperCase()));
        try {
            setCar(new Car(carName));
        }catch (IndexOutOfBoundsException e){
            setCar(null);
        }
        setCreationDate();
    }
    HumanBeing(String[] args) throws Exception{
        setName(args[0]);
        setCoordinates(new Coordinates(Double.parseDouble(args[1]),Long.parseLong(args[2])));
        setCreationDate(new Date());
        setRealHero(Boolean.valueOf(args[3]));
        setHasToothpick(Boolean.parseBoolean(args[4]));
        setImpactSpeed(Integer.parseInt(args[5]));
        setMinutesOfWaiting(Double.parseDouble(args[6]));
        setWeaponType(WeaponType.valueOf(args[7]));
        setMood(Mood.valueOf(args[8]));
        setCar(new Car(args[9]));
    }
    HumanBeing(String name,Coordinates coordinates,Boolean realHero,boolean hasToothpick,int impactSpeed,
               Double minutesOfWaiting,WeaponType weaponType,Mood mood,Car car) throws Exception{
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(new Date());
        setRealHero(realHero);
        setHasToothpick(hasToothpick);
        setImpactSpeed(impactSpeed);
        setMinutesOfWaiting(minutesOfWaiting);
        setWeaponType(weaponType);
        setMood(mood);
        setCar(car);
    }


    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public Boolean getRealHero() {
        return realHero;
    }
    public boolean getHasToothpick(){
        return hasToothpick;
    }
    public int getImpactSpeed() {
        return impactSpeed;
    }
    public Double getMinutesOfWaiting() {
        return minutesOfWaiting;
    }
    public WeaponType getWeaponType() {
        return weaponType;
    }
    public Mood getMood() {
        return mood;
    }
    public Car getCar() {
        return car;
    }

    public void setId(long id){
//        try {
//            if (id > 0) {
                this.id = id;
//            } else {
//                throw new Exception();
//            }
//        }catch (Exception e){
//            System.err.println("Вы ввели некоретное значение" );
//        }
    } // Доработать ошибки(добавил обработку)
    public void setName(String name) throws Exception{
        try {
            if(!(name.equals(null)) && !(name.equals(""))) {
                this.name = name;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Нельзя оставлять строчку пустой");
            throw new Exception();
        }
    } // Доработать ошибки(добавил обработку)
    public void setCoordinates(Coordinates coordinates) throws Exception{
        try {
            if(!(coordinates.equals(null))) {
                this.coordinates = coordinates;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Нельзя оставлять поле пустым");
            throw new Exception();
        }
    } // Доработать ошибки(добавил обработку)
    public void setCreationDate(){
        creationDate = new Date();
    }
    public void setCreationDate(Date parse) {
        creationDate = parse;
    }
    public void setRealHero(Boolean realHero) throws Exception{
        try {
            if(!(realHero.equals(""))) {
                this.realHero = realHero;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Нельзя оставлять поле пустым");
            throw new Exception();
        }
    } // Доработать ошибки(добавил обработку)
    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }
    public void setImpactSpeed(int impactSpeed) {
        this.impactSpeed = impactSpeed;
    }
    public void setMinutesOfWaiting(Double minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }
    public void setWeaponType(WeaponType weaponType) throws Exception{
        try {
            if(!(weaponType.equals(null))) {
                this.weaponType = weaponType;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Нельзя оставлять поле пустым");
            throw new Exception();
        }
    } // Доработать ошибки(добавил обработку)

    /**
     *
     * @param mood - возвращает enum
     * @throws Exception - если объект введен некоректно
     */
    public void setMood(Mood mood) throws Exception{
        try {
            if(mood != null){
                this.mood = mood;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Нельзя оставлять поле пустым");
            throw new Exception();
        }
    }// Доработать ошибки

    /**
     *
     * @param car возвращает поле name объекта класса Car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    public static HumanBeing scannerObj(Scanner scanner){
        HumanBeing humanBeing = new HumanBeing();
        Coordinates coordinates = new Coordinates();
        Car car = new Car();
        humanBeing.scannerName(scanner,humanBeing);
        humanBeing.scannerCoordinatesY(scanner,coordinates);
        humanBeing.scannerCoordinatesX(scanner,coordinates);
        try {
            humanBeing.setCoordinates(coordinates);
        }catch (Exception e){

        }
        humanBeing.scannerRealHero(scanner,humanBeing);
        humanBeing.scannerHasToothpick(scanner,humanBeing);
        humanBeing.scannerImpactSpeed(scanner,humanBeing);
        humanBeing.scannerMinutesOfWaiting(scanner,humanBeing);
        humanBeing.scannerWeaponType(scanner,humanBeing);
        humanBeing.scannerMood(scanner,humanBeing);
        humanBeing.scannerCarName(scanner,car);
        humanBeing.setCar(car);
        humanBeing.setCreationDate();
        return humanBeing;
    }

    private void scannerName(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Введите имя");
            humanBeing.setName(scanner.nextLine());
        }catch (Exception e){
            scannerName(scanner,humanBeing);
        }
    }
    private void scannerCoordinatesY(Scanner scanner,Coordinates coordinates){
        try {
            System.out.println("Введите координату Y");
            coordinates.setY(Long.parseLong(scanner.nextLine()));
        }catch (Exception e){
            scannerCoordinatesY(scanner,coordinates);

        }
    }
    private void scannerCoordinatesX(Scanner scanner,Coordinates coordinates){
        try {
            System.out.println("Введите координату X");
            coordinates.setX(Double.parseDouble(scanner.nextLine()));
        }catch (Exception e){
            scannerCoordinatesX(scanner,coordinates);
        }
    }
    private void scannerRealHero(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Вы настоящий герой(true/false)");
            humanBeing.setRealHero(Boolean.valueOf(scanner.nextLine()));
        }catch (Exception e){
            scannerRealHero(scanner,humanBeing);
        }
    }
    private void scannerHasToothpick(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Введите hasToothpick(true/false)");
            humanBeing.setHasToothpick(Boolean.parseBoolean(scanner.nextLine()));
        } catch (Exception e){
            scannerHasToothpick(scanner,humanBeing);
        }
    }
    private void scannerImpactSpeed(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Введите impactSpeed(число)");
            humanBeing.setImpactSpeed(Integer.parseInt(scanner.nextLine()));
        } catch (Exception e){
            scannerImpactSpeed(scanner,humanBeing);
        }
    }
    private void scannerMinutesOfWaiting(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Введите minutesOfWaiting(число)");
            humanBeing.setMinutesOfWaiting(Double.parseDouble(scanner.nextLine()));
        } catch (Exception e){
            scannerMinutesOfWaiting(scanner,humanBeing);
        }
    }
    private void scannerWeaponType(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Введите weaponType(HAMMER, PISTOL, SHOTGUN, RIFLE, KNIFE)");
            humanBeing.setWeaponType(WeaponType.valueOf(scanner.nextLine().toUpperCase()));
        } catch (Exception e){
            scannerWeaponType(scanner,humanBeing);
        }
    }
    private void scannerMood(Scanner scanner,HumanBeing humanBeing){
        try {
            System.out.println("Введите Mood( SADNESS, SORROW, RAGE, FRENZY)");
            humanBeing.setMood(Mood.valueOf(scanner.nextLine().toUpperCase()));
        } catch (Exception e){
            scannerMood(scanner,humanBeing);
        }
    }
    private void scannerCarName(Scanner scanner,Car car){
        try {
            System.out.println("Введите carName");
            car.setName(scanner.nextLine());
        } catch (Exception e){
            scannerCarName(scanner,car);
        }
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        HumanBeing guest = (HumanBeing) obj;
        return id == guest.id && (name == guest.name || (name != null && name.equals(guest.name)))
                && (coordinates == guest.coordinates || (coordinates != null && coordinates.equals((guest.coordinates))))
                && (creationDate == guest.creationDate || (creationDate != null && creationDate.equals(guest.creationDate)))
                && (realHero == guest.realHero || (realHero != null && realHero.equals(guest.realHero)))
                && (hasToothpick == guest.hasToothpick)
                && (impactSpeed == guest.impactSpeed)
                && (minutesOfWaiting == guest.minutesOfWaiting || (minutesOfWaiting != null && minutesOfWaiting.equals(guest.minutesOfWaiting)))
                && (weaponType == guest.weaponType || (weaponType != null && weaponType.equals(guest.weaponType)))
                && (mood == guest.mood || (mood != null && mood.equals(guest.mood)))
                && (car == guest.car || (car != null && car.equals(guest.car)));
    }

    /**
     *
     * @return возвращает хэш-код обьекта
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((realHero == null) ? 0 : realHero.hashCode());
        result = prime * result + ((hasToothpick) ? 1 : 0); // дополнить а что если null
        result = prime * result + impactSpeed;
        result = prime * result + ((minutesOfWaiting == null) ? 0 : minutesOfWaiting.hashCode());
        result = prime * result + ((weaponType == null) ? 0 : weaponType.hashCode());
        result = prime * result + ((mood == null) ? 0 : mood.hashCode());
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        return result;
    }

    /**
     *
     * @return выводит поля объекта
     */
    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getCoordinates() + " " + getCreationDate()
                + " " + getRealHero() + " " + getHasToothpick() + " " + getImpactSpeed()
                + " " + getMinutesOfWaiting() + " " + getWeaponType() + " " + getMood() + " " + getCar();
    }


    /**
     *
     * @param o - обЬект который надо сравнить
     * @return возвращает число
     */
    @Override
    public int compareTo(HumanBeing o) {
        return (int)(this.id - o.getId());
    }
}

