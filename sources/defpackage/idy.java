package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.device.HiHealthDeviceInfo;

/* loaded from: classes.dex */
public class idy {
    private long mEndTime;
    private String mMetaData;
    private HiHealthDeviceInfo mSourceDevice;
    private long mStartTime;
    private int mType;
    private long mUpdateTime;
    protected ContentValues mValueHolder;

    public idy(int i, long j, long j2, String str, HiHealthDeviceInfo hiHealthDeviceInfo) {
        this.mType = i;
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mMetaData = str;
        this.mSourceDevice = hiHealthDeviceInfo;
        ContentValues contentValues = new ContentValues();
        this.mValueHolder = contentValues;
        contentValues.put("meta_data", str);
    }

    public idy(int i, long j, long j2, String str) {
        this(i, j, j2, str, null);
    }

    public idy() {
        this(0, 0L, 0L, null, null);
    }

    public ContentValues getValueHolder() {
        return this.mValueHolder;
    }

    public void setValueHolder(ContentValues contentValues) {
        this.mValueHolder = contentValues;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public void setUpdateTime(long j) {
        this.mUpdateTime = j;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public HiHealthDeviceInfo getSourceDevice() {
        return this.mSourceDevice;
    }

    public void setSourceDevice(HiHealthDeviceInfo hiHealthDeviceInfo) {
        this.mSourceDevice = hiHealthDeviceInfo;
    }

    public String getMetaData() {
        return this.mMetaData;
    }

    public void setMetaData(String str) {
        this.mMetaData = str;
    }

    public void setString(String str, String str2) {
        this.mValueHolder.put(str, str2);
    }
}
