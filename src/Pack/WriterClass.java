package Pack;


public class WriterClass extends Thread {

    private final Library library;

    WriterClass(Library library) {
        this.library = library;
    }

    @Override
    public void run() {
        synchronized (library){
            while(library.seats.get(0)!=0) {
                try {
                    library.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(library.seats.get(0)==0)
            {library.seats.set(0,2);
            library.WriterList.remove(0);}
            System.out.println(library.seats.toString()+" R "+library.ReaderList.size()+" W "+library.WriterList.size());
            library.notifyAll();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (library){
            if(library.seats.get(0)==2)
                library.seats.set(0,0);

            library.notifyAll();
        }
    }
}
