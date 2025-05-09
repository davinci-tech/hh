package com.huawei.ui.main.stories.utils;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.fcj;
import defpackage.gsd;
import defpackage.qpk;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes7.dex */
public class LastTimeHealthDataReader<T extends BaseActivity> {
    private final b<T> c;
    private long d;
    private final Context e = BaseApplication.getContext().getApplicationContext();

    public enum CardData {
        SLEEP,
        BLOOD_OXYGEN,
        PRESSURE,
        WEIGHT,
        HEALTH_RATE,
        BLOOD_SUGAR,
        TEMPERATURE
    }

    public LastTimeHealthDataReader(T t, IBaseResponseCallback iBaseResponseCallback) {
        this.c = new b<>(Looper.getMainLooper(), t, iBaseResponseCallback);
    }

    public void b(CardData cardData) {
        HiHealthManager.d(this.e).readHiHealthData(e(cardData), new e(this.d, this.c, cardData, this));
    }

    public static final class e implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<LastTimeHealthDataReader> f10536a;
        private long c;
        private CardData d;
        private WeakReference<b> e;

        public e(long j, b bVar, CardData cardData, LastTimeHealthDataReader lastTimeHealthDataReader) {
            this.c = j;
            this.e = new WeakReference<>(bVar);
            this.d = cardData;
            this.f10536a = new WeakReference<>(lastTimeHealthDataReader);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LastTimeHealthDataReader lastTimeHealthDataReader = this.f10536a.get();
            if (lastTimeHealthDataReader == null) {
                LogUtil.b("LastTimeHealthDataReader", "onResult handle abort, cause reader is null!");
                return;
            }
            LogUtil.a("LastTimeHealthDataReader", "readCardData ", " end, Time: ", Long.valueOf(System.currentTimeMillis() - this.c), " ms");
            b bVar = this.e.get();
            if (bVar == null) {
                LogUtil.b("LastTimeHealthDataReader", "onResult handle abort, cause updateUiHandler is null!");
                return;
            }
            Message obtainMessage = bVar.obtainMessage();
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("LastTimeHealthDataReader", this.d.name(), "data is null! errorCode=", Integer.valueOf(i));
                obtainMessage.what = -1;
                obtainMessage.arg1 = i;
                bVar.sendMessage(obtainMessage);
                return;
            }
            SparseArray<List<HiHealthData>> sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                HiDataReadOption e = lastTimeHealthDataReader.e(this.d);
                HiHealthData dWc_ = dWc_(sparseArray, e.getType());
                if (dWc_ == null) {
                    LogUtil.h("LastTimeHealthDataReader", "read ", this.d.name(), "lastData == null ,errorCode=", Integer.valueOf(i));
                    obtainMessage.what = -1;
                    obtainMessage.arg1 = i;
                    bVar.sendMessage(obtainMessage);
                    return;
                }
                dWb_(sparseArray, dWc_, this.d, e.getType());
                LogUtil.a("LastTimeHealthDataReader", "read ", this.d.name(), "last data time=", Long.valueOf(dWc_.getStartTime()));
                obtainMessage.what = 0;
                obtainMessage.obj = dWc_;
                bVar.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("LastTimeHealthDataReader", this.d.name(), "map.size() <= 0,errorCode=", Integer.valueOf(i));
            obtainMessage.what = -1;
            obtainMessage.arg1 = i;
            bVar.sendMessage(obtainMessage);
        }

        private void dWb_(SparseArray<List<HiHealthData>> sparseArray, HiHealthData hiHealthData, CardData cardData, int[] iArr) {
            if (cardData != CardData.SLEEP) {
                return;
            }
            long startTime = hiHealthData.getStartTime();
            boolean z = false;
            boolean z2 = false;
            int i = 0;
            for (int i2 : iArr) {
                List<HiHealthData> list = sparseArray.get(i2);
                if (list instanceof List) {
                    List<HiHealthData> list2 = list;
                    if (list2.get(0) != null) {
                        HiHealthData hiHealthData2 = list2.get(0);
                        LogUtil.a("LastTimeHealthDataReader", "hihealthdata ", hiHealthData2.toString(), " dayTime ", Long.valueOf(startTime));
                        if (hiHealthData2.getStartTime() == startTime) {
                            if (hiHealthData2.getType() == 44110) {
                                z2 = fcj.i(hiHealthData2.getIntValue());
                            } else if (hiHealthData2.getType() == 44217) {
                                i = hiHealthData2.getIntValue();
                            }
                        }
                    }
                }
            }
            if (z2 && i > 0) {
                z = true;
            }
            hiHealthData.putBoolean("HAS_BED_SCIENCE", z);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.c("LastTimeHealthDataReader", "onResultIntent");
        }

        private HiHealthData dWc_(SparseArray<List<HiHealthData>> sparseArray, int[] iArr) {
            ArrayList arrayList = new ArrayList(sparseArray.size());
            for (int i : iArr) {
                List<HiHealthData> list = sparseArray.get(i);
                if (list instanceof List) {
                    arrayList.addAll(list);
                }
            }
            if (arrayList.size() == 0) {
                return null;
            }
            Collections.sort(arrayList, new Comparator() { // from class: sda
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(((HiHealthData) obj2).getEndTime(), ((HiHealthData) obj).getEndTime());
                    return compare;
                }
            });
            return (HiHealthData) arrayList.get(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiDataReadOption e(CardData cardData) {
        int[] iArr;
        long currentTimeMillis = System.currentTimeMillis();
        this.d = currentTimeMillis;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        switch (AnonymousClass4.b[cardData.ordinal()]) {
            case 1:
                hiDataReadOption.setType(new int[]{44105, 44004, 44108, 44109, 44217, 44110});
                hiDataReadOption.setConstantsKey(new String[]{"core_sleep_total_sleep_time_key", "common_sleep_duration_sleep_sum_key", "core_sleep_day_sleep_time_key", "data_session_manual_sleep_bed_time_key", "core_sleep_bed_time_key", "sleep_device_category_key"});
                return hiDataReadOption;
            case 2:
                hiDataReadOption.setType(new int[]{44307});
                return hiDataReadOption;
            case 3:
                hiDataReadOption.setType(new int[]{46019});
                return hiDataReadOption;
            case 4:
                hiDataReadOption.setType(new int[]{10006});
                hiDataReadOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
                long h = gsd.h();
                if (h < currentTimeMillis) {
                    hiDataReadOption.setStartTime(h);
                }
                return hiDataReadOption;
            case 5:
                hiDataReadOption.setType(new int[]{2103, 2107});
                return hiDataReadOption;
            case 6:
                hiDataReadOption.setType(new int[]{10001, 2108});
                return hiDataReadOption;
            case 7:
                qpk d = qpk.d();
                if (Utils.o()) {
                    iArr = new int[]{d.o()};
                } else {
                    iArr = new int[]{d.b(), 2104, d.h(), d.j(), d.s(), d.k()};
                }
                hiDataReadOption.setType(iArr);
                return hiDataReadOption;
            default:
                LogUtil.b("LastTimeHealthDataReader", "Unknown card data type");
                return hiDataReadOption;
        }
    }

    /* renamed from: com.huawei.ui.main.stories.utils.LastTimeHealthDataReader$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CardData.values().length];
            b = iArr;
            try {
                iArr[CardData.SLEEP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[CardData.PRESSURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[CardData.HEALTH_RATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[CardData.WEIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[CardData.BLOOD_OXYGEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[CardData.BLOOD_SUGAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[CardData.TEMPERATURE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static class b<T> extends BaseHandler<T> {
        private final IBaseResponseCallback e;

        b(Looper looper, T t, IBaseResponseCallback iBaseResponseCallback) {
            super(looper, t);
            this.e = iBaseResponseCallback;
        }

        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(T t, Message message) {
            if (t == null) {
                LogUtil.b("LastTimeHealthDataReader", "handleMessageWhenReferenceNotNull obj == null");
                return;
            }
            if (this.e == null) {
                LogUtil.b("LastTimeHealthDataReader", "handleMessageWhenReferenceNotNull callback == null");
                return;
            }
            if (message == null) {
                LogUtil.b("LastTimeHealthDataReader", "handleMessageWhenReferenceNotNull msg == null");
                return;
            }
            if (message.what == 0 && (message.obj instanceof HiHealthData)) {
                LogUtil.a("LastTimeHealthDataReader", "handleMessage()MSG_READ_LAST_DATA_TIME_SUCCESS");
                this.e.d(message.arg1, (HiHealthData) message.obj);
                return;
            }
            this.e.d(message.arg1, null);
        }
    }
}
