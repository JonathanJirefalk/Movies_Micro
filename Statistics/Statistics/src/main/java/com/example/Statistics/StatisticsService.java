package com.example.Statistics;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public Statistics addStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    public Statistics getStatisticsById(Long id) {
        return statisticsRepository.findById(id).orElse(null);
    }

    public Statistics getStatisticsByMovieId(Long movieId) {
        return statisticsRepository.findByMovieId(movieId);
    }

    public void deleteStatistics(Long id) {
        statisticsRepository.deleteById(id);
    }

    public Statistics updateStatistics(Long id, Statistics updatedStatistics) {

        Statistics targetStatistics = getStatisticsById(id);

        if (targetStatistics != null) {

            targetStatistics = updatedStatistics;
            return addStatistics(targetStatistics);
        }else{

            throw new EntityNotFoundException("Statistics not found");
        }
    }
}