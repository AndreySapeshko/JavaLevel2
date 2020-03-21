package testThread;

public class Main {
    private static final int SIZE = 10000000;
    private static final int HELF = SIZE / 2;
    private static float[] arr;
    private static float[] a1;
    private static float[] a2;

    public static void main(String[] args) throws InterruptedException {

        oneThread();
        twoThread();
    }

    public static void oneThread() {
        arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ( float )( arr [ i ] * Math . sin ( 0.2f + i / 5 ) * Math . cos ( 0.2f + i / 5 ) *
                    Math . cos ( 0.4f + i / 2 ));
        }
        System.out.println(arr[arr.length-1]);
        System.out.println("One: " + (System.currentTimeMillis() - a));
    }

    public static void twoThread() throws InterruptedException {
        arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        a1 = new float[HELF];
        a2 = new float[HELF];
        System.arraycopy(arr, 0, a1, 0, HELF);
        System.arraycopy(arr, HELF, a2, 0, HELF);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Main.a1.length; i++) {
                    Main.a1[i] = ( float )( Main.a1 [ i ] * Math . sin ( 0.2f + i / 5 ) * Math . cos ( 0.2f + i / 5 ) *
                            Math . cos ( 0.4f + i / 2 ));
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Main.a2.length; i++) {
                    Main.a2[i] = ( float )( Main.a2 [ i ] * Math . sin ( 0.2f + i / 5 ) * Math . cos ( 0.2f + i / 5 ) *
                            Math . cos ( 0.4f + i / 2 ));
                    if (i == Main.a2.length -1) System.out.println("end for");
                }
            }
        });
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(a1, 0, arr, 0, HELF);
        System.arraycopy(a2, 0, arr, HELF, HELF);
        System.out.println(arr[arr.length-1]);
        System.out.println("Two: " + (System.currentTimeMillis() - a));
    }
}
