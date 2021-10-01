// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.notification;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationFragment_ViewBinding implements Unbinder {
  private NotificationFragment target;

  private View view7f0a0095;

  @UiThread
  public NotificationFragment_ViewBinding(final NotificationFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_tanam, "method 'onClickTanam'");
    view7f0a0095 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickTanam();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a0095.setOnClickListener(null);
    view7f0a0095 = null;
  }
}
