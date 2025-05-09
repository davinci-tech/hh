package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.healthzone.model.FeedsPostReq;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import com.huawei.ui.main.stories.userprofile.card.familycard.beans.FamilyHealthCardInfo;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.exh;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sae extends rzv {
    private FamilyHealthCardInfo f;
    private d g;
    private ConcurrentHashMap<Long, rbe> i;
    private boolean j;
    private long k;
    private String l;
    private int m;
    private rbl n;
    private boolean o;
    private int r;

    public sae(Context context, PersonalCenterUiApi personalCenterUiApi) {
        super(context, personalCenterUiApi);
        this.m = 4;
        this.j = false;
        this.g = new d();
        this.i = new ConcurrentHashMap<>(3);
        this.f = new FamilyHealthCardInfo();
        this.o = false;
        this.r = 0;
        i();
    }

    private boolean c(List<raw> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<raw> it = list.iterator();
        while (it.hasNext()) {
            rat c = it.next().c();
            if (c != null && "01A".equals(c.a()) && str.equals(c.e())) {
                return true;
            }
        }
        return false;
    }

    private void i() {
        if (CommonUtil.cc()) {
            LogUtil.h("MyFamilyHealthCardData", "getCommonUsedDevices test version");
            return;
        }
        FamilyHealthZoneApi familyHealthZoneApi = (FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class);
        if (familyHealthZoneApi == null) {
            LogUtil.h("MyFamilyHealthCardData", "healthZoneApi is null");
        } else {
            familyHealthZoneApi.getCommonUsedDevices(new IBaseResponseCallback() { // from class: saf
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    sae.this.b(i, obj);
                }
            });
        }
    }

    /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            if (obj instanceof JSONObject) {
                List<raw> d2 = rbu.d((JSONObject) obj, "resultData", raw.class);
                LogUtil.a("MyFamilyHealthCardData", "getCommonUsedDevices list", d2);
                String androidId = FoundationCommonUtil.getAndroidId(this.d);
                if (c(d2, androidId)) {
                    return;
                }
                LogUtil.a("MyFamilyHealthCardData", "getCommonUsedDevices currentPhone does not exist");
                exf exfVar = new exf(androidId, "SET", LoginInit.getInstance(this.d).getDeviceId());
                ArrayList arrayList = new ArrayList();
                arrayList.add(exfVar);
                d(arrayList);
                return;
            }
            return;
        }
        LogUtil.h("MyFamilyHealthCardData", "getCommonUsedDevices failed, errorCode:", Integer.valueOf(i));
    }

    private void d(List<exf> list) {
        if (CommonUtil.cc()) {
            LogUtil.h("MyFamilyHealthCardData", "setCommonUsedDevices test version");
            return;
        }
        LogUtil.a("MyFamilyHealthCardData", "setCommonUsedDevices settings:", list);
        FamilyHealthZoneApi familyHealthZoneApi = (FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class);
        if (familyHealthZoneApi == null) {
            LogUtil.h("MyFamilyHealthCardData", "healthZoneApi is null");
        } else {
            familyHealthZoneApi.setCommonUsedDevices(list, new IBaseResponseCallback() { // from class: sad
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    sae.c(i, obj);
                }
            });
        }
    }

    static /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            LogUtil.a("MyFamilyHealthCardData", "setCommonUsedDevices successed.");
        } else {
            LogUtil.h("MyFamilyHealthCardData", "setCommonUsedDevices failed, errorCode:", Integer.valueOf(i));
        }
    }

    @Override // defpackage.rzv
    protected void e() {
        super.e();
        if (this.f16979a) {
            this.g.sendEmptyMessage(4);
        } else {
            d();
            ThreadPoolManager.d().execute(new Runnable() { // from class: sag
                @Override // java.lang.Runnable
                public final void run() {
                    sae.this.a();
                }
            });
        }
    }

    /* synthetic */ void a() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        b(countDownLatch);
        e(countDownLatch);
        try {
            LogUtil.a("MyFamilyHealthCardData", "initData is ", Boolean.valueOf(countDownLatch.await(3000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("MyFamilyHealthCardData", "initData interrupted");
        }
        h();
        g();
    }

    @Override // defpackage.rzv, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        e();
    }

    private void b(final CountDownLatch countDownLatch) {
        rbm.b(new ICloudOperationResult<rbh>() { // from class: sae.2
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void operationResult(rbh rbhVar, String str, boolean z) {
                LogUtil.c("MyFamilyHealthCardData", "memberInfoDataRsp ", Boolean.valueOf(z));
                if (rbhVar == null) {
                    LogUtil.h("MyFamilyHealthCardData", "memberInfoDataRsp is null");
                    countDownLatch.countDown();
                    return;
                }
                if (!z) {
                    String e = rbt.e(sae.this.d);
                    if (!TextUtils.isEmpty(e) && !e.equals(LoginInit.getInstance(sae.this.d).getAccountInfo(1011))) {
                        rbt.c(sae.this.d, new ArrayList(10), "key_health_zone_member_info_list");
                        rbt.a(sae.this.d);
                    }
                } else {
                    List<rbk> c = rbhVar.c();
                    if (c == null) {
                        c = new ArrayList<>(10);
                        LogUtil.h("MyFamilyHealthCardData", "memberInfoList is empty");
                    }
                    LogUtil.a("MyFamilyHealthCardData", "memberInfoList: ", c);
                    if (!rbt.a(sae.this.d, c, "key_health_zone_member_info_list")) {
                        LogUtil.c("MyFamilyHealthCardData", "cloud data has been updated ");
                        rbt.c(sae.this.d, c, "key_health_zone_member_info_list");
                        rbt.a(sae.this.d);
                    }
                }
                countDownLatch.countDown();
            }
        });
    }

    private void b(FeedsPostReq feedsPostReq, final CountDownLatch countDownLatch) {
        if (System.currentTimeMillis() - this.k < 2000) {
            LogUtil.h("MyFamilyHealthCardData", "getFeedsPostData request too often");
            countDownLatch.countDown();
        } else {
            this.k = System.currentTimeMillis();
            rbm.b(feedsPostReq, new ICloudOperationResult<rau>() { // from class: sae.3
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void operationResult(rau rauVar, String str, boolean z) {
                    if (rauVar == null) {
                        LogUtil.h("MyFamilyHealthCardData", "feedsPostRsp is null");
                        countDownLatch.countDown();
                        return;
                    }
                    if (z) {
                        LogUtil.c("MyFamilyHealthCardData", "feedsPostRsp is Success");
                        List<ras> b = rauVar.b();
                        if (b == null) {
                            b = new ArrayList<>(10);
                            LogUtil.h("MyFamilyHealthCardData", "allFeedsPostDataList is empty");
                        }
                        List a2 = sae.this.a(b);
                        if (a2.size() <= 0) {
                            sae.this.j = false;
                        } else {
                            LogUtil.a("MyFamilyHealthCardData", "cloud data has been updated ");
                            String d2 = rbt.d(sae.this.d, "key_health_zone_post_data");
                            sae.this.l = ((ras) a2.get(0)).b();
                            LogUtil.a("MyFamilyHealthCardData", "lastPostId  = ", d2, "  newPostId  = ", sae.this.l);
                            if (d2 == null || d2.equals(sae.this.l)) {
                                sae.this.j = false;
                            } else {
                                sae.this.j = true;
                            }
                            countDownLatch.countDown();
                            return;
                        }
                    } else {
                        LogUtil.h("MyFamilyHealthCardData", "feeds post callback is failed, errorCode = ", rauVar.getResultCode());
                    }
                    countDownLatch.countDown();
                }
            });
        }
    }

    private void c(final CountDownLatch countDownLatch) {
        rbm.d(new ICloudOperationResult<rbg>() { // from class: sae.4
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(rbg rbgVar, String str, boolean z) {
                if (rbgVar == null) {
                    LogUtil.h("MyFamilyHealthCardData", "latestPostIdRsp is null");
                    countDownLatch.countDown();
                    return;
                }
                if (z) {
                    LogUtil.a("MyFamilyHealthCardData", "LatestPostId LatestPostIdRsp is Success");
                    List<rbl> b = rbgVar.b();
                    if (b == null) {
                        b = new ArrayList<>(10);
                        LogUtil.h("MyFamilyHealthCardData", "LatestPostDataList is empty");
                    }
                    if (b.size() > 0) {
                        sae.this.n = b.get(0);
                        if (sae.this.n != null) {
                            ReleaseLogUtil.b("MyFamilyHealthCardData", "cloud data has been updated,mMemberPostId is ： ", sae.this.n.c());
                        }
                    }
                } else {
                    LogUtil.h("MyFamilyHealthCardData", "callback is failed, errorCode = ", rbgVar.getResultCode());
                }
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ras> a(List<ras> list) {
        ArrayList arrayList = new ArrayList();
        for (ras rasVar : list) {
            if (rasVar != null && rasVar.c() == 0) {
                arrayList.add(rasVar);
            }
        }
        return arrayList;
    }

    private void g() {
        if (this.g == null) {
            LogUtil.h("MyFamilyHealthCardData", "mFamilyHealthHandler is null");
            return;
        }
        this.i.clear();
        this.r = 0;
        List<rbd> c = rbt.c(this.d, "key_health_zone_member_info_list");
        ArrayList arrayList = new ArrayList();
        Iterator<rbd> it = c.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().d()));
        }
        Map<String, Integer> b = rbt.b(arrayList);
        boolean z = arrayList.contains(MessageConstant.CERTIFICATE) || arrayList.contains("10");
        if (koq.c(c)) {
            if (rbt.d(c, b) && !rbt.b()) {
                j();
                return;
            }
            for (rbd rbdVar : c) {
                LogUtil.c("MyFamilyHealthCardData", "status = ", Integer.valueOf(rbdVar.d()));
                if (rbdVar.d() != 40 && rbdVar.d() != 4000 && rbdVar.d() != 30 && (!z || MessageConstant.CERTIFICATE.equals(String.valueOf(rbdVar.d())) || "10".equals(String.valueOf(rbdVar.d())))) {
                    e(rbdVar.a(), 1);
                }
            }
            return;
        }
        if (koq.b(c) && !rbt.b()) {
            j();
            return;
        }
        this.g.sendEmptyMessage(1);
        this.f.setImageMap(new LinkedHashMap());
        rzz.a(this.f);
        this.g.sendEmptyMessage(4);
    }

    private void j() {
        try {
            e(Long.parseLong(LoginInit.getInstance(this.d).getAccountInfo(1011)), 1);
        } catch (NumberFormatException unused) {
            LogUtil.b("MyFamilyHealthCardData", "NumberFormatException");
        }
    }

    private void h() {
        if (this.g == null) {
            LogUtil.h("MyFamilyHealthCardData", "mFamilyHealthHandler is null");
            return;
        }
        List<rbd> c = rbt.c(this.d, "key_health_zone_member_info_list");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (rbd rbdVar : c) {
            String valueOf = String.valueOf(rbdVar.d());
            arrayList.add(valueOf);
            if (c(rbdVar)) {
                arrayList2.add(valueOf);
            }
        }
        LogUtil.a("MyFamilyHealthCardData", "statusList: ", arrayList, ", validNewMemberStatusList: ", arrayList2);
        d(arrayList, arrayList2, c);
        this.f.setShowType(this.m);
        this.g.sendEmptyMessage(2);
        this.g.sendEmptyMessage(3);
    }

    private void d(List<String> list, List<String> list2, List<rbd> list3) {
        this.m = -1;
        this.o = false;
        if (list2.contains(MessageConstant.CERTIFICATE) || list2.contains("10")) {
            LogUtil.a("MyFamilyHealthCardData", "refresh card for new member invitation");
            this.m = 0;
            this.o = true;
            return;
        }
        if (e(this.n)) {
            this.m = 7;
            this.o = true;
            return;
        }
        if (this.j) {
            this.m = 2;
            this.o = true;
            return;
        }
        Map<String, Integer> b = rbt.b(list);
        if (list3.size() >= 1 && !rbt.d(list3, b)) {
            this.m = 4;
            this.o = false;
        } else {
            if (list3.size() == 0 || rbt.d(list3, b)) {
                if (!rbt.b()) {
                    this.m = 5;
                } else {
                    this.m = 6;
                }
                this.o = false;
                return;
            }
            LogUtil.h("MyFamilyHealthCardData", "show default");
        }
    }

    private boolean c(rbd rbdVar) {
        JSONObject d2 = FunctionSetBeanHelper.c().d();
        if (d2 == null) {
            return true;
        }
        long optLong = d2.optLong("enterTime", 0L);
        return optLong == 0 || optLong <= rbdVar.c();
    }

    private void e(CountDownLatch countDownLatch) {
        FeedsPostReq feedsPostReq = new FeedsPostReq();
        feedsPostReq.setDataCursor("");
        feedsPostReq.setPageDirection(0);
        feedsPostReq.setPostLimit(15);
        feedsPostReq.setTmpLang(1);
        c(countDownLatch);
        b(feedsPostReq, countDownLatch);
        LogUtil.a("MyFamilyHealthCardData", "newPOST ", Boolean.valueOf(this.j), "newAbnormalPost ", this.n);
    }

    private void e(long j, int i) {
        if (i == 1) {
            this.i.put(Long.valueOf(j), new rbe());
        }
        c(j, i);
    }

    private void c(final long j, final int i) {
        ((FamilyHealthZoneApi) Services.c("PluginHealthZone", FamilyHealthZoneApi.class)).requestFindUserInfo(2, String.valueOf(j), new UserInfoCallback<exh.b>() { // from class: sae.1
            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void infoCallback(exh.b bVar) {
                String e;
                int i2 = i;
                if (i2 == 1) {
                    if (bVar == null || TextUtils.isEmpty(bVar.e())) {
                        e = rbt.e(sae.this.d, j);
                    } else {
                        e = bVar.e();
                    }
                    sae.this.d(j, e, i);
                    return;
                }
                if (i2 != 2) {
                    LogUtil.h("MyFamilyHealthCardData", "getUserImageAndNicknameFromSocial infoCallback defult");
                } else if (bVar != null) {
                    sae.this.a(i2);
                } else {
                    LogUtil.h("MyFamilyHealthCardData", "getWaitUser result is null");
                }
            }

            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            public void errorCallback(int i2) {
                LogUtil.h("MyFamilyHealthCardData", "getUserImageAndNicknameFromSocial errorCallback errorCode:", Integer.valueOf(i2));
                int i3 = i;
                if (i3 == 1) {
                    sae.this.d(j, rbt.e(sae.this.d, j), i);
                } else if (i3 == 2) {
                    sae.this.a(i3);
                } else {
                    LogUtil.h("MyFamilyHealthCardData", "getUserImageAndNicknameFromSocial errorCallback defult");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, String str, int i) {
        d dVar;
        this.r++;
        rbe rbeVar = this.i.get(Long.valueOf(j)) == null ? new rbe() : this.i.get(Long.valueOf(j));
        rbeVar.e(j);
        rbeVar.c(str);
        rbt.a(this.d, rbeVar);
        if (this.r != this.i.size() || (dVar = this.g) == null) {
            return;
        }
        dVar.sendEmptyMessage(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        d dVar = this.g;
        if (dVar != null) {
            dVar.sendEmptyMessage(i);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        l();
        LogUtil.c("MyFamilyHealthCardData", "onDestroy");
        this.h = null;
        this.i.clear();
    }

    private void d() {
        LogUtil.c("MyFamilyHealthCardData", "apply card cache");
        this.f = rzz.a();
        this.g.sendEmptyMessage(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.f16979a) {
            LogUtil.h("MyFamilyHealthCardData", "saveFamilyHealthCardCache is browser");
            return;
        }
        this.f.setImageMap(this.i);
        this.f.setShowType(this.m);
        rzz.a(this.f);
    }

    static class d extends Handler {
        private WeakReference<sae> c;

        private d(sae saeVar) {
            this.c = new WeakReference<>(saeVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            final sae saeVar = this.c.get();
            if (saeVar != null) {
                if (saeVar.h == null) {
                    LogUtil.h("MyFamilyHealthCardData", "mFamilyHealthCardData.mViewHolder is null");
                    return;
                }
                LogUtil.a("MyFamilyHealthCardData", "FamilyHealthHandler msg  == ", Integer.valueOf(message.what));
                boolean z = true;
                if (message.what == 1) {
                    if (!saeVar.f16979a) {
                        saeVar.f.setImageMap(saeVar.i);
                        rzz.a(saeVar.f);
                        return;
                    } else {
                        LogUtil.h("MyFamilyHealthCardData", "cardData.mFamilyHealthCardInfo is browser");
                        return;
                    }
                }
                if (message.what == 3) {
                    saeVar.d(saeVar.o, 9);
                } else if (message.what == 2) {
                    saeVar.e.c(saeVar.d.getString(saeVar.c(saeVar.m)));
                    saeVar.h.d(saeVar.e);
                } else if (message.what == 4) {
                    saeVar.e.c(saeVar.d.getString(saeVar.c(saeVar.f16979a ? 5 : saeVar.f.getShowType())));
                    if (!saeVar.f16979a && !saeVar.e.l()) {
                        z = false;
                    }
                    saeVar.d(z, 9);
                    saeVar.h.d(saeVar.e);
                }
                ThreadPoolManager.d().c("MyFamilyHealthCardData", new Runnable() { // from class: sae.d.4
                    @Override // java.lang.Runnable
                    public void run() {
                        saeVar.l();
                    }
                });
                return;
            }
            LogUtil.h("MyFamilyHealthCardData", "cardData is null");
        }
    }

    @Override // defpackage.rzv
    protected void c() {
        if (this.e != null) {
            return;
        }
        this.e = new rzs();
        this.e.b(5);
        this.e.a(R.mipmap._2131821256_res_0x7f1102c8);
        this.e.d(R$string.IDS_hwh_family_health_zone);
        this.e.c(this.d.getString(c(5)));
        this.e.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                sae.this.dUE_(view);
            }
        }, (BaseActivity) this.d, true, ""));
    }

    /* synthetic */ void dUE_(View view) {
        if (nsn.a(500)) {
            LogUtil.a("MyFamilyHealthCardData", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (e(this.n)) {
            rbt.a(this.d, nrv.e(this.n, rbl.class), "key_health_zone_abnormal_post_data");
        }
        if (this.j) {
            rbt.a(this.d, this.l, "key_health_zone_post_data");
            this.j = false;
        }
        LogUtil.a("MyFamilyHealthCardData", "FamilyHealth card clicked");
        f();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean e(rbl rblVar) {
        rbl rblVar2;
        if (rblVar == null) {
            LogUtil.h("MyFamilyHealthCardData", "memberPostId is null");
            return false;
        }
        try {
            rblVar2 = (rbl) HiJsonUtil.e(rbt.d(this.d, "key_health_zone_abnormal_post_data"), rbl.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.h("MyFamilyHealthCardData", "MemberPostId fromJson is jsonSyntaxException");
            rblVar2 = null;
        }
        if (rblVar2 != null) {
            ReleaseLogUtil.b("MyFamilyHealthCardData", "lastAbnormalPostId  = ", rblVar2.c(), "  newAbnormalPostId  = ", rblVar.c());
        } else {
            LogUtil.h("MyFamilyHealthCardData", "lastMemberPostId is null");
        }
        return (rblVar2 == null && !TextUtils.isEmpty(rblVar.c())) || !(rblVar2 == null || TextUtils.equals(rblVar2.c(), rblVar.c()));
    }

    private void f() {
        LogUtil.a("MyFamilyHealthCardData", "gotoFamilyHealth enter");
        b();
        ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).gotoFamilyHealth(this.d, knl.e("sIsFirstHealth"));
    }

    void b() {
        HashMap hashMap = new HashMap(1);
        FamilyHealthCardInfo a2 = rzz.a();
        if (a2 == null) {
            LogUtil.h("MyFamilyHealthCardData", "familyHeathCardinfo is null");
            return;
        }
        hashMap.put("click", 1);
        hashMap.put("type", String.valueOf(a2.getShowType()));
        ixx.d().d(this.d, AnalyticsValue.HEALTH_FAMILY_ZONE_2119070.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(int i) {
        LogUtil.a("MyFamilyHealthCardData", "getHealthCardDesc descType = ", Integer.valueOf(i));
        if (i == 0) {
            return R$string.IDS_health_new_members;
        }
        if (i == 2) {
            return R$string.IDS_health_banner_new_developments;
        }
        if (i == 4) {
            return R$string.IDS_health_banner_guard_family;
        }
        if (i == 5) {
            return R$string.IDS_health_invite_members;
        }
        if (i == 6) {
            return R$string.IDS_health_banner_features;
        }
        if (i == 7) {
            return R$string.IDS_health_banner_new_abnormal_notices;
        }
        LogUtil.h("MyFamilyHealthCardData", "getHealthCardDesc default");
        return R$string.IDS_health_banner_features;
    }

    @Override // defpackage.rzv, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "MyFamilyHealthCardData";
    }
}
