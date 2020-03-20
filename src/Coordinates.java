public class Coordinates {
    private Double x;
    private Long y;

    Coordinates(){
        super();
    }
    Coordinates(Double x,Long y) throws Exception{
        setX(x);
        setY(y);
    }

    public Double getX() {
        return x;
    }
    public Long getY() {
        return y;
    }

    public void setX(Double x) throws Exception {
        try {
            if(!(x<-672.0) && x != null) {
                this.x = x;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Максимальное значение -672.0");
            throw new Exception();
        }
    } // Доработать ошибки(добавил обработку)
    public void setY(Long y) throws Exception {
        try {
            if(!(y>151) && !(y.equals(null))) {
                this.y = y;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Максимальное значение 151");
            throw new Exception();
        }
    } // Доработать ошибки(добавил обработку)

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }
        Coordinates guest = (Coordinates) obj;
        return (x == guest.x || (x != null && x.equals(guest.x)))
                && (y == guest.y || (y != null && y.equals(guest.y)));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getX() + " " + getY();
    }
}
