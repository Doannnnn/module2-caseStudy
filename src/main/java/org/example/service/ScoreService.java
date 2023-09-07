package org.example.service;

import org.example.model.Card;
import org.example.model.Score;
import org.example.utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreService {
    public final String fileScore = "./data/score.csv";
    public List<Score> getAllScore() {
        return FileUtils.readData(fileScore, Score.class);
    }
    public List<Score> getAllScoreByTournamentsId(long idTournaments) {
        List<Score> scores = getAllScore();
        List<Score> results = scores.stream()
                .filter(score -> score.getIdTournaments() == idTournaments)
                .collect(Collectors.toList());
        return results;
    }
    public Score findScore(long id) {
        List<Score> users = getAllScore();
        Score c = users.stream().filter(score -> score.getId() == id).findFirst().orElseThrow(null);
        return c;
    }

    public void updateScore(long id, Score score) {
        List<Score> scores = getAllScore();
        for (Score c : scores) {
            if (c.getId() == id) {
                c.setIdTournaments(score.getIdTournaments());
                c.setIdTeam(score.getIdTeam());
                c.setIdSchedule(score.getIdSchedule());
                c.setIdPlayer(score.getIdPlayer());
                c.setQuantity(score.getQuantity());
            }
        }
        FileUtils.writeData(fileScore, scores);
    }

    public void deleteScore(long id) {
        List<Score> scores = getAllScore();
        Score score = null;
        for (Score c : scores) {
            if (c.getId() == id) {
                score = c;
            }
        }
        scores.remove(score);
        FileUtils.writeData(fileScore, scores);
    }

    public void createScore(Score score) {
        List<Score> scores = getAllScore();
        score.setId(scores.size() + 1);
        scores.add(score);

        FileUtils.writeData(fileScore, scores);
    }
}
