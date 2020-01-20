package ru.otus.atm_department.atm;

import ru.otus.atm_department.atm.banknote.Ruble;

import java.util.List;
import java.util.SortedMap;

public interface BanknoteContainer {

    List getBanknotesForIssue(int sum);

    int getIssuedSum();

    void foldBanknotes(Ruble ruble, int amount);

    void selectBanknotes(int sum);

    int getEmptyCellAmount();

    /**
     * Get total sum of banknotesContainer in banknote container
     *
     * @return total sum of banknotesContainer
     */
    int getBanknoteContainerBalance();

    SortedMap<Ruble, Integer> getBanknotesContainer();
}
