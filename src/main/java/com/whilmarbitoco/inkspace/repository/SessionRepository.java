package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Session;
import com.whilmarbitoco.inkspace.model.User;

import java.util.List;
import java.util.Optional;

public class SessionRepository extends BaseRepository<Session> {

    private final UserRepository userRepo = new UserRepository();

    public SessionRepository() {
        super(Session.class);
    }

    public Session getByUser(int id) {
        List<Session> sessions = findWhere("userID", "=", id);
        return sessions.isEmpty() ? null : sessions.getFirst();
    }

    public Optional<User> getCurrentUser() {
        List<Session> result = findWhere("SessionID", ">", "0 LIMIT 1");
        if (result.isEmpty()) return null;

        return userRepo.findByID(result.getFirst().getUserID());
    }
}
