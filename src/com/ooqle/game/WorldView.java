package com.ooqle.game;

/**
 * Created by augiedoebling on 5/16/15.
 */
public class WorldView {

    private int viewCols;
    private int viewRows;
    private World world;
    private int tileWidth;

    public WorldView(int view_cols, int view_rows, /*screen*/ World world, int tile_width)
    {
        this.viewCols = view_cols;
        this.viewRows = view_rows;
        this.world = world;
        this.tileWidth = tile_width;
    }


}
