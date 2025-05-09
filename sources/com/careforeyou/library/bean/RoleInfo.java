package com.careforeyou.library.bean;

/* loaded from: classes2.dex */
public class RoleInfo {
    private int age;
    private int height;
    private int id;
    private int sex;
    private float weight;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int i) {
        this.sex = i;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float f) {
        this.weight = f;
    }

    public String toString() {
        return "RoleInfo{id=" + this.id + ", height=" + this.height + ", sex=" + this.sex + ", age=" + this.age + ", weight=" + this.weight + '}';
    }
}
