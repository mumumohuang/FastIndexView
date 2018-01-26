package com.example.gyh.fastindexview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 快速索引的基本实现
 */
public class FastIndexView extends View {

    private Context mContext;
    private Paint mBgPaint;
    private Paint mTextPaint;
    private float cell;
    private int mPaddingTop;
    private float mTextHeightY;
    private int mCurrentIndex;
    private int mRealWidth;
    private int mPaddingLeft;
    private int mPaddingBottom;
    private int mRealHeight;
    private Rect bg;


    public FastIndexView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FastIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public FastIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mBgPaint = new Paint();
        mBgPaint.setColor(0xff3c3f41);
        mBgPaint.setStyle(Paint.Style.FILL);


        mTextPaint = new Paint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setTextSize(45);
        mTextPaint.setStyle(Paint.Style.FILL);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeightY = Math.abs(fontMetrics.ascent) - fontMetrics.descent;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        cell = Float.valueOf(i) / mTexts.length;
        mPaddingTop = getPaddingTop();
        mPaddingLeft = getPaddingLeft();
        mRealWidth = getMeasuredWidth() - getPaddingRight();
        mPaddingBottom = getPaddingBottom();
        mRealHeight = getMeasuredHeight() - getPaddingBottom();
        bg = new Rect(mPaddingLeft, mPaddingTop, mRealWidth, mRealHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(bg, mBgPaint);
        for (int i = 0; i < mTexts.length; i++) {
            float textWidth = mTextPaint.measureText(mTexts[i]);//每次的width都要计算
            //(cell - y)/2 这个高度 是为了让每个text在每个cell居中
            if (i == mCurrentIndex) {
                mTextPaint.setColor(0xffff0000);
            } else {
                mTextPaint.setColor(0xffffffff);
            }
            canvas.drawText(mTexts[i], Float.valueOf(getMeasuredWidth()) / 2 - textWidth / 2, getPaddingTop() + mTextHeightY + (cell - mTextHeightY) / 2 + cell * i, mTextPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(mContext,"我被点击了",Toast.LENGTH_SHORT).show();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                confirmText(event.getY());
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return true;
    }

    private void confirmText(float y) {
        mCurrentIndex = (int) (y / cell);
        if (mCurrentIndex < 0) {
            mCurrentIndex = 0;
        } else if (mCurrentIndex > mTexts.length - 1) {
            mCurrentIndex = mTexts.length -1;
        }
        if (mOnSelectListener != null) {
            mOnSelectListener.onSelect(mTexts[mCurrentIndex]);
        }
        invalidate();
    }

    private OnSelectListener mOnSelectListener;

    public void setOnSelectListener(OnSelectListener mOnSelectListener) {
        this.mOnSelectListener = mOnSelectListener;
    }

    public interface OnSelectListener {
        void onSelect(String textValue);
    }


    private String[] mTexts = {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z",
    };
}
