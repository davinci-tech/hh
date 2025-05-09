package com.huawei.pluginachievement.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.CalorieExchange;
import com.huawei.pluginachievement.manager.model.GiftRecord;
import com.huawei.pluginachievement.manager.model.KakaCheckInReturnBody;
import com.huawei.pluginachievement.manager.model.KakaCheckinRecord;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.ui.AchieveKaKaActivity;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskClickListener;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskRVAdapter;
import com.huawei.pluginachievement.ui.views.KakaCheckinView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.errortip.ErrorTipBar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.view.KakaClaimAnimation;
import defpackage.bzs;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.koq;
import defpackage.mct;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mde;
import defpackage.mdf;
import defpackage.meh;
import defpackage.mer;
import defpackage.mfg;
import defpackage.mfm;
import defpackage.mht;
import defpackage.mjg;
import defpackage.mji;
import defpackage.mkg;
import defpackage.mkj;
import defpackage.mlb;
import defpackage.mlc;
import defpackage.mle;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class AchieveKaKaActivity extends BaseActivity implements View.OnClickListener, AchieveObserver, AchieveKaKaTaskClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f8377a;
    private H5ProWebView ab;
    private CustomTitleBar ad;
    private HealthTextView ae;
    private mjg af;
    private LinearLayout ag;
    private HealthSwitchButton ai;
    private LinearLayout aj;
    private LinearLayout ak;
    private KakaCheckinView al;
    private KakaClaimAnimation am;
    private HealthTextView an;
    private LinearLayout ao;
    private RelativeLayout ap;
    private LinearLayout aq;
    private LinearLayout ar;
    private ViewFlipper as;
    private ErrorTipBar at;
    private LinearLayout au;
    private LinearLayout av;
    private int aw;
    private HealthScrollView ay;
    private meh az;
    private RelativeLayout b;
    private LinearLayoutManager ba;
    private long bc;
    private AchieveKaKaTaskRVAdapter c;
    private ImageView d;
    private HealthRecycleView e;
    private HealthTextView f;
    private LinearLayout g;
    private HealthTextView h;
    private KakaCheckInReturnBody j;
    private int l;
    private HealthTextView m;
    private Context n;
    private LinearLayout o;
    private int p;
    private int q;
    private int s;
    private HealthTextView t;
    private LinearLayout v;
    private LinearLayout w;
    private ThreadPoolManager x;
    private LinearLayout y;
    private Handler z;
    private NoTitleCustomAlertDialog r = null;
    private boolean i = false;
    private ArrayList<mkg> be = new ArrayList<>(0);
    private ArrayList<mkg> bb = new ArrayList<>(0);
    private boolean u = false;
    private boolean aa = false;
    private boolean ac = true;
    private Queue<GiftRecord> ax = new LinkedList();
    private boolean ah = true;
    private int k = 0;

    /* loaded from: classes8.dex */
    static class b extends BaseHandler<AchieveKaKaActivity> {
        b(AchieveKaKaActivity achieveKaKaActivity) {
            super(achieveKaKaActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cgY_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveKaKaActivity achieveKaKaActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                achieveKaKaActivity.d(message.obj);
                achieveKaKaActivity.ai();
            }
            if (i == 5) {
                achieveKaKaActivity.a(message.obj + "");
                achieveKaKaActivity.d();
                return;
            }
            if (i == 11) {
                achieveKaKaActivity.j();
                return;
            }
            if (i == 1101) {
                achieveKaKaActivity.d();
                return;
            }
            if (i == 1104) {
                achieveKaKaActivity.a(((Integer) message.obj).intValue());
                return;
            }
            if (i == 27) {
                achieveKaKaActivity.ab();
                return;
            }
            if (i == 28) {
                achieveKaKaActivity.d(message.obj);
                return;
            }
            if (i == 5005) {
                achieveKaKaActivity.bb = achieveKaKaActivity.c.d(achieveKaKaActivity.be, achieveKaKaActivity.aw, achieveKaKaActivity.p);
                return;
            }
            if (i == 5006) {
                achieveKaKaActivity.z();
                return;
            }
            switch (i) {
                case 1000:
                    achieveKaKaActivity.a(message.obj + "");
                    LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "ERROR_TIP :", message.obj);
                    break;
                case 1001:
                    achieveKaKaActivity.i(((Integer) message.obj).intValue());
                    break;
                case 1002:
                    if (message.obj instanceof Boolean) {
                        achieveKaKaActivity.b(((Boolean) message.obj).booleanValue());
                        break;
                    }
                    break;
                default:
                    cgX_(achieveKaKaActivity, message);
                    break;
            }
        }

        private void cgX_(AchieveKaKaActivity achieveKaKaActivity, Message message) {
            int i = message.what;
            if (i != 11204) {
                switch (i) {
                    case 1106:
                        achieveKaKaActivity.z.removeMessages(1106);
                        achieveKaKaActivity.z.sendEmptyMessageDelayed(1106, 1000L);
                        break;
                    case ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED /* 1107 */:
                        achieveKaKaActivity.am();
                        break;
                    case 1108:
                        achieveKaKaActivity.af.cih_(achieveKaKaActivity);
                        break;
                    default:
                        switch (i) {
                            case 11200:
                                if (message.obj instanceof KakaCheckInReturnBody) {
                                    achieveKaKaActivity.d((KakaCheckInReturnBody) message.obj);
                                    break;
                                }
                                break;
                            case 11201:
                                if (message.obj instanceof List) {
                                    achieveKaKaActivity.d((List<KakaCheckinRecord>) message.obj);
                                    break;
                                }
                                break;
                            case MLAsrConstants.ERR_NO_NETWORK /* 11202 */:
                                LogUtil.c("PLGACHIEVE_AchieveKaKaActivity", "updateGiftsView gift list.");
                                break;
                            default:
                                LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "handlerCheckInMsg message = ", Integer.valueOf(message.what));
                                break;
                        }
                }
            }
            achieveKaKaActivity.a((AchieveKaKaActivity) message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            b(R.string._2130842060_res_0x7f0211cc);
        } else {
            p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        if (this.q > 0) {
            this.ak.setVisibility(0);
            this.an.setText(this.n.getString(R.string._2130849018_res_0x7f022cfa, String.valueOf(this.q)));
            mle.a(0, 0);
            return;
        }
        this.ak.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "mCurrentKaka:", String.valueOf(obj));
        if (obj instanceof String) {
            String str = (String) obj;
            this.s = CommonUtil.h(str);
            this.t.setText(UnitUtil.e(CommonUtil.a(str), 1, 0));
        }
    }

    private void k() {
        if (mer.e(500)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "goToLuck enter isFastClick!");
            return;
        }
        if (CommonUtil.bu()) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "isStoreDemoVersion true");
            return;
        }
        if (!mcx.d(this.n)) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "showNetworkErrorDialog kakaConvert");
            b(1001, (int) Integer.valueOf(R.string._2130842541_res_0x7f0213ad));
        } else {
            d(8);
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "goToLuck enter");
            this.af.c(this.n);
            mle.b(1, 0, this.s);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color._2131299296_res_0x7f090be0));
        setContentView(R.layout.achieve_kaka);
        aa();
        clearBackgroundDrawable();
        this.z = new b(this);
        this.n = this;
        this.x = ThreadPoolManager.d();
        this.af = new mjg(this.n, this.z);
        r();
        q();
        ae();
        ac();
        g();
        this.af.c();
        ad();
    }

    private void aa() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "isFromNotification getIntent is null");
        } else if (intent.getBooleanExtra(KakaConstants.KAKA_FROM_NOTIFICATION_KEY, false)) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("from", "3");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_KAKA_1100007.value(), hashMap, 0);
        }
    }

    private void q() {
        Typeface.create(Constants.FONT, 0);
        this.t = (HealthTextView) findViewById(R.id.cur_kaka_value);
        this.ap = (RelativeLayout) findViewById(R.id.kaka_text_layout);
        this.an = (HealthTextView) findViewById(R.id.kaka_delay_text);
        this.ak = (LinearLayout) findViewById(R.id.kaka_delay_layout);
        HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.my_kaka_scrollview);
        this.ay = healthScrollView;
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        ScrollUtil.cKx_(this.ay, getWindow().getDecorView(), 3032);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.kaka_lot_layout);
        linearLayout.setOnClickListener(this);
        findViewById(R.id.kaka_exchange_layout).setOnClickListener(this);
        this.ap.setOnClickListener(this);
        this.t.setTypeface(Typeface.createFromAsset(this.n.getAssets(), "font/HarmonyOS_Sans_Black_Italic.ttf"));
        this.t.setOnClickListener(this);
        this.g = (LinearLayout) findViewById(R.id.center_card_layout);
        this.aj = (LinearLayout) findViewById(R.id.kaka_main_layout);
        af();
        this.ag = (LinearLayout) findViewById(R.id.kaka_checkin);
        this.w = (LinearLayout) findViewById(R.id.gifts_layout);
        if (CommonUtil.bu() || LoginInit.getInstance(this.n).isKidAccount()) {
            linearLayout.setVisibility(8);
        }
        this.e = (HealthRecycleView) mfm.cgL_(this, R.id.achieve_task_kaka_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.ba = linearLayoutManager;
        this.e.setLayoutManager(linearLayoutManager);
        this.e.setIsScroll(false);
        AchieveKaKaTaskRVAdapter achieveKaKaTaskRVAdapter = new AchieveKaKaTaskRVAdapter(this, this.be);
        this.c = achieveKaKaTaskRVAdapter;
        achieveKaKaTaskRVAdapter.e(this);
        this.e.setAdapter(this.c);
        t();
        this.at = (ErrorTipBar) findViewById(R.id.network_error_bar);
        d(8);
        v();
    }

    private void d(int i) {
        this.at.setVisibility(i);
        if (this.aj == null) {
            this.aj = (LinearLayout) findViewById(R.id.kaka_main_layout);
        }
        ViewGroup.LayoutParams layoutParams = this.aj.getLayoutParams();
        int r = nsn.r(this.n) + nsn.c(this.n, 56.0f);
        if (i == 0) {
            r += nsn.c(this.n, 48.0f);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            int i2 = this.l;
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(i2, r, i2, 0);
        }
        this.aj.setLayoutParams(layoutParams);
    }

    private void t() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.kaka_titlebar);
        this.ad = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        this.ad.setRightButtonDrawable(ContextCompat.getDrawable(this.n, R.mipmap._2131820551_res_0x7f110007), nsf.h(R.string._2130840678_res_0x7f020c66));
        this.ad.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(AchieveKaKaActivity.this.n, PersonalData.CLASS_NAME_PERSONAL_KAKA_DETAIL);
                Bundle bundle = new Bundle();
                bundle.putString("tag", "rule");
                intent.putExtra("tag", bundle);
                intent.putExtra("totalNum", AchieveKaKaActivity.this.s);
                jdw.bGh_(intent, AchieveKaKaActivity.this.n);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ad.setTitleText(BaseApplication.getContext().getResources().getString(R.string._2130840677_res_0x7f020c65));
        this.ad.setTitleTextColor(ContextCompat.getColor(this.n, R.color._2131297372_res_0x7f09045c));
        this.ad.setLeftButtonDrawable(ContextCompat.getDrawable(this.n, R.mipmap._2131820545_res_0x7f110001), nsf.h(R.string._2130841378_res_0x7f020f22));
        this.ad.setRightSoftkeyVisibility(0);
        this.ad.setRightSoftkeyBackground(ContextCompat.getDrawable(this.n, R.mipmap._2131820547_res_0x7f110003), nsf.h(R.string._2130838139_res_0x7f02027b));
        this.ad.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!mer.e(500)) {
                    AchieveKaKaActivity.this.af.a();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "openMyAwardPage enter isFastClick!");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.ad.setTitleBarBackgroundColor(ContextCompat.getColor(this.n, R.color._2131299296_res_0x7f090be0));
        if (CommonUtil.bf()) {
            return;
        }
        this.ad.setRightThirdKeyVisibility(0);
        this.ad.setRightThirdKeyBackground(ContextCompat.getDrawable(this.n, R.mipmap._2131821345_res_0x7f110321), nsf.h(R.string._2130840820_res_0x7f020cf4));
        this.ad.setRightThirdKeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!mcx.d(AchieveKaKaActivity.this.n)) {
                    AchieveKaKaActivity.this.b(1001, (int) Integer.valueOf(R.string._2130842541_res_0x7f0213ad));
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    AchieveKaKaActivity.this.m();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void r() {
        this.ae = (HealthTextView) findViewById(R.id.kaka_checkin_days);
        this.ai = (HealthSwitchButton) findViewById(R.id.kaka_checkin_reminder_switch);
        this.al = (KakaCheckinView) findViewById(R.id.kaka_checkin_layout);
        this.y = (LinearLayout) findViewById(R.id.kaka_to_prizes_more);
        this.v = (LinearLayout) findViewById(R.id.gift_layout);
        this.au = (LinearLayout) findViewById(R.id.no_gift_layout);
        this.as = (ViewFlipper) findViewById(R.id.message_layout);
        this.ao = (LinearLayout) View.inflate(this.n, R.layout.achieve_kaka_msg_item, null);
        this.aq = (LinearLayout) View.inflate(this.n, R.layout.achieve_kaka_msg_item, null);
        this.as.addView(this.ao);
        this.as.addView(this.aq);
        this.av = this.ao;
        this.y.setOnClickListener(this);
        this.af.c(this.ai);
    }

    private int f() {
        int[] iArr = new int[2];
        LinearLayout linearLayout = this.g;
        if (linearLayout != null) {
            linearLayout.getLocationOnScreen(iArr);
        }
        return iArr[1];
    }

    private void x() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.kaka_checkin_success_dialog);
        if (viewStub == null) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "initCheckInSeccessLayout ViewStub is loaded fail.");
            return;
        }
        this.o = (LinearLayout) viewStub.inflate();
        this.h = (HealthTextView) findViewById(R.id.kaka_checkin_result_add);
        this.m = (HealthTextView) findViewById(R.id.kaka_checkin_result_days);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.close_dialog_btn);
        this.f = healthTextView;
        healthTextView.setOnClickListener(this);
    }

    private void y() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.achieve_kk_task_reload_layout);
        if (viewStub == null) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "initNetworkReloadLayout ViewStub is loaded fail.");
            return;
        }
        this.b = (RelativeLayout) viewStub.inflate();
        ImageView imageView = (ImageView) mfm.cgL_(this, R.id.achieve_kk_task_img_no_net_work);
        this.d = imageView;
        imageView.setOnClickListener(this);
        HealthButton healthButton = (HealthButton) mfm.cgL_(this, R.id.btn_no_net_work);
        this.f8377a = healthButton;
        healthButton.setOnClickListener(this);
    }

    private void u() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.kaka_convert_anim_layout);
        if (viewStub == null) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "initConvertAnimLayout ViewStub is loaded fail.");
        } else {
            this.am = (KakaClaimAnimation) viewStub.inflate();
        }
    }

    private void v() {
        ImageView imageView = (ImageView) findViewById(R.id.top_bg_view);
        if (nrt.a(this.n)) {
            imageView.setImageAlpha(200);
        }
        if (nsn.ag(this.n)) {
            int h = nsn.h(this) + nsn.c(this, 30.0f);
            int h2 = (nsn.h(this) * 924) / 1080;
            Bitmap cJJ_ = nrf.cJJ_(nrf.cHF_(ResourcesCompat.getDrawable(getResources(), R.mipmap._2131821335_res_0x7f110317, null)), h, h2);
            int min = Math.min(nsn.h(this), cJJ_.getWidth());
            imageView.setImageBitmap(nrf.cHv_(cJJ_, min, Math.min(h2, (min * h2) / h), 2));
        }
    }

    private void ac() {
        if (LanguageUtil.bc(this.n)) {
            this.ao.findViewById(R.id.kaka_message_icon).setBackground(nrz.cKn_(this.n, R.mipmap._2131820552_res_0x7f110008));
            this.aq.findViewById(R.id.kaka_message_icon).setBackground(nrz.cKn_(this.n, R.mipmap._2131820552_res_0x7f110008));
            ((ImageView) findViewById(R.id.kaka_gift_more)).setImageDrawable(nrz.cKn_(this.n, R.mipmap._2131820550_res_0x7f110006));
            this.ad.setLeftButtonDrawable(ContextCompat.getDrawable(this.n, R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
            return;
        }
        this.ad.setLeftButtonDrawable(ContextCompat.getDrawable(this.n, R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
    }

    private void af() {
        LinearLayout linearLayout = this.aj;
        if (linearLayout == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "setCenterCardLayout() mCenterCardLayout is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        int i = 0;
        if (nsn.ag(this.n)) {
            i = (int) (r1.c() + new HealthColumnSystem(this.n, 0).g() + r1.a());
        }
        if (layoutParams2 != null) {
            this.l = i;
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            layoutParams2.topMargin = nsn.r(this.n) + nsn.c(this.n, 56.0f);
            this.aj.setLayoutParams(layoutParams2);
        }
    }

    private void ae() {
        final float f = nrt.a(BaseApplication.getContext()) ? 0.8f : 1.0f;
        this.ay.setScrollViewListener(new ScrollViewListener() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.6
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
                AchieveKaKaActivity.this.a(healthScrollView);
                if (i2 <= 0) {
                    CustomTitleBar customTitleBar = AchieveKaKaActivity.this.ad;
                    float f2 = f;
                    customTitleBar.setBackgroundColor(Color.argb(0, (int) (255.0f * f2), (int) (159.0f * f2), (int) (f2 * 75.0f)));
                } else if (i2 <= 0 || i2 > 100) {
                    CustomTitleBar customTitleBar2 = AchieveKaKaActivity.this.ad;
                    float f3 = f;
                    customTitleBar2.setBackgroundColor(Color.argb(255, (int) (255.0f * f3), (int) (159.0f * f3), (int) (f3 * 75.0f)));
                } else {
                    CustomTitleBar customTitleBar3 = AchieveKaKaActivity.this.ad;
                    float f4 = f;
                    customTitleBar3.setBackgroundColor(Color.argb((int) ((i2 / 100.0f) * 255.0f), (int) (255.0f * f4), (int) (159.0f * f4), (int) (f4 * 75.0f)));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthScrollView healthScrollView) {
        if (healthScrollView.getChildAt(healthScrollView.getChildCount() - 1).getBottom() - (healthScrollView.getHeight() + healthScrollView.getScrollY()) != 0 || mer.e(1000)) {
            return;
        }
        mle.b(10, 0, this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        String b2 = mct.b(this.n, "kakaCheckedDays");
        e(TextUtils.isEmpty(b2) ? 0 : Integer.parseInt(b2));
        boolean z = !mcx.d(mct.b(this.n, "kakaLastCheckInTime"));
        this.ah = z;
        if (z || "".equals(h())) {
            this.af.b();
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "today has checked in");
        }
    }

    private String h() {
        return mct.b(this.n, "kakaLastCheckInTime");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.af == null) {
            this.af = new mjg(this.n, this.z);
        }
        if (this.x == null) {
            this.x = ThreadPoolManager.d();
        }
        this.x.execute(new Runnable() { // from class: mhy
            @Override // java.lang.Runnable
            public final void run() {
                AchieveKaKaActivity.this.b();
            }
        });
        d(8);
        if (!mcx.d(this)) {
            ah();
        } else {
            s();
            w();
            mer.b(this.n).h();
            this.af.d();
            this.x.execute(new Runnable() { // from class: mic
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveKaKaActivity.this.c();
                }
            });
        }
        if (CommonUtil.bu()) {
            RelativeLayout relativeLayout = this.b;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(8);
            }
            this.e.setVisibility(8);
        }
    }

    public /* synthetic */ void b() {
        al();
        an();
        if (this.ac) {
            o();
            this.ac = false;
        }
    }

    public /* synthetic */ void c() {
        a();
        i();
        j();
    }

    private void an() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            return;
        }
        marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(this.n).setPageId(290).build());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.z.removeMessages(1106);
        this.z.removeMessages(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED);
        this.ax.clear();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onConfigurationChanged enter");
        af();
    }

    private void ah() {
        if (this.b == null) {
            y();
        }
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        this.ag.setVisibility(8);
        this.w.setVisibility(8);
        this.as.setVisibility(8);
        this.e.setVisibility(8);
    }

    private void s() {
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        this.ag.setVisibility(0);
        this.e.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "freshKakaTask");
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(0));
        meh mehVar = this.az;
        if (mehVar != null) {
            List<mcz> b2 = mehVar.b(12, hashMap);
            if (b2 != null) {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "freshKakaTask kakas size = ", Integer.valueOf(b2.size()));
                ArrayList arrayList = new ArrayList(b2.size());
                mdf mdfVar = null;
                boolean z = true;
                for (mcz mczVar : b2) {
                    if (mczVar instanceof mdf) {
                        mdf mdfVar2 = (mdf) mczVar;
                        int e2 = nsn.e(mdfVar2.p());
                        if (mdfVar2.ag() == 10004 && e2 < 1) {
                            z = false;
                        }
                        if (mdfVar2.ag() == 30008) {
                            mdfVar = mdfVar2;
                        } else if (mdfVar2.ag() == 20007) {
                            mer.b(BaseApplication.getContext()).c(mdfVar2);
                        } else {
                            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "unknown task rule");
                        }
                        arrayList.add(mdfVar2);
                    }
                }
                if (!z) {
                    LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "freshKakaTask: isFirstBindFinished = false");
                    mct.b(this.n, "isFirstBindDeviceFinishedV2", "");
                    if (mdfVar != null) {
                        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "freshKakaTask: remove normalBindTask");
                        arrayList.remove(mdfVar);
                    }
                }
                this.be = mji.c((List<mdf>) arrayList);
            } else {
                LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "freshKakaTask kakas == null");
                b(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, (int) null);
                return;
            }
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "mService is null");
        }
        b(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, (int) null);
    }

    private void g() {
        this.az = meh.c(getApplicationContext());
        mer.b(this.n).b();
        this.u = true;
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "getData()");
        this.az.b((AchieveObserver) this);
        this.x.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.9
            @Override // java.lang.Runnable
            public void run() {
                if (AchieveKaKaActivity.this.az != null) {
                    mcz d = AchieveKaKaActivity.this.az.d(5, new HashMap(2));
                    if (d instanceof AchieveInfo) {
                        AchieveInfo achieveInfo = (AchieveInfo) d;
                        AchieveKaKaActivity.this.bc = achieveInfo.getSyncTimestamp();
                        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "getData mSyncTimestamp=", Long.valueOf(AchieveKaKaActivity.this.bc));
                        AchieveKaKaActivity.this.b(0, (int) String.valueOf(achieveInfo.getUserPoint()));
                    }
                }
                mle.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void b(int i, T t) {
        Handler handler = this.z;
        if (handler == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "sendHandlerMessage handler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        handler.sendMessage(obtain);
    }

    private void o() {
        if (CommonUtil.bu()) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "gotoCalorieExchange isStoreDemoVersion calorie_not_enough.");
            return;
        }
        if (!mcx.d(this.n)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert Network is available");
            return;
        }
        int n = n();
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert kakaValue ", Integer.valueOf(n));
        if (n >= 10) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert Today convert max!");
        } else {
            c(n);
            mle.b(0, 0, l());
        }
    }

    private int l() {
        int i = this.s;
        if (i != 0) {
            return i;
        }
        String b2 = mct.b(BaseApplication.getContext(), "_personalCenterStatus");
        if (!TextUtils.isEmpty(b2)) {
            ArrayList<String> c = mlc.c(b2);
            if (c.size() > 0) {
                int b3 = mfg.b(c.get(0));
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "getPersonalTotalKaka num = ", Integer.valueOf(b3));
                return b3;
            }
        }
        return this.s;
    }

    private int n() {
        String b2 = mct.b(this.n, "_achieve_exchange_datastr");
        if (!String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10).equals(b2)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert dataStr is kua tian. ", b2);
            return 0;
        }
        return mht.b(mct.b(this.n, "_achieve_exchange_num"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (nsn.o()) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.n, PersonalData.CLASS_NAME_PERSONAL_KAKA_EXCHANGE_POINT);
        this.n.startActivity(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.kaka_lot_layout == id) {
            k();
        } else if (R.id.achieve_kk_task_img_no_net_work == id) {
            ag();
        } else if (R.id.btn_no_net_work == id) {
            CommonUtil.q(this.n);
        } else if (R.id.kaka_text_layout == id || R.id.cur_kaka_value == id) {
            Intent intent = new Intent();
            intent.setClassName(this.n, PersonalData.CLASS_NAME_PERSONAL_KAKA_DETAIL);
            Bundle bundle = new Bundle();
            bundle.putString("tag", JsbMapKeyNames.H5_TEXT_DETAIL);
            intent.putExtra("tag", bundle);
            intent.putExtra("from", 0);
            intent.putExtra("totalNum", this.s);
            jdw.bGh_(intent, this.n);
        } else if (R.id.kaka_exchange_layout == id) {
            if (this.ar == null) {
                this.ar = (LinearLayout) findViewById(R.id.achieve_kaka_mall);
            }
            int[] iArr = new int[2];
            this.ar.getLocationOnScreen(iArr);
            int f = f();
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", String.format("kaka scrollToH5Mall: %s, %s", Integer.valueOf(iArr[1]), Integer.valueOf(f)));
            this.ay.smoothScrollTo(0, iArr[1] - f);
            mle.b(8, 0, this.s);
        } else if (R.id.close_dialog_btn == id) {
            this.o.setVisibility(8);
            KakaCheckInReturnBody kakaCheckInReturnBody = this.j;
            if (kakaCheckInReturnBody != null && this.s < kakaCheckInReturnBody.getKakaSum()) {
                d(this.s, this.j.getKakaSum());
            }
            this.ah = false;
            this.af.a(this.ai);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onClick id:", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ag() {
        if (mcx.d(this)) {
            s();
            if (this.x == null) {
                this.x = ThreadPoolManager.d();
            }
            this.x.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    AchieveKaKaActivity.this.j();
                    AchieveKaKaActivity.this.i();
                    AchieveKaKaActivity.this.w();
                    if (!AchieveKaKaActivity.this.ah) {
                        AchieveKaKaActivity.this.af.c();
                    }
                    AchieveKaKaActivity.this.af.d();
                }
            });
        }
    }

    private void c(int i) {
        PluginAchieveAdapter adapter = mcv.d(this.n).getAdapter();
        if (adapter != null) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "enter kakaConvert");
            adapter.getTotalCalorie(new e(i), this.n);
        }
        e();
    }

    /* loaded from: classes8.dex */
    static class e implements AchieveCallback {

        /* renamed from: a, reason: collision with root package name */
        private int f8381a;

        e(int i) {
            this.f8381a = i;
        }

        @Override // com.huawei.pluginachievement.impl.AchieveCallback
        public void onResponse(int i, Object obj) {
            if (obj == null) {
                LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert called by counterpartAdapter,but obj == null!");
                return;
            }
            int l = mlb.l(String.valueOf(obj));
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert curCalorie ", Integer.valueOf(l));
            c(l, this.f8381a);
        }

        private void c(int i, int i2) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert()");
            HashMap hashMap = new HashMap(2);
            int e = e(i);
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert CalorieCard ", Integer.valueOf(e), " kakaValue ", Integer.valueOf(i2));
            if ((e / 1000) / 50 <= i2) {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert not enough");
                return;
            }
            String valueOf = String.valueOf(e);
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert strCalorieValue ", valueOf);
            hashMap.put("calorie", valueOf);
            meh.c(BaseApplication.getContext()).a(5, hashMap);
        }

        private int e(int i) {
            return ((i + 500) / 1000) * 1000;
        }
    }

    private void e() {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "enter kakaMsgId");
        PluginAchieveAdapter adapter = mcv.d(this.n).getAdapter();
        if (adapter != null) {
            String b2 = mct.b(this, "_achieve_msg_id_kaka");
            if (TextUtils.isEmpty(b2)) {
                return;
            }
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "deleteMessage kakaMsgId=", b2);
            adapter.deleteMessage(b2);
            a("_achieve_flag", "flag");
            a("_achieve_calorie", (String) null);
            a("_achieve_msg_id_kaka", (String) null);
        }
    }

    private void a(String str, String str2) {
        mct.b(this.n, str, str2);
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onDataChanged error=", Integer.valueOf(i));
        if (i == -1) {
            if (userAchieveWrapper != null && userAchieveWrapper.getContentType() == 12) {
                this.aa = true;
            }
            if (CommonUtil.bu() || !mle.a(userAchieveWrapper)) {
                return;
            }
            b(1002, (int) 1);
            return;
        }
        b(1002, (int) 0);
        this.aa = false;
        if (userAchieveWrapper == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onDataChanged resultCode=", userAchieveWrapper.getResultCode(), " type= ", Integer.valueOf(userAchieveWrapper.getContentType()));
        if (!"0".equals(userAchieveWrapper.getResultCode())) {
            a(userAchieveWrapper);
            return;
        }
        if (userAchieveWrapper.getContentType() == 5) {
            b(userAchieveWrapper);
            return;
        }
        if (userAchieveWrapper.getContentType() == 0) {
            AchieveInfo achieveInfo = userAchieveWrapper.getAchieveInfo();
            if (achieveInfo != null) {
                this.bc = achieveInfo.getSyncTimestamp();
                this.q = achieveInfo.getExpirationKaka();
                b(0, (int) String.valueOf(achieveInfo.getUserPoint()));
                return;
            }
            return;
        }
        if (userAchieveWrapper.getContentType() == 12) {
            d(userAchieveWrapper);
            return;
        }
        if (userAchieveWrapper.getContentType() == 11) {
            b(11, (int) null);
            meh mehVar = this.az;
            if (mehVar == null || !this.u) {
                return;
            }
            this.u = false;
            mehVar.b((CountDownLatch) null);
            this.az.e((CountDownLatch) null);
            return;
        }
        if (userAchieveWrapper.getContentType() == 27) {
            b(27, (int) null);
        } else if (userAchieveWrapper.getContentType() == 28) {
            b(28, (int) String.valueOf(i));
        } else {
            LogUtil.c("PLGACHIEVE_AchieveKaKaActivity", "onDataChanged content type:", Integer.valueOf(userAchieveWrapper.getContentType()));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(UserAchieveWrapper userAchieveWrapper) {
        char c;
        String string;
        String resultCode = userAchieveWrapper.getResultCode();
        resultCode.hashCode();
        switch (resultCode.hashCode()) {
            case -1948319161:
                if (resultCode.equals(ResultCode.CODE_NOT_VIP_NOW)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1507426:
                if (resultCode.equals(ResultCode.ERROR_TS_TIMEOUT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 46730162:
                if (resultCode.equals(ResultCode.CODE_UNKNOWN_ERROR)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 46730166:
                if (resultCode.equals(ResultCode.CODE_AUTH_FAIL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 46730196:
                if (resultCode.equals("10014")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 50424247:
                if (resultCode.equals("50002")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 50424248:
                if (resultCode.equals(ResultCode.CODE_NOT_SYNC)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 50424276:
                if (resultCode.equals(ResultCode.CODE_CALORIE_NOT_ENOUGH)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 50424277:
                if (resultCode.equals(ResultCode.CODE_EXCHANGE_UPPER_LIMIT)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 50424278:
                if (resultCode.equals(ResultCode.CODE_CALORIE_USER_NOT_EXSIST)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                string = getString(R.string._2130841078_res_0x7f020df6);
                break;
            case 1:
                b(1108, (int) "");
                string = null;
                break;
            case 2:
                string = getString(R.string._2130840720_res_0x7f020c90);
                break;
            case 3:
                string = getString(R.string._2130840721_res_0x7f020c91);
                break;
            case 4:
                e(userAchieveWrapper);
                string = null;
                break;
            case 5:
                string = getString(R.string._2130840722_res_0x7f020c92);
                break;
            case 6:
                string = getString(R.string._2130840723_res_0x7f020c93);
                break;
            case 7:
                LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "dealKakaExchangeError CODE_CALORIE_NOT_ENOUGH msg: ", resultCode);
                string = null;
                break;
            case '\b':
                aj();
                string = getString(R.string._2130840725_res_0x7f020c95);
                break;
            case '\t':
                string = getString(R.string._2130840726_res_0x7f020c96);
                break;
            default:
                LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "unknow error:", resultCode);
                string = null;
                break;
        }
        if (string != null && userAchieveWrapper.getContentType() != 10) {
            b(1000, (int) string);
        }
        if (userAchieveWrapper.getContentType() == 5) {
            b(1101, (int) null);
        }
    }

    private void b(UserAchieveWrapper userAchieveWrapper) {
        CalorieExchange exchange = userAchieveWrapper.getExchange();
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert cloud exchangeKaka.");
        if (exchange != null) {
            b(5, (int) getResources().getQuantityString(R.plurals._2130903166_res_0x7f03007e, exchange.getExchangeKakaNum(), Integer.valueOf(exchange.getExchangeKakaNum())));
            if (!this.ah) {
                b(ExceptionCode.CHECK_FILE_HASH_FAILED, (int) Integer.valueOf(exchange.getExchangeKakaNum()));
            }
            String b2 = mct.b(this, "_achieve_exchange_datastr");
            String substring = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10);
            int exchangeKakaNum = exchange.getExchangeKakaNum();
            if (!substring.equals(b2)) {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert cloud new exchange =", Integer.valueOf(exchangeKakaNum));
                a("_achieve_exchange_datastr", substring);
                a("_achieve_exchange_num", String.valueOf(exchangeKakaNum));
            } else {
                String b3 = mct.b(this, "_achieve_exchange_num");
                int b4 = (TextUtils.isEmpty(b3) ? 0 : mht.b(b3)) + exchangeKakaNum;
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kakaConvert cloud exchange sum =", Integer.valueOf(b4));
                a("_achieve_exchange_num", String.valueOf(b4));
            }
        }
    }

    private void aj() {
        a("_achieve_exchange_datastr", String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10));
        a("_achieve_exchange_num", String.valueOf(10));
    }

    private void d(UserAchieveWrapper userAchieveWrapper) {
        final mde acquireKakaUpdateReturnBody = userAchieveWrapper.acquireKakaUpdateReturnBody();
        if (acquireKakaUpdateReturnBody != null) {
            int c = acquireKakaUpdateReturnBody.c();
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onDataChange UPDATE_TASK_STATUS RewardKaka=", Integer.valueOf(c));
            if (c > 0) {
                b(ExceptionCode.CHECK_FILE_HASH_FAILED, (int) Integer.valueOf(c));
                final int b2 = acquireKakaUpdateReturnBody.b();
                b(0, (int) String.valueOf(b2));
                this.x.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.8
                    @Override // java.lang.Runnable
                    public void run() {
                        String e2 = acquireKakaUpdateReturnBody.e();
                        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onDataChange UPDATE_TASK_STATUS taskIdTemp =", e2);
                        HashMap hashMap = new HashMap(2);
                        hashMap.put("taskId", e2);
                        mcz d = meh.c(AchieveKaKaActivity.this.n.getApplicationContext()).d(12, hashMap);
                        if (d instanceof mdf) {
                            mdf mdfVar = (mdf) d;
                            mdfVar.h(0);
                            mer.b(AchieveKaKaActivity.this.n).d(mdfVar, 2);
                            AchieveKaKaActivity.this.j();
                            mer.b(AchieveKaKaActivity.this.n.getApplicationContext()).b(b2);
                            if (mdfVar.ag() == 30008) {
                                AchieveKaKaActivity.this.i();
                            }
                        }
                    }
                });
                return;
            }
            j();
        }
    }

    private void e(final UserAchieveWrapper userAchieveWrapper) {
        if (this.i) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.10
            @Override // java.lang.Runnable
            public void run() {
                AchieveKaKaActivity.this.c(userAchieveWrapper);
                AchieveKaKaActivity.this.i();
                AchieveKaKaActivity.this.i = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(UserAchieveWrapper userAchieveWrapper) {
        mde acquireKakaUpdateReturnBody = userAchieveWrapper.acquireKakaUpdateReturnBody();
        if (acquireKakaUpdateReturnBody != null) {
            String e2 = acquireKakaUpdateReturnBody.e();
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "retryToUpload=", e2);
            if (TextUtils.isEmpty(e2)) {
                return;
            }
            HashMap hashMap = new HashMap(2);
            hashMap.put("taskId", e2);
            mcz d = meh.c(this.n.getApplicationContext()).d(12, hashMap);
            mdf mdfVar = d instanceof mdf ? (mdf) d : null;
            if (mdfVar == null) {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "retryToUpload kakaTaskInfo is null");
            } else if (mdfVar.ag() == 30008) {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "retryToUpload taskRule = ", Integer.valueOf(mdfVar.ag()));
                c(e2);
            } else {
                mer.b(this.n.getApplicationContext()).a(mdfVar, 1);
            }
        }
    }

    private void c(String str) {
        PluginAchieveAdapter adapter = mcv.d(this.n).getAdapter();
        String b2 = mct.b(this.n, "bindDeviceProductId");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "refreshBindDeviceTaskStatusInCloud binderDeviceInfo is empty");
            return;
        }
        String[] split = b2.split(",");
        JSONArray jSONArray = new JSONArray();
        String d = HiDateUtil.d((String) null);
        try {
            for (String str2 : split) {
                if (TextUtils.isEmpty(str2)) {
                    LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "refreshBindDeviceTaskStatusInCloud secret is empty");
                } else {
                    String decryptData = adapter.decryptData(str2, this.n);
                    LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "refreshBindDeviceTaskStatusInCloud secretDec == ", decryptData);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ParsedFieldTag.KAKA_TASK_UUID, str);
                    jSONObject.put("status", 1);
                    jSONObject.put("productId", decryptData);
                    jSONObject.put("timeZone", d);
                    jSONArray.put(jSONObject);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("timeZone", d);
            jSONObject2.put(ParsedFieldTag.KAKA_TASKS, jSONArray);
            jSONObject2.put(ParsedFieldTag.KAKA_TASK_SCENARIO, 0);
            meh mehVar = this.az;
            if (mehVar != null) {
                mehVar.c(12, jSONObject2);
            }
        } catch (JSONException e2) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "JSONException", e2.getMessage());
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        meh mehVar = this.az;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        if (this.n == null || isFinishing() || isDestroyed()) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "activity is finish");
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        meh mehVar = this.az;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        Handler handler = this.z;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.z = null;
        }
        this.am = null;
        mjg mjgVar = this.af;
        if (mjgVar != null) {
            mjgVar.e();
        }
        ViewFlipper viewFlipper = this.as;
        if (viewFlipper != null) {
            viewFlipper.removeAllViews();
            this.as = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (this.am == null) {
            u();
        }
        KakaClaimAnimation kakaClaimAnimation = this.am;
        if (kakaClaimAnimation != null) {
            kakaClaimAnimation.e(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.az == null) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "doRefresh() mService == null");
        } else {
            a();
        }
    }

    private void a() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("timestamp", String.valueOf(this.bc));
        hashMap.put("countryCode", LoginInit.getInstance(this.n).getAccountInfo(1010));
        meh mehVar = this.az;
        if (mehVar != null) {
            mehVar.a(0, hashMap);
        }
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "doRefreshAchieve() mSyncTimestamp=", Long.valueOf(this.bc));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        HashMap hashMap = new HashMap(2);
        meh mehVar = this.az;
        if (mehVar != null) {
            mehVar.a(11, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Toast.makeText(BaseApplication.getContext(), str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "kaka scrollToTask()");
        this.ay.smoothScrollTo(0, 500);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i) {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "showNetworkErrorDialog()");
        if (this.at == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "mNetworkErrorTipBar is null");
            return;
        }
        a(getString(i));
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "achieveKakaTaskReloadLayout is show");
            return;
        }
        this.at.setLeadBtnText(R.string._2130842065_res_0x7f0211d1);
        this.at.setTipText(i);
        this.at.setOnClicked(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonUtil.q(AchieveKaKaActivity.this.n);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (this.at.getVisibility() != 0) {
            d(0);
        }
    }

    @Override // com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskClickListener
    public void onTaskClick(String str, String str2, int i) {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "onTaskClick code =", str, " taskId =", str2, "position =", Integer.valueOf(i));
        if ("4".equals(str)) {
            c(str2, i);
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", "1");
        hashMap.put("clickType", str);
        String value = AnalyticsValue.KAKA_TASK_1100021.value();
        if (koq.b(this.bb, i)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "onTaskClick position is out of taskRecyclerViewDatas bounds,return");
            return;
        }
        mkj b2 = this.bb.get(i).b();
        if (b2 == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "onTaskClick achieveKaKaTask is null");
            return;
        }
        hashMap.put("taskID", Integer.valueOf(b2.n()));
        hashMap.put(ParsedFieldTag.TASK_NAME, b2.a());
        if (b2.k() == 0) {
            hashMap.put("isVip", "false");
        } else {
            hashMap.put("isVip", "true");
        }
        if ("0".equals(str)) {
            mer.b(this.n).b(b2, this.n);
            ixx.d().d(this.n, value, hashMap, 0);
            return;
        }
        if ("1".equals(str)) {
            if (!mcx.d(this)) {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "showNetworkErrorDialog kakaConvert");
                b(1001, (int) Integer.valueOf(R.string._2130842541_res_0x7f0213ad));
                return;
            }
            mdf mdfVar = new mdf();
            mdfVar.e(str2);
            mdfVar.n(0);
            mer.b(this.n).a(mdfVar, 2);
            ixx.d().d(this.n, value, hashMap, 0);
            return;
        }
        if ("3".equals(str)) {
            c(b2, i);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "task code:", str);
        }
    }

    private void c(String str, int i) {
        if ("0".equals(str)) {
            this.aw = i;
        } else if ("1".equals(str)) {
            this.p = i;
            mle.b(9, 0, this.s);
        } else {
            this.aw = 0;
            this.p = 0;
        }
        this.bb = this.c.d(this.be, this.aw, this.p);
    }

    private void c(final mkj mkjVar, int i) {
        String string;
        if (koq.b(this.bb, i)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "dealTaskFinished IndexOutOfBoundsException");
            return;
        }
        if (mkjVar.n() == 10001) {
            string = getString(R.string._2130841938_res_0x7f021152);
        } else {
            string = getString(R.string._2130840768_res_0x7f020cc0);
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(mkjVar.c());
        builder.czE_(string, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (mkjVar.n() != 10001) {
                    mer.b(AchieveKaKaActivity.this.n).b(mkjVar, AchieveKaKaActivity.this.n);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        this.r = e2;
        e2.setCancelable(true);
        this.r.show();
    }

    private void p() {
        if (this.at != null) {
            d(8);
        }
    }

    private void b(int i) {
        if (this.at == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "handleNetworkError mNetworkErrorTipBar is null");
            this.aa = false;
            return;
        }
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "achieveKakaTaskReloadLayout is show");
            this.aa = false;
            return;
        }
        if (this.aa) {
            a(getString(i));
            this.aa = false;
        }
        this.at.setTipText(i);
        this.at.setLeadBtnText(-1);
        this.at.setOnClicked(null);
        if (this.at.getVisibility() != 0) {
            d(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(KakaCheckInReturnBody kakaCheckInReturnBody) {
        this.j = kakaCheckInReturnBody;
        this.af.c();
        if (this.o == null) {
            x();
        }
        LinearLayout linearLayout = this.o;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
            String e2 = UnitUtil.e(kakaCheckInReturnBody.getKaka(), 1, 0);
            this.h.setText(Marker.ANY_NON_NULL_MARKER + e2);
            this.m.setText(this.n.getResources().getQuantityString(R.plurals._2130903181_res_0x7f03008d, kakaCheckInReturnBody.getConDays(), UnitUtil.e((double) kakaCheckInReturnBody.getConDays(), 1, 0), e2));
        }
        e(kakaCheckInReturnBody.getConDays());
        String replaceAll = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10).replaceAll(Constants.LINK, "");
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "CheckedIn:date  == " + replaceAll);
        a("kakaLastCheckInTime", replaceAll);
        a("kakaCheckedDays", String.valueOf(kakaCheckInReturnBody.getConDays()));
        HashMap hashMap = new HashMap(2);
        hashMap.put(KakaConstants.KAKA_CHACKIN_BI_PARAMS, this.ai.isChecked() ? "1" : "-1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.KAKA_CHECKED_IN_1100046.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<KakaCheckinRecord> list) {
        int b2 = this.al.b(list);
        if (b2 > this.k) {
            e(b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void a(T t) {
        if (t instanceof List) {
            this.ax.addAll((List) t);
        }
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "startMessageAnimation mRedeemMessageQueue size = ", Integer.valueOf(this.ax.size()));
        if (this.ax.size() == 0) {
            this.as.setVisibility(8);
            return;
        }
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout == null || relativeLayout.getVisibility() == 8) {
            this.as.setVisibility(0);
            am();
        } else {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "can not show messageFlipper");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        if (this.as.getVisibility() == 8 && this.ax.size() <= 0) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "updateMessageQueue message is empty");
            return;
        }
        GiftRecord poll = this.ax.poll();
        while (poll == null && this.ax.size() > 0) {
            poll = this.ax.poll();
        }
        if (poll != null) {
            LinearLayout linearLayout = this.av;
            LinearLayout linearLayout2 = this.aq;
            if (linearLayout == linearLayout2) {
                this.av = this.ao;
            } else {
                this.av = linearLayout2;
            }
            ((HealthTextView) this.av.findViewById(R.id.kaka_msg_text)).setText(getString(R.string._2130840878_res_0x7f020d2e, new Object[]{poll.getNickName(), poll.getAwardName()}));
            this.as.showNext();
            this.z.removeMessages(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED);
            this.z.sendEmptyMessageDelayed(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED, 3000L);
        } else {
            LogUtil.h("PLGACHIEVE_AchieveKaKaActivity", "updateMessageQueue message is empty");
        }
        if (this.ax.size() == 1) {
            this.af.d();
        }
    }

    private void e(int i) {
        this.k = i;
        String e2 = UnitUtil.e(i, 1, 0);
        final SpannableString spannableString = new SpannableString(getResources().getString(R.string._2130840875_res_0x7f020d2b, getResources().getQuantityString(R.plurals._2130903164_res_0x7f03007c, i, e2)));
        int indexOf = spannableString.toString().indexOf(e2);
        if (indexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.n, R.color._2131296377_res_0x7f090079)), indexOf, e2.length() + indexOf, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(this.n.getResources().getDimensionPixelSize(nsn.a(3.1f) ? R.dimen._2131363466_res_0x7f0a068a : R.dimen._2131365080_res_0x7f0a0cd8)), indexOf, e2.length() + indexOf, 33);
        }
        runOnUiThread(new Runnable() { // from class: mhw
            @Override // java.lang.Runnable
            public final void run() {
                AchieveKaKaActivity.this.cgV_(spannableString);
            }
        });
    }

    public /* synthetic */ void cgV_(SpannableString spannableString) {
        this.ae.setText(spannableString);
    }

    private void d(int i, int i2) {
        final int min = Math.min(Math.abs(i2 - i) * 50, 1000);
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.setDuration(min);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: mhx
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AchieveKaKaActivity.this.cgW_(min, valueAnimator);
            }
        });
        ofInt.start();
        this.s = i2;
    }

    public /* synthetic */ void cgW_(int i, ValueAnimator valueAnimator) {
        String obj = valueAnimator.getAnimatedValue().toString();
        try {
            if (valueAnimator.getDuration() == i) {
                obj = String.valueOf(Math.max(Integer.parseInt(obj), this.s));
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("PLGACHIEVE_AchieveKaKaActivity", "totalKakaTextStartAnimation: exception -> " + e2.getMessage());
        }
        this.t.setText(UnitUtil.e(CommonUtil.a(obj), 1, 0));
    }

    private void ad() {
        this.ar = (LinearLayout) findViewById(R.id.achieve_kaka_mall);
        this.ab = (H5ProWebView) findViewById(R.id.kaka_h5_pro_web_view);
        bzs.e().initH5Pro();
        H5ProClient.preLoadH5MiniProgram(this, KakaConstants.KAKA_H5_PACKAGE_NAME, new H5ProAppLoader.H5ProPreloadCbk() { // from class: com.huawei.pluginachievement.ui.AchieveKaKaActivity.3
            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onSuccess() {
                LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "loadKakaMallH5 preload success");
                AchieveKaKaActivity.this.b(FitnessStatusCodes.MISSING_BLE_PERMISSION, (int) null);
            }

            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onFailure(int i, String str) {
                LogUtil.b("PLGACHIEVE_AchieveKaKaActivity", String.format("loadKakaMallH5 onFailure: errCode = %s, errMsg = %s", Integer.valueOf(i), str));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        H5ProLaunchOption.Builder addPath = new H5ProLaunchOption.Builder().addPath("#/mall");
        if (this.ab != null) {
            bzs.e().loadEmbeddedH5(this.ab, KakaConstants.KAKA_H5_PACKAGE_NAME, addPath);
        }
    }

    private void al() {
        LogUtil.a("PLGACHIEVE_AchieveKaKaActivity", "triggerRefreshKaKaMall");
        H5ProWebView h5ProWebView = this.ab;
        if (h5ProWebView != null) {
            h5ProWebView.execJs("if (typeof(window.refreshGift) === 'function') {window.refreshGift();}", null);
        }
    }
}
