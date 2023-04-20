package ru.netology.javaqadiplom;

/**
 * Сберегательный счёт
 * Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
 * Не может уходить в минус (минимальный баланс не может быть отрицательным).
 * Имеет ставку - количество процентов годовых на остаток.
 */
public class SavingAccount extends Account {
    protected int minBalance;
    protected int maxBalance;

    /**
     * Создаёт новый объект сберегательного счёта с заданными параметрами.
     * Если параметры некорректны (мин. баланс больше максимального и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     *
     * @param initialBalance - начальный баланс
     * @param minBalance     - минимальный баланс
     * @param maxBalance     - максимальный баланс
     * @param rate           - неотрицательное число, ставка в процентах годовых на остаток
     */

    // В конструкторе начальный баланс должен быть сверен с минимальным и максимальным балансом.
    //Если он не находится в пределах этого диапазона, должно быть выдано исключение.
    //Добавил еще одно условие сравнения балансов.
    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        if (rate < 0) {
            throw new IllegalArgumentException(
                    "Накопительная ставка не может быть отрицательной, а у вас: " + rate
            );
        }
        if (initialBalance < minBalance || initialBalance > maxBalance) {
            throw new IllegalArgumentException(
                    "Начальный баланс должен быть в пределах от " + minBalance + " до " + maxBalance
            );
        }

        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }


    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */

    // В методе pay условие проверки
    //превышает ли баланс минимальный баланс, неверно.

    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false;
        }
        balance = balance - amount;
        if (balance >= minBalance) {  //if (balance > minBalance)   было
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма пополнения
     * @param amount
     * @return true если операция прошла успешно, false иначе.
     * @return
     */

    //В методе add:
    // 1. Переменная balance обновляется некорректно.
    //Вместо добавление, происходит присвоение
    //Изменим баланс += сумма.
    // 2. Добавим корректное сравненние balance + amount <= maxBalance вместо balance + amount < maxBalance.
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance + amount <= maxBalance) {   //balance + amount < maxBalance
            balance += amount;                  // balance = amount было
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция расчёта процентов на остаток счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте 200 рублей, то при ставке 15% ответ должен быть 30.
     *
     * @return
     */
    @Override
    public int yearChange() {
        return balance / 100 * rate;
    }

    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
}