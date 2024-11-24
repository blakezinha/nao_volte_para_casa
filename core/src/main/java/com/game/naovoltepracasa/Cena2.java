package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

public class Cena2 extends Cena {

    private TextButton inspecionarPlacaBtn;
    private TextButton portaBtn;
    private TextButton jardimBtn;
    

    public Cena2(Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super("imagem2.jpg", stage, bitmap, listaObjetos);
        
        // Adiciona todos os textos da cena
        this.textos.put(Texto.C2_INICIAL, "Você está na frente de uma casa antiga e desgastada. Há uma grande árvore à esquerda, um jardim\nmais adiante e uma placa de madeira no portão.");
        this.textos.put(Texto.C2_PLACA, "A placa está gasta e ilegível em algumas partes: 'Bem-vindo à residência da família -XXXXXXX-'.");
        this.textos.put(Texto.C2_PORTA,"A porta está trancada. Você precisa de uma chave, talvez a encontre no jardim.");

        this.setTextoAtual(this.textos.get(Texto.C2_INICIAL));

        this.inspecionarPlacaBtn = new TextButton("1. Inspecionar a placa.", this.btnStyle);
        this.inspecionarPlacaBtn.setSize(1000, 50);
        this.inspecionarPlacaBtn.setPosition(33, 200);
        this.inspecionarPlacaBtn.setVisible(false);
        this.inspecionarPlacaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!inspecionarPlacaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C2_PLACA));
                    inspecionarPlacaBtn.setDisabled(true);
                    estado = 0;
                }
                return true;
            }
        });


        this.portaBtn = new TextButton("2. Entrar na casa.", this.btnStyle);
        this.portaBtn.setSize(1000, 50);
        this.portaBtn.setPosition(33, 160);
        this.portaBtn.setVisible(false);
        this.portaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!portaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C2_PORTA));
                    portaBtn.setDisabled(true);
                    estado = 1;
                }
                return true;
            }
        });

        this.jardimBtn = new TextButton("1. Ir para o jardim.", this.btnStyle);
        this.jardimBtn.setSize(1000, 50);
        this.jardimBtn.setPosition(33, 200);
        this.jardimBtn.setVisible(false);
        this.jardimBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!jardimBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C2_JARDIM));
                    jardimBtn.setDisabled(true);
                    ended = true;
                    estado = 2;
                }
                return true;
            }
        });


        // Adiciona todos os botoes na tela
        this.stage.addActor(this.inspecionarPlacaBtn);
        this.stage.addActor(this.portaBtn);
        this.stage.addActor(this.jardimBtn);
    }

    @Override
    public void run() {
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(this.background, 0, 400);
        this.bitmap.draw(this.stage.getBatch(), this.typeText(), 10, 390);
        this.stage.getBatch().end();

        // Oculta ou mostra os botoes de acordo com o estado da cena
        if(estado == 0) {
            this.inspecionarPlacaBtn.setVisible(true);
            this.portaBtn.setVisible(true);
            this.jardimBtn.setVisible(false);
        } else if(estado == 1) {
            this.inspecionarPlacaBtn.setVisible(false);
            this.portaBtn.setVisible(false);
            this.jardimBtn.setVisible(true);
        }

        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }

    @Override
    public void init() {
        this.inspecionarPlacaBtn.setDisabled(false);
        this.portaBtn.setDisabled(false);
        this.jardimBtn.setDisabled(false);
        this.ended = false;
        this.estado = 0;
        this.setTextoAtual(this.textos.get(Texto.C2_INICIAL));
    }

    @Override
    public void close() {
        this.inspecionarPlacaBtn.setVisible(false);
        this.portaBtn.setVisible(false);
        this.jardimBtn.setVisible(false);
    }
}