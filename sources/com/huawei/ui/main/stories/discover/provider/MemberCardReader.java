package com.huawei.ui.main.stories.discover.provider;

import android.content.Context;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.health.marketing.datatype.RecommendResourceInfo;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.stories.discover.provider.MemberCardReader;
import defpackage.dqj;
import defpackage.koq;
import defpackage.npt;
import defpackage.nrf;
import defpackage.pfy;
import defpackage.rhv;
import defpackage.rhz;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public class MemberCardReader implements MemberBaseCardReader {
    protected MemberCardListener c;
    protected Context d;
    private String h;
    private String j;
    private String n;
    private List<RecommendResourceInfo> o;
    private String p;
    private List<Integer> q;
    private String s;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f9706a = false;
    protected boolean e = false;
    protected int i = -1;
    protected List<Integer> b = Arrays.asList(new Integer[3]);
    private boolean m = false;
    private boolean l = false;
    private boolean g = false;
    private rhv k = new rhv();
    private int f = -1;

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void queryLabel() {
    }

    public MemberCardReader(Context context) {
        LogUtil.c("VIPCard_", "MemberCardReader");
        if (context != null) {
            this.d = context;
        } else {
            this.d = BaseApplication.e();
        }
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void initCardReader(List<RecommendResourceInfo> list) {
        if (koq.c(list)) {
            LogUtil.c(g(), "recommendResources", list.toString());
            this.o = list;
        } else {
            LogUtil.e(g(), "recommendResources is empty");
        }
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void registerCardListener(MemberCardListener memberCardListener) {
        this.c = memberCardListener;
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void unRegisterListener() {
        this.c = null;
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void setOrder(int i) {
        this.i = i;
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void setDataChangeTrue() {
        LogUtil.c(g(), "setDataChangeTrue");
        this.f9706a = true;
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void setPrivacyStatus(boolean z, boolean z2, rhv rhvVar) {
        this.l = z;
        this.g = z2;
        this.k = rhvVar;
    }

    public boolean e() {
        return this.g;
    }

    public boolean j() {
        return this.l;
    }

    public int b() {
        return this.f;
    }

    public boolean e(String str) {
        rhv rhvVar = this.k;
        if (rhvVar == null || koq.b(rhvVar.b())) {
            LogUtil.e(g(), "mLabelsRsp is not set");
            return false;
        }
        for (rhz rhzVar : this.k.b()) {
            if (!TextUtils.isEmpty(str) && str.equals(rhzVar.h())) {
                return rhzVar.l() == 0;
            }
        }
        return false;
    }

    public void a(List<Integer> list, MemberBaseCardReader memberBaseCardReader) {
        LogUtil.c(g(), "registerHealthDataStatus");
        HiHealthNativeApi.a(this.d).subscribeHiHealthData(list, new b(memberBaseCardReader));
    }

    public void t() {
        if (koq.c(this.q)) {
            LogUtil.c(g(), "unSubscribeList is not empty");
            HiHealthNativeApi.a(this.d).unSubscribeHiHealthData(this.q, new BaseCardConstructor.InnerHiUnSubscribeListener(g(), "isSuccess: "));
        }
    }

    public void r() {
        LogUtil.c(g(), "reset");
        this.n = null;
        this.p = null;
        this.m = false;
        this.f9706a = false;
    }

    public RecommendResourceInfo d(String str) {
        for (RecommendResourceInfo recommendResourceInfo : this.o) {
            if (!TextUtils.isEmpty(recommendResourceInfo.getLabelScenario()) && !TextUtils.isEmpty(str) && g(recommendResourceInfo.getLabelScenario().trim()).contains(str)) {
                LogUtil.c(g(), "select label ", str);
                return recommendResourceInfo;
            }
        }
        LogUtil.a(g(), "no resources on cloud for card", this.j, " label=", str);
        return null;
    }

    private int f(String str) {
        for (int i = 0; i < this.o.size(); i++) {
            String labelScenario = this.o.get(i).getLabelScenario();
            if (!TextUtils.isEmpty(labelScenario) && !TextUtils.isEmpty(str) && labelScenario.trim().contains(str)) {
                List list = (List) HiJsonUtil.b(labelScenario.trim(), new TypeToken<ArrayList<String>>() { // from class: com.huawei.ui.main.stories.discover.provider.MemberCardReader.5
                }.getType());
                LogUtil.c(g(), "find index of ", str, "the list is ", list);
                int indexOf = list.indexOf(str);
                if (indexOf != -1) {
                    return indexOf;
                }
            }
        }
        LogUtil.a(g(), "no resources on cloud for card", this.j, " label=", str);
        return -1;
    }

    public boolean b(String str) {
        for (RecommendResourceInfo recommendResourceInfo : this.o) {
            if (!TextUtils.isEmpty(recommendResourceInfo.getLabelScenario()) && g(recommendResourceInfo.getLabelScenario().trim()).contains(str)) {
                LogUtil.c(g(), "select label ", str);
                return true;
            }
        }
        LogUtil.a(g(), "no resources on cloud for card", this.j, " label=", str);
        return false;
    }

    public void doF_(RecommendResourceInfo recommendResourceInfo, Integer num, SpannableString spannableString, Integer num2, npt nptVar) {
        if (m() && n()) {
            LogUtil.c(g(), "callback bean for card ", this.j);
            b(recommendResourceInfo);
            RecommendCardBean build = new RecommendCardBean.Builder().setBackGround(nrf.cHS_(Uri.parse(recommendResourceInfo.getCoverPicture()))).setTitleColor(num).setTitle(recommendResourceInfo.getRecommendDescription()).setSubtitle(recommendResourceInfo.getResourceName()).setSubtitleDataLine(spannableString).setButtonColor(num2).setButtonText(recommendResourceInfo.getButtonDescription()).setCardId(this.j).setCardStatus(this.p).setResourceId(recommendResourceInfo.getResourceId()).setEventId(this.h).setClickSectionListener(nptVar).setLabelWeights(this.b).setSceneShowArticles(d(recommendResourceInfo)).build();
            if (this.c == null || !koq.c(this.b)) {
                return;
            }
            MemberCardListener memberCardListener = this.c;
            List<Integer> list = this.b;
            memberCardListener.onResponse(list.get(list.size() - 1).intValue(), build);
            this.m = true;
            return;
        }
        LogUtil.e(g(), "cannot callback for card ", this.j, " mLabelName=", this.n, " mScenario", this.p, " mEventId", this.h);
    }

    public String d(RecommendResourceInfo recommendResourceInfo) {
        if (recommendResourceInfo == null) {
            LogUtil.e(g(), "tmpStrSceneShowAritcles is empty");
            return "";
        }
        String sceneShowArticles = recommendResourceInfo.getSceneShowArticles();
        String d = d();
        LogUtil.c(g(), "tmpStrSceneShowAritcles:", sceneShowArticles);
        if (TextUtils.isEmpty(sceneShowArticles)) {
            LogUtil.e(g(), "tmpStrSceneShowAritcles is empty");
            return "";
        }
        if (sceneShowArticles.indexOf(d) == -1) {
            LogUtil.e(g(), "tmpStrSceneShowAritcles.indexOf is -1");
            return "";
        }
        List<String> g = g(sceneShowArticles);
        if (koq.b(g)) {
            LogUtil.a(g(), "sceneShowAritclesList is empty");
            return "";
        }
        String str = g.get(0);
        if (str.length() > d.length() + 1) {
            str = str.substring(d.length() + 1);
        }
        LogUtil.c(g(), "strSceneShowAritcles:", str);
        return str;
    }

    private List<String> g(String str) {
        ArrayList arrayList = new ArrayList();
        List<String> list = (List) HiJsonUtil.b(str.trim(), new TypeToken<ArrayList<String>>() { // from class: com.huawei.ui.main.stories.discover.provider.MemberCardReader.4
        }.getType());
        if (koq.b(list)) {
            return arrayList;
        }
        for (String str2 : list) {
            if (str2.contains(d())) {
                arrayList.add(str2);
            }
        }
        LogUtil.c(g(), "getParamContainCardId:", arrayList);
        return arrayList;
    }

    public void b(RecommendResourceInfo recommendResourceInfo) {
        s();
        int f = f(h());
        String labelPriority = recommendResourceInfo.getLabelPriority();
        LogUtil.c(g(), "card:", d(), " labelPriority is ", labelPriority, "index is ", Integer.valueOf(f));
        if (recommendResourceInfo == null || TextUtils.isEmpty(labelPriority)) {
            return;
        }
        List asList = Arrays.asList(labelPriority.substring(1, labelPriority.length() - 1).split(","));
        if (!koq.d(asList, f)) {
            LogUtil.e(g(), "index is out of bounds");
            return;
        }
        String[] split = ((String) asList.get(f)).split(Constants.LINK);
        if (split.length == 0) {
            LogUtil.e(g(), "cloud not set weight of labels");
            return;
        }
        for (int i = 0; i < split.length; i++) {
            this.b.set(i, Integer.valueOf(CommonUtils.e(split[i], -1)));
        }
    }

    private void s() {
        if (this.b == null) {
            this.b = Arrays.asList(new Integer[3]);
        }
        this.b.set(0, -1);
        this.b.set(1, -1);
        this.b.set(2, pfy.d.get(d()));
    }

    public List<Integer> f() {
        return this.b;
    }

    public String d(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(g(), "linkurl is empty");
            return "";
        }
        String str3 = str.contains("?") ? "&" : "?";
        String[] split = str.split(str3);
        if (split != null) {
            boolean z = false;
            for (int i2 = 0; i2 < split.length; i2++) {
                if (split[i2].contains("pullfrom=")) {
                    split[i2] = "pullfrom=" + this.j;
                } else if (split[i2].contains("resourceId=")) {
                    split[i2] = "resourceId=" + this.p;
                } else if (split[i2].contains("resourceName=")) {
                    split[i2] = "resourceName=" + str2;
                } else if (split[i2].contains("pullOrder=")) {
                    split[i2] = "pullOrder=" + String.valueOf(i + 1);
                }
                z = true;
            }
            if (z) {
                List asList = Arrays.asList(split);
                LogUtil.c(g(), "hasBiWords, tmp=", asList.toString());
                return String.join(str3, asList);
            }
        }
        LogUtil.c(g(), "do not have bi words");
        return str + str3 + "pullfrom=" + this.j + str3 + "resourceName=" + str2 + str3 + "resourceId=" + this.p + str3 + "pullOrder=" + String.valueOf(i + 1);
    }

    protected boolean m() {
        return (TextUtils.isEmpty(this.n) || TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.p) || TextUtils.isEmpty(this.h)) ? false : true;
    }

    protected boolean n() {
        return koq.c(this.o) && this.c != null;
    }

    public String d() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String i() {
        return this.p;
    }

    public void i(String str) {
        this.p = str;
    }

    public String h() {
        return this.n;
    }

    public void h(String str) {
        this.n = str;
    }

    public String c() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public boolean o() {
        return this.m;
    }

    public Context a() {
        return this.d;
    }

    public boolean l() {
        return this.f9706a;
    }

    public boolean k() {
        return this.e;
    }

    public void d(boolean z) {
        int i = this.f;
        if (i == -1) {
            this.e = true;
        } else {
            this.e = i != z;
        }
        this.f = z ? 1 : 0;
    }

    public String g() {
        return this.s;
    }

    public void j(String str) {
        this.s = str;
    }

    protected void b(HiHealthData hiHealthData) {
        setDataChangeTrue();
    }

    public void b(int i, RecommendResourceInfo recommendResourceInfo) {
        LogUtil.c(g(), "BaseOnClickSectionListener position is ", Integer.valueOf(i));
        if (LoginInit.getInstance(this.d).isBrowseMode()) {
            LoginInit.getInstance(this.d).browsingToLogin(new IBaseResponseCallback() { // from class: pgd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    MemberCardReader.this.e(i2, obj);
                }
            }, "");
        } else {
            a(i, recommendResourceInfo);
        }
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.c(g(), "browsingToLogin onResponse errorCode : ", Integer.valueOf(i));
    }

    protected void a(int i, RecommendResourceInfo recommendResourceInfo) {
        if (recommendResourceInfo == null) {
            LogUtil.c(g(), "recommendResourceInfo is null");
            return;
        }
        dqj.a(BaseApplication.e(), 1, new RecommendCardBean.Builder().setCardId(d()).setCardStatus(i()).setResourceId(recommendResourceInfo.getResourceId()).setTitle(recommendResourceInfo.getRecommendDescription()).setButtonText(recommendResourceInfo.getButtonDescription()).setEventId(c()).build());
        String d = d(recommendResourceInfo.getCoverLinkValue(), recommendResourceInfo.getRecommendDescription(), i);
        LogUtil.c(g(), "linkurl is ", d);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(d, new IBaseResponseCallback() { // from class: pgh
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    MemberCardReader.this.b(i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        LogUtil.c(g(), " onlick errorCode ", Integer.valueOf(i));
    }

    static class b implements HiSubscribeListener {
        private WeakReference<MemberBaseCardReader> d;

        public b(MemberBaseCardReader memberBaseCardReader) {
            this.d = new WeakReference<>(memberBaseCardReader);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            WeakReference<MemberBaseCardReader> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.c("VIPCard_", "mWeakReference is null");
                return;
            }
            MemberBaseCardReader memberBaseCardReader = weakReference.get();
            if (memberBaseCardReader == null) {
                LogUtil.c("VIPCard_", "reader is null");
            } else if (koq.c(list) && (memberBaseCardReader instanceof MemberCardReader)) {
                MemberCardReader memberCardReader = (MemberCardReader) memberBaseCardReader;
                LogUtil.c(memberCardReader.g(), "mSubscribeSleepDataListener onResult");
                memberCardReader.q = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            WeakReference<MemberBaseCardReader> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.c("VIPCard_", "mWeakReference is null");
                return;
            }
            MemberBaseCardReader memberBaseCardReader = weakReference.get();
            if (memberBaseCardReader == null) {
                LogUtil.c("VIPCard_", "reader is null");
            } else if (memberBaseCardReader instanceof MemberCardReader) {
                MemberCardReader memberCardReader = (MemberCardReader) memberBaseCardReader;
                LogUtil.c(memberCardReader.g(), "value changed, and newValue is ", hiHealthData);
                memberCardReader.b(hiHealthData);
            }
        }
    }
}
