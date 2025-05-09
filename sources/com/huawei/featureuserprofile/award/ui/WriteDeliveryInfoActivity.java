package com.huawei.featureuserprofile.award.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.featureuserprofile.award.model.AddressInfo;
import com.huawei.featureuserprofile.award.model.AddressResp;
import com.huawei.featureuserprofile.award.model.SaveInfoItem;
import com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.edittext.HealthEditText;
import defpackage.bqz;
import defpackage.nrh;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class WriteDeliveryInfoActivity extends BaseActivity implements WriteDeliveryInfoContract.View {

    /* renamed from: a, reason: collision with root package name */
    private String f1979a;
    private HealthEditText aa;
    private HealthButton ac;
    private int ad;
    private String b;
    private AddressInfo d;
    private int e;
    private HealthEditText f;
    private HealthEditText g;
    private Context h;
    private HealthEditText i;
    private int j;
    private int k;
    private int n;
    private int o;
    private String p;
    private String q;
    private String s;
    private String t;
    private String u;
    private String v;
    private WriteDeliveryInfoContract.Presenter w;
    private String x;
    private String y;
    private TextWatcher l = new TextWatcher() { // from class: com.huawei.featureuserprofile.award.ui.WriteDeliveryInfoActivity.3
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int length = editable.toString().trim().length();
            WriteDeliveryInfoActivity.this.k = length;
            if (length == 0) {
                WriteDeliveryInfoActivity.this.e(false);
            } else {
                if (WriteDeliveryInfoActivity.this.o <= 0 || WriteDeliveryInfoActivity.this.j <= 0) {
                    return;
                }
                WriteDeliveryInfoActivity.this.e(true);
            }
        }
    };
    private TextWatcher r = new TextWatcher() { // from class: com.huawei.featureuserprofile.award.ui.WriteDeliveryInfoActivity.5
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int length = editable.toString().trim().length();
            WriteDeliveryInfoActivity.this.o = length;
            if (length == 0) {
                WriteDeliveryInfoActivity.this.e(false);
            } else {
                if (WriteDeliveryInfoActivity.this.k <= 0 || WriteDeliveryInfoActivity.this.j <= 0) {
                    return;
                }
                WriteDeliveryInfoActivity.this.e(true);
            }
        }
    };
    private TextWatcher c = new TextWatcher() { // from class: com.huawei.featureuserprofile.award.ui.WriteDeliveryInfoActivity.2
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int length = editable.toString().trim().length();
            WriteDeliveryInfoActivity.this.j = length;
            if (length == 0) {
                WriteDeliveryInfoActivity.this.e(false);
            } else {
                if (WriteDeliveryInfoActivity.this.k <= 0 || WriteDeliveryInfoActivity.this.o <= 0) {
                    return;
                }
                WriteDeliveryInfoActivity.this.e(true);
            }
        }
    };
    private View.OnClickListener m = new View.OnClickListener() { // from class: com.huawei.featureuserprofile.award.ui.WriteDeliveryInfoActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WriteDeliveryInfoActivity.this.showLoading();
            WriteDeliveryInfoActivity writeDeliveryInfoActivity = WriteDeliveryInfoActivity.this;
            writeDeliveryInfoActivity.y = writeDeliveryInfoActivity.g.getText().toString().trim();
            WriteDeliveryInfoActivity writeDeliveryInfoActivity2 = WriteDeliveryInfoActivity.this;
            writeDeliveryInfoActivity2.u = writeDeliveryInfoActivity2.f.getText().toString().trim();
            WriteDeliveryInfoActivity writeDeliveryInfoActivity3 = WriteDeliveryInfoActivity.this;
            writeDeliveryInfoActivity3.v = writeDeliveryInfoActivity3.i.getText().toString().trim();
            WriteDeliveryInfoActivity writeDeliveryInfoActivity4 = WriteDeliveryInfoActivity.this;
            writeDeliveryInfoActivity4.x = writeDeliveryInfoActivity4.aa.getText().toString().trim();
            if (TextUtils.isEmpty(WriteDeliveryInfoActivity.this.y) || TextUtils.isEmpty(WriteDeliveryInfoActivity.this.u) || TextUtils.isEmpty(WriteDeliveryInfoActivity.this.v)) {
                WriteDeliveryInfoActivity.this.e(false);
                LogUtil.b("WriteDeliveryInfoActivity award", "Required input data is empty");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            String format = new SimpleDateFormat("Z").format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
            if (WriteDeliveryInfoActivity.this.n == 1) {
                if (!TextUtils.isEmpty(WriteDeliveryInfoActivity.this.y) && !TextUtils.isEmpty(WriteDeliveryInfoActivity.this.u) && !TextUtils.isEmpty(WriteDeliveryInfoActivity.this.v)) {
                    WriteDeliveryInfoActivity.this.showLoading();
                    SaveInfoItem saveInfoItem = new SaveInfoItem();
                    saveInfoItem.setActivityId(WriteDeliveryInfoActivity.this.f1979a);
                    saveInfoItem.setAwardRecordId(WriteDeliveryInfoActivity.this.b);
                    saveInfoItem.setName(WriteDeliveryInfoActivity.this.y);
                    saveInfoItem.setTelephone(WriteDeliveryInfoActivity.this.u);
                    saveInfoItem.setAddress(WriteDeliveryInfoActivity.this.v);
                    saveInfoItem.setRemark(WriteDeliveryInfoActivity.this.x);
                    WriteDeliveryInfoActivity.this.w.saveInfo(WriteDeliveryInfoActivity.this.ad, saveInfoItem, format, WriteDeliveryInfoActivity.this.e);
                }
            } else if (WriteDeliveryInfoActivity.this.n == 2) {
                WriteDeliveryInfoActivity.this.e(format);
            } else {
                LogUtil.b("WriteDeliveryInfoActivity award", "data from type error");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    @Override // com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract.View
    public void hideLoading() {
    }

    @Override // com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract.View
    public void showLoading() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (c()) {
            if (TextUtils.isEmpty(this.y) || TextUtils.isEmpty(this.u) || TextUtils.isEmpty(this.v)) {
                return;
            }
            showLoading();
            SaveInfoItem saveInfoItem = new SaveInfoItem();
            saveInfoItem.setActivityId(this.f1979a);
            saveInfoItem.setAwardRecordId(this.b);
            saveInfoItem.setName(this.y);
            saveInfoItem.setTelephone(this.u);
            saveInfoItem.setAddress(this.v);
            saveInfoItem.setRemark(this.x);
            this.w.saveInfo(this.ad, saveInfoItem, str, this.e);
            return;
        }
        finish();
    }

    private boolean c() {
        return (this.y.equals(this.p) && this.u.equals(this.q) && this.v.equals(this.t) && this.x.equals(this.s)) ? false : true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("WriteDeliveryInfoActivity award", "onCreate()");
        setContentView(R.layout.activity_write_delivery_info);
        cancelAdaptRingRegion();
        this.h = this;
        b();
        e();
        this.w = new bqz(this);
    }

    @Override // com.huawei.featureuserprofile.award.BaseView
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void setPresenter(WriteDeliveryInfoContract.Presenter presenter) {
        this.w = presenter;
    }

    private void b() {
        this.g = (HealthEditText) findViewById(R.id.contact_name);
        this.f = (HealthEditText) findViewById(R.id.contact_phone);
        this.i = (HealthEditText) findViewById(R.id.contact_address);
        this.aa = (HealthEditText) findViewById(R.id.remark_info);
        this.ac = (HealthButton) findViewById(R.id.save_btn);
    }

    private void e() {
        if (getIntent() != null) {
            Serializable serializableExtra = getIntent().getSerializableExtra("address_info");
            if (serializableExtra instanceof AddressInfo) {
                this.d = (AddressInfo) serializableExtra;
            }
        }
        AddressInfo addressInfo = this.d;
        if (addressInfo != null && addressInfo.getItem() != null) {
            this.b = this.d.getItem().getAwardRecordId();
            this.f1979a = this.d.getItem().getActivityId();
            this.e = this.d.getClickPos();
            this.ad = this.d.getSource();
            if (!TextUtils.isEmpty(this.d.getItem().getName())) {
                this.n = 2;
                this.p = this.d.getItem().getName();
                this.q = this.d.getItem().getTelephone();
                this.t = this.d.getItem().getAddress();
                d();
                this.k = this.p.length();
                this.o = this.q.length();
                this.j = this.t.length();
                this.g.setText(this.p);
                this.f.setText(this.q);
                this.i.setText(this.t);
                this.aa.setText(this.s);
                e(true);
            } else {
                this.n = 1;
                this.p = "";
                this.q = "";
                this.t = "";
                this.s = "";
            }
        } else if (getIntent() != null) {
            this.n = 1;
            this.p = "";
            this.q = "";
            this.t = "";
            this.s = "";
            this.e = 0;
            this.b = getIntent().getStringExtra("awardRecordId");
            this.f1979a = getIntent().getStringExtra("activityId");
            this.ad = 20002;
        }
        a();
    }

    private void d() {
        if (!TextUtils.isEmpty(this.d.getItem().getRemark())) {
            this.s = this.d.getItem().getRemark();
        } else {
            this.s = "";
        }
    }

    private void a() {
        this.g.addTextChangedListener(this.l);
        this.f.addTextChangedListener(this.r);
        this.i.addTextChangedListener(this.c);
        this.ac.setOnClickListener(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        if (z) {
            this.ac.setEnabled(true);
        } else {
            this.ac.setEnabled(false);
        }
    }

    @Override // com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract.View
    public void onModifyDataSuccess(String str) {
        AddressResp addressResp;
        hideLoading();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WriteDeliveryInfoActivity award", "result is empty");
            return;
        }
        try {
            addressResp = (AddressResp) new Gson().fromJson(str, AddressResp.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("WriteDeliveryInfoActivity award", "JsonSyntaxException");
            addressResp = null;
        }
        if (addressResp != null) {
            int resultCode = addressResp.getResultCode();
            LogUtil.a("WriteDeliveryInfoActivity award", "resultCode: ", Integer.valueOf(resultCode), ", resultDesc: ", addressResp.getResultDesc());
            if (resultCode == 0) {
                nrh.b(this, R.string._2130842374_res_0x7f021306);
                Intent intent = new Intent();
                intent.putExtra("mail_name", this.y);
                intent.putExtra("mail_phone", this.u);
                intent.putExtra("mail_address", this.v);
                intent.putExtra("mail_remark", this.x);
                intent.putExtra("click_pos", this.e);
                setResult(-1, intent);
                finish();
                return;
            }
            nrh.b(this, R.string._2130851113_res_0x7f023529);
        }
    }

    @Override // com.huawei.featureuserprofile.award.presenter.WriteDeliveryInfoContract.View
    public void onModifyDataFailed(String str) {
        hideLoading();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
