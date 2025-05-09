package com.huawei.watchface.videoedit.param;

/* loaded from: classes9.dex */
public class PlayerBean {
    private int fadeInDuration;
    private int fadeOutDuration;
    private int fadeoutStart;
    private int height;
    private String id;
    private String materiaId;
    private int offset;
    private String path;
    private SourceTimeRange sourceTimeRange;
    private float speed;
    private TargetTimeRange targetTimeRange;
    private String type;
    private int volume;
    private int width;

    public PlayerBean() {
    }

    public PlayerBean(PlayerBean playerBean) {
        this.type = playerBean.getType();
        this.materiaId = playerBean.getMaterialId();
        this.path = playerBean.getPath();
        this.id = playerBean.getId();
        this.speed = playerBean.getSpeed();
        this.volume = playerBean.getVolume();
        this.width = playerBean.getWidth();
        this.height = playerBean.getHeight();
        this.sourceTimeRange = new SourceTimeRange(playerBean.getSourceTimeRange().getStart(), playerBean.getSourceTimeRange().getDuration());
        this.targetTimeRange = new TargetTimeRange(playerBean.getTargetTimeRange().getStart(), playerBean.getTargetTimeRange().getDuration());
    }

    public final SourceTimeRange getSourceTimeRange() {
        return this.sourceTimeRange;
    }

    public PlayerBean setSourceTimeRange(SourceTimeRange sourceTimeRange) {
        this.sourceTimeRange = sourceTimeRange;
        return this;
    }

    public final TargetTimeRange getTargetTimeRange() {
        return this.targetTimeRange;
    }

    public PlayerBean setTargetTimeRange(TargetTimeRange targetTimeRange) {
        this.targetTimeRange = targetTimeRange;
        return this;
    }

    public final String getType() {
        return this.type;
    }

    public PlayerBean setType(String str) {
        this.type = str;
        return this;
    }

    public final String getMaterialId() {
        return this.materiaId;
    }

    public PlayerBean setMaterialId(String str) {
        this.materiaId = str;
        return this;
    }

    public final String getId() {
        return this.id;
    }

    public PlayerBean setId(String str) {
        this.id = str;
        return this;
    }

    public final float getSpeed() {
        float f = this.speed;
        if (f <= 0.0f) {
            return 1.0f;
        }
        return f;
    }

    public PlayerBean setSpeed(float f) {
        this.speed = f;
        return this;
    }

    public final int getVolume() {
        return this.volume;
    }

    public final int getVolume(int i) {
        this.offset = i - this.targetTimeRange.getStart();
        int duration = this.targetTimeRange.getDuration();
        int i2 = this.fadeOutDuration;
        int i3 = duration - i2;
        this.fadeoutStart = i3;
        int i4 = this.offset;
        int i5 = this.fadeInDuration;
        if (i4 >= i5 && i4 <= i3) {
            return this.volume;
        }
        if (i4 < i5) {
            return (this.volume * i4) / i5;
        }
        int i6 = this.volume;
        return i6 - (((i4 - i3) * i6) / i2);
    }

    public PlayerBean setVolume(int i) {
        this.volume = i;
        return this;
    }

    public final String getPath() {
        return this.path;
    }

    public PlayerBean setPath(String str) {
        this.path = str;
        return this;
    }

    public final int getWidth() {
        return this.width;
    }

    public PlayerBean setWidth(int i) {
        this.width = i;
        return this;
    }

    public final int getHeight() {
        return this.height;
    }

    public PlayerBean setHeight(int i) {
        this.height = i;
        return this;
    }

    public int getFadeInDuration() {
        return this.fadeInDuration;
    }

    public int getFadeOutDuration() {
        return this.fadeOutDuration;
    }

    public PlayerBean setFadeInDuration(int i) {
        this.fadeInDuration = i;
        return this;
    }

    public PlayerBean setFadeOutDuration(int i) {
        this.fadeOutDuration = i;
        return this;
    }
}
