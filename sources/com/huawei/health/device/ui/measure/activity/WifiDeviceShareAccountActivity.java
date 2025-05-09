package com.huawei.health.device.ui.measure.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceDeleteAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUpdateAuthorizeSubUserReq;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.crx;
import defpackage.ctq;
import defpackage.ctv;
import defpackage.jbs;
import defpackage.nmn;
import defpackage.nrh;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes3.dex */
public class WifiDeviceShareAccountActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f2241a;
    private HealthButton b;
    private NoTitleCustomAlertDialog c;
    private ImageView d;
    private ImageView f;
    private CustomViewDialog g;
    private HealthDivider h;
    private CustomViewDialog.Builder j;
    private b l;
    private HealthEditText n;
    private CommonDialog21 o;
    private LinearLayout p;
    private HealthButton q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private crx u;
    private CustomTitleBar w;
    private NoTitleCustomAlertDialog x;
    private String i = "";
    private String m = "";
    private boolean e = false;
    private boolean k = false;

    static class b extends StaticHandler<WifiDeviceShareAccountActivity> {
        b(WifiDeviceShareAccountActivity wifiDeviceShareAccountActivity) {
            super(wifiDeviceShareAccountActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: Ii_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(WifiDeviceShareAccountActivity wifiDeviceShareAccountActivity, Message message) {
            if (wifiDeviceShareAccountActivity == null || wifiDeviceShareAccountActivity.isDestroyed() || wifiDeviceShareAccountActivity.isFinishing()) {
                cpw.a(false, "WifiDeviceShareAccountActivity", "activity is not exist");
                return;
            }
            cpw.a(false, "WifiDeviceShareAccountActivity", "MyHandler what:", Integer.valueOf(message.what));
            switch (message.what) {
                case 1001:
                    wifiDeviceShareAccountActivity.a();
                    nrh.d(wifiDeviceShareAccountActivity.f2241a, wifiDeviceShareAccountActivity.f2241a.getResources().getString(R.string.IDS_device_wifi_share_input_note_success));
                    wifiDeviceShareAccountActivity.t.setText(wifiDeviceShareAccountActivity.m);
                    wifiDeviceShareAccountActivity.s.setText(wifiDeviceShareAccountActivity.m);
                    wifiDeviceShareAccountActivity.u.d(wifiDeviceShareAccountActivity.m);
                    wifiDeviceShareAccountActivity.e = true;
                    ctq.d(wifiDeviceShareAccountActivity.i, wifiDeviceShareAccountActivity.u);
                    break;
                case 1002:
                    wifiDeviceShareAccountActivity.c();
                    break;
                case 1003:
                    wifiDeviceShareAccountActivity.a();
                    ctq.b(wifiDeviceShareAccountActivity.i, wifiDeviceShareAccountActivity.u);
                    wifiDeviceShareAccountActivity.setResult(12);
                    wifiDeviceShareAccountActivity.finish();
                    break;
                case 1004:
                    wifiDeviceShareAccountActivity.a();
                    nrh.b(wifiDeviceShareAccountActivity.f2241a, R.string._2130841551_res_0x7f020fcf);
                    break;
                case 1005:
                    wifiDeviceShareAccountActivity.a();
                    wifiDeviceShareAccountActivity.n();
                    break;
                default:
                    cpw.d(false, "MyHandler what is other", new Object[0]);
                    break;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cpw.a(false, "WifiDeviceShareAccountActivity", "onCreate");
        setContentView(R.layout.wifi_device_share_account_layout);
        this.f2241a = this;
        this.l = new b(this);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getSerializableExtra("share_sub_user_info") instanceof crx) {
                this.u = (crx) intent.getSerializableExtra("share_sub_user_info");
            } else {
                LogUtil.h("WifiDeviceShareAccountActivity", "onCreate, SHARE_SUB_USER_INFO isn't SubUser");
            }
            this.i = intent.getStringExtra("deviceId");
            this.k = intent.getBooleanExtra("isSubUser", false);
        }
        j();
        e();
        f();
    }

    private void j() {
        this.w = (CustomTitleBar) nsy.cMc_(this, R.id.wifi_device_share_title);
        this.f = (ImageView) nsy.cMc_(this, R.id.share_member_header_img);
        this.t = (HealthTextView) nsy.cMc_(this, R.id.share_member_header_title_tv);
        this.r = (HealthTextView) nsy.cMc_(this, R.id.share_member_sub_title_tv);
        this.s = (HealthTextView) nsy.cMc_(this, R.id.share_member_note_tv);
        this.h = (HealthDivider) findViewById(R.id.item_divide_line_view);
        this.p = (LinearLayout) nsy.cMc_(this, R.id.share_member_note_layout);
        this.b = (HealthButton) nsy.cMc_(this, R.id.delete_member_btn);
        this.d = (ImageView) nsy.cMc_(this, R.id.share_arrow_img);
        this.h.setVisibility(8);
        if (this.k) {
            this.b.setVisibility(8);
        }
        if (LanguageUtil.bc(this)) {
            this.d.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.d.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
    }

    private void e() {
        this.w.setLeftButtonClickable(true);
        this.w.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: cmf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDeviceShareAccountActivity.this.If_(view);
            }
        });
        this.p.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    public /* synthetic */ void If_(View view) {
        if (this.e) {
            setResult(11);
        }
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        if (this.u != null) {
            if (this.k) {
                DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
                StringBuilder sb = new StringBuilder(16);
                sb.append(this.i);
                sb.append("_");
                sb.append(this.u.e());
                String c = deviceCloudSharePreferencesManager.c(sb.toString());
                if (!TextUtils.isEmpty(c)) {
                    this.u.d(c);
                }
            }
            if (!TextUtils.isEmpty(this.u.d())) {
                this.t.setText(this.u.d());
                this.s.setText(this.u.d());
            } else {
                this.t.setText(this.u.c());
            }
            this.r.setText(this.u.c());
            ctv.Mh_(this.u.a(), this.f, nmn.cBh_(this.f2241a.getResources(), new nmn.c(null, this.u.e(), true)));
            return;
        }
        LogUtil.h("WifiDeviceShareAccountActivity", "WifiDeviceShareAccountActivity initUserInfo mSubUserInfo is null");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("WifiDeviceShareAccountActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.share_member_note_layout) {
            k();
        } else if (id == R.id.delete_member_btn) {
            o();
        } else {
            cpw.d(false, "WifiDeviceShareAccountActivity", "onClick default");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void k() {
        cpw.a(false, "WifiDeviceShareAccountActivity", "showEditNickDialog");
        CustomViewDialog customViewDialog = this.g;
        if (customViewDialog == null || !customViewDialog.isShowing()) {
            if (!(this.f2241a.getSystemService("layout_inflater") instanceof LayoutInflater)) {
                LogUtil.h("WifiDeviceShareAccountActivity", "mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) not instance of LayoutInflater");
                return;
            }
            View inflate = ((LayoutInflater) this.f2241a.getSystemService("layout_inflater")).inflate(R.layout.commonui_dialog_single_edit, (ViewGroup) null);
            if (inflate == null) {
                cpw.a(false, "WifiDeviceShareAccountActivity", "showSettingUnitPickerDialog() dialog layout fail");
                return;
            }
            Id_(inflate);
            String string = this.f2241a.getResources().getString(R.string.IDS_device_wifi_share_note);
            String string2 = this.f2241a.getResources().getString(R.string._2130841938_res_0x7f021152);
            String string3 = this.f2241a.getResources().getString(R.string._2130841130_res_0x7f020e2a);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f2241a);
            this.j = builder;
            builder.a(string).czg_(inflate).czf_(string2, new View.OnClickListener() { // from class: cmh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDeviceShareAccountActivity.this.Ig_(view);
                }
            }).czd_(string3, new View.OnClickListener() { // from class: cmi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDeviceShareAccountActivity.this.Ih_(view);
                }
            });
            this.g = this.j.e();
            HealthButton b2 = this.j.b();
            this.q = b2;
            c(b2);
            this.g.setCancelable(false);
            this.g.show();
        }
    }

    public /* synthetic */ void Ig_(View view) {
        cpw.a(false, "WifiDeviceShareAccountActivity", "showNickEditDialog setPositiveButton");
        if (this.k) {
            m();
        } else {
            l();
        }
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Ih_(View view) {
        cpw.a(false, "WifiDeviceShareAccountActivity", "showNickEditDialog setNegativeButton");
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if (this.q != null) {
            this.q = null;
        }
        if (this.g != null) {
            this.g = null;
        }
    }

    private void Id_(View view) {
        this.n = (HealthEditText) view.findViewById(R.id.edit);
        if (!TextUtils.isEmpty(this.u.d())) {
            this.n.setText(this.u.d());
        }
        this.n.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        this.n.setInputType(1);
        this.n.setHint(R.string.IDS_device_wifi_share_input_note);
        this.n.setSingleLine();
        this.n.setLongClickable(false);
        this.n.setTextIsSelectable(false);
        i();
        this.n.setImeOptions(268435456);
        this.n.setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity.4
            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
        this.n.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return WifiDeviceShareAccountActivity.Ie_(textView, i, keyEvent);
            }
        });
        h();
    }

    static /* synthetic */ boolean Ie_(TextView textView, int i, KeyEvent keyEvent) {
        return (i == 4 || i == 6) || (keyEvent != null && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0);
    }

    private void h() {
        this.n.addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                WifiDeviceShareAccountActivity wifiDeviceShareAccountActivity = WifiDeviceShareAccountActivity.this;
                wifiDeviceShareAccountActivity.c(wifiDeviceShareAccountActivity.q);
            }
        });
    }

    private void i() {
        Editable text = this.n.getText();
        if (text != null) {
            if (text instanceof Spannable) {
                Selection.setSelection(text, text.length());
            } else {
                LogUtil.h("WifiDeviceShareAccountActivity", "spanText is not instanceof Spannable!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HealthButton healthButton) {
        if (healthButton != null) {
            String trim = this.n.getText().toString().trim();
            if (TextUtils.isEmpty(trim) || trim.equals(this.u.d())) {
                healthButton.setTextColor(this.f2241a.getResources().getColor(R.color._2131296874_res_0x7f09026a));
                healthButton.setClickable(false);
            } else {
                healthButton.setTextColor(this.f2241a.getResources().getColor(R.color._2131296927_res_0x7f09029f));
                healthButton.setClickable(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        cpw.a(false, "WifiDeviceShareAccountActivity", "showSubUserNoExistDialog");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.x;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            String format = String.format(Locale.ENGLISH, this.f2241a.getResources().getString(R.string.IDS_device_wifi_share_sub_user_release), this.u.d());
            String string = this.f2241a.getResources().getString(R.string._2130841794_res_0x7f0210c2);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f2241a);
            builder.e(format).b(true).czE_(string, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (WifiDeviceShareAccountActivity.this.x != null) {
                        WifiDeviceShareAccountActivity.this.x = null;
                    }
                    WifiDeviceShareAccountActivity.this.setResult(11);
                    WifiDeviceShareAccountActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.x = e;
            e.setCancelable(false);
            this.x.show();
        }
    }

    private void o() {
        String format;
        cpw.a(false, "WifiDeviceShareAccountActivity", "showDeleteMemberDialog");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.c;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            String string = this.f2241a.getResources().getString(R.string.IDS_device_wifi_share_remove_member1);
            if (!TextUtils.isEmpty(this.u.d())) {
                format = String.format(Locale.ENGLISH, string, this.u.d(), "");
            } else {
                format = String.format(Locale.ENGLISH, string, this.u.c(), "");
            }
            String string2 = this.f2241a.getResources().getString(R.string._2130842177_res_0x7f021241);
            String string3 = this.f2241a.getResources().getString(R.string._2130841130_res_0x7f020e2a);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f2241a);
            builder.e(format).czE_(string2, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WifiDeviceShareAccountActivity.this.b();
                    if (WifiDeviceShareAccountActivity.this.c != null) {
                        WifiDeviceShareAccountActivity.this.c = null;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czA_(string3, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareAccountActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (WifiDeviceShareAccountActivity.this.c != null) {
                        WifiDeviceShareAccountActivity.this.c = null;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.c = e;
            e.setCancelable(false);
            this.c.show();
        }
    }

    private void m() {
        if (TextUtils.isEmpty(this.i) || this.u == null) {
            cpw.d(false, "WifiDeviceShareAccountActivity", "updateSubUserInfo mDeviceId is null or mSubUserInfo is null");
            return;
        }
        e(this.f2241a.getResources().getString(R.string._2130841527_res_0x7f020fb7));
        this.m = this.n.getText().toString();
        new DeviceCloudSharePreferencesManager(cpp.a()).a(this.i + "_" + this.u.e(), this.m);
        this.l.sendEmptyMessage(1001);
    }

    private void l() {
        cpw.a(false, "WifiDeviceShareAccountActivity", "updateSubUserInfo");
        if (g()) {
            if (TextUtils.isEmpty(this.i) || this.u == null) {
                cpw.e(false, "WifiDeviceShareAccountActivity", "updateSubUserInfo mDeviceId is null or mSubUserInfo is null");
                return;
            }
            e(this.f2241a.getResources().getString(R.string._2130841527_res_0x7f020fb7));
            this.m = this.n.getText().toString();
            WifiDeviceUpdateAuthorizeSubUserReq wifiDeviceUpdateAuthorizeSubUserReq = new WifiDeviceUpdateAuthorizeSubUserReq();
            wifiDeviceUpdateAuthorizeSubUserReq.setDevId(this.i);
            wifiDeviceUpdateAuthorizeSubUserReq.setSubHuid(this.u.e());
            wifiDeviceUpdateAuthorizeSubUserReq.setNickName(this.m);
            jbs.a(this.f2241a).e(wifiDeviceUpdateAuthorizeSubUserReq, new ICloudOperationResult() { // from class: cmb
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    WifiDeviceShareAccountActivity.this.e((CloudCommonReponse) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void e(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        if (z) {
            if (cloudCommonReponse != null) {
                cpw.a(false, "WifiDeviceShareAccountActivity", "updateSubUserInfo() errorCode = " + cloudCommonReponse.getResultCode() + "|resultDesc = " + cloudCommonReponse.getResultDesc());
            }
            this.l.sendEmptyMessage(1001);
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        cpw.a(false, "WifiDeviceShareAccountActivity", "updateSubUserInfo() errorCode = " + i + "resultDesc" + str2);
        if (i == 112000030) {
            this.l.sendEmptyMessage(1005);
        } else {
            this.l.sendEmptyMessage(1004);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToCloud");
        if (g()) {
            if (TextUtils.isEmpty(this.i) || this.u == null) {
                cpw.e(false, "WifiDeviceShareAccountActivity", "deleteSubUserToCloud mDeviceId is null or mSubUserInfo is null");
                return;
            }
            e(this.f2241a.getResources().getString(R.string.IDS_device_wifi_share_removing));
            WifiDeviceDeleteAuthorizeSubUserReq wifiDeviceDeleteAuthorizeSubUserReq = new WifiDeviceDeleteAuthorizeSubUserReq();
            wifiDeviceDeleteAuthorizeSubUserReq.setDevId(this.i);
            wifiDeviceDeleteAuthorizeSubUserReq.setSubHuid(this.u.e());
            jbs.a(this.f2241a).b(wifiDeviceDeleteAuthorizeSubUserReq, new ICloudOperationResult() { // from class: cmd
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    WifiDeviceShareAccountActivity.this.c((CloudCommonReponse) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void c(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        if (z) {
            if (cloudCommonReponse != null) {
                cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToCloud() errorCode = ", cloudCommonReponse.getResultCode(), "|resultDesc = ", cloudCommonReponse.getResultDesc());
            }
            this.l.sendEmptyMessage(1002);
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToCloud() errorCode = ", Integer.valueOf(i), " resultDesc = ", str2);
        if (i == 112000030) {
            this.l.sendEmptyMessage(1002);
        } else {
            this.l.sendEmptyMessage(1004);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToDevice");
        if (g()) {
            if (TextUtils.isEmpty(this.i) || this.u == null) {
                cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToDevice deviceid is null or mSubUserInfo is null");
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
            HashMap hashMap = new HashMap(16);
            hashMap.put(this.u.b(), this.u.e());
            deviceServiceInfo.setData(hashMap);
            deviceServiceInfo.setSid("setDevParam");
            arrayList.add(deviceServiceInfo);
            WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
            wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
            wifiDeviceControlDataModelReq.setDevId(this.i);
            jbs.a(this.f2241a).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: cmc
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    WifiDeviceShareAccountActivity.this.a((CloudCommonReponse) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z) {
            cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToDevice success ");
            this.l.sendEmptyMessage(1003);
            return;
        }
        if (cloudCommonReponse != null) {
            cpw.a(false, "WifiDeviceShareAccountActivity", "deleteSubUserToDevice errorCode = " + cloudCommonReponse.getResultCode().intValue() + " | errorDes = " + cloudCommonReponse.getResultDesc());
        }
        this.l.sendEmptyMessage(1004);
    }

    private boolean g() {
        if (CommonUtil.aa(this.f2241a)) {
            return true;
        }
        nrh.b(this.f2241a, R.string._2130841884_res_0x7f02111c);
        return false;
    }

    private void e(String str) {
        if (this.o == null) {
            new CommonDialog21(this, R.style.app_update_dialogActivity);
            this.o = CommonDialog21.a(this);
        }
        this.o.setCancelable(false);
        this.o.e(str);
        this.o.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CommonDialog21 commonDialog21;
        if (isFinishing() || (commonDialog21 = this.o) == null || !commonDialog21.isShowing()) {
            return;
        }
        this.o.dismiss();
        this.o = null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.e) {
            setResult(11);
        }
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
