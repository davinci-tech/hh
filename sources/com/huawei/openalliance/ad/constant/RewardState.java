package com.huawei.openalliance.ad.constant;

import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class RewardState {
    private static final String TAG = "RewardState";
    private final byte[] lock = new byte[0];
    private State currentState = State.IDLE;

    public enum State {
        IDLE(0),
        VIDEO_DISPLAYING(1),
        DIALOG_DISPLAYING(2),
        SWIPE_DISPLAYING(3),
        CLICK_WEBVIEW(4),
        END_WEBVIEW(5),
        END_MASKING(6),
        ENDCARD(7),
        HIDE(8);

        int code;

        @Override // java.lang.Enum
        public String toString() {
            return name() + com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY + this.code + com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY;
        }

        public int getCode() {
            return this.code;
        }

        State(int i) {
            this.code = i;
        }
    }

    public void switchToState(State state) {
        if (state == null) {
            return;
        }
        synchronized (this.lock) {
            ho.b(TAG, "switch to state: %s, preState: %s", state, this.currentState);
            this.currentState = state;
        }
    }

    public boolean isState(State state) {
        boolean z;
        synchronized (this.lock) {
            z = this.currentState == state;
        }
        return z;
    }
}
