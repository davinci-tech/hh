package com.huawei.featureuserprofile.sos.manager;

import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.huawei.featureuserprofile.sos.interf.ContactInfoChangeListener;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.ResultCallback;
import defpackage.btz;
import defpackage.bua;
import defpackage.buh;
import defpackage.gmu;
import defpackage.gmw;
import defpackage.gmx;
import defpackage.jdi;
import defpackage.jei;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class EmergencyInfoManager extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static EmergencyInfoManager f2027a;
    private ContactInfoChangeListener f;
    private ContentObserver g;
    private HashMap h;
    private Context i;
    private CountDownTimer j;
    private EmergencyContactChangeReceiver m;
    private BroadcastReceiver n;
    private List<gmx> o;
    private static final String e = "content://" + BaseApplication.getAppPackage() + ".sos.provider";
    private static final Object d = new Object();
    private static final String[] c = {"android.permission.READ_CONTACTS"};
    private static final Uri b = Uri.parse("content://com.android.emergency.EmergencyInfoProvider");

    private EmergencyInfoManager(Context context) {
        super(context);
        this.o = new ArrayList(10);
        this.h = null;
        this.g = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.2
            @Override // android.database.ContentObserver
            public boolean deliverSelfNotifications() {
                return super.deliverSelfNotifications();
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                LogUtil.d("EmergencyInfoManager", "contactObserver onChange");
                EmergencyInfoManager.this.j.cancel();
                EmergencyInfoManager.this.j.start();
            }
        };
        this.n = new BroadcastReceiver() { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                    LogUtil.c("EmergencyInfoManager", "mImageInfoReceiver intent is null");
                    return;
                }
                if ("image_info_sync".equals(intent.getAction())) {
                    Serializable serializableExtra = intent.getSerializableExtra("image_info");
                    if (serializableExtra instanceof HashMap) {
                        EmergencyInfoManager.this.h = (HashMap) serializableExtra;
                    } else {
                        LogUtil.d("EmergencyInfoManager", "image is null");
                    }
                }
            }
        };
        LogUtil.d("EmergencyInfoManager", "EmergencyInfoManager init");
        e(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("image_info_sync");
        BroadcastManagerUtil.bFE_(context, this.n, intentFilter);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager$4] */
    private void e(Context context) {
        this.i = context.getApplicationContext();
        if (!r()) {
            new HandlerThread("EmergencyInfoManager") { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.4
                @Override // android.os.HandlerThread
                protected void onLooperPrepared() {
                    EmergencyInfoManager.this.q();
                }
            }.start();
        } else {
            q();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.j = new CountDownTimer(3000L, 1000L) { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.5
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                EmergencyInfoManager.this.x();
                LogUtil.d("EmergencyInfoManager", "UPDATE_CONTACT");
            }
        };
    }

    private boolean r() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return Integer.valueOf(GLMapStaticValue.MAP_PARAMETERNAME_SCENIC);
    }

    public static EmergencyInfoManager c() {
        EmergencyInfoManager emergencyInfoManager;
        synchronized (d) {
            if (f2027a == null) {
                f2027a = new EmergencyInfoManager(BaseApplication.getContext());
            }
            emergencyInfoManager = f2027a;
        }
        return emergencyInfoManager;
    }

    public void e(ContactInfoChangeListener contactInfoChangeListener) {
        if (contactInfoChangeListener != null) {
            this.f = contactInfoChangeListener;
            LogUtil.d("EmergencyInfoManager", "setContactInfoChangeListener");
        } else {
            LogUtil.c("EmergencyInfoManager", "setContactInfoChangeListener contactChangeListener is null");
        }
    }

    public void n() {
        LogUtil.d("EmergencyInfoManager", "removeContactInfoChangeListener");
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        List<gmx> c2 = c(this.i);
        boolean z = this.o.size() != c2.size();
        Iterator<gmx> it = this.o.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            gmx next = it.next();
            if (next != null) {
                if (z) {
                    LogUtil.d("EmergencyInfoManager", "break in mEmergencyContactsUris");
                    break;
                }
                Iterator<gmx> it2 = c2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    gmx next2 = it2.next();
                    if (next2 == null) {
                        LogUtil.c("EmergencyInfoManager", "onUpdatingContact newInfo is null");
                    } else if (next.d() == null || next2.d() == null) {
                        LogUtil.c("EmergencyInfoManager", "refreshUi getUri() is null");
                    } else if (next.e() == null || next2.e() == null) {
                        LogUtil.c("EmergencyInfoManager", "refreshUi getContactVersion() is null");
                    } else if (next.d().equals(next2.d()) && !next.e().equals(next2.e())) {
                        LogUtil.d("EmergencyInfoManager", "break in updateContactsUris");
                        z = true;
                        break;
                    }
                }
            } else {
                LogUtil.c("EmergencyInfoManager", "onUpdatingContact contactInfo is null");
            }
        }
        LogUtil.d("EmergencyInfoManager", "onUpdatingContact isContactInfoChanged:", Boolean.valueOf(z));
        if (z) {
            e(c2, true);
            ContactInfoChangeListener contactInfoChangeListener = this.f;
            if (contactInfoChangeListener != null) {
                contactInfoChangeListener.onContactInfoChange();
            }
        }
        this.o = c2;
    }

    public void l() {
        if (this.m == null) {
            LogUtil.d("EmergencyInfoManager", "Enter registerEmergencyContactChange()");
            this.m = new EmergencyContactChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.emergency.action.EMERGENCY_INFO_CHANGED");
            BroadcastManagerUtil.bFB_(this.i, this.m, intentFilter);
        }
    }

    public void s() {
        if (this.m != null) {
            LogUtil.d("EmergencyInfoManager", "Enter unregisterEmergencyContactChange");
            try {
                this.i.unregisterReceiver(this.m);
            } catch (IllegalArgumentException e2) {
                LogUtil.e("EmergencyInfoManager", "unregisterEmergencyContactChange illegalArgumentException ", LogAnonymous.b((Throwable) e2));
            }
            this.m = null;
        }
    }

    public boolean g() {
        return jdi.c(this.i, c);
    }

    public void m() {
        if (g()) {
            try {
                this.i.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, this.g);
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (EmergencyInfoManager.this.f()) {
                            EmergencyInfoManager.this.l();
                        }
                    }
                });
                LogUtil.d("EmergencyInfoManager", "registerContactChangeObserver");
                return;
            } catch (SecurityException unused) {
                ReleaseLogUtil.c("R_EmergencyInfoManager", "registerContactChangeObserver securityException");
                return;
            }
        }
        LogUtil.d("EmergencyInfoManager", "no READ_CONTACTS permission");
    }

    public void k() {
        this.i.getContentResolver().unregisterContentObserver(this.g);
        s();
        LogUtil.d("EmergencyInfoManager", "unRegisterContactChangeObserver");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00e8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[Catch: SQLiteException | SecurityException -> 0x00bc, SYNTHETIC, TRY_LEAVE, TryCatch #7 {SQLiteException | SecurityException -> 0x00bc, blocks: (B:54:0x00bb, B:53:0x00b8, B:48:0x00b2), top: B:47:0x00b2, inners: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.c ve_(android.net.Uri r19) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.ve_(android.net.Uri):com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager$c");
    }

    private Bitmap va_(Long l) {
        Cursor query = this.i.getContentResolver().query(ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, l.longValue()), new String[]{"data15"}, null, null, null);
        Bitmap bitmap = null;
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    byte[] blob = query.getBlob(0);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    vc_(options);
                    bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(blob), null, options);
                }
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (query != null) {
            query.close();
        }
        return bitmap;
    }

    private void vc_(BitmapFactory.Options options) {
        HashMap hashMap = this.h;
        if (hashMap != null) {
            Object obj = hashMap.get("image_color_type_tag");
            if (obj instanceof String) {
                String str = (String) obj;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                int w = CommonUtil.w(str);
                if (w == 0) {
                    options.inPreferredConfig = Bitmap.Config.ALPHA_8;
                } else if (w == 1) {
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                } else {
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                }
            }
        }
    }

    private void uY_(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    public boolean vg_(Context context, Uri uri) {
        return (context == null || uri == null || !uZ_(context, uri)) ? false : true;
    }

    private boolean uZ_(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            try {
                cursor = context.getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        return true;
                    }
                }
            } catch (SQLiteException | SecurityException unused) {
                LogUtil.e("EmergencyInfoManager", "contactExists catch Exception");
            }
            return false;
        } finally {
            uY_(cursor);
        }
    }

    public void a(String str, String str2) {
        char c2;
        if (str == null || str2 == null) {
            LogUtil.c("EmergencyInfoManager", "updateEmergencyInfo key or value is null");
            return;
        }
        try {
            gmu gmuVar = (gmu) new Gson().fromJson(i(), gmu.class);
            if (gmuVar == null) {
                gmuVar = new gmu();
            }
            if (!TextUtils.isEmpty(str)) {
                switch (str.hashCode()) {
                    case -1148703137:
                        if (str.equals("blood_type")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -1147692044:
                        if (str.equals("address")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -1099451720:
                        if (str.equals("organ_donor")) {
                            c2 = 6;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3373707:
                        if (str.equals("name")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 81679390:
                        if (str.equals("allergies")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 522223425:
                        if (str.equals("emergency_contacts")) {
                            c2 = 7;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1838387076:
                        if (str.equals("medications")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 2111429926:
                        if (str.equals("medical_conditions")) {
                            c2 = 5;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                switch (c2) {
                    case 0:
                        gmuVar.b(str2);
                        break;
                    case 1:
                        gmuVar.a(str2);
                        break;
                    case 2:
                        gmuVar.a(btz.a(this.i, str2));
                        break;
                    case 3:
                        gmuVar.c(str2);
                        break;
                    case 4:
                        gmuVar.d(str2);
                        break;
                    case 5:
                        gmuVar.e(str2);
                        break;
                    case 6:
                        gmuVar.c(btz.d(this.i, str2));
                        break;
                    case 7:
                        d(gmuVar);
                        break;
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "updateEmergencyInfo default";
                        LogUtil.d("EmergencyInfoManager", objArr);
                        break;
                }
            }
            e(str, gmuVar);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("EmergencyInfoManager", "updateEmergencyInfo JsonSyntaxException");
        }
    }

    public void b() {
        for (String str : gmw.d()) {
            if (str.equals("blood_type")) {
                setSharedPreference(btz.c() + str, this.i.getResources().getString(R.string._2130848862_res_0x7f022c5e), new StorageParams(1));
            } else if (str.equals("organ_donor")) {
                setSharedPreference(btz.c() + str, this.i.getResources().getString(R.string._2130848864_res_0x7f022c60), new StorageParams(1));
            } else {
                setSharedPreference(btz.c() + str, "", new StorageParams(1));
            }
        }
        PreferenceManager.getDefaultSharedPreferences(this.i).edit().remove(btz.c() + "emergency_contacts").apply();
        setSharedPreference(btz.c() + "_basic", "", new StorageParams(1));
        b("key_clear_all_emergency", p());
    }

    public String i() {
        return getSharedPreference(btz.c() + "_basic");
    }

    private void d(gmu gmuVar) {
        gmuVar.c(b(btz.c("emergency_contacts", this.i, btz.a(this.i))));
    }

    private void e(String str, gmu gmuVar) {
        try {
            setSharedPreference(btz.c() + "_basic", new Gson().toJson(gmuVar), new StorageParams(1));
            b(str, gmuVar);
        } catch (JsonIOException unused) {
            LogUtil.d("EmergencyInfoManager", "saveAndCallBack catch JsonIOException");
        }
    }

    private void b(String str, gmu gmuVar) {
        Intent intent = new Intent();
        intent.setAction("emergency_info_change");
        intent.putExtra("emergency_info_key", str);
        intent.putExtra("emergency_info_value", gmuVar);
        BroadcastManagerUtil.bFH_(this.i, intent, LocalBroadcast.c, true);
    }

    private ArrayList<gmu.e> b(List<gmx> list) {
        ArrayList<gmu.e> arrayList = new ArrayList<>(10);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                gmx gmxVar = list.get(i);
                if (gmxVar != null) {
                    String d2 = gmxVar.d();
                    if (TextUtils.isEmpty(d2)) {
                        LogUtil.d("EmergencyInfoManager", "contactInfo.getUri() is null");
                    } else {
                        Uri parse = Uri.parse(d2);
                        if (parse == null) {
                            LogUtil.d("EmergencyInfoManager", "updateEmergencyContacts contactUri is null ");
                        } else if (vg_(this.i, parse)) {
                            c ve_ = ve_(parse);
                            if (ve_ != null) {
                                gmu.e eVar = new gmu.e();
                                eVar.a(i);
                                eVar.b(ve_.b());
                                eVar.e(ve_.a());
                                a(ve_, eVar);
                                arrayList.add(eVar);
                            }
                        } else {
                            gmu.e eVar2 = new gmu.e();
                            eVar2.a(i);
                            eVar2.b(gmxVar.a());
                            eVar2.e(gmxVar.c());
                            arrayList.add(eVar2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private void a(c cVar, gmu.e eVar) {
        Bitmap vi_ = cVar.vi_();
        if (vi_ != null) {
            LogUtil.d("EmergencyInfoManager", "bitmap:", Integer.valueOf(vi_.getByteCount()), "with:", Integer.valueOf(vi_.getWidth()), "height", Integer.valueOf(vi_.getHeight()));
            HashMap hashMap = this.h;
            if (hashMap == null) {
                vd_(eVar, vi_);
                return;
            }
            Object obj = hashMap.get("image_size_tag");
            if (!(obj instanceof String)) {
                vd_(eVar, vi_);
                return;
            }
            String str = (String) obj;
            if (TextUtils.isEmpty(str)) {
                vd_(eVar, vi_);
                return;
            }
            String d2 = HEXUtils.d(str);
            if (TextUtils.isEmpty(d2)) {
                vd_(eVar, vi_);
                return;
            }
            try {
                int parseInt = Integer.parseInt(d2);
                LogUtil.d("EmergencyInfoManager", "newImageSize:", Integer.valueOf(parseInt));
                if (parseInt > 0) {
                    vd_(eVar, nrf.cJJ_(vi_, parseInt, parseInt));
                } else {
                    vd_(eVar, vi_);
                }
                return;
            } catch (NumberFormatException unused) {
                LogUtil.e("EmergencyInfoManager", "updateContacts contact catch NumberFormatException");
                vd_(eVar, vi_);
                return;
            }
        }
        LogUtil.d("EmergencyInfoManager", "updateContacts contact photo is null");
        eVar.c(new byte[0]);
    }

    private void vd_(gmu.e eVar, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        LogUtil.d("EmergencyInfoManager", "setPhotoByte size = ", Integer.valueOf(byteArray.length));
        if (byteArray.length > 15360) {
            byteArray = nrf.cHu_(bitmap, 15);
        }
        eVar.c(byteArray);
        jei.c(byteArrayOutputStream);
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private Uri f2031a;
        private String b;
        private Uri c;
        private String d;
        private long e;
        private String h;
        private Bitmap i;

        public c(Uri uri, Uri uri2, String str, String str2, String str3) {
            this.f2031a = uri;
            this.c = uri2;
            this.b = str;
            this.d = str2;
            this.h = str3;
        }

        public c(Uri uri, String str, String str2) {
            this.c = uri;
            this.b = str;
            this.d = str2;
        }

        public Uri vh_() {
            return this.c;
        }

        public String b() {
            return this.b;
        }

        public String a() {
            return this.d;
        }

        public Bitmap vi_() {
            return this.i;
        }

        public long c() {
            return this.e;
        }

        public void vj_(Bitmap bitmap) {
            this.i = bitmap;
        }

        public void c(long j) {
            this.e = j;
        }
    }

    public Long a(Context context, Long l) {
        long j = 0;
        if (context == null) {
            LogUtil.e("EmergencyInfoManager", "getContactsVersion context is null.");
            return 0L;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = context.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, null, "_id=?", new String[]{String.valueOf(l)}, null);
                while (cursor != null) {
                    if (!cursor.moveToNext()) {
                        break;
                    }
                    j = cursor.getLong(cursor.getColumnIndex("version"));
                }
                uY_(cursor);
                return Long.valueOf(j);
            } catch (SQLiteException | SecurityException unused) {
                LogUtil.e("EmergencyInfoManager", "getContactVersion catch Exception");
                uY_(cursor);
                return Long.valueOf(j);
            }
        } catch (Throwable th) {
            uY_(cursor);
            throw th;
        }
    }

    public List<gmx> c(Context context) {
        if (context == null) {
            LogUtil.e("EmergencyInfoManager", "getEmergencyContactInfo context is null.");
            return Collections.emptyList();
        }
        return btz.c("emergency_contacts", context, btz.a(context));
    }

    public void e(List<gmx> list, boolean z) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.i);
        String b2 = btz.b(list, this.i);
        defaultSharedPreferences.edit().putString(btz.c() + "emergency_contacts", b2).apply();
        LogUtil.d("EmergencyInfoManager", "updateContact method updateEmergencyContact. isSynDevice ", Boolean.valueOf(z));
        if (z) {
            a("emergency_contacts", b2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0046, code lost:
    
        if (r3 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x006a, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006b, code lost:
    
        if (r3 == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x006d, code lost:
    
        health.compact.a.util.LogUtil.c("EmergencyInfoManager", "getEmergencyInfo cursor is null");
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0076, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0077, code lost:
    
        if (r10 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0079, code lost:
    
        health.compact.a.util.LogUtil.c("EmergencyInfoManager", "getEmergencyInfo bundle is null");
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0082, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0083, code lost:
    
        r3 = (defpackage.gmu) new com.google.gson.Gson().fromJson(r10.getString("value"), defpackage.gmu.class);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0097, code lost:
    
        if (r3 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0099, code lost:
    
        health.compact.a.util.LogUtil.c("EmergencyInfoManager", "getEmergencyInfo from json info is null");
        r3 = p();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bc, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a7, code lost:
    
        health.compact.a.util.LogUtil.d("EmergencyInfoManager", "getEmergencyInfo success");
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b1, code lost:
    
        r0 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b2, code lost:
    
        health.compact.a.util.LogUtil.e("EmergencyInfoManager", "getEmergencyInfo catch JsonSyntaxException");
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
    
        r3.close();
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0063, code lost:
    
        if (r3 == null) goto L23;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.gmu a() {
        /*
            r11 = this;
            gmu r0 = r11.p()
            android.content.Context r1 = r11.i
            java.lang.String r2 = "EmergencyInfoManager"
            if (r1 != 0) goto L14
            java.lang.String r1 = "getInfoMessage context is null"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.c(r2, r1)
            return r0
        L14:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.e
            r1.append(r3)
            java.lang.String r3 = "/getString"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.net.Uri r4 = android.net.Uri.parse(r1)
            r1 = 0
            r9 = 1
            r10 = 0
            android.content.Context r3 = r11.i     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            if (r3 == 0) goto L46
            android.os.Bundle r4 = r3.getExtras()     // Catch: java.lang.Exception -> L44 java.lang.Throwable -> Lbd
            r10 = r4
            goto L46
        L44:
            r4 = move-exception
            goto L4f
        L46:
            if (r3 == 0) goto L6a
            goto L65
        L49:
            r0 = move-exception
            goto Lbf
        L4c:
            r3 = move-exception
            r4 = r3
            r3 = r10
        L4f:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r6 = "getEmergencyInfo exception:"
            r5[r1] = r6     // Catch: java.lang.Throwable -> Lbd
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r4 = r4.getSimpleName()     // Catch: java.lang.Throwable -> Lbd
            r5[r9] = r4     // Catch: java.lang.Throwable -> Lbd
            health.compact.a.util.LogUtil.e(r2, r5)     // Catch: java.lang.Throwable -> Lbd
            if (r3 == 0) goto L6a
        L65:
            r3.close()
            r3 = r1
            goto L6b
        L6a:
            r3 = r9
        L6b:
            if (r3 == 0) goto L77
            java.lang.String r1 = "getEmergencyInfo cursor is null"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.c(r2, r1)
            return r0
        L77:
            if (r10 != 0) goto L83
            java.lang.String r1 = "getEmergencyInfo bundle is null"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.c(r2, r1)
            return r0
        L83:
            java.lang.String r3 = "value"
            java.lang.String r3 = r10.getString(r3)     // Catch: com.google.gson.JsonSyntaxException -> Lb2
            com.google.gson.Gson r4 = new com.google.gson.Gson     // Catch: com.google.gson.JsonSyntaxException -> Lb2
            r4.<init>()     // Catch: com.google.gson.JsonSyntaxException -> Lb2
            java.lang.Class<gmu> r5 = defpackage.gmu.class
            java.lang.Object r3 = r4.fromJson(r3, r5)     // Catch: com.google.gson.JsonSyntaxException -> Lb2
            gmu r3 = (defpackage.gmu) r3     // Catch: com.google.gson.JsonSyntaxException -> Lb2
            if (r3 != 0) goto La7
            java.lang.Object[] r0 = new java.lang.Object[r9]     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            java.lang.String r4 = "getEmergencyInfo from json info is null"
            r0[r1] = r4     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            health.compact.a.util.LogUtil.c(r2, r0)     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            gmu r3 = r11.p()     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            goto Lbc
        La7:
            java.lang.Object[] r0 = new java.lang.Object[r9]     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            java.lang.String r4 = "getEmergencyInfo success"
            r0[r1] = r4     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            health.compact.a.util.LogUtil.d(r2, r0)     // Catch: com.google.gson.JsonSyntaxException -> Lb1
            goto Lbc
        Lb1:
            r0 = r3
        Lb2:
            java.lang.String r1 = "getEmergencyInfo catch JsonSyntaxException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r2, r1)
            r3 = r0
        Lbc:
            return r3
        Lbd:
            r0 = move-exception
            r10 = r3
        Lbf:
            if (r10 == 0) goto Lc4
            r10.close()
        Lc4:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.a():gmu");
    }

    private gmu p() {
        LogUtil.d("EmergencyInfoManager", "create default emergencyInfo");
        gmu gmuVar = new gmu();
        gmuVar.b("");
        gmuVar.a("");
        gmuVar.a(0);
        gmuVar.c("");
        gmuVar.c(0);
        gmuVar.d("");
        gmuVar.e("");
        gmuVar.c(new ArrayList<>(10));
        return gmuVar;
    }

    public gmx vf_(Uri uri) {
        gmx gmxVar = new gmx();
        if (uri == null) {
            LogUtil.c("EmergencyInfoManager", "getContactInfoByUri uri is null");
            return gmxVar;
        }
        c ve_ = ve_(uri);
        if (ve_ != null) {
            Long a2 = a(this.i, Long.valueOf(ve_.c()));
            gmxVar.b(uri.toString());
            gmxVar.c(String.valueOf(a2));
        } else {
            LogUtil.c("EmergencyInfoManager", "getContactInfoByUri contact is null");
        }
        return gmxVar;
    }

    public class EmergencyContactChangeReceiver extends BroadcastReceiver {
        public EmergencyContactChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || context == null) {
                LogUtil.c("EmergencyInfoManager", "getContact context or contactUri is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.c("EmergencyInfoManager", "EmergencyContactChangeReceiver action : ", action);
            if ("com.huawei.emergency.action.EMERGENCY_INFO_CHANGED".equals(action)) {
                EmergencyInfoManager.this.c(true);
                if (Utils.o()) {
                    return;
                }
                EmergencyInfoManager.this.o();
            }
        }
    }

    public void t() {
        gmu gmuVar;
        String i = c().i();
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(i)) {
            try {
                gmuVar = (gmu) gson.fromJson(i, gmu.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.e("EmergencyInfoManager", "updateEmergencyInfoProvider JsonSyntaxException");
                gmuVar = null;
            }
            if (gmuVar != null) {
                gmuVar.c(b(c(this.i)));
            } else {
                gmuVar = p();
            }
        } else {
            gmuVar = p();
        }
        setSharedPreference(btz.c() + "_basic", gson.toJson(gmuVar), new StorageParams(1));
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0103: MOVE (r5 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:34:0x0103 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(boolean r10) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.c(boolean):void");
    }

    public void o() {
        if (!f()) {
            LogUtil.d("EmergencyInfoManager", "hasEmergencyInfoProvider return false");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.10
                @Override // java.lang.Runnable
                public void run() {
                    buh.d(new ResultCallback<bua>() { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.10.3
                        @Override // com.huawei.networkclient.ResultCallback
                        /* renamed from: c, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(bua buaVar) {
                            if (buaVar.getResultCode().intValue() == 0 && !koq.b(buaVar.e()) && !koq.b(buaVar.a()) && buaVar.e().size() == buaVar.a().size()) {
                                EmergencyInfoManager.this.e(buaVar);
                            } else {
                                LogUtil.d("EmergencyInfoManager", "queryFollowUsers failure, resultCode = ", buaVar.getResultCode());
                            }
                        }

                        @Override // com.huawei.networkclient.ResultCallback
                        public void onFailure(Throwable th) {
                            LogUtil.e("EmergencyInfoManager", "queryFollowUsers failure");
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(bua buaVar) {
        List<gmx> h = h();
        ArrayList arrayList = new ArrayList();
        if (koq.b(h)) {
            c(buaVar, arrayList);
        } else {
            LogUtil.d("EmergencyInfoManager", "delete server some data");
            b(buaVar, h, arrayList);
        }
        d(arrayList);
    }

    private void c(bua buaVar, List<Long> list) {
        for (bua.b bVar : buaVar.a()) {
            if (!TextUtils.isEmpty(bVar.d()) && !TextUtils.isEmpty(bVar.c())) {
                list.add(Long.valueOf(bVar.a()));
            } else {
                LogUtil.d("EmergencyInfoManager", "huid:", Long.valueOf(bVar.a()), " name or phoneNum is null");
            }
        }
    }

    private void b(bua buaVar, List<gmx> list, List<Long> list2) {
        for (bua.b bVar : buaVar.a()) {
            if (TextUtils.isEmpty(bVar.d()) || TextUtils.isEmpty(bVar.c())) {
                LogUtil.d("EmergencyInfoManager", "familyCareRelation name or phoneNum is null,contactInfo:", Long.valueOf(bVar.a()));
            } else {
                b(list, list2, bVar);
            }
        }
    }

    private void b(List<gmx> list, List<Long> list2, bua.b bVar) {
        for (gmx gmxVar : list) {
            if (bVar.d().equals(gmxVar.a()) && bVar.c().equals(gmxVar.c())) {
                LogUtil.d("EmergencyInfoManager", "server and local is both exist,contactInfo:", Long.valueOf(bVar.a()));
                return;
            }
        }
        list2.add(Long.valueOf(bVar.a()));
    }

    private void d(List<Long> list) {
        if (koq.b(list)) {
            LogUtil.d("EmergencyInfoManager", "deleteFamilyCareUsers,delEmergencyList is empty");
        } else {
            buh.a(list, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.7
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                    LogUtil.d("EmergencyInfoManager", "deleteFamilyCareUsers successful");
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.e("EmergencyInfoManager", "deleteFamilyCareUsers failure");
                }
            });
        }
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0081: MOVE (r4 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:34:0x0081 */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean f() {
        /*
            r8 = this;
            boolean r0 = health.compact.a.CommonUtil.ar()
            java.lang.String r1 = "EmergencyInfoManager"
            r2 = 0
            if (r0 != 0) goto L13
            java.lang.String r0 = "hasEmergencyInfoProvider emui version less than 10.0.0"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.d(r1, r0)
            return r2
        L13:
            android.content.Context r0 = r8.i
            if (r0 != 0) goto L21
            java.lang.String r0 = "hasEmergencyInfoProvider context is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.c(r1, r0)
            return r2
        L21:
            android.content.ContentResolver r0 = r0.getContentResolver()
            if (r0 != 0) goto L31
            java.lang.String r0 = "hasEmergencyInfoProvider contentResolver is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.c(r1, r0)
            return r2
        L31:
            r3 = 1
            r4 = 0
            android.net.Uri r5 = com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.b     // Catch: java.lang.Throwable -> L51 java.lang.SecurityException -> L53 java.lang.IllegalArgumentException -> L5e
            android.content.ContentProviderClient r6 = r0.acquireUnstableContentProviderClient(r5)     // Catch: java.lang.Throwable -> L51 java.lang.SecurityException -> L53 java.lang.IllegalArgumentException -> L5e
            if (r6 != 0) goto L48
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.IllegalArgumentException -> L4f java.lang.SecurityException -> L54 java.lang.Throwable -> L80
            java.lang.String r5 = "emergencyInfoProviderClient is null"
            r0[r2] = r5     // Catch: java.lang.IllegalArgumentException -> L4f java.lang.SecurityException -> L54 java.lang.Throwable -> L80
            health.compact.a.util.LogUtil.c(r1, r0)     // Catch: java.lang.IllegalArgumentException -> L4f java.lang.SecurityException -> L54 java.lang.Throwable -> L80
            vb_(r6)
            return r2
        L48:
            java.lang.String r7 = "QUERY_EMERGENCY_INFO"
            android.os.Bundle r4 = r0.call(r5, r7, r4, r4)     // Catch: java.lang.IllegalArgumentException -> L4f java.lang.SecurityException -> L54 java.lang.Throwable -> L80
            goto L70
        L4f:
            r0 = move-exception
            goto L60
        L51:
            r0 = move-exception
            goto L82
        L53:
            r6 = r4
        L54:
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L80
            java.lang.String r5 = "hasEmergencyInfoProvider securityException"
            r0[r2] = r5     // Catch: java.lang.Throwable -> L80
            health.compact.a.util.LogUtil.e(r1, r0)     // Catch: java.lang.Throwable -> L80
            goto L70
        L5e:
            r0 = move-exception
            r6 = r4
        L60:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L80
            java.lang.String r7 = "hasEmergencyInfoProvider getContentResolver call "
            r5[r2] = r7     // Catch: java.lang.Throwable -> L80
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L80
            r5[r3] = r0     // Catch: java.lang.Throwable -> L80
            health.compact.a.util.LogUtil.e(r1, r5)     // Catch: java.lang.Throwable -> L80
        L70:
            vb_(r6)
            if (r4 == 0) goto L7f
            java.lang.String r0 = "hasEmergencyInfoProvider bundle is not null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.d(r1, r0)
            return r3
        L7f:
            return r2
        L80:
            r0 = move-exception
            r4 = r6
        L82:
            vb_(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.f():boolean");
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.n;
        if (broadcastReceiver != null) {
            BroadcastManagerUtil.bFK_(this.i, broadcastReceiver);
        }
    }

    public boolean c(gmx gmxVar) {
        List<gmx> c2 = btz.c("emergency_contacts", BaseApplication.getContext(), btz.a(BaseApplication.getContext()));
        LogUtil.d("EmergencyInfoManager", "saveAppEmergencyContacts: ", Integer.valueOf(c2.size()));
        if (c2.contains(gmxVar)) {
            return false;
        }
        c2.add(gmxVar);
        LogUtil.d("EmergencyInfoManager", "Add to first size: ", Integer.valueOf(c2.size()));
        c().e(c2, true);
        return true;
    }

    public List<gmx> d() {
        List<gmx> c2 = btz.c("emergency_contacts", BaseApplication.getContext(), btz.a(BaseApplication.getContext()));
        LogUtil.d("EmergencyInfoManager", "exceedEmergencyContact: ", Integer.valueOf(c2.size()));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < c2.size(); i++) {
            arrayList.add(e(c2.get(i)));
            LogUtil.d("EmergencyInfoManager", "redContactInfo: ", Integer.valueOf(arrayList.size()));
        }
        return arrayList;
    }

    private gmx e(gmx gmxVar) {
        c ve_;
        if (gmxVar != null && gmxVar.b() && (ve_ = c().ve_(Uri.parse(gmxVar.d()))) != null) {
            gmxVar.d(ve_.a());
            gmxVar.a(ve_.b());
        }
        return gmxVar;
    }

    public List<gmx> h() {
        List<String> j = j();
        ArrayList arrayList = new ArrayList();
        if (j.size() > 0) {
            for (int i = 0; i < j.size(); i++) {
                Uri parse = Uri.parse(j.get(i));
                if (c().vg_(BaseApplication.getContext(), parse) && arrayList.size() < 3) {
                    c ve_ = c().ve_(parse);
                    gmx vf_ = c().vf_(parse);
                    if (ve_ != null) {
                        vf_.a(ve_.b());
                        vf_.d(ve_.a());
                        arrayList.add(vf_);
                    } else {
                        LogUtil.d("EmergencyInfoManager", "contact empty");
                    }
                }
                LogUtil.d("EmergencyInfoManager", "filteredEmergencyContacts", Integer.valueOf(arrayList.size()));
            }
        } else {
            LogUtil.d("EmergencyInfoManager", "emergencyContactsArray", Integer.valueOf(j.size()));
        }
        return arrayList;
    }

    public List<gmx> e() {
        if (f()) {
            return h();
        }
        return d();
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0099: MOVE (r4 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:29:0x0099 */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<java.lang.String> j() {
        /*
            r8 = this;
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r1 = "EmergencyInfoManager"
            if (r0 != 0) goto L1b
            java.lang.String r0 = "hasEmergencyInfoProvider contentResolver is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.c(r1, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        L1b:
            r2 = 0
            r3 = 1
            r4 = 0
            android.net.Uri r5 = com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.b     // Catch: java.lang.Throwable -> L41 java.lang.SecurityException -> L43 java.lang.IllegalArgumentException -> L4e
            android.content.ContentProviderClient r6 = r0.acquireUnstableContentProviderClient(r5)     // Catch: java.lang.Throwable -> L41 java.lang.SecurityException -> L43 java.lang.IllegalArgumentException -> L4e
            if (r6 != 0) goto L38
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.IllegalArgumentException -> L3f java.lang.SecurityException -> L44 java.lang.Throwable -> L98
            java.lang.String r5 = "emergencyInfoProviderClient is null"
            r0[r2] = r5     // Catch: java.lang.IllegalArgumentException -> L3f java.lang.SecurityException -> L44 java.lang.Throwable -> L98
            health.compact.a.util.LogUtil.c(r1, r0)     // Catch: java.lang.IllegalArgumentException -> L3f java.lang.SecurityException -> L44 java.lang.Throwable -> L98
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.IllegalArgumentException -> L3f java.lang.SecurityException -> L44 java.lang.Throwable -> L98
            r0.<init>()     // Catch: java.lang.IllegalArgumentException -> L3f java.lang.SecurityException -> L44 java.lang.Throwable -> L98
            vb_(r6)
            return r0
        L38:
            java.lang.String r7 = "QUERY_EMERGENCY_INFO"
            android.os.Bundle r4 = r0.call(r5, r7, r4, r4)     // Catch: java.lang.IllegalArgumentException -> L3f java.lang.SecurityException -> L44 java.lang.Throwable -> L98
            goto L60
        L3f:
            r0 = move-exception
            goto L50
        L41:
            r0 = move-exception
            goto L9a
        L43:
            r6 = r4
        L44:
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L98
            java.lang.String r3 = "hasEmergencyInfoProvider securityException"
            r0[r2] = r3     // Catch: java.lang.Throwable -> L98
            health.compact.a.util.LogUtil.e(r1, r0)     // Catch: java.lang.Throwable -> L98
            goto L60
        L4e:
            r0 = move-exception
            r6 = r4
        L50:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L98
            java.lang.String r7 = "hasEmergencyInfoProvider getContentResolver call "
            r5[r2] = r7     // Catch: java.lang.Throwable -> L98
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L98
            r5[r3] = r0     // Catch: java.lang.Throwable -> L98
            health.compact.a.util.LogUtil.e(r1, r5)     // Catch: java.lang.Throwable -> L98
        L60:
            vb_(r6)
            if (r4 == 0) goto L92
            java.lang.String r0 = "emergency_contacts"
            java.lang.String r0 = r4.getString(r0)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L92
            java.lang.String r2 = "|"
            java.lang.String r2 = java.util.regex.Pattern.quote(r2)
            java.lang.String[] r0 = r0.split(r2)
            java.util.List r0 = java.util.Arrays.asList(r0)
            int r2 = r0.size()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r3 = "emergencyContactsList: "
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            health.compact.a.util.LogUtil.d(r1, r2)
            return r0
        L92:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        L98:
            r0 = move-exception
            r4 = r6
        L9a:
            vb_(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager.j():java.util.List");
    }

    private static void vb_(ContentProviderClient contentProviderClient) {
        if (contentProviderClient == null) {
            return;
        }
        try {
            contentProviderClient.close();
        } catch (Exception e2) {
            LogUtil.e("EmergencyInfoManager", "release providerClient failed with exception: ", LogAnonymous.b((Throwable) e2));
        }
    }
}
