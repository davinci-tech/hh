package com.huawei.health.ecologydevice.fitness;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.AbstractCommand;
import com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeExtendedSingleData;
import com.huawei.health.ecologydevice.fitness.factory.RopeDataFactory;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bkz;
import defpackage.cyf;
import defpackage.cyu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes3.dex */
public class RopeDataQueues {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2283a;
    private boolean b;
    private BaseRopeData d;
    private long e;
    private long g;
    private long h;
    private ExtendHandler i;
    private RopeDataListener j;
    private Queue<BaseRopeData> f = new ConcurrentLinkedQueue();
    private Queue<AbstractCommand> c = new ConcurrentLinkedQueue();

    public interface RopeDataListener {
        void onResult(byte[] bArr, boolean z);
    }

    public RopeDataQueues(long j, long j2) {
        this.g = j == 0 ? 1000L : j;
        this.e = j2 == 0 ? 500L : j2;
        this.i = HandlerCenter.yt_(new b(), "BleMessageQueues");
    }

    public void a(int[] iArr, String str) {
        if (iArr == null) {
            return;
        }
        if (this.d == null) {
            RopeDataFactory d = cyu.d(str);
            if (d == null) {
                LogUtil.a("BleMessageQueues", "ropeDataFactory is null");
                return;
            }
            this.d = d.createRopeData(5, 1, false);
        }
        BaseRopeData baseRopeData = this.d;
        if (baseRopeData == null) {
            LogUtil.a("BleMessageQueues", "baseRopeData is null");
            return;
        }
        baseRopeData.setPara(iArr);
        LogUtil.a("BleMessageQueues", "sendRealTimeData paras = ", Arrays.toString(iArr));
        LogUtil.a("BleMessageQueues", "isBlockRealTimeData ", Boolean.valueOf(this.f2283a), " queue size ", Integer.valueOf(this.f.size()));
        if (this.f2283a || this.f.size() >= 1 || this.b) {
            return;
        }
        this.f2283a = true;
        this.h = System.currentTimeMillis();
        this.i.sendEmptyMessage(1, this.g);
    }

    public void b(int i, int i2, int[] iArr, String str) {
        if (c(i, i2, iArr)) {
            boolean z = Math.abs(System.currentTimeMillis() - this.h) >= 200;
            if (i == 5 && i2 == 0 && this.f2283a && z) {
                LogUtil.a("BleMessageQueues", "abandoned heart rate message");
                return;
            }
            RopeDataFactory d = cyu.d(str);
            if (d == null) {
                LogUtil.a("BleMessageQueues", "ropeDataFactory is null");
                return;
            }
            BaseRopeData createRopeData = d.createRopeData(i, i2, true);
            if (createRopeData == null) {
                LogUtil.a("BleMessageQueues", "baseRopeData is null");
                return;
            }
            createRopeData.setPara(iArr);
            this.f.add(createRopeData);
            LogUtil.a("BleMessageQueues", "sendDelayMessage command ", Integer.valueOf(i), " code ", Integer.valueOf(i2), " paras ", Arrays.toString(iArr), " QUEUE length ", Integer.valueOf(this.f.size()), "isSubPackageMessageSending = ", Boolean.valueOf(this.b));
            if (this.f.size() != 1 || this.b) {
                return;
            }
            this.i.sendEmptyMessage(2, this.e);
        }
    }

    public void e(RopeDataListener ropeDataListener) {
        this.j = ropeDataListener;
    }

