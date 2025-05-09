package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.main.R$string;
import defpackage.fdp;
import defpackage.nrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SleepTabSwitchProvider extends MinorProvider<fdp> {
    private long b;
    private boolean c = false;
    private boolean e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepTabSwitchProvider", "parseParams");
        if (fdpVar == null) {
            return;
        }
        e(context, hashMap, fdpVar);
    }

    private void e(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        boolean booleanValue = (fdpVar.l().containsKey("OPEN_STATUS") && (fdpVar.l().get("OPEN_STATUS") instanceof Boolean)) ? ((Boolean) fdpVar.l().get("OPEN_STATUS")).booleanValue() : false;
        LogUtil.a("SleepTabSwitchProvider", "getTimePeriodType: ", fdpVar.e());
        hashMap.put("LEFT_ICON_TEXT", context.getString(R$string.IDS_sleep_analysis_suggestions));
        hashMap.put("RIGHT_ICON_TEXT", context.getString(R$string.IDS_clear_sleep_music_data_cache_new));
        hashMap.put("ITEM_TAG", booleanValue ? ContextCompat.getDrawable(BaseApplication.e(), R.drawable._2131430560_res_0x7f0b0ca0) : null);
        hashMap.put("ITEM_RIGHT_BTN", Integer.valueOf(a(booleanValue, fdpVar)));
        hashMap.put("RIGHT_ICON_IMAGE", c(booleanValue));
        hashMap.put("ITEM_STATUS_BACKGROUND", e(booleanValue));
        this.c = booleanValue;
    }

    private List<Integer> c(boolean z) {
        ArrayList arrayList = new ArrayList();
        Integer valueOf = Integer.valueOf(R.drawable._2131431514_res_0x7f0b105a);
        int i = R.drawable._2131431515_res_0x7f0b105b;
        if (z) {
            arrayList.add(valueOf);
            arrayList.add(Integer.valueOf(R.drawable._2131431515_res_0x7f0b105b));
        } else {
            arrayList.add(valueOf);
            if (!nrt.a(BaseApplication.e())) {
                i = R.drawable._2131431513_res_0x7f0b1059;
            }
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    private List<Integer> e(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(Integer.valueOf(R.color._2131299109_res_0x7f090b25));
            arrayList.add(Integer.valueOf(R.color._2131296865_res_0x7f090261));
        } else {
            arrayList.add(Integer.valueOf(R.color._2131299103_res_0x7f090b1f));
            arrayList.add(Integer.valueOf(R.color._2131299106_res_0x7f090b22));
        }
        return arrayList;
    }

    private int a(boolean z, fdp fdpVar) {
        if (!this.e) {
            this.e = true;
            if (fdpVar != null && fdpVar.g() != 0) {
                this.b = fdpVar.g();
            }
            return e(z, fdpVar);
        }
        return b(z, fdpVar);
    }

    private int e(boolean z, fdp fdpVar) {
        Object[] objArr = new Object[4];
        objArr[0] = "hasData: ";
        objArr[1] = fdpVar == null ? Constants.NULL : Boolean.valueOf(fdpVar.m());
        objArr[2] = ", currentOpenState: ";
        objArr[3] = Boolean.valueOf(z);
        LogUtil.a("SleepTabSwitchProvider", objArr);
        return 1;
    }

    private int b(boolean z, fdp fdpVar) {
        boolean z2 = this.c;
        boolean z3 = z2 != z;
        Object[] objArr = new Object[6];
        objArr[0] = "mLastIsOpenSleepManagement: ";
        objArr[1] = Boolean.valueOf(z2);
        objArr[2] = ", currentOpenState: ";
        objArr[3] = Boolean.valueOf(z);
        objArr[4] = ", hasData: ";
        objArr[5] = fdpVar == null ? Constants.NULL : Boolean.valueOf(fdpVar.m());
        LogUtil.a("SleepTabSwitchProvider", objArr);
        if (z3) {
            return c(z, fdpVar);
        }
        return d(z, fdpVar);
    }

    private int c(boolean z, fdp fdpVar) {
        boolean z2 = fdpVar != null && fdpVar.m();
        LogUtil.a("SleepTabSwitchProvider", "currentOpenState: ", Boolean.valueOf(z), ", hasData: ", Boolean.valueOf(z2));
        return (z || z2) ? 1 : 2;
    }

    private int d(boolean z, fdp fdpVar) {
        if (fdpVar == null || fdpVar.g() == 0) {
            LogUtil.a("SleepTabSwitchProvider", "sleepData is null");
            return 3;
        }
        if (fdpVar.g() == this.b) {
            LogUtil.a("SleepTabSwitchProvider", "same day");
            return 3;
        }
        this.b = fdpVar.g();
        return c(z, fdpVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        this.e = false;
    }
}
