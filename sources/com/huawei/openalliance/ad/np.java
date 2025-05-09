package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes5.dex */
public class np extends jj<com.huawei.openalliance.ad.views.interfaces.k> implements oe<com.huawei.openalliance.ad.views.interfaces.k> {
    private Context d;
    private com.huawei.openalliance.ad.inter.data.h e;
    private int f;
    private int g;

    @Override // com.huawei.openalliance.ad.oe
    public void a() {
    }

    @Override // com.huawei.openalliance.ad.oe
    public boolean c() {
        if (!com.huawei.openalliance.ad.utils.dd.g() || this.f7126a == null) {
            return false;
        }
        return "3".equalsIgnoreCase(this.f7126a.ad());
    }

    @Override // com.huawei.openalliance.ad.oe
    public void c(com.huawei.openalliance.ad.inter.data.h hVar) {
        this.e = hVar;
        this.f7126a = pn.a(hVar);
        Context context = this.d;
        this.b = new ou(context, new sh(context), this.f7126a);
    }

    @Override // com.huawei.openalliance.ad.oe
    public boolean b(com.huawei.openalliance.ad.inter.data.h hVar) {
        AppInfo appInfo = hVar.getAppInfo();
        return com.huawei.openalliance.ad.utils.bc.e(hVar.B()) && !com.huawei.openalliance.ad.utils.i.a(this.d, appInfo == null ? "" : appInfo.getPackageName());
    }

