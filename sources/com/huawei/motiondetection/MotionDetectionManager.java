package com.huawei.motiondetection;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.SystemClock;
import com.huawei.motiondetection.motionrelay.RelayListener;
import com.huawei.motiondetection.motionrelay.RelayManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MotionDetectionManager {
    private static final String MOTION_SERVICE_APK_ACTION = "com.huawei.action.MOTION_SETTINGS";
    private static final int SERVICE_JUDGE_DELAY = 100;
    private static final String TAG = "MotionDetectionManager";
    private Context mContext;
    private ArrayList<MotionDetectionListener> mMDListenerList;
    private ArrayList<Integer> mMotionAppsRegList;
    private RelayListener mRelayListener = new RelayListener() { // from class: com.huawei.motiondetection.MotionDetectionManager.1
        @Override // com.huawei.motiondetection.motionrelay.RelayListener
        public void notifyResult(int i, Object obj) {
            MotionDetectionManager.this.processMotionRecoResult(i, obj);
        }
    };
    private RelayManager mRelayManager;

    public static boolean isMotionRecoApkExist(Context context) {
        List<ResolveInfo> queryIntentActivities = context.getApplicationContext().getPackageManager().queryIntentActivities(new Intent(MOTION_SERVICE_APK_ACTION), 0);
        if (queryIntentActivities != null && queryIntentActivities.size() != 0) {
            return true;
        }
        MRLog.e("MotionRecoApkCheck", "Motion service not installed, it can not do motion recognize.");
        return false;
    }

    public MotionDetectionManager(Context context) {
        this.mContext = null;
        this.mRelayManager = null;
        this.mMDListenerList = null;
        this.mMotionAppsRegList = null;
        this.mContext = context;
        RelayManager relayManager = new RelayManager(this.mContext);
        this.mRelayManager = relayManager;
        relayManager.setRelayListener(this.mRelayListener);
        this.mMDListenerList = new ArrayList<>();
        this.mMotionAppsRegList = new ArrayList<>();
    }

    public void startMotionService() {
        if (MRUtils.isServiceRunning(this.mContext, MotionConfig.MOTION_SERVICE_PROCESS)) {
            return;
        }
        this.mRelayManager.startMotionService();
    }

    public void stopMotionService() {
        if (MRUtils.isServiceRunning(this.mContext, MotionConfig.MOTION_SERVICE_PROCESS)) {
            this.mRelayManager.stopMotionService();
        }
    }

    public void startMotionRecoTutorial(int i) {
        MRLog.d(TAG, "startMotionRecoTutorial motionApps: " + i);
        if (this.mMotionAppsRegList.contains(Integer.valueOf(i))) {
            MRLog.d(TAG, "startMotionRecoTutorial repeat motionApps " + i);
            return;
        }
        int motionTypeByMotionApps = MotionTypeApps.getMotionTypeByMotionApps(i);
        if (motionTypeByMotionApps == i) {
            MRLog.e(TAG, "startMotionRecoTutorial motionApps " + i + " is not supported.");
            return;
        }
        this.mRelayManager.startMotionRecognition(motionTypeByMotionApps);
        this.mMotionAppsRegList.add(Integer.valueOf(i));
    }

    public void stopMotionRecoTutorial(int i) {
        MRLog.d(TAG, "stopMotionRecoTutorial motionApps: " + i);
        int motionTypeByMotionApps = MotionTypeApps.getMotionTypeByMotionApps(i);
        if (motionTypeByMotionApps != i && !this.mMotionAppsRegList.contains(Integer.valueOf(i))) {
            MRLog.d(TAG, "stopMotionRecoTutorial not recognition motionApps " + i);
        } else {
            this.mRelayManager.stopMotionRecognition(motionTypeByMotionApps);
            this.mMotionAppsRegList.remove(Integer.valueOf(i));
        }
    }

    public boolean startMotionAppsReco(int i) {
        MRLog.d(TAG, "startMotionAppsReco motionApps: " + i);
        if (this.mMotionAppsRegList.contains(Integer.valueOf(i))) {
            MRLog.w(TAG, "startMotionAppsReco repeat motionApps " + i);
            return false;
        }
        int motionTypeByMotionApps = MotionTypeApps.getMotionTypeByMotionApps(i);
        if (motionTypeByMotionApps == i) {
            MRLog.e(TAG, "startMotionAppsReco motionApps " + i + " is not supported.");
            return false;
        }
        String motionKeyByMotionApps = MotionTypeApps.getMotionKeyByMotionApps(motionTypeByMotionApps);
        if (resetMotionState(MRUtils.getMotionEnableState(this.mContext, motionKeyByMotionApps), motionKeyByMotionApps) == 1) {
            if (MRUtils.getMotionEnableState(this.mContext, MotionTypeApps.getMotionKeyByMotionApps(i)) == 1) {
                MRLog.d(TAG, "startMotionAppsReco motionTypeReco: " + motionTypeByMotionApps);
                if (!MRUtils.isServiceRunning(this.mContext, MotionConfig.MOTION_SERVICE_PROCESS)) {
                    MRLog.d(TAG, "startMotionAppsReco call startMotionService ");
                    startMotionService();
                    SystemClock.sleep(100L);
                }
                this.mRelayManager.startMotionRecognition(motionTypeByMotionApps);
                this.mMotionAppsRegList.add(Integer.valueOf(i));
                return true;
            }
            MRLog.w(TAG, "startMotionAppsReco motionApps: " + i + " disabled");
        } else {
            MRLog.w(TAG, "startMotionAppsReco motionTypeReco: " + motionTypeByMotionApps + " disabled");
        }
        return false;
    }

    public boolean stopMotionAppsReco(int i) {
        MRLog.d(TAG, "stopMotionAppsReco motionApps: " + i);
        int motionTypeByMotionApps = MotionTypeApps.getMotionTypeByMotionApps(i);
        if (motionTypeByMotionApps != i && !this.mMotionAppsRegList.contains(Integer.valueOf(i))) {
            MRLog.d(TAG, "stopMotionAppsReco not recognition motionApps " + i);
            return false;
        }
        if (isMotionStopValid(i, motionTypeByMotionApps)) {
            MRLog.d(TAG, "stopMotionAppsReco motionTypeReco: " + motionTypeByMotionApps);
            this.mRelayManager.stopMotionRecognition(motionTypeByMotionApps);
        } else {
            MRLog.w(TAG, "stopMotionAppsReco can not stop motionReco: " + motionTypeByMotionApps);
        }
        this.mMotionAppsRegList.remove(Integer.valueOf(i));
        return true;
    }

    public void destroy() {
        stopAllMotionReco();
        this.mMotionAppsRegList.clear();
        this.mMotionAppsRegList = null;
        this.mMDListenerList.clear();
        this.mMDListenerList = null;
        this.mRelayManager.destroy();
        this.mRelayManager = null;
        this.mContext = null;
    }

    public void addMotionListener(MotionDetectionListener motionDetectionListener) {
        ArrayList<MotionDetectionListener> arrayList = this.mMDListenerList;
        if (arrayList == null || arrayList.contains(motionDetectionListener)) {
            return;
        }
        this.mMDListenerList.add(motionDetectionListener);
    }

    public void removeMotionListener(MotionDetectionListener motionDetectionListener) {
        ArrayList<MotionDetectionListener> arrayList = this.mMDListenerList;
        if (arrayList == null || !arrayList.contains(motionDetectionListener)) {
            return;
        }
        this.mMDListenerList.remove(motionDetectionListener);
    }

    private boolean isMotionStopValid(int i, int i2) {
        int size = this.mMotionAppsRegList.size();
        for (int i3 = 0; i3 < size; i3++) {
            int intValue = this.mMotionAppsRegList.get(i3).intValue();
            if (MotionTypeApps.getMotionTypeByMotionApps(intValue) == i2 && intValue != i) {
                MRLog.w(TAG, "isMotionStopValid same motionReco running by other motionApps: " + intValue);
                return false;
            }
        }
        return true;
    }

    private int resetMotionState(int i, String str) {
        if (i != -1) {
            return i;
        }
        MRUtils.setMotionEnableState(this.mContext, str, 1);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMotionRecoResult(int i, Object obj) {
        MRLog.d(TAG, "processReceiverMsg ... ");
        if (i == 1) {
            notifyMotionRecoResult((Intent) obj);
        }
    }

    private void stopAllMotionReco() {
        ArrayList<Integer> arrayList = this.mMotionAppsRegList;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        int i = 0;
        while (i < this.mMotionAppsRegList.size()) {
            if (stopMotionAppsReco(this.mMotionAppsRegList.get(i).intValue())) {
                i--;
            }
            i++;
        }
    }

    private void notifyMotionRecoResult(Intent intent) {
        int recoMotionType = getRecoMotionType(intent);
        int recoMotionResult = getRecoMotionResult(intent);
        int recoMotionDirect = getRecoMotionDirect(intent);
        Bundle recoMotionExtras = getRecoMotionExtras(intent);
        try {
            ArrayList<Integer> motionsAppsByMotionReco = getMotionsAppsByMotionReco(recoMotionType);
            MRLog.d(TAG, "notifyMotionRecoResult motionTypeReco: " + recoMotionType + "  recoRes: " + recoMotionResult + " rDirect: " + recoMotionDirect);
            StringBuilder sb = new StringBuilder("notifyMotionRecoResult mMPListenerList size: ");
            sb.append(this.mMDListenerList.size());
            MRLog.d(TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder("notifyMotionRecoResult mMotionAppsRegList size: ");
            sb2.append(this.mMotionAppsRegList.size());
            MRLog.d(TAG, sb2.toString());
            for (int i = 0; i < motionsAppsByMotionReco.size(); i++) {
                for (int i2 = 0; i2 < this.mMDListenerList.size(); i2++) {
                    this.mMDListenerList.get(i2).notifyMotionRecoResult(getRecoResult(motionsAppsByMotionReco.get(i).intValue(), recoMotionResult, recoMotionDirect, recoMotionExtras));
                }
            }
        } catch (Exception e) {
            MRLog.w(TAG, e.getMessage());
        }
    }

    private MotionRecoResult getRecoResult(int i, int i2, int i3, Bundle bundle) {
        return new MotionRecoResult(i, i2, i3, bundle);
    }

    private ArrayList<Integer> getMotionsAppsByMotionReco(int i) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (this.mMotionAppsRegList.size() > 0) {
            for (int i2 = 0; i2 < this.mMotionAppsRegList.size(); i2++) {
                if (MotionTypeApps.getMotionTypeByMotionApps(this.mMotionAppsRegList.get(i2).intValue()) == i) {
                    arrayList.add(this.mMotionAppsRegList.get(i2));
                }
            }
        }
        return arrayList;
    }

    private int getRecoMotionType(Intent intent) {
        return intent.getIntExtra(MotionConfig.MOTION_TYPE_RECOGNITION, 0);
    }

    private int getRecoMotionResult(Intent intent) {
        return intent.getIntExtra(MotionConfig.MOTION_RECOGNITION_RESULT, 0);
    }

    private int getRecoMotionDirect(Intent intent) {
        return intent.getIntExtra(MotionConfig.MOTION_RECOGNITION_DIRECTION, 0);
    }

    private Bundle getRecoMotionExtras(Intent intent) {
        return intent.getBundleExtra(MotionConfig.MOTION_RECOGNITION_EXTRAS);
    }
}
