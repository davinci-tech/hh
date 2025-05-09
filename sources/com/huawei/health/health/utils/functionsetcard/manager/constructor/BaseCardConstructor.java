package com.huawei.health.health.utils.functionsetcard.manager.constructor;

import android.content.Context;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.health.utils.functionsetcard.manager.OnCardChangedListener;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class BaseCardConstructor implements CardConstructor {
    public CardConfig mCardConfig;
    protected View mMarketingView;
    public OnCardChangedListener mOnCardChangedListener;
    protected List<Integer> mSuccessList = null;
    protected int mIsMessageLargeCard = -1;
    protected Context mContext = BaseApplication.e();

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void release() {
    }

    public BaseCardConstructor(CardConfig cardConfig) {
        this.mCardConfig = cardConfig;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void init() {
        CardConfig cardConfig = this.mCardConfig;
        if (cardConfig == null || cardConfig.isInitialized()) {
            return;
        }
        subscribeDataChange();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        CardConfig cardConfig = this.mCardConfig;
        if (cardConfig != null) {
            return cardConfig.isSupport(z, i);
        }
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void setOnChangedListener(OnCardChangedListener onCardChangedListener) {
        this.mOnCardChangedListener = onCardChangedListener;
    }

    public void updateSubscribeSuccessList(List<Integer> list) {
        this.mSuccessList = list;
    }

    public boolean isSubscribeType(int i) {
        if (koq.b(this.mSuccessList)) {
            return false;
        }
        return this.mSuccessList.contains(Integer.valueOf(i));
    }

    public void onFirstSubscribeReceived(int i, String str, HiHealthData hiHealthData, long j) {
        CardConfig cardConfig;
        if (this.mOnCardChangedListener != null && (cardConfig = this.mCardConfig) != null && !cardConfig.isInitialized()) {
            this.mOnCardChangedListener.onShowStatusChanged(this.mCardConfig.getCardId(), true, -1);
        }
        unSubscribeDataChange();
    }

    public static class InnerHiSubscribeListener implements HiSubscribeListener {
        private WeakReference<BaseCardConstructor> mConstructorWeakReference;
        private String mTag;

        public InnerHiSubscribeListener(String str, BaseCardConstructor baseCardConstructor) {
            this.mTag = str;
            this.mConstructorWeakReference = new WeakReference<>(baseCardConstructor);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a(this.mTag, "successList:", list, "failList:", list2);
            WeakReference<BaseCardConstructor> weakReference = this.mConstructorWeakReference;
            if (weakReference == null) {
                LogUtil.b(this.mTag, " onResult failed with null mConstructorWeakReference");
                return;
            }
            BaseCardConstructor baseCardConstructor = weakReference.get();
            if (baseCardConstructor != null) {
                baseCardConstructor.updateSubscribeSuccessList(list);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (this.mConstructorWeakReference == null) {
                LogUtil.b(this.mTag, "type", Integer.valueOf(i), " onChange failed with null mConstructorWeakReference");
                return;
            }
            LogUtil.a(this.mTag, "type", Integer.valueOf(i));
            BaseCardConstructor baseCardConstructor = this.mConstructorWeakReference.get();
            if (baseCardConstructor != null) {
                baseCardConstructor.onFirstSubscribeReceived(i, str, hiHealthData, j);
            }
        }
    }

    public static class InnerHiUnSubscribeListener implements HiUnSubscribeListener {
        private String mDesc;
        private String mTag;

        public InnerHiUnSubscribeListener(String str, String str2) {
            this.mTag = str;
            this.mDesc = str2;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a(this.mTag, this.mDesc, Boolean.valueOf(z));
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public View getMarketingView() {
        return this.mMarketingView;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void setMarketingView(View view) {
        this.mMarketingView = view;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void setIsMessageLargeCard(int i) {
        this.mIsMessageLargeCard = i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getIsMessageLargeCard() {
        return this.mIsMessageLargeCard;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public String getCardId() {
        CardConfig cardConfig = this.mCardConfig;
        return cardConfig != null ? cardConfig.getCardId() : "";
    }
}
