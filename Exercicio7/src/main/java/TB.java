public class TB extends Thread{
    private Contador cont;
    public TB(Contador cont){
        this.cont = cont;
    }
    public void run(){
        System.out.println("TB:Vou decrementar contador...");
        this.cont.decrementa();
        System.out.println("TB:Valor do contador:"+this.cont.getValor());
    }
}
