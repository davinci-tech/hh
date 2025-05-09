package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class deq extends HealthData {
    private static int[] d = {a.C, 417, 536, 9088};
    private static int[] b = {150, 358, 536, 9088};
    private static int[] e = {180, 417, 536, 9088};

    public static int b(Context context, int i) {
        int[] iArr;
        if (context == null || i < 0 || i > 9088) {
            return 1100;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        int genderOrDefaultValue = userInfo != null ? userInfo.getGenderOrDefaultValue() : -1;
        if (genderOrDefaultValue == 0) {
            iArr = d;
        } else if (genderOrDefaultValue == 1) {
            iArr = b;
        } else {
            iArr = e;
        }
        return a(i, iArr);
    }

    private static int a(int i, int[] iArr) {
        if (i < iArr[1]) {
            return 1101;
        }
        if (i < iArr[2]) {
            return 1102;
        }
        if (i < iArr[3]) {
            return 1103;
        }
        if (i < iArr[4]) {
            return ExceptionCode.CHECK_FILE_HASH_FAILED;
        }
        return 1100;
    }

    public static void d(HealthTextView healthTextView, int i) {
        if (healthTextView == null) {
            LogUtil.h("UricAcid", "setUricAcidLevelTextAndColor, valueLevelView is null!");
        }
        Resources resources = BaseApplication.getContext().getResources();
        switch (i) {
            case 1101:
                healthTextView.setText(resources.getString(R.string._2130843960_res_0x7f021938));
                healthTextView.setTextColor(resources.getColor(R.color._2131296903_res_0x7f090287));
                break;
            case 1102:
                healthTextView.setText(resources.getString(R.string._2130843959_res_0x7f021937));
                healthTextView.setTextColor(resources.getColor(R.color._2131296904_res_0x7f090288));
                break;
            case 1103:
                healthTextView.setText(resources.getString(R.string._2130843961_res_0x7f021939));
                healthTextView.setTextColor(resources.getColor(R.color._2131296902_res_0x7f090286));
                break;
            case ExceptionCode.CHECK_FILE_HASH_FAILED /* 1104 */:
                healthTextView.setText(resources.getString(R.string._2130843966_res_0x7f02193e));
                healthTextView.setTextColor(resources.getColor(R.color._2131296902_res_0x7f090286));
                break;
        }
    }
}
