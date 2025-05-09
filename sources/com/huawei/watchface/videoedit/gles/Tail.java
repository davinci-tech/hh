package com.huawei.watchface.videoedit.gles;

import com.huawei.watchface.videoedit.gles.glutils.TimeRange;
import java.util.Objects;
import org.w3c.dom.Text;

/* loaded from: classes9.dex */
public class Tail {
    private int mIndex;
    private boolean mIsPreviewMode = true;
    private String mName;
    private Text mText;
    private TimeRange mTimeRange;

    public Tail(int i, TimeRange timeRange, Text text, String str) {
        this.mIndex = i;
        this.mTimeRange = timeRange;
        this.mText = text;
        this.mName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tail)) {
            return false;
        }
        Tail tail = (Tail) obj;
        return this.mIndex == tail.mIndex && Objects.equals(this.mTimeRange, tail.mTimeRange) && Objects.equals(this.mText, tail.mText);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mIndex), this.mTimeRange, this.mText);
    }

    public int getIndex() {
        return this.mIndex;
    }

    public TimeRange getTimeRange() {
        return this.mTimeRange;
    }

    public Text getText() {
        return this.mText;
    }

    public String toString() {
        if (this.mText == null) {
            return "";
        }
        return "index: " + this.mIndex + System.lineSeparator() + "timeRange: " + this.mTimeRange;
    }

    public boolean isPreviewMode() {
        return this.mIsPreviewMode;
    }

    public String getName() {
        return this.mName;
    }
}
