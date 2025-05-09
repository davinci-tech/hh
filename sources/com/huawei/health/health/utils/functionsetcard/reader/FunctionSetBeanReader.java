package com.huawei.health.health.utils.functionsetcard.reader;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.ICardChangedCallback;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.marketing.views.HomePageRecommendLayout;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.functionsetcard.view.RatioRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.dpw;
import defpackage.efb;
import defpackage.iyb;
import defpackage.jdl;
import defpackage.jdx;
import defpackage.nrr;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class FunctionSetBeanReader implements FunctionSetSubCardData {
    private static final String AB_TEST_INFO_KEY = "ab_info";
    private static final int AUTO_TEXT_MIN_SIZE = 10;
    private static final int AUTO_TEXT_SIZE_STEP_GRANULARITY = 1;
    public static final String BI_ACTION = "action";
    public static final String BI_DATA_SOURCE = "dataSource";
    public static final String BI_ELEMENT = "element";
    public static final String BI_HAS_DATA = "hasData";
    public static final String BI_HAS_DATA_TYPE = "hasDataType";
    public static final String BI_MODULE_NAME = "moduleName";
    public static final String BI_POSITION = "position";
    public static final String BI_VALUE = "value";
    private static final String CARD_NAME = "cardName";
    private static final String CARD_POSITION = "cardPosition";
    private static final String CARD_TYPE = "cardType";
    private static final String CLICK = "click";
    public static final String CLICK_CANCEL = "5";
    public static final String CLICK_CARD = "3";
    private static final String DATA_STATUS = "dataStatus";
    private static final int DEFAULT_CHINESE_MARGIN_TOP = 2;
    public static final String EDIT_CARD = "2";
    private static final int EXPECTED_BUFFER_DATA = 1024;
    public static final int EXTRA_LANGUAGE_TEXT_SIZE_NORMAL = 12;
    public static final int EXTRA_LANGUAGE_TEXT_SIZE_SMALL = 10;
    public static final String HIDE_CARD = "1";
    private static final int HORIZONTAL_X_AXIS_THRESHOLD = 200;
    private static final int HORIZONTAL_Y_AXIS_THRESHOLD = 400;
    private static final String HW_CHINESE_MEDIUM = "HwChinese-medium";
    private static final String IS_OPEN_SMART_RECOMMEND_BY_USER = "IS_OPEN_SMART_RECOMMEND_BY_USER";
    private static final String LARGE = "large";
    public static final String LONG_CLICK_CARD = "4";
    private static final int MAX_DATA = 128;
    private static final int MAX_LINES = 1;
    public static final String NOTIFY_ITEM_CHANGED_LABEL = "function_set_card_data_changed";
    private static final int ONE = 1;
    private static final int RATIO_HEIGHT_WEIGHT = 585;
    private static final int RATIO_WIDTH_WEIGHT = 470;
    protected static final String RELEASE_TAG = "TimeEat_";
    private static final int SET_BI_LISTENER_DELAY = 1000;
    public static final String SHOW_CARD = "6";
    protected static final int SIZE_DEFAULT = -1;
    protected static final int SIZE_LARGE = 1;
    private static final String SMALL = "small";
    private static final int SMALL_CARD_HEIGHT_LIMIT = 150;
    private static final String SMART_RECOMMEND = "smartRecommend";
    private static final String TAG = "FunctionSetBeanReader";
    private static final int TWO = 2;
    private static int mIsSmartRecommend = -1;
    private static String sAbTestInfo = "";
    private HealthButton adjustButton;
    private HealthButton cancelButton;
    private CustomViewDialog customViewDialog;
    private boolean hasCardDataFromSp;
    private HealthButton hideButton;
    private String mBiDataSource;
    private String mBiHasDataType;
    private ICardChangedCallback mCardChangedCallback;
    private CardConfig mCardConfig;
    private CardConstructor mCardConstructor;
    private Observer mCardRedDotObserver;
    protected RecyclerView.ViewHolder mCardViewHolder;
    protected String mChangeKey;
    public Context mContext;
    private boolean mCurrentIsLargeCard;
    private FunctionSetBean mFunctionSetBean;
    protected boolean mIsDataChangeOnBg;
    protected boolean mIsShowDot;
    protected int mLastUniCode;
    private int mLayoutId;
    private int mPosition;
    private String mTag;
    private HealthTextView popupTittle;
    protected volatile boolean hasCardData = false;
    protected volatile boolean hasCardDataLastTime = false;
    protected int mCardRefreshType = -1;
    private String mBiHasData = Constants.NULL;
    private Set<View> mHasBiEventSet = new HashSet();
    private int mExtraWidth = 0;
    private boolean mIsExtraWidth = false;
    private final Object mIsSameBeanLock = new Object();
    private View.OnClickListener mCardClickListener = new View.OnClickListener() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FunctionSetBeanReader.this.onCardViewClickListener();
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnLongClickListener mCardLongClickListener = new View.OnLongClickListener() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.3
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            LogUtil.a(FunctionSetBeanReader.TAG, "OnLongClickListener true");
            FunctionSetBeanReader.this.onCardViewLongClickListener();
            return true;
        }
    };
    private boolean mIsInit = true;
    protected FunctionSetBeanHelper mBeanRequestHelper = FunctionSetBeanHelper.c();

    private boolean isSameVisibility(boolean z, int i) {
        if (z && i == 0) {
            return true;
        }
        return !z && i == 8;
    }

    protected void adjustDataAndUnitLocation(LinearLayout linearLayout, HealthTextView healthTextView, HealthTextView healthTextView2) {
    }

    protected int getButtonColorId() {
        return R.color._2131297756_res_0x7f0905dc;
    }

    protected int getButtonTextId() {
        return 0;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public int getCardType() {
        return -1;
    }

    protected float getChartBottomMargin() {
        return 0.0f;
    }

    protected float getChartTopMargin() {
        return 2.0f;
    }

    public abstract String getChildTag();

    protected int getDataTypeTextColor() {
        return R.color._2131299241_res_0x7f090ba9;
    }

    protected abstract int getDefaultImage(boolean z);

    protected abstract String getDefaultLargeCardImageRes();

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public int getExtraWidth() {
        return 0;
    }

    protected HandleCacheDataRunnable getRunnable() {
        return null;
    }

    protected boolean handleHealthModelSp(String str) {
        return false;
    }

    protected boolean isMessageDefaultLargeCard() {
        return false;
    }

    protected boolean isOverseaDefaultLargeCard() {
        return false;
    }

    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void refreshRedDotSyncNotify(boolean z) {
    }

    protected void reportCustomBiEvent() {
    }

    protected void setGifImage(ImageView imageView, boolean z) {
    }

    public FunctionSetBeanReader(Context context, String str, CardConfig cardConfig) {
        this.mContext = context;
        this.mTag = str;
        this.mCardConfig = cardConfig;
        registerCardRedDotListener();
    }

    private void registerCardRedDotListener() {
        e eVar = new e(this);
        this.mCardRedDotObserver = eVar;
        ObserverManagerUtil.d(eVar, "HOME_FRAGMENT_ACTIVITY_FOR_RESULT");
    }

    /* loaded from: classes8.dex */
    static class e implements Observer {
        private WeakReference<FunctionSetBeanReader> b;

        public e(FunctionSetBeanReader functionSetBeanReader) {
            this.b = new WeakReference<>(functionSetBeanReader);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            FunctionSetBeanReader functionSetBeanReader = this.b.get();
            if (functionSetBeanReader == null) {
                LogUtil.h(FunctionSetBeanReader.TAG, "reader is null in RedDotListener.");
                return;
            }
            if (objArr != null) {
                Object obj = objArr[0];
                if ((obj instanceof String) && obj.equals(functionSetBeanReader.getChildTag()) && functionSetBeanReader.getIsShowDot()) {
                    LogUtil.a(FunctionSetBeanReader.TAG, "refresh red dot, TAG: ", functionSetBeanReader.getChildTag());
                    functionSetBeanReader.refreshRedDotSyncNotify(false);
                }
            }
        }
    }

    public void notifyItemChanged(FunctionSetBean functionSetBean) {
        if (functionSetBean == null) {
            LogUtil.a(this.mTag, "bean is null");
            return;
        }
        if (!isUpdateContextSuccess()) {
            ReleaseLogUtil.d("TimeEat_FunctionSetBeanReader", "notifyItemChanged updateContext failed.");
            return;
        }
        if (this.mCardViewHolder == null) {
            LogUtil.h(this.mTag, "notifyItemChanged holder is null");
        } else {
            boolean isSameBean = isSameBean(functionSetBean);
            boolean isSameData = isSameData(functionSetBean);
            LogUtil.a(this.mTag, "isSameBean: ", Boolean.valueOf(isSameBean), ", isSameData: ", Boolean.valueOf(isSameData));
            if (isSameBean && isSameData) {
                return;
            }
        }
        setFunctionSetBean(functionSetBean);
        if (this.mCardChangedCallback == null) {
            LogUtil.b(TAG, "mCardChangedCallback is null");
        } else {
            LogUtil.a(this.mTag, "notifyItemChanged");
            this.mCardChangedCallback.notifyCardChanged(this);
        }
    }

    private boolean isActivityInvalid() {
        Context context = this.mContext;
        if (!(context instanceof Activity)) {
            LogUtil.a(TAG, "mContext is not activity");
            return true;
        }
        if (!((Activity) context).isDestroyed()) {
            return false;
        }
        LogUtil.a(TAG, "Activity is isDestroyed");
        return true;
    }

    private boolean isUpdateContextSuccess() {
        if (!isActivityInvalid()) {
            return true;
        }
        Activity activity = BaseApplication.getActivity();
        if (activity != null) {
            this.mContext = activity;
            return true;
        }
        ReleaseLogUtil.d("TimeEat_FunctionSetBeanReader", "updateContext failed, getActivity is null.");
        return false;
    }

    private boolean isSameData(FunctionSetBean functionSetBean) {
        RecyclerView.ViewHolder viewHolder = this.mCardViewHolder;
        if (!(viewHolder instanceof MyHolder)) {
            LogUtil.h(this.mTag, "isSameData, mCardViewHolder is null");
            return false;
        }
        MyHolder myHolder = (MyHolder) viewHolder;
        if (TextUtils.isEmpty(functionSetBean.i())) {
            if (!isEqualString(functionSetBean.j(), myHolder.f2489a.getText())) {
                return false;
            }
        } else {
            if (!isEqualString(functionSetBean.i(), myHolder.f2489a.getText())) {
                return false;
            }
            if (functionSetBean.YA_() != null && !functionSetBean.YA_().equals(myHolder.f2489a.getCompoundDrawablesRelative()[0])) {
                return false;
            }
        }
        return isEqualString(functionSetBean.d(), myHolder.e.getText()) && isEqualString(functionSetBean.m(), myHolder.af.getText()) && isEqualString(functionSetBean.c(), myHolder.b.getText()) && isSameVisibility(functionSetBean.h(), myHolder.m.getVisibility()) && isEqualString(functionSetBean.o(), myHolder.d.getText());
    }

    private boolean isEqualString(CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence) && TextUtils.isEmpty(charSequence2)) {
            return true;
        }
        if (TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(charSequence2)) {
            return false;
        }
        return charSequence.toString().equals(charSequence2.toString());
    }

    private boolean isSameBean(FunctionSetBean functionSetBean) {
        int hash = Objects.hash(functionSetBean.n(), getCharSequenceStr(functionSetBean.j()), getCharSequenceStr(functionSetBean.d()), getCharSequenceStr(functionSetBean.m()), functionSetBean.e(), Integer.valueOf(functionSetBean.g()), functionSetBean.k(), functionSetBean.q(), Boolean.valueOf(functionSetBean.h()), functionSetBean.Yz_(), getCharSequenceStr(functionSetBean.c()), Integer.valueOf(functionSetBean.a()), getCharSequenceStr(functionSetBean.i()), functionSetBean.YA_(), Integer.valueOf(functionSetBean.l()));
        synchronized (this.mIsSameBeanLock) {
            LogUtil.a(this.mTag, "uniqueCode: ", Integer.valueOf(hash), ", mLastUniCode: ", Integer.valueOf(this.mLastUniCode));
            if (this.mLastUniCode == hash) {
                return true;
            }
            this.mLastUniCode = hash;
            return false;
        }
    }

    private String getCharSequenceStr(CharSequence charSequence) {
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public static <T> void setHasCardData(WeakReference<T> weakReference, boolean z) {
        if (weakReference == null) {
            LogUtil.a(TAG, "weakReference is null");
            return;
        }
        T t = weakReference.get();
        if (!FunctionSetBeanReader.class.isInstance(t)) {
            LogUtil.a(TAG, "not FunctionSetBeanReader instance");
            return;
        }
        FunctionSetBeanReader functionSetBeanReader = (FunctionSetBeanReader) t;
        Object[] objArr = new Object[6];
        objArr[0] = "cardId: ";
        objArr[1] = functionSetBeanReader.getCardConfig() == null ? "" : functionSetBeanReader.getCardConfig().getCardId();
        objArr[2] = ", hasCardDataLastTime: ";
        objArr[3] = Boolean.valueOf(functionSetBeanReader.hasCardData);
        objArr[4] = ", hasCardData: ";
        objArr[5] = Boolean.valueOf(z);
        LogUtil.a(TAG, objArr);
        functionSetBeanReader.hasCardDataLastTime = functionSetBeanReader.hasCardData;
        functionSetBeanReader.hasCardData = z;
        functionSetBeanReader.saveHasCardDataToSp();
    }

    public void setHasCardData(boolean z) {
        this.hasCardData = z;
        saveHasCardDataToSp();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        readCardData();
    }

    public void setRefreshReason(int i) {
        this.mCardRefreshType = i;
    }

    public static <T> void saveDataFromHealthApi(String str, WeakReference<T> weakReference, HiHealthData hiHealthData) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(str, "invalid tag");
            return;
        }
        if (weakReference == null) {
            LogUtil.a(str, "weakReference is null");
            return;
        }
        T t = weakReference.get();
        if (!FunctionSetBeanReader.class.isInstance(t)) {
            LogUtil.a(str, "not FunctionSetBeanReader instance");
            return;
        }
        FunctionSetBeanReader functionSetBeanReader = (FunctionSetBeanReader) t;
        functionSetBeanReader.handleHealthData(str, hiHealthData);
        FunctionSetBeanHelper functionSetBeanHelper = functionSetBeanReader.mBeanRequestHelper;
        if (functionSetBeanHelper != null) {
            functionSetBeanHelper.e(str, hiHealthData);
        } else {
            LogUtil.a(str, "reader.mBeanRequestHelper is null");
        }
    }

    public void saveDataFromHealthApi(String str, FunctionSetBeanReader functionSetBeanReader, HiHealthData hiHealthData) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(str, "invalid tag");
            return;
        }
        if (functionSetBeanReader == null) {
            LogUtil.a(str, "reader is null");
            return;
        }
        functionSetBeanReader.handleHealthData(str, hiHealthData);
        FunctionSetBeanHelper functionSetBeanHelper = functionSetBeanReader.mBeanRequestHelper;
        if (functionSetBeanHelper != null) {
            functionSetBeanHelper.e(str, hiHealthData);
        } else {
            LogUtil.a(str, "reader.mBeanRequestHelper is null");
        }
    }

    private void handleHealthData(String str, HiHealthData hiHealthData) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            if (getRunnable() != null) {
                getRunnable().handleCacheData(hiHealthData, true);
            }
        }
        LogUtil.a(str, "refresh card by healthApi data, time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    protected String getHuid() {
        FunctionSetBeanHelper functionSetBeanHelper = this.mBeanRequestHelper;
        return functionSetBeanHelper == null ? "0" : functionSetBeanHelper.c;
    }

    private void saveHasCardDataToSp() {
        LogUtil.a(this.mTag, "saveHasCardDataToSp");
        if (this.hasCardDataFromSp == this.hasCardData) {
            LogUtil.a(this.mTag, "same value");
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.2
                @Override // java.lang.Runnable
                public void run() {
                    if (FunctionSetBeanReader.this.mCardConfig == null) {
                        LogUtil.a(FunctionSetBeanReader.this.mTag, "mCardConfig is null");
                    } else if (SharedPreferenceManager.e(FunctionSetBeanReader.this.mContext, Integer.toString(AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE), FunctionSetBeanReader.this.mCardConfig.getCardId(), Boolean.toString(FunctionSetBeanReader.this.hasCardData), new StorageParams()) != 0) {
                        LogUtil.a(FunctionSetBeanReader.this.mTag, "setSharedPreference failed");
                    } else {
                        FunctionSetBeanReader functionSetBeanReader = FunctionSetBeanReader.this;
                        functionSetBeanReader.hasCardDataFromSp = functionSetBeanReader.hasCardData;
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater, int i) {
        this.mPosition = i;
        LogUtil.a(this.mTag, "create mCardViewHolder");
        MyHolder myHolder = new MyHolder(layoutInflater.inflate(R.layout.function_set_items, viewGroup, false), this.mContext, this);
        this.mCardViewHolder = myHolder;
        return myHolder;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        if (!(viewHolder instanceof MyHolder)) {
            LogUtil.h(this.mTag, "onBindViewHolder holder is not FunctionSetViewAdapter.MyHolder");
            return;
        }
        RecyclerView.ViewHolder viewHolder2 = this.mCardViewHolder;
        if (viewHolder2 == null || !viewHolder2.equals(viewHolder)) {
            LogUtil.h(this.mTag, "onBindViewHolder mCardViewHolder is null");
            this.mCardViewHolder = viewHolder;
        }
        if (functionSetSubCardData == null) {
            LogUtil.h(this.mTag, "onBindViewHolder reader is null");
            return;
        }
        if (this.mIsInit) {
            this.mIsInit = false;
            if (requestData(functionSetSubCardData)) {
                LogUtil.a(this.mTag, "handle sp and refresh end");
                return;
            }
        }
        if (functionSetSubCardData.getFunctionSetBean() == null) {
            LogUtil.h(this.mTag, "onBindViewHolder bean is null");
            setFunctionSetBean(buildEmptyCardBean());
        }
        LogUtil.a(this.mTag, "onBindViewHolder CardId = ", functionSetSubCardData.getCardConfig().getCardId());
        final MyHolder myHolder = (MyHolder) viewHolder;
        LogUtil.a(this.mTag, "hasCardData: " + functionSetSubCardData.hasCardData());
        if (functionSetSubCardData.getFunctionSetBean().q() == FunctionSetBean.ViewType.EMPTY_VIEW) {
            LogUtil.a(this.mTag, "EMPTY_VIEW: " + this);
            nsy.cMA_(myHolder.h, 8);
            nsy.cMA_(myHolder.f, 8);
            nsy.cMA_(myHolder.v, 8);
            nsy.cMA_(myHolder.w, 8);
            nsy.cMA_(myHolder.o, 8);
            if (efb.e(this.mContext)) {
                this.mCurrentIsLargeCard = true;
                initNoDataView(myHolder);
                showEmptyCard(myHolder, functionSetSubCardData.getFunctionSetBean());
            } else {
                bindMarketingViewHolder(myHolder);
            }
            dpw.YH_(this.mContext, myHolder.ae, myHolder.s, this.mCurrentIsLargeCard, false);
        } else if (functionSetSubCardData.getFunctionSetBean().q() == FunctionSetBean.ViewType.DATA_VIEW || this.hasCardData) {
            this.mCurrentIsLargeCard = true;
            dpw.YH_(this.mContext, myHolder.ae, myHolder.s, true, false);
            LogUtil.a(this.mTag, "DATA_VIEW: " + this);
            nsy.cMA_(myHolder.h, 8);
            nsy.cMA_(myHolder.v, 8);
            nsy.cMA_(myHolder.w, 8);
            nsy.cMA_(myHolder.o, 8);
            nsy.cMA_(myHolder.f, 0);
            showDataCard(myHolder, functionSetSubCardData);
            onBottomLayoutClickListener(myHolder.g);
        }
        myHolder.e(this.mContext);
        myHolder.j.setOnClickListener(this.mCardClickListener);
        if (efb.k()) {
            myHolder.j.setOnLongClickListener(this.mCardLongClickListener);
        }
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.5
            @Override // java.lang.Runnable
            public void run() {
                FunctionSetBeanReader.this.setBiEventListener(myHolder.j);
            }
        }, 1000L);
    }

    private void initNoDataView(MyHolder myHolder) {
        if (efb.e(BaseApplication.getContext())) {
            LogUtil.a(this.mTag, "initNoDataView: oversea");
            showOverseaEmptyCard(myHolder);
        } else if (this.mCurrentIsLargeCard) {
            LogUtil.a(this.mTag, "initNoDataView: LargeCard");
            showEmptyLargeCard(myHolder);
        } else if (nsn.r()) {
            LogUtil.a(this.mTag, "initNoDataView: large font small card");
            showSmallEmptyCardForLargeFont(myHolder);
        } else {
            LogUtil.a(this.mTag, "initNoDataView: small card");
            showSmallEmptyCard(myHolder);
        }
    }

    private void showOverseaEmptyCard(MyHolder myHolder) {
        if (myHolder.h != null) {
            myHolder.h.setVisibility(0);
            return;
        }
        myHolder.h = (LinearLayout) ((ViewStub) myHolder.itemView.findViewById(R.id.function_set_empty_layout)).inflate();
        myHolder.l = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_empty_items_title);
        myHolder.ad = (HealthImageView) myHolder.itemView.findViewById(R.id.function_set_empty_red_dot);
        myHolder.i = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_empty_items_description);
        myHolder.h.setVisibility(0);
    }

    private void showEmptyLargeCard(MyHolder myHolder) {
        if (myHolder.o != null) {
            myHolder.o.setVisibility(0);
            return;
        }
        myHolder.o = (RelativeLayout) ((ViewStub) myHolder.itemView.findViewById(R.id.function_set_market_layout_large)).inflate();
        myHolder.s = (ImageView) myHolder.itemView.findViewById(R.id.function_set_market_layout_large_bg);
        myHolder.q = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_market_title_large);
        myHolder.r = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_market_content_large);
        myHolder.t = (HealthButton) myHolder.itemView.findViewById(R.id.large_card_button);
        myHolder.p = (ImageView) myHolder.itemView.findViewById(R.id.function_set_market_image_large);
        myHolder.k = (ImageView) myHolder.itemView.findViewById(R.id.function_set_market_gif);
        myHolder.o.setVisibility(0);
    }

    private void showSmallEmptyCard(MyHolder myHolder) {
        if (myHolder.v != null) {
            myHolder.v.setVisibility(0);
            return;
        }
        myHolder.v = (RelativeLayout) ((ViewStub) myHolder.itemView.findViewById(R.id.function_set_market_layout)).inflate();
        myHolder.v = (RelativeLayout) myHolder.itemView.findViewById(R.id.function_set_market_layout);
        myHolder.z = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_market_title);
        myHolder.u = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_market_content);
        myHolder.aa = (ImageView) myHolder.itemView.findViewById(R.id.function_set_market_image);
        myHolder.v.setVisibility(0);
    }

    private void showSmallEmptyCardForLargeFont(MyHolder myHolder) {
        if (myHolder.w != null) {
            myHolder.w.setVisibility(0);
            return;
        }
        myHolder.w = (RelativeLayout) ((ViewStub) myHolder.itemView.findViewById(R.id.function_set_market_layout_large_font)).inflate();
        myHolder.ab = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_market_title_large_font);
        myHolder.y = (HealthTextView) myHolder.itemView.findViewById(R.id.function_set_market_content_large_font);
        myHolder.ac = (ImageView) myHolder.itemView.findViewById(R.id.function_set_market_image_large_font);
        myHolder.w.setVisibility(0);
    }

    private boolean requestData(FunctionSetSubCardData functionSetSubCardData) {
        if (this.mBeanRequestHelper == null) {
            LogUtil.a(this.mTag, "mBeanRequestHelper is null");
            functionSetSubCardData.setFunctionSetBean(buildEmptyCardBean());
            functionSetSubCardData.initCardData();
            return false;
        }
        LogUtil.a(this.mTag, "getFunctionSetBean");
        if (this.mTag.equals("HealthModel_FunctionSetHealthModelCardReader")) {
            return handleHealthModelSp(this.mBeanRequestHelper.a());
        }
        HiHealthData a2 = this.mBeanRequestHelper.a(this.mTag);
        if (a2 == null) {
            LogUtil.a(this.mTag, "data is null");
            functionSetSubCardData.setFunctionSetBean(buildEmptyCardBean());
            functionSetSubCardData.initCardData();
            return false;
        }
        handleResult(a2, false);
        functionSetSubCardData.initCardData();
        return true;
    }

    private void handleResult(HiHealthData hiHealthData, boolean z) {
        HandleCacheDataRunnable runnable = getRunnable();
        try {
            synchronized (this) {
                if (runnable != null) {
                    runnable.handleCacheData(hiHealthData, z);
                }
            }
        } catch (Exception e2) {
            LogUtil.h(this.mTag, "handleResult fail, isNewData=", Boolean.valueOf(z), ", e=", LogAnonymous.b((Throwable) e2));
            FunctionSetBeanHelper functionSetBeanHelper = this.mBeanRequestHelper;
            if (functionSetBeanHelper != null) {
                functionSetBeanHelper.e(this.mTag, null);
            }
        }
    }

    public void refreshCardBySp(final FunctionSetBean functionSetBean) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.1
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetBeanReader.this.refreshCardBySp(functionSetBean);
                }
            });
            return;
        }
        LogUtil.a(this.mTag, "sync refreshCardBySp");
        if (functionSetBean == null) {
            LogUtil.a(this.mTag, "bean is null");
            setFunctionSetBean(buildEmptyCardBean());
            onBindViewHolder(this.mCardViewHolder, this);
            ReleaseLogUtil.e(RELEASE_TAG + this.mTag, "REFRESH_HOME_END refreshCardBySp, hasCardData: ", false);
            return;
        }
        if (!isUpdateContextSuccess()) {
            LogUtil.h(this.mTag, "refreshCardBySp Activity is destroyed, return");
            ReleaseLogUtil.d(RELEASE_TAG + this.mTag, "REFRESH_HOME_END refreshCardBySp, hasCardData: ", false);
            return;
        }
        if (this.mCardViewHolder == null) {
            LogUtil.a(this.mTag, "mCardViewHolder = null");
            ReleaseLogUtil.e(RELEASE_TAG + this.mTag, "REFRESH_HOME_END refreshCardBySp, hasCardData: ", false);
            return;
        }
        if (!this.hasCardData) {
            setFunctionSetBean(buildEmptyCardBean());
            LogUtil.a(this.mTag, "refreshCardBySp: show empty card");
            onBindViewHolder(this.mCardViewHolder, this);
            ReleaseLogUtil.e(RELEASE_TAG + this.mTag, "REFRESH_HOME_END refreshCardBySp, hasCardData: ", false);
            return;
        }
        isSameBean(functionSetBean);
        setFunctionSetBean(functionSetBean);
        LogUtil.a(this.mTag, "refreshCardBySp: show data card, bean: ", functionSetBean.toString());
        onBindViewHolder(this.mCardViewHolder, this);
        ReleaseLogUtil.e(RELEASE_TAG + this.mTag, "REFRESH_HOME_END refreshCardBySp, hasCardData: ", Boolean.valueOf(this.hasCardData));
    }

    private void bindMarketingViewHolder(MyHolder myHolder) {
        LogUtil.a(this.mTag, "Marketing view: " + this);
        this.mCurrentIsLargeCard = isDefaultLargeCard();
        if (nsn.ag(BaseApplication.getActivity())) {
            this.mCurrentIsLargeCard = true;
        }
        initNoDataView(myHolder);
        LogUtil.a(this.mTag, "mMarketingContent invalid, mCurrentIsLargeCard: ", Boolean.valueOf(this.mCurrentIsLargeCard), ", mIsMessageLargeCard: ", Integer.valueOf(getIsMessageLargeCard()));
        bindViewHolder(myHolder, this.mCurrentIsLargeCard);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean getCurrentIsLargeCard() {
        return this.mCurrentIsLargeCard;
    }

    private void bindViewHolder(MyHolder myHolder, boolean z) {
        if (!z) {
            if (nsn.r()) {
                setTheme(myHolder.ab);
                setDescription(myHolder.y);
                setImage(myHolder.ac, z);
            } else {
                setTheme(myHolder.z);
                setDescription(myHolder.u);
                setImage(myHolder.aa, z);
            }
        } else {
            setTheme(myHolder.q);
            setDescription(myHolder.r);
            if (efb.k()) {
                setButtonText(myHolder.t);
                setImage(myHolder.s, z);
                setGifImage(myHolder.s, z);
            } else {
                setImage(myHolder.p, z);
            }
        }
        setClickEventForButton(myHolder.t);
    }

    protected void setClickEventForButton(HealthButton healthButton) {
        if (healthButton == null) {
            return;
        }
        healthButton.setClickable(false);
    }

    private void setButtonText(HealthButton healthButton) {
        if (healthButton == null) {
            LogUtil.h(this.mTag, "setButtonText largeNoDataButton is null");
            return;
        }
        int buttonTextId = getButtonTextId();
        if (buttonTextId == 0) {
            healthButton.setVisibility(8);
            return;
        }
        healthButton.setVisibility(0);
        healthButton.setText(this.mContext.getResources().getString(buttonTextId));
        healthButton.setTextColor(this.mContext.getResources().getColor(getButtonColorId()));
    }

    private void setTheme(HealthTextView healthTextView) {
        LogUtil.a(this.mTag, "setTheme");
        nsy.cMr_(healthTextView, getCardTitle());
    }

    private void setDescription(HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h(this.mTag, "setDescription textview is null");
            return;
        }
        LogUtil.a(this.mTag, "setDescription");
        if (dpw.b(this.mContext) < 150) {
            healthTextView.setMaxLines(1);
        }
        healthTextView.setText(getEmptyCardDescription());
    }

    private void setImage(ImageView imageView, boolean z) {
        LogUtil.a(this.mTag, "setImage");
        Drawable defaultImg = getDefaultImg(z);
        if (defaultImg == null || nsn.cLi_(defaultImg)) {
            return;
        }
        nsy.cMm_(imageView, defaultImg);
    }

    private Drawable getDefaultImg(boolean z) {
        if (efb.k()) {
            return this.mContext.getResources().getDrawable(getDefaultImage(z));
        }
        return getDefaultMarketingImage();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean hasCardData() {
        return this.hasCardData;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean hasCardDataLastTime() {
        return this.hasCardDataLastTime;
    }

    private void showEmptyCard(MyHolder myHolder, FunctionSetBean functionSetBean) {
        if (functionSetBean.q() != FunctionSetBean.ViewType.EMPTY_VIEW) {
            LogUtil.h(this.mTag, "showEmptyCard getViewType is not equal to EMPTY_VIEW");
            return;
        }
        myHolder.a();
        nsy.cMr_(myHolder.l, getCardTitle());
        nsy.cMr_(myHolder.i, getEmptyCardDescription());
        nsy.cMj_(myHolder.h, getEmptyCardBackground());
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        if (efb.e(BaseApplication.getContext()) || MLAsrConstants.LAN_ZH.equalsIgnoreCase(language) || nsn.t()) {
            return;
        }
        nsy.cMx_(myHolder.i, 8.0f, 1);
    }

    private void showDataCard(MyHolder myHolder, FunctionSetSubCardData functionSetSubCardData) {
        FunctionSetBean functionSetBean = functionSetSubCardData.getFunctionSetBean();
        if (functionSetBean.q() != FunctionSetBean.ViewType.DATA_VIEW) {
            LogUtil.h(this.mTag, "showDataCard getViewType is not equal to DATA_VIEW");
            return;
        }
        resetHasDataViewConfig(myHolder);
        myHolder.ai.setText(getCardTitle());
        nsy.cMs_(myHolder.e, functionSetBean.d(), true);
        nsy.cMs_(myHolder.af, functionSetBean.m(), true);
        adjustDataAndUnitLocation(myHolder.c, myHolder.e, myHolder.af);
        if (!TextUtils.isEmpty(functionSetBean.i())) {
            LogUtil.a(TAG, "show card message, content=", functionSetBean.i());
            myHolder.f2489a.setText(functionSetBean.i());
            myHolder.f2489a.setSingleLine(false);
            myHolder.f2489a.setMaxLines(2);
            myHolder.b.setText("");
            Drawable YA_ = functionSetBean.YA_();
            if (YA_ != null) {
                YA_.setBounds(0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446), this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
                myHolder.f2489a.setCompoundDrawablesRelative(YA_, null, null, null);
                myHolder.f2489a.setCompoundDrawablePadding(this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb));
            } else {
                myHolder.f2489a.setCompoundDrawablesRelative(null, null, null, null);
            }
        } else {
            LogUtil.a(TAG, "no card message show time");
            myHolder.f2489a.setText(functionSetBean.j());
            myHolder.f2489a.setSingleLine(true);
            myHolder.f2489a.setCompoundDrawablesRelative(null, null, null, null);
            myHolder.b.setText(functionSetBean.c());
        }
        if (!TextUtils.isEmpty(functionSetBean.o())) {
            myHolder.d.setVisibility(0);
            myHolder.d.setText(functionSetBean.o());
            Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable._2131429712_res_0x7f0b0950);
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                drawable = nrz.cKm_(BaseApplication.getContext(), drawable);
            }
            drawable.setBounds(0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435), this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435));
            myHolder.d.setCompoundDrawablesRelative(null, null, drawable, null);
            myHolder.f2489a.setSingleLine(false);
            myHolder.f2489a.setMaxLines(2);
            myHolder.f2489a.setAutoTextSize(2, 10.0f);
            myHolder.b.setTextSize(2, 10.0f);
            myHolder.d.setTextSize(2, 10.0f);
        } else {
            myHolder.d.setVisibility(8);
            myHolder.f2489a.setAutoTextSize(2, 12.0f);
            myHolder.f2489a.setAutoTextInfo(10, 1, 2);
            myHolder.b.setTextSize(2, 12.0f);
        }
        myHolder.b.setTextColor(this.mContext.getResources().getColor(getDataTypeTextColor()));
        if (!LanguageUtil.h(BaseApplication.getContext()) && !nsn.t()) {
            myHolder.f2489a.setAutoTextSize(1, 10.0f);
            myHolder.b.setTextSize(1, 10.0f);
            myHolder.d.setTextSize(1, 10.0f);
        }
        myHolder.j.setCardBackgroundColor(this.mContext.getResources().getColor(R.color._2131296699_res_0x7f0901bb));
        View createCardView = functionSetSubCardData.createCardView();
        LogUtil.a(this.mTag, "createCardView: ", createCardView);
        if (!this.mIsExtraWidth) {
            this.mExtraWidth = functionSetSubCardData.getExtraWidth();
        }
        if (createCardView != null && (createCardView.getParent() instanceof ViewGroup)) {
            ((ViewGroup) createCardView.getParent()).removeView(createCardView);
        }
        if (createCardView != null) {
            setChartView(myHolder, createCardView);
        }
        bindRecommendView(myHolder);
        boolean h = functionSetBean.h();
        String str = this.mTag;
        Object[] objArr = new Object[2];
        objArr[0] = "ShowRedDot: ";
        objArr[1] = h ? functionSetBean.toString() : "false";
        LogUtil.a(str, objArr);
        if (h) {
            myHolder.m.setVisibility(0);
        } else {
            myHolder.m.setVisibility(8);
        }
        myHolder.a(this.mContext, (FunctionSetBeanReader) functionSetSubCardData);
    }

    private void resetHasDataViewConfig(MyHolder myHolder) {
        myHolder.e.setTypeface(nsk.cKN_());
        myHolder.e.setSingleLine(true);
        myHolder.e.setMaxLines(1);
        if (nsn.p()) {
            myHolder.e.setAutoTextSize(1, 32.0f);
            myHolder.af.setAutoTextSize(1, 16.0f);
            myHolder.ai.setAutoTextSize(1, 20.0f);
        } else {
            myHolder.e.setAutoTextSize(2, 26.0f);
            myHolder.af.setAutoTextSize(2, 12.0f);
            myHolder.ai.setAutoTextSize(2, 16.0f);
        }
    }

    private void bindRecommendView(MyHolder myHolder) {
        ICardChangedCallback iCardChangedCallback = this.mCardChangedCallback;
        if (iCardChangedCallback == null) {
            LogUtil.h(TAG, "mCardChangedCallback is null when bindRecommendView.");
            return;
        }
        View loadMarketingView = iCardChangedCallback.loadMarketingView(this.mCardConfig.getCardId(), myHolder.getLayoutPosition());
        if (isKeepRecommendView(myHolder.x.getChildAt(0), loadMarketingView)) {
            LogUtil.a(TAG, "same HomePageRecommendLayout. No need to refresh. mCardConfig.getCardId() = ", this.mCardConfig.getCardId());
            return;
        }
        myHolder.x.removeAllViews();
        if (loadMarketingView != null) {
            ViewParent parent = loadMarketingView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(loadMarketingView);
            }
            myHolder.x.addView(loadMarketingView, -1, -1);
        }
    }

    private boolean isKeepRecommendView(View view, View view2) {
        if (!(view instanceof HomePageRecommendLayout) || !(view2 instanceof HomePageRecommendLayout)) {
            return false;
        }
        HomePageRecommendLayout homePageRecommendLayout = (HomePageRecommendLayout) view;
        HomePageRecommendLayout homePageRecommendLayout2 = (HomePageRecommendLayout) view2;
        if (homePageRecommendLayout2.getGridContent() == null) {
            LogUtil.h(TAG, "new layout is wrong.keep");
            return true;
        }
        boolean equals = homePageRecommendLayout2.equals(homePageRecommendLayout);
        LogUtil.a(TAG, "presentLayout.getRecommendCard() = ", homePageRecommendLayout.getRecommendCard(), "; newLayout.getRecommendCard() = ", homePageRecommendLayout2.getRecommendCard(), "; isRefresh = ", Boolean.valueOf(equals));
        return equals;
    }

    protected void setDataVisible(MyHolder myHolder, boolean z) {
        if (z) {
            myHolder.e.setVisibility(0);
        } else {
            myHolder.e.setVisibility(8);
        }
    }

    protected void setHealthRecordTextStyles(MyHolder myHolder) {
        Context context = BaseApplication.getContext();
        if (LanguageUtil.ak(context) || LanguageUtil.p(context) || LanguageUtil.bn(context)) {
            myHolder.af.setMaxLines(1);
        }
        if (myHolder.af.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) myHolder.af.getLayoutParams();
            layoutParams.setMarginStart(0);
            myHolder.af.setLayoutParams(layoutParams);
        }
    }

    private void setChartView(MyHolder myHolder, View view) {
        if (this.mExtraWidth > 0) {
            ViewGroup.LayoutParams layoutParams = myHolder.n.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.width += this.mExtraWidth;
                myHolder.n.setLayoutParams(layoutParams2);
                this.mExtraWidth = 0;
                this.mIsExtraWidth = true;
            }
        }
        myHolder.n.removeAllViews();
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams3.gravity = 17;
        view.setLayoutParams(layoutParams3);
        myHolder.n.addView(view);
    }

    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(getCardTitle()).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(getEmptyCardDescription()).d(this.mContext).c();
    }

    protected String getCardTitle() {
        int identifier;
        if (this.mCardConfig == null) {
            LogUtil.h(this.mTag, "getEmptyCardTitle mCardConfig is null");
            return "";
        }
        Resources resources = this.mContext.getResources();
        String cardNameRes = this.mCardConfig.getCardNameRes();
        LogUtil.a(this.mTag, "cardNameRes: ", cardNameRes);
        return (cardNameRes == null || (identifier = resources.getIdentifier(cardNameRes, "string", this.mContext.getPackageName())) <= 0) ? "" : this.mContext.getResources().getString(identifier);
    }

    private String getEmptyCardDescription() {
        int identifier;
        int identifier2;
        if (this.mCardConfig == null) {
            LogUtil.h(this.mTag, "getEmptyCardDescription mCardConfig is null");
            return "";
        }
        Resources resources = this.mContext.getResources();
        if (efb.e(this.mContext)) {
            String noDataDescRes = this.mCardConfig.getNoDataDescRes();
            if (noDataDescRes != null && (identifier2 = resources.getIdentifier(noDataDescRes, "string", this.mContext.getPackageName())) > 0) {
                return this.mContext.getResources().getString(identifier2);
            }
        } else {
            String defaultMarketingDescRes = this.mCardConfig.getDefaultMarketingDescRes();
            if (defaultMarketingDescRes != null && (identifier = resources.getIdentifier(defaultMarketingDescRes, "string", this.mContext.getPackageName())) > 0) {
                return getDefaultMarketingDes(identifier);
            }
        }
        return "";
    }

    protected String getDefaultMarketingDes(int i) {
        return this.mContext.getResources().getString(i);
    }

    private Drawable getEmptyCardBackground() {
        int identifier;
        if (this.mCardConfig == null) {
            LogUtil.h(this.mTag, "getEmptyCardBackground mCardConfig is null");
            return null;
        }
        Resources resources = this.mContext.getResources();
        String noDataBgRes = this.mCardConfig.getNoDataBgRes();
        if (noDataBgRes == null || (identifier = resources.getIdentifier(noDataBgRes, "drawable", this.mContext.getPackageName())) <= 0) {
            return null;
        }
        return this.mContext.getResources().getDrawable(identifier);
    }

    private Drawable getDefaultMarketingImage() {
        int identifier;
        if (this.mCardConfig == null) {
            LogUtil.h(this.mTag, "getDefaultMarketingImage mCardConfig is null");
            return null;
        }
        Resources resources = this.mContext.getResources();
        String defaultLargeCardImageRes = isDefaultLargeCard() ? getDefaultLargeCardImageRes() : null;
        if (defaultLargeCardImageRes == null) {
            defaultLargeCardImageRes = this.mCardConfig.getDefaultMarketingImageRes();
        }
        if (defaultLargeCardImageRes == null || defaultLargeCardImageRes.length() == 0 || (identifier = resources.getIdentifier(defaultLargeCardImageRes, "drawable", this.mContext.getPackageName())) <= 0) {
            return null;
        }
        return this.mContext.getResources().getDrawable(identifier);
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2489a;
        private ImageView aa;
        private HealthTextView ab;
        private ImageView ac;
        private HealthImageView ad;
        private RatioRelativeLayout ae;
        private HealthTextView af;
        private HealthTextView ai;
        private HealthTextView b;
        private final LinearLayout c;
        private HealthTextView d;
        public HealthTextView e;
        private ConstraintLayout f;
        private RelativeLayout g;
        private LinearLayout h;
        private HealthTextView i;
        private HealthCardView j;
        private ImageView k;
        private HealthTextView l;
        private ImageView m;
        private LinearLayout n;
        private RelativeLayout o;
        private ImageView p;
        private HealthTextView q;
        private HealthTextView r;
        private ImageView s;
        private HealthButton t;
        private HealthTextView u;
        private RelativeLayout v;
        private RelativeLayout w;
        private LinearLayout x;
        private HealthTextView y;
        private HealthTextView z;

        MyHolder(View view, Context context, FunctionSetBeanReader functionSetBeanReader) {
            super(view);
            ((ViewGroup) view).setLayoutParams(Ze_(context));
            this.ae = (RatioRelativeLayout) view.findViewById(R.id.recyclerview_item);
            this.j = (HealthCardView) view.findViewById(R.id.function_set_card_view);
            this.f = (ConstraintLayout) view.findViewById(R.id.function_set_data_layout);
            this.s = (ImageView) view.findViewById(R.id.function_set_market_layout_large_bg);
            this.ai = (HealthTextView) view.findViewById(R.id.function_set_items_title);
            this.f2489a = (HealthTextView) view.findViewById(R.id.function_set_bottom_left);
            this.e = (HealthTextView) view.findViewById(R.id.function_set_items_data);
            this.c = (LinearLayout) view.findViewById(R.id.function_set_items_data_layout);
            this.af = (HealthTextView) view.findViewById(R.id.function_set_items_unit);
            this.m = (ImageView) view.findViewById(R.id.function_set_red_dot);
            this.n = (LinearLayout) view.findViewById(R.id.function_set_items_chart);
            this.x = (LinearLayout) view.findViewById(R.id.marketing_area);
            this.g = (RelativeLayout) view.findViewById(R.id.card_des);
            this.d = (HealthTextView) view.findViewById(R.id.function_set_bottom_end);
            this.b = (HealthTextView) view.findViewById(R.id.function_set_bottom_right);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Context context, FunctionSetBeanReader functionSetBeanReader) {
            ViewGroup.LayoutParams layoutParams = this.n.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                if (functionSetBeanReader == null) {
                    marginLayoutParams.topMargin = nrr.e(context, 2.0f);
                    marginLayoutParams.bottomMargin = nrr.e(context, 0.0f);
                } else {
                    marginLayoutParams.topMargin = nrr.e(context, functionSetBeanReader.getChartTopMargin());
                    marginLayoutParams.bottomMargin = nrr.e(context, functionSetBeanReader.getChartBottomMargin());
                }
                this.n.setLayoutParams(marginLayoutParams);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (nsn.t()) {
                nsn.b(this.l);
                nsn.b(this.i);
            }
        }

        private RelativeLayout.LayoutParams Ze_(Context context) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            int d = dpw.d(context);
            layoutParams.bottomMargin = dpw.c(context, dpw.c(context));
            int i = d / 2;
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i);
            return layoutParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(Context context) {
            if (LanguageUtil.p(context) || LanguageUtil.q(context) || LanguageUtil.w(context) || LanguageUtil.r(context) || LanguageUtil.au(context) || LanguageUtil.f(context) || LanguageUtil.bb(context) || LanguageUtil.ag(context) || LanguageUtil.v(context)) {
                this.f2489a.setAutoTextSize(1, 12.0f);
                this.ai.setTextSize(1, 12.0f);
                this.b.setTextSize(1, 12.0f);
                this.d.setTextSize(1, 12.0f);
                nsy.cMx_(this.l, 12.0f, 1);
            }
            if (LanguageUtil.al(context)) {
                return;
            }
            b(this.e);
        }

        private void b(HealthTextView healthTextView) {
            if (healthTextView != null) {
                ViewGroup.LayoutParams layoutParams = healthTextView.getLayoutParams();
                LogUtil.a(FunctionSetBeanReader.TAG, "setTextBottomMargin" + layoutParams.toString());
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    layoutParams2.bottomMargin = 0;
                    healthTextView.setLayoutParams(layoutParams2);
                }
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements HiSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<FunctionSetBeanReader> f2490a;
        private String d;

        public c(String str, FunctionSetBeanReader functionSetBeanReader) {
            this.d = str;
            this.f2490a = new WeakReference<>(functionSetBeanReader);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            FunctionSetBeanReader functionSetBeanReader;
            WeakReference<FunctionSetBeanReader> weakReference = this.f2490a;
            if (weakReference == null || (functionSetBeanReader = weakReference.get()) == null) {
                return;
            }
            functionSetBeanReader.updateSuccessList(list);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData != null) {
                LogUtil.a(this.d, "onChange, type = ", Integer.valueOf(i), ", newValue = ", hiHealthData.toString());
            } else {
                LogUtil.a(this.d, "onChange, type = ", Integer.valueOf(i));
            }
            LogUtil.a(this.d, "onChange, readCardData changeKey = ", str);
            WeakReference<FunctionSetBeanReader> weakReference = this.f2490a;
            if (weakReference == null) {
                return;
            }
            FunctionSetBeanReader functionSetBeanReader = weakReference.get();
            if (functionSetBeanReader == null) {
                LogUtil.a(this.d, "reader is null or reader does not subscribe, type: ", Integer.valueOf(i));
                return;
            }
            functionSetBeanReader.mChangeKey = str;
            functionSetBeanReader.refreshRedDotSyncNotify(true);
            if (functionSetBeanReader.isSubscribeType(i)) {
                if (!"sync download".equals(str) || i == HiSubscribeType.c || i == 10) {
                    functionSetBeanReader.processDataChange();
                } else {
                    LogUtil.a(this.d, "change key: ", str, " type: ", Integer.valueOf(i));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processDataChange() {
        if (!BaseApplication.isRunningForeground()) {
            LogUtil.h(this.mTag, "onChange isRunningBackground");
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.10
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetBeanReader.this.mIsDataChangeOnBg = true;
                }
            });
        } else {
            readCardData();
        }
    }

    public static class b implements HiUnSubscribeListener {
        private String b;
        private String d;

        public b(String str, String str2) {
            this.b = str;
            this.d = str2;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a(this.b, this.d, Boolean.valueOf(z));
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public FunctionSetBean getFunctionSetBean() {
        return this.mFunctionSetBean;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void setFunctionSetBean(FunctionSetBean functionSetBean) {
        this.mFunctionSetBean = functionSetBean;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public CardConfig getCardConfig() {
        return this.mCardConfig;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void setCardConfig(CardConfig cardConfig) {
        this.mCardConfig = cardConfig;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
        this.mBiHasData = Constants.NULL;
        this.mBiDataSource = "";
        this.mBiHasDataType = "";
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void setCardConstructor(CardConstructor cardConstructor) {
        this.mCardConstructor = cardConstructor;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public String getCardId() {
        CardConfig cardConfig = this.mCardConfig;
        return cardConfig != null ? cardConfig.getCardId() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCardViewClickListener() {
        LogUtil.a(this.mTag, "card is clicked");
        if (getFunctionSetBean() == null) {
            LogUtil.a(this.mTag, "getFunctionSetBean is null");
            return;
        }
        FunctionSetBean.ViewType q = getFunctionSetBean().q();
        if (q != FunctionSetBean.ViewType.DATA_VIEW && q != FunctionSetBean.ViewType.EMPTY_VIEW) {
            LogUtil.a(this.mTag, "viewType is invalid");
            return;
        }
        LogUtil.a(this.mTag, "card is clicked, viewType: " + q);
        ObserverManagerUtil.c("PRESS_HODLE_DELETE_CARD", this, "3");
        onCardViewClickListener(q);
    }

    public void onBottomLayoutClickListener(RelativeLayout relativeLayout) {
        if (relativeLayout != null) {
            relativeLayout.setClickable(false);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void setNotifyCardChangedCallback(ICardChangedCallback iCardChangedCallback) {
        this.mCardChangedCallback = iCardChangedCallback;
    }

    public void setBiDataSource(int i) {
        if (i == 0) {
            this.mBiDataSource = "";
        } else if (i == 32 || i == 30) {
            this.mBiDataSource = "telephone";
        } else if (i == 1) {
            this.mBiDataSource = "manual";
        } else if (i == 10001) {
            this.mBiDataSource = "thirdDevice";
        } else {
            this.mBiDataSource = "wearDevice";
        }
        LogUtil.a(this.mTag, "mBiDataSource : ", this.mBiDataSource);
    }

    public void setBiDataSource(String str) {
        this.mBiDataSource = str;
    }

    public void setBiHasData(long j) {
        this.mBiHasData = jdl.ac(j) ? "current_data" : "no_current_data";
    }

    public void setBiHasDataType(String str) {
        this.mBiHasDataType = str;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public iyb getShowOrClickBiInfo(int i) {
        iyb iybVar = new iyb();
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put(BI_ELEMENT, "data_card");
        hashMap.put("value", getCardConfig().getCardId());
        hashMap.put("moduleName", "home_card");
        iybVar.d(hashMap);
        ArrayList arrayList = new ArrayList(1);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("action", 0);
        hashMap2.put("moduleName", "home_card");
        hashMap2.put(BI_HAS_DATA, this.mBiHasData);
        hashMap2.put(BI_HAS_DATA_TYPE, this.mBiHasDataType);
        hashMap2.put("dataSource", this.mBiDataSource);
        hashMap2.put("position", Integer.valueOf(this.mPosition + 1));
        arrayList.add(hashMap2);
        iybVar.d(arrayList);
        return iybVar;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        if (isNeedRefreshOnResume()) {
            readCardData();
        }
        this.mIsDataChangeOnBg = false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        FunctionSetBeanHelper functionSetBeanHelper = this.mBeanRequestHelper;
        if (functionSetBeanHelper != null) {
            functionSetBeanHelper.e();
            this.mBeanRequestHelper = null;
        }
        ObserverManagerUtil.e(this.mCardRedDotObserver, "HOME_FRAGMENT_ACTIVITY_FOR_RESULT");
    }

    public boolean isNeedRefreshOnResume() {
        LogUtil.a(this.mTag, "isNeedRefreshOnResume, mIsDataChangeOnBg ：", Boolean.valueOf(this.mIsDataChangeOnBg));
        return this.mIsDataChangeOnBg;
    }

    protected boolean isDefaultLargeCard() {
        int hasCardDataFromSp = getHasCardDataFromSp();
        boolean z = (hasCardDataFromSp == -1 || hasCardDataFromSp == 0) ? false : true;
        if (z) {
            return z;
        }
        if (efb.k()) {
            if (getIsMessageLargeCard() == -1) {
                return isMessageDefaultLargeCard();
            }
            return getIsMessageLargeCard() == 1;
        }
        return isOverseaDefaultLargeCard();
    }

    private int getIsMessageLargeCard() {
        CardConstructor cardConstructor = this.mCardConstructor;
        if (cardConstructor == null) {
            return -1;
        }
        return cardConstructor.getIsMessageLargeCard();
    }

    protected int getHasCardDataFromSp() {
        LogUtil.a(TAG, "getHasCardDataFromSp");
        if (this.mCardConfig == null) {
            LogUtil.a(TAG, "mCardConfig is null");
            return -1;
        }
        String b2 = SharedPreferenceManager.b(this.mContext, Integer.toString(AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE), this.mCardConfig.getCardId());
        if (b2 == null || b2.length() == 0) {
            return -1;
        }
        boolean parseBoolean = Boolean.parseBoolean(b2);
        this.hasCardDataFromSp = parseBoolean;
        return parseBoolean ? 1 : 0;
    }

    public List<HiHealthData> getList(Object obj, int i) {
        ArrayList arrayList = new ArrayList();
        if (!(obj instanceof SparseArray)) {
            return arrayList;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            return arrayList;
        }
        Object obj2 = sparseArray.get(i);
        return !(obj2 instanceof List) ? arrayList : (List) obj2;
    }

    protected boolean getIsShowDot() {
        return this.mIsShowDot;
    }

    protected void setIsShowRedDot(boolean z) {
        this.mIsShowDot = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCardViewLongClickListener() {
        ObserverManagerUtil.c("PRESS_HODLE_DELETE_CARD", this, "4");
        if (!isUpdateContextSuccess()) {
            LogUtil.h(TAG, "onCardViewLongClickListener updateContext failed, return");
            return;
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mContext);
        View inflate = View.inflate(this.mContext, R.layout.home_card_button, null);
        this.popupTittle = (HealthTextView) inflate.findViewById(R.id.popup_tittle);
        this.hideButton = (HealthButton) inflate.findViewById(R.id.hide_button);
        this.adjustButton = (HealthButton) inflate.findViewById(R.id.adjust_sequence);
        this.cancelButton = (HealthButton) inflate.findViewById(R.id.cancel_button);
        this.hideButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ObserverManagerUtil.c("PRESS_HODLE_DELETE_CARD", FunctionSetBeanReader.this, "1");
                LogUtil.a(FunctionSetBeanReader.TAG, "hideButton ture");
                FunctionSetBeanReader.this.customViewDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.adjustButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ObserverManagerUtil.c("PRESS_HODLE_DELETE_CARD", FunctionSetBeanReader.this, "2");
                LogUtil.a(FunctionSetBeanReader.TAG, "adjustButton ture");
                FunctionSetBeanReader.this.customViewDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ObserverManagerUtil.c("PRESS_HODLE_DELETE_CARD", FunctionSetBeanReader.this, "5");
                LogUtil.a(FunctionSetBeanReader.TAG, "cancelButton ture");
                FunctionSetBeanReader.this.customViewDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czh_(inflate, 0, 0);
        builder.a(nsf.b(R$string.IDS_edit_card, getCardTitle()));
        this.popupTittle.setText(nsf.b(R$string.IDS_check_check_card, getCardTitle()));
        CustomViewDialog e2 = builder.e();
        this.customViewDialog = e2;
        e2.setCancelable(false);
        this.customViewDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasSetBiEvent(View view) {
        return view != null && this.mHasBiEventSet.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiEventListener(View view) {
        if (view == null || hasSetBiEvent(view)) {
            return;
        }
        nsy.cMa_(view, new a(this, view));
        nsy.cMb_(view, new a(this, view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkVisibilityAndSetBiEvent(View view) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        int height = view.getHeight();
        int width = view.getWidth();
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        if (isRectVisibleToUser(rect, height, width)) {
            ICardChangedCallback iCardChangedCallback = this.mCardChangedCallback;
            if (iCardChangedCallback != null) {
                iCardChangedCallback.notifyCardScrolled(this);
            }
            if (hasSetBiEvent(view)) {
                return;
            }
            if (this.mCardConfig == null) {
                LogUtil.b(TAG, "mCardConfig is null");
                return;
            }
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10100), IS_OPEN_SMART_RECOMMEND_BY_USER);
            if (b2 != null && b2.equals(Boolean.toString(true))) {
                LogUtil.a(TAG, "card is smart recommend");
                mIsSmartRecommend = 1;
            }
            String cardId = this.mCardConfig.getCardId();
            this.mHasBiEventSet.add(view);
            LogUtil.a(this.mTag, "card is show, cardId: " + cardId);
            ObserverManagerUtil.c("PRESS_HODLE_DELETE_CARD", this, "6");
            reportCustomBiEvent();
        }
    }

    private boolean isRectVisibleToUser(Rect rect, int i, int i2) {
        return rect != null && rect.left >= 0 && rect.left < i2 && rect.right >= Math.min(i2 / 2, 200) && rect.top >= 0 && rect.top < i && rect.bottom >= Math.min((i * 2) / 3, 400);
    }

    static class a implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
        private final WeakReference<FunctionSetBeanReader> b;
        private final WeakReference<View> d;

        public a(FunctionSetBeanReader functionSetBeanReader, View view) {
            this.b = new WeakReference<>(functionSetBeanReader);
            this.d = new WeakReference<>(view);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FunctionSetBeanReader functionSetBeanReader = this.b.get();
            View view = this.d.get();
            if (functionSetBeanReader == null || view == null) {
                return;
            }
            if (!functionSetBeanReader.hasSetBiEvent(view)) {
                functionSetBeanReader.checkVisibilityAndSetBiEvent(view);
            } else {
                nsy.cMf_(view, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            FunctionSetBeanReader functionSetBeanReader = this.b.get();
            View view = this.d.get();
            if (functionSetBeanReader == null || view == null) {
                return;
            }
            if (!functionSetBeanReader.hasSetBiEvent(view)) {
                functionSetBeanReader.checkVisibilityAndSetBiEvent(view);
            } else {
                nsy.cMg_(view, this);
            }
        }
    }
}
