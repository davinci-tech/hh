package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.SearchResultFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class ote {
    private WeakReference<SearchResultFragment> d;
    private ConcurrentHashMap<Integer, Object> e = new ConcurrentHashMap<>();
    private otd b = new otd();

    public ote(SearchResultFragment searchResultFragment) {
        this.d = new WeakReference<>(searchResultFragment);
    }

    public void e(final String str) {
        if (c()) {
            final int i = 203;
            if (TextUtils.isEmpty(str)) {
                c(203, false);
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: ote.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Map<String, Object> d = ote.this.b.d(str);
                        if (!d.containsKey("ALL_DEVICE_KEY") || !d.containsKey("CONNECTED_DEVICE_KEY")) {
                            ote.this.c(i, false);
                        } else {
                            ote.this.e.put(Integer.valueOf(i), d);
                            ote.this.c(i, true);
                        }
                    }
                });
            }
        }
    }

    public static boolean b() {
        if (!TextUtils.isEmpty(GlobalSearchActivity.b())) {
            return true;
        }
        LogUtil.a("SearchUtils", "isLessonAggregateSearch lessonSubCategory is null");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(final java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "SearchUtils"
            boolean r1 = r8.c()
            if (r1 != 0) goto L9
            return
        L9:
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            android.os.Looper r2 = android.os.Looper.myLooper()
            if (r1 != r2) goto L20
            com.huawei.haf.threadpool.ThreadPoolManager r0 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            ote$4 r1 = new ote$4
            r1.<init>()
            r0.execute(r1)
            return
        L20:
            boolean r1 = b()
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L2a
            r1 = r3
            goto L2b
        L2a:
            r1 = r2
        L2b:
            java.util.concurrent.CountDownLatch r4 = new java.util.concurrent.CountDownLatch
            r4.<init>(r1)
            java.util.concurrent.CopyOnWriteArrayList r1 = new java.util.concurrent.CopyOnWriteArrayList
            r1.<init>()
            com.huawei.haf.threadpool.ThreadPoolManager r5 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            ote$3 r6 = new ote$3
            r6.<init>()
            r5.execute(r6)
            boolean r5 = b()
            if (r5 != 0) goto L53
            com.huawei.haf.threadpool.ThreadPoolManager r5 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            ote$1 r6 = new ote$1
            r6.<init>()
            r5.execute(r6)
        L53:
            r9 = 0
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.InterruptedException -> L79
            java.lang.String r6 = "doOverallAndMaterialSearch waiting!"
            r5[r9] = r6     // Catch: java.lang.InterruptedException -> L79
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)     // Catch: java.lang.InterruptedException -> L79
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L79
            r6 = 10000(0x2710, double:4.9407E-320)
            boolean r5 = r4.await(r6, r5)     // Catch: java.lang.InterruptedException -> L79
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.InterruptedException -> L7a
            java.lang.String r6 = "doOverallAndMaterialSearch waiting done! latch "
            r2[r9] = r6     // Catch: java.lang.InterruptedException -> L7a
            long r6 = r4.getCount()     // Catch: java.lang.InterruptedException -> L7a
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch: java.lang.InterruptedException -> L7a
            r2[r3] = r4     // Catch: java.lang.InterruptedException -> L7a
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.InterruptedException -> L7a
            goto L83
        L79:
            r5 = r9
        L7a:
            java.lang.String r2 = "doOverallAndMaterialSearch interrupted!"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        L83:
            if (r5 != 0) goto L8e
            java.lang.String r2 = "doOverallAndMaterialSearch time out!"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        L8e:
            boolean r0 = defpackage.koq.c(r1)
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 == 0) goto L9a
            r8.e(r2, r1)
            goto L9d
        L9a:
            r8.c(r2, r9)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ote.d(java.lang.String):void");
    }

    private void e(int i, List<GlobalSearchResult> list) {
        SearchResultFragment searchResultFragment = this.d.get();
        if (searchResultFragment == null) {
            return;
        }
        if (koq.b(list)) {
            searchResultFragment.b(i, false);
            return;
        }
        Iterator<GlobalSearchResult> it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            GlobalSearchResult next = it.next();
            if (next == null) {
                it.remove();
            } else if (!koq.b(next.getContent())) {
                z = false;
            }
        }
        if (z) {
            searchResultFragment.b(i, false);
        } else {
            this.e.put(Integer.valueOf(i), list);
            searchResultFragment.b(i, true);
        }
    }

    public Object e(int i) {
        ConcurrentHashMap<Integer, Object> concurrentHashMap = this.e;
        if (concurrentHashMap == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return null;
        }
        return this.e.get(Integer.valueOf(i));
    }

    public List<GlobalSearchContent> c(int i) {
        ArrayList arrayList = new ArrayList();
        ConcurrentHashMap<Integer, Object> concurrentHashMap = this.e;
        if (concurrentHashMap == null) {
            return arrayList;
        }
        Object obj = concurrentHashMap.get(200);
        if (!koq.e(obj, GlobalSearchResult.class)) {
            LogUtil.a("SearchUtils", "not match GlobalSearchResult");
            return arrayList;
        }
        for (GlobalSearchResult globalSearchResult : (List) obj) {
            Integer num = fbo.c.get(globalSearchResult.getCategoryId());
            if (num != null && num.intValue() == i) {
                List<GlobalSearchContent> content = globalSearchResult.getContent();
                if (!koq.b(content)) {
                    return content;
                }
            }
        }
        return arrayList;
    }

    public void a() {
        ConcurrentHashMap<Integer, Object> concurrentHashMap = this.e;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    private boolean c() {
        WeakReference<SearchResultFragment> weakReference = this.d;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, boolean z) {
        SearchResultFragment searchResultFragment;
        WeakReference<SearchResultFragment> weakReference = this.d;
        if (weakReference == null || (searchResultFragment = weakReference.get()) == null) {
            return;
        }
        searchResultFragment.b(i, z);
    }
}
