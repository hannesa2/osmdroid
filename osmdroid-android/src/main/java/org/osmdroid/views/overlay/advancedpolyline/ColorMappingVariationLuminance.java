package org.osmdroid.views.overlay.advancedpolyline;

import java.util.ArrayList;

/**
 * Color mapping for luminance variation.
 * @author Matthias Dittmer
 */
public class ColorMappingVariationLuminance extends ColorMappingVariation {

    /**
     * Fixed HSL values.
     */
    private float mHue;
    private float mSaturation;

    /**
     * Constructor
     * @param scalarStart start of scalar
     * @param scalarEnd end of scalar
     * @param luminanceStart luminance start value
     * @param luminanceEnd luminance end value
     * @param hue fixed hue value
     * @param saturation fixed saturation value
     */
    public ColorMappingVariationLuminance(final float scalarStart, final float scalarEnd,
                                           float luminanceStart, float luminanceEnd,
                                           final float hue, final float saturation) {

        // do basic clipping for luminance value
        // please note: end can be lower than start for inverse mapping
        luminanceStart = ColorHelper.constrain(luminanceStart, 0.0f, 1.0f);
        luminanceEnd = ColorHelper.constrain(luminanceEnd, 0.0f, 1.0f);

        // do clipping for hue and saturation
        mHue = ColorHelper.constrain(hue, 0.0f, 360.0f);
        mSaturation = ColorHelper.constrain(saturation, 0.0f, 1.0f);

        super.init(scalarStart, scalarEnd, luminanceStart, luminanceEnd);
    }

    /**
     * Add a points.
     * @param scalar point scalar
     */
    @Override
    public void addPoint(final float scalar) {
        // create mapped luminance value
        super.addToLists(scalar, mHue, mSaturation, mapScalar(scalar));
    }
}
