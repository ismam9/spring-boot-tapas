package com.mine.springboot.tapas.app.models.services;

import com.mine.springboot.tapas.app.models.entity.Vote;

public interface IVoteService {
	
	public void save(Vote vote);
	
	public Vote findVoteById(Long id);
	
	public Vote findVoteByUserId(Long id);
	
	public Vote selectVoteByUserAndTapa(Long userId, Long tapaId);
	
	public void delete(Long id);
}