    public void b() {
        this.d = null;
        this.f.clear();
        this.c.clear();
        ExtendHandler extendHandler = this.i;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.i.quit(false);
        }
    }

    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                RopeDataQueues.this.d();
                return true;
            }
            if (i == 2) {
                RopeDataQueues.this.a();
                return true;
            }
            if (i != 3) {
                return false;
            }
            RopeDataQueues.this.RH_(message);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f.isEmpty()) {
            LogUtil.a("queue is empty", new Object[0]);
            return;
        }
        BaseRopeData poll = this.f.poll();
        if (poll == null) {
            LogUtil.a("ropeData is null", new Object[0]);
        } else {
            c(poll);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RH_(Message message) {
        this.i.removeMessages(1);
        this.i.removeMessages(2);
        Bundle data = message.getData();
        boolean z = data != null ? data.getBoolean("commandType") : false;
        AbstractCommand poll = this.c.poll();
        RopeDataListener ropeDataListener = this.j;
        if (ropeDataListener != null && poll != null) {
            ropeDataListener.onResult(poll.toByteArray(), z);
        }
        if (this.c.size() <= 0) {
            this.f2283a = false;
            this.h = 0L;
            this.b = false;
            e();
            return;
        }
        this.i.sendMessage(RI_(z), this.e);
    }

    private void c(BaseRopeData baseRopeData) {
        if (baseRopeData == null) {
            LogUtil.a("BleMessageQueues", "ropeData is null");
            return;
        }
        LogUtil.a("BleMessageQueues", "handleBleMessage opCommand: " + baseRopeData.getCommand());
        if (baseRopeData instanceof RopeExtendedSingleData) {
            byte[] heartRateByteArray = ((RopeExtendedSingleData) baseRopeData).getHeartRateByteArray();
            RopeDataListener ropeDataListener = this.j;
            if (ropeDataListener != null) {
                ropeDataListener.onResult(heartRateByteArray, baseRopeData.isRopeConfigCommand());
            }
            e();
            return;
        }
        List<AbstractCommand> b2 = b(baseRopeData);
        if (!bkz.e(b2) && b2.size() == 1) {
            byte[] byteArray = b2.get(0).toByteArray();
            RopeDataListener ropeDataListener2 = this.j;
            if (ropeDataListener2 != null) {
                ropeDataListener2.onResult(byteArray, baseRopeData.isRopeConfigCommand());
            }
            e();
            return;
        }
        this.c.addAll(b2);
        if (this.c.size() > 1) {
            this.b = true;
            this.i.sendMessage(RI_(baseRopeData.isRopeConfigCommand()));
        }
    }

    private void e() {
        LogUtil.a("BleMessageQueues", "queue size is ", Integer.valueOf(this.f.size()));
        if (this.f.size() > 0) {
            this.i.sendEmptyMessage(2, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        BaseRopeData baseRopeData = this.d;
        if (baseRopeData == null) {
            this.f2283a = false;
            this.h = 0L;
            return;
        }
        c(baseRopeData);
        if (this.b) {
            return;
        }
        this.f2283a = false;
        this.h = 0L;
    }

    private Message RI_(boolean z) {
        Message message = new Message();
        message.what = 3;
        Bundle bundle = new Bundle();
        bundle.putBoolean("commandType", z);
        message.setData(bundle);
        return message;
    }

    private List<AbstractCommand> b(BaseRopeData baseRopeData) {
        ArrayList arrayList = new ArrayList();
        cyf transmitCommand = baseRopeData.getTransmitCommand();
        if (transmitCommand == null) {
            LogUtil.a("BleMessageQueues", "getRopeConfigCommands ropeConfigCommand is null");
            return arrayList;
        }
        return transmitCommand.b();
    }

    private boolean c(int i, int i2, int[] iArr) {
        LogUtil.a("BleMessageQueues", "verifyingData opCode " + i2);
        if (i > 255 || i < 0) {
            LogUtil.a("BleMessageQueues", "verifyingData, invalid opCommand!");
            return false;
        }
        if (i2 > 255 || i2 < 0) {
            LogUtil.a("BleMessageQueues", "verifyingData, invalid opcode!");
            return false;
        }
        if (iArr != null) {
            return true;
        }
        LogUtil.a("BleMessageQueues", "verifyingData, invalid para!");
        return false;
    }
}
