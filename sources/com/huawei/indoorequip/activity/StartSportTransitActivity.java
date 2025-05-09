package com.huawei.indoorequip.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.gnm;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class StartSportTransitActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f6427a;
    private static String b;
    private static String c;
    private static List<String> d;
    private static String e;
    private static String i;
    private boolean g = false;
    private Context j;

    static {
        ArrayList arrayList = new ArrayList();
        d = arrayList;
        arrayList.add("290");
        d.add("291");
        ArrayList arrayList2 = new ArrayList();
        f6427a = arrayList2;
        arrayList2.add("261");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("StartSportTransitActivity", "enter onCreate");
        this.j = this;
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("startSportParam");
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            c(stringExtra);
            if (d()) {
                d(this.j, c());
                return;
            }
            return;
        }
        LogUtil.h("StartSportTransitActivity", "StartSportTransitActivity intent == null");
    }

    @Override // android.app.Activity
    protected void onResume() {
        LogUtil.a("StartSportTransitActivity", "onResume then finish self");
        super.onResume();
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        LogUtil.a("StartSportTransitActivity", "onDestroy");
        super.onDestroy();
    }

    private void c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.get("ftmp").toString();
            b = jSONObject.getString("deviceType");
            c = jSONObject.getString("macAddress");
            e = jSONObject.getString(BleConstants.SPORT_TYPE);
            this.g = jSONObject.optBoolean("ExitApp");
        } catch (JSONException e2) {
            LogUtil.b("StartSportTransitActivity", "JSONException ", e2.getMessage());
        }
    }

    private boolean d() {
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("StartSportTransitActivity", "mMacAddress or  mDeviceType is empty");
            return false;
        }
        if (d.contains(e) && f6427a.contains(b)) {
            return true;
        }
        LogUtil.a("StartSportTransitActivity", "mDeviceType or mSportType is error, mDeviceType = ", b, "mSportType = ", e);
        return false;
    }

    private String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("ftmp=");
        sb.append(i);
        sb.append("&t=");
        sb.append(b);
        sb.append("&ble=");
        sb.append(c);
        sb.append("&stype=");
        sb.append(e);
        return sb.toString();
    }

    private void d(Context context, String str) {
        if (context == null) {
            LogUtil.a("StartSportTransitActivity", "startIndoorEquipConnectedActivity context is null");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("PAYLOAD_FROM_NFC", str);
        intent.putExtra("ExitApp", this.g);
        if (this.g) {
            intent.setFlags(335577088);
        }
        intent.setClass(context, IndoorEquipConnectedActivity.class);
        gnm.aPB_(context, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
