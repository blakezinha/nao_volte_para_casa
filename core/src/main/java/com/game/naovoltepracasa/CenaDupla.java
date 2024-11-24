package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Set;

public class CenaDupla extends Cena {
    
    private Cena cena1;
    private Cena cena2;
    private long lastTime;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    
    public CenaDupla(Cena cena1, Cena cena2, Stage stage, BitmapFont bitmap, Set<Objeto> listaObjetos){
        super(null, stage, bitmap, listaObjetos);
        this.cena1 = cena1;
        this.cena2 = cena2;

        // Gerador de fonte personalizado   
        this.fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonte1.ttf"));
        this.fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        this.fontParameter.size = 60;
        this.bitmap = fontGenerator.generateFont(this.fontParameter);
    }

    @Override
    public void run() {
        if(this.objetos.contains(Objeto.LANTERNA) && this.objetos.contains(Objeto.BATERIAS)) {
            this.bitmap.setColor(Color.BLACK);
            
            if(!this.cena1.hasEnded()) {
                this.cena1.run();
                this.lastTime = TimeUtils.millis();
            } else {
                ScreenUtils.clear(Color.WHITE);
                
                if(this.getTextoAtual().isEmpty()) this.setTextoAtual("Voce encontrou o descanso\n\n\n\n\n\n              RECOMECAR");

                this.stage.act();
                this.stage.getBatch().begin();
                this.bitmap.draw(this.stage.getBatch(), this.typeText(10), 
                                 Gdx.graphics.getWidth() / 2f - (26f * 13f), 
                                 Gdx.graphics.getHeight() / 2f);
                this.stage.getBatch().end();
                this.fimJogo();
            }
        } else {
            this.bitmap.setColor(Color.WHITE);

            if(!this.cena2.hasEnded()) {
                this.cena2.run();
                this.lastTime = TimeUtils.millis();
            } else {
                ScreenUtils.clear(Color.BLACK);

                if(this.getTextoAtual().isEmpty()) this.setTextoAtual("Voce esta presa na escuridao\n\n\n\n\n\n               RECOMECAR");

                this.stage.act();
                this.stage.getBatch().begin();
                this.bitmap.draw(this.stage.getBatch(), this.typeText(10), 
                                 Gdx.graphics.getWidth() / 2f - (29f * 12.8f),
                                 Gdx.graphics.getHeight() / 2f);
                this.stage.getBatch().end();
                this.fimJogo();
            }
        }
    }

    @Override
    public void dispose() {
        this.cena1.dispose();
        this.cena2.dispose();
    }

    @Override
    public boolean hasEnded() {
        return ended;
    }

    @Override
    public void close() {
        this.cena1.close();
        this.cena2.close();
    }

    @Override
    public void init() {
        this.ended = false;
        this.cena1.init();
        this.cena2.init();
        this.setTextoAtual("");
    }

    @Override
    public void setObjetos(Set<Objeto> objetos) {
        this.objetos = objetos;
        this.cena1.setObjetos(objetos);
        this.cena2.setObjetos(objetos);
    }

    public void fimJogo() {
        if(TimeUtils.millis() - this.lastTime >= 1000) {
            if(Gdx.input.isTouched()) {
                this.ended = true;
            }
        }
    }
}