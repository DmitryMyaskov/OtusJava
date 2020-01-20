package ru.otus.atm_department.atm;

import ru.otus.atm_department.ATMEventPublisher;
import ru.otus.atm_department.atm.banknote.Ruble;

public interface ATM {

    void acceptBanknotes(Ruble ruble, int amount);

    void getBanknotes(int sum);

    int getATMBalance();

    void displayAccountStatus();

    void displayIssuedBanknotes(String issuedSum, String issuedBanknotes);
}
