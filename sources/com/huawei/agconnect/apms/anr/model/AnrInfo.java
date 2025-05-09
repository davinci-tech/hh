package com.huawei.agconnect.apms.anr.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class AnrInfo {
    private boolean isRoot;
    private AnrMemInfo memInfo;
    private String tracesInfo;
    private String parentActivity = "";
    private List<ThreadStackInfo> allThreadStack = new ArrayList();
    private String longMsg = "";
    private long anrTimeStamp = System.currentTimeMillis();

    public List<ThreadStackInfo> getAllThreadStack() {
        return this.allThreadStack;
    }

    public long getAnrTimeStamp() {
        return this.anrTimeStamp;
    }

    public String getLongMsg() {
        return this.longMsg;
    }

    public AnrMemInfo getMemInfo() {
        return this.memInfo;
    }

    public String getParentActivity() {
        return this.parentActivity;
    }

    public String getTracesInfo() {
        return this.tracesInfo;
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public void setAllThreadStack(List<ThreadStackInfo> list) {
        this.allThreadStack = list;
    }

    public void setLongMsg(String str) {
        this.longMsg = str;
    }

    public void setMemInfo(AnrMemInfo anrMemInfo) {
        this.memInfo = anrMemInfo;
    }

    public void setParentActivity(String str) {
        this.parentActivity = str;
        if (TextUtils.isEmpty(str)) {
            this.parentActivity = "";
        }
    }

    public void setRoot(boolean z) {
        this.isRoot = z;
    }

    public void setTracesInfo(String str) {
        this.tracesInfo = str;
    }
}
