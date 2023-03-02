public class TestaSistemaAmigo {
    public static void main(String[] args) {
        SistemaAmigo sistema = new SistemaAmigo();
        sistema.cadastraAmigo("José", "ze@mail.com");
        sistema.cadastraAmigo("Maria", "maria@mail.com");
        try {
            sistema.configuraAmigoSecretoDe("ze@mail.com", "maria@mail.com");
            sistema.configuraAmigoSecretoDe("maria@mail.com", "ze@mail.com");
        } catch (AmigoInexistenteException e) {
            throw new RuntimeException(e);
        }
        sistema.enviarMensagemParaAlguem("COCÔZÃO B)", "ze@mail.com", "maria@mail.com", true);
        sistema.enviarMensagemParaTodos("COCÔZÃOZÃO :O", "maria@mail.com", true);
        sistema.pesquisaMensagensAnonimas().forEach(msg -> {
            System.out.println(msg.getTextoCompletoAExibir());
        });
        if(sistema.pesquisaAmigo("ze@mail.com").getEmailAmigoSorteado().equals("maria@mail.com")) {
            System.out.println("OK");
        }
    }
}
