package com.huawei.wear.oversea.satcomcard;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class SatcomCardMassTestConfig {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("BeiDouAuditCycle")
    private int f11213a;

    @SerializedName("MassTestPeriod")
    private String b;

    @SerializedName("BeidouAuditWaitDay")
    private int c;

    @SerializedName("MaxMsgCnt")
    private int d;

    @SerializedName("IsMsgCustomized")
    private int e;

    @SerializedName("PresetMsg")
    private String f;

    @SerializedName("SatcomStopTips")
    private String j;

    /* loaded from: classes9.dex */
    public interface AuditCycleType {
        public static final int DEFAULT = 0;
        public static final int FIX_CYCLE = 1;
    }

    public int d() {
        return this.f11213a;
    }

    public int c() {
        return this.c;
    }

    public String a() {
        return this.b;
    }
}
