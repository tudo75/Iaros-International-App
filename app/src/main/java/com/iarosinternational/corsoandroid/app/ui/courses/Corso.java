package com.iarosinternational.corsoandroid.app.ui.courses;

import android.widget.Button;

public class Corso {

    private String mTitolo;
    private String mDataConsegnaModuli;
    private String mSchedaCorso;
    private String mSchedaCorsoUrl;
    private String mSchedaBando;
    private String mSchedaBandoUrl;
    private String mSchedaIscrizione;
    private String mSchedaIscrizioneUrl;

    public String getTitolo() {
        return mTitolo;
    }

    public void setTitolo(String mTitolo) {
        this.mTitolo = mTitolo;
    }

    public String getDataConsegnaModuli() {
        return mDataConsegnaModuli;
    }

    public void setDataConsegnaModuli(String mDataConsegnaModuli) {this.mDataConsegnaModuli = mDataConsegnaModuli;}

    public String getSchedaCorso() {return mSchedaCorso;}

    public void setSchedaCorso(String mSchedaCorso) {this.mSchedaCorso = mSchedaCorso;}

    public void setSchedaCorsoUrl (String mSchedaCorsoUrl) {this.mSchedaCorsoUrl = mSchedaCorsoUrl;}

    public String getSchedaCorsoUrl() {return mSchedaCorsoUrl;}

    public String getSchedaBando() {return mSchedaBando;}

    public void setSchedaBando(String mSchedaBando) {this.mSchedaBando = mSchedaBando;}

    public String getSchedaBandoUrl() {return mSchedaBandoUrl;}

    public void setSchedaBandoUrl(String mSchedaBandoUrl) {this.mSchedaBandoUrl = mSchedaBandoUrl;}

    public String getSchedaIscrizione() {return mSchedaIscrizione;}

    public void setSchedaIscrizione(String mSchedaIscrizione) {this.mSchedaIscrizione = mSchedaIscrizione;}

    public String getSchedaIscrizioneUrl() {return mSchedaIscrizioneUrl;}

    public void setSchedaIscrizioneUrl(String mSchedaIscrizioneUrl) {this.mSchedaIscrizioneUrl = mSchedaIscrizioneUrl;}

}
