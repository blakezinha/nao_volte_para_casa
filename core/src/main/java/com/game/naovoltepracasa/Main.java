package com.game.naovoltepracasa;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private List<Cena> cenas;
    private Texture telaInicial;
    private int cenaAtual;
    private boolean jogoIniciado;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont bitmap;
    private BitmapFont bitmapDefatult;
    private Stage stage;
    private Set<Objeto> objetos = new HashSet<>();
    private Long lastTime;
    private Music music;

    @Override
    public void create() {
        this.cenas = new ArrayList<>();
        this.cenaAtual = 0;
        this.jogoIniciado = false;
        this.telaInicial = new Texture("imagem0.jpg");
        this.stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(this.stage);

        // Gerador de fonte personalizado
        this.fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonte1.ttf"));
        this.fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        
        this.fontParameter.color = Color.BLACK;
        this.fontParameter.size = 60;

        this.bitmap = fontGenerator.generateFont(this.fontParameter);
        this.bitmapDefatult = new BitmapFont();
        this.bitmapDefatult.getData().setScale(1.5f);

        this.music = Gdx.audio.newMusic(Gdx.files.internal("musica.mp3"));
        this.music.setLooping(true);
        this.music.setVolume(.3f);
        this.music.play();

        // Cria e adiciona as cenas
        this.cenas.add(new Cena1(this.stage, this.bitmapDefatult, this.objetos));
        this.cenas.add(new Cena2(this.stage, this.bitmapDefatult, this.objetos));
        this.cenas.add(new Cena3(this.stage, this.bitmapDefatult, this.objetos));
        this.cenas.add(new Cena4(this.stage, this.bitmapDefatult, this.objetos));
        this.cenas.add(new CenaDupla(new Cena5(this.stage, this.bitmapDefatult, this.objetos), 
                                new Cena6( this.stage, this.bitmapDefatult, this.objetos), 
                                this.stage, 
                                this.bitmapDefatult, 
                                this.objetos));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.DARK_GRAY);
        
        if(!this.jogoIniciado) {
            this.desenhaTelaInicial();
        } else {
            if(this.cenaAtual < cenas.size()) {
                if(!cenas.get(this.cenaAtual).hasEnded()) {
                    cenas.get(this.cenaAtual).run();
                } else {
                    cenas.get(this.cenaAtual).close();
                    this.cenaAtual++;
                }

                this.stage.act();
                this.stage.getBatch().begin();
                this.bitmapDefatult.draw(this.stage.getBatch(), "Itens: ", Gdx.graphics.getWidth() - 220, 50f);
                float posX = Gdx.graphics.getWidth() - 220;
                for(Objeto obj : this.objetos) {
                    posX += 55;
                    this.stage.getBatch().draw(obj.getImg(), posX, 20f);
                }
                this.stage.getBatch().end();

                this.lastTime = TimeUtils.millis();
            } else {
                this.cenaAtual = 0;
                this.objetos = new HashSet<>();
                
                // Reseta todas as cenas
                for(Cena cena : this.cenas) {
                    cena.init();
                    cena.setObjetos(this.objetos);
                }
                
                this.jogoIniciado = false;
            }
        }

    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.cenas.get(this.cenaAtual).dispose();
        this.telaInicial.dispose();
    }

    public void desenhaTelaInicial() {
        if(this.lastTime == null || TimeUtils.millis() - this.lastTime >= 1000) {
            if(Gdx.input.isTouched()) {
                this.jogoIniciado = true;
            }
        }

        this.stage.getBatch().begin();
        this.stage.getBatch().draw(telaInicial, -342, 0);
        this.bitmap.draw(this.stage.getBatch(), "Clique para comecar",
                        (Gdx.graphics.getWidth() / 2f) - (19f * 13.5f), 
                        Gdx.graphics.getHeight() / 2f);
        this.stage.getBatch().end();
    }
}
