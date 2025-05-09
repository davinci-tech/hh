package com.huawei.appgallery.agd.api;

import android.content.Intent;
import com.huawei.appmarket.service.externalservice.distribution.opendetail.ModuleInfo;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.util.List;

/* loaded from: classes2.dex */
public class OpenDetailResult extends BaseServiceResult {

    /* renamed from: a, reason: collision with root package name */
    private int f1862a;
    private List<ModuleInfo> d;

    /* loaded from: classes8.dex */
    interface DisplayResultCode {
        public static final int FA_DISPLAY_FAILED = 1;
        public static final int FA_DISPLAY_INVALID = -1;
        public static final int FA_DISPLAY_SUCCESS = 0;
        public static final int FA_FILTER_EMPTY = 2;
    }

    public String toString() {
        return "OpenDetailResult{loadResult=" + this.e + ", displayResult=" + this.f1862a + ", bundleName='" + this.c + "', callerContext='" + this.b + "', addToDeskModuleInfo=" + this.d + '}';
    }

    public List<ModuleInfo> getModules() {
        return this.d;
    }

    public String getDisplayResultDesc() {
        int i = this.f1862a;
        if (i == -1) {
            return "FA_DISPLAY_INVALID";
        }
        if (i == 0) {
            return "FA_DISPLAY_SUCCESS";
        }
        if (i == 1) {
            return "FA_DISPLAY_FAILED";
        }
        if (i == 2) {
            return "FA_FILTER_EMPTY";
        }
        return "unknown result " + this.f1862a;
    }

    public int getDisplayResult() {
        return this.f1862a;
    }

    public static OpenDetailResult from(Intent intent) {
        if (intent == null) {
            return null;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        OpenDetailResult openDetailResult = new OpenDetailResult();
        if (safeIntent.hasExtra("result")) {
            d(safeIntent, openDetailResult);
        } else {
            e(safeIntent, openDetailResult);
        }
        openDetailResult.c = safeIntent.getStringExtra("bundleName");
        openDetailResult.b = safeIntent.getStringExtra("callerContext");
        return openDetailResult;
    }

    private static void d(SafeIntent safeIntent, OpenDetailResult openDetailResult) {
        int i;
        openDetailResult.e = safeIntent.getIntExtra("result", -1);
        int i2 = openDetailResult.e;
        if (i2 == 100) {
            i = 0;
        } else {
            if (i2 != 101) {
                openDetailResult.f1862a = -1;
                openDetailResult.d = safeIntent.getParcelableArrayListExtra("module_list");
            }
            i = 1;
        }
        openDetailResult.f1862a = i;
        openDetailResult.d = safeIntent.getParcelableArrayListExtra("module_list");
    }

    private static void e(SafeIntent safeIntent, OpenDetailResult openDetailResult) {
        openDetailResult.e = safeIntent.getIntExtra("loadResultCode", -1);
        openDetailResult.f1862a = safeIntent.getIntExtra("displayResultCode", -1);
        openDetailResult.d = safeIntent.getParcelableArrayListExtra("moduleList");
    }

    private OpenDetailResult() {
    }
}
