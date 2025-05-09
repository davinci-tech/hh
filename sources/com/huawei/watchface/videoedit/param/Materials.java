package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class Materials {

    @SerializedName("audio_effects")
    @Expose
    private List<String> audioEffects;

    @SerializedName("audio_fades")
    @Expose
    private List<String> audioFades;

    @SerializedName("audios")
    @Expose
    private List<Audios> audios;

    @SerializedName("canvases")
    @Expose
    private List<Canvases> canvases;

    @SerializedName("images")
    @Expose
    private List<String> images;

    @SerializedName("lyricsStyle")
    @Expose
    private int lyricsStyle;

    @SerializedName("material_animations")
    @Expose
    private List<String> materialAnimations;

    @SerializedName("music_volume")
    @Expose
    private int musicVolume;

    @SerializedName("original_volume")
    @Expose
    private int originalVolume;

    @SerializedName("placeholders")
    @Expose
    private List<String> placeholders;

    @SerializedName("stickers")
    @Expose
    private List<String> stickers;

    @SerializedName("texts")
    @Expose
    private List<String> texts;

    @SerializedName("videos")
    @Expose
    private List<Videos> videos;

    public void setVideos(List<Videos> list) {
        this.videos = list;
    }

    public List<Videos> getVideos() {
        return this.videos;
    }

    public Materials withVideos(List<Videos> list) {
        this.videos = list;
        return this;
    }

    public void setAudioFades(List<String> list) {
        this.audioFades = list;
    }

    public List<String> getAudioFades() {
        return this.audioFades;
    }

    public Materials withAudioFades(List<String> list) {
        this.audioFades = list;
        return this;
    }

    public void setCanvases(List<Canvases> list) {
        this.canvases = list;
    }

    public List<Canvases> getCanvases() {
        return this.canvases;
    }

    public Materials withCanvases(List<Canvases> list) {
        this.canvases = list;
        return this;
    }

    public void setImages(List<String> list) {
        this.images = list;
    }

    public List<String> getImages() {
        return this.images;
    }

    public Materials withImages(List<String> list) {
        this.images = list;
        return this;
    }

    public void setStickers(List<String> list) {
        this.stickers = list;
    }

    public List<String> getStickers() {
        return this.stickers;
    }

    public Materials withStickers(List<String> list) {
        this.stickers = list;
        return this;
    }

    public void setAudioEffects(List<String> list) {
        this.audioEffects = list;
    }

    public List<String> getAudioEffects() {
        return this.audioEffects;
    }

    public Materials withAudioEffects(List<String> list) {
        this.audioEffects = list;
        return this;
    }

    public void setTexts(List<String> list) {
        this.texts = list;
    }

    public List<String> getTexts() {
        return this.texts;
    }

    public Materials withTexts(List<String> list) {
        this.texts = list;
        return this;
    }

    public void setMaterialAnimations(List<String> list) {
        this.materialAnimations = list;
    }

    public List<String> getMaterialAnimations() {
        return this.materialAnimations;
    }

    public Materials withMaterialAnimations(List<String> list) {
        this.materialAnimations = list;
        return this;
    }

    public void setAudios(List<Audios> list) {
        this.audios = list;
    }

    public List<Audios> getAudios() {
        return this.audios;
    }

    public Materials withAudios(List<Audios> list) {
        this.audios = list;
        return this;
    }

    public void setPlaceholders(List<String> list) {
        this.placeholders = list;
    }

    public List<String> getPlaceholders() {
        return this.placeholders;
    }

    public Materials withPlaceholders(List<String> list) {
        this.placeholders = list;
        return this;
    }
}
