package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.xa;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PlayInfo implements Parcelable {
    public static final int ADAPT_MODE_NONE = 1;
    public static final int DRM_TYPE_BASE = 1;
    public static final int DRM_TYPE_CHINADRM = 16;
    public static final int DRM_TYPE_CLEARKEY = 2;
    public static final int DRM_TYPE_PLAYREADY = 8;
    public static final int DRM_TYPE_WIDEVINE = 4;
    public static final int ID_MAX_LEN = 100;
    public static final String KEY_ALBUM_COVER_URL = "ALBUM_COVER_URL";
    public static final String KEY_ALBUM_TITLE = "ALBUM_TITLE";
    public static final String KEY_APP_ICON_URL = "APP_ICON_URL";
    public static final String KEY_APP_NAME = "APP_NAME";
    public static final String KEY_AUDIO_TRACK_NAME = "AUDIO_TRACK_NAME";
    public static final String KEY_CLOSING_CREDITS_POSITION = "CLOSING_CREDITS_POSITION";
    public static final String KEY_DRM_TYPE = "DRM_TYPE";
    public static final String KEY_DURATION = "DURATION";
    public static final String KEY_LRC_CONTENT = "LRC_CONTENT";
    public static final String KEY_LRC_URL = "LRC_URL";
    public static final String KEY_MEDIA_ARTIST = "MEDIA_ARTIST";
    public static final String KEY_MEDIA_ID = "MEDIA_ID";
    public static final String KEY_MEDIA_NAME = "MEDIA_NAME";
    public static final String KEY_MEDIA_SIZE = "MEDIA_SIZE";
    public static final String KEY_MEDIA_TYPE = "MEDIA_TYPE";
    public static final String KEY_MEDIA_URL = "MEDIA_URL";
    public static final String KEY_START_POSITION = "START_POSITION";
    private static final String KEY_SUBTITLE_LANGUAGE = "SUBTITLE_LANGUAGE";
    public static final String KEY_SUBTITLE_TRACK_NAME = "SUBTITLE_TRACK_NAME";
    private static final String KEY_SUBTITLE_TYPE = "SUBTITLE_TYPE";
    private static final String KEY_SUBTITLE_URL = "SUBTITLE_URL";
    public static final String KEY_SUB_TITLE = "SUB_TITLE";
    public static final String KEY_UX_ADAPT_MODE = "UX_ADAPT_MODE";
    public static final String KEY_VIDEO_TRACK_NAME = "VIDEO_TRACK_NAME";
    private static final String TAG = "HiSight-S-PlayInfo";
    private static final int TITLE_MAX_LEN = 200;
    private static final int URL_MASK_LEN = 20;
    private static final int URL_MAX_LEN = 2083;
    private JSONObject mInfoObject;
    private List<a> mSubTitleList;
    public static final Parcelable.Creator<PlayInfo> CREATOR = new Parcelable.Creator<PlayInfo>() { // from class: com.huawei.android.airsharing.api.PlayInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlayInfo[] newArray(int i) {
            return new PlayInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dO_, reason: merged with bridge method [inline-methods] */
        public PlayInfo createFromParcel(Parcel parcel) {
            return new PlayInfo(parcel.readString());
        }
    };
    private static xa sLog = xa.c();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayInfo(EHwMediaInfoType eHwMediaInfoType) {
        this.mSubTitleList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        this.mInfoObject = jSONObject;
        try {
            jSONObject.put(KEY_MEDIA_TYPE, eHwMediaInfoType.toString());
        } catch (JSONException e) {
            sLog.d(TAG, "ERROR: PlayInfo JSONException " + e);
        }
    }

    private PlayInfo(String str) {
        this.mSubTitleList = new ArrayList();
        this.mInfoObject = new JSONObject();
        try {
            this.mInfoObject = new JSONObject(str);
        } catch (JSONException e) {
            sLog.d(TAG, "ERROR: createFromParcel JSONException " + e);
        }
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject(this.mInfoObject.toString());
            if (jSONObject.has(KEY_MEDIA_URL)) {
                jSONObject.put(KEY_MEDIA_URL, maskUrl(jSONObject.getString(KEY_MEDIA_URL)));
            }
            if (jSONObject.has(KEY_ALBUM_COVER_URL)) {
                jSONObject.put(KEY_ALBUM_COVER_URL, maskUrl(jSONObject.getString(KEY_ALBUM_COVER_URL)));
            }
            if (jSONObject.has(KEY_LRC_URL)) {
                jSONObject.put(KEY_LRC_URL, maskUrl(jSONObject.getString(KEY_LRC_URL)));
            }
            if (jSONObject.has(KEY_APP_ICON_URL)) {
                jSONObject.put(KEY_APP_ICON_URL, maskUrl(jSONObject.getString(KEY_APP_ICON_URL)));
            }
            if (jSONObject.has(KEY_SUBTITLE_URL)) {
                jSONObject.put(KEY_SUBTITLE_URL, maskUrl(jSONObject.getString(KEY_SUBTITLE_URL)));
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            sLog.d(TAG, "PlayInfo toString JSONException");
            return "";
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mInfoObject.toString());
    }

    public boolean addSubtitle(String str, String str2, String str3) {
        if (!verifyInputParameters(KEY_SUBTITLE_URL, str)) {
            sLog.d(TAG, "addSubtitle url invalid, url is " + maskUrl(str));
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            sLog.d(TAG, "addSubtitle type invalid, type is " + str3);
            return false;
        }
        if (TextUtils.isEmpty(str3)) {
            sLog.d(TAG, "addSubtitle language invalid, language is " + str3);
            return false;
        }
        a aVar = new a(str, str2, str3);
        this.mSubTitleList.add(aVar);
        try {
            this.mInfoObject.put(KEY_SUB_TITLE, subTitleListToArray(this.mSubTitleList));
            return true;
        } catch (JSONException e) {
            sLog.d(TAG, "addSubtitle throw JSONException e:" + e.toString());
            this.mSubTitleList.remove(aVar);
            return false;
        }
    }

    public List<a> getSubtitleList() {
        return this.mSubTitleList;
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

    public boolean putInt(String str, int i) {
        if (!verifyInputParameters(str, i)) {
            sLog.d(TAG, "putInt key or value is illegal, key is " + str + ", value is " + i);
            return false;
        }
        try {
            this.mInfoObject.put(str, i);
            return true;
        } catch (JSONException unused) {
            sLog.d(TAG, "PlayInfo putInt throw JSONException, key: " + str);
            return false;
        }
    }

    public boolean putLong(String str, long j) {
        if (!verifyInputParameters(str, j)) {
            sLog.d(TAG, "putLong key or value is illegal, key is " + str + ", value is " + j);
            return false;
        }
        try {
            this.mInfoObject.put(str, j);
            return true;
        } catch (JSONException unused) {
            sLog.d(TAG, "PlayInfo putLong throw JSONException, key: " + str);
            return false;
        }
    }

    public boolean setMediaId(String str) {
        return putString(KEY_MEDIA_ID, str);
    }

    public String getMediaId() {
        return getString(KEY_MEDIA_ID);
    }

    public boolean setMediaName(String str) {
        return putString(KEY_MEDIA_NAME, str);
    }

    public String getMediaName() {
        return getString(KEY_MEDIA_NAME);
    }

    public boolean setMediaUrl(String str) {
        return putString(KEY_MEDIA_URL, str);
    }

    public String getMediaUrl() {
        return getString(KEY_MEDIA_URL);
    }

    private boolean setMediaType(String str) {
        return putString(KEY_MEDIA_TYPE, str);
    }

    public EHwMediaInfoType getMediaType() {
        try {
            return EHwMediaInfoType.valueOf(getString(KEY_MEDIA_TYPE));
        } catch (IllegalArgumentException e) {
            sLog.d(TAG, "getMediaType " + e);
            return EHwMediaInfoType.UNKNOWN;
        }
    }

    public boolean setMediaSize(long j) {
        return putLong(KEY_MEDIA_SIZE, j);
    }

    public long getMediaSize() {
        return getLong(KEY_MEDIA_SIZE);
    }

    public boolean setStartPosition(int i) {
        return putInt(KEY_START_POSITION, i);
    }

    public int getStartPosition() {
        return getInt(KEY_START_POSITION);
    }

    public boolean setDuration(int i) {
        return putInt(KEY_DURATION, i);
    }

    public int getDuration() {
        return getInt(KEY_DURATION);
    }

    public boolean setClosingCreditsPosition(int i) {
        return putInt(KEY_CLOSING_CREDITS_POSITION, i);
    }

    public int getClosingCreditsPosition() {
        return getInt(KEY_CLOSING_CREDITS_POSITION);
    }

    public boolean setAlbumCoverUrl(String str) {
        return putString(KEY_ALBUM_COVER_URL, str);
    }

    public String getAlbumCoverUrl() {
        return getString(KEY_ALBUM_COVER_URL);
    }

    public boolean setAlbumTitle(String str) {
        return putString(KEY_ALBUM_TITLE, str);
    }

    public String getAlbumTitle() {
        return getString(KEY_ALBUM_TITLE);
    }

    public boolean setMediaArtist(String str) {
        return putString(KEY_MEDIA_ARTIST, str);
    }

    public String getMediaArtist() {
        return getString(KEY_MEDIA_ARTIST);
    }

    public boolean setLrcUrl(String str) {
        return putString(KEY_LRC_URL, str);
    }

    public String getLrcUrl() {
        return getString(KEY_LRC_URL);
    }

    public boolean setLrcContent(String str) {
        return putString(KEY_LRC_CONTENT, str);
    }

    public String getLrcContent() {
        return getString(KEY_LRC_CONTENT);
    }

    public boolean setAppIconUrl(String str) {
        return putString(KEY_APP_ICON_URL, str);
    }

    public String getAppIconUrl() {
        return getString(KEY_APP_ICON_URL);
    }

    public boolean setAppName(String str) {
        return putString(KEY_APP_NAME, str);
    }

    public String getAppName() {
        return getString(KEY_APP_NAME);
    }

    public boolean setDrmType(int i) {
        return putInt(KEY_DRM_TYPE, i);
    }

    public int getDrmType() {
        return getInt(KEY_DRM_TYPE);
    }

    public boolean setAudioTrackName(String str) {
        return putString(KEY_AUDIO_TRACK_NAME, str);
    }

    public String getAudioTrackName() {
        return getString(KEY_AUDIO_TRACK_NAME);
    }

    public boolean setVideoTrackName(String str) {
        return putString(KEY_VIDEO_TRACK_NAME, str);
    }

    public String getVideoTrackName() {
        return getString(KEY_VIDEO_TRACK_NAME);
    }

    public boolean setSubtitleTrackName(String str) {
        return putString(KEY_SUBTITLE_TRACK_NAME, str);
    }

    public String getSubtitleTrackName() {
        return getString(KEY_SUBTITLE_TRACK_NAME);
    }

    public boolean setUxAdaptMode(int i) {
        return putInt(KEY_UX_ADAPT_MODE, i);
    }

    public int getUxAdaptMode() {
        return getInt(KEY_UX_ADAPT_MODE);
    }

    public int getInt(String str) {
        return this.mInfoObject.optInt(str, -1);
    }

    public long getLong(String str) {
        return this.mInfoObject.optLong(str, -1L);
    }

    public String getString(String str) {
        return this.mInfoObject.optString(str, "");
    }

    public boolean isEmpty(String str) {
        return this.mInfoObject.isNull(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean verifyInputParameters(String str, String str2) {
        char c;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1840648666:
                if (str.equals(KEY_MEDIA_NAME)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1687823288:
                if (str.equals(KEY_ALBUM_TITLE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -626447097:
                if (str.equals(KEY_APP_ICON_URL)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -118116490:
                if (str.equals(KEY_MEDIA_ID)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 274065576:
                if (str.equals(KEY_SUBTITLE_URL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 306893858:
                if (str.equals(KEY_MEDIA_ARTIST)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 633368148:
                if (str.equals(KEY_MEDIA_URL)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1143183757:
                if (str.equals(KEY_LRC_URL)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1979827721:
                if (str.equals(KEY_APP_NAME)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1988647607:
                if (str.equals(KEY_ALBUM_COVER_URL)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 5:
            case '\b':
                return str2.length() < 200;
            case 2:
            case 3:
            case 4:
            case 7:
            case '\t':
                return str2.length() < URL_MAX_LEN;
            case 6:
                if (str2.length() > URL_MAX_LEN) {
                    return false;
                }
                if (TextUtils.isEmpty(this.mInfoObject.optString(KEY_MEDIA_ID, ""))) {
                    try {
                        String encodeToString = Base64.encodeToString((str2 + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8), 2);
                        this.mInfoObject.put(KEY_MEDIA_ID, encodeToString.substring(0, Math.min(100, encodeToString.length())));
                    } catch (JSONException e) {
                        sLog.d(TAG, "put MEDIA_ID JSONException " + e);
                    }
                }
                return true;
            default:
                return true;
        }
    }

    private boolean verifyInputParameters(String str, int i) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1409705850) {
            if (str.equals(KEY_START_POSITION)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 307645946) {
            if (hashCode == 1823963576 && str.equals(KEY_CLOSING_CREDITS_POSITION)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals(KEY_DRM_TYPE)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return i == 1 || i == 2 || i == 4 || i == 8 || i == 16;
            }
            if (c != 2) {
                return true;
            }
        }
        return i >= 0;
    }

    private boolean verifyInputParameters(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.hashCode();
        return !str.equals(KEY_MEDIA_SIZE) || j >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayInfo)) {
            return false;
        }
        PlayInfo playInfo = (PlayInfo) obj;
        return getMediaType() != null && Objects.equals(getMediaType(), playInfo.getMediaType()) && !TextUtils.isEmpty(getMediaUrl()) && Objects.equals(getMediaUrl(), playInfo.getMediaUrl()) && !TextUtils.isEmpty(getMediaId()) && Objects.equals(getMediaId(), playInfo.getMediaId());
    }

    public int hashCode() {
        return Objects.hash(getMediaType() + getMediaUrl() + getMediaId());
    }

    public static String maskUrl(String str) {
        int length;
        if (str == null || (length = str.length()) <= 20) {
            return Constants.CONFUSION_CHARS;
        }
        return str.substring(0, (length - 20) / 2) + Constants.CONFUSION_CHARS + str.substring((length + 20) / 2);
    }

    public static boolean checkPlayInfo(PlayInfo playInfo) {
        if (playInfo == null) {
            sLog.a(TAG, "in checkPlayInfo, playInfo not valid");
            return false;
        }
        if (playInfo.getMediaType() != EHwMediaInfoType.IMAGE && playInfo.getMediaType() != EHwMediaInfoType.AUDIO && playInfo.getMediaType() != EHwMediaInfoType.VIDEO) {
            sLog.a(TAG, "in checkPlayInfo, PlayMediaType not valid");
            return false;
        }
        return !TextUtils.isEmpty(playInfo.getMediaUrl());
    }

    private JSONArray subTitleListToArray(List<a> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (a aVar : list) {
            if (aVar == null) {
                throw new IllegalArgumentException("subTitleListToArray subTitle is null");
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_SUBTITLE_URL, aVar.d());
            jSONObject.put(KEY_SUBTITLE_TYPE, aVar.e());
            jSONObject.put(KEY_SUBTITLE_LANGUAGE, aVar.c());
            jSONArray.put(jSONObject.toString());
        }
        return jSONArray;
    }

    public static PlayInfo toJavaBean(JSONObject jSONObject) {
        boolean jsonArrayToSubtitle;
        if (jSONObject == null) {
            sLog.d(TAG, "toJavaBean, dataObj not valid");
            return null;
        }
        try {
            PlayInfo playInfo = new PlayInfo(EHwMediaInfoType.valueOf(jSONObject.getString(KEY_MEDIA_TYPE)));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof String) {
                    jsonArrayToSubtitle = playInfo.putString(next, (String) obj);
                } else if (obj instanceof Integer) {
                    jsonArrayToSubtitle = playInfo.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    jsonArrayToSubtitle = playInfo.putLong(next, ((Long) obj).longValue());
                } else if (obj instanceof JSONArray) {
                    jsonArrayToSubtitle = jsonArrayToSubtitle(playInfo, next, (JSONArray) obj);
                } else {
                    sLog.d(TAG, "toJavaBean, unknown value type");
                    sLog.d(TAG, "toJavaBean, put invalid param to playinfo, key is " + next + ", value is " + obj);
                }
                if (!jsonArrayToSubtitle) {
                    sLog.d(TAG, "toJavaBean, put invalid param to playinfo, key is " + next + ", value is " + obj);
                }
            }
            return playInfo;
        } catch (IllegalArgumentException | JSONException e) {
            sLog.d(TAG, "toJavaBean Exception " + e);
            return null;
        }
    }

    private static boolean jsonArrayToSubtitle(PlayInfo playInfo, String str, JSONArray jSONArray) throws JSONException {
        if (!KEY_SUB_TITLE.equals(str)) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
            z = playInfo.addSubtitle(jSONObject.optString(KEY_SUBTITLE_URL, ""), jSONObject.optString(KEY_SUBTITLE_TYPE, ""), jSONObject.optString(KEY_SUBTITLE_LANGUAGE, ""));
        }
        return z;
    }

    public JSONObject toJsonObject() {
        return this.mInfoObject;
    }

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f1822a;
        private String b;
        private String e;

        a(String str, String str2, String str3) {
            this.e = str;
            this.b = str2;
            this.f1822a = str3;
        }

        public String d() {
            return this.e;
        }

        public String e() {
            return this.b;
        }

        public String c() {
            return this.f1822a;
        }
    }
}
