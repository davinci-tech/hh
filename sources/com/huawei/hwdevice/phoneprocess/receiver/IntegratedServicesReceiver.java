package com.huawei.hwdevice.phoneprocess.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.datatypes.MsgImage;
import com.huawei.hwcommonmodel.datatypes.MsgText;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.cvz;
import defpackage.jeg;
import defpackage.jrg;
import defpackage.khg;
import defpackage.khi;
import defpackage.khk;
import defpackage.kho;
import defpackage.khs;
import defpackage.khu;
import defpackage.kim;
import defpackage.kiq;
import defpackage.kjl;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class IntegratedServicesReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private Context f6354a;
    private int b = 0;

    public IntegratedServicesReceiver(Context context) {
        this.f6354a = context;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        char c;
        if (intent == null || (action = intent.getAction()) == null) {
        }
        ReleaseLogUtil.e("R_IntegratedServicesReceiver", "onReceive handle action is: ", action);
        action.hashCode();
        switch (action.hashCode()) {
            case -1965616525:
                if (action.equals("com.huawei.health.ACTION_WEATHER_DATA_PUSH")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1935865425:
                if (action.equals("com.huawei.health.ACTION_LOCAL_PRESSURE_PUSH")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1850726883:
                if (action.equals("com.huawei.health.ACTION_NOTIFICATION_CLOCK_STATE")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1343153562:
                if (action.equals("com.huawei.bone.ACTION_NOTIFICATION_DELETE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 494135478:
                if (action.equals("com.huawei.health.ACTION_WEATHER_PUSH")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 698154387:
                if (action.equals("com.huawei.health.ACTION_NOTIFICATION_ENABLE_PUSH")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1366571669:
                if (action.equals("com.huawei.bone.ACTION_NOTIFICATION_PUSH")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1514929024:
                if (action.equals("com.huawei.health.ACTION_NOTIFY_PUSH_MULTI_LINK")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1601123058:
                if (action.equals("com.huawei.intelligent.action.NOTIFY_MSG")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                LogUtil.a("IntegratedServicesReceiver", "pushWeatherDataToDevice");
                kjl.b().d(true);
                break;
            case 1:
                LogUtil.a("IntegratedServicesReceiver", "pushLocalPressureToDevice");
                kjl.b().e();
                break;
            case 2:
                bOT_(intent);
                break;
            case 3:
                bOW_(intent);
                break;
            case 4:
                bPc_(intent);
                break;
            case 5:
                bPa_(intent);
                break;
            case 6:
                bPb_(intent);
                break;
            case 7:
                kim.bOt_(intent);
                break;
            case '\b':
                khs.bOq_(intent, this.f6354a);
                break;
            default:
                LogUtil.h("IntegratedServicesReceiver", "do not support action: ", action);
                break;
        }
    }

    private void bOT_(Intent intent) {
        if (intent == null) {
            LogUtil.h("IntegratedServicesReceiver", "dealClockState intent is null");
            return;
        }
        String stringExtra = intent.getStringExtra("clockStateType");
        LogUtil.a("IntegratedServicesReceiver", "dealClockState dealClockState: ", stringExtra);
        if (TextUtils.equals(stringExtra, "0") && cvz.c() && khs.a()) {
            boolean booleanExtra = intent.getBooleanExtra("clockStateValue", false);
            LogUtil.a("IntegratedServicesReceiver", "dealClockState clockState: ", Boolean.valueOf(booleanExtra));
            khu.a(this.f6354a).e(false, false, booleanExtra, false);
        }
    }

    private void bPb_(Intent intent) {
        Bundle bundle;
        ReleaseLogUtil.e("R_IntegratedServicesReceiver", "pushNotificationToDevice enter");
        try {
            bundle = intent.getExtras();
        } catch (Exception unused) {
            LogUtil.b("IntegratedServicesReceiver", "pushNotificationToDevice error");
            bundle = null;
        }
        if (bundle == null) {
            return;
        }
        String string = bundle.getString("notificationSwitchChangeType");
        if (!TextUtils.isEmpty(string)) {
            LogUtil.a("IntegratedServicesReceiver", "pushNotificationToDevice changeType: ", string);
            if ("notificationSwitchChangeType".equals(string)) {
                khu.a(this.f6354a).g();
                return;
            }
            if ("subNotificationSwitchChangeType".equals(string)) {
                khu.a(this.f6354a).e(bundle.getString("packageName"));
                return;
            }
            if ("notification_collaborate_switch_change".equals(string)) {
                kho.b().c();
                return;
            }
            if ("notificationReplayChangeType".equals(string)) {
                kiq.b(2, 2);
                return;
            } else if ("notificationLiveSwitchOpen".equals(string)) {
                khg.d().c();
                return;
            } else {
                LogUtil.a("IntegratedServicesReceiver", "pushNotificationToDevice else");
                return;
            }
        }
        if ("LIVE_NOTIFICATION_PUSH".equals(bundle.getString("LIVE_NOTIFICATION_PUSH_TYPE"))) {
            LogUtil.a("IntegratedServicesReceiver", "pushNotificationToDevice 5.55.2 jsonString is :" + bundle.getString("LIVE_NOTIFICATION_PUSH_JSON_STRING"));
            khi.c().d(bundle.getString("LIVE_NOTIFICATION_PUSH_JSON_STRING"));
            return;
        }
        bOU_(bundle, bundle.getBoolean("data_extra_send_call_wechat", false), bundle.getBoolean("data_extra_send_call_no_wechat", false));
    }

    private void bOU_(Bundle bundle, boolean z, boolean z2) {
        int i;
        try {
            i = bundle.getInt("type");
        } catch (Exception unused) {
            LogUtil.b("IntegratedServicesReceiver", "pushNotificationToDevice messageType error");
            i = 0;
        }
        String str = "";
        try {
            str = bundle.getString("packagename");
            if (!z && "com.tencent.mm".equals(str)) {
                ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, " pushNotificationToDevice wechat usual message not RING_CALL");
                bundle.putString("data_category", "msg");
            }
        } catch (Exception unused2) {
            LogUtil.b("IntegratedServicesReceiver", "pushNotificationToDevice message package error");
        }
        if (z && "com.tencent.mm".equals(str) && !khs.m()) {
            ReleaseLogUtil.e("R_IntegratedServicesReceiver", "pushNotificationToDevice do not support wechat voice push");
            return;
        }
        if (z2 && !b(str)) {
            ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, " pushNotificationToDevice RING_CALL FAILED");
            return;
        }
        if (!khs.o() && bundle.getInt("voip_type") == 2) {
            ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, "pushNotificationToDevice isSupportVoipType false FAILED");
            return;
        }
        if (z2 && khs.n()) {
            ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, "pushNotificationToDevice SUPPORT and SET_CATEGORY_IMCALL");
            bundle.putString("data_category", "imcall");
        }
        LogUtil.a("IntegratedServicesReceiver", "pushNotificationToDevice notificationKey is: ", bundle.getString("data_extra_noty_key"));
        khu.a(this.f6354a).a(khu.a(this.f6354a).bOk_(bundle, i, khu.a(this.f6354a).b(i, true, (List<MsgImage>) new ArrayList(16), (List<MsgText>) bPd_(this.f6354a, bundle), str)), 2);
        this.b++;
        if (khu.a(this.f6354a).c(this.b)) {
            LogUtil.a("IntegratedServicesReceiver", "pushNotificationToDevice mSendingMessageNumber is: ", Integer.valueOf(this.b));
            this.b = 0;
        }
    }

    private boolean b(String str) {
        if (TextUtils.equals(str, "com.whatsapp")) {
            ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, " allows push RING_CALL");
            return true;
        }
        if (khs.n()) {
            if (Build.VERSION.SDK_INT >= 29) {
                return c(str);
            }
            ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, " pushNotificationToDevice RING_CALL_FAILED because android version is: ", Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        ReleaseLogUtil.e("R_IntegratedServicesReceiver", str, " pushNotificationToDevice RING_CALL_FAILED: DEVICE_CAPABILITY_INCAPABLE");
        return false;
    }

    private boolean c(String str) {
        return TextUtils.equals(str, "org.telegram.messenger") || TextUtils.equals(str, "com.gbox.com.whatsapp") || TextUtils.equals(str, "jp.naver.line.android") || TextUtils.equals(str, "com.gbox.jp.naver.line.android") || TextUtils.equals(str, "com.instagram.android") || TextUtils.equals(str, "com.gbox.com.instagram.android") || TextUtils.equals(str, "com.facebook.orca") || TextUtils.equals(str, "com.alibaba.android.rimet.aliding") || TextUtils.equals(str, "com.alibaba.android.rimet");
    }

    public ArrayList<MsgText> bPd_(Context context, Bundle bundle) {
        int i;
        ArrayList<MsgText> arrayList = new ArrayList<>(16);
        if (context != null && bundle != null) {
            MsgText bOY_ = bOY_(bundle);
            arrayList.add(bOY_);
            arrayList.add(bOX_(bundle));
            if (jeg.d().c(context, "android.permission.READ_CALL_LOG")) {
                MsgText msgText = new MsgText();
                try {
                    i = bundle.getInt("incoming_type");
                } catch (Exception unused) {
                    LogUtil.b("IntegratedServicesReceiver", "numberType error");
                    i = 0;
                }
                if (i == 7 && c(context, bOY_, msgText)) {
                    msgText.setTextType(i);
                    arrayList.add(msgText);
                }
            }
            bOZ_(bundle, arrayList);
        }
        return arrayList;
    }

    private void bOZ_(Bundle bundle, ArrayList<MsgText> arrayList) {
        int i;
        try {
            i = bundle.getInt("incoming_type");
        } catch (Exception unused) {
            LogUtil.b("IntegratedServicesReceiver", "numberType error");
            i = 0;
        }
        if (i == 8 && khs.o()) {
            String string = bundle.getString("reject_button_key", null);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            MsgText msgText = new MsgText();
            msgText.setTextType(i);
            msgText.setTextContent(string);
            arrayList.add(msgText);
        }
    }

    private MsgText bOY_(Bundle bundle) {
        int i;
        String str;
        MsgText msgText = new MsgText();
        try {
            i = bundle.getInt("title_type");
        } catch (Exception unused) {
            LogUtil.b("IntegratedServicesReceiver", "messageTextType error");
            i = 0;
        }
        msgText.setTextType(i);
        try {
            str = bundle.getString("title");
        } catch (Exception unused2) {
            LogUtil.b("IntegratedServicesReceiver", "messageTextContent error");
            str = "";
        }
        msgText.setTextContent(str);
        return msgText;
    }

    private MsgText bOX_(Bundle bundle) {
        int i;
        String str;
        MsgText msgText = new MsgText();
        try {
            i = bundle.getInt("text_type");
        } catch (Exception unused) {
            LogUtil.b("IntegratedServicesReceiver", "messageTextType error");
            i = 0;
        }
        msgText.setTextType(i);
        try {
            str = bundle.getString(Constant.TEXT);
        } catch (Exception unused2) {
            LogUtil.b("IntegratedServicesReceiver", "textContent error");
            str = "";
        }
        msgText.setTextContent(str);
        return msgText;
    }

    private boolean c(Context context, MsgText msgText, MsgText msgText2) {
        List<String> c = c(context);
        String str = c.get(0);
        String str2 = c.get(1);
        if (!CommonUtil.bh()) {
            LogUtil.a("IntegratedServicesReceiver", "dealMissedCallNameNumber is not emui");
            if (!TextUtils.isEmpty(str)) {
                msgText.setTextContent(str);
            } else if (TextUtils.isEmpty(msgText.getTextContent())) {
                msgText.setTextContent(khs.e(str2));
            } else {
                LogUtil.h("IntegratedServicesReceiver", "dealMissedCallNameNumber nameContent is empty and title not is empty");
            }
        }
        LogUtil.a("IntegratedServicesReceiver", "dealMissedCallNameNumber numberContent: ", khs.a(str2), " nameContent: ", str);
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.d("R_IntegratedServicesReceiver", "isHandleMissedCallNameNumber numberContent is empty.");
            return false;
        }
        msgText2.setTextContent(str2);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<java.lang.String> c(android.content.Context r14) {
        /*
            r13 = this;
            java.lang.String r0 = "new"
            java.lang.String r1 = "number"
            java.lang.String r2 = "name"
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 16
            r3.<init>(r4)
            java.lang.String r4 = ""
            r5 = 0
            android.content.ContentResolver r6 = r14.getContentResolver()     // Catch: android.database.SQLException -> L6c
            android.net.Uri r7 = android.provider.CallLog.Calls.CONTENT_URI     // Catch: android.database.SQLException -> L6c
            r14 = 5
            java.lang.String[] r8 = new java.lang.String[r14]     // Catch: android.database.SQLException -> L6c
            r14 = 0
            r8[r14] = r2     // Catch: android.database.SQLException -> L6c
            r12 = 1
            r8[r12] = r1     // Catch: android.database.SQLException -> L6c
            java.lang.String r9 = "type"
            r10 = 2
            r8[r10] = r9     // Catch: android.database.SQLException -> L6c
            r9 = 3
            r8[r9] = r0     // Catch: android.database.SQLException -> L6c
            java.lang.String r10 = "date"
            r11 = 4
            r8[r11] = r10     // Catch: android.database.SQLException -> L6c
            java.lang.String[] r10 = new java.lang.String[r12]     // Catch: android.database.SQLException -> L6c
            java.lang.String r9 = java.lang.Integer.toString(r9)     // Catch: android.database.SQLException -> L6c
            r10[r14] = r9     // Catch: android.database.SQLException -> L6c
            java.lang.String r9 = "type=?"
            java.lang.String r11 = "date DESC"
            android.database.Cursor r14 = r6.query(r7, r8, r9, r10, r11)     // Catch: android.database.SQLException -> L6c
            if (r14 == 0) goto L6a
            boolean r6 = r14.moveToFirst()     // Catch: android.database.SQLException -> L6c
            if (r6 == 0) goto L65
            int r0 = r14.getColumnIndex(r0)     // Catch: android.database.SQLException -> L6c
            int r0 = r14.getInt(r0)     // Catch: android.database.SQLException -> L6c
            if (r0 != r12) goto L65
            int r0 = r14.getColumnIndex(r1)     // Catch: android.database.SQLException -> L6c
            java.lang.String r0 = r14.getString(r0)     // Catch: android.database.SQLException -> L6c
            int r1 = r14.getColumnIndex(r2)     // Catch: android.database.SQLException -> L6d
            java.lang.String r1 = r14.getString(r1)     // Catch: android.database.SQLException -> L6d
            r5 = r1
            goto L66
        L65:
            r0 = r4
        L66:
            r14.close()     // Catch: android.database.SQLException -> L6d
            goto L78
        L6a:
            r0 = r4
            goto L78
        L6c:
            r0 = r4
        L6d:
            java.lang.String r14 = "getMissedCallNumber SQLException"
            java.lang.Object[] r14 = new java.lang.Object[]{r14}
            java.lang.String r1 = "IntegratedServicesReceiver"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r14)
        L78:
            r3.add(r5)
            if (r0 != 0) goto L7e
            goto L7f
        L7e:
            r4 = r0
        L7f:
            r3.add(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.receiver.IntegratedServicesReceiver.c(android.content.Context):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void bOW_(android.content.Intent r6) {
        /*
            r5 = this;
            java.lang.String r0 = "IntegratedServicesReceiver"
            r1 = 0
            java.lang.String r2 = "type"
            int r2 = r6.getIntExtra(r2, r1)     // Catch: java.lang.Exception -> L11
            java.lang.String r3 = "data_extra_remove_call"
            boolean r1 = r6.getBooleanExtra(r3, r1)     // Catch: java.lang.Exception -> L12
            goto L1b
        L11:
            r2 = r1
        L12:
            java.lang.String r3 = "deleteMessageToDevice error"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
        L1b:
            if (r1 == 0) goto L2d
            boolean r1 = defpackage.khs.m()
            if (r1 != 0) goto L2d
            java.lang.String r6 = "deleteMessageToDevice wechat remove calls in old device return"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r6)
            return
        L2d:
            java.util.ArrayList r1 = new java.util.ArrayList
            r3 = 16
            r1.<init>(r3)
            r3 = 128(0x80, float:1.8E-43)
            if (r2 != r3) goto L4a
            r3 = 7
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.add(r3)
            r3 = 8
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.add(r3)
            goto L51
        L4a:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r1.add(r3)
        L51:
            android.os.Bundle r3 = r6.getExtras()
            java.lang.String r4 = "data_category"
            java.lang.String r3 = r3.getString(r4)
            boolean r4 = defpackage.khs.l()
            if (r4 != 0) goto L8c
            boolean r4 = defpackage.khs.d()
            if (r4 != 0) goto L8c
            boolean r4 = defpackage.khs.o()
            if (r4 == 0) goto L75
            java.lang.String r4 = "call"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L8c
        L75:
            java.lang.String r3 = "deleteMessageToDevice isSupportRepeatedNotifyProcess false"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            r3 = 127(0x7f, float:1.78E-43)
            if (r2 != r3) goto L8c
            java.lang.String r6 = "deleteMessageToDevice msgType is DEFAULT_TYPE"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)
            return
        L8c:
            r5.bOV_(r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.receiver.IntegratedServicesReceiver.bOW_(android.content.Intent):void");
    }

    private void bOV_(List<Integer> list, Intent intent) {
        if (list == null) {
            LogUtil.h("IntegratedServicesReceiver", "deleteMessageByType is null");
            return;
        }
        for (Integer num : list) {
            LogUtil.a("IntegratedServicesReceiver", "deleteMessageToDevice: msgType: ", num);
            khu.a(this.f6354a).a(khu.a(this.f6354a).bOj_(intent.getExtras(), khu.a(this.f6354a).d(num.intValue())), 2);
        }
    }

    private void bPc_(Intent intent) {
        boolean z = true;
        try {
            z = intent.getBooleanExtra("com.huawei.health.ACTION_WEATHER_PUSH_VALUE", true);
        } catch (Exception unused) {
            LogUtil.b("IntegratedServicesReceiver", "pushWeatherToDevice error");
        }
        LogUtil.a("IntegratedServicesReceiver", "pushWeatherToDevice value: ", Boolean.valueOf(z));
        kjl.b().c(z);
    }

    private void bPa_(Intent intent) {
        if (intent == null) {
            LogUtil.h("IntegratedServicesReceiver", "pushNotificationEnableToDevice() intent is null");
            return;
        }
        String stringExtra = intent.getStringExtra("notificationEnablePushType");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        if ("notificationEnablePushMainSwitch".equals(stringExtra)) {
            boolean booleanExtra = intent.getBooleanExtra("notificationEnableStatus", false);
            LogUtil.a("IntegratedServicesReceiver", "pushNotificationEnableToDevice, status is ", Boolean.valueOf(booleanExtra));
            khu.a(this.f6354a).a(jrg.d(booleanExtra, "IntegratedServicesReceiver"), 2);
            boolean booleanExtra2 = intent.getBooleanExtra("notificationEnableTurnOnSwitch", false);
            LogUtil.a("IntegratedServicesReceiver", "pushNotificationEnableToDevice isTurnOnSwitch: ", Boolean.valueOf(booleanExtra2));
            if (booleanExtra2) {
                khk.c().c(2);
                return;
            }
            return;
        }
        if ("notificationEnablePushSubSwitch".equals(stringExtra)) {
            khk.c().c(1);
        }
    }
}
