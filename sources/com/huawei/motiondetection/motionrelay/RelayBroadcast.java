package com.huawei.motiondetection.motionrelay;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.huawei.motiondetection.MRLog;
import com.huawei.motiondetection.MotionConfig;

/* loaded from: classes5.dex */
public class RelayBroadcast implements IRelay {
    private static final String TAG = "RelayBroadcast";
    private Context mContext;
    private RelayHandler mRHandler;
    private RelayListener mRelayListener = null;
    private RelayReceiver mRelayReceiver;

    public RelayBroadcast(Context context) {
        this.mRHandler = null;
        this.mRelayReceiver = null;
        this.mContext = context;
        this.mRHandler = new RelayHandler();
        this.mRelayReceiver = new RelayReceiver(this.mRHandler);
        initReceiver();
    }

    @Override // com.huawei.motiondetection.motionrelay.IRelay
    public void destroy() {
        this.mContext.unregisterReceiver(this.mRelayReceiver);
        this.mRelayReceiver.destroy();
        this.mRelayReceiver = null;
        this.mRelayListener = null;
        this.mRHandler = null;
        this.mContext = null;
    }

    @Override // com.huawei.motiondetection.motionrelay.IRelay
    public void startMotionService() {
        Intent intent = new Intent(MotionConfig.MOTION_ACTION_SERVICE);
        intent.setPackage(MotionConfig.MOTION_SERVICE_PROCESS);
        intent.setFlags(32);
        intent.putExtra(MotionConfig.MOTION_SERVICE_START_TYPE, 1);
        this.mContext.startService(intent);
    }

    @Override // com.huawei.motiondetection.motionrelay.IRelay
    public void stopMotionService() {
        Intent intent = new Intent(MotionConfig.MOTION_ACTION_SERVICE);
        intent.setPackage(MotionConfig.MOTION_SERVICE_PROCESS);
        this.mContext.stopService(intent);
    }

    @Override // com.huawei.motiondetection.motionrelay.IRelay
    public void startMotionReco(int i) {
        doModtionReco(i, 1);
    }

    @Override // com.huawei.motiondetection.motionrelay.IRelay
    public void stopMotionReco(int i) {
        doModtionReco(i, 0);
    }

    @Override // com.huawei.motiondetection.motionrelay.IRelay
    public void setRelayListener(RelayListener relayListener) {
        this.mRelayListener = relayListener;
    }

    private void initReceiver() {
        try {
            this.mContext.registerReceiver(this.mRelayReceiver, new IntentFilter(MotionConfig.MOTION_ACTION_RECOGNITION), "com.huawei.motion.permission.MOTION_ACTION_RECOGNITION", null);
        } catch (Exception e) {
            MRLog.w(TAG, e.getMessage());
        }
    }

    private void doModtionReco(int i, int i2) {
        Intent intent = new Intent(MotionConfig.MOTION_ACTION_OPERATE);
        intent.putExtra(MotionConfig.APP_MOTION_TYPE, i);
        intent.putExtra(MotionConfig.APP_OPERATION_TYPE, i2);
        intent.putExtra(MotionConfig.APP_PROCESS_NAME, getCurProcessName(this.mContext));
        intent.putExtra(MotionConfig.APP_PKG_NAME, this.mContext.getPackageName());
        intent.putExtra(MotionConfig.APP_CLASS_NAME, getClass().getName());
        MRLog.d(TAG, "doModtionReco sendBroadcast motionType: " + i + "  operateType: " + i2);
        this.mContext.sendBroadcast(intent, "com.huawei.motion.permission.MOTION_ACTION_OPERATE");
    }

    private String getCurProcessName(Context context) {
        int myPid = Process.myPid();
        MRLog.d(TAG, "getCurProcessName pid: " + myPid);
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMotionRecoResult(Intent intent) {
        try {
            RelayListener relayListener = this.mRelayListener;
            if (relayListener != null) {
                relayListener.notifyResult(1, intent);
            }
        } catch (Exception e) {
            MRLog.w(TAG, e.getMessage());
        }
    }

    class RelayHandler extends Handler {
        RelayHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            RelayBroadcast.this.processMotionRecoResult((Intent) message.obj);
        }
    }
}
