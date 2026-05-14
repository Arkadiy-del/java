package org.example;

class Polynomial {
    private double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = removeZeros(coefficients);
    }

    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double first = i < this.coefficients.length ? this.coefficients[i] : 0;
            double second = i < other.coefficients.length ? other.coefficients[i] : 0;

            result[i] = first + second;
        }

        return new Polynomial(result);
    }

    public Polynomial subtract(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double first = i < this.coefficients.length ? this.coefficients[i] : 0;
            double second = i < other.coefficients.length ? other.coefficients[i] : 0;

            result[i] = first - second;
        }

        return new Polynomial(result);
    }

    public Polynomial multiply(Polynomial other) {
        double[] result = new double[this.coefficients.length + other.coefficients.length - 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                result[i + j] += this.coefficients[i] * other.coefficients[j];
            }
        }

        return new Polynomial(result);
    }

    public DivisionResult divide(Polynomial other) {
        if (other.isZero()) {
            throw new ArithmeticException("Деление на нулевой многочлен невозможно");
        }

        double[] remainder = this.coefficients.clone();

        int quotientLength = Math.max(1, this.getDegree() - other.getDegree() + 1);
        double[] quotient = new double[quotientLength];

        while (getDegree(remainder) >= other.getDegree() && !isZero(remainder)) {
            int degreeDifference = getDegree(remainder) - other.getDegree();

            double coefficient =
                    remainder[getDegree(remainder)] / other.coefficients[other.getDegree()];

            quotient[degreeDifference] = coefficient;

            for (int i = 0; i <= other.getDegree(); i++) {
                remainder[i + degreeDifference] -= coefficient * other.coefficients[i];
            }

            remainder = removeZeros(remainder);
        }

        return new DivisionResult(new Polynomial(quotient), new Polynomial(remainder));
    }

    public int getDegree() {
        return coefficients.length - 1;
    }

    private int getDegree(double[] array) {
        return removeZeros(array).length - 1;
    }

    private boolean isZero() {
        return coefficients.length == 1 && coefficients[0] == 0;
    }

    private boolean isZero(double[] array) {
        double[] temp = removeZeros(array);
        return temp.length == 1 && temp[0] == 0;
    }

    private double[] removeZeros(double[] array) {
        int last = array.length - 1;

        while (last > 0 && Math.abs(array[last]) < 0.000001) {
            last--;
        }

        double[] result = new double[last + 1];

        for (int i = 0; i <= last; i++) {
            if (Math.abs(array[i]) < 0.000001) {
                result[i] = 0;
            } else {
                result[i] = array[i];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        if (isZero()) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        for (int i = coefficients.length - 1; i >= 0; i--) {
            double coefficient = coefficients[i];

            if (coefficient == 0) {
                continue;
            }

            if (result.length() > 0) {
                if (coefficient > 0) {
                    result.append(" + ");
                } else {
                    result.append(" - ");
                }
            } else if (coefficient < 0) {
                result.append("-");
            }

            double absCoefficient = Math.abs(coefficient);

            if (i == 0) {
                result.append(absCoefficient);
            } else if (i == 1) {
                result.append(absCoefficient).append("x");
            } else {
                result.append(absCoefficient).append("x^").append(i);
            }
        }

        return result.toString();
    }
}
