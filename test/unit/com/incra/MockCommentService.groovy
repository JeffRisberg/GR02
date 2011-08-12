package com.incra

import java.util.List;

/** 
 * Replacement for full comment services which uses Criteria queries
 * 
 * @author Jeffrey Risberg
 * @since November 2010
 */
class MockCommentService {
	
	/**
	 * Fetch all comments for a given entityType and entityId
	 * @param entityType
	 * @param entityId
	 * @return
	 */
	List<Comment> getComments(EntityType entityType, Long entityId) {
		new ArrayList<Comment>()
	}
}
