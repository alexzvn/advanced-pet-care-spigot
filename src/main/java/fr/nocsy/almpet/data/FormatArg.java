package fr.nocsy.almpet.data;

public class FormatArg {
    private String toReplace;
    private String replaceWith;

    public FormatArg(String toReplace, String replaceWith) {
        /* 10 */ this.toReplace = toReplace;
        /* 11 */ this.replaceWith = replaceWith;
    }

    public String applyToString(String toApply) {
        /* 21 */ return toApply.replace(this.toReplace, this.replaceWith);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\FormatArg.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
