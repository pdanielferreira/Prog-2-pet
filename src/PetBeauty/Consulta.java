package PetBeauty;

import java.util.*;

public class Consulta {
    private String codigo;
    private String data;
    private String hora;
    private String NIFEmpresa;
    private String NIFFuncionario;
    private String NIFDono;
    private String codAnimal;
    private String confirmada;
    private String valor;
    private String realizada;
    private String paga;

    public Consulta(String codigo, String data, String hora, String NIFEmpresa, String NIFFuncionario, String NIFDono,
                    String codAnimal, String confirmada, String valor, String realizada, String paga){

        this.codigo=codigo;
        this.data=data;
        this.hora=hora;
        this.NIFEmpresa=NIFEmpresa;
        this.NIFFuncionario=NIFFuncionario;
        this.NIFDono=NIFDono;
        this.codAnimal=codAnimal;
        this.confirmada=confirmada;
        this.valor=valor;
        this.realizada=realizada;
        this.paga=paga;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNIFEmpresa() {
        return NIFEmpresa;
    }

    public String getNIFFuncionario() {
        return NIFFuncionario;
    }

    public void setNIFFuncionario(String NIFFuncionario) {
        this.NIFFuncionario = NIFFuncionario;
    }

    public String getNIFDono() {
        return NIFDono;
    }

    public String getCodAnimal() {
        return codAnimal;
    }

    public String getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(String confirmada) {
        this.confirmada = confirmada;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }

    public String getPaga() {
        return paga;
    }

    public void setPaga(String paga) {
        this.paga = paga;
    }
}
