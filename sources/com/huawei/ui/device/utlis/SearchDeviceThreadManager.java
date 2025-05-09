package com.huawei.ui.device.utlis;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import defpackage.bkz;
import defpackage.nyq;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class SearchDeviceThreadManager {
    private static final Object b = new Object();
    private static volatile SearchDeviceThreadManager d;
    private SearchCallback c;

    /* loaded from: classes.dex */
    public interface SearchCallback {
        void onSearchResult(List<nyq> list);
    }

    private SearchDeviceThreadManager() {
    }

    public static SearchDeviceThreadManager b() {
        SearchDeviceThreadManager searchDeviceThreadManager;
        synchronized (b) {
            if (d == null) {
                d = new SearchDeviceThreadManager();
            }
            searchDeviceThreadManager = d;
        }
        return searchDeviceThreadManager;
    }

    public void d(SearchCallback searchCallback) {
        this.c = searchCallback;
    }

    public void e(final String str, final List<nyq> list) {
        LogUtil.d("SearchDeviceThreadManager", "start updateSearchLocalDevice");
        ThreadPoolManager.d().d("searchDevice", new Runnable() { // from class: com.huawei.ui.device.utlis.SearchDeviceThreadManager.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.d("SearchDeviceThreadManager", "start thread do search");
                List list2 = list;
                if (list2 != null && !list2.isEmpty()) {
                    SearchDeviceThreadManager.this.c(str, (List<nyq>) list);
                } else {
                    LogUtil.c("SearchDeviceThreadManager", "Search original list is empty");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, List<nyq> list) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (nyq nyqVar : list) {
            if (1 != nyqVar.t() && 2 != nyqVar.t()) {
                String h = nyqVar.h();
                String c = nyqVar.c();
                boolean d2 = d(h, str);
                boolean d3 = d(c, str);
                if (d2 || d3) {
                    nyqVar.j(4);
                    arrayList.add(nyqVar);
                } else {
                    List<String> a2 = nyqVar.a();
                    if (bkz.e(a2)) {
                        arrayList2.add(nyqVar);
                    } else {
                        Iterator<String> it = a2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (d(it.next(), str)) {
                                    nyqVar.j(4);
                                    arrayList.add(nyqVar);
                                    break;
                                }
                            } else {
                                arrayList2.add(nyqVar);
                                break;
                            }
                        }
                    }
                }
            }
        }
        b(arrayList2, arrayList, str);
        SearchCallback searchCallback = this.c;
        if (searchCallback != null) {
            searchCallback.onSearchResult(arrayList);
        }
    }

    private void b(List<nyq> list, List<nyq> list2, String str) {
        for (nyq nyqVar : list) {
            String h = nyqVar.h();
            String c = nyqVar.c();
            boolean c2 = c(h, str);
            boolean c3 = c(c, str);
            if (c2 || c3) {
                nyqVar.j(4);
                list2.add(nyqVar);
            } else {
                List<String> a2 = nyqVar.a();
                if (!bkz.e(a2)) {
                    Iterator<String> it = a2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (d(it.next(), str)) {
                            nyqVar.j(4);
                            list2.add(nyqVar);
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.toLowerCase(Locale.ENGLISH).contains(str2.toLowerCase(Locale.ENGLISH));
    }

    private boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.c("SearchDeviceThreadManager", "deviceText or searchKey is empty");
            return false;
        }
        String replaceAll = str2.toLowerCase(Locale.ENGLISH).replaceAll("\\s*", "");
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            if (i2 > length) {
                break;
            }
            String substring = lowerCase.substring(i, i2);
            if (!TextUtils.isEmpty(substring) && replaceAll.contains(substring)) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    public void d() {
        ThreadPoolManager.d().e("searchDevice", null);
        this.c = null;
    }

    public void e() {
        c();
    }

    private static void c() {
        synchronized (b) {
            d = null;
        }
    }
}
