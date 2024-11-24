package com.game.naovoltepracasa;

import com.badlogic.gdx.graphics.Texture;

public enum Objeto {
    LANTERNA("lanterna.png"),
    BATERIAS("bateria.png"),
    CHAVE("chave.png");

    private final Texture img;

    private Objeto(final String path) {
        this.img = new Texture(path);
    }

    public Texture getImg() {
        return this.img;
    }
}