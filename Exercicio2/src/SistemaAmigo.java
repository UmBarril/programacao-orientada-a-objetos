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
        this.amigos.add(new Amigo(nomeAmigo, emailAmigo));
    }

    public Amigo pesquisaAmigo(String emailAmigo) {
        return this.amigos
                .stream()
                .filter(msg -> msg.getEmail().equals(emailAmigo))
                .findFirst()
                .orElse(null);
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        this.mensagens.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
        this.mensagens.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima));
    }

    //Método que pesquisa as mensagens que são anônimas e retorna uma lista com tais mensagens.
    public List<Mensagem> pesquisaMensagensAnonimas() {
        return this.pesquisaTodasAsMensagens()
                .stream()
                .filter(msg -> msg.ehAnonima())
                .toList();
    }

    //Método que pesquisa todas as mensagens já enviadas.
    public List<Mensagem> pesquisaTodasAsMensagens() {
        return this.mensagens;
    }

    // Método que configura o amigo secreto sorteado para a pessoa cujo e-mail é “emailDaPessoa”, atribuindo-lhe seu
    // amigo secreto como sendo a pessoa de e-mail “emailAmigoSecreto”. Caso não exista pessoa cadastrada no sistema
    // com o e-mail “emailDaPessoa, deve ser lançada a exceção “AmigoInexistenteException”.
    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {
        Amigo pessoa = this.pesquisaAmigo(emailDaPessoa);
        if(pessoa == null) {
            throw new AmigoInexistenteException();
        }
        pessoa.setEmailAmigoSorteado(emailAmigoSorteado);
    }

    // Método que pesquisa o e-mail do amigo secreto sorteado para a pessoa cujo e-mail é “emailDaPessoa”
    // Caso não exista pessoa cadastrada no sistema com o e-mail “emailDaPessoa”, deve ser lançada a exceção
    // “AmigoInexistenteException”. Caso exista a pessoa com esse e-mail, mas o seu amigo secreto sorteado
    // ainda não tenha sido configurado (o e-mail do amigo sorteado é null), deve ser lançada a exceção
    // “AmigoNaoSorteadoException”.
    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        Amigo pessoa = this.pesquisaAmigo(emailDaPessoa);
        if(pessoa == null) {
            throw new AmigoInexistenteException();
        }
        String emailAmigoSorteado = pessoa.getEmailAmigoSorteado();
        if(emailAmigoSorteado == null) {
            throw new AmigoNaoSorteadoException();
        }
        return emailAmigoSorteado;
    }

    // Opcional
    public void sortear() {
        List<Amigo> amigosNaoSorteados = new ArrayList<>();
        amigosNaoSorteados.addAll(this.amigos);
        for (Amigo amigo : this.amigos) {
            int posicaoDaListaSorteada = (int)(Math.random()*amigosNaoSorteados.size());
            Amigo amigoSorteado = amigosNaoSorteados.get(posicaoDaListaSorteada);
            amigo.setEmailAmigoSorteado(amigoSorteado.getEmail());
        }
    }
    // Extra
    public List<Amigo> getAmigos() {
        return this.amigos;
    }
}
