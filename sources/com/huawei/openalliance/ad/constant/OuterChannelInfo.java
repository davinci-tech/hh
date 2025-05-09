package com.huawei.openalliance.ad.constant;

import com.huawei.openalliance.ad.annotations.a;

/* loaded from: classes9.dex */
public class OuterChannelInfo {
    public static final String CHANNEL_ID = "channelId";
    public static final String CHANNEL_INFO = "channelInfo";

    @a
    private String channelInfo;

    @a
    private long clickTimestamp;

    @a
    private long installTimestamp;

    public void setInstallTimestamp(long j) {
        this.installTimestamp = j;
    }

    public void setClickTimestamp(long j) {
        this.clickTimestamp = j;
    }

    public void setChannelInfo(String str) {
        this.channelInfo = str;
    }

    public long getInstallTimestamp() {
        return this.installTimestamp;
    }

    public long getClickTimestamp() {
        return this.clickTimestamp;
    }

    public String getChannelInfo() {
        return this.channelInfo;
    }

    public OuterChannelInfo(String str, long j, long j2) {
        this.channelInfo = str;
        this.clickTimestamp = j;
        this.installTimestamp = j2;
    }

    public OuterChannelInfo() {
        this.installTimestamp = -1L;
        this.clickTimestamp = -1L;
    }
}
