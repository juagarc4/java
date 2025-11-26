package snacksmachine2.service;

import snacksmachine2.domain.Snack;

import java.util.List;

public interface ISnacksService {

    void addSnack(Snack Snack);

    void showSnacks();

    List<Snack> getSnacks();
}
