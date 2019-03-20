package org.sonar.samples.java.checks;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.utils.WildcardPattern;

public final class DcitsPatternUtils {

  private DcitsPatternUtils() {
  }

  public static WildcardPattern[] createPatterns(String patterns) {
    String[] p = StringUtils.split(patterns, ',');
    WildcardPattern[] result = new WildcardPattern[p.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = WildcardPattern.create(StringUtils.trim(p[i]), ".");
    }
    return result;
  }

}