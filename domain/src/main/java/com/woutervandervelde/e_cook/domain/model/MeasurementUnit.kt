package com.woutervandervelde.e_cook.domain.model

enum class MeasurementUnit(val text: String, val pluralText: String) {
    // Volume
    MILLILITER("milliliter", "milliliters"),
    CENTILITER("centiliter", "centiliters"),
    DECILITER("deciliter", "deciliters"),
    LITER("liter", "liters"),
    TEASPOON("teaspoon", "teaspoons"),
    TABLESPOON("tablespoon", "tablespoons"),
    FLUID_OUNCE("fluid ounce", "fluid ounces"),
    CUP("cup", "cups"),
    PINT("pint", "pints"),
    QUART("quart", "quarts"),
    GALLON("gallon", "gallons"),

    // Weight
    MILLIGRAM("milligram", "milligrams"),
    GRAM("gram", "grams"),
    KILOGRAM("kilogram", "kilograms"),
    OUNCE("ounce", "ounces"),
    POUND("pound", "pounds"),

    // Length
    MILLIMETER("millimeter", "millimeters"),
    CENTIMETER("centimeter", "centimeters"),
    METER("meter", "meters"),
    INCH("inch", "inches"),
    FOOT("foot", "feet"),

    // Count
    UNIT("unit", "units"),
    PIECE("piece", "pieces"),
    SLICE("slice", "slices"),

    // Cooking-Specific
    PINCH("pinch", "pinches"),
    DASH("dash", "dashes"),
    DROP("drop", "drops"),
    HANDFUL("handful", "handfuls"),
    BUNCH("bunch", "bunches"),
    STALK("stalk", "stalks"),
}