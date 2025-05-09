package com.huawei.hwsmartinteractmgr.smarter;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import defpackage.kor;
import defpackage.kvs;
import defpackage.kvx;
import defpackage.kwn;
import health.compact.a.SharedPreferenceManager;
import java.util.Date;
import java.util.List;

/* loaded from: classes5.dex */
public class BloodPressureSmarter extends BaseSmarter {

    /* renamed from: a, reason: collision with root package name */
    private kvs f6402a;
    private kvx d;

    public BloodPressureSmarter(Context context) {
        super(context);
        this.d = kvx.c(this.e);
        this.f6402a = kvs.b(this.e);
    }

    @Override // com.huawei.hwsmartinteractmgr.smarter.BaseSmarter
    public void d() {
        LogUtil.a("SMART_BloodPressSmarter", "startTimerCheck");
        super.d();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        boolean z;
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(30001);
        smartMsgDbObject.setMsgSrc(3);
        d(smartMsgDbObject, str, i, SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setMessagePriority(kwn.d(30002, "ai-bloodp-002"));
        SmartMsgDbObject a2 = this.f6402a.a(30001);
        if (a2 == null) {
            z = this.f6402a.a(smartMsgDbObject);
        } else {
            long updateTime = a2.getUpdateTime();
            LogUtil.a("SMART_BloodPressSmarter", "setMeasureMsg createTime = ", new Date(updateTime));
            if (System.currentTimeMillis() - updateTime > i * 86400000) {
                this.f6402a.d(30001);
                z = this.f6402a.a(smartMsgDbObject);
            } else {
                z = false;
            }
        }
        LogUtil.a("SMART_BloodPressSmarter", "setMeasureMsg isInserted = ", Boolean.valueOf(z));
    }

    private void c() {
        final int i;
        LogUtil.a("SMART_BloodPressSmarter", "setOrDeleteMeasureMsg");
        boolean b = this.d.b(6);
        String b2 = SharedPreferenceManager.b(this.e, Integer.toString(10021), "telled_user_measure_blood_pressure");
        boolean c = kwn.c(30002, "ai-bloodp-002");
        LogUtil.c("SMART_BloodPressSmarter", "setOrDeleteMeasureMsg isRuleOpen = ", Boolean.valueOf(c), "isFollowBloodPress = ", Boolean.valueOf(b));
        if (c && !"1".equals(b2)) {
            if (e(this.e)) {
                String b3 = kwn.b(30002, "ai-bloodp-002", "recently_num_days_no_data");
                String b4 = kwn.b(30002, "ai-bloodp-002", "recommended_time");
                LogUtil.a("SMART_BloodPressSmarter", "setOrDeleteMeasureMsg recommendTime = ", b4);
                final String d = d(b4);
                try {
                    i = Integer.parseInt(b3);
                } catch (NumberFormatException e) {
                    LogUtil.b("SMART_BloodPressSmarter", "setOrDeleteMeasureMsg NumberFormatException = ", e.getMessage());
                    i = 0;
                }
                b(i, new CommonUiBaseResponse() { // from class: com.huawei.hwsmartinteractmgr.smarter.BloodPressureSmarter.4
                    @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                    public void onResponse(int i2, Object obj) {
                        LogUtil.a("SMART_BloodPressSmarter", "setOrDeleteMeasureMsg hasBloodPressureData errCode = ", Integer.valueOf(i2));
                        if (i2 == 100001) {
                            BloodPressureSmarter.this.e(i, d);
                        } else {
                            kwn.c(BloodPressureSmarter.this.e, 30001, 3);
                        }
                    }
                });
                return;
            }
            kwn.c(this.e, 30001, 3);
            return;
        }
        kwn.c(this.e, 30001, 3);
    }

    private void b(int i, final CommonUiBaseResponse commonUiBaseResponse) {
        long currentTimeMillis = System.currentTimeMillis();
        kor.a().a(currentTimeMillis - (i * 86400000), currentTimeMillis, 0, new IBaseResponseCallback() { // from class: com.huawei.hwsmartinteractmgr.smarter.BloodPressureSmarter.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (obj instanceof List) {
                    if (((List) obj).size() <= 0) {
                        commonUiBaseResponse.onResponse(100001, null);
                        return;
                    } else {
                        commonUiBaseResponse.onResponse(0, null);
                        return;
                    }
                }
                commonUiBaseResponse.onResponse(100001, null);
            }
        });
    }

    private static boolean e(Context context) {
        return context != null && kwn.b(context, "HDK_BLOOD_PRESSURE") > 0;
    }
}
