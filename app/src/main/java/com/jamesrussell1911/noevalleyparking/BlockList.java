package com.jamesrussell1911.noevalleyparking;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Returns list of Block objects, which describe a black.
 */
public class BlockList {
    public static HashMap<Integer, Block> getBlockList() {
        HashMap<Integer, Block> blocks = makeBlocks();
        addPolicies(blocks);
        return blocks;
    }

    /**
     * Generates the list of Block objects (without any policies yet)
     * @return ArrayList<Block>
     */
    public static HashMap<Integer, Block> makeBlocks() {
        HashMap<Integer, Block> blocks = new HashMap<>();
        int width = 1;
        int height = 2;
        int block_num = 1;
        BlockType type = BlockType.HOUSES;
        for (int yy = 0; yy < 3; yy++) {
            for (int xx = 0; xx < 5; xx++, block_num++) {
                blocks.put(block_num, new Block(
                        xx, yy * 2, width, height,
                        (block_num == 3) ? BlockType.SCHOOL : type
                ));
            }
        }

        width = 2;
        height = 1;
        blocks.put(block_num++, new Block(
                0, 6 , width, height,
                type
        ));
        blocks.put(block_num++, new Block(
                0, 7 , width, height,
                type
        ));
        blocks.put(block_num++, new Block(
                2, 6, 1, 4,
                type
        ));

        // Add the Park
        blocks.put(block_num++, new Block(
                3, 6, 2, 4,
                BlockType.PARK
        ));
        return blocks;
    }

    public static void add(Block b, CompassDir dir, int day_of_week, Frequency freq) {
        b.addPolicy(new Policy(dir, day_of_week, freq));
    }

    protected static void addPolicies(HashMap<Integer, Block> blocks) {
        Block b = blocks.get(1);
        add(b, CompassDir.SOUTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(2);
        add(b, CompassDir.NORTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.TUESDAY, Frequency.WEEKLY);
        add(b, CompassDir.WEST, Calendar.THURSDAY, Frequency.WEEKLY);

        b = blocks.get(3);
        add(b, CompassDir.NORTH, Calendar.MONDAY, Frequency.WEEKLY);
        add(b, CompassDir.EAST, Calendar.WEDNESDAY, Frequency.WEEKLY);
        add(b, CompassDir.SOUTH, Calendar.WEDNESDAY, Frequency.WEEKLY);
        add(b, CompassDir.SOUTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.THURSDAY, Frequency.WEEKLY);

        b = blocks.get(4);
        add(b, CompassDir.NORTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(5);
        add(b, CompassDir.NORTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(6);
        add(b, CompassDir.SOUTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(7);
        add(b, CompassDir.NORTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.WEEKLY);
        add(b, CompassDir.EAST, Calendar.FRIDAY, Frequency.WEEKLY);
        add(b, CompassDir.SOUTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(8);
        add(b, CompassDir.NORTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(9);
        add(b, CompassDir.NORTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(10);
        add(b, CompassDir.NORTH, Calendar.FRIDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(11);
        add(b, CompassDir.SOUTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(12);
        add(b, CompassDir.NORTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(13);
        add(b, CompassDir.NORTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(14);
        add(b, CompassDir.NORTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(15);
        add(b, CompassDir.NORTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(16);
        add(b, CompassDir.SOUTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(17);
        add(b, CompassDir.NORTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.WEST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(18);
        add(b, CompassDir.NORTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.SOUTH, Calendar.MONDAY, Frequency.EVEN_WEEKS);

        b = blocks.get(19);
        add(b, CompassDir.NORTH, Calendar.TUESDAY, Frequency.EVEN_WEEKS);
        add(b, CompassDir.EAST, Calendar.WEDNESDAY, Frequency.EVEN_WEEKS);
    }
}