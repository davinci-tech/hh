package com.huawei.motiondetection.motionrelay;

import android.content.Context;
import com.huawei.motiondetection.MRLog;

/* loaded from: classes5.dex */
public class RelayManager {
    private Context mContext;
    private IRelay mRelay;
    private RelayListener mRListener = null;
    private RelayListener mRelayListener = new RelayListener() { // from class: com.huawei.motiondetection.motionrelay.RelayManager.1
        @Override // com.huawei.motiondetection.motionrelay.RelayListener
        public void notifyResult(int i, Object obj) {
            RelayManager.this.processRecoResult(i, obj);
        }
    };

    public RelayManager(Context context) {
        this.mContext = null;
        this.mRelay = null;
        this.mContext = context;
        RelayBroadcast relayBroadcast = new RelayBroadcast(context);
        this.mRelay = relayBroadcast;
        relayBroadcast.setRelayListener(this.mRelayListener);
    }

    public void startMotionService() {
        this.mRelay.startMotionService();
    }

    public void stopMotionService() {
        this.mRelay.stopMotionService();
    }

    public void startMotionRecognition(int i) {
        this.mRelay.startMotionReco(i);
    }

    public void stopMotionRecognition(int i) {
        this.mRelay.stopMotionReco(i);
    }

    public void destroy() {
        this.mRelay.destroy();
        this.mRelay = null;
        this.mRelayListener = null;
        this.mRListener = null;
        this.mContext = null;
    }

    public void setRelayListener(RelayListener relayListener) {
        this.mRListener = relayListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRecoResult(int i, Object obj) {
        try {
            RelayListener relayListener = this.mRListener;
            if (relayListener != null) {
                relayListener.notifyResult(i, obj);
            }
        } catch (Exception e) {
            MRLog.w("RelayManager", e.getMessage());
        }
    }
}
