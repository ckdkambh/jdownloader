package jd.captcha.easy;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import jd.captcha.pixelgrid.Captcha;
import jd.nutils.Colors;

public class CPoint extends Point implements Serializable, Cloneable {

    public final static byte LAB_DIFFERENCE = 1;
    public final static byte RGB_DIFFERENCE1 = 2;
    public final static byte RGB_DIFFERENCE2 = 3;
    public final static byte HUE_DIFFERENCE = 4;
    public final static byte SATURATION_DIFFERENCE = 5;
    public final static byte BRIGHTNESS_DIFFERENCE = 6;
    public final static byte RED_DIFFERENCE = 7;
    public final static byte GREEN_DIFFERENCE = 8;
    public final static byte BLUE_DIFFERENCE = 9;

    private static final long serialVersionUID = 333616481245029882L;
    private int color, distance;
    private boolean foreground = true;
    private byte colorDifferenceMode = LAB_DIFFERENCE;

    public byte getColorDistanceMode() {
        return colorDifferenceMode;
    }

    public void setColorDistanceMode(final byte colorDistanceMode) {
        this.colorDifferenceMode = colorDistanceMode;
    }

    public boolean isForeground() {
        return foreground;
    }

    public void setForeground(final boolean foreground) {
        this.foreground = foreground;
    }

    public int getColor() {
        return color;
    }

    public void setColor(final int color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public double getColorDifference(final int color) {
        double dst = 0;

        if (color == this.color) return dst;
        switch (colorDifferenceMode) {
        case LAB_DIFFERENCE:
            dst = Colors.getColorDifference(color, this.color);
            break;
        case RGB_DIFFERENCE1:
            dst = Colors.getRGBColorDifference1(color, this.color);
            break;
        case RGB_DIFFERENCE2:
            dst = Colors.getRGBColorDifference2(color, this.color);
            break;
        case HUE_DIFFERENCE:
            dst = Colors.getHueColorDifference360(color, this.color);
            break;
        case SATURATION_DIFFERENCE:
            dst = Colors.getSaturationColorDifference(color, this.color);
            break;
        case BRIGHTNESS_DIFFERENCE:
            dst = Colors.getBrightnessColorDifference(color, this.color);
            break;
        case RED_DIFFERENCE:
            dst = Math.abs(new Color(color).getRed() - new Color(this.color).getRed());
            break;
        case GREEN_DIFFERENCE:
            dst = Math.abs(new Color(color).getGreen() - new Color(this.color).getGreen());
            break;
        case BLUE_DIFFERENCE:
            dst = Math.abs(new Color(color).getBlue() - new Color(this.color).getBlue());
            break;
        default:
            dst = Colors.getColorDifference(color, this.color);
            break;
        }
        return dst;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    public CPoint() {
    }

    public CPoint(final int x, final int y, final int distance, final Captcha captcha) {
        this(x, y, distance, captcha.getPixelValue(x, y));
    }

    public CPoint(final int x, final int y, final int distance, final int color) {
        super(x, y);
        this.color = color;
        this.distance = distance;
    }

    @Override
    public Object clone() {
        return new CPoint(x, y, distance, color);
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj) || ((CPoint) obj).color == color;
    }
}
