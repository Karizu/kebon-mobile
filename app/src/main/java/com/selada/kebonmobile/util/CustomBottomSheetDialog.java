package com.selada.kebonmobile.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.jadwal.BottomSheetEventAdapter;

import java.util.List;


public class CustomBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public CustomBottomSheetDialog(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_event, container, false);
        TextView tv_title = v.findViewById(R.id.tv_title);
        RecyclerView rv_event = v.findViewById(R.id.rv_event);

        BottomSheetEventAdapter adapter = new BottomSheetEventAdapter(transactionModels, context, activity);
        rv_event.setAdapter(adapter);

        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}