public class ErrorLengthArrayException extends Exception {
//    public ErrorLengthArrayException() {
//        super();
//        throw new ArrayIndexOutOfBoundsException();
//    }


    public ErrorLengthArrayException() {
    }

    public ErrorLengthArrayException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Не соответствует ожидаемой длинне массива";
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
