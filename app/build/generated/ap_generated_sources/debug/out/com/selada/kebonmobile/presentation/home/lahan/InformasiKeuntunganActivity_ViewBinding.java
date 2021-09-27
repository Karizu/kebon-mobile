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

  private View view7f0a007c;

  private View view7f0a0069;

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
    view7f0a007c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickKembali();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'onClickBack'");
    view7f0a0069 = view;
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


    view7f0a007c.setOnClickListener(null);
    view7f0a007c = null;
    view7f0a0069.setOnClickListener(null);
    view7f0a0069 = null;
  }
}
