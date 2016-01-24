package com.gkudva.kidscolor.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gkudva on 1/23/16.
 */
public class ColorMap {

    private static Map<String, String> colorsMap;
    private static List<String> keys;

    public static void initializeColorMap()
    {
        colorsMap = new HashMap<>();

        colorsMap.put("fffafa", "Snow");
        colorsMap.put("faf0e6","Linen");
        colorsMap.put("fffff0", "Ivory");
        colorsMap.put("f0ffff","Azure");
        colorsMap.put("ffffff","White");
        colorsMap.put("000000","Black");
        colorsMap.put("bebebe","Gray");
        colorsMap.put("2f4f4f","Dark Gray");
        colorsMap.put("191970","Midnight Blue");
        colorsMap.put("000080","Navy");
        colorsMap.put("0000ff","Blue");
        colorsMap.put("87ceeb","Sky Blue");
        colorsMap.put("00ffff","Cyan");
        colorsMap.put("006400","Dark Green");
        colorsMap.put("f0e68c","Khaki");
        colorsMap.put("7cfc00","Green");
        colorsMap.put("ffff00","Yellow");
        colorsMap.put("ffd700","Gold");
        colorsMap.put("a52a2a","Brown");
        colorsMap.put("d2691e","Chocolate");
        colorsMap.put("ff0000","Red");
        colorsMap.put("f5f5dc","Biege");
        colorsMap.put("ffa500","Orange");
        colorsMap.put("ff7f50","Coral");
        colorsMap.put("fa8072","Salmon");
        colorsMap.put("ff1493","Pink");
        colorsMap.put("b03060","Maroon");
        colorsMap.put("ee82ee","Violet");
        colorsMap.put("dda0dd","Plum");
        colorsMap.put("da70d6","Orchid");
        colorsMap.put("a020f0","Purple");
        colorsMap.put("f5f5f5","White Smoke");
        colorsMap.put("ffe4b5","Moccasin");
        colorsMap.put("fff8dc","COrnsilk");
        colorsMap.put("fff5ee","Seashell");
        colorsMap.put("f0fff0","Honeydew");
        colorsMap.put("e6e6fa","Lavender");
        colorsMap.put("40e0d0","Turquoise");
        colorsMap.put("7fffd4","Aquamarine");
        colorsMap.put("bdb76b","Dark Khaki");
        colorsMap.put("f5f5dc","Beige");
        colorsMap.put("f5deb3","Wheat");
        colorsMap.put("ff6347","Tomato");
        colorsMap.put("d8bfd8","Thistle");
        colorsMap.put("FFFF31","Daffodil");
        colorsMap.put("E75480","Dark Pink");
        colorsMap.put("1560BD","Denim");
        colorsMap.put("50C878","Emerald");
        colorsMap.put("808000","Olive");
        colorsMap.put("738678","Xanadu");

        keys = new ArrayList<String>(colorsMap.keySet());
    }

    public static int getColorMapSize()
    {
        return colorsMap.size();
    }

    public static String getColorHexCode(int colorIndex)
    {
        if (colorIndex < colorsMap.size())
        {
            return keys.get(colorIndex);
        }

        return "";
    }

    public static String getColorName(String key)
    {
        return colorsMap.get(key);
    }
}