    @Override // com.huawei.openalliance.ad.oe
    public boolean b(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return false;
        }
        return com.huawei.openalliance.ad.utils.bc.a(contentRecord.B());
    }

    @Override // com.huawei.openalliance.ad.oe
    public void b(String str, com.huawei.openalliance.ad.inter.data.h hVar, final IRewardAdStatusListener iRewardAdStatusListener) {
        if (TextUtils.isEmpty(str) || hVar == null || iRewardAdStatusListener == null) {
            ho.c("RewardAdPresenter", "directReward, param invalid.");
            return;
        }
        if (hVar.Z()) {
            ho.b("RewardAdPresenter", "hasRewarded, skip reward.");
            return;
        }
        hVar.h(true);
        final ContentRecord a2 = pn.a(hVar);
        d().setContentRecord(a2);
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.np.2
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(np.this.d).d(a2);
                iRewardAdStatusListener.onRewarded();
            }
        });
        a(str, a2, hVar);
    }

    @Override // com.huawei.openalliance.ad.oe
    public void b(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.np.3
            @Override // java.lang.Runnable
            public void run() {
                File file = new File(str);
                if (com.huawei.openalliance.ad.utils.ae.d(file)) {
                    VideoInfo R = np.this.f7126a == null ? null : np.this.f7126a.R();
                    if (R == null || !com.huawei.openalliance.ad.utils.ae.a(R.g(), file)) {
                        ho.b("RewardAdPresenter", "delete invalid cacheFile.");
                        com.huawei.openalliance.ad.utils.ae.a(np.this.d, str, "normal");
                    }
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.oe
    public void b() {
        this.b.b();
    }

    @Override // com.huawei.openalliance.ad.oe
    public boolean a(String str, int i) {
        if (TextUtils.isEmpty(str) || com.huawei.openalliance.ad.utils.cz.j(str)) {
            this.g = 0;
            return false;
        }
        if (this.f == i) {
            this.g++;
        } else {
            this.g = 0;
            this.f = i;
        }
        return this.g > 25;
    }

    @Override // com.huawei.openalliance.ad.oe
    public boolean a(com.huawei.openalliance.ad.inter.data.h hVar) {
        if (hVar == null) {
            return false;
        }
        return com.huawei.openalliance.ad.utils.bc.d(hVar.B());
    }

    @Override // com.huawei.openalliance.ad.oe
    public boolean a(int i, MaterialClickInfo materialClickInfo) {
        com.huawei.openalliance.ad.inter.data.h hVar = this.e;
        if (hVar == null) {
            return false;
        }
        hVar.e(true);
        ho.a("RewardAdPresenter", "begin to deal click, clickSource: %s", Integer.valueOf(i));
        HashMap hashMap = new HashMap();
        hashMap.put("appId", this.e.H());
        hashMap.put("thirdId", this.e.G());
        hashMap.put(AdShowExtras.DOWNLOAD_SOURCE, String.valueOf(5));
        ta a2 = sz.a(this.d, this.f7126a, hashMap);
        boolean a3 = a2.a();
        if (a3) {
            a(a2, i, materialClickInfo);
        }
        return a3;
    }

    @Override // com.huawei.openalliance.ad.oe
    public void a(boolean z) {
        if (this.b == null) {
            return;
        }
        this.b.b(z);
    }

    @Override // com.huawei.openalliance.ad.oe
    public void a(String str, com.huawei.openalliance.ad.inter.data.h hVar, IRewardAdStatusListener iRewardAdStatusListener) {
        if (hVar == null || TextUtils.isEmpty(str)) {
            ho.c("RewardAdPresenter", "invalid status");
            return;
        }
        ho.b("RewardAdPresenter", "notifyReward, condition:%s", str);
        if (hVar.Z()) {
            return;
        }
        if ("1".equals(str) || str.equals(hVar.Y()) || "-1".equals(str)) {
            ho.a("RewardAdPresenter", "onAdRewarded, condition: %s", str);
            b(str, hVar, iRewardAdStatusListener);
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.np.1
                @Override // java.lang.Runnable
                public void run() {
                    np.this.d().c();
                }
            });
        }
    }

    public void a(String str, ContentRecord contentRecord, com.huawei.openalliance.ad.inter.data.h hVar) {
        Context context = this.d;
        ou ouVar = new ou(context, new sh(context), contentRecord);
        ho.a("RewardAdPresenter", "onAdRewarded, createEvent start:%s", str);
        ouVar.a(hVar.k(), hVar.l(), "");
    }

    @Override // com.huawei.openalliance.ad.oe
    public void a(String str, ContentRecord contentRecord) {
        ho.b("RewardAdPresenter", "report Type is " + str);
        new com.huawei.openalliance.ad.analysis.h(this.d).d(contentRecord, str);
    }

    @Override // com.huawei.openalliance.ad.oe
    public void a(long j, int i, Integer num) {
        String f = f();
        com.huawei.openalliance.ad.inter.data.h hVar = this.e;
        if (hVar != null) {
            ho.a("RewardAdPresenter", "slotId: %s, contentId: %s, slot pos: %s", hVar.P(), this.e.getContentId(), f);
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(j)).a(Integer.valueOf(i)).d(f).e(g()).b(num);
        this.b.a(c0207a.a());
    }

    @Override // com.huawei.openalliance.ad.oe
    public String a(int i, int i2, com.huawei.openalliance.ad.inter.data.h hVar) {
        Resources resources = this.d.getResources();
        return hVar.Z() ? i > 0 ? String.format(Locale.ENGLISH, "%s | %s", resources.getQuantityString(R.plurals._2130903535_res_0x7f0301ef, i, Integer.valueOf(i)), resources.getString(R.string._2130851145_res_0x7f023549)) : resources.getString(R.string._2130851145_res_0x7f023549) : String.format(Locale.ENGLISH, "%s | %s", resources.getQuantityString(R.plurals._2130903535_res_0x7f0301ef, i, Integer.valueOf(i)), resources.getQuantityString(R.plurals._2130903533_res_0x7f0301ed, i2, Integer.valueOf(i2)));
    }

    @Override // com.huawei.openalliance.ad.oe
    public View a(IRemoteCreator iRemoteCreator, bv bvVar, ContentRecord contentRecord, com.huawei.openalliance.ad.inter.data.h hVar) {
        if (iRemoteCreator != null && contentRecord != null) {
            String b = com.huawei.openalliance.ad.utils.be.b(AdContentData.a(this.d, contentRecord));
            Bundle bundle = new Bundle();
            bundle.putBinder(ParamConstants.Param.CONTEXT, (IBinder) ObjectWrapper.wrap(this.d));
            bundle.putString("content", b);
            bundle.putInt("sdkVersion", 30474310);
            bundle.putBoolean("isMute", hVar.W());
            bundle.putBoolean(ParamConstants.RewardCfg.REWARDED, hVar.Z());
            bundle.putBoolean("alertSwitch", hVar.ad());
            bundle.putInt("audioFocusType", hVar.ae());
            try {
                View view = (View) ObjectWrapper.unwrap(iRemoteCreator.newRewardTemplateView(bundle, bvVar));
                if (view == null) {
                    ho.c("RewardAdPresenter", "remote view is null.");
                    return null;
                }
                iRemoteCreator.bindData(ObjectWrapper.wrap(view), b);
                ho.b("RewardAdPresenter", "bind data end, contentId: %s", contentRecord.m());
                return view;
            } catch (Throwable th) {
                ho.c("RewardAdPresenter", "create template view ex: %s", th.getClass().getSimpleName());
            }
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.oe
    public int a(ContentRecord contentRecord, int i) {
        boolean b = b(contentRecord);
        int H = (contentRecord == null || contentRecord.h() == null) ? 2 : contentRecord.h().H();
        if (!b || H < 1 || H > 5 || ((H == 1 || H == 5) && (contentRecord == null || contentRecord.ae() == null || TextUtils.isEmpty(contentRecord.ae().getIconUrl())))) {
            return 2;
        }
        ho.b("RewardAdPresenter", "request orientation %s", Integer.valueOf(i));
        if (com.huawei.openalliance.ad.utils.dd.a(i) || H == 1) {
            return H;
        }
        return 2;
    }

    public static boolean a(ContentRecord contentRecord) {
        return contentRecord != null && contentRecord.h() != null && "1".equals(contentRecord.ad()) && "4".equals(contentRecord.h().I());
    }

    private void a(ta taVar, int i, MaterialClickInfo materialClickInfo) {
        b.a aVar = new b.a();
        aVar.b(taVar.c()).a(Integer.valueOf(i)).a(materialClickInfo);
        this.b.a(aVar.a());
    }

    public static int a(Context context, int i) {
        int bO = (fh.b(context).bO() * i) / 100000;
        if (bO <= 0) {
            bO = (i * 90) / 100000;
        }
        return Math.min(bO, 27);
    }

    public np(Context context, com.huawei.openalliance.ad.views.interfaces.k kVar) {
        this.d = context;
        a((np) kVar);
        this.b = new ou(context, new sh(context));
    }
}
