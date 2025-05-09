package defpackage;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.smarter.BaseSmarter;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kwf extends BaseSmarter {
    private static volatile kwf c;
    private static final Object d = new Object();

    private kwf(Context context) {
        super(context);
        this.e = context.getApplicationContext();
    }

    public static kwf d(Context context) {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    if (context == null) {
                        c = new kwf(BaseApplication.getContext());
                    } else {
                        c = new kwf(context);
                    }
                }
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i, String str2, int i2) {
        LogUtil.a("UIDV_InfoSmarter", "insertOneInfoMessageToDb msgType = ", Integer.valueOf(i));
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("UIDV_InfoSmarter", "DEFAULT_RECOMMEND_TIME");
            str2 = SmartMsgConstant.DEFAULT_SHOW_TIME;
        }
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        List<MessageObject> messages = messageCenterApi.getMessages(str);
        if (CommonUtil.SLEEP_INFO.equals(str)) {
            messages.addAll(messageCenterApi.getMessages(CommonUtil.STRESS_INFO));
        }
        ArrayList arrayList = new ArrayList(5);
        for (MessageObject messageObject : messages) {
            if (messageObject != null && !TextUtils.isEmpty(messageObject.getImgUri()) && !TextUtils.isEmpty(messageObject.getDetailUri())) {
                arrayList.add(messageObject);
            }
        }
        LogUtil.a("UIDV_InfoSmarter", "insertOneInfoMessageToDb infoMessageList.size = ", Integer.valueOf(arrayList.size()));
        if (arrayList.isEmpty()) {
            LogUtil.b("UIDV_InfoSmarter", "no message msgType = ", Integer.valueOf(i));
            return;
        }
        Collections.sort(arrayList, new a());
        Collections.sort(arrayList, new b());
        b((MessageObject) arrayList.get(0), i, str2, i2);
    }

    private void b(MessageObject messageObject, int i, String str, int i2) {
        int i3;
        SmartMsgDbObject a2 = kvs.b(this.e).a(i);
        String str2 = "";
        long j = 0;
        if (a2 != null) {
            i3 = a2.getId();
            try {
                JSONObject jSONObject = new JSONObject(a2.getMsgContent());
                str2 = jSONObject.getString("msgId");
                j = jSONObject.getLong("createTime");
            } catch (JSONException e) {
                LogUtil.b("UIDV_InfoSmarter", "JSONException = ", e.getMessage());
            }
        } else {
            LogUtil.a("UIDV_InfoSmarter", "no oldMsgDbObject");
            i3 = 0;
        }
        if (TextUtils.isEmpty(str2)) {
            b(i, messageObject, str);
            return;
        }
        if (!str2.equals(messageObject.getMsgId())) {
            b(i, messageObject, str);
        } else if (j == messageObject.getCreateTime()) {
            LogUtil.a("UIDV_InfoSmarter", "no message change");
        } else {
            c(messageObject, str, i3);
        }
    }

    private void c(MessageObject messageObject, String str, int i) {
        LogUtil.a("UIDV_InfoSmarter", "updateMessage");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", messageObject.getMsgTitle());
            jSONObject.put("msgId", messageObject.getMsgId());
            jSONObject.put(SmartMsgConstant.MSG_CONTENT_MESSAGE_CENTER_DETAIL_URL, messageObject.getDetailUri());
            jSONObject.put("createTime", messageObject.getCreateTime());
        } catch (JSONException e) {
            LogUtil.b("UIDV_InfoSmarter", "JSONException = ", e.getMessage());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("msgContent", jSONObject.toString());
        contentValues.put("expireTime", Long.valueOf(messageObject.getExpireTime()));
        contentValues.put(SmartMsgConstant.MSG_SHOW_TIME, str);
        contentValues.put("priority", Integer.valueOf(R.attr.priority));
        kvs.b(this.e).bSl_(i, contentValues);
    }

    private void b(int i, MessageObject messageObject, String str) {
        LogUtil.a("UIDV_InfoSmarter", "insertMessage");
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(i);
        smartMsgDbObject.setMsgSrc(4);
        smartMsgDbObject.setMsgContentType(2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", messageObject.getMsgTitle());
            jSONObject.put("msgId", messageObject.getMsgId());
            jSONObject.put(SmartMsgConstant.MSG_CONTENT_MESSAGE_CENTER_DETAIL_URL, messageObject.getDetailUri());
            jSONObject.put("createTime", messageObject.getCreateTime());
        } catch (JSONException e) {
            LogUtil.b("UIDV_InfoSmarter", "JSONException = ", e.getMessage());
        }
        smartMsgDbObject.setMsgContent(jSONObject.toString());
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setExpireTime(messageObject.getExpireTime());
        smartMsgDbObject.setShowTime(str);
        smartMsgDbObject.setStatus(1);
        smartMsgDbObject.setMessagePriority(R.attr.priority);
        kvs.b(this.e).d(i);
        kvs.b(this.e).a(smartMsgDbObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        SmartMsgDbObject a2 = kvs.b(this.e).a(i);
        if (a2 != null && a2.getStatus() == 1) {
            LogUtil.a("UIDV_InfoSmarter", "deleteAfterCheckStatus msgType = ", Integer.valueOf(i));
            kvs.b(this.e).d(i);
        } else {
            LogUtil.a("UIDV_InfoSmarter", "deleteAfterCheckStatus msgType = ", Integer.valueOf(i), "no delete");
        }
    }

    public void g() {
        jdx.b(new Runnable() { // from class: kwf.4
            @Override // java.lang.Runnable
            public void run() {
                int parseInt;
                final String a2;
                boolean h;
                if (!kwf.this.b("reduce_fat_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-001")) {
                    kwf.this.e(SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER);
                    LogUtil.a("UIDV_InfoSmarter", "weightRecommend deleteAfterCheckStatus");
                    return;
                }
                final int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-001");
                String b2 = kwn.b(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-001", "recently_num_days_have_data");
                if (!TextUtils.isEmpty(b2)) {
                    try {
                        parseInt = Integer.parseInt(b2);
                    } catch (NumberFormatException e) {
                        LogUtil.b("UIDV_InfoSmarter", "NumberFormatException = ", e.getMessage());
                    }
                    a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-001");
                    LogUtil.a("UIDV_InfoSmarter", "weightRecommend priority = ", Integer.valueOf(d2), ", recentlyHaveDataDate = ", b2, ", recommendedTime = ", a2);
                    h = kvz.h();
                    boolean b3 = kvx.c(kwf.this.e).b(4);
                    if (!h || b3) {
                        kwf.this.e(CommonUtil.WEIGHT_INFO, SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER, a2, d2);
                    }
                    kot.a().b(kwf.this.e, System.currentTimeMillis() - (parseInt * 86400000), System.currentTimeMillis(), 1, new IBaseResponseCallback() { // from class: kwf.4.3
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            kwf.this.c(i, obj, a2, d2);
                        }
                    });
                    return;
                }
                parseInt = 0;
                a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-001");
                LogUtil.a("UIDV_InfoSmarter", "weightRecommend priority = ", Integer.valueOf(d2), ", recentlyHaveDataDate = ", b2, ", recommendedTime = ", a2);
                h = kvz.h();
                boolean b32 = kvx.c(kwf.this.e).b(4);
                if (!h) {
                }
                kwf.this.e(CommonUtil.WEIGHT_INFO, SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER, a2, d2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj, String str, int i2) {
        LogUtil.a("UIDV_InfoSmarter", "getWeightData onResponse errCode = ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof List)) {
            if (!((List) obj).isEmpty()) {
                e(CommonUtil.WEIGHT_INFO, SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER, str, i2);
            } else {
                e(SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER);
                LogUtil.a("UIDV_InfoSmarter", "weightRecommend deleteAfterCheckStatus");
            }
        }
    }

    private void n() {
        jdx.b(new Runnable() { // from class: kwf.3
            @Override // java.lang.Runnable
            public void run() {
                if (!kwf.this.b("daily_health_care_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-002")) {
                    LogUtil.a("UIDV_InfoSmarter", "walkRecommend deleteAfterCheckStatus");
                    kwf.this.e(40001);
                    return;
                }
                int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-002");
                String a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-002");
                LogUtil.a("UIDV_InfoSmarter", "walkRecommend priority = ", Integer.valueOf(d2), ", recommendedTime = ", a2);
                boolean b2 = kvz.b();
                boolean e = kvz.e();
                if (b2 && e) {
                    kwf.this.e(CommonUtil.WALK_INFO, 40001, a2, d2);
                } else {
                    LogUtil.a("UIDV_InfoSmarter", "walkRecommend deleteAfterCheckStatus");
                    kwf.this.e(40001);
                }
            }
        });
    }

    public void c() {
        jdx.b(new Runnable() { // from class: kwf.2
            @Override // java.lang.Runnable
            public void run() {
                int parseInt;
                String a2;
                int b2;
                if (!kwf.this.b("blood_pressure_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-003")) {
                    LogUtil.a("UIDV_InfoSmarter", "bloodPressureRecommend deleteAfterCheckStatus");
                    kwf.this.e(40002);
                    return;
                }
                int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-003");
                String b3 = kwn.b(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-003", "recently_num_days_have_data");
                if (!TextUtils.isEmpty(b3)) {
                    try {
                        parseInt = Integer.parseInt(b3);
                    } catch (NumberFormatException e) {
                        LogUtil.b("UIDV_InfoSmarter", "NumberFormatException = ", e.getMessage());
                    }
                    a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-003");
                    LogUtil.a("UIDV_InfoSmarter", "bloodPressureRecommend priority = ", Integer.valueOf(d2), ", recentlyHaveDataDate = ", b3, ", recommendedTime = ", a2);
                    b2 = kwn.b(kwf.this.e, "HDK_BLOOD_PRESSURE");
                    boolean b4 = kvx.c(kwf.this.e).b(6);
                    if (b2 <= 0 || b4) {
                        kwf.this.e(CommonUtil.BLOOD_PRESSURE_INFO, 40002, a2, d2);
                    }
                    kwf.this.d(System.currentTimeMillis() - (parseInt * 86400000), 1, a2, d2);
                    return;
                }
                parseInt = 0;
                a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-003");
                LogUtil.a("UIDV_InfoSmarter", "bloodPressureRecommend priority = ", Integer.valueOf(d2), ", recentlyHaveDataDate = ", b3, ", recommendedTime = ", a2);
                b2 = kwn.b(kwf.this.e, "HDK_BLOOD_PRESSURE");
                boolean b42 = kvx.c(kwf.this.e).b(6);
                if (b2 <= 0) {
                }
                kwf.this.e(CommonUtil.BLOOD_PRESSURE_INFO, 40002, a2, d2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, int i, final String str, final int i2) {
        kor.a().a(j, System.currentTimeMillis(), i, new IBaseResponseCallback() { // from class: kwf.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("UIDV_InfoSmarter", "getBloodPressureData onResponse errCode = ", Integer.valueOf(i3));
                if (i3 == 0 && (obj instanceof List)) {
                    if (!((List) obj).isEmpty()) {
                        kwf.this.e(CommonUtil.BLOOD_PRESSURE_INFO, 40002, str, i2);
                    } else {
                        LogUtil.a("UIDV_InfoSmarter", "bloodPressureRecommend deleteAfterCheckStatus");
                        kwf.this.e(40002);
                    }
                }
            }
        });
    }

    private void i() {
        jdx.b(new Runnable() { // from class: kwf.6
            @Override // java.lang.Runnable
            public void run() {
                if (!kwf.this.b("body_build_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-004")) {
                    LogUtil.a("UIDV_InfoSmarter", "healthRecommend deleteAfterCheckStatus");
                    kwf.this.e(40003);
                    return;
                }
                int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-004");
                String a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-004");
                LogUtil.a("UIDV_InfoSmarter", "healthRecommend priority = ", Integer.valueOf(d2), ", recommendedTime = ", a2);
                boolean d3 = kvz.d();
                boolean b2 = kvx.c(kwf.this.e).b(3);
                if (d3 || b2) {
                    kwf.this.e(CommonUtil.HEALTH_INFO, 40003, a2, d2);
                } else {
                    LogUtil.a("UIDV_InfoSmarter", "healthRecommend deleteAfterCheckStatus");
                    kwf.this.e(40003);
                }
            }
        });
    }

    private void l() {
        jdx.b(new Runnable() { // from class: kwf.10
            @Override // java.lang.Runnable
            public void run() {
                if (!kwf.this.b("run_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-005")) {
                    LogUtil.a("UIDV_InfoSmarter", "runRecommend deleteAfterCheckStatus");
                    kwf.this.e(40004);
                    return;
                }
                int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-005");
                String a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-005");
                LogUtil.a("UIDV_InfoSmarter", "runRecommend priority = ", Integer.valueOf(d2), ", recommendedTime = ", a2);
                boolean a3 = kvz.a();
                boolean b2 = kvx.c(kwf.this.e).b(0);
                if (a3 || b2) {
                    kwf.this.e(CommonUtil.RUN_INFO, 40004, a2, d2);
                } else {
                    LogUtil.a("UIDV_InfoSmarter", "runRecommend deleteAfterCheckStatus");
                    kwf.this.e(40004);
                }
            }
        });
    }

    private void o() {
        jdx.b(new Runnable() { // from class: kwf.8
            @Override // java.lang.Runnable
            public void run() {
                if (!kwf.this.b("ride_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-006")) {
                    LogUtil.a("UIDV_InfoSmarter", "rideRecommend deleteAfterCheckStatus");
                    kwf.this.e(SmartMsgConstant.MSG_TYPE_RIDE_USER);
                    return;
                }
                int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-006");
                String a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-006");
                LogUtil.a("UIDV_InfoSmarter", "rideRecommend priority = ", Integer.valueOf(d2), ", recommendedTime = ", a2);
                boolean c2 = kvz.c();
                boolean b2 = kvx.c(kwf.this.e).b(1);
                if (c2 || b2) {
                    kwf.this.e(CommonUtil.RIDE_INFO, SmartMsgConstant.MSG_TYPE_RIDE_USER, a2, d2);
                } else {
                    LogUtil.a("UIDV_InfoSmarter", "rideRecommend deleteAfterCheckStatus");
                    kwf.this.e(SmartMsgConstant.MSG_TYPE_RIDE_USER);
                }
            }
        });
    }

    public void a() {
        LogUtil.a("UIDV_InfoSmarter", "bloodSugarRecommend");
        jdx.b(new Runnable() { // from class: kwf.7
            @Override // java.lang.Runnable
            public void run() {
                int parseInt;
                final String a2;
                int b2;
                if (kwf.this.j()) {
                    final int d2 = kwn.d(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-007");
                    String b3 = kwn.b(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-007", "recently_num_days_have_data");
                    if (!TextUtils.isEmpty(b3)) {
                        try {
                            parseInt = Integer.parseInt(b3);
                        } catch (NumberFormatException e) {
                            LogUtil.b("UIDV_InfoSmarter", "NumberFormatException = ", e.getMessage());
                        }
                        a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-007");
                        b2 = kwn.b(kwf.this.e, "HDK_BLOOD_SUGAR");
                        boolean b4 = kvx.c(kwf.this.e).b(7);
                        LogUtil.c("UIDV_InfoSmarter", "bloodSugarRecommend BindBloods = ", Integer.valueOf(b2), ", isFocusBloodSugar = ", Boolean.valueOf(b4));
                        if (b2 <= 0 || b4) {
                            kwf.this.e(CommonUtil.BLOOD_SUGAR_INFO, SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER, a2, d2);
                        }
                        kor.a().d(kwf.this.e, System.currentTimeMillis() - (parseInt * 86400000), System.currentTimeMillis(), 1, new IBaseResponseCallback() { // from class: kwf.7.5
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i, Object obj) {
                                LogUtil.a("UIDV_InfoSmarter", "bloodSugarRecommend onResponse errCode = ", Integer.valueOf(i));
                                kwf.this.b(i, obj, SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER, a2, d2);
                            }
                        });
                        return;
                    }
                    parseInt = 0;
                    a2 = kwn.a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-007");
                    b2 = kwn.b(kwf.this.e, "HDK_BLOOD_SUGAR");
                    boolean b42 = kvx.c(kwf.this.e).b(7);
                    LogUtil.c("UIDV_InfoSmarter", "bloodSugarRecommend BindBloods = ", Integer.valueOf(b2), ", isFocusBloodSugar = ", Boolean.valueOf(b42));
                    if (b2 <= 0) {
                    }
                    kwf.this.e(CommonUtil.BLOOD_SUGAR_INFO, SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER, a2, d2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        if (b("blood_sugar_user", OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "ai-info-007")) {
            return true;
        }
        LogUtil.a("UIDV_InfoSmarter", "bloodSugarRecommend deleteAfterCheckStatus");
        e(SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Object obj, int i2, String str, int i3) {
        if (!(obj instanceof List)) {
            LogUtil.a("UIDV_InfoSmarter", "setRecommendResponse objData is null!");
            return;
        }
        if (i == 0) {
            if (!((List) obj).isEmpty()) {
                LogUtil.a("UIDV_InfoSmarter", "setRecommendResponse have data");
                e(CommonUtil.BLOOD_SUGAR_INFO, i2, str, i3);
                return;
            } else {
                LogUtil.a("UIDV_InfoSmarter", "setRecommendResponse deleteAfterCheckStatus");
                e(i2);
                return;
            }
        }
        LogUtil.a("UIDV_InfoSmarter", "setRecommendResponse deleteAfterCheckStatus");
        e(i2);
    }

    public void f() {
        jdx.b(new Runnable() { // from class: kwf.9
            /* JADX WARN: Removed duplicated region for block: B:11:0x006e  */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0080  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r12 = this;
                    kwf r0 = defpackage.kwf.this
                    java.lang.String r1 = "sleep_user"
                    r2 = 30003(0x7533, float:4.2043E-41)
                    java.lang.String r3 = "ai-info-008"
                    boolean r0 = r0.b(r1, r2, r3)
                    r1 = 40007(0x9c47, float:5.6062E-41)
                    java.lang.String r4 = "UIDV_InfoSmarter"
                    if (r0 != 0) goto L24
                    java.lang.String r0 = "sleepRecommend deleteAfterCheckStatus"
                    java.lang.Object[] r0 = new java.lang.Object[]{r0}
                    com.huawei.hwlogsmodel.LogUtil.a(r4, r0)
                    kwf r0 = defpackage.kwf.this
                    defpackage.kwf.a(r0, r1)
                    return
                L24:
                    int r0 = defpackage.kwn.d(r2, r3)
                    java.lang.String r5 = "recently_num_days_have_data"
                    java.lang.String r9 = defpackage.kwn.b(r2, r3, r5)
                    boolean r5 = android.text.TextUtils.isEmpty(r9)
                    if (r5 != 0) goto L47
                    int r5 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L39
                    goto L48
                L39:
                    r5 = move-exception
                    java.lang.String r6 = "NumberFormatException = "
                    java.lang.String r5 = r5.getMessage()
                    java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
                    com.huawei.hwlogsmodel.LogUtil.b(r4, r5)
                L47:
                    r5 = 0
                L48:
                    java.lang.String r2 = defpackage.kwn.a(r2, r3)
                    java.lang.String r6 = "sleepRecommend priority = "
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
                    java.lang.String r8 = ", recentlyHaveDataDate = "
                    java.lang.String r10 = ", recommendedTime = "
                    r11 = r2
                    java.lang.Object[] r3 = new java.lang.Object[]{r6, r7, r8, r9, r10, r11}
                    com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
                    kwf r3 = defpackage.kwf.this
                    android.content.Context r3 = r3.e
                    kvx r3 = defpackage.kvx.c(r3)
                    r6 = 5
                    boolean r3 = r3.b(r6)
                    if (r3 == 0) goto L80
                    java.lang.String r3 = "sleep insert"
                    java.lang.Object[] r3 = new java.lang.Object[]{r3}
                    com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
                    kwf r3 = defpackage.kwf.this
                    java.lang.String r4 = "IC7"
                    defpackage.kwf.c(r3, r4, r1, r2, r0)
                    return
                L80:
                    long r3 = java.lang.System.currentTimeMillis()
                    long r5 = (long) r5
                    com.huawei.hihealth.HiDataReadOption r1 = new com.huawei.hihealth.HiDataReadOption
                    r1.<init>()
                    r7 = 86400000(0x5265c00, double:4.2687272E-316)
                    long r5 = r5 * r7
                    long r3 = r3 - r5
                    long r5 = java.lang.System.currentTimeMillis()
                    r1.setTimeInterval(r3, r5)
                    r3 = 44105(0xac49, float:6.1804E-41)
                    r4 = 44004(0xabe4, float:6.1663E-41)
                    int[] r3 = new int[]{r3, r4}
                    r1.setType(r3)
                    r3 = 1
                    r1.setSortOrder(r3)
                    r3 = 7
                    r1.setCount(r3)
                    kwf r3 = defpackage.kwf.this
                    defpackage.kwf.e(r3, r1, r2, r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.kwf.AnonymousClass9.run():void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HiDataReadOption hiDataReadOption, final String str, final int i) {
        HiHealthManager.d(this.e).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kwf.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                if (!(obj instanceof SparseArray)) {
                    LogUtil.a("UIDV_InfoSmarter", "requestSleepData data is null");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                List list = (List) sparseArray.get(44004);
                List list2 = (List) sparseArray.get(44105);
                boolean z = (list == null || list.isEmpty()) ? false : true;
                boolean z2 = (list2 == null || list2.isEmpty()) ? false : true;
                if (z || z2) {
                    kwf.this.e(CommonUtil.SLEEP_INFO, SmartMsgConstant.MSG_TYPE_SLEEP_USER, str, i);
                } else {
                    LogUtil.a("UIDV_InfoSmarter", "sleepRecommend deleteAfterCheckStatus");
                    kwf.this.e(SmartMsgConstant.MSG_TYPE_SLEEP_USER);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("UIDV_InfoSmarter", "requestSleepData onResultIntent");
            }
        });
    }

    public static class b implements Comparator<MessageObject>, Serializable {
        private static final long serialVersionUID = -8568787313376601753L;

        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObject messageObject, MessageObject messageObject2) {
            return messageObject2.getWeight() - messageObject.getWeight();
        }
    }

    public static class a implements Comparator<MessageObject>, Serializable {
        private static final long serialVersionUID = 2755029354926884596L;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObject messageObject, MessageObject messageObject2) {
            return Long.compare(messageObject2.getCreateTime(), messageObject.getCreateTime());
        }
    }

    public void e() {
        LogUtil.a("UIDV_InfoSmarter", "allUserRecommend");
        g();
        c();
        a();
        o();
        l();
        f();
        i();
        n();
    }

    public void b() {
        LogUtil.a("UIDV_InfoSmarter", "labelRelatedRecommend");
        g();
        n();
        i();
        l();
        o();
    }

    public void h() {
        LogUtil.a("UIDV_InfoSmarter", "userPreferenceRelatedRecommend");
        g();
        c();
        a();
        o();
        l();
        f();
        i();
    }

    @Override // com.huawei.hwsmartinteractmgr.smarter.BaseSmarter
    public void d() {
        LogUtil.a("UIDV_InfoSmarter", "info startTimerCheck");
        e();
    }
}
