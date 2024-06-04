package org.miniproject.elearningplatform.repository;

import org.miniproject.elearningplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
    // Common operations
}

