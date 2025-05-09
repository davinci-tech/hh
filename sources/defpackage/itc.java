package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Process;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes7.dex */
public class itc implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private int f13591a = HiDateUtil.c(System.currentTimeMillis());
    private int b;
    private Context c;
    private int d;
    private isf e;

    public itc(Context context, int i, int i2) {
        this.c = context;
        this.d = i;
        this.b = i2;
    }

    private void b() {
        this.e = isf.a(this.c);
    }

    @Override // java.lang.Runnable
    public void run() {
        b();
        ReleaseLogUtil.e("HiH_HiSyncBackgroud", "insert time ");
        SQLiteDatabase writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        this.f13591a = HiDateUtil.c(System.currentTimeMillis());
        if (writableDatabase == null) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "error: db is not open!");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!BaseApplication.isRunningForeground()) {
            ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
        }
        b(1, 10001, writableDatabase, currentTimeMillis);
        boolean z = -2 != b(1, 1, writableDatabase, currentTimeMillis);
        iuz.b(this.c, this.d, this.f13591a);
        if (z) {
            iuz.e(this.c, this.d, 0);
        } else {
            iuz.e(this.c, this.d, 1);
            iuz.d(this.c, this.d, currentTimeMillis);
        }
        ism.b(false);
        try {
            writableDatabase.execSQL(iil.a(1));
            writableDatabase.execSQL(iil.c(1));
        } catch (SQLiteException e) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "ClearAllInfo SQLiteException!", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "ClearAllInfo Exception!", LogAnonymous.b((Throwable) e2));
        }
        ReleaseLogUtil.e("HiH_HiSyncBackgroud", "insert over !Background task is down! totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private int b(int i, int i2, SQLiteDatabase sQLiteDatabase, long j) {
        boolean z;
        d(i, sQLiteDatabase);
        try {
            Cursor bBo_ = ijx.e().bBo_(this.d, i2);
            try {
                if (bBo_ == null) {
                    LogUtil.h("Debug_HiSyncBackgroud", "query = null");
                    if (bBo_ == null) {
                        return -1;
                    }
                    bBo_.close();
                    return -1;
                }
                int i3 = 4;
                char c = 3;
                LogUtil.c("Debug_HiSyncBackgroud", "type=", Integer.valueOf(i2), " ,query count ", Integer.valueOf(bBo_.getCount()));
                List<HiHealthData> arrayList = new ArrayList<>(10);
                int i4 = 0;
                while (true) {
                    if (!bBo_.moveToNext()) {
                        z = true;
                        break;
                    }
                    if (a(i2, j)) {
                        Object[] objArr = new Object[i3];
                        objArr[0] = "backgroud insert large ";
                        objArr[1] = Integer.valueOf((this.b / 1000) / 60);
                        objArr[2] = "data size=";
                        objArr[c] = Integer.valueOf(i4);
                        LogUtil.a("HiH_HiSyncBackgroud", objArr);
                        z = false;
                        break;
                    }
                    String string = bBo_.getString(bBo_.getColumnIndex("data"));
                    if (string == null) {
                        LogUtil.b("Debug_HiSyncBackgroud", "data  = nul");
                    } else {
                        List list = (List) HiJsonUtil.b(string, new TypeToken<List<HiHealthData>>() { // from class: itc.4
                        }.getType());
                        if (!HiCommonUtil.d(list)) {
                            arrayList.addAll(list);
                            i4 = e(i2, arrayList, i4);
                            bCd_(i, sQLiteDatabase, bBo_);
                        }
                    }
                    i3 = 4;
                    c = 3;
                }
                if (arrayList.size() > 0) {
                    c(arrayList, i2);
                    i4 += arrayList.size();
                }
                if (!z) {
                    if (bBo_ != null) {
                        bBo_.close();
                    }
                    return -2;
                }
                LogUtil.a("HiH_HiSyncBackgroud", "insert ", Integer.valueOf(i2), "over datasize=", Integer.valueOf(i4), ", total time=", Long.valueOf(System.currentTimeMillis() - j));
                if (bBo_ != null) {
                    bBo_.close();
                }
                return 0;
            } finally {
            }
        } catch (SQLiteException e) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "ClearAllInfo SQLiteException!", LogAnonymous.b((Throwable) e));
            return -2;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "backgroudInsertHealthData Exception", LogAnonymous.b((Throwable) e2));
            return -2;
        }
    }

    private int e(int i, List<HiHealthData> list, int i2) {
        if (list.size() <= 200) {
            return i2;
        }
        c(list, i);
        int size = i2 + list.size();
        list.clear();
        ism.d();
        return size;
    }

    private void bCd_(int i, SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        sQLiteDatabase.execSQL(iil.e(i, cursor.getInt(cursor.getColumnIndex("_id"))));
    }

    private void d(int i, SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(iil.a(i));
            sQLiteDatabase.execSQL(iil.c(i));
        } catch (SQLiteException e) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "ClearAllInfo SQLiteException!", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiSyncBackgroud", "ClearAllInfo Exception!", LogAnonymous.b((Throwable) e2));
        }
    }

    private boolean a(int i, long j) {
        return 1 == i && ((long) this.b) < System.currentTimeMillis() - j;
    }

    private void c(List<HiHealthData> list, int i) {
        int c = HiDateUtil.c(list.get(0).getStartTime());
        LogUtil.c("Debug_HiSyncBackgroud", "insert size=", Integer.valueOf(list.size()), ", Record day=", Integer.valueOf(c), " ,errorCode=", Integer.valueOf(this.e.c(list, this.d)));
        if (1 == i && this.f13591a > c) {
            this.f13591a = c;
        }
        this.e.prepareRealTimeHealthDataStat(list, false);
        this.e.doRealTimeHealthDataStat(false);
    }

    public static void d(Context context, List<HiHealthData> list, int i, int i2) {
        ijx e = ijx.e();
        int i3 = 0;
        long startTime = list.get(0).getStartTime();
        int size = list.size();
        LogUtil.c("Debug_HiSyncBackgroud", "hiHealthDatas size is ", Integer.valueOf(size));
        if (size > 1) {
            Collections.sort(list, new a());
            LogUtil.c("Debug_HiSyncBackgroud", "Collections.sort finish");
        }
        if (size <= 1000) {
            if (e.d(i2, i, HiJsonUtil.e(list), startTime)) {
                return;
            }
            LogUtil.h("Debug_HiSyncBackgroud", "performDownloadByVersion saveVersionToDB failed ");
            return;
        }
        int i4 = size / 1000;
        if (size % 1000 != 0) {
            i4++;
        }
        int i5 = i4;
        while (i3 < i5) {
            LogUtil.c("Debug_HiSyncBackgroud", "subhiHealthDatas begin page = ", Integer.valueOf(i3));
            int i6 = i3 + 1;
            int i7 = i6 * 1000;
            List<HiHealthData> subList = list.subList(i3 * 1000, i7 > size ? size : i7 - 1);
            if (subList == null || subList.isEmpty()) {
                LogUtil.h("Debug_HiSyncBackgroud", "subhiHealthDatas is null");
            } else {
                if (!e.d(i2, i, HiJsonUtil.e(subList), startTime)) {
                    LogUtil.h("Debug_HiSyncBackgroud", "performDownloadByVersion saveVersionToDB failed ");
                }
                LogUtil.c("Debug_HiSyncBackgroud", "subhiHealthDatas over");
            }
            i3 = i6;
        }
    }

    static class a implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = 4146406961938280295L;

        private a() {
        }

        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return Double.compare(hiHealthData2.getStartTime(), hiHealthData.getStartTime());
        }
    }
}
