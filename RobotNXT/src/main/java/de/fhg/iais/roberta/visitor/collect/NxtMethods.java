package de.fhg.iais.roberta.visitor.collect;

public enum NxtMethods {
    BOOL_OUT, // Boolean output
    COLOR_OUT, // Color output
    FACTORIAL, // some trigonometric functions need this
    IDTY, // for some list ops, don't really know why TODO see if this can be removed
    SANITISE_FROM_START, // for list get/set
    SANITISE_FROM_END, // for list get/set
    INSERTION_SORT, // for median
    FIND_FIRST_NUM,
    FIND_LAST_NUM,
    FIND_FIRST_STR,
    FIND_LAST_STR,
    FIND_FIRST_BOOL,
    FIND_LAST_BOOL,
}