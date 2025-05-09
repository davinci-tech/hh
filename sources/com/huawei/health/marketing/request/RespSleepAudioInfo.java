package com.huawei.health.marketing.request;

import java.util.List;

/* loaded from: classes3.dex */
public class RespSleepAudioInfo {
    private int resultCode;
    private String resultDesc;
    private List<SleepAudioInfo> sleepAudios;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public List<SleepAudioInfo> getSleepAudios() {
        return this.sleepAudios;
    }

    public void setSleepAudios(List<SleepAudioInfo> list) {
        this.sleepAudios = list;
    }
}
