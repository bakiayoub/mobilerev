package com.example.tpglobale;

import java.io.Serializable;

public class car implements Serializable {

    private int image;
    private String titre;
    private String desc;

    public car() {
    }

    public car(int image, String titre, String desc) {
        this.image = image;
        this.titre = titre;
        this.desc = desc;
    }

    public car(String titre, String desc) {
        this.titre = titre;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "car{" +
                "titre='" + titre + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
