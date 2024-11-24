package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

public class Cena4 extends Cena {

    private TextButton inspecionarQuadroBtn;
    private TextButton escadasBtn;

    

    public Cena4(Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super("imagem4.jpg", stage, bitmap, listaObjetos);
        
        // Adiciona todos os textos da cena
        this.textos.put(Texto.C4_INICIAL, "O hall da casa é sombrio e silencioso. Há uma escadaria que leva ao andar superior e um quadro\nao centro.");
        this.textos.put(Texto.C4_QUADRO, "Na parede, há um quadro de uma família. Os rostos estão borrados, mas os sorrisos permanecem.\nVocê sente uma" +
        " estranha familiaridade com a cena.");

        this.setTextoAtual(this.textos.get(Texto.C4_INICIAL));

        this.inspecionarQuadroBtn = new TextButton("1. Inspecionar o quadro.", this.btnStyle);
        this.inspecionarQuadroBtn.setSize(1000, 50);
        this.inspecionarQuadroBtn.setPosition(33, 200);
        this.inspecionarQuadroBtn.setVisible(false);
        this.inspecionarQuadroBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!inspecionarQuadroBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C4_QUADRO));
                    inspecionarQuadroBtn.setDisabled(true);
                    estado = 0;
                }
                return true;
            }
        });


        this.escadasBtn = new TextButton("2. Subir as escadas.", this.btnStyle);
        this.escadasBtn.setSize(1000, 50);
        this.escadasBtn.setPosition(33, 160);
        this.escadasBtn.setVisible(false);
        this.escadasBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!escadasBtn.isDisabled()) {
                    setTextoAtual("");
                    escadasBtn.setDisabled(true);
                    estado = 1;
                    ended = true; 
                }
                return true;
            }
        });

        // Adiciona todos os botoes na tela
        this.stage.addActor(this.inspecionarQuadroBtn);
        this.stage.addActor(this.escadasBtn);
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
            this.inspecionarQuadroBtn.setVisible(true);
            this.escadasBtn.setVisible(true);

        }

        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }

    @Override
    public void init() {
        this.inspecionarQuadroBtn.setDisabled(false);
        this.escadasBtn.setDisabled(false);
        this.ended = false;
        this.estado = 0;
        this.setTextoAtual(this.textos.get(Texto.C4_INICIAL));
    }

    @Override
    public void close() {
        this.inspecionarQuadroBtn.setVisible(false);
        this.escadasBtn.setVisible(false);
    }
}