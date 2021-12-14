package PetBeauty;

public class Utilizador implements InterfaceUser{
    private String username;
    private String password;
    private String primeiroNome;
    private String apelido;
    private String NIF;
    private String nTel;
    private String tipo;

    public Utilizador(String username, String pass, String NIF, String tipo){
        this.username = username;
        this.password = pass;
        this.NIF = NIF;
        this.tipo = tipo;
    }

    public Utilizador(String username, String password, String nome, String apelido, String NIF, String nTel, String tipo){
        this.username = username;
        this.password = password;
        this.primeiroNome = nome;
        this.apelido = apelido;
        this.NIF = NIF;
        this.nTel = nTel;
        this.tipo = tipo;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    @Override
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    @Override
    public String getApelido() {
        return apelido;
    }

    @Override
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public String getNIF() {
        return NIF;
    }

    @Override
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    @Override
    public String getnTel() {
        return nTel;
    }

    @Override
    public void setnTel(String nTel) {
        this.nTel = nTel;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
