public class TA extends Thread{
    private Contador cont;
    public TA(Contador cont){
        this.cont = cont;
    }
    public void run(){
        System.out.println("TA:Vou incrementar contador...");
        this.cont.incrementa();
        System.out.println("TA:Valor do contador:" + this.cont.getValor());
    }
}