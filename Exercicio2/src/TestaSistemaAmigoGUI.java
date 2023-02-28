import javax.swing.*;

public class TestaSistemaAmigoGUI {
    public static void main(String[] args) {
        int quantidadeMaximaMsgs = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade máxima que o sistema irá suportar:"));
        SistemaAmigo sistema = new SistemaAmigo(quantidadeMaximaMsgs);
        System.out.println();
    }
}
