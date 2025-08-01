package com.mycompany.app.Models;

public class Subjects {
    private float math;
    private float physics;
    private float it;
    private float literature;
    private float english;
    private float japanese;

    public Subjects() {}

    public Subjects(float math, float physics, float it, float literature, float english, float japanese) {
        this.math = math;
        this.physics = physics;
        this.it = it;
        this.literature = literature;
        this.english = english;
        this.japanese = japanese;
    }

    public float getMath() {
        return math;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public float getPhysics() {
        return physics;
    }

    public void setPhysics(float physics) {
        this.physics = physics;
    }

    public float getIt() {
        return it;
    }

    public void setIt(float it) {
        this.it = it;
    }

    public float getLiterature() {
        return literature;
    }

    public void setLiterature(float literature) {
        this.literature = literature;
    }

    public float getEnglish() {
        return english;
    }

    public void setEnglish(float english) {
        this.english = english;
    }

    public float getJapanese() {
        return japanese;
    }

    public void setJapanese(float japanese) {
        this.japanese = japanese;
    }
}
