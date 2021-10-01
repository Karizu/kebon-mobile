// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.home.lahan;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InformasiKeuntunganActivity_ViewBinding implements Unbinder {
  private InformasiKeuntunganActivity target;

  private View view7f0a0082;

  private View view7f0a006b;

  @UiThread
  public InformasiKeuntunganActivity_ViewBinding(InformasiKeuntunganActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InformasiKeuntunganActivity_ViewBinding(final InformasiKeuntunganActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_kembali, "method 'onClickKembali'");
    view7f0a0082 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickKembali();
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a0082.setOnClickListener(null);
    view7f0a0082 = null;
    view7f0a006b.setOnClickListener(null);
    view7f0a006b = null;
  }
}
