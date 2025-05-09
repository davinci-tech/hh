package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import com.huawei.ui.main.stories.userprofile.card.PersonalGridCardViewHolder;

/* loaded from: classes7.dex */
public class rzv extends AbstractBaseCardData {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f16979a;
    protected PersonalCenterUiApi b;
    protected boolean c;
    protected Context d;
    protected rzs e;
    protected PersonalGridCardViewHolder h;

    protected void c() {
    }

    public rzv(Context context, PersonalCenterUiApi personalCenterUiApi) {
        this.d = context;
        this.b = personalCenterUiApi;
        this.f16979a = LoginInit.getInstance(context).isBrowseMode();
        c();
    }

    public void e(boolean z) {
        LogUtil.a("PersonalBaseCardData", "setIsVerticalLayout isVerticalLayout = ", Boolean.valueOf(z));
        this.c = z;
        PersonalGridCardViewHolder personalGridCardViewHolder = this.h;
        if (personalGridCardViewHolder != null) {
            personalGridCardViewHolder.b(z);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        PersonalGridCardViewHolder personalGridCardViewHolder = new PersonalGridCardViewHolder(layoutInflater.inflate(R.layout.personal_grid_item_view, viewGroup, false), this.d, this.e);
        this.h = personalGridCardViewHolder;
        personalGridCardViewHolder.b(this.c);
        return this.h;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        e();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        super.onResume();
        e();
    }

    protected void e() {
        LogUtil.a("PersonalBaseCardData", "initData  mIsBrowseMode = ", Boolean.valueOf(this.f16979a));
        this.f16979a = LoginInit.getInstance(this.d).isBrowseMode();
    }

    protected void d(boolean z, int i) {
        if (z) {
            this.b.setBottomRedDotVisibility(i);
        } else {
            this.b.cancelBottomRedDotVisible(i);
        }
        this.e.b(z);
        PersonalGridCardViewHolder personalGridCardViewHolder = this.h;
        if (personalGridCardViewHolder != null) {
            personalGridCardViewHolder.c(z);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "PersonalBaseCardData";
    }
}
