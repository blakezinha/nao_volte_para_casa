package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

public class Cena6 extends Cena {
    
    private TextButton verificarCaixaBtn;
    private TextButton continuarBtn;
    private TextButton continuar2Btn;
    
    public Cena6(Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super("imagem6.jpg", stage, bitmap, listaObjetos);
        
        // Adiciona todos os textos da cena
        this.textos.put(Texto.C6_INICIAL, "Você está em um quarto completamente escuro. O ar está pesado e cheira a mofo. Algo parece " +
        "observar\nvocê nas sombras. Você tenta enxergar algo porém precisaria de uma lanterna com baterias. Ao tentar\nentrar no quarto " +
        "você se depara com uma caixa ao lado da porta.");
        this.textos.put(Texto.C6_CAIXA, "Ao abrir, foi encontrado recortes de jornais e um colar infantil. Os recortes dizem: " +
        "'Família\nWinters morre em acidente na estrada principal. Você lembra da última vez que colocou o colar em\nAlice.");
        this.textos.put(Texto.C6_CONTINUAR, "Inconformada com a situação, sua respiração fica ofegante e a escuridão parece estar cada vez " +
        "mais\npróxima. Sua visão fica turva e tudo ao seu redor começa escurecer sem sobrar nenhuma luz.");


        this.setTextoAtual(this.textos.get(Texto.C6_INICIAL));
        
        // Configura todos os botes
        this.verificarCaixaBtn = new TextButton("1. Verificar caixa.", this.btnStyle);
        this.verificarCaixaBtn.setSize(1000, 50);
        this.verificarCaixaBtn.setPosition(33, 200);
        this.verificarCaixaBtn.setVisible(false);
        this.verificarCaixaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!verificarCaixaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C6_CAIXA));
                    verificarCaixaBtn.setDisabled(true);
                    estado = 1;
                }
                return true;
            }
        });


        this.continuarBtn = new TextButton("1. Continuar.", this.btnStyle);
        this.continuarBtn.setSize(1000, 50);
        this.continuarBtn.setPosition(33, 200);
        this.continuarBtn.setVisible(false);
        this.continuarBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!continuarBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C6_CONTINUAR));
                    continuarBtn.setDisabled(true);
                    estado = 2;
                }
                return true;
            }
        });

        this.continuar2Btn = new TextButton("1. Continuar.", this.btnStyle);
        this.continuar2Btn.setSize(1000, 50);
        this.continuar2Btn.setPosition(33, 200);
        this.continuar2Btn.setVisible(false);
        this.continuar2Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!continuar2Btn.isDisabled()) {
                    setTextoAtual("");
                    continuar2Btn.setDisabled(true);
                    ended = true;
                    estado = 3;
                }
                return true;
            }
        });

        // Adiciona todos os botoes na tela
        this.stage.addActor(this.verificarCaixaBtn);
        this.stage.addActor(this.continuarBtn);
        this.stage.addActor(this.continuar2Btn);
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
            this.verificarCaixaBtn.setVisible(true);
            this.continuarBtn.setVisible(false);
            this.continuar2Btn.setVisible(false);
        } else if(estado == 1) {
            this.verificarCaixaBtn.setVisible(false);
            this.continuarBtn.setVisible(true);
            this.continuar2Btn.setVisible(false);
        } else if(estado == 2) {
            this.verificarCaixaBtn.setVisible(false);
            this.continuarBtn.setVisible(false);
            this.continuar2Btn.setVisible(true);
        }

        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }

    @Override
    public boolean hasEnded() {
        return ended;
    }

    @Override
    public void init() {
        this.verificarCaixaBtn.setDisabled(false);
        this.continuarBtn.setDisabled(false);
        this.continuar2Btn.setDisabled(false);
        this.ended = false;
        this.estado = 0;
        this.setTextoAtual(this.textos.get(Texto.C6_INICIAL));
    }

    @Override
    public void close() {
        this.verificarCaixaBtn.setVisible(false);
        this.continuarBtn.setVisible(false);
        this.continuar2Btn.setVisible(false);
    }

    @Override
    public void setObjetos(Set<Objeto> objetos) {
        this.objetos = objetos;
    }
}