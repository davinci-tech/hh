package com.huawei.ui.main.stories.history.model;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import defpackage.rds;
import defpackage.rdy;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class SearchSportThreadManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f10316a = new Object();
    private static volatile SearchSportThreadManager d;
    private SearchCallback b;

    public interface SearchCallback {
        void onSearchResult(List<rdy> list);
    }

    private SearchSportThreadManager() {
    }

    public static SearchSportThreadManager b() {
        if (d == null) {
            synchronized (f10316a) {
                if (d == null) {
                    d = new SearchSportThreadManager();
                }
            }
        }
        return d;
    }

    public void b(SearchCallback searchCallback) {
        this.b = searchCallback;
    }

    public void c(final String str, final List<rdy> list) {
        LogUtil.d("R_SearchSportThreadManager", "start updateSearchLocalSport");
        ThreadPoolManager.d().d("searchSportType", new Runnable() { // from class: com.huawei.ui.main.stories.history.model.SearchSportThreadManager.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.d("R_SearchSportThreadManager", "start thread do search");
                List list2 = list;
                if (list2 != null && !list2.isEmpty()) {
                    SearchSportThreadManager.this.e(str, list);
                } else {
                    LogUtil.c("R_SearchSportThreadManager", "Search original list is empty");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, List<rdy> list) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (rdy rdyVar : list) {
            if (a(rds.c(rdyVar), str)) {
                arrayList.add(rdyVar);
            } else {
                arrayList2.add(rdyVar);
            }
        }
        d(arrayList2, arrayList, str);
        SearchCallback searchCallback = this.b;
        if (searchCallback != null) {
            searchCallback.onSearchResult(arrayList);
        }
    }

    private void d(List<rdy> list, List<rdy> list2, String str) {
        for (rdy rdyVar : list) {
            if (d(rds.c(rdyVar), str)) {
                list2.add(rdyVar);
            }
        }
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.toLowerCase(Locale.ENGLISH).contains(str2.toLowerCase(Locale.ENGLISH));
    }

    private boolean d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.c("R_SearchSportThreadManager", "sportText or searchKey is empty");
            return false;
        }
        String replaceAll = str2.toLowerCase(Locale.ENGLISH).replaceAll("\\s*", "");
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            if (length < i2) {
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
        ThreadPoolManager.d().e("searchSportType", null);
        this.b = null;
    }

    public void c() {
        e();
    }

    private static void e() {
        synchronized (f10316a) {
            d = null;
        }
    }
}
