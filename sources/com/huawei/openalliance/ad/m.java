package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static m f7194a;
    private static final byte[] b = new byte[0];
    private final Map<String, g> c = new HashMap();
    private final Map<String, Class<? extends g>> d;
    private final List<String> e;

    public g a(String str) {
        StringBuilder sb;
        String sb2;
        if (!TextUtils.isEmpty(str)) {
            g gVar = this.c.get(str);
            if (gVar == null) {
                ho.a("JsbCmdManager", "create action %s", str);
                Class<? extends g> cls = this.d.get(str);
                if (cls == null) {
                    sb = new StringBuilder("no class found for cmd: ");
                } else {
                    try {
                        gVar = cls.newInstance();
                    } catch (InstantiationException unused) {
                        ho.c("JsbCmdManager", "get cmd %s InstantiationException", str);
                    } catch (Throwable th) {
                        ho.c("JsbCmdManager", "get cmd %s: %s", str, th.getClass().getSimpleName());
                    }
                    if (gVar == null) {
                        sb = new StringBuilder("no instance created for cmd: ");
                    } else {
                        this.c.put(str, gVar);
                    }
                }
                sb.append(str);
                sb2 = sb.toString();
            }
            return gVar;
        }
        sb2 = "get cmd, method is empty";
        ho.c("JsbCmdManager", sb2);
        return null;
    }

    public static m a() {
        m mVar;
        synchronized (b) {
            if (f7194a == null) {
                f7194a = new m();
            }
            mVar = f7194a;
        }
        return mVar;
    }

    private m() {
        HashMap hashMap = new HashMap();
        this.d = hashMap;
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        hashMap.put("pps.native.request", v.class);
        hashMap.put("pps.reward.request", x.class);
        hashMap.put("pps.interstitial.request", u.class);
        hashMap.put("pps.placement.request", w.class);
        hashMap.put("pps.action.click", h.class);
        hashMap.put("pps.download.progress", av.class);
        hashMap.put("pps.download.status", aw.class);
        hashMap.put("pps.download.reserveapp", ax.class);
        hashMap.put("pps.download.start", az.class);
        hashMap.put("pps.click.complianceele", k.class);
        hashMap.put("pps.click.share", l.class);
        hashMap.put("pps.download.resume", ay.class);
        hashMap.put("pps.download.pause", au.class);
        hashMap.put("pps.download.cancel", ah.class);
        hashMap.put("pps.listener.appstatus", ap.class);
        hashMap.put("pps.listener.offDownloadChange", ai.class);
        hashMap.put("pps.listener.browserappstatus", al.class);
        hashMap.put("pps.listener.appprogress", ao.class);
        hashMap.put("pps.listener.appopen", ak.class);
        hashMap.put("pps.listener.downloadcancel", am.class);
        hashMap.put("pps.activity.reward", ac.class);
        hashMap.put("pps.activity.interstitial", ab.class);
        hashMap.put("pps.advertiserinfo.show", aa.class);
        hashMap.put("pps.feedback.click", o.class);
        hashMap.put("pps.feedback.toggle", p.class);
        hashMap.put("pps.common.report", bd.class);
        hashMap.put("pps.common.analysis", n.class);
        hashMap.put("pps.listener.webopen", at.class);
        hashMap.put("pps.listener.webclose", ar.class);
        hashMap.put("pps.listener.webloadfinish", as.class);
        hashMap.put("pps.event.showstart", bl.class);
        hashMap.put("pps.event.praise", bj.class);
        hashMap.put("pps.event.show", bk.class);
        hashMap.put("pps.event.click", bb.class);
        hashMap.put("pps.event.close", bc.class);
        hashMap.put("pps.event.playtime", bi.class);
        hashMap.put("pps.event.playstart", bh.class);
        hashMap.put("pps.event.playpause", bf.class);
        hashMap.put("pps.event.playresume", bg.class);
        hashMap.put("pps.event.playend", be.class);
        hashMap.put("pps.settings", y.class);
        hashMap.put("pps.process.whythisad", t.class);
        hashMap.put("pps.listener.appreservestatus", aj.class);
        if (com.huawei.openalliance.ad.utils.ck.c(Constants.CONSENT_SDK)) {
            hashMap.put("pps.consent.query", ad.class);
            hashMap.put("pps.set.consentstatus", af.class);
            hashMap.put("pps.set.consentpromise", ae.class);
        }
        hashMap.put("pps.api.req.getbody", q.class);
        hashMap.put("pps.api.parse.ad", s.class);
        arrayList.add("pps.action.click");
        arrayList.add("pps.activity.reward");
        arrayList.add("pps.activity.interstitial");
    }
}
