package com.jamesrussell1911.noevalleyparking;

import java.util.ArrayList;

/**
 * Data object that defines the attributes of a city block.
 */
public class Block {
    public final int grid_x; // Grid position, not pixel position
    public final int grid_y; // Grid position, not pixel position
    public final int width; // Measured in blocks, not pixels
    public final int height; // Measured in blocks, not pixels
    public final BlockType type;

    public ArrayList<Policy> policies; // Sweeping policies that apply to this block

    public Block(
            int grid_x, int grid_y,
            int width, int height,
            BlockType type
    ) {
        this.grid_x = grid_x;
        this.grid_y = grid_y;
        this.width = width;
        this.height = height;
        this.type = type;

        policies = new ArrayList<Policy>();
    }

    public void addPolicy(Policy policy) {
        policies.add(policy);
    }
}
