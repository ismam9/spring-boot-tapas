package com.mine.springboot.tapas.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.tapas.app.models.dao.IVoteDao;
import com.mine.springboot.tapas.app.models.entity.Vote;

@Service
public class VoteServiceImpl implements IVoteService{
	
	@Autowired
	private IVoteDao voteDao;
	
	@Override
	public void save(Vote vote) {
		voteDao.save(vote);
	}

	@Override
	public Vote findVoteByUserId(Long id) {
		// TODO Auto-generated method stub
		return voteDao.findVoteByUserId(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		voteDao.deleteById(id);
	}

	@Override
	public Vote selectVoteByUserAndTapa(Long userId, Long tapaId) {
		// TODO Auto-generated method stub
		return voteDao.selectVoteByUserAndTapa(userId, tapaId);
	}

	@Override
	public Vote findVoteById(Long id) {
		// TODO Auto-generated method stub
		return voteDao.findById(id).orElse(null);
	}
}
