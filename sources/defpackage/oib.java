package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public final class oib {
    public static void d(Context context, final int[] iArr, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        if (context == null || iArr == null) {
            iBaseResponseCallback.d(7, null);
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: oib.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e("FunctionSet_CardManagerHelper", "errorCode = ", Integer.valueOf(i), " input types:", Arrays.toString(iArr));
                ReleaseLogUtil.e("FunctionSet_TIME", "query all data times:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                List daT_ = oib.daT_(oib.daS_(obj, i));
                ReleaseLogUtil.e("FunctionSet_CardManagerHelper", "has data card type size:", Integer.valueOf(daT_.size()));
                iBaseResponseCallback.d(i, daT_);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                ReleaseLogUtil.d("FunctionSet_CardManagerHelper", "onResultIntent");
                iBaseResponseCallback.d(7, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SparseArray<Object> daS_(Object obj, int i) {
        ReleaseLogUtil.e("FunctionSet_CardManagerHelper", "result = ", Integer.valueOf(i));
        if (i != 0 || !(obj instanceof SparseArray)) {
            ReleaseLogUtil.c("FunctionSet_CardManagerHelper", "checkResult result != SUCCESS && data not instanceof SparseArray");
            return null;
        }
        return (SparseArray) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Integer> daT_(SparseArray sparseArray) {
        if (sparseArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(Integer.valueOf(sparseArray.keyAt(i)));
        }
        return arrayList;
    }

    public static int[] c(Collection<Integer> collection) {
        int i = 0;
        if (koq.b(collection)) {
            return new int[0];
        }
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    public static boolean d() {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit == null) {
            ReleaseLogUtil.d("FunctionSet_CardManagerHelper", "queryPhysiologicalCycleShow network error");
            return false;
        }
        String gender = loginInit.getGender();
        String birthDate = loginInit.getBirthDate();
        LogUtil.a("FunctionSet_CardManagerHelper", "gender: ", gender, ", birthDate: ", birthDate);
        if (gender == null || birthDate == null) {
            ReleaseLogUtil.d("FunctionSet_CardManagerHelper", "queryPhysiologicalCycleShow gender or birthDate is null");
            return false;
        }
        try {
            boolean equals = "1".equals(gender);
            int c = (HiDateUtil.c(System.currentTimeMillis()) / 10000) - (Integer.parseInt(birthDate) / 10000);
            boolean z = c <= 55 && c >= 16;
            Object[] objArr = new Object[6];
            objArr[0] = "gender: ";
            objArr[1] = gender;
            objArr[2] = ", age: ";
            objArr[3] = Integer.valueOf(c);
            objArr[4] = ", isShow: ";
            objArr[5] = Boolean.valueOf(equals && z);
            LogUtil.a("FunctionSet_CardManagerHelper", objArr);
            return equals && z;
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("FunctionSet_CardManagerHelper", "queryPhysiologicalCycleShow NumberFormatException");
            return false;
        }
    }

    public static int c() {
        if (CommonUtil.bu()) {
            return 32768;
        }
        if (CommonUtil.aq()) {
            return 4;
        }
        return CommonUtil.as() ? 2 : 1;
    }

    public static boolean c(String str, String str2) {
        String num = Integer.toString(1);
        String num2 = Integer.toString(2);
        return (str.equals(num) || str.equals(num2) || str2.equals(num) || str2.equals(num2)) ? false : true;
    }

    public static List<String> b(List<CardConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (CardConfig cardConfig : list) {
            if (cardConfig != null) {
                arrayList.add(cardConfig.getCardId());
            }
        }
        return arrayList;
    }
}
