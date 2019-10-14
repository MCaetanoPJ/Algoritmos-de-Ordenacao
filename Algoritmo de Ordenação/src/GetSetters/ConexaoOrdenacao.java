/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetSetters;

public class ConexaoOrdenacao {
    
    private int Id;
    private int IdOrdenado;
    private byte[] Imagem;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdOrdenado() {
        return IdOrdenado;
    }

    public void setIdOrdenado(int IdOrdenado) {
        this.IdOrdenado = IdOrdenado;
    }

    public byte[] getImagem() {
        return Imagem;
    }

    public void setImagem(byte[] Imagem) {
        this.Imagem = Imagem;
    }
    
    public ConexaoOrdenacao(int Id,int IdOrdenado){
    this.Id = Id;
    this.IdOrdenado = IdOrdenado;
    }
    
    public ConexaoOrdenacao() {
    }
       
}
