package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

public class Cena5 extends Cena {
    
    private TextButton inspecionararCorposBtn;
    private TextButton armarioBtn;
    private TextButton cartaBtn; 
    private TextButton irCaixaBtn;
    private TextButton continuarBtn;
    private TextButton continuar2Btn;
    private TextButton continuar3Btn;

    private int btnIniciaisCont;

    public Cena5(Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super("imagem5.jpg", stage, bitmap, listaObjetos);

        this.btnIniciaisCont = 0;
        
        // Adiciona todos os textos da cena
        this.textos.put(Texto.C5_INICIAL, "Você está em um quarto completamente escuro. O ar está pesado e cheira a mofo. Algo parece\n" +
        "observar você nas sombras. Está muito escuro. Você não consegue distinguir nada. " + 
        "Você liga a lanterna e\nilumina o quarto. Há uma cama com dois corpos abraçados cobertos por um lençol, " +
        "um armário encostado\nna parede e uma mesa de cabeceira com uma carta.");
        this.textos.put(Texto.C5_CORPOS, "Os corpos parecem estar dormindo, mas estão rígidos e frios. Um adulto abraça uma criança.\n" +
        "Algo sobre eles faz seu coração apertar.");
        this.textos.put(Texto.C5_ARMARIO,"O armário range ao ser aberto. Dentro, há um uniforme médico com as iniciais E.W.");
        this.textos.put(Texto.C5_CARTA, "Oi, amor, Saí para buscar as crianças na escola. Não esqueça do mercado. Alice quer bolo de\nchocolate " +
        "hoje. Estarei no jardim quando você voltar. Com amor, Elizabeth W.");
        this.textos.put(Texto.C5_CONTINUAR, "Foi visto uma caixa ao lado da porta do quarto.");
        this.textos.put(Texto.C5_CAIXA, "Ao abrir, foi encontrado recortes de jornais e um colar infantil. Os recortes dizem: " +
        "'Família\nWinters morre em acidente na estrada principal. Você lembra da última vez que colocou o colar em\nAlice.");
        this.textos.put(Texto.C5_FINAL, "Sua ficha cai e Elizabeth relembra de todos os fatos, ela percebe que esta morta... porém\nse conforma. " +
        "Ela deita na cama entre os corpos de sua família. Enquanto fecha os olhos, sente uma\npaz profunda. " +
        "O quarto é tomado por luz, e risos infantis ecoam. Sua jornada chegou ao fim.");

        this.setTextoAtual(this.textos.get(Texto.C5_INICIAL));
        
        // Configura todos os botes
        this.inspecionararCorposBtn = new TextButton("1. Inspecionar os corpos.", this.btnStyle);
        this.inspecionararCorposBtn.setSize(1000, 50);
        this.inspecionararCorposBtn.setPosition(33, 200);
        this.inspecionararCorposBtn.setVisible(false);
        this.inspecionararCorposBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!inspecionararCorposBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C5_CORPOS));
                    inspecionararCorposBtn.setDisabled(true);
                    ++btnIniciaisCont;
                    if(btnIniciaisCont < 3) estado = 0;
                    else estado = 1;
                }
                return true;
            }
        });

        this.armarioBtn = new TextButton("2. Olhar armário.", this.btnStyle);
        this.armarioBtn.setSize(1000, 50);
        this.armarioBtn.setPosition(33, 160);
        this.armarioBtn.setVisible(false);
        this.armarioBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!armarioBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C5_ARMARIO));
                    armarioBtn.setDisabled(true);
                    ++btnIniciaisCont;
                    if(btnIniciaisCont < 3) estado = 0;
                    else estado = 1;
                }
                return true;
            }
        });

        this.cartaBtn = new TextButton("3. Ler carta.", this.btnStyle);
        this.cartaBtn.setSize(1000, 50);
        this.cartaBtn.setPosition(33, 120);
        this.cartaBtn.setVisible(false);
        this.cartaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!cartaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C5_CARTA));
                    cartaBtn.setDisabled(true);
                    ++btnIniciaisCont;
                    if(btnIniciaisCont < 3) estado = 0;
                    else estado = 1;
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
                    setTextoAtual(textos.get(Texto.C5_CONTINUAR));
                    estado = 2;
                    continuarBtn.setDisabled(true);
                }
                return true;
            }
        });
        
        this.irCaixaBtn = new TextButton("1. Verificar caixa.", this.btnStyle);
        this.irCaixaBtn.setSize(1000, 50);
        this.irCaixaBtn.setPosition(33, 200);
        this.irCaixaBtn.setVisible(false);
        this.irCaixaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!irCaixaBtn.isDisabled()) {
                    setTextoAtual(textos.get(Texto.C5_CAIXA));
                    estado = 3;
                    irCaixaBtn.setDisabled(true);
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
                    setTextoAtual(textos.get(Texto.C5_FINAL));
                    estado = 4;
                    continuar2Btn.setDisabled(true);
                }
                return true;
            }
        });


        this.continuar3Btn = new TextButton("1. Continuar.", this.btnStyle);
        this.continuar3Btn.setSize(1000, 50);
        this.continuar3Btn.setPosition(33, 200);
        this.continuar3Btn.setVisible(false);
        this.continuar3Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(!continuar3Btn.isDisabled()) {
                    setTextoAtual("");
                    estado = 5;
                    continuar3Btn.setDisabled(true);
                    ended = true;
                }
                return true;
            }
        });


        // Adiciona todos os botoes na tela
        this.stage.addActor(this.inspecionararCorposBtn);
        this.stage.addActor(this.armarioBtn);
        this.stage.addActor(this.cartaBtn);
        this.stage.addActor(this.irCaixaBtn);
        this.stage.addActor(this.continuarBtn);
        this.stage.addActor(this.continuar2Btn);
        this.stage.addActor(this.continuar3Btn);
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
            this.inspecionararCorposBtn.setVisible(true);
            this.armarioBtn.setVisible(true);
            this.cartaBtn.setVisible(true);
            this.continuarBtn.setVisible(false);
            this.irCaixaBtn.setVisible(false);
            this.continuar2Btn.setVisible(false);
            this.continuar3Btn.setVisible(false);
        } else if(estado == 1) {
            this.inspecionararCorposBtn.setVisible(false);
            this.armarioBtn.setVisible(false);
            this.cartaBtn.setVisible(false);
            this.continuarBtn.setVisible(true);
            this.irCaixaBtn.setVisible(false);
            this.continuar2Btn.setVisible(false);
            this.continuar3Btn.setVisible(false);
        } else if(estado == 2) {
            this.inspecionararCorposBtn.setVisible(false);
            this.armarioBtn.setVisible(false);
            this.cartaBtn.setVisible(false);
            this.continuarBtn.setVisible(false);
            this.irCaixaBtn.setVisible(true);
            this.continuar2Btn.setVisible(false);
            this.continuar3Btn.setVisible(false);
        } else if(estado == 3) {
            this.inspecionararCorposBtn.setVisible(false);
            this.armarioBtn.setVisible(false);
            this.cartaBtn.setVisible(false);
            this.continuarBtn.setVisible(false);
            this.irCaixaBtn.setVisible(false);
            this.continuar2Btn.setVisible(true);
            this.continuar3Btn.setVisible(false);
        } else if(estado == 4) {
            this.inspecionararCorposBtn.setVisible(false);
            this.armarioBtn.setVisible(false);
            this.cartaBtn.setVisible(false);
            this.continuarBtn.setVisible(false);
            this.irCaixaBtn.setVisible(false);
            this.continuar2Btn.setVisible(false);
            this.continuar3Btn.setVisible(true);
        }

        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }

    @Override
    public void init() {
        this.inspecionararCorposBtn.setDisabled(false);
        this.armarioBtn.setDisabled(false);
        this.continuarBtn.setDisabled(false);
        this.cartaBtn.setDisabled(false);
        this.irCaixaBtn.setDisabled(false);
        this.continuar2Btn.setDisabled(false);
        this.continuar3Btn.setDisabled(false);
        this.ended = false;
        this.estado = 0;
        this.setTextoAtual(this.textos.get(Texto.C5_INICIAL));
    }

    @Override
    public void close() {
        this.inspecionararCorposBtn.setVisible(false);
        this.armarioBtn.setVisible(false);
        this.continuarBtn.setVisible(false);
        this.cartaBtn.setVisible(false);
        this.irCaixaBtn.setVisible(false);
        this.continuar2Btn.setVisible(false);
        this.continuar3Btn.setVisible(false);
    }
}