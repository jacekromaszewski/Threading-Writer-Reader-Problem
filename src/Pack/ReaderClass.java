package Pack;


public class ReaderClass extends Thread {

    private final Library library;
    ReaderClass(Library library){
        this.library=library;
    }

    @Override
    public void run() {
        synchronized (library){
            while(library.seats.get(0)==2 && library.seats.size()<10) {
                try {
                    library.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(library.seats.get(0)==0)
                library.seats.set(0,1);
            else library.seats.add(1);
            library.ReaderList.remove(0);
            System.out.println(library.seats.toString()+" R "+library.ReaderList.size()+" W "+library.WriterList.size());
            library.notifyAll();
        }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (library){
                if(library.seats.get(0)!=2)
                    if (library.seats.size() > 1) {
                        library.seats.remove(0);
                    } else library.seats.set(0, 0);

                    library.notifyAll();
            }
    }
}

