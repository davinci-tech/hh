package com.huawei.appgallery.agd.api;

import android.content.Intent;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes8.dex */
public class StartAbilityResult extends BaseServiceResult {

    /* renamed from: a, reason: collision with root package name */
    private String f1864a;
    private int d;
    private String j;

    interface StartResultCode {
        public static final int ABILITY_START_FAILED = 1;
        public static final int ABILITY_START_INVALID = -1;
        public static final int ABILITY_START_SUCCESS = 0;
    }

    public String toString() {
        return "StartAbilityResult{loadResult=" + this.e + ", startResult=" + this.d + ", bundleName='" + this.c + "', moduleNameName='" + this.f1864a + "', abilityNameName='" + this.j + "', callerContext='" + this.b + "'}";
    }

    public String getStartResultDesc() {
        int i = this.d;
        if (i == -1) {
            return "ABILITY_START_INVALID";
        }
        if (i == 0) {
            return "ABILITY_START_SUCCESS";
        }
        if (i == 1) {
            return "ABILITY_START_FAILED";
        }
        return "unknown result " + this.d;
    }

    public int getStartResult() {
        return this.d;
    }

    public String getModuleName() {
        return this.f1864a;
    }

    public String getAbilityName() {
        return this.j;
    }

    public static StartAbilityResult from(Intent intent) {
        if (intent == null) {
            return null;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        StartAbilityResult startAbilityResult = new StartAbilityResult();
        startAbilityResult.e = safeIntent.getIntExtra("loadResultCode", -1);
        startAbilityResult.d = safeIntent.getIntExtra("startResultCode", -1);
        startAbilityResult.c = safeIntent.getStringExtra("bundleName");
        startAbilityResult.f1864a = safeIntent.getStringExtra("moduleName");
        startAbilityResult.j = safeIntent.getStringExtra("abilityName");
        startAbilityResult.b = safeIntent.getStringExtra("callerContext");
        return startAbilityResult;
    }

    private StartAbilityResult() {
    }
}
