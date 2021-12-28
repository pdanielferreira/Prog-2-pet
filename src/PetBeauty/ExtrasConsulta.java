package PetBeauty;

public class ExtrasConsulta {
    private String codConsulta;
    private String desc;
    private String valor;

    public ExtrasConsulta(String codConsulta, String desc, String valor){
        this.codConsulta=codConsulta;
        this.desc=desc;
        this.valor=valor;
    }

    public String getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(String codConsulta) {
        this.codConsulta = codConsulta;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
