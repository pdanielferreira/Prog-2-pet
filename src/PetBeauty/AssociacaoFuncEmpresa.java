package PetBeauty;

public class AssociacaoFuncEmpresa {
    private String nifEmpresa;                      //NIF DA EMPRESA
    private String nifFuncionario;                  //NIF DO FUNCIONARIO
    private String ativo;                           //ENCONTRAA-SE A TRABALHAR

    public AssociacaoFuncEmpresa(String nifFuncionario, String nifEmpresa, String ativo){
        this.nifFuncionario=nifFuncionario;
        this.nifEmpresa=nifEmpresa;
        this.ativo=ativo;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    public String getNifFuncionario() {
        return nifFuncionario;
    }

    public void setNifFuncionario(String nifFuncionario) {
        this.nifFuncionario = nifFuncionario;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
