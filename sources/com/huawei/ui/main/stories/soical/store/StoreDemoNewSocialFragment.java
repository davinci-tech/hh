package com.huawei.ui.main.stories.soical.store;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageExt;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.util.SmartRulesApi;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationWebActivityIntentBuilder;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.pluginachievement.AchieveNavigationApi;
import com.huawei.pluginmessagecenter.util.HttpResCallback;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.soical.SocialFragmentHelper;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import defpackage.ceb;
import defpackage.ixx;
import defpackage.moh;
import defpackage.nsn;
import defpackage.rxf;
import defpackage.rxh;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class StoreDemoNewSocialFragment extends Fragment implements View.OnClickListener, ScrollViewListener {
    private ImageView ab;
    private RelativeLayout af;
    private HealthScrollView ai;
    private LinearLayout aj;
    private SocialFragmentHelper ak;
    private LinearLayout al;
    private View am;
    private LinearLayout an;
    private LinearLayout ao;
    private LinearLayout ap;
    private LinearLayout aq;
    private LinearLayout ar;
    private RelativeLayout at;
    private HealthViewPager au;
    private SocialRecyclerview av;
    private HealthRecycleView aw;
    private HealthViewPager f;
    private HealthButton h;
    private Context k;
    private e l;
    private HealthDotsPageIndicator n;
    private e s;
    private rxf t;
    private View w;
    private long g = 0;
    private long r = 1000;
    private final List<MessageObject> j = new ArrayList(10);
    private final List<MessageObject> c = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private final List<MessageObject> f10496a = new ArrayList(10);
    private final List<ImageView> b = new ArrayList(10);
    private final List<MessageObject> i = new ArrayList(10);
    private final ArrayList<ImageView> d = new ArrayList<>(3);
    private HandlerThread o = null;
    private Handler as = null;
    private JSONArray p = null;
    private String y = "";
    private String x = null;
    private boolean q = false;
    private String v = null;
    private final List<ceb> ad = new ArrayList(10);
    private final List<ceb> z = new ArrayList(10);
    private SocialActRecyclerAdapter ah = null;
    private final Handler m = new BaseHandler<StoreDemoNewSocialFragment>(this) { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dTo_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(StoreDemoNewSocialFragment storeDemoNewSocialFragment, Message message) {
            storeDemoNewSocialFragment.dTn_(message);
        }
    };
    private final Runnable ag = new Runnable() { // from class: rxk
        @Override // java.lang.Runnable
        public final void run() {
            StoreDemoNewSocialFragment.this.e();
        }
    };
    private final OperationWebActivityIntentBuilderApi aa = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    private final AchieveNavigationApi e = (AchieveNavigationApi) Services.a("PluginAchievement", AchieveNavigationApi.class);
    private final MessageCenterApi u = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
    private final SmartRulesApi ae = (SmartRulesApi) Services.a("HWSmartInteractMgr", SmartRulesApi.class);
    private final OperationInteractorsApi ac = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommonUtil.u("TimeEat_StoreDemoNewSocialFragmentEnter onCreateView");
        FragmentActivity activity = getActivity();
        this.k = activity;
        this.ak = new SocialFragmentHelper(activity);
        this.am = layoutInflater.inflate(R.layout.fragment_main_circle_new, viewGroup, false);
        this.t = rxf.e(this.k);
        this.w = this.am.findViewById(R.id.fragment_social_ad);
        HealthScrollView healthScrollView = (HealthScrollView) this.am.findViewById(R.id.fragment_social_sroll);
        this.ai = healthScrollView;
        healthScrollView.setScrollViewListener(this);
        this.ap = (LinearLayout) this.am.findViewById(R.id.social_message_container);
        ((LinearLayout) this.am.findViewById(R.id.fragment_item)).setVisibility(8);
        dTm_(this.am);
        HandlerThread handlerThread = new HandlerThread("UIDV_StoreDemoNewSocialFragment");
        this.o = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.o.getLooper());
        this.as = handler;
        handler.post(new Runnable() { // from class: rxm
            @Override // java.lang.Runnable
            public final void run() {
                StoreDemoNewSocialFragment.this.b();
            }
        });
        CommonUtil.u("TimeEat_StoreDemoNewSocialFragmentLeave onCreateView");
        return this.am;
    }

    public /* synthetic */ void b() {
        o();
        m();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        CommonUtil.u("TimeEat_StoreDemoNewSocialFragmentEnter onActivityCreated");
        l();
        f();
        CommonUtil.u("TimeEat_StoreDemoNewSocialFragmentLeave onActivityCreated");
    }

    private void l() {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "Enter initView");
        this.an = (LinearLayout) this.am.findViewById(R.id.social_activity_card);
        this.aq = (LinearLayout) this.am.findViewById(R.id.social_shop_card);
        this.aj = (LinearLayout) this.am.findViewById(R.id.social_information_card);
        this.ao = (LinearLayout) this.am.findViewById(R.id.social_service_card);
        this.an.setVisibility(8);
        this.aq.setVisibility(8);
        this.aj.setVisibility(8);
        this.ao.setVisibility(8);
        this.al = (LinearLayout) this.am.findViewById(R.id.social_activity_container);
        this.ar = (LinearLayout) this.am.findViewById(R.id.social_root_lyt);
        this.at = (RelativeLayout) this.am.findViewById(R.id.social_net_work_layout);
        this.af = (RelativeLayout) this.am.findViewById(R.id.social_reload_layout);
        this.h = (HealthButton) this.am.findViewById(R.id.social_btn_no_net_work);
        this.aw = (HealthRecycleView) this.am.findViewById(R.id.social_activity_recycle_view);
        this.ah = new SocialActRecyclerAdapter(this.k, this.z);
    }

    private void f() {
        if (nsn.ag(this.k)) {
            this.aw.setLayoutManager(new GridLayoutManager(this.k, 2, 1, false));
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.k);
            linearLayoutManager.setOrientation(1);
            this.aw.setLayoutManager(linearLayoutManager);
        }
        this.aw.setAdapter(this.ah);
        this.aw.setNestedScrollingEnabled(false);
        this.aw.setHasFixedSize(true);
        this.an.setOnClickListener(this);
        this.aq.setOnClickListener(this);
        this.ao.setOnClickListener(this);
        this.aj.setOnClickListener(this);
        this.at.setOnClickListener(this);
        this.af.setOnClickListener(this);
        this.h.setOnClickListener(this);
        ((RelativeLayout) this.am.findViewById(R.id.social_activity_title)).setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) this.am.findViewById(R.id.social_activity_more_layout);
        ImageView imageView = (ImageView) this.am.findViewById(R.id.activity_more_arrow);
        imageView.setVisibility(8);
        linearLayout.setVisibility(8);
        this.ab = (ImageView) this.am.findViewById(R.id.activity_red_dot);
        if (LanguageUtil.bc(this.k)) {
            imageView.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            imageView.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.ac.setNeedUpdateActivity(this.k, false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("main_social_red_dot_friend_show");
        intentFilter.addAction("main_social_red_dot_friend_dismiss");
        intentFilter.addAction("main_social_update_ranking");
        q();
        b((List<MessageObject>) null);
    }

    private void q() {
        this.at.setVisibility(8);
        this.ar.setVisibility(0);
    }

    public void d() {
        this.m.sendEmptyMessageDelayed(12289, 2000L);
        this.as.post(new Runnable() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.1
            @Override // java.lang.Runnable
            public void run() {
                StoreDemoNewSocialFragment.this.u.refreshMessages();
                StoreDemoNewSocialFragment.this.j();
                StoreDemoNewSocialFragment.this.h();
            }
        });
    }

    private void dTm_(View view) {
        this.c.clear();
        this.f10496a.clear();
        this.b.clear();
        this.i.clear();
        this.d.clear();
        this.au = (HealthViewPager) view.findViewById(R.id.view_pager_common_top_banner);
        this.f = (HealthViewPager) view.findViewById(R.id.view_pager_common_top_banner_cant_loop);
        this.n = (HealthDotsPageIndicator) view.findViewById(R.id.indicator);
        this.l = new e(this.d);
        this.s = new e(this.d);
        this.au.setAdapter(this.l);
        this.f.setAdapter(this.s);
        this.au.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3 || action == 4) {
                    StoreDemoNewSocialFragment.this.e();
                    return false;
                }
                if (action == 0) {
                    StoreDemoNewSocialFragment.this.g();
                    return false;
                }
                LogUtil.h("UIDV_StoreDemoNewSocialFragment", "no branch!");
                return false;
            }
        });
        this.f.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3 || action == 4) {
                    StoreDemoNewSocialFragment.this.e();
                    return false;
                }
                if (action == 0) {
                    StoreDemoNewSocialFragment.this.g();
                    return false;
                }
                LogUtil.h("UIDV_StoreDemoNewSocialFragment", "no branch!");
                return false;
            }
        });
    }

    private void e(HealthViewPager healthViewPager) {
        boolean ag = nsn.ag(this.k);
        int i = i();
        int d = (i - d(360.0f)) / 2;
        int d2 = (!ag || this.d.size() <= 1) ? d : (i - (d(360.0f) * 2)) / 2;
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "resizeViewPager() Window width ", Integer.valueOf(i), " banner padding ", Integer.valueOf(d), " pageMargin ", Integer.valueOf(d2));
        healthViewPager.setPageMargin(d2);
        healthViewPager.setPadding(d, 0, d, 0);
    }

    private int i() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.k).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int d(float f) {
        return (int) ((f * this.k.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void dTj_(ImageView imageView, MessageObject messageObject) {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "addImageToShow");
        if (dTl_(imageView, messageObject)) {
            this.ak.dSH_(this.f10496a, messageObject, this.b, imageView);
            if (this.f10496a.size() >= 1 && this.f10496a.size() <= 3) {
                this.w.setVisibility(0);
                this.i.clear();
                this.i.addAll(this.f10496a);
                this.d.clear();
                this.d.addAll(this.b);
            }
            LogUtil.a("UIDV_StoreDemoNewSocialFragment", "mADImageShowList size = ", Integer.valueOf(this.d.size()));
            if (this.f10496a.size() == 1) {
                this.au.setVisibility(8);
                this.f.setVisibility(0);
                this.n.setViewPager(this.f);
                this.n.setVisibility(8);
                return;
            }
            if (this.f10496a.size() == 2) {
                this.au.setVisibility(0);
                this.f.setVisibility(8);
                this.n.setViewPager(this.au);
                this.n.setVisibility(0);
                return;
            }
            if (this.f10496a.size() == 3) {
                this.au.setVisibility(0);
                this.f.setVisibility(8);
                this.n.setViewPager(this.au);
                this.n.setVisibility(0);
                return;
            }
            LogUtil.c("UIDV_StoreDemoNewSocialFragment", "mAdImageShowList size is illegal");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View dTk_(Drawable drawable) {
        View inflate = LayoutInflater.from(this.k).inflate(R.layout.layout_social_cardview, (ViewGroup) null);
        ((ImageView) inflate.findViewById(R.id.card_image)).setImageDrawable(drawable);
        return inflate;
    }

    private boolean dTl_(ImageView imageView, MessageObject messageObject) {
        boolean z;
        if (imageView == null || messageObject == null) {
            LogUtil.a("UIDV_StoreDemoNewSocialFragment", "imageView||messageObject is null");
            z = false;
        } else {
            z = true;
        }
        ArrayList arrayList = new ArrayList(10);
        arrayList.addAll(this.c);
        boolean z2 = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) != null && messageObject != null && messageObject.getMsgId().equals(((MessageObject) arrayList.get(i)).getMsgId())) {
                z2 = true;
            }
        }
        if (z2) {
            return z;
        }
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "this ad is not in display list");
        return false;
    }

    private void b(List<MessageObject> list) {
        this.d.clear();
        this.au.removeAllViews();
        this.f.removeAllViews();
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "no Ad message in database");
        this.w.setVisibility(8);
        this.n.setVisibility(8);
    }

    public class e extends HwPagerAdapter {
        private ArrayList<ImageView> e;

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        e(ArrayList<ImageView> arrayList) {
            this.e = arrayList;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return this.e.size();
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            if (this.e.size() == 0 || this.e.size() <= i) {
                return null;
            }
            View dTk_ = StoreDemoNewSocialFragment.this.dTk_(this.e.get(i).getDrawable());
            viewGroup.addView(dTk_);
            dTk_.setOnClickListener(new View.OnClickListener() { // from class: rxp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StoreDemoNewSocialFragment.e.this.dTp_(i, view);
                }
            });
            return dTk_;
        }

        public /* synthetic */ void dTp_(int i, View view) {
            String str;
            String str2;
            String str3;
            String str4;
            LogUtil.a("UIDV_StoreDemoNewSocialFragment", "Enter gotoNotifyCard");
            if (StoreDemoNewSocialFragment.this.i == null || StoreDemoNewSocialFragment.this.i.isEmpty()) {
                SocialFragmentHelper.a(StoreDemoNewSocialFragment.this.k, "0", "0", "0");
                GRSManager.a(StoreDemoNewSocialFragment.this.k).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.e.3
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i2) {
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(String str5) {
                        StoreDemoNewSocialFragment.this.e(str5);
                    }
                });
            } else {
                if (i >= StoreDemoNewSocialFragment.this.i.size() || StoreDemoNewSocialFragment.this.i.get(i) == null) {
                    str = "";
                    str2 = "";
                    str3 = str2;
                    str4 = str3;
                } else {
                    str = ((MessageObject) StoreDemoNewSocialFragment.this.i.get(i)).getMsgId();
                    str3 = ((MessageObject) StoreDemoNewSocialFragment.this.i.get(i)).getMsgTitle();
                    str4 = ((MessageObject) StoreDemoNewSocialFragment.this.i.get(i)).getDetailUri();
                    str2 = ((MessageObject) StoreDemoNewSocialFragment.this.i.get(i)).getModule();
                }
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str4)) {
                    SocialFragmentHelper.a(StoreDemoNewSocialFragment.this.k, str, str3, str2);
                    try {
                        StoreDemoNewSocialFragment.this.k.startActivity(StoreDemoNewSocialFragment.this.aa.builder(StoreDemoNewSocialFragment.this.k, str4).setBI(str, str3, "SOCIALBANNER", "SHOW_TIME_BI").build());
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b("UIDV_StoreDemoNewSocialFragment", "ActivityNotFoundException", e.getMessage());
                    }
                } else {
                    LogUtil.a("UIDV_StoreDemoNewSocialFragment", "messageId||detailUrl is null");
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public void e() {
        ArrayList<ImageView> arrayList = this.d;
        if (arrayList == null || this.n == null) {
            return;
        }
        if (arrayList.size() > 1) {
            this.n.a(4000);
        } else {
            this.n.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        HealthDotsPageIndicator healthDotsPageIndicator = this.n;
        if (healthDotsPageIndicator != null) {
            healthDotsPageIndicator.c();
        }
    }

    private void o() {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "initOperationList");
        String b = SharedPreferenceManager.b(this.k, Integer.toString(10011), "OPERATION_SOCIAL_ACTIVITY_SAVE");
        this.x = SharedPreferenceManager.b(this.k, Integer.toString(10011), OperationInteractorsApi.OPERATION_ACTIVITY_CURRENT_TIME);
        this.y = SharedPreferenceManager.b(this.k, Integer.toString(10011), " OPERATION_ACTIVITY_MAX_ID");
        try {
            this.ad.clear();
            if (!TextUtils.isEmpty(b)) {
                JSONArray jSONArray = new JSONArray(b);
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.ad.add(this.ac.expoundOperationActivity(jSONArray.getJSONObject(i)));
                }
            }
            this.m.sendEmptyMessage(24);
        } catch (JSONException e2) {
            LogUtil.b("UIDV_StoreDemoNewSocialFragment", "1Json data error! JSONException:", e2.getMessage());
        }
        h();
    }

    private String b(String str, String str2) {
        String str3;
        InputStream e2;
        str3 = "";
        InputStream inputStream = null;
        try {
            try {
                e2 = moh.e(str, str2 + ".txt");
            } catch (IOException e3) {
                LogUtil.b("UIDV_StoreDemoNewSocialFragment", "getBetaFile e is ", e3.getMessage());
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        LogUtil.b("UIDV_StoreDemoNewSocialFragment", "getBetaFile e is ", e4.getMessage());
                    }
                }
            }
            if (e2 == null) {
                LogUtil.h("UIDV_StoreDemoNewSocialFragment", "readTXTDataFile inputStream is null.");
                if (e2 != null) {
                    try {
                        e2.close();
                    } catch (IOException e5) {
                        LogUtil.b("UIDV_StoreDemoNewSocialFragment", "getBetaFile e is ", e5.getMessage());
                    }
                }
                return "";
            }
            byte[] bArr = new byte[e2.available()];
            str3 = e2.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
            if (e2 != null) {
                try {
                    e2.close();
                } catch (IOException e6) {
                    LogUtil.b("UIDV_StoreDemoNewSocialFragment", "getBetaFile e is ", e6.getMessage());
                }
            }
            return str3;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    LogUtil.b("UIDV_StoreDemoNewSocialFragment", "getBetaFile e is ", e7.getMessage());
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "getActivityFromCloud()");
        this.m.sendEmptyMessage(12289);
        try {
            JSONObject jSONObject = new JSONObject(b("operation", "activity"));
            String string = jSONObject.getString("resultCode");
            LogUtil.a("UIDV_StoreDemoNewSocialFragment", "HttpPost result:resultCode = ", string);
            if ("0".equals(string)) {
                this.x = jSONObject.optString("currentTime");
                StorageParams storageParams = new StorageParams();
                b(OperationInteractorsApi.OPERATION_ACTIVITY_CURRENT_TIME, this.x, storageParams);
                JSONArray jSONArray = jSONObject.getJSONArray("activities");
                this.p = jSONArray;
                if (jSONArray.length() < 1) {
                    LogUtil.h("UIDV_StoreDemoNewSocialFragment", "no activity");
                    return;
                }
                this.ad.clear();
                LogUtil.a("UIDV_StoreDemoNewSocialFragment", "jsonArrayOperationActivity ", this.p);
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < this.p.length(); i++) {
                    JSONObject jSONObject2 = this.p.getJSONObject(i);
                    LogUtil.a("UIDV_StoreDemoNewSocialFragment", "jsonObjectActivity ", jSONObject2);
                    ceb expoundOperationActivity = this.ac.expoundOperationActivity(jSONObject2);
                    if (expoundOperationActivity != null && expoundOperationActivity.q() > 0 && this.ac.getActivityStatus(this.x, expoundOperationActivity.t(), expoundOperationActivity.n()) != -1) {
                        jSONArray2.put(jSONObject2);
                        this.ad.add(expoundOperationActivity);
                    }
                }
                b("OPERATION_SOCIAL_ACTIVITY_SAVE", jSONArray2.toString(), storageParams);
                this.m.sendEmptyMessage(24);
                d(storageParams);
            }
        } catch (JSONException e2) {
            LogUtil.b("UIDV_StoreDemoNewSocialFragment", "Json data error! JSONException:", e2.getMessage());
        }
    }

    private void d(StorageParams storageParams) {
        for (int i = 0; i < this.p.length(); i++) {
            try {
                JSONObject jSONObject = this.p.getJSONObject(i);
                if (((Integer) jSONObject.get("activityPosition")).intValue() == 2) {
                    b("OPERATION_ACTIVITY_STEP", jSONObject.toString(), storageParams);
                    return;
                }
            } catch (JSONException e2) {
                LogUtil.b("UIDV_StoreDemoNewSocialFragment", "Json data error! JSONException:", e2.getMessage());
                return;
            }
        }
        b("OPERATION_ACTIVITY_STEP", "", storageParams);
    }

    private void b(String str, String str2, StorageParams storageParams) {
        SharedPreferenceManager.e(this.k, Integer.toString(10011), str, str2, storageParams);
    }

    private void s() {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "updateOperationActivity()");
        if (this.ad != null) {
            this.z.clear();
            this.z.addAll(this.ad);
            if (this.z.size() <= 0) {
                this.al.setVisibility(8);
            } else {
                this.al.setVisibility(0);
                this.m.sendEmptyMessage(30);
            }
            this.ah.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "getMoreInfoUrlFromCloud()");
        this.t.e(new HttpResCallback() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.5
            @Override // com.huawei.pluginmessagecenter.util.HttpResCallback
            public void onSucceed(String str, String str2, String str3) {
                StoreDemoNewSocialFragment.this.m.sendEmptyMessage(12289);
                if (str == null || "".equals(str)) {
                    return;
                }
                StoreDemoNewSocialFragment.this.v = str;
                SharedPreferenceManager.e(StoreDemoNewSocialFragment.this.k, Integer.toString(10011), "SOCIAL_MORE_INFO_URL", StoreDemoNewSocialFragment.this.v, new StorageParams());
            }

            @Override // com.huawei.pluginmessagecenter.util.HttpResCallback
            public void onFailed(int i) {
                LogUtil.a("UIDV_StoreDemoNewSocialFragment", "getMoreInfoUrlFromCloud doPostReq onFailed ==> resCode == " + i);
            }
        });
    }

    private void m() {
        this.as.post(new Runnable() { // from class: rxn
            @Override // java.lang.Runnable
            public final void run() {
                StoreDemoNewSocialFragment.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public void a() {
        ArrayList arrayList = new ArrayList(10);
        c(arrayList);
        StringBuilder sb = new StringBuilder("{\"groupMsgId\":1259,");
        sb.append(" \"title\":\"50米防水！智能手环的功能可以多强大？！\", \"imgUrl\":\"file:///android_asset/operation/MessageH5/image/intelligent_hand's_ring.jpg\", \"jumpType\":0, \"jumpUrl\":\"file:///android_asset/operation/MessageH5/html/honorBracelet.html\", \"beginTime\":1542643201000, \"endTime\":1543569831000, \"weight\":35, \"description\":\"50米防水！智能手环的功能可以多强大？！\"}");
        String sb2 = sb.toString();
        sb.setLength(0);
        arrayList.add((MessageExt) HiJsonUtil.e(sb2, MessageExt.class));
        sb.append("{\"groupMsgId\":1260,");
        sb.append(" \"title\":\"你不得不知道的马拉松入门秘籍！\",");
        sb.append(" \"imgUrl\":\"file:///android_asset/operation/MessageH5/image/marathon.jpg\",");
        sb.append(" \"jumpType\":0,");
        sb.append(" \"jumpUrl\":\"file:///android_asset/operation/MessageH5/html/recommendIndex2.html\",");
        sb.append(" \"beginTime\":1542643201000,");
        sb.append(" \"endTime\":1543569831000,");
        sb.append(" \"weight\":35,");
        sb.append(" \"description\":\"你不得不知道的马拉松入门秘籍！\"}");
        String sb3 = sb.toString();
        sb.setLength(0);
        arrayList.add((MessageExt) HiJsonUtil.e(sb3, MessageExt.class));
        sb.append("{\"groupMsgId\":1261,");
        sb.append(" \"title\":\"最美夏夜之梦 华为运动手环助你一夜好眠\",");
        sb.append(" \"imgUrl\":\"file:///android_asset/operation/MessageH5/image/sleep.jpg\",");
        sb.append(" \"jumpType\":0,");
        sb.append(" \"jumpUrl\":\"file:///android_asset/operation/MessageH5/html/huaweiBracelet.html\",");
        sb.append(" \"beginTime\":1542643201000,");
        sb.append(" \"endTime\":1543569831000,");
        sb.append(" \"weight\":34,");
        sb.append(" \"description\":\"最美夏夜之梦 华为运动手环助你一夜好眠\"}");
        String sb4 = sb.toString();
        sb.setLength(0);
        arrayList.add((MessageExt) HiJsonUtil.e(sb4, MessageExt.class));
        this.m.obtainMessage(27, new MessageObject[]{null, d(arrayList)}).sendToTarget();
    }

    private void c(List<MessageExt> list) {
        StringBuilder sb = new StringBuilder("{\"groupMsgId\":1257,");
        sb.append(" \"title\":\"大家都想减脂，为什么他们吃得多还瘦了？\", \"imgUrl\":\"file:///android_asset/operation/MessageH5/image/banner_eat_thin.webp\", \"jumpType\":0, \"jumpUrl\":\"file:///android_asset/operation/MessageH5/html/recommendBohee.html\", \"beginTime\":1542643201000, \"endTime\":1543569831000, \"weight\":33, \"description\":\"大家都想减脂，为什么他们吃得多还瘦了？\"}");
        list.add((MessageExt) HiJsonUtil.e(sb.toString(), MessageExt.class));
        sb.setLength(0);
        sb.append("{\"groupMsgId\":1258,");
        sb.append(" \"title\":\"5分钟，4个参数，华为智能穿戴带你告别运动文盲！\",");
        sb.append(" \"imgUrl\":\"file:///android_asset/operation/MessageH5/image/huawei_wear.webp\",");
        sb.append(" \"jumpType\":0,");
        sb.append(" \"jumpUrl\":\"file:///android_asset/operation/MessageH5/html/recommendIndex1.html\",");
        sb.append(" \"beginTime\":1542643201000,");
        sb.append(" \"endTime\":1543569831000,");
        sb.append(" \"weight\":34,");
        sb.append(" \"description\":\"5分钟，4个参数，华为智能穿戴带你告别运动文盲！\"}");
        list.add((MessageExt) HiJsonUtil.e(sb.toString(), MessageExt.class));
    }

    private MessageObject d(List<MessageExt> list) {
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgId("G1257");
        messageObject.setModule("5");
        messageObject.setMsgType(1);
        messageObject.setFlag(0);
        messageObject.setWeight(1);
        messageObject.setMsgTitle("热门文章");
        messageObject.setCreateTime(1542643201000L);
        messageObject.setExpireTime(1543569831000L);
        messageObject.setReceiveTime(1542783106021L);
        messageObject.setPosition(1);
        messageObject.setMsgPosition(28);
        messageObject.setReadFlag(0);
        messageObject.setNotified(0);
        messageObject.setInfoRecommend(0);
        messageObject.setMsgUserLabel(null);
        messageObject.setLayout(4);
        messageObject.setMessageExtList(list);
        return messageObject;
    }

    private void d(MessageObject messageObject, MessageObject messageObject2) {
        this.ap.removeAllViews();
        SocialRecyclerview socialRecyclerview = new SocialRecyclerview(this.k);
        this.av = socialRecyclerview;
        socialRecyclerview.d(messageObject2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        this.ap.addView(this.av, layoutParams);
    }

    @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
    public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
        if (this.k == null || !getUserVisibleHint()) {
            return;
        }
        int scrollY = healthScrollView.getScrollY();
        int height = healthScrollView.getHeight();
        int paddingTop = healthScrollView.getPaddingTop();
        if (healthScrollView.getChildAt(0).getHeight() != ((scrollY + height) - paddingTop) - healthScrollView.getPaddingBottom() || System.currentTimeMillis() - this.r <= 1000) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("scroll", "1");
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 1);
        ixx.d().d(this.k, AnalyticsValue.HEALTH_DISCOVER_SCROLL_TO_BOTTOM_2020014.value(), hashMap, 0);
        ixx.d().c(this.k);
        this.r = System.currentTimeMillis();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a("UIDV_StoreDemoNewSocialFragment", "Enter onPause begin");
        super.onPause();
        g();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CommonUtil.u("TimeEat_StoreDemoNewSocialFragmentEnter onResume");
        this.m.removeCallbacks(this.ag);
        this.m.postDelayed(this.ag, 700L);
        boolean needUpdateActivity = this.ac.getNeedUpdateActivity(this.k);
        boolean z = this.q;
        if (z && needUpdateActivity) {
            LogUtil.c("UIDV_StoreDemoNewSocialFragment", "isHideRedDot = ", Boolean.valueOf(z));
            this.m.sendEmptyMessage(26);
        }
        if (needUpdateActivity) {
            LogUtil.a("UIDV_StoreDemoNewSocialFragment", "isNeedUpdateActivity = ", Boolean.valueOf(needUpdateActivity));
            h();
            this.ac.setNeedUpdateActivity(this.k, false);
        }
        CommonUtil.u("TimeEat_StoreDemoNewSocialFragmentLeave onResume");
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.social_activity_card) {
            k();
        } else {
            boolean z = SystemClock.elapsedRealtime() - this.g > 2000;
            if (id == R.id.social_shop_card && z) {
                t();
            } else if (id == R.id.social_information_card && z) {
                HashMap hashMap = new HashMap();
                hashMap.put("click", "1");
                ixx.d().d(this.k, AnalyticsValue.HEALTH_DISCOVER_SCROLL_INDEED_2020022.value(), hashMap, 0);
                c();
            } else if (id == R.id.social_service_card && z) {
                AppRouter.b("/OpenService/1.0/OpenServiceActivity").c(this.k);
                rxh.e(this.k, AnalyticsValue.OPEN_SERVICE_MORE_20100060.value());
            } else if (id == R.id.social_activity_title && z) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("click", "1");
                hashMap2.put("type", "1");
                hashMap2.put("from", "3");
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_ACTIVITY_1100005.value(), hashMap2, 0);
                GRSManager.a(this.k).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.7
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i) {
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(String str) {
                        LogUtil.c("UIDV_StoreDemoNewSocialFragment", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str);
                        StoreDemoNewSocialFragment.this.d(str);
                    }
                });
            } else if (id == R.id.social_btn_no_net_work && z) {
                CommonUtil.q(this.k);
                this.g = SystemClock.elapsedRealtime();
            } else if (id == R.id.social_reload_layout && z) {
                d();
                this.g = SystemClock.elapsedRealtime();
            } else {
                LogUtil.h("UIDV_StoreDemoNewSocialFragment", "no branch!");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(this.k, AnalyticsValue.HEALTH_SHOP_FEATURE_TAB_CLICK_2120017.value(), hashMap, 0);
        GRSManager.a(this.k).e("healthRecommendUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.6
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("UIDV_StoreDemoNewSocialFragment", "GRSManager onCallBackSuccess HEALTH_RECOMMEND url = ", str);
                try {
                    StoreDemoNewSocialFragment.this.k.startActivity(StoreDemoNewSocialFragment.this.aa.builder(StoreDemoNewSocialFragment.this.k, str + "/miniShop/html/homePage.html").setIntType(3001).setFlags(268435456).build());
                } catch (ActivityNotFoundException e2) {
                    LogUtil.b("UIDV_StoreDemoNewSocialFragment", "ActivityNotFoundException", e2.getMessage());
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("UIDV_StoreDemoNewSocialFragment", "GRSManager onCallBackFail HEALTH_RECOMMEND i = ", Integer.valueOf(i));
            }
        });
    }

    private void k() {
        GRSManager.a(this.k).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.StoreDemoNewSocialFragment.9
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("UIDV_StoreDemoNewSocialFragment", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str);
                try {
                    StoreDemoNewSocialFragment.this.k.startActivity(StoreDemoNewSocialFragment.this.aa.builder(StoreDemoNewSocialFragment.this.k, str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + Constants.ACTIVITY_HTML).setFlags(268435456).build());
                } catch (ActivityNotFoundException e2) {
                    LogUtil.b("UIDV_StoreDemoNewSocialFragment", "ActivityNotFoundException", e2.getMessage());
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("UIDV_StoreDemoNewSocialFragment", "GRSManager onCallBackFail ACTIVITY_KEY i = ", Integer.valueOf(i));
            }
        });
        rxh.e(this.k, AnalyticsValue.HEALTH_DISCOVER_SCROLL_ACT_2020021.value());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.m.removeCallbacks(this.ag);
        this.o.quit();
        super.onDestroy();
    }

    public void d(String str) {
        String str2 = this.y;
        if (str2 != null && !"".equals(str2) && !"-1".equals(this.y)) {
            SharedPreferenceManager.e(this.k, Integer.toString(10011), " OPERATION_ACTIVITY_MAX_ID", this.y, new StorageParams());
            LogUtil.c("UIDV_StoreDemoNewSocialFragment", "setSharedPreference：", this.y);
        }
        this.ac.setNeedUpdateActivity(this.k, true);
        this.q = true;
        OperationWebActivityIntentBuilder builder = this.aa.builder(this.k, str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + Constants.ACTIVITY_HTML);
        try {
            Context context = this.k;
            context.startActivity(builder.setBI("", context.getResources().getString(R.string._2130841426_res_0x7f020f52), "ACT_MORE", "SHOW_TIME_BI").build());
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("UIDV_StoreDemoNewSocialFragment", "openHwActivity exception", e2.getMessage());
        }
        this.g = SystemClock.elapsedRealtime();
    }

    public void e(String str) {
        this.ac.setNeedUpdateActivity(this.k, true);
        try {
            this.k.startActivity(this.aa.builder(this.k, str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + Constants.ACTIVITY_HTML).setFlags(268435456).build());
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("UIDV_StoreDemoNewSocialFragment", "openHwActivityFromAds exception", e2.getMessage());
        }
        this.g = SystemClock.elapsedRealtime();
    }

    public boolean c(JSONArray jSONArray) {
        int i;
        int i2;
        if (jSONArray == null) {
            LogUtil.h("UIDV_StoreDemoNewSocialFragment", "jsonArrayOperation is empty!");
            return false;
        }
        this.y = this.ac.getMaxActivityId(jSONArray);
        String b = SharedPreferenceManager.b(this.k, Integer.toString(10011), " OPERATION_ACTIVITY_MAX_ID");
        if (b == null || "".equals(b)) {
            return true;
        }
        String str = this.y;
        if (str != null && !"".equals(str)) {
            try {
                i = Integer.parseInt(this.y);
            } catch (NumberFormatException e2) {
                LogUtil.b("UIDV_StoreDemoNewSocialFragment", "NumberFormatException e = ", e2.getMessage());
                i = 0;
            }
            try {
                i2 = Integer.parseInt(b);
            } catch (NumberFormatException e3) {
                LogUtil.b("UIDV_StoreDemoNewSocialFragment", "NumberFormatException exception = ", e3.getMessage());
                i2 = 0;
            }
            if (i > i2) {
                LogUtil.c("UIDV_StoreDemoNewSocialFragment", "There is new activity!");
                return true;
            }
            LogUtil.c("UIDV_StoreDemoNewSocialFragment", "There is no new activity!");
        }
        return false;
    }

    public void c() {
        if (this.k != null) {
            boolean isRuleOpen = this.ae.isRuleOpen(30004, SmartRulesApi.AI_INFORMATION_MODULE);
            LogUtil.a("UIDV_StoreDemoNewSocialFragment", "openHWInformation = ", Boolean.valueOf(isRuleOpen));
            if (isRuleOpen) {
                this.v = this.ae.getRuleData(30004, SmartRulesApi.AI_INFORMATION_MODULE, SmartRulesApi.INFORMATION_MORE_URL);
            }
            try {
                Context context = this.k;
                context.startActivity(this.aa.builder(context, this.v).setStringType("RecommendInfo").setBI("", this.k.getResources().getString(R.string._2130841926_res_0x7f021146), "INFO_MORE", "SHOW_TIME_BI").build());
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("UIDV_StoreDemoNewSocialFragment", "openHwInformation exception", e2.getMessage());
            }
        } else {
            LogUtil.h("UIDV_StoreDemoNewSocialFragment", "openHWInformation mContext is null");
        }
        this.g = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dTn_(Message message) {
        int i = message.what;
        if (i == 21) {
            if (message.obj != null) {
                b((List<MessageObject>) message.obj);
            }
            this.l.notifyDataSetChanged();
            this.s.notifyDataSetChanged();
            return;
        }
        if (i == 30) {
            q();
            return;
        }
        if (i == 23) {
            SocialFragmentHelper.a aVar = (SocialFragmentHelper.a) message.obj;
            if (aVar != null) {
                dTj_(aVar.dSL_(), aVar.e());
                e(this.au);
                this.au.getAdapter().notifyDataSetChanged();
                e(this.f);
                this.f.getAdapter().notifyDataSetChanged();
                e();
                return;
            }
            return;
        }
        if (i != 24) {
            if (i == 26) {
                this.ab.setVisibility(8);
                return;
            } else {
                if (i != 27) {
                    return;
                }
                MessageObject[] messageObjectArr = (MessageObject[]) message.obj;
                d(messageObjectArr[0], messageObjectArr[1]);
                return;
            }
        }
        if (c(this.p) && !this.q) {
            this.q = false;
            LogUtil.c("UIDV_StoreDemoNewSocialFragment", "To display the red dot!");
            this.ab.setVisibility(0);
        } else {
            LogUtil.c("UIDV_StoreDemoNewSocialFragment", "Not display the red dot!");
            this.ab.setVisibility(8);
        }
        s();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
