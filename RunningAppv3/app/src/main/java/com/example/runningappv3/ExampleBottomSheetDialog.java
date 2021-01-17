package com.example.runningappv3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.Nullable;

public class ExampleBottomSheetDialog extends BottomSheetDialogFragment {
    public static  BottomSheetListener mListener;

    private ExampleBottomSheetDialog dialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dialog=this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);

        SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0);
        int id = pref.getInt("key_name", -1);
        if(id!=-1)
        {
            RadioButton button=v.findViewById(id);
            button.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton button=v.findViewById(checkedId);
                mListener.onButtonClicked(button.getText().toString());

                //shared preferences to save the state of the radio buttons
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt("key_name",checkedId);
                edit.apply();
                dialog.dismiss();

            }
        });

        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

}