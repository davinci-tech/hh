package com.huawei.watchface.videoedit.dfx;

/* loaded from: classes9.dex */
public class DfxGuard {
    public static final Policy LAX_POLICY = new Policy() { // from class: com.huawei.watchface.videoedit.dfx.DfxGuard.1
        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onDecodeImage() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onDeleteDatabase() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onExtractVideo() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onInsertDatabase() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onNetwork() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onQueryDatabase() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onReadDisk() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onUpdateDatabase() {
        }

        @Override // com.huawei.watchface.videoedit.dfx.DfxGuard.Policy
        public void onWriteDisk() {
        }
    };
    private static ThreadLocal<Policy> sPolicy = new ThreadLocal<Policy>() { // from class: com.huawei.watchface.videoedit.dfx.DfxGuard.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Policy initialValue() {
            return DfxGuard.LAX_POLICY;
        }
    };

    public interface Policy {
        void onDecodeImage();

        void onDeleteDatabase();

        void onExtractVideo();

        void onInsertDatabase();

        void onNetwork();

        void onQueryDatabase();

        void onReadDisk();

        void onUpdateDatabase();

        void onWriteDisk();
    }

    private DfxGuard() {
    }

    public static Policy getPolicy() {
        return sPolicy.get();
    }

    public static void setPolicy(Policy policy) {
        if (policy == null) {
            throw new NullPointerException("policy == null");
        }
        sPolicy.set(policy);
    }
}
