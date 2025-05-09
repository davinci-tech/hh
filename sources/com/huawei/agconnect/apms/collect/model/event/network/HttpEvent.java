package com.huawei.agconnect.apms.collect.model.event.network;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.EventType;
import com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;
import com.huawei.agconnect.apms.mlk;
import com.huawei.agconnect.apms.nml;
import com.huawei.agconnect.apms.vwx;
import java.util.List;

/* loaded from: classes2.dex */
public class HttpEvent extends Event {
    private long bytesReceived;
    private long bytesSent;
    private String cdnProvider;
    private int connectFailedTimes;
    private int connectTotalTimes;
    private long contentLength;
    private String contentType;
    private int dnsFailedTimes;
    private JsonArray dnsInfos;
    private int dnsTotalTimes;
    private String domain;
    private String errorMessage;
    private int followUpTimes;
    private String httpMethod;
    private int libType;
    private String nQoETransactionId;
    private String nuwaSampleState;
    private String nuwaSpanId;
    private String nuwaTraceId;
    private String nuwaTraceNeIn;
    private String nuwaTraceNeOut;
    private String nuwaTraceRqIn;
    private String nuwaTraceRqOut;
    private long requestBodyEnd;
    private long requestBodyStart;
    private long requestHeadersEnd;
    private long requestHeadersStart;
    private long responseBodyEnd;
    private long responseBodyStart;
    private long responseHeaderEnd;
    private long responseHeaderStart;
    private String serverIp;
    private String serviceCode;
    private String serviceInteractionId;
    private String serviceSessionId;
    private String serviceTraceId;
    private JsonArray socketInfos;
    private String stackTrace;
    private int statusCode;
    private long totalTime;
    private String url;

    public static class DnsEvent extends CollectableArray {
        private String addressList;
        private long dnsStart;
        private long dnsStop;
        private String domainName;
        private boolean isSuccess;

        public DnsEvent(nml nmlVar) {
            this.domainName = nmlVar.def();
            this.addressList = nmlVar.abc();
            this.dnsStart = nmlVar.bcd();
            this.dnsStop = nmlVar.cde();
            this.isSuccess = nmlVar.efg();
        }

        @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
        public JsonArray asJsonArray() {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(m0.abc(this.domainName));
            jsonArray.add(m0.abc(this.addressList));
            jsonArray.add(m0.abc(Long.valueOf(this.dnsStart)));
            jsonArray.add(m0.abc(Long.valueOf(this.dnsStop)));
            jsonArray.add(m0.abc(Boolean.valueOf(this.isSuccess)));
            return jsonArray;
        }
    }

    public static class SocketEvent extends CollectableArray {
        private String cipherSuite;
        private long connectEnd;
        private long connectStart;
        private String errorDesc;
        private String httpVersion;
        private String inetaddress;
        private boolean isHttps;
        private boolean isSuccess;
        private long secConnectEnd;
        private long secConnectStart;
        private String tlsVersion;

        public SocketEvent(mlk mlkVar) {
            this.inetaddress = mlkVar.fgh();
            this.connectEnd = mlkVar.bcd();
            this.connectStart = mlkVar.cde();
            this.secConnectEnd = mlkVar.ghi();
            this.secConnectStart = mlkVar.hij();
            this.isHttps = mlkVar.jkl();
            this.httpVersion = mlkVar.efg();
            this.tlsVersion = mlkVar.ijk();
            this.cipherSuite = mlkVar.abc();
            this.isSuccess = mlkVar.klm();
            this.errorDesc = mlkVar.def();
        }

        @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
        public JsonArray asJsonArray() {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(m0.abc(this.inetaddress));
            jsonArray.add(m0.abc(Long.valueOf(this.connectStart)));
            jsonArray.add(m0.abc(Long.valueOf(this.connectEnd)));
            jsonArray.add(m0.abc(Long.valueOf(this.secConnectStart)));
            jsonArray.add(m0.abc(Long.valueOf(this.secConnectEnd)));
            jsonArray.add(m0.abc(Boolean.valueOf(this.isHttps)));
            jsonArray.add(m0.abc(this.httpVersion));
            jsonArray.add(m0.abc(this.tlsVersion));
            jsonArray.add(m0.abc(this.cipherSuite));
            jsonArray.add(m0.abc(Boolean.valueOf(this.isSuccess)));
            jsonArray.add(m0.abc(this.errorDesc));
            return jsonArray;
        }
    }

