package com.huawei.featureuserprofile.award.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.featureuserprofile.award.ui.MyAwardActivity;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.AwardRecordsBean;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nqx;
import defpackage.smy;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class MyAwardActivity extends BaseActivity {
    private Context b;
    private AwardExpiredFragment d;
    private AwardValidFragment e;
    private List<String> f;
    private HealthSubTabWidget g;
    private nqx i;
    private HealthViewPager j;

    /* renamed from: a, reason: collision with root package name */
    private List<AwardRecordsBean> f1977a = new ArrayList();
    private Handler c = new b(this);

    /* loaded from: classes7.dex */
    static class b extends BaseHandler<MyAwardActivity> {
        b(MyAwardActivity myAwardActivity) {
            super(myAwardActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: tH_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MyAwardActivity myAwardActivity, Message message) {
            if (myAwardActivity == null || message == null) {
                LogUtil.h("MyAwardActivity", "handleMessageWhenReferenceNotNull fragment or msg is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                myAwardActivity.a();
            } else if (i == 1) {
                myAwardActivity.h();
            } else {
                LogUtil.h("MyAwardActivity", "MSG_NOT_MATCH");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("MyAwardActivity", "onCreate()");
        setContentView(R.layout.activity_my_award);
        cancelAdaptRingRegion();
        this.b = this;
        c();
        b();
        d();
        e();
    }

    private void e() {
        Intent intent = getIntent();
        String str = "";
        if (intent != null && intent.getExtras() != null) {
            str = intent.getExtras().getString("from", "");
        }
        HashMap hashMap = new HashMap();
        hashMap.put(ParamConstants.CallbackMethod.ON_SHOW, 1);
        hashMap.put("type", 3);
        LogUtil.a("MyAwardActivity", "setBiCollect from ", str);
        hashMap.put("from", str);
        ixx.d().d(this.b, AnalyticsValue.MY_ASSET_SHOW_2120122.value(), hashMap, 0);
    }

    private void c() {
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        arrayList.add(getString(R.string._2130838140_res_0x7f02027c));
        this.f.add(getString(R.string._2130838141_res_0x7f02027d));
    }

    private void b() {
        this.g = (HealthSubTabWidget) findViewById(R.id.tab_layout);
        g();
        this.j = (HealthViewPager) findViewById(R.id.viewpager);
        this.i = new nqx(this, this.j, this.g);
        for (int i = 0; i < this.f.size(); i++) {
            smy c = this.g.c(this.f.get(i));
            if (i == 0) {
                AwardValidFragment d = AwardValidFragment.d();
                this.e = d;
                this.i.c(c, d, true);
            } else {
                AwardExpiredFragment e = AwardExpiredFragment.e();
                this.d = e;
                this.i.c(c, e, false);
            }
        }
    }

    private void g() {
        if (LanguageUtil.j(this.b)) {
            return;
        }
        try {
            Field declaredField = this.g.getClass().getSuperclass().getDeclaredField("mSubTabItemMargin");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                declaredField.set(this.g, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131364456_res_0x7f0a0a68)));
            }
        } catch (IllegalAccessException e) {
            LogUtil.b("MyAwardActivity", e.getMessage());
        } catch (NoSuchFieldException e2) {
            LogUtil.b("MyAwardActivity", "can't get mSubTabItemMargin field for subtab", e2);
        }
    }

    private void d() {
        final a aVar = new a(this);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.award.ui.MyAwardActivity.3
            @Override // java.lang.Runnable
            public void run() {
                ((PayApi) Services.c("TradeService", PayApi.class)).awardListQuery(aVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (koq.b(this.f1977a)) {
            LogUtil.b("MyAwardActivity", "AwardRecordsBeans is empty");
            h();
            return;
        }
        ArrayList<AwardRecordsBean> arrayList = new ArrayList();
        arrayList.addAll(this.f1977a);
        List<AwardRecordsBean> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        for (AwardRecordsBean awardRecordsBean : arrayList) {
            if (awardRecordsBean.getIsExpire() == 0) {
                arrayList2.add(awardRecordsBean);
            } else {
                arrayList3.add(awardRecordsBean);
            }
        }
        if (this.e != null) {
            if (arrayList2.size() > 0) {
                List<AwardRecordsBean> arrayList4 = new ArrayList<>();
                arrayList4.addAll(arrayList2);
                a(arrayList4);
                this.e.e(arrayList4);
                b(arrayList2);
            } else {
                this.e.c();
            }
        }
        if (this.d != null) {
            if (arrayList3.size() > 0) {
                List<AwardRecordsBean> arrayList5 = new ArrayList<>();
                arrayList5.addAll(arrayList3);
                c(arrayList5);
                this.d.d(arrayList5);
                return;
            }
            this.d.d();
        }
    }

    /* loaded from: classes7.dex */
    static class a implements IBaseResponseCallback {
        private final WeakReference<MyAwardActivity> b;

        a(MyAwardActivity myAwardActivity) {
            this.b = new WeakReference<>(myAwardActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MyAwardActivity myAwardActivity = this.b.get();
            if (myAwardActivity == null) {
                LogUtil.h("MyAwardActivity", "activity is null");
                return;
            }
            if (i == 0 && (obj instanceof List)) {
                myAwardActivity.f1977a.addAll((List) obj);
                myAwardActivity.c.sendEmptyMessage(0);
            } else {
                LogUtil.h("MyAwardActivity", "awardQuery fail:", Integer.valueOf(i));
                myAwardActivity.c.sendEmptyMessage(1);
            }
        }
    }

    private void a(List<AwardRecordsBean> list) {
        Collections.sort(list, new Comparator() { // from class: brd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MyAwardActivity.this.e((AwardRecordsBean) obj, (AwardRecordsBean) obj2);
            }
        });
    }

    public /* synthetic */ int e(AwardRecordsBean awardRecordsBean, AwardRecordsBean awardRecordsBean2) {
        String a2 = a(awardRecordsBean);
        String exchangeStatus = awardRecordsBean.getExchangeStatus();
        String a3 = a(awardRecordsBean2);
        String exchangeStatus2 = awardRecordsBean2.getExchangeStatus();
        if (!a2.equals(a3)) {
            return a2.compareTo(a3);
        }
        if ("1".equals(a2) && "1".equals(a3) && !exchangeStatus.equals(exchangeStatus2)) {
            return Integer.compare(e(exchangeStatus), e(exchangeStatus2));
        }
        return Long.compare(awardRecordsBean2.getWonTime(), awardRecordsBean.getWonTime());
    }

    private void c(List<AwardRecordsBean> list) {
        Collections.sort(list, new Comparator() { // from class: brc
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MyAwardActivity.this.a((AwardRecordsBean) obj, (AwardRecordsBean) obj2);
            }
        });
    }

    public /* synthetic */ int a(AwardRecordsBean awardRecordsBean, AwardRecordsBean awardRecordsBean2) {
        String a2 = a(awardRecordsBean);
        String a3 = a(awardRecordsBean2);
        if (!a2.equals(a3)) {
            return a2.compareTo(a3);
        }
        return Long.compare(awardRecordsBean2.getWonTime(), awardRecordsBean.getWonTime());
    }

    private String a(AwardRecordsBean awardRecordsBean) {
        String awardType = awardRecordsBean.getAwardType();
        return "4".equals(awardType) ? "1" : awardType;
    }

    private int e(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 180654607) {
            if (str.equals("not_exchange")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1254744472) {
            if (hashCode == 2082219389 && str.equals("is_send")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("is_exchange")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return 1;
        }
        if (c != 1) {
            return c != 2 ? Integer.MAX_VALUE : 3;
        }
        return 2;
    }

    private void b(List<AwardRecordsBean> list) {
        if (koq.b(list)) {
            LogUtil.h("MyAwardActivity", "awardValidList is empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        List<AwardRecordsBean> d = d(arrayList);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        long b2 = SharedPreferenceManager.b(Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_award_time", 0L);
        long wonTime = d.get(0).getWonTime();
        LogUtil.a("MyAwardActivity", "beforeTime:", Long.valueOf(b2), " wonTime:", Long.valueOf(wonTime));
        if (wonTime > b2) {
            SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_award_time", wonTime);
        }
    }

    private static List<AwardRecordsBean> d(List<AwardRecordsBean> list) {
        Collections.sort(list, new Comparator() { // from class: bre
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((AwardRecordsBean) obj2).getWonTime(), ((AwardRecordsBean) obj).getWonTime());
                return compare;
            }
        });
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        AwardValidFragment awardValidFragment = this.e;
        if (awardValidFragment != null) {
            awardValidFragment.c();
        }
        AwardExpiredFragment awardExpiredFragment = this.d;
        if (awardExpiredFragment != null) {
            awardExpiredFragment.d();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
