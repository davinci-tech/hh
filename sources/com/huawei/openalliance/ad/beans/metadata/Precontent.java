package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.annotations.f;
import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.constant.AdConfigMapKey;
import com.huawei.openalliance.ad.utils.bg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Precontent extends RspBean {
    private Integer apiVer;
    private List<Asset> assets;
    private String contentid;
    private int creativetype;
    private String ctrlSwitchs;
    private List<ImageInfo> imageInfo;
    private List<MediaFile> mediaFileList;

    @f
    private List<String> noReportEventList;

    @d(a = "prio")
    private Integer priority;
    private String slotid;

    @d(a = AdConfigMapKey.SPLASH_PRELOAD_MODE)
    private Integer splashPreloadMode;
    private TemplateV3 template;
    private List<TemplateV3> templates;
    private VideoInfo videoInfo;

    public Integer n() {
        return this.splashPreloadMode;
    }

    public List<TemplateV3> m() {
        return this.templates;
    }

    public TemplateV3 l() {
        return this.template;
    }

    public List<Asset> k() {
        return this.assets;
    }

    public Integer j() {
        return this.apiVer;
    }

    public Integer i() {
        return this.priority;
    }

    public List<MediaFile> h() {
        return this.mediaFileList;
    }

    public List<String> g() {
        return this.noReportEventList;
    }

    public String f() {
        return this.ctrlSwitchs;
    }

    public VideoInfo e() {
        return this.videoInfo;
    }

    public void d(List<TemplateV3> list) {
        this.templates = list;
    }

    public List<ImageInfo> d() {
        return this.imageInfo;
    }

    public void c(List<Asset> list) {
        this.assets = list;
    }

    public void c(String str) {
        this.ctrlSwitchs = str;
    }

    public void c(Integer num) {
        this.splashPreloadMode = num;
    }

    public int c() {
        return this.creativetype;
    }

    public void b(List<MediaFile> list) {
        this.mediaFileList = list;
    }

    public void b(String str) {
        this.contentid = str;
    }

    public void b(Integer num) {
        this.apiVer = num;
    }

    public String b() {
        return this.contentid;
    }

    public void a(List<AdTypeEvent> list, int i) {
        List<String> b;
        if (bg.a(list)) {
            return;
        }
        for (AdTypeEvent adTypeEvent : list) {
            if (adTypeEvent != null && adTypeEvent.a() == i && (b = adTypeEvent.b()) != null && b.size() > 0) {
                ArrayList arrayList = new ArrayList();
                this.noReportEventList = arrayList;
                arrayList.addAll(b);
            }
        }
    }

    public void a(List<ImageInfo> list) {
        this.imageInfo = list;
    }

    public void a(String str) {
        this.slotid = str;
    }

    public void a(Integer num) {
        this.priority = num;
    }

    public void a(TemplateV3 templateV3) {
        this.template = templateV3;
    }

    public void a(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public void a(int i) {
        this.creativetype = i;
    }

    public String a() {
        return this.slotid;
    }
}
