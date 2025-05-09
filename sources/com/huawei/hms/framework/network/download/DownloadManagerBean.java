package com.huawei.hms.framework.network.download;

import com.huawei.hms.network.file.core.util.Utils;

/* loaded from: classes9.dex */
public class DownloadManagerBean {
    private int maxRequests = 64;
    private int maxHttp1RequestsPerHost = 5;
    private int maxHttp2RequestsPerHost = 32;
    private int maxHttp2ConnectionsPerHost = 4;
    private int readTimeOut = 20;
    private int writeTimeOut = 20;
    private int idleConnections = 8;
    private int keepAliveDuration = 5;
    private int connectionTimeOut = 9;
    private long pingInterval = 0;

    @Deprecated
    public int getMaxRequests() {
        Utils.printDeprecatedMethodLog("getMaxRequests of download");
        return this.maxRequests;
    }

    @Deprecated
    public void setMaxRequests(int i) {
        Utils.printDeprecatedMethodLog("setMaxRequests of download");
        this.maxRequests = i;
    }

    @Deprecated
    public int getMaxHttp1RequestsPerHost() {
        Utils.printDeprecatedMethodLog("getMaxHttp1RequestsPerHost of download");
        return this.maxHttp1RequestsPerHost;
    }

    @Deprecated
    public void setMaxHttp1RequestsPerHost(int i) {
        Utils.printDeprecatedMethodLog("setMaxHttp1RequestsPerHost of download");
        this.maxHttp1RequestsPerHost = i;
    }

    @Deprecated
    public int getMaxHttp2RequestsPerHost() {
        Utils.printDeprecatedMethodLog("getMaxHttp2RequestsPerHost of download");
        return this.maxHttp2RequestsPerHost;
    }

    @Deprecated
    public void setMaxHttp2RequestsPerHost(int i) {
        Utils.printDeprecatedMethodLog("setMaxHttp2RequestsPerHost of download");
        this.maxHttp2RequestsPerHost = i;
    }

    @Deprecated
    public int getMaxHttp2ConnectionsPerHost() {
        Utils.printDeprecatedMethodLog("getMaxHttp2ConnectionsPerHost of download");
        return this.maxHttp2ConnectionsPerHost;
    }

    @Deprecated
    public void setMaxHttp2ConnectionsPerHost(int i) {
        Utils.printDeprecatedMethodLog("setMaxHttp2ConnectionsPerHost of download");
        this.maxHttp2ConnectionsPerHost = i;
    }

    public int getReadTimeOut() {
        return this.readTimeOut;
    }

    public void setReadTimeOut(int i) {
        this.readTimeOut = i;
    }

    public int getWriteTimeOut() {
        return this.writeTimeOut;
    }

    public void setWriteTimeOut(int i) {
        this.writeTimeOut = i;
    }

    @Deprecated
    public int getIdleConnections() {
        Utils.printDeprecatedMethodLog("getIdleConnections of download");
        return this.idleConnections;
    }

    @Deprecated
    public void setIdleConnections(int i) {
        Utils.printDeprecatedMethodLog("setIdleConnections of download");
        this.idleConnections = i;
    }

    @Deprecated
    public int getKeepAliveDuration() {
        Utils.printDeprecatedMethodLog("getKeepAliveDuration of download");
        return this.keepAliveDuration;
    }

    @Deprecated
    public void setKeepAliveDuration(int i) {
        Utils.printDeprecatedMethodLog("setKeepAliveDuration of download");
        this.keepAliveDuration = i;
    }

    public int getConnectionTimeOut() {
        return this.connectionTimeOut;
    }

    public void setConnectionTimeOut(int i) {
        this.connectionTimeOut = i;
    }

    public void setPingInterval(long j) {
        if (j < 0 || j > 2147483647L) {
            j = 0;
        }
        this.pingInterval = j;
    }

    public long getPingInterval() {
        return this.pingInterval;
    }
}
