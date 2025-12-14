package me.abbatrombone.traz.GameItems;

public class Stratagems {
    private final StaragemColor staragemColor;
    private final String stragemName;

    enum StaragemColor{
        Red("Eagle","Orbital","N/A","N/A","N/A"),
        Blue("Weapon","Backpack","Weapon With Backpack","Mech","Vehicle"),
        Green("Turret","Mines","Encampment","N/A","N/A");

        private final String subType1;
        private final String subType2;
        private final String subType3;
        private final String subType4;
        private final String subType5;

        private StaragemColor(String subType1,String subType2,String subType3,String subType4,String subType5){
            this.subType1 = subType1;
            this.subType2 = subType2;
            this.subType3 = subType3;
            this.subType4 = subType4;
            this.subType5 = subType5;
        }

        public String getSubType1() {
            return subType1;
        }

        public String getSubType2() {
            return subType2;
        }

        public String getSubType3() {
            return subType3;
        }

        public String getSubType4() {
            return subType4;
        }

        public String getSubType5() {
            return subType5;
        }
    }

    public Stratagems(String stragemName,StaragemColor staragemColor){
        this.stragemName = stragemName;
        this.staragemColor = staragemColor;
    }
}
