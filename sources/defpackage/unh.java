package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.channels.ReceiveOrClosed;
import kotlinx.coroutines.channels.Send;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0014¢\u0006\u0002\u0010\u0015J/\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0014\u0010\u000b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/channels/LinkedListChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "offerInternal", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotentList", "list", "Lkotlinx/coroutines/internal/InlineList;", "Lkotlinx/coroutines/channels/Send;", "closed", "Lkotlinx/coroutines/channels/Closed;", "onCancelIdempotentList-w-w6eGU", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class unh<E> extends AbstractChannel<E> {
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return false;
    }

    public unh(Function1<? super E, ueu> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerInternal(E element) {
        ReceiveOrClosed<?> sendBuffered;
        do {
            Object offerInternal = super.offerInternal(element);
            if (offerInternal == C0350umy.f17474a) {
                return C0350umy.f17474a;
            }
            if (offerInternal == C0350umy.b) {
                sendBuffered = sendBuffered(element);
                if (sendBuffered == null) {
                    return C0350umy.f17474a;
                }
            } else {
                if (offerInternal instanceof ung) {
                    return offerInternal;
                }
                throw new IllegalStateException(uhy.b("Invalid offerInternal result ", offerInternal).toString());
            }
        } while (!(sendBuffered instanceof ung));
        return sendBuffered;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerSelectInternal(E element, SelectInstance<?> select) {
        Object performAtomicTrySelect;
        while (true) {
            if (getHasReceiveOrClosed()) {
                performAtomicTrySelect = super.offerSelectInternal(element, select);
            } else {
                performAtomicTrySelect = select.performAtomicTrySelect(describeSendBuffered(element));
                if (performAtomicTrySelect == null) {
                    performAtomicTrySelect = C0350umy.f17474a;
                }
            }
            if (performAtomicTrySelect == ALREADY_SELECTED.e()) {
                return ALREADY_SELECTED.e();
            }
            if (performAtomicTrySelect == C0350umy.f17474a) {
                return C0350umy.f17474a;
            }
            if (performAtomicTrySelect != C0350umy.b && performAtomicTrySelect != NO_DECISION.c) {
                if (performAtomicTrySelect instanceof ung) {
                    return performAtomicTrySelect;
                }
                throw new IllegalStateException(uhy.b("Invalid result ", performAtomicTrySelect).toString());
            }
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    /* renamed from: onCancelIdempotentList-w-w6eGU */
    public void mo976onCancelIdempotentListww6eGU(Object obj, ung<?> ungVar) {
        uqc uqcVar = null;
        if (obj != null) {
            if (!(obj instanceof ArrayList)) {
                Send send = (Send) obj;
                if (send instanceof AbstractSendChannel.a) {
                    Function1<E, ueu> function1 = this.onUndeliveredElement;
                    if (function1 != null) {
                        uqcVar = bindCancellationFun.a(function1, ((AbstractSendChannel.a) send).e, (uqc) null);
                    }
                } else {
                    send.resumeSendClosed(ungVar);
                }
            } else if (obj != null) {
                ArrayList arrayList = (ArrayList) obj;
                int size = arrayList.size() - 1;
                if (size >= 0) {
                    uqc uqcVar2 = null;
                    while (true) {
                        int i = size - 1;
                        Send send2 = (Send) arrayList.get(size);
                        if (send2 instanceof AbstractSendChannel.a) {
                            Function1<E, ueu> function12 = this.onUndeliveredElement;
                            uqcVar2 = function12 == null ? null : bindCancellationFun.a(function12, ((AbstractSendChannel.a) send2).e, uqcVar2);
                        } else {
                            send2.resumeSendClosed(ungVar);
                        }
                        if (i < 0) {
                            break;
                        } else {
                            size = i;
                        }
                    }
                    uqcVar = uqcVar2;
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            }
        }
        if (uqcVar != null) {
            throw uqcVar;
        }
    }
}
