public class NotMatchDataTypeException extends Exception {
    @Override
    public String getMessage() {
        return "Массив содержит не числа";
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
