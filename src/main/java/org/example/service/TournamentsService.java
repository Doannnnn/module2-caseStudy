package org.example.service;

import org.example.CRUD.ITournamentsService;
import org.example.model.Schedule;
import org.example.model.Tournaments;
import org.example.utils.FileUtils;

import java.util.List;

public class TournamentsService implements ITournamentsService {
    private final String fileTournaments = "./data/tournaments.csv";
    @Override
    public List<Tournaments> getAllTournaments() {
        return FileUtils.readData(fileTournaments, Tournaments.class);
    }

    @Override
    public Tournaments findTournaments(long id) {
        List<Tournaments> tournaments = getAllTournaments();
        return tournaments.stream().filter(tournament -> tournament.getId() == id).findFirst().orElseThrow(null);
    }

    @Override
    public void updateTournaments(long id, Tournaments tournaments) {
        List<Tournaments> tournaments1 = getAllTournaments();
        for (Tournaments t : tournaments1){
            if (t.getId() == id){
                t.setName(tournaments.getName());
                t.setAddress(tournaments.getAddress());
                t.setNumbersTeam(tournaments.getNumbersTeam());
            }
        }
        FileUtils.writeData(fileTournaments, tournaments1);
    }

    @Override
    public void deleteTournaments(long id) {
        List<Tournaments> tournaments1 = getAllTournaments();
        Tournaments tournaments = null;
        for (Tournaments t : tournaments1){
            if (t.getId() == id){
                tournaments = t;
            }
        }
        tournaments1.remove(tournaments);
        FileUtils.writeData(fileTournaments, tournaments1);
    }

    @Override
    public void createTournaments(Tournaments tournaments) {
        List<Tournaments> tournaments1 = getAllTournaments();
        tournaments.setId(tournaments1.size() + 1);
        tournaments1.add(tournaments);
        FileUtils.writeData(fileTournaments, tournaments1);
    }

    @Override
    public Tournaments searchName(String name) {
        List<Tournaments> tournaments = getAllTournaments();
        if (!tournaments.stream().anyMatch(tournament -> tournament.getName().equalsIgnoreCase(name))){
            System.out.println("Không tìm thấy tên " + name);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            return tournaments.stream().filter(tournament -> tournament.getName().equalsIgnoreCase(name)).findFirst().get();
        }
        return null;
    }
    public String getNameTournamentsByTeamId(long idTournaments) {
        List<Tournaments> tournaments = getAllTournaments();
        for (Tournaments t : tournaments){
            if (t.getId() == idTournaments){
                return t.getName();
            }
        }
        return null;
    }
}
