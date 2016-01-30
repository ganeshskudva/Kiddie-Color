package com.gkudva.kidscolor.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.gkudva.kidscolor.R;

public class SettingsMenu {

    private CheckBox advModeCheckBox;
    private Context  settingsContext;
    public AlertDialog dialog;

    public SettingsMenu(Context context){

        settingsContext = context;
        AlertDialog.Builder builder = new AlertDialog.Builder( context);
        builder.setTitle("Settings");
        LayoutInflater inflater = (LayoutInflater) settingsContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View settingsView = inflater.inflate( R.layout.settings, null );
        builder.setView(settingsView);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialog, int whichButton ) {
                        ColorMap.initializeColorMap(advModeCheckBox.isChecked());
                    }
                });

        builder.setNegativeButton("Cancel", null);
        dialog = builder.create();

        advModeCheckBox = (CheckBox) settingsView.findViewById(R.id.debugLogCheckBox);

    }

}