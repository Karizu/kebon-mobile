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

public class GaleriLahanActivity_ViewBinding implements Unbinder {
  private GaleriLahanActivity target;

  private View view7f0a008e;

  private View view7f0a006b;

  @UiThread
  public GaleriLahanActivity_ViewBinding(GaleriLahanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GaleriLahanActivity_ViewBinding(final GaleriLahanActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_pilih, "method 'onClickPilih'");
    view7f0a008e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPilih();
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


    view7f0a008e.setOnClickListener(null);
    view7f0a008e = null;
    view7f0a006b.setOnClickListener(null);
    view7f0a006b = null;
  }
}
