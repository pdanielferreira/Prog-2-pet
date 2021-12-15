package PetBeauty;

public class Animal {
    private String codAnimal;
    private String nome;
    private String especie;
    private String nifDono;

    public Animal(String codAnimal, String nome, String especie, String nifDono){
        this.codAnimal=codAnimal;
        this.nome=nome;
        this.especie=especie;
        this.nifDono=nifDono;
    }

    public String getCodAnimal() {
        return codAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNifDono() {
        return nifDono;
    }

    public void setNifDono(String nifDono) {
        this.nifDono = nifDono;
    }


}
