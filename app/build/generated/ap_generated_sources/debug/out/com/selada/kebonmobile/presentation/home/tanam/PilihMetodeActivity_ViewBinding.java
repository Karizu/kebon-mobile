// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.home.tanam;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PilihMetodeActivity_ViewBinding implements Unbinder {
  private PilihMetodeActivity target;

  private View view7f0a0068;

  private View view7f0a007d;

  private View view7f0a0083;

  private View view7f0a0069;

  private View view7f0a007a;

  private View view7f0a0077;

  private View view7f0a0078;

  @UiThread
  public PilihMetodeActivity_ViewBinding(PilihMetodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PilihMetodeActivity_ViewBinding(final PilihMetodeActivity target, View source) {
    this.target = target;

    View view;
    target.btn_chat = Utils.findRequiredViewAsType(source, R.id.btn_chat, "field 'btn_chat'", ElasticImageView.class);
    target.tv_title_bar = Utils.findRequiredViewAsType(source, R.id.tv_title_bar, "field 'tv_title_bar'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_auto_pilot, "field 'btn_auto_pilot' and method 'onClickAutoPilot'");
    target.btn_auto_pilot = Utils.castView(view, R.id.btn_auto_pilot, "field 'btn_auto_pilot'", ElasticLayout.class);
    view7f0a0068 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickAutoPilot();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_konvensional, "field 'btn_konvensional' and method 'onClickKonvensional'");
    target.btn_konvensional = Utils.castView(view, R.id.btn_konvensional, "field 'btn_konvensional'", ElasticLayout.class);
    view7f0a007d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickKonvensional();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_pilih, "field 'btn_pilih' and method 'onClickPilih'");
    target.btn_pilih = Utils.castView(view, R.id.btn_pilih, "field 'btn_pilih'", ElasticButton.class);
    view7f0a0083 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPilih();
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
    view = Utils.findRequiredView(source, R.id.btn_istilah, "method 'onClickIstilah'");
    view7f0a007a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickIstilah();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_info_selengkapnya, "method 'onClickInfoAuto'");
    view7f0a0077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickInfoAuto();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_info_selengkapnya_konvensional, "method 'onClickInfoKonvensional'");
    view7f0a0078 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickInfoKonvensional();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PilihMetodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_chat = null;
    target.tv_title_bar = null;
    target.btn_auto_pilot = null;
    target.btn_konvensional = null;
    target.btn_pilih = null;

    view7f0a0068.setOnClickListener(null);
    view7f0a0068 = null;
    view7f0a007d.setOnClickListener(null);
    view7f0a007d = null;
    view7f0a0083.setOnClickListener(null);
    view7f0a0083 = null;
    view7f0a0069.setOnClickListener(null);
    view7f0a0069 = null;
    view7f0a007a.setOnClickListener(null);
    view7f0a007a = null;
    view7f0a0077.setOnClickListener(null);
    view7f0a0077 = null;
    view7f0a0078.setOnClickListener(null);
    view7f0a0078 = null;
  }
}
