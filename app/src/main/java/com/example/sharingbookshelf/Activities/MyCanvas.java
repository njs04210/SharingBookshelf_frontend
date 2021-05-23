package com.example.sharingbookshelf.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sharingbookshelf.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyCanvas extends View {
    Bitmap resize = null;
    Bitmap mBitmap;
    Canvas mCanvas;
    Paint mPaint = new Paint();
    Paint paint = new Paint();

    Boolean stamp = false, rotate = false, move = false, scale = false, skew = false;
    String operationType = "";

    public MyCanvas(Context context) {
        super(context);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint.setColor(Color.BLACK);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint.setColor(Color.BLACK);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

    }

    public void setStemp(Boolean b) {
        this.stamp = b;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap != null) {
            if (operationType.equalsIgnoreCase("eraser")) {
                clear();
            } else if (operationType.equalsIgnoreCase("open")) {
                canvas.drawBitmap(resize, (getWidth() - resize.getWidth()) / 2
                        , (getHeight() - resize.getHeight()) / 2, paint);
            } else if (operationType.equalsIgnoreCase("rotate")) {
                rotate = true;
            } else if (operationType.equalsIgnoreCase("move")) {
                move = true;
            } else if (operationType.equalsIgnoreCase("scale")) {
                scale = true;
            } else if (operationType.equalsIgnoreCase("skew")) {
                skew = true;
            }
            canvas.drawBitmap(mBitmap, 0, 0, paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.WHITE);
        mCanvas.save();
    }

    int oldX = -1, oldY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        /*if (stamp) {
            if (event.getAction() == event.ACTION_DOWN) {
                drawStamp(x, y);
            }
        } else {*/
            if (event.getAction() == event.ACTION_DOWN) {
                oldX = x;
                oldY = y;
            } else if (event.getAction() == event.ACTION_MOVE) {
                if (oldX != -1) {
                    mCanvas.drawLine(oldX, oldY, x, y, paint);
                    invalidate();
                    oldX = x;
                    oldY = y;
                }

            } else if (event.getAction() == event.ACTION_UP) {
                if (oldX != -1) {
                    mCanvas.drawLine(oldX, oldY, x, y, paint);
                    invalidate();
                }
                oldX = -1;
                oldY = -1;
            }

//        }
        return true;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
        invalidate();
    }

  /*  public void drawStamp(int x, int y) {
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.icon_logo);
        if (rotate) {
            mCanvas.rotate(30, getWidth() / 2, getHeight() / 2);
        }
        if (move) {
            x += 10;
            y += 10;
        }
        if (scale) {
            Bitmap bigBitmap = Bitmap.createScaledBitmap(img, (int) (img.getWidth() * 1.5), (int) (img.getHeight() * 1.5), false);
            img = bigBitmap;
        }
        if (skew) {
            mCanvas.skew(0.2f, 0);
        }

        mCanvas.drawBitmap(img, x, y, mPaint);
        img.recycle();
        invalidate();

    }*/

    private void clear() {
        mBitmap.eraseColor(Color.WHITE);
        mCanvas.restore();
        mCanvas.save();
        stamp = false;
        rotate = false;
        move = false;
        scale = false;
        skew = false;
        this.setOperationType("");
    }

    public void setPenColor(int colorId) {
        if (colorId == 1) paint.setColor(Color.BLUE);
        else if (colorId == 2) paint.setColor(Color.RED);
        else paint.setColor(Color.BLACK);
    }

    public void setPenWidth(boolean widthFlag) {
        if (widthFlag) paint.setStrokeWidth(10);
        else paint.setStrokeWidth(5);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

  /*  public boolean save(String file_name) {
        try {
            FileOutputStream out = new FileOutputStream(file_name, false);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
        }
        return false;
    }*/
}
