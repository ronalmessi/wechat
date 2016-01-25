package com.ronaldong.messi.ui.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ronaldong.messi.R;


/**
 * Created by sanvi on 6/12/14.
 */
public class InfoTextView extends LinearLayout {
	private Context mContext;
	private TextView labelTextView;
	private TextView valueTextView;
	private ImageView rightArrowImageView;
	private ImageView image_myicon;

	public InfoTextView(Context context) {
		super(context);
		this.mContext = context;
		initViews();
	}

	public InfoTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initViews();
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.iclass);
		labelTextView.setText(typedArray.getString(R.styleable.iclass_label));
		labelTextView.setTextColor(typedArray.getColor(
				R.styleable.iclass_label_color,
				labelTextView.getCurrentTextColor()));
		labelTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray
				.getDimensionPixelSize(R.styleable.iclass_label_textSize,
						getPixel(14.0f)));
		valueTextView.setText(typedArray.getString(R.styleable.iclass_value));
		valueTextView.setTextColor(typedArray.getColor(
				R.styleable.iclass_value_color,
				valueTextView.getCurrentTextColor()));
		valueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray
				.getDimensionPixelSize(R.styleable.iclass_value_textSize,
						getPixel(14.0f)));
		rightArrowImageView.setVisibility(VISIBLE);

		image_myicon.setVisibility(VISIBLE);
		typedArray.recycle();
	}

	private int getPixel(float dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, getResources().getDisplayMetrics());
	}

	private void initViews() {
		LayoutInflater.from(mContext).inflate(R.layout.widget_info_textview,
				this, true);
		labelTextView = (TextView) findViewById(R.id.widget_info_label);
		valueTextView = (TextView) findViewById(R.id.widget_info_value);
		rightArrowImageView = (ImageView) findViewById(R.id.self_info_right_arrow);
		image_myicon = (ImageView) findViewById(R.id.image_myicon);
	}
}
