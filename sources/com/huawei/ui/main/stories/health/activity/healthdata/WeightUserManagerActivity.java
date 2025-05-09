package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightUserManagerActivity;
import com.huawei.up.api.UpApi;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.cfi;
import defpackage.glz;
import defpackage.nsy;
import defpackage.qpz;
import defpackage.qsj;
import defpackage.sbc;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class WeightUserManagerActivity extends BaseActivity implements HealthToolBar.OnSingleTapListener {
    private WeightUserManagerActivity b;
    private b c;
    private c e;
    private ListView g;
    private HealthToolBar h;
    private UserProfileMgrApi i;
    private LocalBroadcastManager j;
    private boolean d = false;
    private final BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightUserManagerActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("HealthWeight_WeightUserManagerActivity", "mUserProfileReceiver action ", intent.getAction());
            WeightUserManagerActivity.this.e();
        }
    };

    /* renamed from: a, reason: collision with root package name */
    CommonCallback f10106a = new CommonCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightUserManagerActivity.5
        @Override // com.huawei.up.callback.CommonCallback
        public void onSuccess(Bundle bundle) {
            LogUtil.a("HealthWeight_WeightUserManagerActivity", "toGetRemoteHeadImage get new headImg and name from cloud success.");
            WeightUserManagerActivity.this.e();
        }

        @Override // com.huawei.up.callback.CommonCallback
        public void onFail(int i) {
            LogUtil.a("HealthWeight_WeightUserManagerActivity", "toGetRemoteHeadImage get new headImg and name from cloud failure.");
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weight_user_manager);
        this.b = this;
        this.c = new b(this);
        d();
        a();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        this.j = localBroadcastManager;
        localBroadcastManager.registerReceiver(this.f, new IntentFilter("com.huawei.bone.action.FITNESS_USERINFO_UPDATED"));
        this.i = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
    }

    /* loaded from: classes8.dex */
    static class b extends BaseHandler<WeightUserManagerActivity> {
        b(WeightUserManagerActivity weightUserManagerActivity) {
            super(weightUserManagerActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dCa_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightUserManagerActivity weightUserManagerActivity, Message message) {
            if (message.what == 2) {
                if (message.obj instanceof UserInfomation) {
                    weightUserManagerActivity.c((UserInfomation) message.obj);
                    return;
                }
                return;
            }
            LogUtil.h("HealthWeight_WeightUserManagerActivity", "handleMessageWhenReferenceNotNull msg.what ", Integer.valueOf(message.what));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(UserInfomation userInfomation) {
        LogUtil.c("HealthWeight_WeightUserManagerActivity", "handleWhenGetUserInfoSuccess userInfo is ", userInfomation.toString());
        String c2 = sbc.c(userInfomation);
        if (TextUtils.isEmpty(c2)) {
            String accountName = new UpApi(this.b).getAccountName();
            LogUtil.c("HealthWeight_WeightUserManagerActivity", "handleWhenGetUserInfoSuccess accountName is ", accountName);
            if (!TextUtils.isEmpty(accountName)) {
                MultiUsersManager.INSTANCE.getMainUser().b(accountName);
                UserInfomation userInfo = this.i.getUserInfo();
                if (userInfo != null) {
                    if (TextUtils.isEmpty(userInfo.getName())) {
                        userInfo.setName(accountName);
                        this.i.setUserInfo(userInfo, null);
                    }
                } else {
                    LogUtil.h("HealthWeight_WeightUserManagerActivity", "userInfomation is null");
                }
            }
        } else {
            MultiUsersManager.INSTANCE.getMainUser().b(c2);
        }
        MultiUsersManager.INSTANCE.getMainUser().e(sbc.a(userInfomation));
        b();
    }

    private void d() {
        ((CustomTitleBar) nsy.cMc_(this.b, R.id.fitness_detail_titlebar)).setTitleText(getString(R$string.IDS_hw_base_health_user_list_manager_user));
        this.g = (ListView) nsy.cMc_(this.b, R.id.weight_user_manager_list_view);
        HealthToolBar healthToolBar = (HealthToolBar) findViewById(R.id.buttomview);
        this.h = healthToolBar;
        healthToolBar.cHc_(View.inflate(this.b, R.layout.hw_toolbar_bottomview, null));
        this.h.setIcon(1, R.drawable.achieve_user_device_icon);
        this.h.setIconTitle(1, getResources().getString(R$string.IDS_hw_health_show_healthdata_heart_add));
        this.h.setIcon(3, R.drawable._2131428277_res_0x7f0b03b5);
        this.h.setIconTitle(3, getResources().getString(R$string.IDS_main_btn_state_settings));
        this.h.cHf_(this);
        this.h.setOnSingleTapListener(this);
        c cVar = new c(MultiUsersManager.INSTANCE.getMainAllUser());
        this.e = cVar;
        this.g.setAdapter((ListAdapter) cVar);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, this.g);
    }

    @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
    public void onSingleTap(int i) {
        if (i == 1) {
            c("");
            qsj.c(this.b, AnalyticsValue.WEIGHT_PAGE_CKICK_MANAGE_MEMBERS_2160120.value(), 2);
        } else {
            LogUtil.h("HealthWeight_WeightUserManagerActivity", "onSingleTap position ", Integer.valueOf(i));
        }
    }

    private void a() {
        this.g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightUserManagerActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                cfi item = WeightUserManagerActivity.this.e.getItem(i);
                if (i == 0) {
                    if (WeightUserManagerActivity.this.b != null) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("bundleKeyJumpUserInfoActivity", true);
                        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").zF_(bundle).c(WeightUserManagerActivity.this.b);
                    }
                } else if (item != null) {
                    WeightUserManagerActivity.this.c(item.i());
                } else {
                    LogUtil.h("HealthWeight_WeightUserManagerActivity", "user is null");
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.i.getUserInfo(new BaseResponseCallback() { // from class: qgb
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightUserManagerActivity.this.d(i, (UserInfomation) obj);
            }
        });
    }

    public /* synthetic */ void d(int i, UserInfomation userInfomation) {
        if (i == 0) {
            if (userInfomation == null) {
                LogUtil.h("HealthWeight_WeightUserManagerActivity", "updateUserInfo objData is null");
                return;
            }
            LogUtil.c("HealthWeight_WeightUserManagerActivity", "updateUserInfo objData ", userInfomation.toString());
            Message obtain = Message.obtain();
            obtain.obj = userInfomation;
            obtain.what = 2;
            if (this.c == null) {
                this.c = new b(this.b);
            }
            this.c.sendMessage(obtain);
        }
    }

    private void b() {
        List<cfi> mainAllUser = MultiUsersManager.INSTANCE.getMainAllUser();
        this.e.c(mainAllUser);
        if (mainAllUser.size() >= 10) {
            this.h.setIconVisible(1, 8);
        } else {
            this.h.setIconVisible(1, 0);
        }
        this.h.setIconVisible(3, 8);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c();
        this.e = null;
        this.c = null;
    }

    public void c() {
        BroadcastReceiver broadcastReceiver = this.f;
        if (broadcastReceiver == null) {
            LogUtil.h("HealthWeight_WeightUserManagerActivity", "unregisterGetUserProfileBroadcast, but mUserProfileReceiver is null");
        } else {
            this.j.unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("HealthWeight_WeightUserManagerActivity", "onActivityResult requestCode is ", Integer.valueOf(i), " resultCode is ", Integer.valueOf(i2));
        if (i == 1) {
            this.i.sync(this.f10106a);
            glz.b("WeightUserManagerActivity");
        } else {
            if (i != 2 || intent == null) {
                return;
            }
            if (!this.d) {
                this.d = intent.getBooleanExtra("isChange", false);
            }
            LogUtil.a("HealthWeight_WeightUserManagerActivity", "onActivityResult isChange ", Boolean.valueOf(this.d));
            if (this.d) {
                qsj.dIi_(this, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Intent intent = new Intent(this.b, (Class<?>) AddOrEditWeightUserActivity.class);
        intent.putExtra("weight_user_id_key", str);
        startActivityForResult(intent, 2);
    }

    /* loaded from: classes8.dex */
    class c extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        private final LayoutInflater f10108a;
        private final List<cfi> b = new ArrayList();

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        c(List<cfi> list) {
            this.f10108a = LayoutInflater.from(WeightUserManagerActivity.this.b);
            c(list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(List<cfi> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.b.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public cfi getItem(int i) {
            if (i < 0 || i > this.b.size() - 1) {
                LogUtil.h("HealthWeight_WeightUserManagerActivity", "position param exception");
                return null;
            }
            return this.b.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            C0265c c0265c;
            if (view == null) {
                c0265c = new C0265c();
                view2 = this.f10108a.inflate(R.layout.item_weight_user_manager_list_view, (ViewGroup) null);
                c0265c.c = (ImageView) nsy.cMd_(view2, R.id.item_weight_current_user_photo);
                c0265c.b = (HealthTextView) nsy.cMd_(view2, R.id.item_right_title_text);
                c0265c.f10109a = (ImageView) nsy.cMd_(view2, R.id.hw_health_weight_user_manager_arrow);
                c0265c.e = (HealthDivider) nsy.cMd_(view2, R.id.user_manager_line);
                view2.setTag(c0265c);
            } else {
                view2 = view;
                c0265c = (C0265c) view.getTag();
            }
            if (this.b.size() == i + 1) {
                c0265c.e.setVisibility(4);
            } else {
                c0265c.e.setVisibility(0);
            }
            cfi item = getItem(i);
            if (item == null) {
                LogUtil.h("HealthWeight_WeightUserManagerActivity", "getView user is null");
            } else {
                qpz.dHr_(item, c0265c.b, c0265c.c);
            }
            if (LanguageUtil.bc(WeightUserManagerActivity.this.b)) {
                c0265c.f10109a.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            } else {
                c0265c.f10109a.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            }
            return view2;
        }

        /* renamed from: com.huawei.ui.main.stories.health.activity.healthdata.WeightUserManagerActivity$c$c, reason: collision with other inner class name */
        class C0265c {

            /* renamed from: a, reason: collision with root package name */
            private ImageView f10109a;
            private HealthTextView b;
            private ImageView c;
            private HealthDivider e;

            C0265c() {
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
