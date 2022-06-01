package com.example.nanodg.tambalban.ir.apend.slider.ui.indicators;

import android.content.Context;
import android.os.Build;
import androidx.core.content.res.ResourcesCompat;

import com.example.nanodg.tambalban.R;


/**
 * @author S.Shahini
 * @since 11/27/16
 */

public class RoundSquareIndicator extends IndicatorShape {

    public RoundSquareIndicator(Context context, int indicatorSize, boolean mustAnimateChanges) {
        super(context, indicatorSize, mustAnimateChanges);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_round_square_unselected, null));
        } else {
            setBackgroundDrawable(getResources().getDrawable(R.drawable.indicator_round_square_unselected));
        }
    }

    @Override
    public void onCheckedChange(boolean isChecked) {
        super.onCheckedChange(isChecked);
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_round_square_selected, null));
            } else {
                setBackgroundDrawable(getResources().getDrawable(R.drawable.indicator_round_square_selected));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_round_square_unselected, null));
            } else {
                setBackgroundDrawable(getResources().getDrawable(R.drawable.indicator_round_square_unselected));
            }
        }
    }
}
