package com.maoqis.desk.javaanglelib;

public class AngleOffsetBean {
    /**
     * 是否是进中袋
     */
    public boolean isMiddle;
    public double angle;
    public double zone;
    public double offset;


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getZone() {
        return zone;
    }

    public void setZone(double zone) {
        this.zone = zone;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "AngleOffsetBean{" +
                "angle=" + angle +
                ", zone=" + zone +
                ", offset=" + offset +
                '}';
    }
}
