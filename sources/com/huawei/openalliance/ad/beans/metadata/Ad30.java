package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.base.RspBean;
import java.util.List;

/* loaded from: classes5.dex */
public class Ad30 extends RspBean {
    private String brsetting;
    private String configMap;
    private List<Content> content;

    @f
    private List<Content> contentFiltered;
    private FlowControl fc;
    private List<ImpEX> impEXs;
    private String slotid;
    private int retcode30 = -1;
    private int adtype = -1;

    public FlowControl i() {
        return this.fc;
    }

    public List<Content> h() {
        return this.contentFiltered;
    }

    public String g() {
        return this.configMap;
    }

    public List<ImpEX> f() {
        return this.impEXs;
    }

    public String e() {
        return this.brsetting;
    }

    public int d() {
        return this.adtype;
    }

    public void c(List<Content> list) {
        this.contentFiltered = list;
    }

    public void c(String str) {
        this.configMap = str;
    }

    public List<Content> c() {
        return this.content;
    }

    public void b(List<ImpEX> list) {
        this.impEXs = list;
    }

    public void b(String str) {
        this.brsetting = str;
    }

    public void b(int i) {
        this.adtype = i;
    }

    public int b() {
        return this.retcode30;
    }

    public void a(List<Content> list) {
        this.content = list;
    }

    public void a(String str) {
        this.slotid = str;
    }

    public void a(FlowControl flowControl) {
        this.fc = flowControl;
    }

    public void a(int i) {
        this.retcode30 = i;
    }

    public String a() {
        return this.slotid;
    }
}
