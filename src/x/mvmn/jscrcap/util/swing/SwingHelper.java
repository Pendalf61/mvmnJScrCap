package x.mvmn.jscrcap.util.swing;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

public class SwingHelper {

	private static volatile Robot ROBOT;
	private static final Object LOCK_OBJECT_4_ROBOT = new Object();

	public static Robot getRobot() {
		Robot result = SwingHelper.ROBOT;
		if (result == null) {
			synchronized (LOCK_OBJECT_4_ROBOT) {
				if (SwingHelper.ROBOT == null) {
					try {
						SwingHelper.ROBOT = new Robot();
					} catch (AWTException e) {
						throw new RuntimeException(e);
					}
				}
				result = SwingHelper.ROBOT;
			}
		}
		return result;
	}

	public static Rectangle getComponentRect(Component component) {
		Point location = component.getLocationOnScreen();
		Dimension size = component.getSize();
		return new Rectangle(location.x, location.y, size.width, size.height);
	}

	public static void moveToScreenCenter(Component component) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension componentSize = component.getSize();
		int newComponentX = screenSize.width - componentSize.width;
		if (newComponentX >= 0)
			newComponentX = newComponentX / 2;
		else
			newComponentX = 0;
		int newComponentY = screenSize.height - componentSize.height;
		if (newComponentY >= 0)
			newComponentY = newComponentY / 2;
		else
			newComponentY = 0;
		component.setLocation(newComponentX, newComponentY);
	}

}
