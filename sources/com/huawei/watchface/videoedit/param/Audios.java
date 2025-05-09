package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.List;

/* loaded from: classes9.dex */
public class Audios {

    @SerializedName("auditionEndTime")
    @Expose
    private long auditionEndTime;

    @SerializedName("auditionStartTime")
    @Expose
    private long auditionStartTime;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("lyricPath")
    @Expose
    private String lyricPath;

    @SerializedName("music_id")
    @Expose
    private String musicId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName(BleConstants.KEY_PATH)
    @Expose
    private String path;

    @SerializedName("platform")
    @Expose
    private String platform;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("wave_points")
    @Expose
    private List<Integer> wavePoints;

    public String getLyricPath() {
        return this.lyricPath;
    }

    public Audios withLyricPath(String str) {
        this.lyricPath = str;
        return this;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public Audios withPlatform(String str) {
        this.platform = str;
        return this;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public Audios withId(String str) {
        this.id = str;
        return this;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public Audios withType(String str) {
        this.type = str;
        return this;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public Audios withDuration(int i) {
        this.duration = i;
        return this;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getPath() {
        return this.path;
    }

    public Audios withPath(String str) {
        this.path = str;
        return this;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public Audios withName(String str) {
        this.name = str;
        return this;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public Audios withCategoryName(String str) {
        this.categoryName = str;
        return this;
    }

    public void setWavePoints(List<Integer> list) {
        this.wavePoints = list;
    }

    public List<Integer> getWavePoints() {
        return this.wavePoints;
    }

    public Audios withWavePoints(List<Integer> list) {
        this.wavePoints = list;
        return this;
    }

    public void setMusicId(String str) {
        this.musicId = str;
    }

    public String getMusicId() {
        return this.musicId;
    }

    public Audios withMusicId(String str) {
        this.musicId = str;
        return this;
    }

    public void setAuditionStartTime(long j) {
        this.auditionStartTime = j;
    }

    public long getAuditionStartTime() {
        return this.auditionStartTime;
    }

    public Audios withAuditionStartTime(long j) {
        this.auditionStartTime = j;
        return this;
    }

    public void setAuditionEndTime(long j) {
        this.auditionEndTime = j;
    }

    public long getAuditionEndTime() {
        return this.auditionEndTime;
    }

    public Audios withAuditionEndTime(long j) {
        this.auditionEndTime = j;
        return this;
    }
}
