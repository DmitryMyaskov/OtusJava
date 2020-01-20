package ru.otus.atm_department.atm;

import ru.otus.atm_department.ATMEventPublisher;
import ru.otus.atm_department.ATMEventSubscriber;
import ru.otus.atm_department.atm.banknote.Ruble;
import ru.otus.atm_department.atm.exception.NotEnoughBanknotesSumException;
import ru.otus.atm_department.atm.exception.SumParityException;
import ru.otus.atm_department.atm.exception.ZeroSumException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ATMImpl emulator class
 */
public class ATMImpl implements ATM, ATMEventPublisher {
    private List<ATMEventSubscriber> subscribers;
    private BanknoteContainer banknoteContainer;

    public ATMImpl() {
        banknoteContainer = new BanknoteContainerImpl();
        subscribers = new LinkedList<>();
    }

    /**
     * Accept the required number of banknotes
     *
     * @param ruble  currency unit
     * @param amount number of banknotes accepted
     */
    public void acceptBanknotes(Ruble ruble, int amount) {
        checkDesiredSumOnZero(ruble.getNominal());
        banknoteContainer.foldBanknotes(ruble, amount);
        createEvent();
    }

    /**
     * Get desired sum
     *
     * @param sum desired sum
     */
    public void getBanknotes(int sum) {
        checkDesiredSumOnZero(sum);
        checkDesiredSumInBanknoteContainer(sum);
        checkNominal(sum);
        String issuedBanknotes = String.valueOf(banknoteContainer.getBanknotesForIssue(sum));
        String issuedSum = String.valueOf(banknoteContainer.getIssuedSum());
        createEvent();
        displayIssuedBanknotes(issuedSum, issuedBanknotes);
    }

    public int getATMBalance() {
        return banknoteContainer.getBanknoteContainerBalance();
    }

    /**
     * Display account status
     */
    public void displayAccountStatus() {
        System.out.println("Account status: " + banknoteContainer.getBanknoteContainerBalance() +
                ", banknotes: " + banknoteContainer.getBanknotesContainer().toString());
    }

    /**
     * Display issued banknotes
     */
    public void displayIssuedBanknotes(String issuedSum, String issuedBanknotes) {
        System.out.println("ISSUED: " + issuedSum + ", banknotes: " + issuedBanknotes);
    }


    // region Checks

    /**
     * Check banknote value is not equal to zero
     *
     * @param nominal banknote nominal value
     */
    private void checkDesiredSumOnZero(int nominal) {
        if (nominal == 0) throw new ZeroSumException("\nОШИБКА: Запрашиваемая сумма не может быть равна 0\n");
    }

    /**
     * Check the desired sum in banknote container
     *
     * @param sum desired sum
     */
    private void checkDesiredSumInBanknoteContainer(int sum) {
        int availableSum = banknoteContainer.getBanknoteContainerBalance();
        if (sum > availableSum) throw new NotEnoughBanknotesSumException(
                String.format("\nОШИБКА: Сумма банкнот в банкомате недостаточна, запрошено: %s, доступно: %s\n", sum, availableSum));
    }

    /**
     * Check the amount is a multiple of the minimum face value of a banknote
     *
     * @param sum desired sum
     */
    private void checkNominal(int sum) {
        if (sum % 50 != 0)
            throw new SumParityException(String.format("\nОШИБКА: Запрашиваемая сумма должна быть кратной номиналу банкнот. " +
                    "\nНоминалы: %s\n", Arrays.toString(Ruble.values())));
    }
    // endregion

    // region Publisher methods
    private void createEvent() {
        notifySubscribers();
    }

    @Override
    public void addSubscriber(ATMEventSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ATMEventSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        subscribers.forEach(subscriber -> subscriber.updateTotalBalance());
    }

    @Override
    public List<ATMEventSubscriber> getSubscribers() {
        return subscribers;
    }

    @Override
    public void removeAllSubscribers() {
        subscribers.clear();
    }
    // endregion
}
