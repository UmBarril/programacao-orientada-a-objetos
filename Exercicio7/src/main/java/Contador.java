
public class Contador {
    private int c;
    public synchronized void incrementa(){
        c++;
    }
    public synchronized void decrementa(){
        c--;
    }
    public synchronized int getValor(){
        return this.c;
    }
}
