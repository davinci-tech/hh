package com.huawei.watchface.videoedit.param;

/* loaded from: classes9.dex */
public class VideoSize {
    public int height;
    public int width;

    public VideoSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof VideoSize) {
            if (obj != this) {
                VideoSize videoSize = (VideoSize) obj;
                if (this.width != videoSize.width || this.height != videoSize.height) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
