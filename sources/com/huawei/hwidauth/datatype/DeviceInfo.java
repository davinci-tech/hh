package com.huawei.hwidauth.datatype;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.kri;
import defpackage.ksy;
import defpackage.kti;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes5.dex */
public class DeviceInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.huawei.hwidauth.datatype.DeviceInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bQn_, reason: merged with bridge method [inline-methods] */
        public DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }
    };
    public static final String IMEI_TYPE = "0";
    public static final String SN_TYPE = "8";
    public static final String TAG_DEVICE_ID = "deviceID";
    public static final String TAG_DEVICE_INFO = "deviceInfo";
    public static final String TAG_DEVICE_TYPE = "deviceType";
    public static final String UDID_TYPE = "9";
    public static final String UUID_TYPE = "6";

    /* renamed from: a, reason: collision with root package name */
    private String f6364a;
    private String b;
    private String c;
    private String d;
    private String e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DeviceInfo(Parcel parcel) {
        this.f6364a = parcel.readString();
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.b = parcel.readString();
        this.d = parcel.readString();
    }

    public DeviceInfo() {
    }

    public static void a(XmlSerializer xmlSerializer, DeviceInfo deviceInfo) throws IllegalArgumentException, IllegalStateException, IOException {
        if (xmlSerializer == null || deviceInfo == null) {
            return;
        }
        kri.e(xmlSerializer, TAG_DEVICE_ID, deviceInfo.a());
        kri.e(xmlSerializer, "uuid", deviceInfo.e());
        kri.e(xmlSerializer, "deviceType", deviceInfo.c());
        kri.e(xmlSerializer, "terminalType", deviceInfo.b());
        if (TextUtils.isEmpty(deviceInfo.d())) {
            return;
        }
        kri.e(xmlSerializer, "deviceAliasName", deviceInfo.d());
    }

    private static DeviceInfo a(Context context, String str, String str2, String str3, String str4) throws IllegalArgumentException {
        String str5;
        if (a(str, str2, str3, str4)) {
            throw new IllegalArgumentException("all device id are empty， at least pass one param");
        }
        if (!TextUtils.isEmpty(str)) {
            ksy.b("DeviceInfo", "sn is not empty, use sn, device2 is udid", true);
            str5 = "8";
        } else if (!TextUtils.isEmpty(str2)) {
            ksy.b("DeviceInfo", "imei is not empty, use imei", true);
            str5 = "0";
            str = str2;
        } else if (!TextUtils.isEmpty(str3)) {
            ksy.b("DeviceInfo", "udid is not empty, use udid", true);
            str5 = "9";
            str = str3;
        } else {
            ksy.b("DeviceInfo", "imei is empty， use uuid", true);
            str = TextUtils.isEmpty(str4) ? kti.a() : str4;
            str5 = "6";
        }
        String b = kti.b(context);
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.e(b);
        deviceInfo.c(str5);
        deviceInfo.a(str);
        deviceInfo.b(kti.b());
        deviceInfo.d(kti.c(context));
        return deviceInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092 A[Catch: Exception -> 0x00a6, JSONException -> 0x00b1, TryCatch #2 {JSONException -> 0x00b1, Exception -> 0x00a6, blocks: (B:7:0x0012, B:9:0x0026, B:11:0x002c, B:13:0x0032, B:28:0x0083, B:32:0x0088, B:34:0x008d, B:36:0x0092, B:38:0x004f, B:41:0x0059, B:44:0x0063, B:47:0x006d, B:50:0x0097), top: B:6:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hwidauth.datatype.DeviceInfo a(android.content.Context r16, java.lang.String r17) throws java.lang.IllegalArgumentException {
        /*
            java.lang.String r0 = "device is not json"
            boolean r1 = android.text.TextUtils.isEmpty(r17)
            java.lang.String r2 = "DeviceInfo"
            r3 = 1
            if (r1 == 0) goto L12
            java.lang.String r0 = "jsonStr is null."
            defpackage.ksy.c(r2, r0, r3)
            r0 = 0
            return r0
        L12:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            r4 = r17
            r1.<init>(r4)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            java.lang.String r4 = "deviceInfo"
            org.json.JSONArray r1 = r1.getJSONArray(r4)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            r4 = 0
            java.lang.String r5 = ""
            r9 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L26:
            int r10 = r1.length()     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            if (r9 >= r10) goto L9f
            org.json.JSONObject r10 = r1.getJSONObject(r9)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            if (r10 == 0) goto L97
            java.lang.String r11 = "deviceType"
            java.lang.String r11 = r10.optString(r11)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            int r12 = r11.hashCode()     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            r13 = 48
            r14 = 3
            r15 = 2
            if (r12 == r13) goto L6d
            r13 = 54
            if (r12 == r13) goto L63
            r13 = 56
            if (r12 == r13) goto L59
            r13 = 57
            if (r12 == r13) goto L4f
            goto L77
        L4f:
            java.lang.String r12 = "9"
            boolean r11 = r11.equals(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            if (r11 == 0) goto L77
            r11 = r14
            goto L78
        L59:
            java.lang.String r12 = "8"
            boolean r11 = r11.equals(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            if (r11 == 0) goto L77
            r11 = r15
            goto L78
        L63:
            java.lang.String r12 = "6"
            boolean r11 = r11.equals(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            if (r11 == 0) goto L77
            r11 = r3
            goto L78
        L6d:
            java.lang.String r12 = "0"
            boolean r11 = r11.equals(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            if (r11 == 0) goto L77
            r11 = r4
            goto L78
        L77:
            r11 = -1
        L78:
            java.lang.String r12 = "deviceID"
            if (r11 == 0) goto L92
            if (r11 == r3) goto L8d
            if (r11 == r15) goto L88
            if (r11 == r14) goto L83
            goto L9c
        L83:
            java.lang.String r7 = r10.optString(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            goto L9c
        L88:
            java.lang.String r5 = r10.optString(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            goto L9c
        L8d:
            java.lang.String r8 = r10.optString(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            goto L9c
        L92:
            java.lang.String r6 = r10.optString(r12)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
            goto L9c
        L97:
            java.lang.String r10 = "jsonObject is null."
            defpackage.ksy.c(r2, r10, r3)     // Catch: java.lang.Exception -> La6 org.json.JSONException -> Lb1
        L9c:
            int r9 = r9 + 1
            goto L26
        L9f:
            r1 = r16
            com.huawei.hwidauth.datatype.DeviceInfo r0 = a(r1, r5, r6, r7, r8)
            return r0
        La6:
            java.lang.String r1 = "func json2device, parse exception."
            defpackage.ksy.c(r2, r1, r3)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        Lb1:
            java.lang.String r1 = "func json2device, json parse exception."
            defpackage.ksy.c(r2, r1, r3)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwidauth.datatype.DeviceInfo.a(android.content.Context, java.lang.String):com.huawei.hwidauth.datatype.DeviceInfo");
    }

    private static boolean a(String str, String str2, String str3, String str4) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str4)) {
            return false;
        }
        ksy.c("DeviceInfo", "all device id are empty, at least pass one param", true);
        return true;
    }

    private void a(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        if (!TextUtils.isEmpty(this.d)) {
            return this.d.toUpperCase(Locale.ENGLISH);
        }
        return this.d;
    }

    private void b(String str) {
        this.d = str;
    }

    public String c() {
        return this.b;
    }

    private void c(String str) {
        this.b = str;
    }

    public String d() {
        if (TextUtils.isEmpty(this.e)) {
            return b();
        }
        return this.e;
    }

    private void d(String str) {
        this.e = str;
    }

    public String e() {
        return this.f6364a;
    }

    private void e(String str) {
        this.f6364a = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6364a);
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.b);
        parcel.writeString(this.d);
    }

    public String toString() {
        return "{'mUUid':" + this.f6364a + "{'mDeviceAliasName':" + this.e + ",'mDeviceID':" + this.c + ",'mTerminalType':" + this.d + ",'mDeviceType':" + this.b + ",'mLoginTime':}";
    }
}
