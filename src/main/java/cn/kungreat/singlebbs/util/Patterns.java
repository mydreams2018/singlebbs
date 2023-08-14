package cn.kungreat.singlebbs.util;

import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern PASSWORD = Pattern.compile("\\p{Graph}{8,12}");

    public static final Pattern USER_ALIAS = Pattern.compile(".{2,12}");


}
