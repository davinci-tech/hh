package com.hihonor.assistant.cardmgrsdk.model.recommend;

import java.util.List;

/* loaded from: classes8.dex */
public class RequestParam {
    public int allCount;
    public List<AppInfo> blockList;
    public List<ServiceParam> blockService;
    public List<AppInfo> dockAppList;
    public int innerCount;
    public int outCount;
    public List<PageInfo> pageApps;
    public String requestId;

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setPageApps(List<PageInfo> list) {
        this.pageApps = list;
    }

    public void setOutCount(int i) {
        this.outCount = i;
    }

    public void setInnerCount(int i) {
        this.innerCount = i;
    }

    public void setDockAppList(List<AppInfo> list) {
        this.dockAppList = list;
    }

    public void setBlockService(List<ServiceParam> list) {
        this.blockService = list;
    }

    public void setBlockList(List<AppInfo> list) {
        this.blockList = list;
    }

    public void setAllCount(int i) {
        this.allCount = i;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public List<PageInfo> getPageApps() {
        return this.pageApps;
    }

    public int getOutCount() {
        return this.outCount;
    }

    public int getInnerCount() {
        return this.innerCount;
    }

    public List<AppInfo> getDockAppList() {
        return this.dockAppList;
    }

    public List<ServiceParam> getBlockService() {
        return this.blockService;
    }

    public List<AppInfo> getBlockList() {
        return this.blockList;
    }

    public int getAllCount() {
        return this.allCount;
    }
}
