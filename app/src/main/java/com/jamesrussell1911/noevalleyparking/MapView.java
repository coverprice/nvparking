package com.jamesrussell1911.noevalleyparking;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This is the most important class, as it draws the blocks and Street Sweeping schedule
 */
public class MapView extends View {
    protected HashMap<Integer, Block> blocks;

    private int offset_left_px, offset_top_px;
    private int grid_width_px, grid_height_px, street_width = 64;
    private int screen_width_px, screen_height_px;

    /**
     * Constructor
     * @param context
     */
    public MapView(Context context) {
        super(context);
        blocks = BlockList.makeBlocks();
    }

    /**
     * Makes the screen a single color
     * @param canvas
     */
    protected void clearView(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
    }

    /**
     * The "main" entry point. Note that getWidth() etc won't return accurate results if
     * called in the constructor, they must be called here.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        screen_width_px = getWidth();
        screen_height_px = getHeight();

        // "Grid" here refers to the grid of city Blocks, not of pixels.
        grid_width_px = screen_width_px / 4;
        grid_height_px = (grid_width_px * 250) / 300;
        offset_left_px = -grid_width_px / 3;
        offset_top_px = 60;

        clearView(canvas);
        drawCityBlocks(canvas);
        drawStreetNames(canvas);
        drawStreetSweepingSchedule(canvas);
    }


    protected void drawCityBlocks(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        RectF rect = new RectF();
        for (Block b : blocks.values()) {
            int col;
            switch (b.type) {
                case PARK:
                    col = Color.rgb(202, 223, 170);
                    break;

                case SCHOOL:
                    col = Color.rgb(232, 221, 189);
                    break;

                default:
                    // House
                    col = Color.rgb(0xCD, 0x5C, 0x5C);
                    break;
            }
            paint.setColor(col);

            rect.left = offset_left_px + b.grid_x * grid_width_px;
            rect.top = offset_top_px + b.grid_y * grid_height_px;
            rect.right = rect.left + b.width * grid_width_px - street_width;
            rect.bottom = rect.top + b.height * grid_height_px - street_width;

            canvas.drawRoundRect(rect, 30.0f, 30.0f, paint);
        }
    }

    public void drawStreetNames(Canvas canvas) {
        // Draw street names
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(70.0f);

        List<String> vStreetNames = Arrays.asList(
                "N O E",
                "C A S T R O",
                "D I A M O N D",
                "D O U G L A S S");
        int a = 0;
        for (String name: vStreetNames) {
            canvas.drawText(
                    name,
                    offset_left_px + 2 * grid_width_px - street_width / 2,
                    offset_top_px + (a++ * 2) * grid_height_px - 10,
                    paint);
        }

        // The Canvas.rotate function is a bit confusing. You can specify a pivot point
        // around which it will rotate, but that doesn't mean that point becomes the new
        // origin. It means that if you rotate around x,y, then drawing something at position
        // x,y will be at same location as before the rotation.
        List<String> hStreetNames = Arrays.asList(
                "J E R S E Y",
                "2 5 T H",
                "C L I P P E R",
                "2 6 T H");
        a = 1;
        for (String name: hStreetNames) {
            canvas.save();
            int x = offset_left_px + grid_width_px * a++ - street_width / 2 + 24;
            int y = offset_top_px + grid_height_px * 3;
            canvas.rotate(-90.0f, x, y);
            canvas.drawText(
                    name,
                    x,
                    y,
                    paint);
            canvas.restore();
        }
    }

    /**
     * Draws all the segments on each side of the road.
     * @param canvas
     */
    public void drawStreetSweepingSchedule(Canvas canvas) {
        // Determine the day of the week
        CalendarUtils cu = new CalendarUtils();
        int day_of_week = cu.getDayOfWeek();
        boolean is_odd_week = cu.getIsWeekOdd();

        // For each block/CompassDir, look for policies. If there are
        // any determine if any of them disallow us from parking there.
        for(Block b : blocks.values()) {
            for(CompassDir dir: CompassDir.values()) {
                boolean is_permitted = true;
                boolean found_policy_for_this_dir = false;
                for(Policy p: b.policies) {
                    if(p.dir == dir) {
                        found_policy_for_this_dir = true;
                        if(!p.isPermitted(day_of_week, is_odd_week)) {
                            is_permitted = false;
                            break;
                        }
                    }
                }
                if(found_policy_for_this_dir) {
                    drawSegmentStatus(canvas, b, dir, is_permitted);
                }
            }
        }
    }

    protected void drawSegmentStatus(
            Canvas canvas,
            Block b,
            CompassDir dir,
            boolean is_permitted) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF();

        int indent = 10;
        int stripe_width = 20;

        switch(dir) {
            case NORTH:
                rect.left = indent;
                rect.right = rect.left + stripe_width;

                rect.top = indent;
                rect.bottom = b.height * grid_height_px - street_width - indent;
                break;

            case SOUTH:
                rect.right = grid_width_px * b.width - indent - street_width;
                rect.left = rect.right - stripe_width;

                rect.top = indent;
                rect.bottom = b.height * grid_height_px - street_width - indent;
                break;

            case EAST:
                rect.top = indent;
                rect.bottom = rect.top + stripe_width;

                rect.left = indent;
                rect.right = b.width * grid_width_px - street_width - indent;
                break;

            case WEST:
                rect.bottom = b.height * grid_height_px - street_width - indent;
                rect.top = rect.bottom - stripe_width;
                rect.left = indent;
                rect.right = b.width * grid_width_px - street_width - indent;
                break;
        }
        rect.left += offset_left_px + b.grid_x * grid_width_px;
        rect.right += offset_left_px + b.grid_x * grid_width_px;
        rect.top += offset_top_px + b.grid_y * grid_height_px;
        rect.bottom += offset_top_px + b.grid_y * grid_height_px;
        paint.setColor(is_permitted ? Color.GREEN : Color.RED);
        canvas.drawRoundRect(rect, 20.0f, 20.0f, paint);
    }


}