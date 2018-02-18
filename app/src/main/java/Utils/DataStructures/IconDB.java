package Utils.DataStructures;

import java.util.List;

public class IconDB {
    static List<Icon> icons;

    public static List<Icon> getIcons() {
        return icons;
    }

    public static void setIcons(List<Icon> icons) {
        IconDB.icons = icons;
    }

    public static void insertIcon(Icon icon) {
        icons.add(icons.size() - 1, icon);
    }
}
