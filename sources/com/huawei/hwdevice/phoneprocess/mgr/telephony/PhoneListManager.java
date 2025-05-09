package com.huawei.hwdevice.phoneprocess.mgr.telephony;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.telephony.CallerInfoHW;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.android.util.NoExtAPIException;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.datatypes.MsgImage;
import com.huawei.hwcommonmodel.datatypes.MsgText;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import defpackage.cun;
import defpackage.jde;
import defpackage.jeg;
import defpackage.khs;
import defpackage.khu;
import defpackage.kiq;
import defpackage.kke;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class PhoneListManager extends PhoneStateListener {
    private Context h;
    private boolean l;
    private TelephonyManager m;
    private jde n;
    private khu q;
    private volatile long r;
    private static final Uri e = Uri.parse("content://com.android.contacts.app/yellow_page_data");
    private static final Uri c = Uri.parse("content://com.android.contacts.app/number_mark");
    private static final String[] b = {"name", "number"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6352a = {"android.permission.READ_PHONE_STATE"};
    private static ThreadPoolManager i = ThreadPoolManager.e(5, 5, "PhoneListManager");
    private static boolean g = false;
    private static boolean f = false;
    public static final Map<String, Long> d = new ConcurrentHashMap(0);
    private boolean k = false;
    private boolean o = false;
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("PhoneListManager", "mid ware onReceive: action: ", action);
            if (action == null || !TextUtils.equals(action, "midware_phone_flag")) {
                return;
            }
            PhoneListManager.this.o = intent.getBooleanExtra("phone_flag", false);
            ReleaseLogUtil.e("Notify_PhoneListManager", "mBroadcastReceiver mIsPhoneForbidden: ", Boolean.valueOf(PhoneListManager.this.o));
        }
    };
    private PhoneStateListener s = new PhoneStateListener() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.2
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i2, String str) {
            super.onCallStateChanged(i2, str);
            ReleaseLogUtil.e("Notify_PhoneListManager", "PhoneStateListener onCallStateChanged state: ", Integer.valueOf(i2), ",incomingNumber: ", khs.a(str));
            PhoneListManager.this.h();
            PhoneListManager.this.c(String.valueOf(i2), str);
            if (PhoneListManager.this.q.a()) {
                if (PhoneListManager.this.l && i2 == 0) {
                    PhoneListManager.this.l = false;
                }
                if (i2 == 2) {
                    PhoneListManager.this.b(str);
                }
                if (i2 == 1) {
                    PhoneListManager.this.f(str);
                    return;
                }
                if (i2 == 2) {
                    PhoneListManager.this.b(13);
                    return;
                } else if (i2 == 0) {
                    PhoneListManager.this.b(12);
                    return;
                } else {
                    LogUtil.h("PhoneListManager", "PhoneStateListener onCallStateChanged else");
                    return;
                }
            }
            LogUtil.h("PhoneListManager", "PhoneStateListener onCallStateChanged push notification failed: DEVICE_CAPABILITY_INCAPABLE!");
        }
    };
    private final BroadcastReceiver p = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.PHONE_STATE".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("state");
                String stringExtra2 = intent.getStringExtra("incoming_number");
                ReleaseLogUtil.e("Notify_PhoneListManager", "BroadcastReceiver onReceive state: ", stringExtra, ",incomingNumber: ", khs.a(stringExtra2));
                PhoneListManager.this.h();
                PhoneListManager.this.c(stringExtra, stringExtra2);
                if (PhoneListManager.this.q.a()) {
                    if (PhoneListManager.this.l && TelephonyManager.EXTRA_STATE_IDLE.equals(stringExtra)) {
                        PhoneListManager.this.l = false;
                    }
                    if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(stringExtra)) {
                        PhoneListManager.this.b(stringExtra2);
                    }
                    if (TelephonyManager.EXTRA_STATE_RINGING.equals(stringExtra)) {
                        PhoneListManager.this.f(stringExtra2);
                        return;
                    }
                    if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(stringExtra)) {
                        PhoneListManager.this.b(13);
                        return;
                    } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(stringExtra)) {
                        PhoneListManager.this.b(12);
                        return;
                    } else {
                        LogUtil.h("PhoneListManager", "BroadcastReceiver onReceive else");
                        return;
                    }
                }
                ReleaseLogUtil.d("Notify_PhoneListManager", "BroadcastReceiver onReceive push notification failed: DEVICE_CAPABILITY_INCAPABLE");
            }
        }
    };
    private final BroadcastReceiver t = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ReleaseLogUtil.d("Notify_PhoneListManager", "mOutCallReceiver intent is null");
                return;
            }
            if ("android.intent.action.NEW_OUTGOING_CALL".equals(intent.getAction())) {
                if (!PhoneListManager.this.q.d()) {
                    ReleaseLogUtil.d("Notify_PhoneListManager", "mOutCallReceiver not support outgoing call");
                    return;
                }
                final String resultData = getResultData();
                try {
                    if (TextUtils.isEmpty(resultData)) {
                        ReleaseLogUtil.e("Notify_PhoneListManager", "mOutCallReceiver getResultData get phoneNumber fail");
                        resultData = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
                    }
                } catch (Exception unused) {
                    LogUtil.b("PhoneListManager", "mOutCallReceiver get phone number exception");
                    sqo.af("mOutCallReceiver get phone number exception");
                }
                ReleaseLogUtil.e("Notify_PhoneListManager", "mOutCallReceiver outPhone: ", khs.a(resultData));
                if (TextUtils.isEmpty(resultData)) {
                    return;
                }
                PhoneListManager.i.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PhoneListManager.this.e(resultData);
                    }
                });
                return;
            }
            ReleaseLogUtil.d("Notify_PhoneListManager", "mOutCallReceiver action not equals");
        }
    };

    public PhoneListManager(Context context) {
        if (context == null) {
            return;
        }
        this.h = context;
        try {
            if (context.getSystemService("phone") instanceof TelephonyManager) {
                this.m = (TelephonyManager) context.getSystemService("phone");
            }
            this.q = khu.a(context);
        } catch (Exception unused) {
            LogUtil.b("PhoneListManager", "Exception getSystemService");
            sqo.af("Exception getSystemService");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        i.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ReleaseLogUtil.e("Notify_PhoneListManager", "print ReadCallLog Permission is ", Boolean.valueOf(jeg.d().c(PhoneListManager.this.h, "android.permission.READ_CALL_LOG")));
                } catch (Exception unused) {
                    LogUtil.b("PhoneListManager", "print ReadCallLog Permission Exception");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        Long remove;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (TextUtils.equals(str, "1") || TextUtils.equals(str, "RINGING")) {
            Map<String, Long> map = d;
            if (map.containsKey(str2)) {
                return;
            }
            map.put(str2, Long.valueOf(SystemClock.elapsedRealtime()));
            return;
        }
        if ((TextUtils.equals(str, "0") || TextUtils.equals(str, "IDLE")) && (remove = d.remove(str2)) != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - remove.longValue();
            if (elapsedRealtime < 1000) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
                linkedHashMap.put("callDelayTime", String.valueOf(elapsedRealtime));
                linkedHashMap.put("callDelaySystemInfo", kiq.e());
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        String str2;
        String str3;
        if (this.l) {
            LogUtil.h("PhoneListManager", "doOutgoingCall mIsOutgoing");
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.h("PhoneListManager", "doOutgoingCall switch not on not need start service");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "PhoneListManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("PhoneListManager", "doOutgoingCall have no device so do not start PhoneService");
            return;
        }
        this.l = true;
        String h = h(str);
        if (h == null || !h.equals(str)) {
            str2 = null;
            str3 = null;
        } else {
            String b2 = b(this.h, str);
            str3 = b2;
            str2 = TextUtils.isEmpty(b2) ? d(this.h, str) : null;
        }
        b(str, h, str2, str3, true);
    }

    public void c() {
        if (this.k) {
            return;
        }
        try {
            LogUtil.a("PhoneListManager", "register READ_PHONE_STATE");
            this.k = true;
            if (!b()) {
                LogUtil.a("PhoneListManager", "register READ_PHONE_STATE listen enter");
                this.m.listen(this.s, 32);
            }
            BroadcastManagerUtil.bFC_(this.h, this.p, new IntentFilter("android.intent.action.PHONE_STATE"), LocalBroadcast.c, null);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("midware_phone_flag");
            BroadcastManagerUtil.bFC_(this.h, this.j, intentFilter, LocalBroadcast.c, null);
            BroadcastManagerUtil.bFC_(this.h, this.t, new IntentFilter("android.intent.action.NEW_OUTGOING_CALL"), LocalBroadcast.c, null);
        } catch (Exception unused) {
            LogUtil.b("PhoneListManager", "Exception registerReceiver");
            sqo.af("Exception registerReceiver");
        }
    }

    private boolean b() {
        return Build.VERSION.SDK_INT >= 33 && !PermissionUtil.e(this.h, new String[]{"android.permission.READ_PHONE_STATE"});
    }

    public void d() {
        if (this.k) {
            try {
                this.k = false;
                ReleaseLogUtil.e("Notify_PhoneListManager", "unregister()");
                if (!b()) {
                    this.m.listen(this.s, 0);
                }
                BroadcastReceiver broadcastReceiver = this.p;
                if (broadcastReceiver != null) {
                    this.h.unregisterReceiver(broadcastReceiver);
                }
                BroadcastReceiver broadcastReceiver2 = this.j;
                if (broadcastReceiver2 != null) {
                    this.h.unregisterReceiver(broadcastReceiver2);
                }
                BroadcastReceiver broadcastReceiver3 = this.t;
                if (broadcastReceiver3 != null) {
                    this.h.unregisterReceiver(broadcastReceiver3);
                }
            } catch (Exception unused) {
                LogUtil.b("PhoneListManager", "Exception unregisterReceiver");
                sqo.af("Exception unregisterReceiver");
            }
        }
    }

    public void a() {
        try {
            LogUtil.a("PhoneListManager", "refreshRegister()");
            if (!b()) {
                this.m.listen(this.s, 0);
                this.m.listen(this.s, 32);
            }
            this.k = true;
        } catch (Exception unused) {
            LogUtil.b("PhoneListManager", "Exception refreshRegister");
            sqo.af("Exception refreshRegister");
        }
    }

    private void i(final String str) {
        i.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.8
            @Override // java.lang.Runnable
            public void run() {
                PhoneListManager.this.d(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        if (kke.h() && kke.a()) {
            kke.d(str);
        }
        if (this.o) {
            ReleaseLogUtil.e("Notify_PhoneListManager", "doPhoneRing mIsPhoneForbidden is true, so return");
            return;
        }
        if (g) {
            ReleaseLogUtil.e("Notify_PhoneListManager", "doPhoneRing mIsPhoneRing is true, so return");
            return;
        }
        g = true;
        f = false;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            ReleaseLogUtil.d("Notify_PhoneListManager", "switch not on, not need start service!");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "PhoneListManager");
        if (deviceList == null || deviceList.isEmpty()) {
            ReleaseLogUtil.d("Notify_PhoneListManager", "have no device so do not start PhoneService.");
            return;
        }
        ReleaseLogUtil.d("Notify_PhoneListManager", "doPhoneRing incomingNumber is ", khs.a(str), ", currentThread is ", Long.valueOf(Thread.currentThread().getId()));
        if (TextUtils.isEmpty(str)) {
            str2 = str;
        } else {
            str2 = h(str);
            if (str2 != null && str2.equals(str)) {
                String b2 = b(this.h, str);
                str3 = str2;
                str5 = b2;
                str4 = TextUtils.isEmpty(b2) ? d(this.h, str) : null;
                b(str, str3, str4, str5, false);
            }
        }
        str3 = str2;
        str4 = null;
        str5 = null;
        b(str, str3, str4, str5, false);
    }

    private void b(String str, String str2, String str3, String str4, boolean z) {
        LogUtil.a("PhoneListManager", "handleLinkLayer DeviceProtocol is ", Integer.valueOf(this.q.c()));
        if (this.q.c() == -1) {
            this.q.b(str);
            return;
        }
        this.n = b(str2, str4, str3, str);
        ReleaseLogUtil.e("Notify_PhoneListManager", "handleLinkLayer start NotifySendData to send command");
        if (z) {
            this.n.a(50);
        }
        DeviceCommand b2 = this.q.b(this.n.c(), true, (List<MsgImage>) this.n.b(), (List<MsgText>) this.n.d(), (String) null);
        if ((this.n.c() == 1 || this.n.c() == 50) && khs.h() && !CommonUtil.bh()) {
            b2 = this.q.d(b2);
        }
        if (g || z) {
            this.q.a(b2, 3);
        } else {
            ReleaseLogUtil.e("Notify_PhoneListManager", "handleLinkLayer not start NotifySendData to send command");
        }
    }

    private jde b(String str, String str2, String str3, String str4) {
        LogUtil.a("PhoneListManager", "wrapCallStateMsgData(): incomingNumber(): ", khs.a(str4), " yellowPage: ", Boolean.valueOf(TextUtils.isEmpty(str2)), " markStr: ", Boolean.valueOf(TextUtils.isEmpty(str3)));
        ArrayList arrayList = new ArrayList(16);
        MsgText msgText = new MsgText();
        msgText.setTextType(1);
        msgText.setTextContent(str);
        if (!TextUtils.isEmpty(str2)) {
            MsgText msgText2 = new MsgText();
            msgText2.setTextType(5);
            msgText2.setTextContent(str2);
            arrayList.add(msgText2);
        }
        if (!TextUtils.isEmpty(str3)) {
            MsgText msgText3 = new MsgText();
            msgText3.setTextType(6);
            msgText3.setTextContent(str3);
            arrayList.add(msgText3);
        }
        arrayList.add(msgText);
        if (!TextUtils.isEmpty(str4)) {
            MsgText msgText4 = new MsgText();
            msgText4.setTextType(7);
            msgText4.setTextContent(str4);
            arrayList.add(msgText4);
        }
        return new jde(1, new ArrayList(16), arrayList, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        ReleaseLogUtil.e("Notify_PhoneListManager", "doPhoneIdleOrOffHook mIsPhoneForbidden: ", Boolean.valueOf(this.o), " mIsPhoneRing: ", Boolean.valueOf(g));
        this.r = System.currentTimeMillis();
        if (this.o) {
            return;
        }
        if (this.q.h()) {
            LogUtil.a("PhoneListManager", "doPhoneIdleOrOffHook msgType:", Integer.valueOf(i2), " mIsOffHook:", Boolean.valueOf(f));
            if (g) {
                g = false;
                if (i2 == 13) {
                    f = true;
                }
                a(i2);
                return;
            }
            if (f && i2 == 12) {
                f = false;
                a(i2);
                return;
            }
            return;
        }
        if (g) {
            g = false;
            a(12);
        }
    }

    private void a(int i2) {
        ArrayList arrayList = new ArrayList(16);
        MsgText msgText = new MsgText();
        msgText.setTextType(1);
        msgText.setTextContent("");
        arrayList.add(msgText);
        LogUtil.a("PhoneListManager", "sendIdleOrOffHookCommand DeviceProtocol is ", Integer.valueOf(this.q.c()));
        if (this.q.c() == -1) {
            this.q.j();
            return;
        }
        this.q.a(this.q.b(i2, false, (List<MsgImage>) null, (List<MsgText>) arrayList, (String) null), 3);
        jde jdeVar = this.n;
        if (jdeVar == null || jdeVar.a()) {
            return;
        }
        this.n.d(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PhoneListManager", "clearSubscriptionIdMapOnOffHook incomingNumber is empty");
        } else if (kke.h()) {
            kke.b(str);
        }
    }

    private String h(String str) {
        return CommonUtil.bh() ? a(str) : c(str);
    }

    private String c(String str) {
        String str2;
        boolean c2 = jeg.d().c(this.h, "android.permission.READ_CONTACTS");
        ReleaseLogUtil.e("Notify_PhoneListManager", "getContactDisplayNameByNumber hasPermission: ", Boolean.valueOf(c2), " number: ", khs.a(str));
        if (c2) {
            try {
                Cursor query = this.h.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"_id", "display_name"}, null, null, null);
                if (query == null || query.getCount() <= 0) {
                    str2 = str;
                } else {
                    query.moveToNext();
                    str2 = query.getString(query.getColumnIndex("display_name"));
                }
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception unused) {
                        LogUtil.b("PhoneListManager", "getContactDisplayNameByNumber Exception");
                        sqo.af("getContactDisplayNameByNumber Exception");
                        ReleaseLogUtil.e("Notify_PhoneListManager", "getContactDisplayNameByNumber name is equals number:", Boolean.valueOf(TextUtils.equals(str, str2)));
                        return str2;
                    }
                }
            } catch (Exception unused2) {
                str2 = str;
            }
        } else {
            str2 = str;
        }
        ReleaseLogUtil.e("Notify_PhoneListManager", "getContactDisplayNameByNumber name is equals number:", Boolean.valueOf(TextUtils.equals(str, str2)));
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "display_name"
            jeg r1 = defpackage.jeg.d()
            android.content.Context r2 = r11.h
            java.lang.String r3 = "android.permission.READ_CONTACTS"
            boolean r1 = r1.c(r2, r3)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            java.lang.String r3 = " number: "
            java.lang.String r4 = defpackage.khs.a(r12)
            java.lang.String r5 = "getContactDisplayNameByNumberHw hasPermission: "
            java.lang.Object[] r2 = new java.lang.Object[]{r5, r2, r3, r4}
            java.lang.String r3 = "Notify_PhoneListManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r3, r2)
            if (r1 != 0) goto L26
            return r12
        L26:
            android.content.Context r1 = r11.h     // Catch: java.lang.Exception -> L72
            android.content.ContentResolver r4 = r1.getContentResolver()     // Catch: java.lang.Exception -> L72
            android.net.Uri r5 = r11.bOB_(r12)     // Catch: java.lang.Exception -> L72
            r1 = 2
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch: java.lang.Exception -> L72
            java.lang.String r2 = "_id"
            r10 = 0
            r6[r10] = r2     // Catch: java.lang.Exception -> L72
            r2 = 1
            r6[r2] = r0     // Catch: java.lang.Exception -> L72
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L72
            if (r4 == 0) goto L6b
            int r5 = r4.getCount()     // Catch: java.lang.Exception -> L72
            if (r5 <= 0) goto L6b
            int r5 = bOA_(r4, r12)     // Catch: java.lang.Exception -> L72
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L72
            java.lang.String r6 = "getContactDisplayNameByNumberHw contactLookup cursor index:  "
            r1[r10] = r6     // Catch: java.lang.Exception -> L72
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L72
            r1[r2] = r6     // Catch: java.lang.Exception -> L72
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r3, r1)     // Catch: java.lang.Exception -> L72
            if (r5 < 0) goto L6b
            r4.moveToPosition(r5)     // Catch: java.lang.Exception -> L72
            int r0 = r4.getColumnIndex(r0)     // Catch: java.lang.Exception -> L72
            java.lang.String r0 = r4.getString(r0)     // Catch: java.lang.Exception -> L72
            goto L6c
        L6b:
            r0 = r12
        L6c:
            if (r4 == 0) goto L81
            r4.close()     // Catch: java.lang.Exception -> L73
            goto L81
        L72:
            r0 = r12
        L73:
            java.lang.String r1 = "getContactDisplayNameByNumberHw Exception"
            java.lang.Object[] r2 = new java.lang.Object[]{r1}
            java.lang.String r4 = "PhoneListManager"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r2)
            defpackage.sqo.af(r1)
        L81:
            boolean r12 = android.text.TextUtils.equals(r12, r0)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            java.lang.String r1 = "getContactDisplayNameByNumberHw name is equals number:"
            java.lang.Object[] r12 = new java.lang.Object[]{r1, r12}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r3, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.a(java.lang.String):java.lang.String");
    }

    private Uri bOB_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("PhoneListManager", "getContactHwUri number is empty");
            return Uri.EMPTY;
        }
        String j = j(str);
        Uri.Builder buildUpon = ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI.buildUpon();
        buildUpon.appendPath(j);
        buildUpon.appendQueryParameter("sip", "false");
        return buildUpon.build();
    }

    private String j(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 7) {
            LogUtil.b("PhoneListManager", "getLastSevenNumber number is empty or length less than ", 7);
            return str;
        }
        return str.substring(str.length() - 7);
    }

    private static int bOA_(Cursor cursor, String str) {
        if (cursor != null) {
            try {
            } catch (Exception unused) {
                LogUtil.h("PhoneListManager", "getCallerIndex Exception");
                sqo.r("getCallerIndex Exception");
            } catch (NoClassDefFoundError unused2) {
                LogUtil.h("PhoneListManager", "getCallerIndex NoClassDefFoundError");
                sqo.r("getCallerIndex NoClassDefFoundError");
            } catch (NoExtAPIException unused3) {
                LogUtil.h("PhoneListManager", "getCallerIndex NoExtAPIException");
                sqo.r("getCallerIndex NoExtAPIException");
            }
            if (cursor.getCount() != 0 && !TextUtils.isEmpty(str)) {
                CallerInfoHW callerInfoHW = CallerInfoHW.getInstance();
                if (callerInfoHW != null) {
                    return callerInfoHW.getCallerIndex(cursor, str);
                }
                return -1;
            }
        }
        LogUtil.b("PhoneListManager", "getCallerInfoHW cursor is null or cursor count is 0 or number is empty");
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(final String str) {
        if (!TextUtils.isEmpty(str)) {
            i(str);
        } else if (g) {
            ReleaseLogUtil.d("Notify_PhoneListManager", "ringingIncoming phone was ringing, return");
        } else {
            LogUtil.a("PhoneListManager", "ringingIncoming has no number");
            i.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(3000L);
                        LogUtil.a("PhoneListManager", "ringingIncoming mOffHookTime is: ", Long.valueOf(PhoneListManager.this.r));
                        if (System.currentTimeMillis() - PhoneListManager.this.r <= 6000) {
                            LogUtil.h("PhoneListManager", "ringingIncoming less than 6000 millis");
                        } else {
                            LogUtil.a("PhoneListManager", "ringingIncoming more than 6000 millis");
                            PhoneListManager.this.d(str);
                        }
                    } catch (InterruptedException unused) {
                        LogUtil.b("PhoneListManager", "ringingIncoming InterruptedException");
                        sqo.af("ringingIncoming InterruptedException");
                    }
                }
            });
        }
    }

    private static String b(Context context, String str) {
        String str2;
        Throwable th;
        Cursor cursor;
        boolean ci = CommonUtil.ci();
        LogUtil.a("PhoneListManager", "getYellowPagesCursor isUpEmui40: ", Boolean.valueOf(ci), ",number: ", khs.a(str));
        Cursor cursor2 = null;
        r2 = null;
        r2 = null;
        String str3 = null;
        if (!ci) {
            return null;
        }
        try {
            try {
                cursor = context.getContentResolver().query(e, b, "PHONE_NUMBERS_EQUAL(number,?)", new String[]{str}, null);
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } catch (Exception unused) {
            str2 = null;
        }
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    str3 = cursor.getString(0);
                    LogUtil.a("PhoneListManager", "getYellowPagesCursor numbers: ", khs.a(cursor.getString(1)), " name is empty: ", Boolean.valueOf(!TextUtils.isEmpty(str3)));
                } else {
                    LogUtil.h("PhoneListManager", "getYellowPagesCursor cursor.moveToFirst is null");
                }
            } else {
                LogUtil.h("PhoneListManager", "getYellowPagesCursor cursor is null");
            }
            if (cursor == null) {
                return str3;
            }
            cursor.close();
            return str3;
        } catch (Exception unused2) {
            String str4 = str3;
            cursor2 = cursor;
            str2 = str4;
            LogUtil.b("PhoneListManager", "getYellowPagesCursor Exception cursor");
            if (CommonUtil.as()) {
                sqo.af("getYellowPagesCursor Exception cursor");
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return str2;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ff, code lost:
    
        if (r5 == null) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(android.content.Context r16, java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager.d(android.content.Context, java.lang.String):java.lang.String");
    }

    private String bOC_(Context context, Cursor cursor) {
        String str;
        String string = cursor.getString(0);
        String string2 = cursor.getString(1);
        String string3 = cursor.getString(2);
        if (!"others".equals(string2)) {
            if ("fraud".equals(string2)) {
                str = context.getResources().getString(R$string.number_mark_fraud);
            } else if ("crank".equals(string2)) {
                str = context.getResources().getString(R$string.number_mark_crank);
            } else if ("express".equals(string2)) {
                str = context.getResources().getString(R$string.number_mark_express);
            } else if ("house agent".equals(string2)) {
                str = context.getResources().getString(R$string.number_mark_house_agent);
            } else if ("promote sales".equals(string2)) {
                str = context.getResources().getString(R$string.number_mark_promote_sales);
            } else if ("taxi".equals(string2)) {
                str = context.getResources().getString(R$string.number_mark_taxi);
            } else if ("satelite".equals(string2)) {
                str = context.getResources().getString(R$string.contacts_str_filter_Maritime_Satellite_calls);
            }
            ReleaseLogUtil.e("Notify_PhoneListManager", "getNumberMark classify: ", string2, " phoneNum: ", khs.a(string), " phoneName: ", Boolean.valueOf(TextUtils.isEmpty(string3)));
            return str;
        }
        str = string3;
        ReleaseLogUtil.e("Notify_PhoneListManager", "getNumberMark classify: ", string2, " phoneNum: ", khs.a(string), " phoneName: ", Boolean.valueOf(TextUtils.isEmpty(string3)));
        return str;
    }
}
