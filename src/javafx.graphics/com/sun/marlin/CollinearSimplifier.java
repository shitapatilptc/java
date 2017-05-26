/*
 * Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.marlin;

import com.sun.javafx.geom.PathConsumer2D;

public final class CollinearSimplifier implements PathConsumer2D {

    enum SimplifierState {

        Empty, PreviousPoint, PreviousLine
    };
    // slope precision threshold
    static final float EPS = 1e-4f; // aaime proposed 1e-3f

    PathConsumer2D delegate;
    SimplifierState state;
    float px1, py1, px2, py2;
    float pslope;

    CollinearSimplifier() {
    }

    public CollinearSimplifier init(PathConsumer2D delegate) {
        this.delegate = delegate;
        this.state = SimplifierState.Empty;

        return this; // fluent API
    }

    @Override
    public void pathDone() {
        emitStashedLine();
        state = SimplifierState.Empty;
        delegate.pathDone();
    }

    @Override
    public void closePath() {
        emitStashedLine();
        state = SimplifierState.Empty;
        delegate.closePath();
    }

    @Override
    public void quadTo(float x1, float y1, float x2, float y2) {
        emitStashedLine();
        delegate.quadTo(x1, y1, x2, y2);
        // final end point:
        state = SimplifierState.PreviousPoint;
        px1 = x2;
        py1 = y2;
    }

    @Override
    public void curveTo(float x1, float y1, float x2, float y2,
                        float x3, float y3) {
        emitStashedLine();
        delegate.curveTo(x1, y1, x2, y2, x3, y3);
        // final end point:
        state = SimplifierState.PreviousPoint;
        px1 = x3;
        py1 = y3;
    }

    @Override
    public void moveTo(float x, float y) {
        emitStashedLine();
        delegate.moveTo(x, y);
        state = SimplifierState.PreviousPoint;
        px1 = x;
        py1 = y;
    }

    @Override
    public void lineTo(final float x, final float y) {
        switch (state) {
            case Empty:
                delegate.lineTo(x, y);
                state = SimplifierState.PreviousPoint;
                px1 = x;
                py1 = y;
                return;

            case PreviousPoint:
                state = SimplifierState.PreviousLine;
                px2 = x;
                py2 = y;
                pslope = getSlope(px1, py1, x, y);
                return;

            case PreviousLine:
                final float slope = getSlope(px2, py2, x, y);
                // test for collinearity
                if ((slope == pslope) || (Math.abs(pslope - slope) < EPS)) {
                    // merge segments
                    px2 = x;
                    py2 = y;
                    return;
                }
                // emit previous segment
                delegate.lineTo(px2, py2);
                px1 = px2;
                py1 = py2;
                px2 = x;
                py2 = y;
                pslope = slope;
                return;
            default:
        }
    }

    private void emitStashedLine() {
        if (state == SimplifierState.PreviousLine) {
            delegate.lineTo(px2, py2);
        }
    }

    private static float getSlope(float x1, float y1, float x2, float y2) {
        float dy = y2 - y1;
        if (dy == 0f) {
            return (x2 > x1) ? Float.POSITIVE_INFINITY
                   : Float.NEGATIVE_INFINITY;
        }
        return (x2 - x1) / dy;
    }
}
