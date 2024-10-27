package guru.qa;

public class Main {
    public static void main(String[] args) {
        byte aByte = 0b00000001;
        short aShort = 32767;
        int aInt = 10;
        int bigInt = Integer.MAX_VALUE;
        double aDouble = 15.0;

        System.out.printf("%nПостфиксные и префиксные операции: %d%n",
                aInt + ++aInt - aInt--);

        System.out.printf("""
                %nСначала, перед вычислением a + b * c значения переменных будут расширены до int, затем,
                поскольку константа 15 имеет тип long, перед вычитанием результат вычисления будет увеличен до long.
                После этого, поскольку литерал 1.5 имеет тип float перед сложением с этим литералом результат
                вычисления a + b * c – 15L будет расширен до float. Перед выполнением сложения с числом 1.08 результат
                предыдущих вычислений будет расширен до double (поскольку вещественные константы по умолчанию
                имеют тип double) и, наконец, перед выполнением последнего сложения литерал 10 (по умолчанию int) будет
                расширен до double. Таким образом, результат вычисления выражения будет иметь тип double - %.2f.
                """, aByte + aByte * aShort - 15L + 1.5F + 1.08 - 10);

        System.out.printf("""
                %nЛогические операции:
                \ttrue && false -> %1$b
                \ttrue && true -> %2$b
                \ttrue || false -> %3$b
                \tfalse || false -> %4$b
                \t!true -> %5$b
                """, true && false, true && true, true || false, false || false, !true);

        System.out.printf("%nПереполнение при сложении %d и %d: %d%n", aInt, bigInt, aInt + bigInt);

        System.out.printf("""
                %nОперации с разными типами
                \t Сложение int и double: %1$.1f
                \t Вычитание int и double: %2$.1f
                \t Умножение int и double: %3$.1f
                \t Деление int и double: %4$.1f
                """, aInt + aDouble, aDouble - aInt, aInt * aDouble, aDouble / aInt);
    }
}
