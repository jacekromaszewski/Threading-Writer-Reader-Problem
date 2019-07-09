package Pack;

import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) {

        Library library=new Library();
        library.seats.add(0);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Podaj częstość występowania pisarza: ");
        float writer=scanner.nextFloat();
        System.out.print("Podaj częstość występowania czytelnika: ");
        float reader = scanner.nextFloat();


        Thread generate = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                float val = random.nextFloat();
                if (val <= writer) {
                    WriterClass writerObject=new WriterClass(library);
                    library.WriterList.add(writerObject);
                    writerObject.start();

                }
                if (val <= reader) {
                    ReaderClass readerObject=new ReaderClass(library);
                    library.ReaderList.add(readerObject);
                    readerObject.start();
                }

            }
        });

        generate.start();
        while (true) { }
    }
}