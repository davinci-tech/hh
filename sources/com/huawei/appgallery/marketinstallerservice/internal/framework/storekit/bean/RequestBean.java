package com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean;

import android.content.Context;
import android.os.Build;
import com.huawei.appgallery.marketinstallerservice.b.b.f.a;
import defpackage.agm;
import defpackage.agn;
import defpackage.agq;
import defpackage.aha;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class RequestBean {
    public static final int CHINA = 0;
    public static final int GLOBAL = 1;

    /* renamed from: a, reason: collision with root package name */
    private Context f1881a;
    private String b;

    @InstallerNetTransmission
    public String brand;

    @InstallerNetTransmission
    private String buildNumber;

    @InstallerNetTransmission
    private String code;

    @InstallerNetTransmission
    private String density;

    @InstallerNetTransmission
    private int deviceType;

    @InstallerNetTransmission
    private int emuiApiLevel;

    @InstallerNetTransmission
    private String emuiVer;

    @InstallerNetTransmission
    private int firmwareVersion;

    @InstallerNetTransmission
    private int international;

    @InstallerNetTransmission
    private String lang;

    @InstallerNetTransmission
    public int magicApiLevel;

    @InstallerNetTransmission
    public String magicVer;

    @InstallerNetTransmission
    private String manufacturer;

    @InstallerNetTransmission
    private String method;

    /* renamed from: net, reason: collision with root package name */
    @InstallerNetTransmission
    private int f1882net;

    @InstallerNetTransmission
    private int odm;

    @InstallerNetTransmission
    private String phoneType;

    @InstallerNetTransmission
    private String sdkVersion;

    @InstallerNetTransmission
    private String subsystem;

    @InstallerNetTransmission
    private int sysBits;

    @InstallerNetTransmission
    private long ts;

    @InstallerNetTransmission
    private String ver;

    public void setVer(String str) {
        this.ver = str;
    }

    public void setTs(long j) {
        this.ts = j;
    }

    public void setSysBits(int i) {
        this.sysBits = i;
    }

    public void setSubsystem(String str) {
        this.subsystem = str;
    }

    public void setServiceUrl(String str) {
        this.b = str;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setPhoneType(String str) {
        this.phoneType = str;
    }

    public void setOdm(int i) {
        this.odm = i;
    }

    public void setNet(int i) {
        this.f1882net = i;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setMagicVer(String str) {
        this.magicVer = str;
    }

    public void setMagicApiLevel(int i) {
        this.magicApiLevel = i;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setInternational(int i) {
        this.international = i;
    }

    public void setFirmwareVersion(int i) {
        this.firmwareVersion = i;
    }

    public void setEmuiVer(String str) {
        this.emuiVer = str;
    }

    public void setEmuiApiLevel(int i) {
        this.emuiApiLevel = i;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public void setDensity(String str) {
        this.density = str;
    }

    public void setContext(Context context) {
        this.f1881a = context;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setBuildNumber(String str) {
        this.buildNumber = str;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getVer() {
        return this.ver;
    }

    public long getTs() {
        return this.ts;
    }

    public int getSysBits() {
        return this.sysBits;
    }

    public String getSubsystem() {
        return this.subsystem;
    }

    public String getServiceUrl() {
        return this.b;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public ResponseBean getResponseBean() {
        return new ResponseBean();
    }

    public String getPhoneType() {
        return this.phoneType;
    }

    public int getOdm() {
        return this.odm;
    }

    public int getNet() {
        return this.f1882net;
    }

    public String getMethod() {
        return this.method;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getMagicVer() {
        return this.magicVer;
    }

    public int getMagicApiLevel() {
        return this.magicApiLevel;
    }

    public String getLang() {
        return this.lang;
    }

    public int getInternational() {
        return this.international;
    }

    public int getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getEmuiVer() {
        return this.emuiVer;
    }

    public int getEmuiApiLevel() {
        return this.emuiApiLevel;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public String getDensity() {
        return this.density;
    }

    public Context getContext() {
        return this.f1881a;
    }

    public String getCode() {
        return this.code;
    }

    public String getBuildNumber() {
        return this.buildNumber;
    }

    public String getBrand() {
        return this.brand;
    }

    public String genBody() {
        String a2;
        b();
        Map<String, Field> a3 = a();
        int size = a3.size();
        String[] strArr = new String[size];
        a3.keySet().toArray(strArr);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        do {
            Field field = a3.get(strArr[i]);
            if (field != null && (a2 = a(field)) != null) {
                String a4 = com.huawei.appgallery.marketinstallerservice.b.b.b.b.a(a2);
                sb.append(strArr[i]);
                sb.append('=');
                sb.append(a4);
                sb.append('&');
            }
            i++;
        } while (i < size);
        int length = sb.length();
        if (length > 0) {
            int i2 = length - 1;
            if (sb.charAt(i2) == '&') {
                sb.deleteCharAt(i2);
            }
        }
        return sb.toString();
    }

    protected void b() {
        this.f1882net = aha.e(this.f1881a);
        this.ts = System.currentTimeMillis();
        this.emuiVer = agm.b().a();
        this.emuiApiLevel = agm.b().d();
    }

    protected Map<String, Field> a() {
        HashMap hashMap = new HashMap();
        for (Field field : a.a(getClass())) {
            if (field.isAnnotationPresent(InstallerNetTransmission.class)) {
                hashMap.put(field.getName(), field);
            }
        }
        return hashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String a(java.lang.reflect.Field r8) {
        /*
            r7 = this;
            java.lang.String r0 = r8.getName()
            java.lang.Class r1 = r8.getType()
            java.lang.String r1 = r1.getName()
            java.lang.String r2 = "boolean"
            boolean r1 = r2.equals(r1)
            java.lang.String r0 = defpackage.ahc.b(r0, r1)
            java.lang.String r1 = "getValue:Can not find getMethod:"
            java.lang.String r2 = "RequestBean"
            r3 = 0
            if (r0 != 0) goto L31
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.String r8 = r8.getName()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            defpackage.agr.e(r2, r8)
            return r3
        L31:
            java.lang.Class r4 = r7.getClass()     // Catch: java.lang.reflect.InvocationTargetException -> L43 java.lang.SecurityException -> L4b java.lang.NoSuchMethodException -> L53
            r5 = 0
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch: java.lang.reflect.InvocationTargetException -> L43 java.lang.SecurityException -> L4b java.lang.NoSuchMethodException -> L53
            java.lang.reflect.Method r0 = r4.getMethod(r0, r6)     // Catch: java.lang.reflect.InvocationTargetException -> L43 java.lang.SecurityException -> L4b java.lang.NoSuchMethodException -> L53
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch: java.lang.reflect.InvocationTargetException -> L43 java.lang.SecurityException -> L4b java.lang.NoSuchMethodException -> L53
            java.lang.Object r8 = r0.invoke(r7, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L43 java.lang.SecurityException -> L4b java.lang.NoSuchMethodException -> L53
            goto L67
        L43:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getValue:GetMethod can not invocation:"
            r0.<init>(r1)
            goto L58
        L4b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getValue:GetMethod can not access:"
            r0.<init>(r1)
            goto L58
        L53:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
        L58:
            java.lang.String r8 = r8.getName()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            defpackage.agr.e(r2, r8)
            r8 = r3
        L67:
            boolean r0 = r8 instanceof com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean
            if (r0 == 0) goto L72
            com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean r8 = (com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean) r8
            java.lang.String r8 = r8.toJson()
            return r8
        L72:
            boolean r0 = r8 instanceof java.util.List
            if (r0 == 0) goto L7d
            java.util.List r8 = (java.util.List) r8
            java.lang.String r8 = com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean.listToJson(r8)
            return r8
        L7d:
            boolean r0 = r8 instanceof java.lang.reflect.Array
            if (r0 == 0) goto L88
            java.lang.reflect.Array r8 = (java.lang.reflect.Array) r8
            java.lang.String r8 = com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean.arrayToJson(r8)
            return r8
        L88:
            boolean r0 = r8 instanceof java.util.Map
            if (r0 == 0) goto L93
            java.util.Map r8 = (java.util.Map) r8
            java.lang.String r8 = com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.JsonBean.mapToJson(r8)
            return r8
        L93:
            if (r8 == 0) goto L9a
            java.lang.String r8 = java.lang.String.valueOf(r8)
            return r8
        L9a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.RequestBean.a(java.lang.reflect.Field):java.lang.String");
    }

    public RequestBean(Context context) {
        this.code = "0500";
        this.ver = "8.0";
        this.emuiApiLevel = 0;
        this.international = 0;
        this.manufacturer = Build.MANUFACTURER;
        this.odm = 0;
        this.b = "";
        this.brand = Build.BRAND;
        this.f1881a = context;
        this.firmwareVersion = agq.f();
        this.density = agq.b(context);
        this.phoneType = agq.d();
        this.buildNumber = agq.b();
        this.lang = agn.c();
        this.sysBits = agq.i();
        this.deviceType = agq.e(context);
        this.international = agn.d() ? 1 : 0;
        if (agq.g()) {
            this.odm = 1;
        }
        this.sdkVersion = "11.5.1.300";
        this.magicVer = agq.c();
        this.magicApiLevel = agq.a();
    }

    public RequestBean() {
        this.code = "0500";
        this.ver = "8.0";
        this.emuiApiLevel = 0;
        this.international = 0;
        this.manufacturer = Build.MANUFACTURER;
        this.odm = 0;
        this.b = "";
        this.brand = Build.BRAND;
    }
}
