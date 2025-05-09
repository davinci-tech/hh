package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.dataswitch.CoreSleepSwitch;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthStatReq;
import com.huawei.hwcloudmodel.model.unite.ProfessionalSleepTotal;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class itn {
    private static Context d;

    /* renamed from: a, reason: collision with root package name */
    private iis f13603a;
    private boolean b;
    private jbs c;
    private int e;
    private int j;

    private itn() {
        this.e = 0;
        this.j = 0;
        this.b = false;
        this.f13603a = iis.d();
        this.c = jbs.a(d);
    }

    static class d {
        private static final itn d = new itn();
    }

    public static itn b(Context context) {
        d = context.getApplicationContext();
        return d.d;
    }

    public void c(int i, HiSyncOption hiSyncOption, CoreSleepSwitch coreSleepSwitch) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncSleepStat", "pushData() begin!");
        this.e = 0;
        this.j = 0;
        this.b = iuz.i();
        if (!ism.m()) {
            ReleaseLogUtil.d("HiH_HiSyncSleepStat", "pushData() healthDataPrivacy switch is closed, push end!");
            return;
        }
        int h = this.f13603a.h(i);
        if (h <= 0) {
            LogUtil.h("HiH_HiSyncSleepStat", "pushData() no statClient get, maybe no data need to push ,push end !");
        } else {
            if (this.b) {
                this.j = 0;
            }
            d(h, hiSyncOption, coreSleepSwitch);
        }
        ReleaseLogUtil.e("HiH_HiSyncSleepStat", "pushData() end !");
    }

    private void d(int i, HiSyncOption hiSyncOption, CoreSleepSwitch coreSleepSwitch) throws iut {
        List<HiHealthData> d2;
        while (this.e < 2 && (d2 = d(i)) != null && !d2.isEmpty()) {
            if (c(d2, hiSyncOption, coreSleepSwitch)) {
                iuz.d(d, d2, HiHealthDataType.a(), i);
            }
        }
        this.e = 0;
    }

    private List<HiHealthData> d(int i) {
        int[] iArr = new int[2];
        if (!this.b) {
            iArr[0] = 50;
            iArr[1] = 0;
            return iuz.e(d, i, HiHealthDataType.a(), HiHealthDataKey.b(), iArr);
        }
        iArr[0] = 50;
        iArr[1] = 1;
        return iuz.e(d, i, HiHealthDataType.a(), HiHealthDataKey.b(), iArr);
    }

    private boolean c(List<HiHealthData> list, HiSyncOption hiSyncOption, CoreSleepSwitch coreSleepSwitch) throws iut {
        if (!this.b) {
            int i = this.j + 1;
            this.j = i;
            iuz.e(i, hiSyncOption.getSyncManual());
        } else {
            int i2 = this.j + 1;
            this.j = i2;
            if (3 < i2) {
                this.e += 2;
                return false;
            }
        }
        List<ProfessionalSleepTotal> d2 = coreSleepSwitch.d(list);
        if (d2.isEmpty()) {
            this.e++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setProfessionalSleepTotal(d2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (this.e < 2) {
            if (!ius.a(this.c.d(addHealthStatReq), false)) {
                this.e++;
            } else {
                ReleaseLogUtil.e("HiH_HiSyncSleepStat", "addCoreSleepStat success, mUploadCount is ,", Integer.valueOf(this.j));
                LogUtil.a("HiH_HiSyncSleepStat", ",stat is ", HiJsonUtil.e(d2));
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncSleepStat", "addCoreSleepStat failed, mUploadCount is ,", Integer.valueOf(this.j));
        LogUtil.a("HiH_HiSyncSleepStat", "stat is ", HiJsonUtil.e(d2));
        return false;
    }

    public List<Integer> b(int i) {
        return iuz.a(d, this.f13603a.h(i));
    }
}
