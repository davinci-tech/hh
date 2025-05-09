package com.huawei.hms.mlsdk.asr.o;

import android.os.Bundle;
import com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class b extends BaseInfoGatherEvent {

    /* renamed from: a, reason: collision with root package name */
    private String f5093a;
    private int d;
    private int f;
    private int g;
    private int h;
    private int i;
    private String j;
    private String k;
    private boolean m;
    private long b = 0;
    private long c = 0;
    private int e = 0;
    private long l = 0;

    public b(boolean z) {
        this.m = z;
    }

    public String a() {
        return this.f5093a;
    }

    public void b(long j) {
        this.b = j;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public void e(int i) {
        this.g = i;
    }

    public void f(int i) {
        this.f = i;
    }

    public Long g() {
        return Long.valueOf(this.l);
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public Bundle getAppInfo() {
        Bundle bundle = MLApplication.getInstance().toBundle();
        bundle.putString("com.huawei.hms.client.service.name:ml-computer-vision", (this.m ? "ml-computer-voice-realtimetranscription:" : "ml-computer-voice-asr:").concat("3.16.7.302"));
        return bundle;
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public LinkedHashMap<String, String> getCustomizedData() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("applanguage", this.f5093a);
        long j = this.b;
        linkedHashMap.put("speechStartTime", j == 0 ? "" : String.valueOf(j));
        long j2 = this.c;
        linkedHashMap.put("speechEndTime", j2 == 0 ? "" : String.valueOf(j2));
        int i = this.d;
        linkedHashMap.put("firstWordCost", i == 0 ? "" : String.valueOf(i));
        int i2 = this.e;
        linkedHashMap.put("lastWordCost", i2 == 0 ? "" : String.valueOf(i2));
        int i3 = this.f;
        linkedHashMap.put("voiceStreamTime", i3 == 0 ? "" : String.valueOf(i3));
        int i4 = this.g;
        linkedHashMap.put("uploadVoiceSize", i4 == 0 ? "" : String.valueOf(i4));
        int i5 = this.h;
        linkedHashMap.put(OpAnalyticsConstants.NOTIFICATION_TEXT_LENGTH, i5 == 0 ? "" : String.valueOf(i5));
        int i6 = this.i;
        linkedHashMap.put("chainBuildingDelay", i6 != 0 ? String.valueOf(i6) : "");
        linkedHashMap.put("result", this.j);
        linkedHashMap.put("resultDetail", this.k);
        return linkedHashMap;
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public String getModuleName() {
        return this.m ? "MLKitRtt" : "MLKitAsr";
    }

    public long h() {
        return this.c;
    }

    public long i() {
        return this.b;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.g;
    }

    public int l() {
        return this.f;
    }

    public void a(String str) {
        this.f5093a = str;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void d(int i) {
        this.h = i;
    }

    public String e() {
        return this.j;
    }

    public String f() {
        return this.k;
    }

    public void a(long j) {
        this.c = j;
    }

    public int b() {
        return this.i;
    }

    public void c(String str) {
        this.k = str;
    }

    public void a(int i) {
        this.i = i;
    }

    public void b(String str) {
        this.j = str;
    }

    public void a(Long l) {
        this.l = l.longValue();
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public String getModuleVersion() {
        return "3.16.7.302";
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public String getModelApkVersion() {
        return "";
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public String getExtension(String str) {
        return "";
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public String getEventId() {
        return "112001";
    }

    @Override // com.huawei.hms.mlkit.common.ha.event.BaseInfoGatherEvent
    public String getApiName() {
        return "";
    }
}
