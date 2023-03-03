import javax.swing.*;
import java.util.List;

public class TestaSistemaAmigoGUI {
    public static void main(String[] args) {
        int quantidadeMaximaMsgs = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade máxima que o sistema irá suportar:"));
        SistemaAmigo sistema = new SistemaAmigo(quantidadeMaximaMsgs);

        int quantAmigos = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de amigos que irão participar:"));
        for (int i = 0; i < quantAmigos; i++) {
            String nomeAmigo = JOptionPane.showInputDialog(String.format("Digite o nome do amigo (%d):", i + 1));
            String emailAmigo = JOptionPane.showInputDialog(String.format("Digite o email do amigo (%d):", i + 1));
            sistema.cadastraAmigo(nomeAmigo, emailAmigo);
        }

        sistema.sortear();

        // relatório de amigos secretos
        List<Amigo> amigos = sistema.getAmigos();

        StringBuilder sb = new StringBuilder();
        for (Amigo amigo : amigos) {
            sb.append(String.format("O amigo secreto de %s é: %s\n", amigo.getEmail(), amigo.getEmailAmigoSorteado()));
        }
        JOptionPane.showMessageDialog(null, sb.toString());

        // enviar mensagem
        Amigo amigoAleatorio = amigos.get((int)(Math.random() * amigos.size()));
        String texto = JOptionPane.showInputDialog(String.format("Mensagem de %s para TODOS. Digite o texto a ser enviado:", amigoAleatorio.getNome()));
        int result = JOptionPane.showConfirmDialog(null, "Deseja enviar esta mensagem de maneira Anônima?", "Pergunta.", JOptionPane.YES_NO_OPTION);
        sistema.enviarMensagemParaTodos(texto, amigoAleatorio.getEmail(), result == 0);
        List<Mensagem> msgs = sistema.pesquisaTodasAsMensagens();
        String ultimaMensagemEnviada = msgs.get(msgs.size() - 1).getTextoCompletoAExibir();
        JOptionPane.showMessageDialog(null, ultimaMensagemEnviada);
    }
}
