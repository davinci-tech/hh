package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.operation.utils.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class AnnualReportAdaptation {
    public Pattern b;
    public boolean d = false;

    public boolean isAnnualMedalResourcesPath(H5ProAppInfo h5ProAppInfo, String str, String str2, Context context) {
        if (TextUtils.isEmpty(str2) || !(str2.startsWith(Constants.ANNUAL_MEDAL_PATH_PREFIX_COMMON) || str2.startsWith(Constants.ANNUAL_MEDAL_PATH_PREFIX))) {
            this.d = false;
            return false;
        }
        if (!H5ProTrustListChecker.isTrusted(h5ProAppInfo, str)) {
            this.d = false;
            return false;
        }
        e(context);
        boolean find = this.b.matcher(c(str2)).find();
        this.d = find;
        return find;
    }

    public WebResourceResponse getAnnualMedalResourceResponse(String str) {
        String c;
        if (!this.d) {
            return null;
        }
        try {
            c = c(str);
        } catch (FileNotFoundException e) {
            LogUtil.w("H5PRO_AnnualReportAdaptation", "getAnnualMedalResourceResponse: " + e.getMessage());
        }
        if (GeneralUtil.isSafePath(c)) {
            return new WebResourceResponse("image/png", StandardCharsets.UTF_8.name(), new FileInputStream(c));
        }
        LogUtil.w("H5PRO_AnnualReportAdaptation", "getAnnualMedalResourceResponse: invalid path");
        return null;
    }

    private String c(String str) {
        return TextUtils.isEmpty(str) ? str : GeneralUtil.getFileSafePath(str.replace(Constants.ANNUAL_MEDAL_PATH_PREFIX_COMMON, "").replace(Constants.ANNUAL_MEDAL_PATH_PREFIX, ""));
    }

    private void e(Context context) {
        if (this.b == null) {
            this.b = Pattern.compile("^(/data/)((data)|(user/[0-9]))/" + context.getPackageName() + "/files/((achievemedalpng)|(achievemedal))/");
        }
    }
}
