package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.xa;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TrackInfo implements Parcelable {
    private static final String KEY_BITRATE = "BITRATE";
    private static final String KEY_CHANNEL_COUNT = "CHANNEL_COUNT";
    private static final String KEY_GROUP_INDEX = "GROUP_INDEX";
    private static final String KEY_HEIGHT = "HEIGHT";
    private static final String KEY_LANGUAGE = "LANGUAGE";
    private static final String KEY_RENDERER_INDEX = "RENDERER_INDEX";
    private static final String KEY_SELECTED = "IS_SELECTED";
    private static final String KEY_TRACK_INDEX = "TRACK_INDEX";
    private static final String KEY_TRACK_NAME = "TRACK_NAME";
    private static final String KEY_WIDTH = "WIDTH";
    private static final String TAG = "HiSight-S-TrackInfo";
    private JSONObject mInfoObject;
    public static final Parcelable.Creator<TrackInfo> CREATOR = new Parcelable.Creator<TrackInfo>() { // from class: com.huawei.android.airsharing.api.TrackInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dS_, reason: merged with bridge method [inline-methods] */
        public TrackInfo createFromParcel(Parcel parcel) {
            return new TrackInfo(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TrackInfo[] newArray(int i) {
            return new TrackInfo[i];
        }
    };
    private static xa sLog = xa.c();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrackInfo() {
        this.mInfoObject = new JSONObject();
    }

    private TrackInfo(String str) {
        this.mInfoObject = new JSONObject();
        try {
            this.mInfoObject = new JSONObject(str);
        } catch (JSONException e) {
            sLog.d(TAG, "ERROR: createFromParcel JSONException " + e);
        }
    }

    private int getRendererIndex() {
        return getInt(KEY_RENDERER_INDEX);
    }

    private boolean setRendererIndex(int i) {
        return putInt(KEY_RENDERER_INDEX, i);
    }

    private int getGroupIndex() {
        return getInt(KEY_GROUP_INDEX);
    }

    private boolean setGroupIndex(int i) {
        return putInt(KEY_GROUP_INDEX, i);
    }

    private int getTrackIndex() {
        return getInt(KEY_TRACK_INDEX);
    }

    private boolean setTrackIndex(int i) {
        return putInt(KEY_TRACK_INDEX, i);
    }

    public int getWidth() {
        return getInt(KEY_WIDTH);
    }

    public boolean setWidth(int i) {
        return putInt(KEY_WIDTH, i);
    }

    public int getHeight() {
        return getInt(KEY_HEIGHT);
    }

    public boolean setHeight(int i) {
        return putInt(KEY_HEIGHT, i);
    }

    public int getBitrate() {
        return getInt(KEY_BITRATE);
    }

    public boolean setBitrate(int i) {
        return putInt(KEY_BITRATE, i);
    }

    public int getChannelCount() {
        return getInt("CHANNEL_COUNT");
    }

    public boolean setChannelCount(int i) {
        return putInt("CHANNEL_COUNT", i);
    }

    public String getLanguage() {
        return getString("LANGUAGE");
    }

    public boolean setLanguage(String str) {
        return putString("LANGUAGE", str);
    }

    public String getTrackName() {
        return getString(KEY_TRACK_NAME);
    }

    public boolean setTrackName(String str) {
        return putString(KEY_TRACK_NAME, str);
    }

    public boolean isSelected() {
        return getBoolean(KEY_SELECTED);
    }

    public boolean setSelected(boolean z) {
        return putBoolean(KEY_SELECTED, z);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mInfoObject.toString());
    }

    public boolean putString(String str, String str2) {
        if (!verifyInputParameters(str, str2)) {
            sLog.d(TAG, "putString key or value is illegal, key is " + str + ", value is " + str2);
            return false;
        }
        try {
            this.mInfoObject.put(str, str2);
            return true;
        } catch (JSONException unused) {
            sLog.d(TAG, "putString throw JSONException, key: " + str);
            return false;
        }
    }

    public String getString(String str) {
        return this.mInfoObject.optString(str, "");
    }

    public boolean putInt(String str, int i) {
        if (!verifyInputParameters(str, i)) {
            sLog.d(TAG, "putInt key or value is illegal, key is " + str + ", value is " + i);
            return false;
        }
        try {
            this.mInfoObject.put(str, i);
            return true;
        } catch (JSONException unused) {
            sLog.d(TAG, "TrackInfo putInt throw JSONException, key: " + str);
            return false;
        }
    }

    public int getInt(String str) {
        return this.mInfoObject.optInt(str, -1);
    }

    public boolean putBoolean(String str, boolean z) {
        if (!verifyInputParameters(str)) {
            sLog.d(TAG, "putBoolean key or value is illegal, key is " + str + ", value is " + z);
            return false;
        }
        try {
            this.mInfoObject.put(str, z);
            return true;
        } catch (JSONException unused) {
            sLog.d(TAG, "TrackInfo putInt throw JSONException, key: " + str);
            return false;
        }
    }

    public boolean getBoolean(String str) {
        return this.mInfoObject.optBoolean(str, false);
    }

    public boolean isEmpty(String str) {
        return this.mInfoObject.isNull(str);
    }

    private boolean verifyInputParameters(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : true;
    }

    private boolean verifyInputParameters(String str, int i) {
        return !TextUtils.isEmpty(str) && i >= 0;
    }

    private boolean verifyInputParameters(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static TrackInfo toJavaBean(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        TrackInfo trackInfo = new TrackInfo();
        trackInfo.setRendererIndex(jSONObject.optInt(KEY_RENDERER_INDEX));
        trackInfo.setGroupIndex(jSONObject.optInt(KEY_GROUP_INDEX));
        trackInfo.setTrackIndex(jSONObject.optInt(KEY_TRACK_INDEX));
        trackInfo.setTrackName(jSONObject.optString(KEY_TRACK_NAME));
        trackInfo.setSelected(jSONObject.optBoolean(KEY_SELECTED));
        trackInfo.setWidth(jSONObject.optInt(KEY_WIDTH));
        trackInfo.setHeight(jSONObject.optInt(KEY_HEIGHT));
        trackInfo.setBitrate(jSONObject.optInt(KEY_BITRATE));
        trackInfo.setChannelCount(jSONObject.optInt("CHANNEL_COUNT"));
        trackInfo.setLanguage(jSONObject.optString("LANGUAGE"));
        return trackInfo;
    }

    public JSONObject toJsonObject() {
        return this.mInfoObject;
    }
}
