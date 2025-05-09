package com.huawei.openalliance.ad.beans.server;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.AdTypeEvent;
import com.huawei.openalliance.ad.beans.metadata.Precontent;
import com.huawei.openalliance.ad.beans.metadata.Template;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class AdContentRsp extends RspBean {

    @f
    private long adFilterDuration;

    @f
    private String appCountry;

    @f
    private String appLanguage;
    private String cost;

    @Deprecated
    private int dsp1cost;

    @Deprecated
    private int dspcost;

    @f
    private Map<String, Integer> filterResultMap;
    private List<String> invalidSloganId;
    private List<String> invalidcontentid;
    private List<Ad30> multiad;
    private List<AdTypeEvent> noReportAdTypeEventList;

    @a
    private String ppsStore;
    private List<Precontent> premulticontent;

    @f
    private String realAppPkgName;
    private String reason;

    @d(a = "clientAdRequestId")
    private String requestId;
    private List<Template> templates;
    private List<String> todayNoShowContentid;
    private int retcode = -1;
    private int totalCacheSize = 800;
    private int adPreloadInterval = 0;

    @f
    private int requestType = 0;

    public String u() {
        return this.realAppPkgName;
    }

    public long t() {
        return this.adFilterDuration;
    }

    public Map<String, Integer> s() {
        return this.filterResultMap;
    }

    public String r() {
        return this.cost;
    }

    public String q() {
        return this.appCountry;
    }

    public String p() {
        return this.appLanguage;
    }

    public AdContentRsp o() {
        AdContentRsp adContentRsp = new AdContentRsp();
        adContentRsp.retcode = this.retcode;
        adContentRsp.reason = this.reason;
        adContentRsp.multiad = this.multiad;
        adContentRsp.invalidcontentid = this.invalidcontentid;
        adContentRsp.invalidSloganId = this.invalidSloganId;
        adContentRsp.todayNoShowContentid = this.todayNoShowContentid;
        adContentRsp.premulticontent = this.premulticontent;
        adContentRsp.ppsStore = this.ppsStore;
        adContentRsp.templates = this.templates;
        adContentRsp.totalCacheSize = this.totalCacheSize;
        adContentRsp.noReportAdTypeEventList = this.noReportAdTypeEventList;
        adContentRsp.adPreloadInterval = this.adPreloadInterval;
        adContentRsp.requestId = this.requestId;
        adContentRsp.dspcost = this.dspcost;
        adContentRsp.dsp1cost = this.dsp1cost;
        adContentRsp.requestType = this.requestType;
        adContentRsp.cost = this.cost;
        return adContentRsp;
    }

    public int n() {
        return this.requestType;
    }

    public int m() {
        int i = this.dsp1cost;
        if (i > 0) {
            return i;
        }
        return 0;
    }

    public int l() {
        int i = this.dspcost;
        if (i > 0) {
            return i;
        }
        return 0;
    }

    public String k() {
        return this.requestId;
    }

    public int j() {
        return this.adPreloadInterval;
    }

    public List<Template> i() {
        return this.templates;
    }

    public List<AdTypeEvent> h() {
        return this.noReportAdTypeEventList;
    }

    public void g(String str) {
        this.realAppPkgName = str;
    }

    public String g() {
        return this.ppsStore;
    }

    public void f(String str) {
        this.cost = str;
    }

    public void f(int i) {
        this.requestType = i;
    }

    public List<Precontent> f() {
        return this.premulticontent;
    }

    public void e(List<AdTypeEvent> list) {
        this.noReportAdTypeEventList = list;
    }

    public void e(String str) {
        this.appCountry = str;
    }

    public void e(int i) {
        this.dsp1cost = i;
    }

    public List<String> e() {
        return this.todayNoShowContentid;
    }

    public void d(List<Precontent> list) {
        this.premulticontent = list;
    }

    public void d(String str) {
        this.appLanguage = str;
    }

    public void d(int i) {
        this.dspcost = i;
    }

    public List<String> d() {
        return this.invalidcontentid;
    }

    public void c(List<String> list) {
        this.todayNoShowContentid = list;
    }

    public void c(String str) {
        this.requestId = str;
    }

    public void c(int i) {
        this.adPreloadInterval = i;
    }

    public List<Ad30> c() {
        return this.multiad;
    }

    public void b(List<String> list) {
        this.invalidcontentid = list;
    }

    public void b(String str) {
        this.ppsStore = str;
    }

    public void b(int i) {
        this.totalCacheSize = i;
    }

    public String b() {
        return this.reason;
    }

    public void a(Map<String, Integer> map) {
        this.filterResultMap = map;
    }

    public void a(List<Ad30> list) {
        this.multiad = list;
    }

    public void a(String str) {
        this.reason = str;
    }

    public void a(AdSlotParam adSlotParam) {
        RequestOptions l;
        if (adSlotParam == null || (l = adSlotParam.l()) == null) {
            return;
        }
        d(l.getAppLang());
        e(l.getAppCountry());
    }

    public void a(long j) {
        this.adFilterDuration = j;
    }

    public void a(int i) {
        this.retcode = i;
    }

    public int a() {
        return this.retcode;
    }
}
