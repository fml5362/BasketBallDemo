package com.hyphenate.chatuidemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

import com.hyphenate.chatuidemo.R;


public class NoteEditText extends EditText {
	private int padding = 80;
	private final static int SPACING_LINE = 10;
	private int lineColor = Color.BLACK;
	private int lineStrokeWidth = 1;
	private Rect mRect;

	public NoteEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setGravity(Gravity.TOP);
		setLineSpacing(SPACING_LINE,1);
		mRect = new Rect();

		// 从xml布局文件中获取用户定义的控件属性值
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.NoteEditText);
		padding = (int) array.getDimension(R.styleable.NoteEditText_padding,
				padding);
		lineColor = array.getColor(R.styleable.NoteEditText_lineColor,
				lineColor);
		lineStrokeWidth = array.getInteger(
				R.styleable.NoteEditText_lineStrokeWidth, lineStrokeWidth);
		// 设置文字的内填充
		setPadding(padding, 0, padding, 0);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint paint = new Paint();
		// 抗锯齿
		paint.setAntiAlias(true);
		paint.setColor(lineColor);
		paint.setStrokeWidth(lineStrokeWidth);

		// 获取控件的宽度和高度
		int viewHeight = getHeight();
		int viewWidth = getWidth();

		int lineHeight = this.getLineHeight();
		int pagelineCounts = viewHeight / lineHeight;

		for (int i = 0; i < pagelineCounts; i++) {
			canvas.drawLine(padding, (i + 1) * lineHeight, viewWidth - padding,
					(i + 1) * lineHeight, paint);
		}

		// Rect r = mRect;
		int textLineCounts = getLineCount();
		int extraCount = textLineCounts - pagelineCounts;
		if (extraCount > 0) {
			for (int i = 0; i < textLineCounts; i++) {
				if (i >= pagelineCounts) {
					// 取得每一行的基准Y坐标，并将每一行的界限值填写到r中
					int baseline = getLineBounds(i, mRect);
					// 设置每一行的文字下带下划线
					canvas.drawLine(padding, baseline + SPACING_LINE, viewWidth
							- padding, baseline + SPACING_LINE, paint);
				}
			}
		}
		// invalidate();
	}

}
