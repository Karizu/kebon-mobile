// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.home.pembayaran;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PembayaranActivity_ViewBinding implements Unbinder {
  private PembayaranActivity target;

  private View view7f0a00a2;

  private View view7f0a00a1;

  private View view7f0a006e;

  private View view7f0a006b;

  @UiThread
  public PembayaranActivity_ViewBinding(PembayaranActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PembayaranActivity_ViewBinding(final PembayaranActivity target, View source) {
    this.target = target;

    View view;
    target.tv_title_bar = Utils.findRequiredViewAsType(source, R.id.tv_title_bar, "field 'tv_title_bar'", TextView.class);
    view = Utils.findRequiredView(source, R.id.checkBoxTransferBank, "field 'checkBoxTransferBank' and method 'onClickTransfer'");
    target.checkBoxTransferBank = Utils.castView(view, R.id.checkBoxTransferBank, "field 'checkBoxTransferBank'", CheckBox.class);
    view7f0a00a2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickTransfer();
      }
    });
    view = Utils.findRequiredView(source, R.id.checkBoxQris, "field 'checkBoxQris' and method 'onClickQris'");
    target.checkBoxQris = Utils.castView(view, R.id.checkBoxQris, "field 'checkBoxQris'", CheckBox.class);
    view7f0a00a1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickQris();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_bayar, "method 'onClickBtnBayar'");
    view7f0a006e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnBayar();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'onClickBack'");
    view7f0a006b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBack();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PembayaranActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title_bar = null;
    target.checkBoxTransferBank = null;
    target.checkBoxQris = null;

    view7f0a00a2.setOnClickListener(null);
    view7f0a00a2 = null;
    view7f0a00a1.setOnClickListener(null);
    view7f0a00a1 = null;
    view7f0a006e.setOnClickListener(null);
    view7f0a006e = null;
    view7f0a006b.setOnClickListener(null);
    view7f0a006b = null;
  }
}
