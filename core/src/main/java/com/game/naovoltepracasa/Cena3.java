package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

public class Cena3 extends Cena {

    private TextButton inspecionarVasoBtn;
    private TextButton paBtn;
    private TextButton covaBtn;
    private TextButton casaBtn;  
    private boolean encontraChave; 
    
    public Cena3(Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super("imagem3.jpg", stage, bitmap, listaObjetos);
        this.encontraChave = false;
        
        // Adiciona todos os textos da cena
        this.textos.put(Texto.C3_INICIAL, "O jardim está descuidado. Há ferramentas espalhadas no chão e três covas abertas que parecem\nrecém-cavadas.");
        this.textos.put(Texto.C3_VASO, "Dentro do vaso tem uma chave. Deve ser a chave da casa.");
        this.textos.put(Texto.C3_PA,"Dentro da pá há um conjunto de baterias. Elas parecem funcionar.");
        this.textos.put(Texto.C3_COVA,"Você vê três covas abertas. Não há cruzes ou placas indicando quem seriam os enterrados.");
        this.textos.put(Texto.C3_CASA,"Você não encontrou a chave, continue procurando no jardim.");
        
        this.setTextoAtual(this.textos.get(Texto.C3_INICIAL));

        this.inspecionarVasoBtn = new TextButton("1. Inspecionar o vaso.", this.btnStyle);
        this.inspecionarVasoBtn.setSize(1000, 50);
        this.inspecionarVasoBtn.setPosition(33, 200);
        this.inspecionarVasoBtn.setVisible(false);
        this.inspecionarVasoBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!inspecionarVasoBtn.isDisabled()) {
                    encontraChave = true;
                    objetos.add(Objeto.CHAVE);
                    setTextoAtual(textos.get(Texto.C3_VASO));
                    inspecionarVasoBtn.setDisabled(true);
                    estado = 0;
                }
                return true;
            }
        });


        this.paBtn = new TextButton("2. Inspecionar a pá.", this.btnStyle);
        this.paBtn.setSize(1000, 50);
        this.paBtn.setPosition(33, 160);
        this.paBtn.setVisible(false);
        this.paBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!paBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C3_PA));
                    objetos.add(Objeto.BATERIAS);
                    paBtn.setDisabled(true);
                    estado = 0;
                }
                return true;
            }
        });

        this.covaBtn = new TextButton("3. Inspecionar cova.", this.btnStyle);
        this.covaBtn.setSize(1000, 50);
        this.covaBtn.setPosition(33, 120);
        this.covaBtn.setVisible(false);
        this.covaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!covaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C3_COVA));
                    covaBtn.setDisabled(true);
                    estado = 0;
                    }
                return true;
            }
        });

        this.casaBtn = new TextButton("4. Voltar para casa.", this.btnStyle);
        this.casaBtn.setSize(1000, 50);
        this.casaBtn.setPosition(33, 80);
        this.casaBtn.setVisible(false);
        this.casaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!casaBtn.isDisabled()) {
                    if(encontraChave == true) {
                        casaBtn.setDisabled(true);
                        ended = true;
                        estado = 1;
                    } else {
                        setTextoAtual(textos.get(Texto.C3_CASA));
                        estado = 0;
                    }
                }
                return true;
            }
        });

        // Adiciona todos os botoes na tela
        this.stage.addActor(this.inspecionarVasoBtn);
        this.stage.addActor(this.paBtn);
        this.stage.addActor(this.covaBtn);
        this.stage.addActor(this.casaBtn);
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
            this.inspecionarVasoBtn.setVisible(true);
            this.paBtn.setVisible(true);
            this.covaBtn.setVisible(true);
            this.casaBtn.setVisible(true);
        }

        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }

    @Override
    public void init() {
        this.inspecionarVasoBtn.setDisabled(false);
        this.paBtn.setDisabled(false);
        this.covaBtn.setDisabled(false);
        this.casaBtn.setDisabled(false);
        this.ended = false;
        this.estado = 0;
        this.setTextoAtual(this.textos.get(Texto.C3_INICIAL));
    }

    @Override
    public void close() {
        this.inspecionarVasoBtn.setVisible(false);
        this.paBtn.setVisible(false);
        this.covaBtn.setVisible(false);
        this.casaBtn.setVisible(false);
    }

}