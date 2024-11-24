package com.game.naovoltepracasa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;

public class Cena {
    protected Texture background; // Imagem de fundo da cena
    protected BitmapFont bitmap; // Objeto utilizado para escrever textos
    protected Stage stage; // Objeto para adicionar os elementos na tela

    protected Map<Texto, String> textos; // Armazena todos os textos dessa cena
    private  String textoAtual; // O texto que sera mostrado na tela
    protected int estado; // Indica o estado dentro da cena
    protected boolean ended; // Indica se a cena terminou
    protected Set<Objeto> objetos; // Lista de objetos encontrados pelo jogador
    protected Skin btnStyle;

    private long lastTimeType;
    private int textIndex;

    public Cena(String backgroundName, Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos) {
        if(backgroundName != null) {
            this.background = new Texture(backgroundName);
        }
        this.stage = stage;
        this.bitmap = bitmap;
        this.textos = new HashMap<>();
        this.estado = 0;
        this.textoAtual = "";
        this.ended = false;
        this.lastTimeType = TimeUtils.millis();
        this.objetos = listaObjetos;
        this.btnStyle = new Skin(Gdx.files.internal("skin/light-hdpi/Holo-light-hdpi.json"));
    }

    public String typeText() {
        return this.typeText(5);
    }

    public String typeText(int limit) {
        if(TimeUtils.millis() - this.lastTimeType >= limit) {
            this.textIndex = Math.min(this.textoAtual.length(), ++this.textIndex);
            this.lastTimeType = TimeUtils.millis();
        }

        return this.textoAtual.substring(0, this.textIndex);
    }

    public void setTextoAtual(String texto) {
        this.textoAtual = texto;
        this.textIndex = 0;
    }
    public String getTextoAtual() {return this.textoAtual;}
    public void run() {};
    public void dispose() {};
    public boolean hasEnded() {return this.ended;};
    public void init() {};
    public void close() {};
    public void setObjetos(Set<Objeto> objetos) {this.objetos = objetos;};
}