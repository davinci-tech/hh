package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.PlanPoster;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import health.compact.a.LogAnonymous;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* loaded from: classes4.dex */
public class ghy {
    public static String c(FitnessPackageInfo fitnessPackageInfo, String str) {
        StringBuilder sb = new StringBuilder("#/PrivilegeDetail");
        if (fitnessPackageInfo == null || fitnessPackageInfo.getPlanPoster() == null) {
            return sb.toString();
        }
        sb.append("?");
        if (!TextUtils.isEmpty(fitnessPackageInfo.acquireName())) {
            sb.append("title=");
            sb.append(fitnessPackageInfo.acquireName());
            sb.append("&");
        }
        PlanPoster planPoster = fitnessPackageInfo.getPlanPoster();
        if (planPoster != null) {
            if (koq.d(planPoster.getPicturesForPhone(), 0) && !TextUtils.isEmpty(planPoster.getPicturesForPhone().get(0))) {
                sb.append("detailImgUrl=");
                sb.append(c(planPoster.getPicturesForPhone().get(0)));
                sb.append("&");
            }
            if (koq.d(planPoster.getPicturesForPad(), 0) && !TextUtils.isEmpty(planPoster.getPicturesForPad().get(0))) {
                sb.append("detailBigImgUrl=");
                sb.append(c(planPoster.getPicturesForPad().get(0)));
                sb.append("&");
            }
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append("nextPage=");
            sb.append(c(String.format(Locale.ENGLISH, "huaweischeme://healthapp/fitnesspage?skip_type=goto_plan&id=%d&planTempId=%s", Integer.valueOf(IntPlan.PlanType.FIT_PLAN.getType()), str)));
        }
        return sb.toString();
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            LogUtil.h("OperationActivity_VipBenefitPathUtil", "getUrlEncode error= ", LogAnonymous.b((Throwable) e));
            return "";
        }
    }
}
