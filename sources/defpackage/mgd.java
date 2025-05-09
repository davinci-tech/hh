package defpackage;

import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mgd extends BaseCalculator {
    private PluginAchieveAdapter e = getPluginAchieveAdapter();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        a(i);
    }

    private void a(int i) {
        if (this.e == null) {
            this.e = mcv.d(BaseApplication.getContext()).getAdapter();
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList arrayList = new ArrayList(16);
        e(i, countDownLatch, arrayList);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_DietRecordCalculator", "getDietRecordData.await(): catch InterruptedException");
        }
        int b = b(arrayList);
        LogUtil.a("PLGACHIEVE_DietRecordCalculator", "getDietRecordDays dietDays = ", Integer.valueOf(b));
        insertData(i, EnumAnnualType.REPORT_DIET.value(), 10016, String.valueOf(b));
    }

    private int b(List<HiHealthData> list) {
        quh quhVar;
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            try {
                quhVar = (quh) new Gson().fromJson(it.next().getMetaData(), quh.class);
            } catch (JsonSyntaxException | NumberFormatException e) {
                LogUtil.b("PLGACHIEVE_DietRecordCalculator", "parse dietRecord fail ", e.getMessage());
                quhVar = null;
            }
            if (quhVar != null && !koq.b(quhVar.a())) {
                String e2 = e(quhVar.f());
                if (!arrayList.contains(e2)) {
                    arrayList.add(e2);
                    i++;
                }
            }
        }
        LogUtil.a("PLGACHIEVE_DietRecordCalculator", "getDietRecordDays dietDays = ", arrayList.toString());
        return i;
    }

    private void e(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        int[] iArr = {DicDataTypeUtil.DataType.DIET_RECORD.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(mht.b(i, true));
        hiDataReadOption.setEndTime(mht.b(i, false));
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: mgd.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() > 0) {
                        if (!(sparseArray.get(DicDataTypeUtil.DataType.DIET_RECORD.value()) instanceof List)) {
                            LogUtil.h("PLGACHIEVE_DietRecordCalculator", "getTotalRecordData datas not instanceof list");
                            countDownLatch.countDown();
                            return;
                        } else {
                            List list2 = (List) sparseArray.get(DicDataTypeUtil.DataType.DIET_RECORD.value());
                            LogUtil.a("PLGACHIEVE_DietRecordCalculator", "getTotalRecordData size ", Integer.valueOf(list2.size()));
                            list.addAll(list2);
                            countDownLatch.countDown();
                            return;
                        }
                    }
                }
                Object[] objArr = new Object[2];
                objArr[0] = "getTotalRecordData no data ";
                objArr[1] = Boolean.valueOf(obj == null);
                LogUtil.h("PLGACHIEVE_DietRecordCalculator", objArr);
                countDownLatch.countDown();
            }
        });
    }

    private String e(long j) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(j));
    }
}