    public HttpEvent(vwx vwxVar) {
        this.nQoETransactionId = "";
        this.nuwaTraceNeIn = "";
        this.nuwaTraceNeOut = "";
        this.nuwaSampleState = "0";
        this.nuwaTraceRqIn = "";
        this.nuwaTraceRqOut = "";
        this.timestamp = vwxVar.nml();
        this.eventName = EventType.NATIVE_HTTP;
        this.url = vwxVar.kji();
        this.httpMethod = vwxVar.nop();
        this.totalTime = vwxVar.lkj();
        this.statusCode = vwxVar.mlk();
        this.bytesReceived = vwxVar.abc();
        this.bytesSent = vwxVar.bcd();
        this.contentType = vwxVar.ghi();
        this.contentLength = vwxVar.fgh();
        this.errorMessage = vwxVar.lmn();
        RuntimeEnvInformation runtimeEnvInformation = Agent.getRuntimeEnvInformation();
        this.runtimeEnvInformation = runtimeEnvInformation;
        runtimeEnvInformation.setSessionArray(vwxVar.qpo());
        this.domain = vwxVar.klm();
        this.followUpTimes = vwxVar.mno();
        this.serverIp = vwxVar.vut();
        this.dnsInfos = copyDnsInfoToEventList(vwxVar.ijk());
        this.dnsFailedTimes = vwxVar.hij();
        this.dnsTotalTimes = vwxVar.jkl();
        this.socketInfos = copySocketInfoToEventList(vwxVar.pon());
        this.connectFailedTimes = vwxVar.def();
        this.connectTotalTimes = vwxVar.efg();
        this.requestHeadersStart = vwxVar.zab();
        this.requestHeadersEnd = vwxVar.yza();
        this.requestBodyStart = vwxVar.xyz();
        this.requestBodyEnd = vwxVar.wxy();
        this.responseHeaderStart = vwxVar.wvu();
        this.responseHeaderEnd = vwxVar.xwv();
        this.responseBodyStart = vwxVar.yxw();
        this.responseBodyEnd = vwxVar.zyx();
        this.stackTrace = vwxVar.onm();
        this.errorMessage = vwxVar.lmn();
        this.libType = vwxVar.opq();
        this.cdnProvider = vwxVar.cde();
        this.serviceCode = vwxVar.uts();
        this.serviceInteractionId = vwxVar.tsr();
        this.serviceSessionId = vwxVar.srq();
        this.serviceTraceId = vwxVar.rqp();
        this.nuwaTraceId = vwxVar.rst();
        this.nuwaSpanId = vwxVar.qrs();
        this.nuwaTraceNeIn = vwxVar.stu();
        this.nuwaTraceNeOut = vwxVar.tuv();
        this.nuwaSampleState = vwxVar.pqr();
        this.nuwaTraceRqIn = vwxVar.uvw();
        this.nuwaTraceRqOut = vwxVar.vwx();
    }

    private JsonArray copyDnsInfoToEventList(List<nml> list) {
        if (list == null) {
            return new JsonArray();
        }
        int size = list.size();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < size; i++) {
            jsonArray.add(new DnsEvent(list.get(i)).asJson());
        }
        return jsonArray;
    }

    private JsonArray copySocketInfoToEventList(List<mlk> list) {
        if (list == null) {
            return new JsonArray();
        }
        int size = list.size();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < size; i++) {
            jsonArray.add(new SocketEvent(list.get(i)).asJson());
        }
        return jsonArray;
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.runtimeEnvInformation.asJsonArray());
        jsonArray.add(m0.abc(Long.valueOf(this.timestamp)));
        jsonArray.add(m0.abc(this.url));
        jsonArray.add(m0.abc(Integer.valueOf(this.libType)));
        jsonArray.add(m0.abc(this.domain));
        jsonArray.add(m0.abc(this.cdnProvider));
        jsonArray.add(m0.abc(this.serverIp));
        jsonArray.add(m0.abc(this.httpMethod));
        jsonArray.add(m0.abc(this.contentType));
        jsonArray.add(m0.abc(Long.valueOf(this.contentLength)));
        jsonArray.add(m0.abc(Integer.valueOf(this.statusCode)));
        jsonArray.add(m0.abc(this.serviceCode));
        jsonArray.add(m0.abc(Long.valueOf(this.totalTime)));
        jsonArray.add(m0.abc(Long.valueOf(this.requestHeadersStart)));
        jsonArray.add(m0.abc(Long.valueOf(this.requestHeadersEnd)));
        jsonArray.add(m0.abc(Long.valueOf(this.requestBodyStart)));
        jsonArray.add(m0.abc(Long.valueOf(this.requestBodyEnd)));
        jsonArray.add(m0.abc(Long.valueOf(this.bytesSent)));
        jsonArray.add(m0.abc(Long.valueOf(this.responseHeaderStart)));
        jsonArray.add(m0.abc(Long.valueOf(this.responseHeaderEnd)));
        jsonArray.add(m0.abc(Long.valueOf(this.responseBodyStart)));
        jsonArray.add(m0.abc(Long.valueOf(this.responseBodyEnd)));
        jsonArray.add(m0.abc(Long.valueOf(this.bytesReceived)));
        jsonArray.add(m0.abc(Integer.valueOf(this.followUpTimes)));
        jsonArray.add(m0.abc(Integer.valueOf(this.dnsFailedTimes)));
        jsonArray.add(m0.abc(Integer.valueOf(this.dnsTotalTimes)));
        jsonArray.add(this.dnsInfos);
        jsonArray.add(m0.abc(Integer.valueOf(this.connectFailedTimes)));
        jsonArray.add(m0.abc(Integer.valueOf(this.connectTotalTimes)));
        jsonArray.add(this.socketInfos);
        jsonArray.add(m0.abc(this.errorMessage));
        jsonArray.add(m0.abc(this.stackTrace));
        jsonArray.add(m0.abc(this.nQoETransactionId));
        jsonArray.add(m0.abc(this.serviceSessionId));
        jsonArray.add(m0.abc(this.serviceInteractionId));
        jsonArray.add(m0.abc(this.serviceTraceId));
        jsonArray.add(m0.abc(this.nuwaTraceId));
        jsonArray.add(m0.abc(this.nuwaSpanId));
        jsonArray.add(m0.abc(this.nuwaTraceNeIn));
        jsonArray.add(m0.abc(this.nuwaTraceNeOut));
        jsonArray.add(m0.abc(this.nuwaSampleState));
        jsonArray.add(m0.abc(this.nuwaTraceRqIn));
        jsonArray.add(m0.abc(this.nuwaTraceRqOut));
        return jsonArray;
    }

    public String getUrl() {
        return this.url;
    }

    public void setNQoETransactionId(String str) {
        this.nQoETransactionId = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public HttpEvent(vwx vwxVar, String str) {
        this(vwxVar);
        if (TextUtils.isEmpty(this.stackTrace)) {
            this.stackTrace = str;
        }
    }
}
