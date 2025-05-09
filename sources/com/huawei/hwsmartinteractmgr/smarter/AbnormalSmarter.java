package com.huawei.hwsmartinteractmgr.smarter;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ContentOrdinaryBgText;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import defpackage.jdx;
import defpackage.kvs;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.List;

/* loaded from: classes5.dex */
public class AbnormalSmarter extends BaseSmarter {

    /* renamed from: a, reason: collision with root package name */
    private kvs f6400a;
    private HiUserInfo c;
    private Context d;

    public AbnormalSmarter(Context context) {
        super(context);
        this.d = context;
        this.f6400a = kvs.b(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // com.huawei.hwsmartinteractmgr.smarter.BaseSmarter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d() {
        /*
            r12 = this;
            super.d()
            android.content.Context r0 = r12.d
            r1 = 10021(0x2725, float:1.4042E-41)
            java.lang.String r1 = java.lang.Integer.toString(r1)
            java.lang.String r2 = "abnoral_information"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r1, r2)
            r1 = 30004(0x7534, float:4.2045E-41)
            java.lang.String r2 = "ai-exception-001"
            boolean r9 = defpackage.kwn.c(r1, r2)
            android.content.Context r3 = r12.d
            r10 = 10000(0x2710, float:1.4013E-41)
            java.lang.String r4 = java.lang.Integer.toString(r10)
            java.lang.String r5 = "onboarding_skip"
            java.lang.String r11 = health.compact.a.SharedPreferenceManager.b(r3, r4, r5)
            java.lang.String r3 = "isNoLongerRecommend = "
            java.lang.String r5 = ", isEnable = "
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r9)
            java.lang.String r7 = ", isSetOnBoarding = "
            r4 = r0
            r8 = r11
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4, r5, r6, r7, r8}
            java.lang.String r4 = "UIDV_AbnormalSmarter"
            com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
            java.lang.String r3 = "1"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto Lc6
            if (r9 != 0) goto L48
            goto Lc6
        L48:
            boolean r0 = r3.equals(r11)
            if (r0 == 0) goto L50
            goto Lc6
        L50:
            android.content.Context r0 = r12.d
            java.lang.String r3 = java.lang.Integer.toString(r10)
            java.lang.String r5 = "onboarding_skip_current_time"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r3, r5)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r5 = "NumberFormatException = "
            r6 = 0
            if (r3 != 0) goto L77
            long r8 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L6b
            goto L78
        L6b:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r5, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r4, r0)
        L77:
            r8 = r6
        L78:
            int r0 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r0 != 0) goto L86
            java.lang.String r0 = "no onBoarding skipTime == 0"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r0)
            return
        L86:
            java.lang.String r0 = "after_num_date_generate_exception_prompt"
            java.lang.String r0 = defpackage.kwn.b(r1, r2, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto La3
            int r1 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L97
            goto La4
        L97:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r1 = new java.lang.Object[]{r5, r1}
            com.huawei.hwlogsmodel.LogUtil.b(r4, r1)
        La3:
            r1 = 7
        La4:
            java.lang.String r2 = " skipDay = "
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            java.lang.String r5 = "day = "
            java.lang.Object[] r0 = new java.lang.Object[]{r5, r0, r2, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r0)
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r8
            float r0 = (float) r2
            r2 = 1285868416(0x4ca4cb80, float:8.64E7)
            float r0 = r0 / r2
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto Lc5
            r12.c()
        Lc5:
            return
        Lc6:
            java.lang.String r0 = "AbnormalSmarter no recommend delete"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r0)
            android.content.Context r0 = r12.d
            kvs r0 = defpackage.kvs.b(r0)
            r1 = 60000(0xea60, float:8.4078E-41)
            r0.d(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwsmartinteractmgr.smarter.AbnormalSmarter.d():void");
    }

    private void c() {
        b();
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("UIDV_AbnormalSmarter", "setAbnormalInfo");
        jdx.b(new Runnable() { // from class: com.huawei.hwsmartinteractmgr.smarter.AbnormalSmarter.3
            @Override // java.lang.Runnable
            public void run() {
                if (kvs.b(AbnormalSmarter.this.d).a(60000) != null) {
                    LogUtil.a("UIDV_AbnormalSmarter", "had AbnormalInfo");
                    return;
                }
                SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
                smartMsgDbObject.setMsgType(60000);
                smartMsgDbObject.setMsgSrc(6);
                smartMsgDbObject.setMsgContentType(2);
                smartMsgDbObject.setMsgContent(HiJsonUtil.d(new ContentOrdinaryBgText(), ContentOrdinaryBgText.class));
                smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
                smartMsgDbObject.setShowTime(SmartMsgConstant.DEFAULT_SHOW_TIME);
                smartMsgDbObject.setStatus(1);
                smartMsgDbObject.setMessagePriority(2);
                AbnormalSmarter.this.f6400a.a(smartMsgDbObject);
            }
        });
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "onboarding_skip_current_time", Integer.toString(0), new StorageParams());
    }

    private void b() {
        LogUtil.a("UIDV_AbnormalSmarter", "getAccountInfo");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1006);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("UIDV_AbnormalSmarter", "enter set by birthDate");
            e();
        }
        if ("0".equals(accountInfo2) || "1".equals(accountInfo2)) {
            return;
        }
        LogUtil.a("UIDV_AbnormalSmarter", "enter set by gender");
        e();
    }

    private void a() {
        HiHealthManager.d(this.d).fetchUserData(new HiCommonListener() { // from class: com.huawei.hwsmartinteractmgr.smarter.AbnormalSmarter.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj instanceof List) {
                    List list = (List) obj;
                    if (list.isEmpty()) {
                        return;
                    }
                    LogUtil.a("UIDV_AbnormalSmarter", "fetchUserData onSuccess");
                    AbnormalSmarter.this.c = (HiUserInfo) list.get(0);
                    if (!AbnormalSmarter.this.c.isHeightValid() && !AbnormalSmarter.this.c.isWeightValid()) {
                        LogUtil.a("UIDV_AbnormalSmarter", "enter set by height & weight");
                        AbnormalSmarter.this.e();
                    }
                    if ("1".equals(SharedPreferenceManager.b(AbnormalSmarter.this.d, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
                        LogUtil.a("UIDV_AbnormalSmarter", "third");
                        if (AbnormalSmarter.this.c.getGender() != 1 && AbnormalSmarter.this.c.getGender() != 0) {
                            LogUtil.a("UIDV_AbnormalSmarter", "enter set by gender");
                            AbnormalSmarter.this.e();
                        }
                        if (AbnormalSmarter.this.c.isBirthdayValid()) {
                            return;
                        }
                        LogUtil.a("UIDV_AbnormalSmarter", "enter set by birthday");
                        AbnormalSmarter.this.e();
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("UIDV_AbnormalSmarter", "fetchUserData onFailure");
            }
        });
    }
}
