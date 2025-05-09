package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.xa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TrackInfoSet implements Parcelable {
    public static final String KEY_AUDIO_HAS_SELECTION_OVERRIDE = "AUDIO_HAS_SELECTION_OVERRIDE";
    public static final String KEY_AUDIO_TRACKINFO_LIST = "AUDIO_TRACK_INFO_LIST";
    public static final String KEY_IS_AUDIO_SUPPORT_AUTO = "IS_AUDIO_SUPPORT_AUTO";
    public static final String KEY_IS_VIDEO_SUPPORT_AUTO = "IS_VIDEO_SUPPORT_AUTO";
    public static final String KEY_SUBTITLE_TRACKINFO_LIST = "SUBTITLE_TRACK_INFO_LIST";
    public static final String KEY_VIDEO_HAS_SELECTION_OVERRIDE = "VIDEO_HAS_SELECTION_OVERRIDE";
    public static final String KEY_VIDEO_TRACKINFO_LIST = "VIDEO_TRACK_INFO_LIST";
    private static final String TAG = "HiSight-S-TrackInfoSet";
    private boolean mAudioHasSelectionOverride;
    private List<TrackInfo> mAudioTrackInfoList;
    private boolean mIsAudioSupportAuto;
    private boolean mIsVideoSupportAuto;
    private List<TrackInfo> mSubtitleTrackInfoList;
    private boolean mVideoHasSelectionOverride;
    private List<TrackInfo> mVideoTrackInfoList;
    public static final Parcelable.Creator<TrackInfoSet> CREATOR = new Parcelable.Creator<TrackInfoSet>() { // from class: com.huawei.android.airsharing.api.TrackInfoSet.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dT_, reason: merged with bridge method [inline-methods] */
        public TrackInfoSet createFromParcel(Parcel parcel) {
            return new TrackInfoSet(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public TrackInfoSet[] newArray(int i) {
            return new TrackInfoSet[i];
        }
    };
    private static xa sLog = xa.c();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrackInfoSet(List<TrackInfo> list, boolean z, List<TrackInfo> list2, boolean z2) {
        this.mAudioTrackInfoList = list;
        this.mAudioHasSelectionOverride = z;
        this.mVideoTrackInfoList = list2;
        this.mVideoHasSelectionOverride = z2;
    }

    protected TrackInfoSet(Parcel parcel) {
        this.mAudioHasSelectionOverride = parcel.readByte() != 0;
        this.mIsAudioSupportAuto = parcel.readByte() != 0;
        this.mAudioTrackInfoList = parcel.createTypedArrayList(TrackInfo.CREATOR);
        this.mVideoHasSelectionOverride = parcel.readByte() != 0;
        this.mIsVideoSupportAuto = parcel.readByte() != 0;
        this.mVideoTrackInfoList = parcel.createTypedArrayList(TrackInfo.CREATOR);
        this.mSubtitleTrackInfoList = parcel.createTypedArrayList(TrackInfo.CREATOR);
    }

    public boolean isAudioHasSelectionOverride() {
        return this.mAudioHasSelectionOverride;
    }

    public void setAudioHasSelectionOverride(boolean z) {
        this.mAudioHasSelectionOverride = z;
    }

    public List<TrackInfo> getAudioTrackInfoList() {
        return this.mAudioTrackInfoList;
    }

    public void setAudioTrackInfoList(List<TrackInfo> list) {
        this.mAudioTrackInfoList = list;
    }

    public boolean isVideoHasSelectionOverride() {
        return this.mVideoHasSelectionOverride;
    }

    public void setVideoHasSelectionOverride(boolean z) {
        this.mVideoHasSelectionOverride = z;
    }

    public List<TrackInfo> getVideoTrackInfoList() {
        return this.mVideoTrackInfoList;
    }

    public void setVideoTrackInfoList(List<TrackInfo> list) {
        this.mVideoTrackInfoList = list;
    }

    public List<TrackInfo> getSubtitleTrackInfoList() {
        return this.mSubtitleTrackInfoList;
    }

    public void setSubtitleTrackInfoList(List<TrackInfo> list) {
        this.mSubtitleTrackInfoList = list;
    }

    public boolean isAudioSupportAuto() {
        return this.mIsAudioSupportAuto;
    }

    public void setAudioSupportAuto(boolean z) {
        this.mIsAudioSupportAuto = z;
    }

    public boolean isVideoSupportAuto() {
        return this.mIsVideoSupportAuto;
    }

    public void setVideoSupportAuto(boolean z) {
        this.mIsVideoSupportAuto = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeByte(this.mAudioHasSelectionOverride ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsAudioSupportAuto ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.mAudioTrackInfoList);
        parcel.writeByte(this.mVideoHasSelectionOverride ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsVideoSupportAuto ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.mVideoTrackInfoList);
        parcel.writeTypedList(this.mSubtitleTrackInfoList);
    }

    public static TrackInfoSet toJavaBean(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            List<TrackInfo> trackInfoList = toTrackInfoList(jSONObject.optJSONArray(KEY_AUDIO_TRACKINFO_LIST));
            boolean optBoolean = jSONObject.optBoolean(KEY_AUDIO_HAS_SELECTION_OVERRIDE);
            boolean optBoolean2 = jSONObject.optBoolean(KEY_IS_AUDIO_SUPPORT_AUTO);
            List<TrackInfo> trackInfoList2 = toTrackInfoList(jSONObject.optJSONArray(KEY_VIDEO_TRACKINFO_LIST));
            boolean optBoolean3 = jSONObject.optBoolean(KEY_VIDEO_HAS_SELECTION_OVERRIDE);
            boolean optBoolean4 = jSONObject.optBoolean(KEY_IS_VIDEO_SUPPORT_AUTO);
            List<TrackInfo> trackInfoList3 = toTrackInfoList(jSONObject.optJSONArray(KEY_SUBTITLE_TRACKINFO_LIST));
            TrackInfoSet trackInfoSet = new TrackInfoSet(trackInfoList, optBoolean, trackInfoList2, optBoolean3);
            trackInfoSet.setAudioSupportAuto(optBoolean2);
            trackInfoSet.setVideoSupportAuto(optBoolean4);
            trackInfoSet.setSubtitleTrackInfoList(trackInfoList3);
            return trackInfoSet;
        } catch (IllegalArgumentException | JSONException e) {
            sLog.d(TAG, "ERROR: toJavaBean Exception " + e);
            return null;
        }
    }

    private static List<TrackInfo> toTrackInfoList(JSONArray jSONArray) throws JSONException, IllegalArgumentException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            TrackInfo javaBean = TrackInfo.toJavaBean(jSONArray.getJSONObject(i));
            if (javaBean != null) {
                arrayList.add(javaBean);
            }
        }
        return arrayList;
    }

    public JSONObject toJsonObject() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(KEY_AUDIO_HAS_SELECTION_OVERRIDE, Boolean.valueOf(isAudioHasSelectionOverride()));
            hashMap.put(KEY_IS_AUDIO_SUPPORT_AUTO, Boolean.valueOf(isAudioSupportAuto()));
            hashMap.put(KEY_AUDIO_TRACKINFO_LIST, trackListToJSONArray(getAudioTrackInfoList()));
            hashMap.put(KEY_VIDEO_HAS_SELECTION_OVERRIDE, Boolean.valueOf(isVideoHasSelectionOverride()));
            hashMap.put(KEY_IS_VIDEO_SUPPORT_AUTO, Boolean.valueOf(isVideoSupportAuto()));
            hashMap.put(KEY_VIDEO_TRACKINFO_LIST, trackListToJSONArray(getVideoTrackInfoList()));
            return new JSONObject(hashMap);
        } catch (IllegalArgumentException e) {
            sLog.d(TAG, "ERROR: toJsonObject IllegalArgumentException " + e);
            return null;
        }
    }

    private static JSONArray trackListToJSONArray(List<TrackInfo> list) throws IllegalArgumentException {
        JSONArray jSONArray = new JSONArray();
        for (TrackInfo trackInfo : list) {
            if (trackInfo == null) {
                throw new IllegalArgumentException("trackListToJSONArray trackInfo is null");
            }
            jSONArray.put(trackInfo.toJsonObject());
        }
        return jSONArray;
    }
}
