// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.status;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StatusFragment_ViewBinding implements Unbinder {
  private StatusFragment target;

  private View view7f0a0083;

  @UiThread
  public StatusFragment_ViewBinding(final StatusFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_pilih, "method 'onClickPilih'");
    view7f0a0083 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPilih();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a0083.setOnClickListener(null);
    view7f0a0083 = null;
  }
}
