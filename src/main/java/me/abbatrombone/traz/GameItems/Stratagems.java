package me.abbatrombone.traz.GameItems;

import java.util.EnumSet;
import java.util.Set;

public class Stratagems {
    private final String stragemName;
    private final StratagemColor staragemColor;
    private final Subtype subtype;

    public enum Subtype {
        EAGLE, ORBITAL,
        WEAPON, BACKPACK, WEAPON_WITH_BACKPACK, MECH, VEHICLE,
        TURRET, MINES, ENCAMPMENT
    }

    public enum StratagemColor {
        RED(EnumSet.of(Subtype.EAGLE, Subtype.ORBITAL)),
        BLUE(EnumSet.of(Subtype.WEAPON, Subtype.BACKPACK, Subtype.WEAPON_WITH_BACKPACK, Subtype.MECH, Subtype.VEHICLE)),
        GREEN(EnumSet.of(Subtype.TURRET, Subtype.MINES, Subtype.ENCAMPMENT));

        private final Set<Subtype> subTypes;

        StratagemColor(Set<Subtype> subTypes) {
            this.subTypes = subTypes;
        }

        public boolean allows(Subtype subtype) {
            return subTypes.contains(subtype);
        }
    }

//   public Stratagems(String stragemName, StratagemColor staragemColor, String subtype){
//       this.stragemName = stragemName;
//       this.staragemColor = staragemColor;
//       this.subtype = subtype;
//   }

    public Stratagems(String name, StratagemColor color, Subtype subtype) {
        if (!color.allows(subtype)) {
            throw new IllegalArgumentException(
                    "Invalid subtype " + subtype + " for color " + color
            );
        }
        this.stragemName = name;
        this.staragemColor = color;
        this.subtype = subtype;
    }

    @Override
    public String toString() {
        return "Stratgem{" +
                "color=" + staragemColor +
                ", subtype='" + subtype + '\'' +
                '}';
    }

}
