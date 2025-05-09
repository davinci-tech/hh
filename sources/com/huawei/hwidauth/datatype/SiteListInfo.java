package com.huawei.hwidauth.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.ksy;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class SiteListInfo implements Parcelable {
    public static final Parcelable.Creator<SiteListInfo> CREATOR = new Parcelable.Creator<SiteListInfo>() { // from class: com.huawei.hwidauth.datatype.SiteListInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bQq_, reason: merged with bridge method [inline-methods] */
        public SiteListInfo createFromParcel(Parcel parcel) {
            SiteListInfo siteListInfo = new SiteListInfo();
            siteListInfo.f6366a = parcel.readInt();
            siteListInfo.b = parcel.readString();
            siteListInfo.c = parcel.readString();
            siteListInfo.d = parcel.readString();
            return siteListInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public SiteListInfo[] newArray(int i) {
            return new SiteListInfo[i];
        }
    };
    public static final String TAG = "SiteListInfo";

    /* renamed from: a, reason: collision with root package name */
    private int f6366a = 0;
    private String b = "";
    private String c = "";
    private String d = "";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f6366a);
        parcel.writeString(this.d);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public static void a(XmlPullParser xmlPullParser, SiteListInfo siteListInfo, String str) {
        if (xmlPullParser == null || siteListInfo == null || str == null) {
            ksy.c(TAG, "param is null.", true);
        } else if ("site".equals(str)) {
            for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                a(xmlPullParser, siteListInfo, i);
            }
        }
    }

    private static void a(XmlPullParser xmlPullParser, SiteListInfo siteListInfo, int i) {
        String attributeName = xmlPullParser.getAttributeName(i);
        String attributeValue = xmlPullParser.getAttributeValue(i);
        if (attributeName.equals("id")) {
            siteListInfo.a(siteListInfo.a(attributeValue));
            return;
        }
        if (attributeName.equals("domain")) {
            try {
                JSONObject jSONObject = new JSONObject(attributeValue);
                siteListInfo.b = a(jSONObject, "cas").trim();
                siteListInfo.d = a(jSONObject, "honor-cas").trim();
                siteListInfo.c = a(jSONObject, "as").trim();
            } catch (JSONException e) {
                ksy.c(TAG, "parseJSONArrayInfos JSONException: " + e.getClass().getSimpleName(), true);
            } catch (Exception e2) {
                ksy.c(TAG, "parseJSONArrayInfos Exception: " + e2.getClass().getSimpleName(), true);
            }
        }
    }

    private void a(int i) {
        this.f6366a = i;
    }

    public int a() {
        return this.f6366a;
    }

    public String b() {
        return this.b;
    }

    private int a(String str) {
        try {
            return b(str);
        } catch (Exception e) {
            ksy.c(TAG, "e = " + e.getClass().getSimpleName(), true);
            return 0;
        }
    }

    private int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            ksy.c(TAG, "parsError " + e.getClass().getSimpleName(), true);
            return -1;
        }
    }

    private static String a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject != null && jSONObject.has(str)) ? jSONObject.getString(str) : "";
    }
}
