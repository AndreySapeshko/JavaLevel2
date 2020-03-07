import java.util.Arrays;

public class ParceStringMatrix {

    public static void main(String[] args) {
        try {
            System.out.println(halfSummOfArray(parceStringToArray("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 no")));
        } catch (ErrorLengthArrayException e) {
            e.printStackTrace();
        } catch (NotMatchDataTypeException e) {
            e.printStackTrace();
        }
    }

    public static String[][] parceStringToArray(String s) throws ErrorLengthArrayException {
        String[] arrNewString = null;
        arrNewString = s.split("\n");
        if (arrNewString.length < 4 || arrNewString.length > 4) {
            throw new ErrorLengthArrayException();
        }
        String[] [] numFromNewString = new String[arrNewString.length][4];
        for (int i = 0; i < arrNewString.length; i++) {
            numFromNewString[i] = arrNewString[i].split(" ");
            if (numFromNewString[i].length < 4 || numFromNewString[i].length > 4) {
                throw new ErrorLengthArrayException();
            }
        }
        return numFromNewString;
    }

    public static int halfSummOfArray(String[][] arr) throws NotMatchDataTypeException {
        int halfSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    halfSum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new NotMatchDataTypeException();
                }
            }
        }
        return halfSum / 2;
    }
}
