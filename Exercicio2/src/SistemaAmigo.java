import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo
{
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;
    public SistemaAmigo() { // extra
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }
    public SistemaAmigo(int quantMaximaMensagens) { // extra
        this.mensagens = new ArrayList<>(quantMaximaMensagens);
        this.amigos = new ArrayList<>();
    }
    public void cadastraAmigo(String nomeAmigo, String emailAmigo) {
        // TODO
    }
    public Amigo pesquisaAmigo(String emailAmigo) {
        // TODO
    }
    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        // TODO
    }
    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
        // TODO
    }
    public List<Mensagem> pesquisaMensagensAnonimas() {
        return this.pesquisaTodasAsMensagens()
                .stream()
                .filter(msg -> msg.ehAnonima())
                .toList();
    }
    public List<Mensagem> pesquisaTodasAsMensagens() {
        return this.mensagens;
    }
    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {
        // Método que configura o amigo secreto sorteado para a pessoa cujo e-mail é “emailDaPessoa”, atribuindo-lhe seu
        // amigo secreto como sendo a pessoa de e-mail “emailAmigoSecreto”. Caso não exista pessoa cadastrada no sistema
        // com o e-mail “emailDaPessoa, deve ser lançada a exceção “AmigoInexistenteException”.
    }
    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {

        // Método que pesquisa o e-mail do amigo secreto sorteado para a pessoa cujo e-mail é “emailDaPessoa”
        // Caso não exista pessoa cadastrada no sistema com o e-mail “emailDaPessoa”, deve ser lançada a exceção
        // “AmigoInexistenteException”. Caso exista a pessoa com esse e-mail, mas o seu amigo secreto sorteado
        // ainda não tenha sido configurado (o e-mail do amigo sorteado é null), deve ser lançada a exceção
        // “AmigoNaoSorteadoException”.
    }
    public void sortear() {
        // TODO: OPCIONAL
        //int posicaoDaListaSorteada = (int)(Math.random()*lista.size());
    }
}
