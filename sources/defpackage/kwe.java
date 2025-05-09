package defpackage;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.smarter.BaseSmarter;
import com.huawei.hwsmartinteractmgr.smarter.BloodPressureSmarter;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import java.util.Date;
import java.util.List;

/* loaded from: classes5.dex */
public class kwe extends BaseSmarter {
    private kvs c;

    private kwe() {
        super(BaseApplication.getContext());
        this.c = kvs.b(this.e);
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final kwe f14657a = new kwe();
    }

    public static kwe e() {
        return d.f14657a;
    }

    @Override // com.huawei.hwsmartinteractmgr.smarter.BaseSmarter
    public void d() {
        super.d();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        boolean z;
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(SmartMsgConstant.MSG_TYPE_TOLD_USER_MEASURE_BLOOD_SUGAR);
        smartMsgDbObject.setMsgSrc(7);
        d(smartMsgDbObject, str, i, SmartMsgConstant.SHOW_METHOD_SMART_CARD);
        smartMsgDbObject.setMessagePriority(kwn.d(30006, "ai-bloodsg-002"));
        SmartMsgDbObject a2 = this.c.a(SmartMsgConstant.MSG_TYPE_TOLD_USER_MEASURE_BLOOD_SUGAR);
        if (a2 == null) {
            z = this.c.a(smartMsgDbObject);
        } else {
            long updateTime = a2.getUpdateTime();
            LogUtil.a("SMART_BloodSugarSmarter", "setMeasureMsg createTime = ", new Date(updateTime));
            if (System.currentTimeMillis() - updateTime > i * 86400000) {
                this.c.d(SmartMsgConstant.MSG_TYPE_TOLD_USER_MEASURE_BLOOD_SUGAR);
                z = this.c.a(smartMsgDbObject);
            } else {
                z = false;
            }
        }
        LogUtil.a("SMART_BloodSugarSmarter", "setMeasureMsg isInserted = ", Boolean.valueOf(z));
    }

    public void b() {
        boolean c = kwn.c(30006, "ai-bloodsg-002");
        LogUtil.a("SMART_BloodSugarSmarter", "setOrDeleteMeasureMsg isRuleOpen = ", Boolean.valueOf(c));
        final int i = 3;
        if (c && c(this.e)) {
            String b = kwn.b(30006, "ai-bloodsg-002", "recently_num_days_no_data");
            String b2 = kwn.b(30006, "ai-bloodsg-002", "recommended_time");
            LogUtil.a("SMART_BloodSugarSmarter", "setOrDeleteMeasureMsg recommendTime = ", b2, "dayStr", b);
            final String d2 = BloodPressureSmarter.d(b2);
            try {
                i = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                LogUtil.b("SMART_BloodSugarSmarter", "setOrDeleteMeasureMsg NumberFormatException exception = ", e.getMessage());
            }
            LogUtil.a("SMART_BloodSugarSmarter", "setOrDeleteMeasureMsg day = ", Integer.valueOf(i));
            c(this.e, i, new CommonUiBaseResponse() { // from class: kwe.1
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i2, Object obj) {
                    LogUtil.a("SMART_BloodSugarSmarter", "setOrDeleteMeasureMsg hasBloodSugarData errCode = ", Integer.valueOf(i2));
                    if (i2 == 100001) {
                        kwe.this.e(i, d2);
                    } else {
                        kwn.c(kwe.this.e, SmartMsgConstant.MSG_TYPE_TOLD_USER_MEASURE_BLOOD_SUGAR, 3);
                    }
                }
            });
            return;
        }
        kwn.c(this.e, SmartMsgConstant.MSG_TYPE_TOLD_USER_MEASURE_BLOOD_SUGAR, 3);
    }

    private void c(Context context, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        long currentTimeMillis = System.currentTimeMillis();
        kor.a().d(context, currentTimeMillis - (i * 86400000), currentTimeMillis, 1, new IBaseResponseCallback() { // from class: kwe.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (obj instanceof List) {
                    if (((List) obj).isEmpty()) {
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

    private static boolean c(Context context) {
        return kwn.b(context, "HDK_BLOOD_SUGAR") > 0;
    }
}
