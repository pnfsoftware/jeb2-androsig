/*
 * JEB Copyright (c) PNF Software, Inc.
 * All rights reserved.
 * This file shall not be distributed or reused, in part or in whole.
 */
package com.pnf.androsig.apply.matcher;

import java.util.Map;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

/**
 * @author Cedric Lucas
 *
 */
public class DatabaseMatcherParameters {
    private static final ILogger logger = GlobalLog.getLogger(DatabaseMatcherParameters.class);

    // Parameters
    public int methodSizeBar = 0; // will skip method if its instruction size is no great than methodSizeBar
    public double matchedInstusPercentageBar = 0; // will skip the class if (total matched instructions / total instructions) is no greater than matchedMethodsPercentageBar

    public static DatabaseMatcherParameters parseParameters(Map<String, String> executionOptions) {
        DatabaseMatcherParameters params = new DatabaseMatcherParameters();
        String methodSizeBar = executionOptions.get("methodSizeBar");
        int methodSizeBarInt = 6;
        if(!Strings.isBlank(methodSizeBar)) {
            try {
                methodSizeBarInt = Integer.parseInt(methodSizeBar);
            }
            catch(NumberFormatException e) {
                logger.warn("Illegal methodSizeBar parameter: \"%s\" (must be an integer)",
                        Formatter.escapeString(methodSizeBar));
            }
            if(methodSizeBarInt < 0) {
                methodSizeBarInt = 6;
            }
        }
        params.methodSizeBar = methodSizeBarInt;

        String matchedInstusPercentageBar = executionOptions.get("matchedInstusPercentageBar");
        Double matchedInstusPercentageBarDbl = 0.5;
        if(!Strings.isBlank(matchedInstusPercentageBar)) {
            try {
                matchedInstusPercentageBarDbl = Double.parseDouble(matchedInstusPercentageBar);
            }
            catch(NumberFormatException e) {
                logger.warn("Illegal matchedInstusPercentageBar parameter: \"%s\" (must be a double)",
                        Formatter.escapeString(matchedInstusPercentageBar));
            }
            if(matchedInstusPercentageBarDbl < 0.0 || matchedInstusPercentageBarDbl > 1.0) {
                matchedInstusPercentageBarDbl = 0.5;
            }
        }
        params.matchedInstusPercentageBar = matchedInstusPercentageBarDbl;
        return params;
    }
}