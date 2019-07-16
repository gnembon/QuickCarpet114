package quickcarpet.logging.loghelpers;

import net.minecraft.entity.TntEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import quickcarpet.logging.Logger;
import quickcarpet.logging.Loggers;
import quickcarpet.utils.Messenger;

import java.util.LinkedHashMap;

public class TNTLogHelper {
    /*
    private final TntEntity tnt;
    private final double primedX, primedY, primedZ, primedAngle;


    public TNTLogHelper(TntEntity tnt) {
        this.tnt = tnt;
        primedX = tnt.x;
        primedY = tnt.y;
        primedZ = tnt.z;
        Vec3d v = tnt.getVelocity();
        if (v.x == 0 && v.z == 0) {
            primedAngle = Double.NaN;
        } else {
            double angle = -Math.toDegrees(Math.atan2(v.x, v.z));
            if (angle < 0) angle += 360;
            primedAngle = angle == -0 ? 0 : angle;
        }
    }


    public void onExploded() {
        double x = tnt.x;
        double y = tnt.y;
        double z = tnt.z;
        Loggers.TNT.log(option -> {
            switch (option) {
                case "brief":
                    return new Text[]{Messenger.c(
                            "l P ", Messenger.dblt("l", primedX, primedY, primedZ, primedAngle),
                            "r  E ", Messenger.dblt("r", x, y, z))};
                case "full":
                    return new Text[]{Messenger.c(
                            "l P ", Messenger.dblf("l", primedX, primedY, primedZ, primedAngle),
                            "r  E ", Messenger.dblf("r", x, y, z))};
            }
            return null;
        }, () -> new LogParameters(x, y, z));
    }

    public class LogParameters extends LinkedHashMap<String, Double> implements Logger.CommandParameters<Double> {
        public LogParameters(double x, double y, double z) {
            put("primed.x", primedX);
            put("primed.y", primedY);
            put("primed.z", primedZ);
            put("primed.angle", primedAngle);
            put("exploded.x", x);
            put("exploded.y", y);
            put("exploded.z", z);
        }
    }*/
}
