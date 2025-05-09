package com.huawei.ui.device.activity.onelevelmenu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview.MenuDragItemTouchHelperCallback;
import com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview.MenuDragListAdapter;
import com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview.RefreshUiCallback;
import defpackage.ixx;
import defpackage.jed;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.oae;
import defpackage.oai;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class OneLevelMenuManagerActivity extends BaseActivity implements RefreshUiCallback {
    private LinearLayout aa;
    private LinearLayout b;
    private CustomTitleBar f;
    private Context g;
    private LinearLayout h;
    private DeviceSettingsInteractors i;
    private HealthRecycleView l;
    private HealthProgressBar m;
    private HealthScrollView n;
    private HealthDivider o;
    private LinearLayout r;
    private HealthButton s;
    private LinearLayout t;
    private ImageView v;
    private oai w;
    private NoTitleCustomAlertDialog x;
    private HealthToolBar z;
    Map<String, byte[]> d = new HashMap(16);
    private MenuDragListAdapter c = null;
    private ArrayList<Integer> q = null;
    private ArrayList<Integer> e = null;
    private ArrayList<Integer> p = null;
    private boolean k = false;
    private String j = "";
    private ArrayList<Integer> y = new ArrayList<>(10);
    private HealthToolBar.OnSingleTapListener ac = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.5
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (i != 2) {
                return;
            }
            OneLevelMenuManagerActivity.this.e();
        }
    };
    private Handler u = new a(this);
    private View.OnClickListener ab = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("OneLevelMenuManagerActivity", "=retry");
            OneLevelMenuManagerActivity.this.u.sendEmptyMessage(2);
            OneLevelMenuManagerActivity.this.u.sendEmptyMessageDelayed(3, 5000L);
            OneLevelMenuManagerActivity.this.d();
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f9170a = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("OneLevelMenuManagerActivity", "addView");
            OneLevelMenuManagerActivity.this.e();
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<OneLevelMenuManagerActivity> f9172a;

        a(OneLevelMenuManagerActivity oneLevelMenuManagerActivity) {
            this.f9172a = new WeakReference<>(oneLevelMenuManagerActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.f9172a.get() == null) {
                return;
            }
            LogUtil.a("OneLevelMenuManagerActivity", "Enter handleMessage():", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                OneLevelMenuManagerActivity.this.o();
                return;
            }
            if (i == 2) {
                OneLevelMenuManagerActivity.this.k = false;
                OneLevelMenuManagerActivity.this.c(true);
                OneLevelMenuManagerActivity.this.f.setRightButtonClickable(false);
                return;
            }
            if (i == 3) {
                OneLevelMenuManagerActivity.this.a(true);
                OneLevelMenuManagerActivity.this.k = true;
                OneLevelMenuManagerActivity.this.f.setRightButtonClickable(false);
            } else {
                if (i == 4) {
                    OneLevelMenuManagerActivity.this.c(false);
                    OneLevelMenuManagerActivity.this.a(false);
                    OneLevelMenuManagerActivity.this.k = false;
                    OneLevelMenuManagerActivity.this.f.setRightButtonClickable(true);
                    return;
                }
                if (i == 5) {
                    OneLevelMenuManagerActivity.this.c.d(OneLevelMenuManagerActivity.this.q);
                    OneLevelMenuManagerActivity.this.c.notifyDataSetChanged();
                    OneLevelMenuManagerActivity.this.o();
                    OneLevelMenuManagerActivity.this.g();
                    return;
                }
                LogUtil.b("OneLevelMenuManagerActivity", "not support message:", Integer.valueOf(message.what));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_one_level_menu_activity);
        a();
        f();
    }

    private void a() {
        this.g = this;
        this.i = DeviceSettingsInteractors.d((Context) this);
        this.w = oai.a();
        this.q = new ArrayList<>(10);
        this.e = new ArrayList<>(10);
        this.p = new ArrayList<>(10);
        LogUtil.a("OneLevelMenuManagerActivity", "mContactTables size = ", Integer.valueOf(this.q.size()));
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getStringExtra("device_id");
        }
    }

    private void f() {
        this.z = (HealthToolBar) nsy.cMc_(this, R.id.one_level_menu_add_bottom_toolbar);
        this.z.cHc_(View.inflate(this.g, R.layout.hw_toolbar_bottomview, null));
        this.z.setIconVisible(3, 8);
        this.z.setIconVisible(1, 8);
        this.z.setIcon(2, R.drawable._2131429693_res_0x7f0b093d);
        this.z.setIconTitle(2, this.g.getResources().getString(R.string._2130841394_res_0x7f020f32));
        this.z.cHf_(this);
        this.z.setOnSingleTapListener(this.ac);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.one_level_menu_titlebar);
        this.f = customTitleBar;
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("OneLevelMenuManagerActivity", "=setRightButtonOnClickListener click right response");
                if (!OneLevelMenuManagerActivity.this.w.e()) {
                    OneLevelMenuManagerActivity.this.i();
                    OneLevelMenuManagerActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("OneLevelMenuManagerActivity", "=setRightButtonOnClickListener click left response");
                OneLevelMenuManagerActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ((HealthTextView) nsy.cMc_(this, R.id.text_1)).setText(jed.b(1.0d, 1, 0) + ". " + this.g.getResources().getString(R.string._2130842234_res_0x7f02127a));
        ((HealthTextView) nsy.cMc_(this, R.id.text_2)).setText(jed.b(2.0d, 1, 0) + ". " + this.g.getResources().getString(R.string._2130842235_res_0x7f02127b));
        this.b = (LinearLayout) nsy.cMc_(this, R.id.one_level_menu_add_bottom_no_content_layout);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.disagree);
        this.s = healthButton;
        healthButton.setOnClickListener(this.f9170a);
        this.h = (LinearLayout) nsy.cMc_(this, R.id.one_level_menu_bottom_textview);
        this.r = (LinearLayout) nsy.cMc_(this, R.id.one_level_menu_loading_view);
        HealthProgressBar healthProgressBar = (HealthProgressBar) nsy.cMc_(this, R.id.one_level_menu_loading_image);
        this.m = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        this.o = (HealthDivider) nsy.cMc_(this, R.id.drag_item_divider);
        k();
        d();
    }

    private void k() {
        this.aa = (LinearLayout) nsy.cMc_(this, R.id.one_level_menu_retry_view);
        this.n = (HealthScrollView) nsy.cMc_(this, R.id.one_level_menu_listview);
        this.t = (LinearLayout) nsy.cMc_(this, R.id.one_level_menu_no_content_view);
        ImageView imageView = (ImageView) nsy.cMc_(this, R.id.one_level_menu_retry_image);
        this.v = imageView;
        imageView.setOnClickListener(this.ab);
        LogUtil.a("OneLevelMenuManagerActivity", "mOneMenuHandler=", this.u);
        this.l = (HealthRecycleView) nsy.cMc_(this, R.id.one_level_menu_drag_list);
        this.u.sendEmptyMessage(2);
        this.u.sendEmptyMessageDelayed(3, 5000L);
        c();
    }

    private void c() {
        this.l.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.10
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 1 && OneLevelMenuManagerActivity.this.c != null) {
                    OneLevelMenuManagerActivity.this.c.notifyDataSetChanged();
                }
                return false;
            }
        });
        this.c = new MenuDragListAdapter(this.g, this.q, this);
        this.l.setLayoutManager(new LinearLayoutManager(this.g));
        this.l.setAdapter(this.c);
        this.l.a(false);
        this.l.d(false);
        new ItemTouchHelper(new MenuDragItemTouchHelperCallback(this.c)).attachToRecyclerView(this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("OneLevelMenuManagerActivity", "Enter dealBackButton");
        LogUtil.a("OneLevelMenuManagerActivity", "Enter dealBackButton mMenuTables", this.q);
        LogUtil.a("OneLevelMenuManagerActivity", "Enter dealBackButton mMenuItemFromBandTables", this.p);
        if (!koq.b(this.q, this.p, true)) {
            n();
        } else {
            finish();
        }
    }

    private void n() {
        LogUtil.a("OneLevelMenuManagerActivity", "showPromptSaveDialog()");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130842170_res_0x7f02123a).czE_(this.g.getResources().getString(R.string._2130841751_res_0x7f021097).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("OneLevelMenuManagerActivity", "showPromptSaveDialog() Yes ...");
                OneLevelMenuManagerActivity.this.x.cancel();
                OneLevelMenuManagerActivity.this.j();
                OneLevelMenuManagerActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(this.g.getResources().getString(R.string._2130841389_res_0x7f020f2d).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneLevelMenuManagerActivity.this.x.cancel();
                OneLevelMenuManagerActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.x = e;
        e.setCancelable(false);
        if (this.x.isShowing()) {
            return;
        }
        this.x.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        ArrayList<Integer> arrayList = this.q;
        if (arrayList != null && arrayList.size() > 0) {
            LogUtil.b("OneLevelMenuManagerActivity", "get null DBdata, the activity will be shut down!");
            this.h.setVisibility(0);
            this.o.setVisibility(0);
            this.t.setVisibility(8);
            this.b.setVisibility(8);
            this.z.setVisibility(0);
            return;
        }
        this.h.setVisibility(8);
        this.o.setVisibility(8);
        this.t.setVisibility(0);
        this.b.setVisibility(0);
        this.z.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Intent intent = new Intent();
        intent.setClass(this.g, OneLevelMenuAddActivity.class);
        intent.putIntegerArrayListExtra("intent_to_next_all_list", this.e);
        intent.putIntegerArrayListExtra("intent_to_next_open_list", this.q);
        LogUtil.a("OneLevelMenuManagerActivity", "mMenuTables=", this.q);
        LogUtil.a("OneLevelMenuManagerActivity", "mAllList=", this.e);
        startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("OneLevelMenuManagerActivity", "saveData() data mContactTables = ", this.q);
        if (this.i == null) {
            LogUtil.a("OneLevelMenuManagerActivity", "mDeviceSettingsInteractors of saveData error null!");
            return;
        }
        if (oae.c(this.g).e(this.j) != 2) {
            nrh.b(this.g, R.string._2130841439_res_0x7f020f5f);
        }
        LogUtil.a("OneLevelMenuManagerActivity", "=mOneLevelMenuInteractor.isListEqual(mMenuTables,mMenuItemFromBandTables)", Boolean.valueOf(koq.b(this.q, this.p, true)));
        j();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("OneLevelMenuManagerActivity", "onDestroy");
        super.onDestroy();
        h();
    }

    private void h() {
        Handler handler = this.u;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.ui.device.views.onelevelmenu.dragonelevelsortlistview.RefreshUiCallback
    public void refreshUi() {
        this.u.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        LogUtil.a("OneLevelMenuManagerActivity", "showLoadingView isShow", Boolean.valueOf(z));
        if (z) {
            this.h.setVisibility(8);
            this.n.setVisibility(8);
            this.aa.setVisibility(8);
            this.r.setVisibility(0);
            this.z.setVisibility(8);
            return;
        }
        this.h.setVisibility(0);
        this.n.setVisibility(0);
        this.aa.setVisibility(8);
        this.r.setVisibility(8);
        this.z.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        LogUtil.a("OneLevelMenuManagerActivity", "showRetryView isShow=");
        if (z) {
            this.h.setVisibility(8);
            this.n.setVisibility(8);
            this.aa.setVisibility(0);
            this.r.setVisibility(8);
            this.z.setVisibility(8);
            return;
        }
        this.h.setVisibility(0);
        this.n.setVisibility(0);
        this.aa.setVisibility(8);
        this.r.setVisibility(8);
        this.z.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.e == null || this.q == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (this.e.clone() instanceof ArrayList) {
            arrayList = (ArrayList) this.e.clone();
        }
        arrayList.removeAll(this.q);
        HashMap hashMap = new HashMap(16);
        if (arrayList.size() == 0) {
            hashMap.put("delete_fun", -1);
        } else {
            hashMap.put("delete_fun", arrayList.toString());
        }
        if (this.q.size() > 0) {
            for (int i = 0; i < this.q.size() && i <= 2; i++) {
                if (i == 0) {
                    hashMap.put("first", this.q.get(0));
                } else if (i == 1) {
                    hashMap.put("second", this.q.get(1));
                } else if (i == 2) {
                    hashMap.put("third", this.q.get(2));
                } else {
                    LogUtil.a("OneLevelMenuManagerActivity", "sendCustomFunEvent default i::", Integer.valueOf(i));
                }
            }
        }
        LogUtil.a("OneLevelMenuManagerActivity", "sendCustomFunEvent, ", hashMap.toString());
        ixx.d().d(this.g, AnalyticsValue.SETTING_CUSTOM_FUNCTION_1090034.value(), hashMap, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("OneLevelMenuManagerActivity", "requestCode=", Integer.valueOf(i), "， resultCode=", Integer.valueOf(i2));
        if (i2 != 1) {
            return;
        }
        LogUtil.a("OneLevelMenuManagerActivity", "mMenuTables fromnext mMenuTables is ", this.q);
        try {
            this.q.addAll(intent.getIntegerArrayListExtra("intent_from_next_open_list"));
        } catch (ArrayIndexOutOfBoundsException e) {
            LogUtil.a("OneLevelMenuManagerActivity", "ArrayIndexOutOfBoundsException occur message is ", e.getMessage());
        }
        this.u.sendEmptyMessage(5);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            b();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("OneLevelMenuManagerActivity", "Enter sendDataToBand");
        ArrayList<Integer> arrayList = this.y;
        if (arrayList != null && arrayList.size() > 0) {
            this.q.addAll(this.y);
        }
        this.i.d(this.g, this.j, this.q, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("OneLevelMenuManagerActivity", "sendDataToBand err_code=", Integer.valueOf(i), "objData=", obj);
            }
        });
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("OneLevelMenuManagerActivity", "Enter getDataFromBand()");
        this.i.b(this.g, this.j, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("OneLevelMenuManagerActivity", "getDataFromBand mIsTimeout=", Boolean.valueOf(OneLevelMenuManagerActivity.this.k));
                OneLevelMenuManagerActivity.this.u.removeMessages(3);
                if (OneLevelMenuManagerActivity.this.k) {
                    return;
                }
                LogUtil.a("OneLevelMenuManagerActivity", "getDataFromBand err_code=", Integer.valueOf(i), "objData=", obj);
                if (i == 0) {
                    LogUtil.a("OneLevelMenuManagerActivity", "Enter getDataFromBand():mOneMenuHandler=", OneLevelMenuManagerActivity.this.u);
                    if (obj instanceof Map) {
                        OneLevelMenuManagerActivity.this.d = (Map) obj;
                    }
                    byte[] bArr = OneLevelMenuManagerActivity.this.d.get("all");
                    if (bArr == null) {
                        return;
                    }
                    OneLevelMenuManagerActivity.this.e.clear();
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        if (OneLevelMenuManagerActivity.this.w.e(OneLevelMenuManagerActivity.this.w.d(bArr[i2]))) {
                            OneLevelMenuManagerActivity.this.e.add(Integer.valueOf(OneLevelMenuManagerActivity.this.w.d(bArr[i2])));
                        }
                    }
                    OneLevelMenuManagerActivity.this.l();
                    LogUtil.a("OneLevelMenuManagerActivity", "getDataFromBand mMenuTables=", OneLevelMenuManagerActivity.this.q, "mMenuItemFromBandTables=", OneLevelMenuManagerActivity.this.p);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("OneLevelMenuManagerActivity", "getDataFromBand mAllList=", this.e);
        byte[] bArr = this.d.get(JsbMapKeyNames.H5_TEXT_DOWNLOAD_OPEN);
        if (bArr == null) {
            return;
        }
        this.q.clear();
        this.p.clear();
        for (int i = 0; i < bArr.length; i++) {
            oai oaiVar = this.w;
            if (!oaiVar.e(oaiVar.d(bArr[i]))) {
                this.y.add(Integer.valueOf(this.w.d(bArr[i])));
            } else {
                this.q.add(Integer.valueOf(this.w.d(bArr[i])));
                this.p.add(Integer.valueOf(this.w.d(bArr[i])));
            }
        }
        this.u.sendEmptyMessage(4);
        this.u.sendEmptyMessage(5);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
