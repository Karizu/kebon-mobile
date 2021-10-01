// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.home.lahan;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SewaLahanActivity_ViewBinding implements Unbinder {
  private SewaLahanActivity target;

  private View view7f0a008b;

  private View view7f0a008c;

  private View view7f0a006b;

  private View view7f0a0068;

  private View view7f0a0069;

  private View view7f0a0080;

  private View view7f0a008e;

  private View view7f0a007f;

  private View view7f0a0136;

  @UiThread
  public SewaLahanActivity_ViewBinding(SewaLahanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SewaLahanActivity_ViewBinding(final SewaLahanActivity target, View source) {
    this.target = target;

    View view;
    target.tv_quantity_kavling = Utils.findRequiredViewAsType(source, R.id.tv_quantity_kavling, "field 'tv_quantity_kavling'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_min_kavling, "field 'btn_min_kavling' and method 'onClickBtnMinKavling'");
    target.btn_min_kavling = Utils.castView(view, R.id.btn_min_kavling, "field 'btn_min_kavling'", ElasticImageView.class);
    view7f0a008b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnMinKavling();
      }
    });
    target.tv_quantity_sewa = Utils.findRequiredViewAsType(source, R.id.tv_quantity_sewa, "field 'tv_quantity_sewa'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_min_sewa, "field 'btn_min_sewa' and method 'onClickBtnMinSewa'");
    target.btn_min_sewa = Utils.castView(view, R.id.btn_min_sewa, "field 'btn_min_sewa'", ElasticImageView.class);
    view7f0a008c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnMinSewa();
      }
    });
    target.tv_title_bar = Utils.findRequiredViewAsType(source, R.id.tv_title_bar, "field 'tv_title_bar'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'onClickBtnBack'");
    view7f0a006b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnBack();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_add_kavling, "method 'onClickBtnAddKavling'");
    view7f0a0068 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnAddKavling();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_add_sewa, "method 'onClickBtnAddSewa'");
    view7f0a0069 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnAddSewa();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_istilah, "method 'onClickBtnIstilah'");
    view7f0a0080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnIstilah();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_pilih, "method 'onClickPilih'");
    view7f0a008e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPilih();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_informasi, "method 'onClickBtnInformasiKeuntungan'");
    view7f0a007f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnInformasiKeuntungan();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_lahan, "method 'onClickImgLahan'");
    view7f0a0136 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickImgLahan();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SewaLahanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_quantity_kavling = null;
    target.btn_min_kavling = null;
    target.tv_quantity_sewa = null;
    target.btn_min_sewa = null;
    target.tv_title_bar = null;

    view7f0a008b.setOnClickListener(null);
    view7f0a008b = null;
    view7f0a008c.setOnClickListener(null);
    view7f0a008c = null;
    view7f0a006b.setOnClickListener(null);
    view7f0a006b = null;
    view7f0a0068.setOnClickListener(null);
    view7f0a0068 = null;
    view7f0a0069.setOnClickListener(null);
    view7f0a0069 = null;
    view7f0a0080.setOnClickListener(null);
    view7f0a0080 = null;
    view7f0a008e.setOnClickListener(null);
    view7f0a008e = null;
    view7f0a007f.setOnClickListener(null);
    view7f0a007f = null;
    view7f0a0136.setOnClickListener(null);
    view7f0a0136 = null;
  }
}
