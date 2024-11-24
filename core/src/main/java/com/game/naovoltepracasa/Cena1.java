package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

public class Cena1 extends Cena {
    
    private TextButton inspecionarCarroBtn;
    private TextButton moverBtn;
    private TextButton pegarLanternaBtn;
    private TextButton irCasaBtn;

    public Cena1(Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super("imagem1.jpg", stage, bitmap, listaObjetos);
        
        // Adiciona todos os textos da cena
        this.textos.put(Texto.C1_INICIAL, "Você acorda dentro de um carro amassado e sente um leve cheiro de gasolina." +
        " Tudo está escuro ao seu\nredor, exceto por alguns raios de luz passando pelos vidros quebrados. Você verifica o seu bolso e\nencontra uma carteira." +
        "Você abre a carteira e encontra um documento. Está sujo, mas ainda legível:\n'Elizabeth, 33 anos.' O sobrenome está parcialmente apagado.");
        this.textos.put(Texto.C1_CARRO, "Há uma lanterna caída no banco");
        this.textos.put(Texto.C1_CARRO_LANTERNA,"Você pega a lanterna. Ela não funciona. Parece que precisa de baterias.");
        this.textos.put(Texto.C1_TRILHA,"Você sai do carro e percebe uma trilha de terra que parece levar até uma casa próxima.");

        this.setTextoAtual(this.textos.get(Texto.C1_INICIAL));
        
        // Configura todos os botes
        this.inspecionarCarroBtn = new TextButton("1. Inspecionar o carro.", this.btnStyle);
        this.inspecionarCarroBtn.setSize(1000, 50);
        this.inspecionarCarroBtn.setPosition(33, 200);
        this.inspecionarCarroBtn.setVisible(false);
        this.inspecionarCarroBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!inspecionarCarroBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C1_CARRO));
                    inspecionarCarroBtn.setDisabled(true);
                    estado = 1;
                }
                return true;
            }
        });


        this.moverBtn = new TextButton("2. Mover-se.", this.btnStyle);
        this.moverBtn.setSize(1000, 50);
        this.moverBtn.setPosition(33, 160);
        this.moverBtn.setVisible(false);
        this.moverBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!moverBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C1_TRILHA));
                    moverBtn.setDisabled(true);
                    estado = 2;
                }
                return true;
            }
        });

        this.pegarLanternaBtn = new TextButton("1. Pegar lanterna.", this.btnStyle);
        this.pegarLanternaBtn.setSize(1000, 50);
        this.pegarLanternaBtn.setPosition(33, 200);
        this.pegarLanternaBtn.setVisible(false);
        this.pegarLanternaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!pegarLanternaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C1_CARRO_LANTERNA));
                    pegarLanternaBtn.setDisabled(true);
                    objetos.add(Objeto.LANTERNA);
                    estado = 1;
                }
                return true;
            }
        });

        this.irCasaBtn = new TextButton("1. Ir para casa.", this.btnStyle);
        this.irCasaBtn.setSize(1000, 50);
        this.irCasaBtn.setPosition(33, 200);
        this.irCasaBtn.setVisible(false);
        this.irCasaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!irCasaBtn.isDisabled()) {
                    setTextoAtual("");
                    estado = 3;
                    irCasaBtn.setDisabled(true);
                    ended = true;
                }
                return true;
            }
        });

        // Adiciona todos os botoes na tela
        this.stage.addActor(this.inspecionarCarroBtn);
        this.stage.addActor(this.moverBtn);
        this.stage.addActor(this.pegarLanternaBtn);
        this.stage.addActor(this.irCasaBtn);
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
            this.inspecionarCarroBtn.setVisible(true);
            this.moverBtn.setVisible(true);
            this.pegarLanternaBtn.setVisible(false);
            this.irCasaBtn.setVisible(false);
        } else if(estado == 1) {
            this.inspecionarCarroBtn.setVisible(false);
            this.moverBtn.setVisible(true);
            this.pegarLanternaBtn.setVisible(true);
            this.irCasaBtn.setVisible(false);
        } else if(estado == 2) {
            this.inspecionarCarroBtn.setVisible(false);
            this.moverBtn.setVisible(false);
            this.pegarLanternaBtn.setVisible(false);
            this.irCasaBtn.setVisible(true);
        }

        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }

    @Override
    public void init() {
        this.inspecionarCarroBtn.setDisabled(false);
        this.moverBtn.setDisabled(false);
        this.pegarLanternaBtn.setDisabled(false);
        this.irCasaBtn.setDisabled(false);
        this.ended = false;
        this.estado = 0;
        this.setTextoAtual(this.textos.get(Texto.C1_INICIAL));
    }

    @Override
    public void close() {
        this.inspecionarCarroBtn.setVisible(false);
        this.moverBtn.setVisible(false);
        this.pegarLanternaBtn.setVisible(false);
        this.irCasaBtn.setVisible(false);
    }
}