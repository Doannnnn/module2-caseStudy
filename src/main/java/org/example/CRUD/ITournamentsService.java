package org.example.CRUD;

import org.example.model.Tournaments;

import java.util.List;

public interface ITournamentsService {
    List<Tournaments> getAllTournaments();
    Tournaments findTournaments(long id);
    void updateTournaments(long id, Tournaments tournaments);
    void deleteTournaments(long id);
    void createTournaments(Tournaments tournaments);
    Tournaments searchName(String name);
}
