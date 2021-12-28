package PetBeauty;

public class Empresa {
    private String nifEmpresa;
    private String nome;
    private String morada;
    private String localidade;
    private String especialidade;
    private String nTel;
    private String nifDono;
    private String ativo;

    public Empresa(String nifEmpresa, String nome, String morada, String localidade, String especialidade, String nTel, String nifDono, String ativo){
        this.nifEmpresa = nifEmpresa;
        this.nome = nome;
        this.morada=morada;
        this.localidade=localidade;
        this.especialidade=especialidade;
        this.nTel=nTel;
        this.nifDono=nifDono;
        this.ativo=ativo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getnTel() {
        return nTel;
    }

    public void setnTel(String nTel) {
        this.nTel = nTel;
    }

    public String getNifDono() {
        return nifDono;
    }

    public void setNifDono(String nifDono) {
        this.nifDono = nifDono;
    }
}
