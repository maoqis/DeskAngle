package com.maoqis.desk.javaanglelib;

public class AngleUtil {
    public static double w = 400;
    public static double h = 800;

    public static void main(String[] args) {


        int max = 90;
        getZoneOffset(33.6);
//        for (int angle = 0; angle <= max; angle++){
//            getZoneOffset(angle);
//        }



    }

    public static AngleOffsetBean getZoneOffset(double angle) {
        AngleOffsetBean angleOffsetBean = new AngleOffsetBean();
        angleOffsetBean.setAngle(angle);
        int state = getState(angle);
        angleOffsetBean.setZone(state);

        double x =0;
        double tan;
        switch (state) {
            case 1:
                tan = Math.tan(angle * Math.PI / 180);
                x = tan * h;

                break;
            case 2:
                tan = Math.tan(angle * Math.PI / 180);
                x = h - w / tan;

                break;
            case 3:
                tan = Math.tan(angle * Math.PI / 180);
                x = h - w / tan;
                x -= 400;


                break;
            default:
                break;
        }
        angleOffsetBean.setOffset(x);
        System.out.println(angle+" , "+state + " , " + x);
        return angleOffsetBean;
    }

    private static int getState(double angle) {
        int state = 1;

        if (angle <= getAngleBottom()) {
            state = 1;
        } else if (angle <= 45) {
            state = 2;
        } else if (angle <= 90) {
            state = 3;
        }
        return state;
    }

    private static double getAngleBottom() {
        double w = 400;
        double h = 800;
        return getAngleByATan(w, h);
    }

    private static double getAngleByATan(double w, double h) {
        double atan = Math.atan(w / h);
        return atan * 180 / Math.PI;
    }
}
