// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.home.pembayaran;

import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class KonfirmasiPembayaranActivity_ViewBinding implements Unbinder {
  private KonfirmasiPembayaranActivity target;

  private View view7f0a006d;

  private View view7f0a006a;

  @UiThread
  public KonfirmasiPembayaranActivity_ViewBinding(KonfirmasiPembayaranActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public KonfirmasiPembayaranActivity_ViewBinding(final KonfirmasiPembayaranActivity target,
      View source) {
    this.target = target;

    View view;
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.btn_beranda, "method 'onClickBeranda'");
    view7f0a006d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBeranda();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_batalkan, "method 'onClickBatalkan'");
    view7f0a006a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBatalkan();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    KonfirmasiPembayaranActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;

    view7f0a006d.setOnClickListener(null);
    view7f0a006d = null;
    view7f0a006a.setOnClickListener(null);
    view7f0a006a = null;
  }
}
