package de.nightdreamer.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 19:27
 */
public class Input {

    public Xbox360Pad xbox360Pad;
    private Controller controller;
    private boolean keyboard[], gamepad[], axisY;

    public Input() {
        keyboard = new boolean[256];
        gamepad = new boolean[10];
        xbox360Pad = new Xbox360Pad();
    }

    public void checkForController() {
        if (Controllers.getControllers().size > 0 && controller == null) {
            if (Controllers.getControllers().first().getName().toLowerCase().contains("xbox")
                    && Controllers.getControllers().first().getName().contains("360")) {
                controller = Controllers.getControllers().first();
            }
        } else if (Controllers.getControllers().size == 0 && controller != null) {
            controller = null;
        }
    }

    public boolean isButtonPressed(int button) {
        if (controller != null) {
            if (controller.getButton(button)) {
                if (gamepad[button]) {
                    return false;
                } else {
                    gamepad[button] = true;
                    return true;
                }
            } else {
                gamepad[button] = false;
            }
        }

        return false;
    }

    public boolean getAxisOnce(int axis) {
        if (controller != null) {
            float tmp = controller.getAxis(axis);

            if (tmp < -0.5f || tmp > 0.5f) {
                if (axisY) {
                    return false;
                } else {
                    axisY = true;
                    return true;
                }
            } else {
                axisY = false;
            }
        }

        return false;
    }

    public float getAxis(int axis) {
        if (controller != null) {
            return controller.getAxis(axis);
        }

        return 0.0f;
    }

    public boolean isKeyPressed(int key) {
        if (Gdx.input.isKeyPressed(key)) {
            if (keyboard[key]) {
                return false;
            } else {
                keyboard[key] = true;
                return true;
            }
        } else {
            keyboard[key] = false;
        }

        return false;
    }


    public class Xbox360Pad {
        /*
         * It seems there are different versions of gamepads with different ID Strings.
         * Therefore its IMO a better bet to check for:
         * if (controller.getName().toLowerCase().contains("xbox") &&
                       controller.getName().contains("360"))
         *
         * Controller (Gamepad for Xbox 360)
           Controller (XBOX 360 For Windows)
           Controller (Xbox 360 Wireless Receiver for Windows)
           Controller (Xbox wireless receiver for windows)
           XBOX 360 For Windows (Controller)
           Xbox 360 Wireless Receiver
           Xbox Receiver for Windows (Wireless Controller)
           Xbox wireless receiver for windows (Controller)
         */
        //public static final String ID = "XBOX 360 For Windows (Controller)";
        public static final int BUTTON_X = 2;
        public static final int BUTTON_Y = 3;
        public static final int BUTTON_A = 0;
        public static final int BUTTON_B = 1;
        public static final int BUTTON_BACK = 6;
        public static final int BUTTON_START = 7;
        public static final int BUTTON_LB = 4;
        public static final int BUTTON_L3 = 8;
        public static final int BUTTON_RB = 5;
        public static final int BUTTON_R3 = 9;
        public static final int AXIS_LEFT_X = 1; //-1 is left | +1 is right
        public static final int AXIS_LEFT_Y = 0; //-1 is up | +1 is down
        public static final int AXIS_LEFT_TRIGGER = 4; //value 0 to 1f
        public static final int AXIS_RIGHT_X = 3; //-1 is left | +1 is right
        public static final int AXIS_RIGHT_Y = 2; //-1 is up | +1 is down
        public static final int AXIS_RIGHT_TRIGGER = 4; //value 0 to -1f
    }


}
