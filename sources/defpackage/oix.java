package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.SystemClock;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes9.dex */
public class oix extends FunctionSetBeanReader {
    private AtomicLong c;
    private Resources d;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131428485_res_0x7f0b0485 : R.drawable.health_life_default_img_small;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return null;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isOverseaDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
    }

    public oix(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetHealthLifeDefaultCardReader", cardConfig);
        LogUtil.a("FunctionSetHealthLifeDefaultCardReader", "FunctionSetHealthLifeDefaultCardReader");
        d();
        setHasCardData(false);
        setFunctionSetBean(buildEmptyCardBean());
    }

    private void d() {
        this.d = this.mContext.getResources();
        this.c = new AtomicLong();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        ReleaseLogUtil.e("TimeEat_FunctionSetHealthLifeDefaultCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("FunctionSetHealthLifeDefaultCardReader", "onDestroy");
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetHealthLifeDefaultCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetHealthLifeDefaultCardReader", "createCardView");
        a(buildEmptyCardBean());
        return null;
    }

    private void a(FunctionSetBean functionSetBean) {
        if (functionSetBean == null) {
            LogUtil.h("FunctionSetHealthLifeDefaultCardReader", "notifyDataView mFunctionSetBean is null");
        } else {
            notifyItemChanged(functionSetBean);
        }
    }

    private String e() {
        return nsf.h(R$string.IDS_health_clover_title);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getCardTitle() {
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean == null) {
            LogUtil.h("FunctionSetHealthLifeDefaultCardReader", "getCardTitle functionSetBean is null");
            return e();
        }
        return functionSetBean.n();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        LogUtil.a("FunctionSetHealthLifeDefaultCardReader", "setEmptyFunctionSetBean");
        return new FunctionSetBean.a(e()).e(this.d.getString(com.huawei.health.servicesui.R$string.IDS_health_model_home_card_description)).a(FunctionSetType.HEALTH_MODEL_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(this.mContext).d(false).c();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        LogUtil.a("FunctionSetHealthLifeDefaultCardReader", "onBindViewHolder onClick elapsedRealtime ", Long.valueOf(elapsedRealtime));
        if (Math.abs(elapsedRealtime - this.c.get()) < 800) {
            LogUtil.h("FunctionSetHealthLifeDefaultCardReader", "onBindViewHolder onClick fast click");
        } else {
            this.c.set(elapsedRealtime);
            dsl.ZK_(this.mContext, Uri.parse("").buildUpon().appendQueryParameter("from", "HealthModel_FunctionSetHealthModelCardReader").build());
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetHealthLifeDefaultCardReader";
    }
}
