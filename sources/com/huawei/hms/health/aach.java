package com.huawei.hms.health;

import android.os.RemoteException;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.Record;
import com.huawei.hms.hihealth.data.SamplePoint;
import com.huawei.hms.hihealth.options.OnSamplePointListener;
import com.huawei.hms.hihealth.options.aabb;
import com.huawei.hms.hihealth.options.aabc;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class aach implements com.huawei.hms.hihealth.aabf {
    private static volatile aach aab;

    public Task<Void> aabb(DataType dataType) {
        aabz.aabb("AutoRecorderImpl", "stopRecord by dataType.");
        Preconditions.checkArgument(dataType != null, "dataType or dataCollector must be set");
        return aacq.aab(4, new aabk(dataType));
    }

    public Task<Void> aaba(DataType dataType, OnSamplePointListener onSamplePointListener) {
        if (dataType != null) {
            aabz.aabb("AutoRecorderImpl", "enter stop record realTime data by dataType.");
            if (onSamplePointListener != null) {
                return aacq.aab(4, new aabl(dataType, new aabb(this), new aabc(this, onSamplePointListener)));
            }
            aabz.aabc("AutoRecorderImpl", "healthKitRealTimeListener is null.");
            throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
        }
        aabz.aab("AutoRecorderImpl", "dataType or healthKitRealTimeListener is null.");
        throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
    }

    public Task<Void> aaba(DataType dataType) {
        aabz.aabb("AutoRecorderImpl", "startRecord by by dataType");
        Preconditions.checkArgument(dataType != null, "dataType or dataCollector must be set");
        return aacq.aab(4, new aabg(dataType));
    }

    public Task<Void> aaba(DataCollector dataCollector) {
        aabz.aabb("AutoRecorderImpl", "stopRecord by dataCollector.");
        Preconditions.checkArgument(dataCollector != null, "dataType or dataCollector must be set");
        return aacq.aab(4, new aabi(dataCollector));
    }

    public Task<Void> aab(Record record) {
        aabz.aabb("AutoRecorderImpl", "stopRecord.");
        Preconditions.checkArgument(record != null, "dataType or dataCollector must be set");
        return aacq.aab(4, new aabj(record));
    }

    public Task<Void> aab(DataType dataType, OnSamplePointListener onSamplePointListener) {
        if (dataType != null) {
            aabz.aabb("AutoRecorderImpl", "start record realTime data by dataType.");
            if (onSamplePointListener != null) {
                return aacq.aabb(4, new aabh(dataType, new aab(this, onSamplePointListener), new aaba(this, onSamplePointListener)));
            }
            aabz.aab("AutoRecorderImpl", "dataType or healthKit RealTime Listener is null.");
            throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
        }
        aabz.aab("AutoRecorderImpl", "dataType or healthKitRealTimeListener is null.");
        throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
    }

    public Task<List<Record>> aab(DataType dataType) {
        aabz.aabb("AutoRecorderImpl", "getRecords by dataType.");
        Preconditions.checkArgument(dataType != null, "dataType or dataCollector must be set");
        return aacq.aab(4, new aabd(dataType));
    }

    public Task<Void> aab(DataCollector dataCollector) {
        aabz.aabb("AutoRecorderImpl", "startRecord by dataCollector.");
        Preconditions.checkArgument(dataCollector != null, "dataType or dataCollector must be set");
        return aacq.aab(4, new aabf(dataCollector));
    }

    class aab extends aabc.aab {
        final /* synthetic */ OnSamplePointListener aab;

        @Override // com.huawei.hms.hihealth.options.aabc
        public void onSamplePoint(SamplePoint samplePoint) throws RemoteException {
            this.aab.onSamplePoint(samplePoint);
        }

        aab(aach aachVar, OnSamplePointListener onSamplePointListener) {
            this.aab = onSamplePointListener;
        }
    }

    class aaba extends aabb.aab {
        final /* synthetic */ OnSamplePointListener aab;

        @Override // com.huawei.hms.hihealth.options.aabb
        public void onException(int i, String str) throws RemoteException {
            this.aab.onException(i, str);
        }

        aaba(aach aachVar, OnSamplePointListener onSamplePointListener) {
            this.aab = onSamplePointListener;
        }
    }

    class aabc extends aabb.aab {
        final /* synthetic */ OnSamplePointListener aab;

        @Override // com.huawei.hms.hihealth.options.aabb
        public void onException(int i, String str) throws RemoteException {
            this.aab.onException(i, str);
        }

        aabc(aach aachVar, OnSamplePointListener onSamplePointListener) {
            this.aab = onSamplePointListener;
        }
    }

    public Task<List<Record>> aab() {
        aabz.aabb("AutoRecorderImpl", "getRecords.");
        return aacq.aab(4, new aabe());
    }

    class aabb extends aabc.aab {
        @Override // com.huawei.hms.hihealth.options.aabc
        public void onSamplePoint(SamplePoint samplePoint) throws RemoteException {
        }

        aabb(aach aachVar) {
        }
    }

    static class aabd implements Callable<List<Record>> {
        private final DataType aab;

        @Override // java.util.concurrent.Callable
        public List<Record> call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "getRecords by dataType doing.");
            try {
                return aack.aabe().aaba().aab(this.aab);
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "getRecords remote exception.");
                return new ArrayList();
            }
        }

        aabd(DataType dataType) {
            this.aab = dataType;
        }
    }

    static class aabe implements Callable<List<Record>> {
        @Override // java.util.concurrent.Callable
        public List<Record> call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "getRecords doing.");
            try {
                return aack.aabe().aaba().aabb();
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "getRecords remote exception.");
                return new ArrayList();
            }
        }

        aabe() {
        }
    }

    static class aabf implements Callable<Void> {
        private final DataCollector aab;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "startRecord by dataCollector doing.");
            try {
                aack.aabe().aaba().aab(this.aab);
                return null;
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "startRecord remote exception.");
                return null;
            }
        }

        aabf(DataCollector dataCollector) {
            this.aab = dataCollector;
        }
    }

    static class aabg implements Callable<Void> {
        private final DataType aab;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "startRecord by dataType doing.");
            aack.aabe().aaba().aaba(this.aab);
            return null;
        }

        aabg(DataType dataType) {
            this.aab = dataType;
        }
    }

    static class aabh implements Callable<Void> {
        private final DataType aab;
        private final aabc.aab aaba;
        private final aabb.aab aabb;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "startRecord realTime data doing.");
            try {
                aack.aabe().aaba().aab(this.aab, this.aaba, this.aabb);
                return null;
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "startRecord realTime data remote exception.");
                throw new SecurityException(String.valueOf(HiHealthStatusCodes.API_EXCEPTION_ERROR));
            }
        }

        aabh(DataType dataType, aabc.aab aabVar, aabb.aab aabVar2) {
            this.aab = dataType;
            this.aaba = aabVar;
            this.aabb = aabVar2;
        }
    }

    static class aabi implements Callable<Void> {
        private final DataCollector aab;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "stopRecord by dataCollector doing.");
            try {
                aack.aabe().aaba().aaba(this.aab);
                return null;
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "stopRecord by dataCollector remote exception.");
                return null;
            }
        }

        aabi(DataCollector dataCollector) {
            this.aab = dataCollector;
        }
    }

    static class aabj implements Callable<Void> {
        private final Record aab;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "stopRecord doing.");
            try {
                aack.aabe().aaba().aab(this.aab);
                return null;
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "stopRecord remote exception.");
                return null;
            }
        }

        aabj(Record record) {
            this.aab = record;
        }
    }

    static class aabk implements Callable<Void> {
        private final DataType aab;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "stopRecord by dataType doing.");
            try {
                aack.aabe().aaba().aabb(this.aab);
                return null;
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "stopRecord by dataType remote exception.");
                return null;
            }
        }

        aabk(DataType dataType) {
            this.aab = dataType;
        }
    }

    static class aabl implements Callable<Void> {
        private final DataType aab;
        private final aabc.aab aaba;
        private final aabb.aab aabb;

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            aabz.aabb("AutoRecorderImpl", "stopRecord realTime data doing.");
            try {
                aack.aabe().aaba().aaba(this.aab, this.aaba, this.aabb);
                return null;
            } catch (RemoteException unused) {
                aabz.aabc("AutoRecorderImpl", "stopRecord realTime data remote exception.");
                throw new SecurityException(String.valueOf(HiHealthStatusCodes.API_EXCEPTION_ERROR));
            }
        }

        aabl(DataType dataType, aabc.aab aabVar, aabb.aab aabVar2) {
            this.aab = dataType;
            this.aaba = aabVar;
            this.aabb = aabVar2;
        }
    }

    public static aach aaba() {
        if (aab == null) {
            synchronized (aach.class) {
                if (aab == null) {
                    aab = new aach();
                }
            }
        }
        return aab;
    }
}
