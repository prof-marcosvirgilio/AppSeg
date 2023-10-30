package br.dev.marcosvirgilio.seg.modelo.usuario;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {
    private String nome;
    private String email;
    private String senha;

    //CONSTRUTOR DE INICIALIZAÇÃO SEM NENHUM PARÂMETRO
    public Usuario() {
        this.nome = "";
        this.email = "";
        this.senha = "";
    }
    //CONSTRUTOR DE INICIALIZAÇÃO COM TODOS PARÂMETROS
    public Usuario(String n, String e, String s) {
        this.nome = n;
        this.email = e;
        this.senha = s;
    }
    //CONSTRUTOR DE INICIALIZAÇÃO COM OBJETO JASON
    public Usuario (JSONObject jo) {
        try {
            this.nome = (String)jo.get("nome");
            this.email = (String)jo.get("email");
            this.senha = (String)jo.get("senha");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("nome", this.nome);
            jsonObj.put("email", this.email);
            jsonObj.put("senha", this.senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
