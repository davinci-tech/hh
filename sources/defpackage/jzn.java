package defpackage;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.SyncHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jzn {
    public static List<Bean> d() {
        String[] b = b(0L);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        b(b, concurrentHashMap, false);
        List<Bean> a2 = a(concurrentHashMap, b);
        LogUtil.a("ContactBuilder", "getAllContacts: contacts list size: ", Integer.valueOf(a2.size()));
        return e(a2);
    }

    public static List<Bean> c(long j) {
        String[] b = b(j);
        if (b.length == 0) {
            LogUtil.h("ContactBuilder", "getChangedContacts: raw contact id arrays is empty");
            return new ArrayList(0);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        b(b, concurrentHashMap, false);
        List<Bean> a2 = a(concurrentHashMap, b);
        LogUtil.a("ContactBuilder", "getChangedContacts: changed contacts size: ", Integer.valueOf(a2.size()));
        return a2;
    }

    public static List<String> d(boolean z, long j) {
        LogUtil.a("ContactBuilder", "getContactIdList: start,isProfile,lastSyncedTime", Boolean.valueOf(z), Long.valueOf(j));
        final ArrayList arrayList = new ArrayList(10);
        ContactsDatabaseUtils.b(new ContactsDatabaseUtils.a().bMm_(z ? ContactsContract.Profile.CONTENT_URI : ContactsContract.Contacts.CONTENT_URI).d(new String[]{"_id"}).c(z ? null : kag.b()).c(z ? null : new String[]{String.valueOf(j)}).e("_id"), new ContactsDatabaseUtils.ResultCallback() { // from class: jzl
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
            public final void onResult(Object obj) {
                jzn.bLZ_(arrayList, (Cursor) obj);
            }
        });
        LogUtil.a("ContactBuilder", "getContactIdList: contact id list size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    static /* synthetic */ void bLZ_(List list, Cursor cursor) {
        Objects.requireNonNull(list);
        ContactsDatabaseUtils.bMk_(cursor, 0, new jzm(list));
    }

    public static List<String> c(List<String> list, boolean z) {
        final ArrayList arrayList = new ArrayList(10);
        String b = kat.b(list, ",", "");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("ContactBuilder", "getRawContactIdList: contactIds is null or empty. isProfile: ", Boolean.valueOf(z));
            return arrayList;
        }
        ContactsDatabaseUtils.b(new ContactsDatabaseUtils.a().bMm_(z ? ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI : ContactsContract.RawContacts.CONTENT_URI).d(new String[]{"_id"}).c(z ? null : kag.a(b)).e("_id"), new ContactsDatabaseUtils.ResultCallback() { // from class: jzj
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
            public final void onResult(Object obj) {
                jzn.bMa_(arrayList, (Cursor) obj);
            }
        });
        LogUtil.a("ContactBuilder", "getRawContactIdList raw contact id list size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    static /* synthetic */ void bMa_(List list, Cursor cursor) {
        Objects.requireNonNull(list);
        ContactsDatabaseUtils.bMk_(cursor, 0, new jzm(list));
    }

    public static void b(String[] strArr, Map<String, kaa> map, boolean z) {
        if (map == null) {
            LogUtil.h("ContactBuilder", "buildLocalContact: context or idBeanMap is null.");
            return;
        }
        String b = kat.b(strArr, ",");
        d(b, map, z);
        a(b, map, z);
    }

    public static List<Bean> a(Map<String, kaa> map, String[] strArr) {
        if (map == null || map.size() == 0) {
            return new ArrayList(0);
        }
        boolean z = strArr == null || strArr.length == 0;
        ArrayList arrayList = new ArrayList(10);
        if (z) {
            Iterator<Map.Entry<String, kaa>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getValue());
            }
            return arrayList;
        }
        for (String str : strArr) {
            kaa kaaVar = map.get(str);
            if (kaaVar != null) {
                arrayList.add(kaaVar);
            }
        }
        return arrayList;
    }

    private static String[] b(long j) {
        List<String> c = c(d(false, j), false);
        LogUtil.a("ContactBuilder", "getRawContactIds: raw raw contact id array length: ", Integer.valueOf(c.size()));
        return (String[]) c.toArray(new String[0]);
    }

    private static void d(String str, final Map<String, kaa> map, boolean z) {
        ContactsDatabaseUtils.b(new ContactsDatabaseUtils.a().bMm_(z ? ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI : ContactsContract.RawContacts.CONTENT_URI).d(jzx.b()).c(z ? null : kag.e(str)).e("_id"), new ContactsDatabaseUtils.ResultCallback() { // from class: jzr
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
            public final void onResult(Object obj) {
                ContactsDatabaseUtils.bMk_(r2, 0, new ContactsDatabaseUtils.ResultCallback() { // from class: jzq
                    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
                    public final void onResult(Object obj2) {
                        jzn.bMb_((String) obj2, r1, r2);
                    }
                });
            }
        });
        LogUtil.a("buildWithRawContact: idBean Map size:  ", Integer.valueOf(map.size()));
    }

    private static void a(String str, final Map<String, kaa> map, final boolean z) {
        ContactsDatabaseUtils.b(new ContactsDatabaseUtils.a().bMm_(z ? Uri.parse("content://com.android.contacts/profile/data") : ContactsContract.Data.CONTENT_URI).d(jzx.d()).c(z ? null : kag.b(str)).e("raw_contact_id"), new ContactsDatabaseUtils.ResultCallback() { // from class: jzs
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
            public final void onResult(Object obj) {
                jzn.bMc_(map, z, (Cursor) obj);
            }
        });
        LogUtil.a("buildWithData: ridBeanMap Map size:  ", Integer.valueOf(map.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bMb_(String str, Map<String, kaa> map, Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("ContactBuilder", "parseRawContactBeanPart1: cursor is null.");
            return;
        }
        kaa kaaVar = map.get(str);
        if (kaaVar == null) {
            kaaVar = new kaa();
            kaaVar.setId(str);
            map.put(str, kaaVar);
        }
        kan.bMs_(cursor, kaaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bMc_(Map<String, kaa> map, boolean z, Cursor cursor) {
        if (ContactsDatabaseUtils.bMi_(cursor)) {
            LogUtil.a("ContactBuilder", "parseRawContactBeanPart2: invalid cursor.");
            return;
        }
        String str = "";
        kaa kaaVar = null;
        while (true) {
            String string = cursor.getString(0);
            if (!str.contentEquals(string)) {
                kaaVar = map.get(string);
            }
            if (kaaVar != null) {
                kan.bMr_(cursor, kaaVar);
                kan.bMt_(cursor, kaaVar, z);
            }
            if (!cursor.moveToNext()) {
                return;
            } else {
                str = string;
            }
        }
    }

    private static List<Bean> e(List<Bean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("ContactBuilder", "list is null or empty.");
            return Collections.emptyList();
        }
        for (Bean bean : list) {
            if (TextUtils.isEmpty(bean.getUid())) {
                bean.setUid(SyncHelper.a());
            }
        }
        return list;
    }
}
