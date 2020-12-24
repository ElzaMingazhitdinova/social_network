package com.example.testingrestdocs.objects;

import java.util.ArrayList;

public class Characteristic {
    private String name;
    private CharacteristicType type;
    private ArrayList<String> variants;
    private boolean refillable;

    private void checkVariant(String variant) throws IllegalArgumentException {
        switch (type) {
            case INTEGER: {
                try {
                    Integer.parseInt(variant);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(String.format("Variant: %s does not match the type: %s",
                            variant, this.type.toString()));
                }
            }
            case DOUBLE: {
                try {
                    Double.parseDouble(variant);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(String.format("Variant: %s does not match the type: %s",
                            variant, this.type.toString()));
                }
            }
        }
    }

    private void checkVariants(ArrayList<String> variants) throws IllegalArgumentException {
        for (String variant : variants) {
            checkVariant(variant);
        }
    }

    public Characteristic(String name, CharacteristicType type) {
        this.name = name;
        this.type = type;
        this.variants = new ArrayList<>();
        this.refillable = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Characteristic other = (Characteristic) o;
        if (!name.equals(other.name))
            return false;
        if (type != other.type)
            return false;
        if (!variants.equals(other.variants))
            return false;
        return refillable == other.refillable;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + variants.hashCode();
        result = 31 * result + type.toString().hashCode();
        result = 31 * result + ((Boolean) refillable).hashCode();
        return result;
    }

    public Characteristic(String name, CharacteristicType type, boolean refillable) {
        this.name = name;
        this.type = type;
        this.variants = new ArrayList<String>();
        this.refillable = refillable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacteristicType getType() {
        return type;
    }

    public void setType(CharacteristicType type) {
        this.type = type;
        try {
            checkVariants(variants);
        } catch (IllegalArgumentException e) {
            variants.clear();
        }
    }

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<String> variants) throws IllegalArgumentException {
        if (!variants.isEmpty()) {
            checkVariants(variants);
        }
        this.variants = variants;
    }

    public void addVariant(String variant) throws IllegalStateException, IllegalArgumentException {
        if (!refillable) {
            throw new IllegalStateException("Not refillable, could not add variant");
        }
        checkVariant(variant);
        this.variants.add(variant);
    }

    public boolean isRefillable() {
        return refillable;
    }

    public void setRefillable(boolean refillable) {
        this.refillable = refillable;
    }
}