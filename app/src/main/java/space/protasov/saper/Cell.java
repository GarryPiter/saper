package space.protasov.saper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class Cell extends BaseCell implements View.OnClickListener , View.OnLongClickListener {

    public Cell(Context context, int x, int y){
        super(context);

        setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().click(getXPos(),getYPos());
    }

    @Override
    public boolean onLongClick(View v) {
        GameEngine.getInstance().flag(getXPos() , getYPos() );
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("Minesweeper", "Cell :: on Draw");
        drawButton(canvas);

        if( isFlagged()){
            drawFlag(canvas);
        }else  if (isRevealed() && isBomb() && !isClicked()){
            drawNormalBomb(canvas);
        }else  {
            if(isClicked()){
                if(getValue() == -1){
                    drawBombExploded(canvas);
                }else {
                    drawNumber(canvas);
                }
            }else{
                drawButton(canvas);
            }
        }
    }

    private void drawBombExploded(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawFlag (Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawNormalBomb(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas){
        Drawable drawable = null;

        switch (getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_zero);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_one);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_two);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_three);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_four);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_five);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_six);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_seven);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.onelevel_eight);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }


}
