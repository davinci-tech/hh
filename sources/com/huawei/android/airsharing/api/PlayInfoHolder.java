package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.xa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class PlayInfoHolder implements Parcelable {
    private static final String TAG = "HiSight-PlayInfoHolder";
    private int currentIndex;
    private List<PlayInfo> playInfoList;
    private int progressRefreshInterval;
    public static final Parcelable.Creator<PlayInfoHolder> CREATOR = new Parcelable.Creator<PlayInfoHolder>() { // from class: com.huawei.android.airsharing.api.PlayInfoHolder.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: dP_, reason: merged with bridge method [inline-methods] */
        public PlayInfoHolder createFromParcel(Parcel parcel) {
            return new PlayInfoHolder(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlayInfoHolder[] newArray(int i) {
            return new PlayInfoHolder[i];
        }
    };
    private static xa sLog = xa.c();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayInfoHolder(PlayInfo playInfo) {
        this(playInfo, 0);
    }

    public PlayInfoHolder(PlayInfo playInfo, int i) {
        this.currentIndex = 0;
        this.progressRefreshInterval = i;
        ArrayList arrayList = new ArrayList();
        this.playInfoList = arrayList;
        arrayList.add(playInfo);
    }

    public PlayInfoHolder(int i, List<PlayInfo> list) {
        this(i, 0, list);
    }

    public PlayInfoHolder(int i, int i2, List<PlayInfo> list) {
        this.currentIndex = i;
        this.progressRefreshInterval = i2;
        this.playInfoList = list;
    }

    protected PlayInfoHolder(Parcel parcel) {
        this.progressRefreshInterval = 0;
        this.currentIndex = parcel.readInt();
        this.playInfoList = parcel.createTypedArrayList(PlayInfo.CREATOR);
        this.progressRefreshInterval = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.currentIndex);
        parcel.writeTypedList(this.playInfoList);
        parcel.writeInt(this.progressRefreshInterval);
    }

    public List<PlayInfo> getPlayInfoList() {
        return this.playInfoList;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public int getProgressRefreshInterval() {
        return this.progressRefreshInterval;
    }

    public PlayInfo getCurrPlayInfo() {
        int i;
        List<PlayInfo> list = this.playInfoList;
        if (list == null || (i = this.currentIndex) < 0 || i >= list.size()) {
            return null;
        }
        return this.playInfoList.get(this.currentIndex);
    }

    public void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public void setProgressRefreshInterval(int i) {
        this.progressRefreshInterval = i;
    }

    public void setPlayInfoList(List<PlayInfo> list) {
        this.playInfoList = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(System.lineSeparator());
        sb.append("currentIndex is ");
        sb.append(this.currentIndex);
        sb.append(System.lineSeparator());
        sb.append("progressRefreshInterval is ");
        sb.append(this.progressRefreshInterval);
        sb.append(System.lineSeparator());
        Iterator<PlayInfo> it = this.playInfoList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(System.lineSeparator());
        }
        sb.append("}");
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public static PlayInfoHolder toJavaBean(int i, int i2, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            sLog.d(TAG, "jsonToPlayInfoHolder, jsonArray is illegal");
            return null;
        }
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            try {
                PlayInfo javaBean = PlayInfo.toJavaBean(jSONArray.getJSONObject(i3));
                if (javaBean == null) {
                    sLog.d(TAG, "jsonToPlayInfoHolder, playInfo is illegal");
                    return null;
                }
                arrayList.add(javaBean);
            } catch (JSONException e) {
                sLog.d(TAG, "jsonToPlayInfoHolder, JSONException " + e);
                return null;
            }
        }
        PlayInfoHolder playInfoHolder = new PlayInfoHolder(i, i2, arrayList);
        if (checkPlayHolder(playInfoHolder)) {
            return playInfoHolder;
        }
        return null;
    }

    private static boolean checkPlayHolder(PlayInfoHolder playInfoHolder) {
        if (playInfoHolder == null) {
            sLog.d(TAG, "checkPlayHolder failed, playInfoHolder is empty");
            return false;
        }
        List<PlayInfo> playInfoList = playInfoHolder.getPlayInfoList();
        if (playInfoList == null || playInfoList.isEmpty()) {
            sLog.d(TAG, "checkPlayHolder failed, playInfoList is empty");
            return false;
        }
        if (playInfoHolder.getCurrentIndex() < 0 || playInfoHolder.getCurrentIndex() >= playInfoList.size()) {
            sLog.d(TAG, "checkPlayHolder CurrentIndex is illegal");
            return false;
        }
        if (playInfoHolder.getProgressRefreshInterval() < 0) {
            sLog.d(TAG, "checkPlayHolder ProgressRefreshInterval is illegal");
            return false;
        }
        PlayInfo playInfo = playInfoList.get(0);
        for (int i = 0; i < playInfoList.size(); i++) {
            PlayInfo playInfo2 = playInfoList.get(i);
            if (!PlayInfo.checkPlayInfo(playInfo2)) {
                sLog.d(TAG, "checkPlayHolder file/url permission failed");
                return false;
            }
            if (!Objects.equals(playInfo.getMediaType(), playInfo2.getMediaType())) {
                sLog.d(TAG, "checkPlayHolder EHwMediaInfoType is not the same");
                return false;
            }
        }
        return true;
    }
}
