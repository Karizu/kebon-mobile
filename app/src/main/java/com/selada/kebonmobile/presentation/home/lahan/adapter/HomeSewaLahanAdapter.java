package com.selada.kebonmobile.presentation.home.lahan.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.model.response.LeaseableObject;
import com.selada.kebonmobile.model.response.SiteLeasableResponse;
import com.selada.kebonmobile.presentation.home.lahan.GaleriLahanActivity;
import com.selada.kebonmobile.presentation.home.lahan.InformasiKeuntunganActivity;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticCheckButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeSewaLahanAdapter extends RecyclerView.Adapter<HomeSewaLahanAdapter.ViewHolder> {
    private List<SiteLeasableResponse> transactionModels;
    private Context context;
    private Activity activity;
    private SewaLahanModel sewaLahanModel;

    public HomeSewaLahanAdapter(List<SiteLeasableResponse> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_sewa_lahan, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SiteLeasableResponse leasableResponse = transactionModels.get(position);
        sewaLahanModel = new SewaLahanModel();

        //set default value
        sewaLahanModel.setId("0");
        sewaLahanModel.setNamaLahan(leasableResponse.getName());
        sewaLahanModel.setJumlahKavling("0");
        sewaLahanModel.setLamaSewa("0");
        sewaLahanModel.setHarga("0");
        sewaLahanModel.setTotalHarga("0");

        int id = leasableResponse.getId();
        int stokKavling;
        int maksSewa;
        int price;

        holder.layout_spinner.setOnClickListener(view -> {
            holder.spinner_type_code.performClick();
        });

        if (leasableResponse.getLeaseableObjects().size()>1){
            holder.btn_add_kavling.setOnClickListener(view -> {
                Toast.makeText(context, "Silahkan pilih jenis sewa terlebih dahulu", Toast.LENGTH_SHORT).show();
            });
            holder.btn_min_kavling.setOnClickListener(view -> {
                Toast.makeText(context, "Silahkan pilih jenis sewa terlebih dahulu", Toast.LENGTH_SHORT).show();
            });
            holder.btn_add_sewa.setOnClickListener(view -> {
                Toast.makeText(context, "Silahkan pilih jenis sewa terlebih dahulu", Toast.LENGTH_SHORT).show();
            });
            holder.btn_min_sewa.setOnClickListener(view -> {
                Toast.makeText(context, "Silahkan pilih jenis sewa terlebih dahulu", Toast.LENGTH_SHORT).show();
            });

            setSpinner(leasableResponse, holder, 2);

        } else {
            holder.layout_spinner.setVisibility(View.INVISIBLE);

            if (leasableResponse.getLeaseableObjects().size() == 0){
                Toast.makeText(context, "Saat ini kavling tidak tersedia", Toast.LENGTH_SHORT).show();

                holder.tv_jumlah_kavling.setText("-");
                holder.tv_jumlah_lubang.setText("-");
                holder.tv_maksimal_sewa.setText("-");
                holder.tv_harga_sewa.setText("-");

            } else {
                stokKavling = leasableResponse.getLeaseableObjects().get(0).getProductionCapacity();
                maksSewa = leasableResponse.getLeaseableObjects().get(0).getMaxDuration();
                price = leasableResponse.getLeaseableObjects().get(0).getPricePerDuration();

                holder.btn_add_kavling.setOnClickListener(view -> {
                    int count = Integer.parseInt(holder.tv_quantity_kavling.getText().toString());
                    count += 1;
                    if (count <= stokKavling) {
                        holder.tv_quantity_kavling.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(id));
                        sewaLahanModel.setJumlahKavling(String.valueOf(count));
                    }
                });

                holder.btn_min_kavling.setOnClickListener(view -> {
                    if (Integer.parseInt(holder.tv_quantity_kavling.getText().toString()) > 1) {
                        int count = Integer.parseInt(holder.tv_quantity_kavling.getText().toString());
                        count -= 1;
                        holder.tv_quantity_kavling.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(id));
                        sewaLahanModel.setJumlahKavling(String.valueOf(count));
                    } else {
                        holder.tv_quantity_kavling.setText("0");
                        sewaLahanModel.setId(String.valueOf(id));
                        sewaLahanModel.setJumlahKavling("0");
                    }
                });

                holder.btn_add_sewa.setOnClickListener(view -> {
                    int count = Integer.parseInt(holder.tv_quantity_sewa.getText().toString());
                    count += 1;
                    if (count <= maksSewa) {
                        int totalPrice = price * count;
                        holder.tv_quantity_sewa.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(id));
                        sewaLahanModel.setLamaSewa(String.valueOf(count));
                        sewaLahanModel.setHarga(String.valueOf(price));
                        sewaLahanModel.setTotalHarga(String.valueOf(totalPrice));
                    }
                });

                holder.btn_min_sewa.setOnClickListener(view -> {
                    if (Integer.parseInt(holder.tv_quantity_sewa.getText().toString()) > 1) {
                        int count = Integer.parseInt(holder.tv_quantity_sewa.getText().toString());
                        count -= 1;
                        int totalPrice = price * count;
                        holder.tv_quantity_sewa.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(id));
                        sewaLahanModel.setLamaSewa(String.valueOf(count));
                        sewaLahanModel.setHarga(String.valueOf(price));
                        sewaLahanModel.setTotalHarga(String.valueOf(totalPrice));
                    } else {
                        holder.tv_quantity_sewa.setText("0");
                        sewaLahanModel.setId(String.valueOf(id));
                        sewaLahanModel.setLamaSewa("0");
                        sewaLahanModel.setHarga("0");
                        sewaLahanModel.setTotalHarga("0");
                    }
                });

                setSpinner(leasableResponse, holder, 1);
            }
        }

        holder.tv_farm_name.setText(leasableResponse.getName());
        holder.tv_lokasi_lahan.setText(leasableResponse.getAddressStreet() + "\n" +
                leasableResponse.getAddressDistrict() + ", " +
                leasableResponse.getAddressCity() + ", " +
                leasableResponse.getAddressProvince());


        holder.btn_informasi.setOnClickListener(view -> {
            Intent intent = new Intent(activity, InformasiKeuntunganActivity.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        holder.img_lahan.setOnClickListener(view -> {
            Intent intent = new Intent(activity, GaleriLahanActivity.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels != null ? transactionModels.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_jumlah_kavling, tv_jumlah_lubang, tv_lokasi_lahan, tv_harga_sewa, tv_maksimal_sewa, tv_quantity_kavling, tv_quantity_sewa, tv_farm_name;
        Spinner spinner_type_code;
        ElasticImageView img_lahan, btn_add_kavling, btn_min_kavling, btn_add_sewa, btn_min_sewa;
        ElasticCheckButton btn_informasi;
        ElasticLayout layout_spinner;

        ViewHolder(View v) {
            super(v);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_jumlah_kavling = v.findViewById(R.id.tv_jumlah_kavling);
            tv_jumlah_lubang = v.findViewById(R.id.tv_jumlah_lubang);
            tv_lokasi_lahan = v.findViewById(R.id.tv_lokasi_lahan);
            img_lahan = v.findViewById(R.id.img_lahan);
            btn_informasi = v.findViewById(R.id.btn_informasi);
            tv_harga_sewa = v.findViewById(R.id.tv_harga_sewa);
            tv_maksimal_sewa = v.findViewById(R.id.tv_maksimal_sewa);
            tv_quantity_kavling = v.findViewById(R.id.tv_quantity_kavling);
            tv_quantity_sewa = v.findViewById(R.id.tv_quantity_sewa);
            btn_add_kavling = v.findViewById(R.id.btn_add_kavling);
            btn_min_kavling = v.findViewById(R.id.btn_min_kavling);
            btn_add_sewa = v.findViewById(R.id.btn_add_sewa);
            btn_min_sewa = v.findViewById(R.id.btn_min_sewa);
            spinner_type_code = v.findViewById(R.id.spinner_type_code);
            layout_spinner = v.findViewById(R.id.layout_spinner);
        }
    }

    private void setSpinner(SiteLeasableResponse siteLeasableResponse, ViewHolder holder, int leasableObjectNum){
        List<String> list = new ArrayList<>();
        if (leasableObjectNum > 1){
            list.add("Pilih jenis sewa");
            holder.tv_jumlah_kavling.setText("-");
            holder.tv_jumlah_lubang.setText("-");
            holder.tv_maksimal_sewa.setText("-");
            holder.tv_harga_sewa.setText("-");
        }

        for (LeaseableObject object: siteLeasableResponse.getLeaseableObjects()){
            list.add(object.getObjectTypeName());
        }

        ArrayAdapter adapter = new ArrayAdapter(activity, R.layout.simple_spinner_item_categories, list){
            @Override
            public boolean isEnabled(int position) {
                if (leasableObjectNum > 1){
                    if (position == 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        };
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_categories);
        holder.spinner_type_code.setAdapter(adapter);

        holder.spinner_type_code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LeaseableObject object = siteLeasableResponse.getLeaseableObjects().get(leasableObjectNum > 1 ? i-1 : i);

                //set default quantity
                holder.tv_quantity_kavling.setText("0");
                holder.tv_quantity_sewa.setText("0");

                int stokKavling = Integer.parseInt(object.getAvailable());
                int maksSewa = object.getMaxDuration();
                int price = object.getPricePerDuration();
                String objectType = object.getObjectTypeName().toLowerCase().contains("kavling")?"Kavling":"Baris";
                holder.tv_jumlah_kavling.setText(object.getAvailable() + " " + objectType);
                holder.tv_jumlah_lubang.setText("" + object.getProductionCapacity());
                holder.tv_maksimal_sewa.setText("" + object.getMaxDuration());
                holder.tv_harga_sewa.setText("Rp. " + MethodUtil.toCurrencyNumber(object.getPricePerDuration()));
                holder.btn_add_kavling.setOnClickListener(view2 -> {
                    int count = Integer.parseInt(holder.tv_quantity_kavling.getText().toString());
                    count += 1;
                    if (count <= stokKavling) {
                        holder.tv_quantity_kavling.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(siteLeasableResponse.getId()));
                        sewaLahanModel.setJumlahKavling(String.valueOf(count));
                    }
                });
                holder.btn_min_kavling.setOnClickListener(view2 -> {
                    if (Integer.parseInt(holder.tv_quantity_kavling.getText().toString()) > 1) {
                        int count = Integer.parseInt(holder.tv_quantity_kavling.getText().toString());
                        count -= 1;
                        holder.tv_quantity_kavling.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(siteLeasableResponse.getId()));
                        sewaLahanModel.setJumlahKavling(String.valueOf(count));
                    } else {
                        holder.tv_quantity_kavling.setText("0");
                        sewaLahanModel.setId(String.valueOf(siteLeasableResponse.getId()));
                        sewaLahanModel.setJumlahKavling("0");
                    }
                });
                holder.btn_add_sewa.setOnClickListener(view2 -> {
                    int count = Integer.parseInt(holder.tv_quantity_sewa.getText().toString());
                    count += 1;
                    if (count <= maksSewa) {
                        int totalPrice = price * count;
                        holder.tv_quantity_sewa.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(siteLeasableResponse.getId()));
                        sewaLahanModel.setLamaSewa(String.valueOf(count));
                        sewaLahanModel.setHarga(String.valueOf(price));
                        sewaLahanModel.setTotalHarga(String.valueOf(totalPrice));
                    }
                });
                holder.btn_min_sewa.setOnClickListener(view2 -> {
                    if (Integer.parseInt(holder.tv_quantity_sewa.getText().toString()) > 1) {
                        int count = Integer.parseInt(holder.tv_quantity_sewa.getText().toString());
                        count -= 1;
                        int totalPrice = price * count;
                        holder.tv_quantity_sewa.setText(""+count);
                        sewaLahanModel.setId(String.valueOf(siteLeasableResponse.getId()));
                        sewaLahanModel.setLamaSewa(String.valueOf(count));
                        sewaLahanModel.setHarga(String.valueOf(price));
                        sewaLahanModel.setTotalHarga(String.valueOf(totalPrice));
                    } else {
                        holder.tv_quantity_sewa.setText("0");
                        sewaLahanModel.setId(String.valueOf(siteLeasableResponse.getId()));
                        sewaLahanModel.setLamaSewa("0");
                        sewaLahanModel.setHarga("0");
                        sewaLahanModel.setTotalHarga("0");
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public boolean isSewaLahanAvailable(){
        PreferenceManager.setSewaLahan(sewaLahanModel);
        if (PreferenceManager.getSewaLahan()!=null){
            Log.d("SewaLahan", new Gson().toJson(PreferenceManager.getSewaLahan()));
            return true;
        }
        return false;
    }
}
